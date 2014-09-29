package yachay.seguridad

/**
 * Controlador que muestra pantallas para el manejo de acciones
 */
class AccionesController {

    def dbConnectionService

    /**
     * Acción que muestra la pantalla de configuración de menu
     */
    def index = {
        redirect(action: "configurarMenu")
    }

    /**
     * OJO. el módulo de noAsignadas es de ID = 0
     */
    /**
     * Acción que muestra un listado de acciones  ordenadas por módulo
     */
    def acciones = {
        def resultado = []
        def cn = dbConnectionService.getConnection()
        cn.eachRow("select mdlo__id, mdlonmbr from mdlo order by mdloordn"){d ->
            resultado.add([d.mdlo__id] + [d.mdlonmbr])
        }
        cn.close()
        render(view:"acciones", model:[lstacmbo: resultado])
    }

    /**
     * Acción que muestra una lista de acciones filtrando por módulo y tipo
     * @param mdlo es el módulo
     * @param tipo es el tipo de acción
     */
    def ajaxAcciones = {
        //println " ajaxAcciones---parametros: ${params}"

        def titulos = []
        def resultado = []
        def mdlo = params.mdlo
        def tipo = params.tipo
        if(params.mdlo?.size() > 0) mdlo = params.mdlo
        def cn = dbConnectionService.getConnection()
        def tx = ""
        tx = poneSQL(tipo, mdlo)
        if(tipo == '1'){
            titulos[0] = ['Permisos']+['Acción']+['Menú']+['Controlador']
        } else {
            titulos[0] = ['Permisos']+['Acción']+['Proceso']+['Controlador']
        }
        cn.eachRow(tx) { d ->
            resultado.add([d.accn__id] + [d.accnnmbr] + [d.accndscr] +[d.ctrlnmbr] + [d.mdlo__id])
        }
        //cn.disconnect()
        if(resultado.size() == 0) {
            resultado[0] = ['0'] + ['<font color="red">no hay acciones</font>'] + [''] + [''] + ['']
        }
        cn.close()
        render(view:'detalle', model:[datos:resultado, mdlo__id:mdlo, tpac__id: tipo, titulos: titulos])
    }

    /**
     * Acción que actualiza el campo descripción de una acción
     * @param id es el identificador de la acción
     * @param dscr es la nueva descripción de la acción
     * @render mensaje de datos modificados
     */
    def grabaAccn = {
        //println "graba---------parametros: ${params}"
        def id  = params.id
        def dscr = params.dscr
        def cn = dbConnectionService.getConnection()
        def tx = "update accn set accndscr = '" + dscr + "' where accn__id = " + id
        //println "graba.... ${tx}"
        try {
            cn.execute(tx)
        }
        catch(Exception ex){
            println "Error al insertar:" + ex.getMessage()
        }
        cn.close()
        render("la accion '${dscr}' ha sido modificada")
    }

    /**
     * Acción que cambia de módulo a una acción
     * @param mdlo nuevo módulo
     * @param ids son los identificadores de las acciones a reubicarse
     * @render mensaje de acciones movidas
     */
    def moverAccn = {
        //println "moverAccn---------parametros: ${params}"
        def mdlo = params.mdlo
        def ids  = params.ids
        if(params.ids?.size() > 0) ids = params.ids; else ids = "null"
        def cn = dbConnectionService.getConnection()
        def tx = "update accn set mdlo__id = " + mdlo + " where accn__id in (" + ids +")"
        //println "modificado.... ${tx}"
        try {
            cn.execute(tx)
        }
        catch(Exception ex){
            println "Error al insertar:" + ex.getMessage()
        }
        cn.close()
        render("Acciones movidas")
    }

    /**
     * Acción que actualiza el campo módulo de un conjunto de acciones
     * @param ids son los identificadores de las acciones a reubicar
     */
    def sacarAccn = {
        //println "sacarAccn---------parametros: ${params}"
        def ids  = params.ids
        if(params.ids?.size() > 0) ids = params.ids; else ids = "null"
        def cn = dbConnectionService.getConnection()
        def tx = ""
        tx = "update accn set mdlo__id = 0 where accn__id in (" + ids +")"
        //println "sacando.... ${tx}"
        try {
            cn.execute(tx)
        }
        catch(Exception ex){
            println "Error al insertar:" + ex.getMessage()
        }
        cn.close()
        //println "redirecciona con: " + params
        redirect(action: "ajaxAcciones", params: params)
    }

    /**
     * Acción que cambia el campo tipo de un subconjunto de acciones
     * @param ids son los identificadores de las acciones a cambiarse el tipo
     * @param tipo es el nuevo tipo puede ser 1 para menú y 2 para proceso
     */
    def cambiaAccn = {
        println "cambia---------parametros: ${params}"
        def ids  = params.ids
        def tipo = params.tipo
        def tp = 0
        if(tipo == '1') {tp = 2} else {tp = 1}
        if(params.ids?.size() > 0) ids = params.ids; else ids = "null"
        def cn = dbConnectionService.getConnection()
        def modulo = Modulo.findByDescripcionLike("no%asignado")
        def tx = "update accn set mdlo__id = " + modulo.id + ", tpac__id = " + tp + " where accn__id in (" + ids +")"
        println "modificado.... ${tx}"
        try {
            cn.execute(tx)
        }
        catch(Exception ex){
            println "Error al insertar:" + ex.getMessage()
        }
        println "-----------modificado"
        cn.close()
        redirect(action: "ajaxAcciones", params: params)
    }


    /**
     * Acción que muestra una lista de controladores con acciones que tengan módulos
     */
    def configurarAcciones = {
        def modulos = Modulo.list([sort:"orden"])
        def controladores = []
        def cn = dbConnectionService.getConnection()
        cn.eachRow("select distinct ctrlnmbr from ctrl where ctrl__id in (select  ctrl__id from accn where mdlo__id is not null)"){
            controladores.add(it.ctrlnmbr)
        }
        def tp = 1
        cn.eachRow("select tpac__id from tpac where upper(tpacdscr) like '%MENU%'"){
            tp = it.tpac__id
        }
       // println tp

        [modulos:modulos, controladores:controladores, tipo_tpac:tp, titulo:'Men&uacute;s por M&oacute;dulo']
    }

    /**
     * Acción que muestra una lista de controladores con acciones que tengan módulos
     */
    def procesos = {
        def modulos = Modulo.list([sort:"orden"])
        def controladores = []
        def cn = dbConnectionService.getConnection()
        cn.eachRow("select distinct ctrlnmbr from ctrl where ctrl__id in (select  ctrl__id from accn where mdlo__id is not null)"){
            controladores.add(it.ctrlnmbr)
        }
        def tp = 2  //hallar el valor desde la BD
        cn.eachRow("select tpac__id from tpac where upper(tpacdscr) like '%PROC%'"){
            tp = it.tpac__id
        }
        render(view: "configurarAcciones", model:[modulos:modulos, controladores:controladores, tipo_tpac:tp,
                titulo:'Procesos por M&oacute;dulo'])
    }


    /**
     * Acción que muestra una lista de módulos
     */
    def perfiles = {
        def modulos = Modulo.list([sort:"orden"])
        [modulos:modulos]
    }


    /**
     * Acción que asigna una lista de permisos a un perfil
     * @param perfil es el indentificador del perfil
     * @param chk es un arreglo con los identificadores de las acciones
     */
    def guardarPermisos = {
       // println "guardar permisos " + params
        def perfil = Prfl.get(params.perfil)
        def permisos = Prms.findAllByPerfil(perfil).accion
        //println "permisos "+permisos+" "+params.chk.clazz
        if(params.chk){
            params.chk.each {

                def accn = Accn.get(it)
                //println "each accn "+it + " "+permisos.contains(accn)
                if(!(permisos.contains(accn))){
                    println "paso el if"
                    def perm = new Prms([accion:accn,perfil:perfil])
                    perm.save(flush:true)
                    println "errors "+perm.errors
                }
            }
            permisos.each {
                //println "quitar "+it+" "+params.chk.toList()
                if(!(params.chk.toList().contains(it.id.toString()))) {
                    println "entro if del"
                    def perm = Prms.findByAccionAndPerfil(it,perfil).delete(flush:true)
                }
            }
        }else{
            permisos = Prms.findAllByPerfil(perfil)
            permisos.each {
                it.delete(flush:true)
            }
        }


        redirect(action: "perfiles")
    }

    /**
     * Acción que muestra las acciones de un perfil
     * @param perfil es el identificador del perfil
     */
    def cargarAccionesPerfil = {
        //println "cargar a p "+params
        def perfil = Prfl.get(params.perfil)
        def permisos = Prms.findAllByPerfil(perfil).accion.id
        def modulos = Modulo.list()
        [modulos:modulos,permisos:permisos,perfil:perfil]
    }

    /**
     * Acción que cambia el tipo a una acción
     * @param accn es il identificador de la acción
     * @param val es el identificador del nuevo tipo
     * @render ok cuando la acción se completo correctamente
     */
    def cambiarTipo = {
        println "cambiar tipo "+params
        def accn = Accn.get(params.accn)
        accn.tipo= Tpac.get(params.val)
        render "ok"
    }

    /**
     * Acción que cambia el módulo de una acción
     * @param accn es el identificador de la acción
     * @param val es el idetificador del nuevo módulo
     * @render ok cuando la acción se completo correctamente
     */
    def cambiarModulo = {
        //println "cambiar tipo "+params
        def accn = Accn.get(params.accn)
        accn.modulo=Modulo.get(params.val)
        render "ok"
    }

    /**
     * Acción que cambia de modulo a todas las acciones de un controlador
     * @param ctrl es identificador del controlador
     * @param val es el identificador del nuevo módulo
     * @render ok cuando la acción se completo correctamente
     */
    def cambiarModuloControlador = {
        //println "cmc "+params
        def ctrl = Ctrl.findByCtrlNombre(params.ctrl)
        def acs = Accn.findAllByControl(ctrl)
        def modulo = Modulo.get(params.val)
        acs.each {
            it.modulo=modulo
            it.save(flush:true)
        }
        render "ok"
    }

    /**
     * Acción que itera sobre todos los controladores creados en el proyecto grails, los busca en la base de datos y si no los encuentra los inserta dentro de la tabla representada en el dominio Ctrl
     * @render un mensaje con el número de controladores agregados a la base de datos
     *
     */
    def cargarControladores = {
        println "cargar controladores"
        def i = 0
        grailsApplication.controllerClasses.each {
            //def  lista = Ctrl.list()
            def ctr =  Ctrl.findByCtrlNombre(it.getName())
            if(!ctr){
                ctr= new Ctrl([ctrlNombre:it.getName()])
                ctr.save(flush:true)
                i++
            }
        }
        render("Se han agregado ${i} Controladores")
    }

    /**
     * Acción que itera sobre todos los controladores creados en el proyecto, analiza las acciones de cada controlador, las busca en la base de datos y si no las encuentra las inserta en la tabla representada por el dominio Accn
     * @render un mensaje con el número de acciones insertadas en la base de datos
     */
    def cargarAcciones = {

        println "cargar acciones"
        def ac = []
        def accs = [:]
        def i=0
        def ignore=["afterInterceptor","beforeInterceptor"]
        grailsApplication.controllerClasses.each {ct ->
            def t = []
            ct.getURIs().each{

                def s = it.split("/")
                if(s.size()>2) {
                    if(!t.contains(s[2])) {
                        if(!ignore.contains(s[2])){
                            if(!(s[2]=~"Service")){

                                def accn = Accn.findByAccnNombreAndControl(s[2],Ctrl.findByCtrlNombre(ct.getName()))
                                //println "si service "+ s[2]+" accion "+accn.id+" url "+it
                                if(accn==null){
                                    println "if 2";
                                    accn=new Accn()
                                    accn.accnNombre=s[2]
                                    accn.control=Ctrl.findByCtrlNombre(ct.getName())
                                    accn.accnDescripcion=s[2]
                                    accn.accnAuditable=1
                                    if(s[2]=~"save" || s[2]=~"update" || s[2]=~"delete" || s[2]=~"guardar")
                                        accn.tipo=Tpac.get(2)
                                    else
                                        accn.tipo=Tpac.get(1)
                                    accn.modulo=Modulo.findByDescripcionLike("no%asignado")
                                    accn.save(flush:true)
                                    i++
                                    println "errores " + accn.errors

                                }
                                t.add(s.getAt(2))
                            }else{
//                                println "no sale por el service "+s[2]+" "+it
                            }
                        }else{
                            //println "no sale por el ignore "+ignore
                        }
                    }else{
                        //println "no sale por el contains t  "+it+"   "+t
                    }
                } else{
                   // println "no sele por el size "+it
                }

            }
            accs.put(ct.getName(),t)
            t=null
        }
        render("Se han agregado ${i} acciones")
    }

    /*
    * Función que construye un sql para seleccionar acciones filtradas por tipo y módulo
    * @param tipo es el identificador del tipo de acción
    * @param mdlo es el identificador del módulo
    * @return un string con el sql generador
    *
    */
    def poneSQL(tipo, mdlo){
        return "select accn.accn__id, accnnmbr, accndscr, ctrlnmbr, accn.mdlo__id " +
                "from accn, mdlo, ctrl where mdlo.mdlo__id = accn.mdlo__id and " +
                "mdlo.mdlo__id = ${mdlo} and accn.ctrl__id = ctrl.ctrl__id and " +
                "tpac__id = ${tipo} order by ctrlnmbr, accnnmbr"
    }
    /*
       * Función que construye un sql para seleccionar acciones filtradas por tipo
       * @param tipo es el identificador del tipo de acción
       * @return un string con el sql generador
       *
       */
    def poneSQLnull(tipo){
        return "select accn.accn__id, accnnmbr, accndscr, ctrlnmbr, accn.mdlo__id " +
                "from accn, ctrl where mdlo__id is null and accn.ctrl__id = ctrl.ctrl__id and " +
                "tpac__id = ${tipo} order by ctrlnmbr, accnnmbr"
    }


}
