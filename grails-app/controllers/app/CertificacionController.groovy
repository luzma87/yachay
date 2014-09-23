package app

import yachay.avales.Certificacion
import yachay.parametros.UnidadEjecutora
import yachay.parametros.poaPac.Anio
import yachay.parametros.TipoResponsable
import yachay.poa.Asignacion
import yachay.proyectos.Obra
import yachay.proyectos.ResponsableProyecto
import yachay.seguridad.Usro

class CertificacionController  extends yachay.seguridad.Shield{

    def kerberosService

    def solicitarCertificacion = {
//        println "params soollciitar "+params
        def usuario = session.usuario
        def band =false
        def unidad
        if(params.tipo!="arbol"){
            unidad=usuario.unidad
        }else{

            unidad=UnidadEjecutora.get(params.id)
            /*ruth*/
            if (usuario.id.toInteger() == 3) {
                band = true
            } else {

                def resp = ResponsableProyecto.findAllByUnidadAndTipo(unidad, TipoResponsable.findByCodigo("I"))
                // println "resp "+resp
                def r = []
                def ahora = new Date()
                resp.each {
                    if (it.desde.before(ahora) && it.hasta.after(ahora))
                        r.add(it)
                }
                r.each {rp ->
                    //println "r -> "+rp
                    if (rp.responsable.id.toInteger() == session.usuario.id.toInteger())
                        band = true

                }
            }
            if (!band) {
                response.sendError(403)
            }
        }

        def actual
        if (params.anio)
            actual = Anio.get(params.anio)
        else
            actual = Anio.findByAnio(new Date().format("yyyy"))

        def inversion = Asignacion.findAll("from Asignacion  where marcoLogico is not null and anio=${actual.id} and unidad=${unidad.id} order by id")
        def now = new Date()
        [unidad:unidad,actual:actual,inversion:inversion,now:now]
    }

    def solicitarAval = {
        def asg = Asignacion.get(params.asg)
        def now = new Date()
        if(asg.marcoLogico.fechaInicio<now){
            response.sendError(403)
        }
        def estados = [0,1]
        def avales = Certificacion.findAllByAsignacionAndEstadoInList(asg,estados)
        def disponible = asg.valorReal
        avales.each {
            disponible-=it.monto
        }
        [asg:asg, disponible:disponible]

    }

    def listaCertificados = {
        def usuario = Usro.get(session.usuario.id)
        def band = false
        /*ruth*/
        if(usuario.id.toInteger() == 3)
            band = true
        if (band){
            def unidad=UnidadEjecutora.get(params.id)
            def actual
            if (params.anio)
                actual = Anio.get(params.anio)
            else
                actual = Anio.findByAnio(new Date().format("yyyy"))
            def corrientes = Asignacion.findAll("from Asignacion  where marcoLogico is null and anio=${actual.id} and unidad=${unidad.id} order by id")
            def inversion = Asignacion.findAll("from Asignacion  where marcoLogico is not null and anio=${actual.id} and unidad=${unidad.id} order by id")
            [unidad:unidad,actual:actual,corrientes:corrientes,inversion:inversion]
        }else{
            response.sendError(403)
        }
    }

    def certificarPac = {
        def unidad=UnidadEjecutora.get(params.id)
        def actual
        if (params.anio)
            actual = Anio.get(params.anio)
        else
            actual = Anio.findByAnio(new Date().format("yyyy"))
        def asgs = Asignacion.findAll("from Asignacion  where anio=${actual.id} and unidad=${unidad.id} order by id")
        def pac = []
        asgs.each {
            def temp = Obra.findAllByAsignacion(it,[sort:"certificado"])
            if(temp)
                pac+=temp
        }
        [unidad:unidad,actual: actual,pac:pac]
    }


    def certificados = {
        def unidad = session.usuario.unidad
        def certificados = []
        def anio
        def actual
        if (params.anio)
            actual = Anio.get(params.anio)
        else
            actual = Anio.findByAnio(new Date().format("yyyy"))
        def asgs = Asignacion.findAll("from Asignacion  where anio=${actual.id} and unidad=${unidad.id} order by id")
        asgs.each {
            def cer = Certificacion.findAllByAsignacion(it)
            if(cer){
                certificados+=cer
            }
        }
        certificados.sort{it.estado}
        [certificados:certificados,actual: actual,unidad: unidad]

    }

    def cargarCertificados = {
        def asgn = Asignacion.get(params.id)
        def aprobados = Certificacion.findAllByAsignacionAndEstado(asgn,1)
        def solicitados =Certificacion.findAllByAsignacionAndEstado(asgn,0)
        def negados = Certificacion.findAllByAsignacionAndEstado(asgn,2)
        [aprobados:aprobados,solicitados:solicitados,negados:negados]

    }

    def guardarSolicitud = {
        println "solicitud "+params
        /*TODO enviar alertas*/

        def path = servletContext.getRealPath("/") + "pdf/solicitudAval/"
        new File(path).mkdirs()
        def f = request.getFile('file')
        def okContents = [
                'application/pdf': 'pdf',
                'application/download': 'pdf'
        ]
        def nombre = ""
        def pathFile
        if (f && !f.empty) {
            def fileName = f.getOriginalFilename() //nombre original del archivo
            def ext

            def parts = fileName.split("\\.")
            fileName = ""
            parts.eachWithIndex { obj, i ->
                if (i < parts.size() - 1) {
                    fileName += obj
                }
            }
            if (okContents.containsKey(f.getContentType())) {
                ext = okContents[f.getContentType()]
                fileName = fileName.size() < 40 ? fileName : fileName[0..39]
                fileName = fileName.tr(/áéíóúñÑÜüÁÉÍÓÚàèìòùÀÈÌÒÙÇç .!¡¿?&#°"'/, "aeiounNUuAEIOUaeiouAEIOUCc_")

                nombre = fileName + "." + ext
                pathFile = path + nombre
                def fn = fileName
                def src = new File(pathFile)
                def i = 1
                while (src.exists()) {
                    nombre = fn + "_" + i + "." + ext
                    pathFile = path + nombre
                    src = new File(pathFile)
                    i++
                }
                try {
                    f.transferTo(new File(pathFile)) // guarda el archivo subido al nuevo path
                    def asg=Asignacion.get(params.asgn)
                    def monto = params.monto
                    monto = monto.toDouble()
                    def concepto = params.concepto
                    def cer = new Certificacion()
                    cer.usuario=session.usuario
                    cer.concepto=concepto
                    cer.monto=monto
                    cer.asignacion=asg
                    cer.memorandoSolicitud=params.memorando
                    cer.pathSolicitud=nombre
                    cer = kerberosService.saveObject(cer,Certificacion,session.perfil,session.usuario,"guardarSolicitud","certificacion",session)
                    flash.message="Solicitud enviada"
                    redirect(action: 'solicitarCertificacion',params: [asg: params.asgn])
                    //println pathFile
                } catch (e) {
                    println "????????\n" + e + "\n???????????"
                }
            }else{
                flash.message="Error: Seleccione un archivo valido. Solo se aceptan archivos ,pdf"
                redirect(action: 'solicitarAval',params: [asg: params.asgn])
            }

        }else{
            flash.message="Error: Seleccione un archivo valido"
            redirect(action: 'solicitarAval',params: [asg: params.asgn])
        }
        /* fin del upload */





    }

    def editarCertificacion = {
        def usuario = Usro.get(session.usuario.id)
        def band = false
        if(usuario.id.toInteger() == 3 || usuario.unidad.id==85)
            band = true
        if (band){
            def cer = Certificacion.get(params.id)
            def msn = null
            if (params.msn)
                msn=params.msn
            params.msn=null
            [cer:cer,msn: msn]
        }else{
            response.sendError(403)
        }
    }

    def verActividad ={
        def cert = Certificacion.get(params.id)
        def act = cert.asignacion.marcoLogico
        [act:act,asg:cert.asignacion]
    }

    def listaSolicitudes = {

        def band = false
        def usuario = Usro.get(session.usuario.id)
        /*Todo Aqui validar quien puede*/
        band = true


        if(!band){
            response.sendError(403)
        }else{
            def actual
            if (params.anio)
                actual = Anio.get(params.anio)
            else
                actual = Anio.findByAnio(new Date().format("yyyy"))

            def msn = params.msn
            params.msn=""
            def mapa = [:]
            def mapaAnulacion = [:]
            def certificaciones = Certificacion.findAllByEstado(0)
            def certsAnulacion =Certificacion.findAll("from Certificacion where estado=1 and pathSolicitudAnulacion is not null and pathSolicitudAnulacion!='' and fechaRevisionAnulacion is null")
            certificaciones.each {
                def unidad = it.asignacion.unidad.nombre
                if(mapa[unidad]){
                    mapa[unidad].add(it)
                }else{
                    mapa.put(unidad,[it])
                }
            }
            certsAnulacion.each {
                def unidad = it.asignacion.unidad.nombre
                if(mapaAnulacion[unidad]){
                    mapaAnulacion[unidad].add(it)
                }else{
                    mapaAnulacion.put(unidad,[it])
                }
            }
            def mapa2 = [:]
            certificaciones = Certificacion.findAllByEstadoGreaterThanAndFechaGreaterThan(0,new Date().parse("dd/MM/yyyy","01/01/"+actual.anio),[sort:"estado"])
            certificaciones.each {
                def unidad = it.asignacion.unidad.nombre
                if(mapa2[unidad]){
                    mapa2[unidad].add(it)
                }else{
                    mapa2.put(unidad,[it])

                }
            }

            [mapa:mapa, msn: msn,mapa2:mapa2,actual: actual,mapaAnulacion:mapaAnulacion]
        }
    }


    def aprobarCertificacion = {

        /*TODO enviar alertas*/
        //println "aprobar "+params
        def path = servletContext.getRealPath("/") + "certificaciones/"
        new File(path).mkdirs()

        def f = request.getFile('archivo')
        if (f && !f.empty) {
            def fileName = f.getOriginalFilename()
            def ext

            def parts = fileName.split("\\.")
            fileName = ""
            parts.eachWithIndex { obj, i ->
                if (i < parts.size() - 1) {
                    fileName += obj
                } else {
                    ext = obj
                }
            }
            def reps = [
                    "a": "[àáâãäåæ]",
                    "e": "[èéêë]",
                    "i": "[ìíîï]",
                    "o": "[òóôõöø]",
                    "u": "[ùúûü]",

                    "A": "[ÀÁÂÃÄÅÆ]",
                    "E": "[ÈÉÊË]",
                    "I": "[ÌÍÎÏ]",
                    "O": "[ÒÓÔÕÖØ]",
                    "U": "[ÙÚÛÜ]",

                    "n": "[ñ]",
                    "c": "[ç]",

                    "N": "[Ñ]",
                    "C": "[Ç]",

                    "": "[\\!@\\\$%\\^&*()='\"\\/<>:;\\.,\\?]",

                    "_": "[\\s]"
            ]

            reps.each { k, v ->
                fileName = (fileName.trim()).replaceAll(v, k)
            }

            fileName = fileName+"_"+new Date().format("mm_ss")+"." + "pdf"

            def pathFile = path + File.separatorChar + fileName
            def src = new File(pathFile)
            def msn

            if (src.exists()) {
                def cer = Certificacion.get(params.id)
                msn="Ya existe un archivo con ese nombre. Por favor cámbielo."
                if (params.tipo)
                    redirect(action: 'editarCertificacion',params: [id:cer.id,unidad: cer.asignacion.unidad,msn: msn])
                else
                    redirect(action: 'listaSolicitudes',params: [msn:msn])


            } else {
                def band = false
                def usuario = Usro.get(session.usuario.id)
                def cer = Certificacion.get(params.id)
                /*Todo aqui validar quien puede*/
                band = true

                if(band){

                    f.transferTo(new File(pathFile))
                    cer.archivo=fileName
                    cer.estado=1
                    if (!params.tipo)
                        cer.fechaRevision=new Date()
                    cer=kerberosService.saveObject(cer,Certificacion,session.perfil,session.usuario,"aprobarCertificacion","certificacion",session)
                    msn="Solciitud aprobada"
                    if (params.tipo)
                        redirect(action: "listaCertificados",params: [cer:cer,id: cer.asignacion.unidad.id])
                    else
                        redirect(action: 'listaSolicitudes',params: [msn:msn])
                }else{
                    msn="Usted no tiene permisos para aprobar esta solicitud"
                    if (params.tipo)
                        redirect(action: "listaCertificados",params: [cer:cer,id: cer.asignacion.unidad.id])
                    else
                        redirect(action: 'listaSolicitudes',params: [msn:msn])
                }
            }
        }
    }

    def negarCertificacion = {
        /*TODO enviar alertas*/
        //println "nergar cert "+params
        def band = false
        def usuario = Usro.get(session.usuario.id)
        def cer = Certificacion.get(params.id)
        /*todo aqui validar quien puede*/
        band = true

        if(band){

            cer.estado=2
            cer.fechaRevision=new Date()
            cer=kerberosService.saveObject(cer,Certificacion,session.perfil,session.usuario,"negarCertificacion","certificacion",session)
            def msn="Solciitud negada"
            render "ok"

        }else{
            render("no")

        }


    }

    def negarAnulacion = {
        // println "negarAnulacion "+params
        def band = false
        def usuario = Usro.get(session.usuario.id)
        def cer = Certificacion.get(params.id)
        /*todo aqui validar quien puede*/
        band = true

        if(band){

            cer.estado=1
            cer.fechaRevisionAnulacion=new Date()
            cer=kerberosService.saveObject(cer,Certificacion,session.perfil,session.usuario,"negarCertificacion","certificacion",session)
            // println "save?  "+cer.errors
            def msn="Solciitud negada"
            render "ok"

        }else{
            render("no")

        }
    }


    def liberarAval = {
        println "libear aval "+params
        def path = servletContext.getRealPath("/") + "certificaciones/"
        new File(path).mkdirs()
        def f = request.getFile('archivo')
        if (f && !f.empty) {
            def fileName = f.getOriginalFilename()
            def ext

            def parts = fileName.split("\\.")
            fileName = ""
            parts.eachWithIndex { obj, i ->
                if (i < parts.size() - 1) {
                    fileName += obj
                } else {
                    ext = obj
                }
            }
            def reps = [
                    "a": "[àáâãäåæ]",
                    "e": "[èéêë]",
                    "i": "[ìíîï]",
                    "o": "[òóôõöø]",
                    "u": "[ùúûü]",

                    "A": "[ÀÁÂÃÄÅÆ]",
                    "E": "[ÈÉÊË]",
                    "I": "[ÌÍÎÏ]",
                    "O": "[ÒÓÔÕÖØ]",
                    "U": "[ÙÚÛÜ]",

                    "n": "[ñ]",
                    "c": "[ç]",

                    "N": "[Ñ]",
                    "C": "[Ç]",

                    "": "[\\!@\\\$%\\^&*()='\"\\/<>:;\\.,\\?]",

                    "_": "[\\s]"
            ]

            reps.each { k, v ->
                fileName = (fileName.trim()).replaceAll(v, k)
            }

            fileName = fileName+"_"+new Date().format("mm_ss")+"." + "pdf"

            def pathFile = path + File.separatorChar + fileName
            def src = new File(pathFile)
            def msn

            if (src.exists()) {
                def cer = Certificacion.get(params.id)
                msn="Ya existe un archivo con ese nombre. Por favor cámbielo."

                redirect(action: 'listaSolicitudes',params: [msn:msn])


            } else {
                def band = false
                def usuario = Usro.get(session.usuario.id)
                def cer = Certificacion.get(params.id)
                /*Todo aqui validar quien puede*/
                band = true

                if(band){
                    f.transferTo(new File(pathFile))
                    cer.pathLiberacion=fileName
                    cer.estado=4;
                    cer.fechaLiberacion=new Date();
                    def monto = params.montoLiberacion.toDouble()
                    cer.montoLiberacion=monto
                    cer.monto=cer.monto-monto
                    cer=kerberosService.saveObject(cer,Certificacion,session.perfil,session.usuario,"aprobarCertificacion","certificacion",session)
                    redirect(action: "listaSolicitudes")
                }else{
                    msn="Usted no tiene permisos para aprobar esta solicitud"
                    if (params.tipo)
                        redirect(action: "listaCertificados",params: [cer:cer,id: cer.asignacion.unidad.id])
                    else
                        redirect(action: 'listaSolicitudes',params: [msn:msn])
                }
            }
        }else{
            println "es empty??"
        }
    }

    def anularAval = {
        println "anular aval "+params
        def path = servletContext.getRealPath("/") + "certificaciones/"
        new File(path).mkdirs()
        def f = request.getFile('archivo')
        if (f && !f.empty) {
            def fileName = f.getOriginalFilename()
            def ext

            def parts = fileName.split("\\.")
            fileName = ""
            parts.eachWithIndex { obj, i ->
                if (i < parts.size() - 1) {
                    fileName += obj
                } else {
                    ext = obj
                }
            }
            def reps = [
                    "a": "[àáâãäåæ]",
                    "e": "[èéêë]",
                    "i": "[ìíîï]",
                    "o": "[òóôõöø]",
                    "u": "[ùúûü]",

                    "A": "[ÀÁÂÃÄÅÆ]",
                    "E": "[ÈÉÊË]",
                    "I": "[ÌÍÎÏ]",
                    "O": "[ÒÓÔÕÖØ]",
                    "U": "[ÙÚÛÜ]",

                    "n": "[ñ]",
                    "c": "[ç]",

                    "N": "[Ñ]",
                    "C": "[Ç]",

                    "": "[\\!@\\\$%\\^&*()='\"\\/<>:;\\.,\\?]",

                    "_": "[\\s]"
            ]

            reps.each { k, v ->
                fileName = (fileName.trim()).replaceAll(v, k)
            }

            fileName = fileName+"_"+new Date().format("mm_ss")+"." + "pdf"

            def pathFile = path + File.separatorChar + fileName
            def src = new File(pathFile)
            def msn

            if (src.exists()) {
                def cer = Certificacion.get(params.id)
                msn="Ya existe un archivo con ese nombre. Por favor cámbielo."

                redirect(action: 'listaSolicitudes',params: [msn:msn])


            } else {
                def band = false
                def usuario = Usro.get(session.usuario.id)
                def cer = Certificacion.get(params.id)
                /*Todo aqui validar quien puede*/
                band = true

                if(band){
                    f.transferTo(new File(pathFile))
                    cer.pathAnulacion=fileName
                    cer.estado=3;
                    cer.fechaRevisionAnulacion=new Date();
                    cer=kerberosService.saveObject(cer,Certificacion,session.perfil,session.usuario,"aprobarCertificacion","certificacion",session)
                    redirect(action: "listaSolicitudes")
                }else{
                    msn="Usted no tiene permisos para aprobar esta solicitud"
                    if (params.tipo)
                        redirect(action: "listaCertificados",params: [cer:cer,id: cer.asignacion.unidad.id])
                    else
                        redirect(action: 'listaSolicitudes',params: [msn:msn])
                }
            }
        }else{
            println "es empty??"
        }
    }

    def saveSolicitudAnulacion = {
        // println "saveSolicitudAnulacion  "+params
        def path = servletContext.getRealPath("/") + "certificaciones/"
        new File(path).mkdirs()
        def f = request.getFile('file')
        if (f && !f.empty) {
            def fileName = f.getOriginalFilename()
            def ext

            def parts = fileName.split("\\.")
            fileName = ""
            parts.eachWithIndex { obj, i ->
                if (i < parts.size() - 1) {
                    fileName += obj
                } else {
                    ext = obj
                }
            }
            def reps = [
                    "a": "[àáâãäåæ]",
                    "e": "[èéêë]",
                    "i": "[ìíîï]",
                    "o": "[òóôõöø]",
                    "u": "[ùúûü]",

                    "A": "[ÀÁÂÃÄÅÆ]",
                    "E": "[ÈÉÊË]",
                    "I": "[ÌÍÎÏ]",
                    "O": "[ÒÓÔÕÖØ]",
                    "U": "[ÙÚÛÜ]",

                    "n": "[ñ]",
                    "c": "[ç]",

                    "N": "[Ñ]",
                    "C": "[Ç]",

                    "": "[\\!@\\\$%\\^&*()='\"\\/<>:;\\.,\\?]",

                    "_": "[\\s]"
            ]

            reps.each { k, v ->
                fileName = (fileName.trim()).replaceAll(v, k)
            }

            fileName = fileName+"_"+new Date().format("mm_ss")+"." + "pdf"

            def pathFile = path + File.separatorChar + fileName
            def src = new File(pathFile)
            def msn

            if (src.exists()) {
                def cer = Certificacion.get(params.id)
                msn="Ya existe un archivo con ese nombre. Por favor cámbielo."
                if (params.tipo)
                    redirect(action: 'editarCertificacion',params: [id:cer.id,unidad: cer.asignacion.unidad,msn: msn])
                else
                    redirect(action: 'listaSolicitudes',params: [msn:msn])


            } else {
                def band = false
                def usuario = Usro.get(session.usuario.id)
                def cer = Certificacion.get(params.id)
                /*Todo aqui validar quien puede*/
                band = true

                if(band){

                    f.transferTo(new File(pathFile))
                    cer.pathSolicitudAnulacion=fileName
                    cer.conceptoAnulacion = params.conceptoAnulacion
                    cer.fechaRevisionAnulacion=null;
                    cer=kerberosService.saveObject(cer,Certificacion,session.perfil,session.usuario,"aprobarCertificacion","certificacion",session)
                    redirect(action: "certificados")
                }else{
                    msn="Usted no tiene permisos para aprobar esta solicitud"
                    if (params.tipo)
                        redirect(action: "listaCertificados",params: [cer:cer,id: cer.asignacion.unidad.id])
                    else
                        redirect(action: 'listaSolicitudes',params: [msn:msn])
                }
            }
        }

    }

    def descargaDocumento = {
        def cer = Certificacion.get(params.id)
        def path = servletContext.getRealPath("/") + "certificaciones/" + cer.archivo

        def src = new File(path)
        if (src.exists()) {
            response.setContentType("application/octet-stream")
            response.setHeader("Content-disposition", "attachment;filename=${src.getName()}")

            response.outputStream << src.newInputStream()
        } else {
            render "archivo no encontrado"
        }
    }
    def descargaSolicitud = {
        def cer = Certificacion.get(params.id)
//        println "path solicitud "+cer.pathSolicitud
        def path = servletContext.getRealPath("/") + "pdf/solicitudAval/" + cer.pathSolicitud

        def src = new File(path)
        if (src.exists()) {
            response.setContentType("application/octet-stream")
            response.setHeader("Content-disposition", "attachment;filename=${src.getName()}")

            response.outputStream << src.newInputStream()
        } else {
            render "archivo no encontrado"
        }
    }
    def descargaSolicitudAnulacion = {
        def cer = Certificacion.get(params.id)
        def path = servletContext.getRealPath("/") + "certificaciones/" + cer.pathSolicitudAnulacion

        def src = new File(path)
        if (src.exists()) {
            response.setContentType("application/octet-stream")
            response.setHeader("Content-disposition", "attachment;filename=${src.getName()}")

            response.outputStream << src.newInputStream()
        } else {
            render "archivo no encontrado"
        }
    }

    def certificarObra = {
        def obra = Obra.get(params.id)
        obra.certificado="S"
        obra.fechaCertificado=new Date()
        kerberosService.saveObject(obra,Obra,session.perfil,session.usuario,"certficarObra","certificacion",session)
        render "ok"
    }

}
