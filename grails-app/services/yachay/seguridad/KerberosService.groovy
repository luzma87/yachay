package yachay.seguridad

import yachay.proyectos.ModificacionV2

import java.sql.Connection
import java.sql.DriverManager
import java.text.SimpleDateFormat
import org.springframework.jdbc.core.JdbcTemplate

/**
 * Principal servicio para seguriidad: crea logs
 */
class KerberosService {

    boolean transactional = true
    def dbConnectionService
    def dataSource
    def noAuditables = ["constraints", "log", "auditable", "version", "errors", "attached", "metaClass", "mapping", "class", "id", "hasMany", "dirty", "dirtyPropertyNames"]
    def sessionFactory
    def messageSource
    def session
    //principal servicio de seguridad

    /**
     * Remplaza la función save por defecto para crear una entrada de log con la acción ejecutada
     * @param params los paraámetros para hacer el save
     * @param dominio el dominio que va a guardarse
     * @param perfil el perfil del usuario efectuando la acción
     * @param usuario el usuario efectuando la acción
     * @return el objeto guardado
     */
    public save(params, dominio, perfil, usuario) {
        session = sessionFactory.getCurrentSession()
        def nuevo = dominio.newInstance()
        def old
        def listAudt = []
        def band = true
        def audt
        def sql
        def sdf = new SimpleDateFormat("dd/MM/yyyy");
        def f

        try {
            params.each {
                if (it?.key =~ "fecha") {
                    if (it.value != "" && it.value != null && it.value != "null" && it.value?.trim().size() > 6) {
                        f = sdf.parse(it.value?.toString())
                        it.value = f
                    } else {
                        it.value = null
                    }
                    //println "clase del dato " + it.key + " ==>   valor final " + it.value
                }

            }
        } catch (e) {
            println "error params " + e
        }
        try {

            if (params.id != null && params.id != "") {
                old = dominio.get(params.id)
                def clone = dominio.newInstance(old.properties)
                nuevo = old

                // println "   kerberos----------------------------\n\n " + dominio

                if (nuevo.properties.keySet().contains("lugar") && params.keySet().contains("lugar")) {
                    if (params.lugar != null && params.lugar != "" && params.lugar != "null") {
                        nuevo.lugar = params.lugar
                        params.remove("lugar.id")
                    } else
                        nuevo.lugar = old.lugar
                    //println "params lugar " + nuevo.lugar
                }

                params.each {
                    try {
                        switch (nuevo[it.key].class) {
                            case "class java.lang.Float":
                                // println "0   WTF CAMBIA EL FORMATO DE LOS NUMEROS!!!!"
                                // println " tipo " + it.key + " --> " + nuevo[it.key].class
                                params[it.key] = params[it.key].toFloat()
                                break;
                            case "class java.lang.Double":
                                // println "1   WTF CAMBIA EL FORMATO DE LOS NUMEROS!!!!"
                                //params[it.key] =  params[it.key].replaceAll("\\.",",")
                                params[it.key] = params[it.key].toDouble()
                                //  println " tipo " + it.key + " --> " + nuevo[it.key].class + " value  " + it
                                break;
                        }
                    } catch (e) {
//                        println "Error pendejo en el cambio de formato de numeros"
                    }
                }

                nuevo.properties = params
                old = dominio.get(params.id)
                def ignore = old.properties.auditable["ignore"] + noAuditables
                nuevo.id = old.id

                clone.properties.each {
                    if (clone.properties?.hasMany?.keySet()?.contains(it.key)) {
                        ignore.add(it.key)
                        noAuditables.add(it.key)
                    }
                    if (it.key != "constraints") {
                        def n = nuevo.properties[it.key]

                        if ((nuevo.properties[it.key]) && (nuevo.properties[it.key]).class == java.lang.String && (nuevo.properties[it.key])?.size() > 255) {
                            n = (nuevo.properties[it.key]).substring(0, 255)
                        }
                        if (it.value != nuevo.properties[it.key]) {
                            //println "asi"
                            if (!ignore.contains(it.key)) {
                                // println "dif ! " + it.key + " viejo  " + it.value + "  actual " + nuevo.properties[it.key] + " en el log " + n
                                sql = """insert into audt (audt__id,usro__id,prfl__id,audtaccn,audtctrl,reg_id,audttbla,audtcmpo,audtoldv,audtnewv,audtfcha,audtoprc)
                                values (default,${usuario.id},${perfil.id},'${params.actionName}','${
                                    params.controllerName
                                }',
                                           ${old.id.toInteger()},'${dominio.getName()}','${it.key}','${
                                    it.properties[it.key]
                                }','${n}','${new Date().format("yyyy/MM/dd hh:mm:ss")}','UPDATE') """
                                listAudt.add(sql)
                                sql = null
                            }

                        }
                    }
                }
                if (nuevo.save(flush: true)) {
                    def cn = dbConnectionService.getConnection()
                    listAudt.each {
                        try {
//                            println "sql " + it
                            if (!cn.execute(it)) {
                                band = false
                                //aqui se deberia hacer algo
                            }
                        } catch (e) {
                            println "ERROR!!!: error en la auditoria "
                            println " audt " + e
                        }

                    }
                    cn.close()
                    session.flush()

                    return nuevo

                } else {
                    println "ERROR!!!: error en el save " + nuevo.properties.errors

                    return nuevo
                }

            } else {
                //println "INSERT \n" + dominio
                nuevo.properties = params
                audt = new Audt()
                audt.id = null
                audt.usuario = usuario
                audt.operacion = "INSERT"
                audt.accion = (params.actionName) ? params.actionName : "N.A."
                audt.perfil = perfil
                audt.controlador = (params.controllerName) ? params.controllerName : "N.A."
                audt.old_value = null
                audt.new_value = null
                audt.fecha = new Date()
                audt.dominio = dominio.getName()
                audt.campo = null
//                nuevo.properties.each{
                //                    if(it.key!="constraints")
                //                    println "-> "+it.key+" === "+it.value
                //                }
                if (nuevo.save(flush: true)) {
                    audt.registro = nuevo.id
                    if (!audt.save(flush: true)) {
                        band = false
                        println "ERROR!!!: error en la auditoria "
                        //aqui deberia hacer algo
                    }
                    session.flush()
                    session.clear()
                    //session.clear()
                    //println "si grabo wtf despues del flush "
                    return nuevo

                } else {
                    println "errores :" + nuevo.properties.errors
                    println "ERROR!!!: error en el save (save)"
                    return nuevo
                }
            }


        } catch (e) {
            println "ERROR: kerberos"
            println "catch " + e + "  causa " + e.cause
            return nuevo
        }

    }

    /**
     * Remplaza la función delete por defecto para crear una entrada de log con la acción ejecutada
     * @param params toma params.id para eliminar, p.controllerName, p.actionName
     * @param dominio el dominio que va a guardarse
     * @param perfil el perfil del usuario efectuando la acción
     * @param usuario el usuario efectuando la acción
     * @return un boolean que indica si se efectuó exitosamente la eliminación
     */
    public delete(params, dominio, perfil, usuario) {
        session = sessionFactory.getCurrentSession()
        //println "delete kerberos " + params
        def old = dominio.get(params.id)

        def ignore = old.properties.auditable["ignore"] + noAuditables
        def listAudt = []
        def band = true
        def sql
        //println " DELETE KERBEROS params !!!\n " + params
        old.properties.each {
            if (!ignore.contains(it.key)) {
                def anterior = old.properties[it.key]
//                println anterior
//                println anterior?.class
//                println anterior?.class.toString()
                if (anterior && anterior.class.toString() =~ "app") {
//                    println " original " + anterior + " clase " + anterior.class
//                    println "aqui deberia cambiar "
                    anterior = anterior.id
//                    println "original value cambiado " + anterior
                }
                if (anterior && anterior.class == java.lang.String) {
//                    println "replaceAlls"
                    anterior = (old.properties[it.key]).replaceAll("\\\n", "").replaceAll("\\\t", "").replaceAll(";", "").replaceAll("'", "").replaceAll("\"", "")
                    if (anterior.size() > 255) {
                        anterior = anterior[0..254]
                    }
//                    println "despues de remplazar " + anterior
                }
//                println "+++++++++++++++++++++++++++++++++="
                sql = """insert into audt (audt__id,usro__id,prfl__id,audtaccn,audtctrl,reg_id,audttbla,audtcmpo,audtoldv,audtnewv,audtfcha,audtoprc)
                                values (default,${usuario.id},${perfil.id},'${params.actionName}','${
                    params.controllerName
                }',
                                ${old.id.toInteger()},'${dominio.getName()}','${it.key}','${anterior}',' BORRADO ','${
                    new Date().format("yyyy/MM/dd hh:mm:ss")
                }','DELETE') """

                listAudt.add(sql)
                sql = null
            }

        }

        try {
            old.delete(flush: true)

        } catch (e) {
            println "error delete " + e
            return false
        }
        def cn = dbConnectionService.getConnection()
        listAudt.each {
//            println it
            cn.execute(it)
        }
        cn.close()
        //println "borrando !! : " + band
        session.flush()
        if (band)
            return true
        else
            return false
    }

    /**
     * Genera una entrada en la tabla de auditoría
     * @param params utiliza actionName y controllerName para guardar en el log
     * @param dominio el dominio que va a guardarse
     * @param campo el campo modificado
     * @param newValue el valor nuevo del campo
     * @param oldValue el valor anterior del campo
     * @param perfil el perfil del usuario efectuando la acción
     * @param usuario usuario el usuario efectuando la acción
     * @return un boolean que indica si se efectuó exitosamente la inserción
     */
    public generarEntradaAuditoria(params, dominio, campo, newValue, oldValue, perfil, usuario) {
        def cn = dbConnectionService.getConnection()
        def band = true
        def sql = """insert into audt (audt__id,usro__id,prfl__id,audtaccn,audtctrl,reg_id,audttbla,audtcmpo,audtoldv,audtnewv,audtfcha,audtoprc)
                                values (default,${usuario.id},${perfil.id},'${params.actionName}','${
            params.controllerName
        }',
                                           ${params.id},'${dominio.getName()}','${campo}','${oldValue}',' ${
            newValue
        } ','${new Date().format("yyyy/MM/dd hh:mm:ss")}','UPDATE') """
        //println "entrada audt " + sql
        if (!cn.execute(sql)) {
            band = false
            println "ERROR: en auditoria (delete)"
            //aqui se deberia hacer algo
        }
        cn.close()
        return band
    }

    /*
    * flow.producto= kerberosService.saveObject( flow.producto,ProductoBancario, session.perfil, session.usuario,actionName,controllerName,session)
     if(flow.producto.errors.getErrorCount()!=0){
     MANEJAR ERRORES AQUI
     } else {
     NO OCURRIERON ERRORES
     }*
     */
    /**
     * Guarda un objeto
     * @param nuevo objeto a guardar
     * @param dominio dominio que se va a guardar
     * @param perfil el perfil del usuario efectuando la acción
     * @param usuario el usuario efectuando la acción
     * @param actionName nombre de la acción que llamó a la función
     * @param controllerName nombre del controlador que llamó a la función
     * @param session objeto session
     * @return el objeto guardado
     */
    public saveObject(nuevo, dominio, perfil, usuario, actionName, controllerName, session) {
        session = sessionFactory.getCurrentSession()
        def old
        def audt
        def listAudt = []
        def sql
        try {

            nuevo.constraints.each { pr ->

                if (pr.getValue().propertyType == java.lang.Integer || pr.getValue().propertyType == java.lang.Float || pr.getValue().propertyType == java.lang.Double) {

                    def val = nuevo[pr.getKey()]
                    val = val.toString()

                    val.replaceAll("\\.", "")
                    val.replaceAll(",", "\\.")

                    if (pr.getValue().propertyType == java.lang.Integer) {
                        val = val.toInteger()
                    } else if (pr.getValue().propertyType == java.lang.Float) {
                        val = val.toFloat()
                    } else if (pr.getValue().propertyType == java.lang.Double) {
                        val = val.toDouble()
                    }

                    nuevo[pr.getKey()] = val
                }

            }

            if (nuevo.id == null) { //Insert
                audt = null
                audt = new Audt()

                if (nuevo.save(flush: false)) {
                    //println "si grabo nuevo wtf " + nuevo.class + " !!! audt " + audt.id + " " + audt
                    audt.withTransaction { status ->
                        audt.registro = nuevo.id
                        audt.usuario = usuario
                        audt.operacion = "INSERT"
                        audt.accion = (actionName) ? actionName : "N.A."
                        audt.perfil = perfil
                        audt.controlador = (controllerName) ? controllerName : "N.A."
                        audt.old_value = null
                        audt.new_value = null
                        audt.fecha = new Date()
                        audt.dominio = nuevo.class
                        audt.campo = null
                        audt.save(flush: false)
                        if (status.isCompleted())
                            println "si se completo"
                    }
                    session.flush()
                    return nuevo
                    audt = null
                } else {
                    println "errores :" + nuevo.properties.errors
                    println "ERROR!!!: error en el save (save)"
                    return nuevo
                }
            } else { // Update
                old = dominio.get(nuevo.id)
                def ignore = old.properties.auditable["ignore"] + noAuditables
                def names = old.dirtyPropertyNames

                for (name in names) {
                    def originalValue = old.getPersistentValue(name)
                    def newValue = old.properties[name]
                    if (originalValue?.class?.toString() =~ "app") {

                        originalValue = originalValue?.id
                        newValue = newValue.id

                    }
                    if (!ignore.contains(name)) {
                        sql = "insert into audt (audt__id,usro__id,prfl__id,audtaccn,audtctrl,reg_id,audttbla,audtcmpo,audtoldv,audtnewv,audtfcha,audtoprc) values (default,${usuario.id},${perfil.id},'${actionName}','${controllerName}',${old.id.toInteger()},'${nuevo.class}','${name}','${originalValue}','${newValue}','${new Date().format("yyyy/MM/dd hh:mm:ss")}','UPDATE') "

                        listAudt.add(sql)
                        sql = null
                    }
                }//for
                old.discard()
                old.finalize()
                old = null
//                println "antes del save " + nuevo.id
                if (nuevo.save(flush: true)) {
                    // println "si GRABO "
                    session.flush()
                    def cn = dbConnectionService.getConnection()
                    listAudt.each {
                        try {

                            cn.execute(it.trim().toString())

                        } catch (e) {
                            println "ERROR!!!: error en la auditoria "
                            println " audt " + e
                        }

                    }
                    cn.close()
                    return nuevo

                } else {
                    println "ERROR!!!: error en el save " + nuevo.properties.errors

                    return nuevo
                }


            }
        } catch (e) {
            println "\n error try save ob " + e
            return nuevo
        }

    }

    /**
     * Guarda una modificación
     * @param nuevo objeto a guardar
     * @param dominio dominio que se va a guardar
     * @param usuario el usuario efectuando la acción
     * @param session objeto session
     * @return el objeto guardado
     */
    public saveModificacion(nuevo, dominio, usuario, session) {
        session = sessionFactory.getCurrentSession()
        def old
        def audt
        def listAudt = []
        def sql
        try {

            nuevo.constraints.each { pr ->

                if (pr.getValue().propertyType == java.lang.Integer || pr.getValue().propertyType == java.lang.Float || pr.getValue().propertyType == java.lang.Double) {

                    def val = nuevo[pr.getKey()]
                    val = val.toString()

                    val.replaceAll("\\.", "")
                    val.replaceAll(",", "\\.")

                    if (pr.getValue().propertyType == java.lang.Integer) {
                        val = val.toInteger()
                    } else if (pr.getValue().propertyType == java.lang.Float) {
                        val = val.toFloat()
                    } else if (pr.getValue().propertyType == java.lang.Double) {
                        val = val.toDouble()
                    }

                    nuevo[pr.getKey()] = val
                }

            }

            if (nuevo.id == null) { //Insert

                def mod = new ModificacionV2()

                if (nuevo.save(flush: false)) {

                    ModificacionV2.withTransaction { status ->

                        mod.usuario = usuario
                        mod.newValue = null
                        mod.oldValue = null
                        mod.campo = "INSERT"
                        mod.dominio = dominio
                        mod.id_remoto = nuevo.id.toInteger()
                        mod.save(flush: false)
                    }
                    session.flush()
                    return nuevo
                    audt = null
                } else {
                    println "errores :" + nuevo.properties.errors
                    println "ERROR!!!: error en el save (save)"
                    return nuevo
                }
            } else { // Update
                old = dominio.get(nuevo.id)
                def ignore = old.properties.auditable["ignore"] + noAuditables
                def names = old.dirtyPropertyNames
                def tipo

                for (name in names) {
                    def originalValue = old.getPersistentValue(name)
                    def newValue = old.properties[name]
                    if (originalValue?.class?.toString() =~ "app") {
                        println " clase tipo  " + old[name].class.toString()
                        tipo = old[name].class.toString().split(" ")[1]
                        originalValue = originalValue?.id
                        newValue = newValue.id
                    } else {
                        if (old.properties.getClass() != java.lang.String) {
                            if (old.properties.getClass() == java.util.Date)
                                tipo = "date"
                            else
                                tipo = "number"
                        } else {
                            tipo = "string"
                        }
                    }
                    if (!ignore.contains(name)) {

                        def mod = new ModificacionV2([usuario: usuario, campo: name, dominio: new org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass(dominio).getName(), id_remoto: old.id.toInteger(), newValue: newValue, oldValue: originalValue, tipo: tipo])
                        listAudt.add(mod)
                        mod = null

                    }
                }//for
                old.discard()
                old.finalize()
                old = null
                println "antes del save " + nuevo.id
                if (nuevo.save(flush: true)) {
                    println "si GRABO "
                    session.flush()
                    println "lista " + listAudt
                    listAudt.each {

                        try {

                            it.save(flush: true)
                            println "errores " + it.errors

                        } catch (e) {
                            println "ERROR!!!: error en la auditoria "
                            println " audt " + e
                        }


                    }
                    return nuevo

                } else {
                    println "ERROR!!!: error en el save " + nuevo.properties.errors

                    return nuevo
                }


            }
        } catch (e) {
            println "\n error try save ob " + e
            return nuevo
        }

    }

    /**
     * Inserta un nuevo registro en la tabla de auditoría
     * @param id el id del registro
     * @param usuario el usuario efectuando la acción
     * @param perfil el perfil del usuario efectuando la acción
     * @return
     */
    def insertaKerberos(id, usuario, perfil) {
        def audt = new Audt()
        //println "si grabo wtf " + nuevo.class + " !!! audt " + audt.id + " " + audt
        audt.withTransaction { status ->
            audt.registro = id
            audt.usuario = usuario
            audt.operacion = "INSERT"
            audt.accion = (actionName) ? actionName : "N.A."
            audt.perfil = perfil
            audt.controlador = (controllerName) ? controllerName : "N.A."
            audt.old_value = null
            audt.new_value = null
            audt.fecha = new Date()
            audt.dominio = nuevo.class
            audt.campo = null
            audt.save(flush: false)
            if (status.isCompleted())
                println "si se completo"
        }
    }

    ///////////////////////////////////////////////////Funciones de base de datos ////////////////////////////////////////

    /**
     * Ejecuta un procedure de la base de datos
     * @param nombre nombre del procedure
     * @param parametros parámetros para el procedure
     * @return los resultados del procedure
     */
    def ejecutarProcedure(nombre, parametros) {
        def p = ""
        parametros.each {
            p += "" + it + ","
        }
        p = p.substring(0, p.length() - 1)
        def sql = " select " + nombre + "(" + p + ")"
        println "ejecutar Procedure " + sql
        def template = new JdbcTemplate(dataSource)
        def result = template.queryForMap(sql)
        println "result " + result
        return result
    }

    /**
     * Efetúa una conexión con la base de datos
     * @return la conexión
     */
    def getJavaConnection() {
        Connection dbConnection
        try {
            dbConnection = DriverManager.getConnection("jdbc:postgresql://10.0.0.3:5432/plan", "postgres", "postgres")
            println "coneccion " + dbConnection
        }
        catch (e) {
            println("Couldn’t get connection!  " + e);
        }
        return dbConnection
    }

///////////////////////////////////////////////////// ALERTAS \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    /**
     * Genera una alerta
     * @param from usuario que envía la alerta
     * @param to usuario que recibe la alerta
     * @param mensaje el mensaje de la alerta
     * @param controlador el controlador para el link de la alerta
     * @param accion la acción para el link de la alerta
     * @param id el id para el link de la alerta
     * @return un boolean que indica si se efectuó exitosamente la inserción
     */
    boolean generarAlerta(from, to, mensaje, controlador, accion, id) {
        try {
            def alerta = new yachay.alertas.Alerta()
            alerta.from = from
            alerta.usro = to
            alerta.mensaje = mensaje
            alerta.controlador = controlador
            alerta.accion = accion
            alerta.id_remoto = id
            alerta.fec_envio = new Date()
            if (alerta.save(flush: true))
                return true
            else
                return false
        } catch (e) {
            println "error generar alerta " + e
            return false
        }
    }

}