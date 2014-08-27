package app.yachai

import app.Anio
import app.Solicitud
import app.seguridad.Usro

class RevisionAvalController {

    def pendientes = {
        def solicitudes = SolicitudAval.findAllByEstado(EstadoAval.findByCodigo("E01"))
        def actual
        if (params.anio)
            actual = Anio.get(params.anio)
        else
            actual = Anio.findByAnio(new Date().format("yyyy"))
        [solicitudes:solicitudes,actual:actual]
    }

    def  negarAval = {

        def band = false
        def usuario = Usro.get(session.usuario.id)
        def sol = SolicitudAval.get(params.id)
        /*todo aqui validar quien puede*/
        band = true
        if(band){

            sol.estado=EstadoAval.findByCodigo("E03")
            sol.fechaRevision=new Date()
            sol.save(flush: true)
            render "ok"

        }else{
            render("no")

        }


    }

    def listaAvales = {
        def actual
        if (params.anio)
            actual = Anio.get(params.anio)
        else
            actual = Anio.findByAnio(new Date().format("yyyy"))
        [actual:actual]
    }

    def historialAvales = {
       // println "historial aval "+params
        def now = new Date()
        def anio = Anio.get(params.anio).anio
        def numero = ""
        def proceso =params.proceso
        def estado = EstadoAval.findByCodigo("E02")
        def datos = []
        def fechaInicio
        def fechaFin
        def orderBy =""
        def externos =["usuario","proceso","proyecto"]
        def band = true
        if(params.numero && params.numero!=""){
            numero=" and numero like ('%${numero}%')"
        }
        if(params.sort && params.sort!=""){
            if(!externos.contains(params.sort))
                orderBy=" order by ${params.sort} ${params.order}"
            else
                band=false
        }
        if(anio && anio!=""){
            fechaInicio= new Date().parse("dd-MM-yyyy HH:mm:ss","01-01-"+anio+" 00:01:01")
            fechaFin= new Date().parse("dd-MM-yyyy HH:mm:ss","31-12-"+anio+" 23:59:59")
//            println "inicio "+fechaInicio+"  fin  "+fechaFin
//            datos += Aval.findAll("from Aval where " +
//                    "(fechaAprobacion > '${fechaInicio}' and fechaAprobacion < '${fechaFin}') " +
//                    "or (fechaAnulacion > '${fechaInicio}' and fechaAnulacion < '${fechaFin}')" +
//                    " or (fechaLiberacion > '${fechaInicio}' and fechaLiberacion < '${fechaFin}') ${numero} ${orderBy}")
//            println "datos fecha "+datos

            def avales = Aval.withCriteria {
                or {
                    and {
                        gt("fechaAprobacion", fechaInicio)
                        lt("fechaAprobacion", fechaFin)
                    }
                    and {
                        gt("fechaAnulacion", fechaInicio)
                        lt("fechaAnulacion", fechaFin)
                    }
                    and {
                        gt("fechaLiberacion", fechaInicio)
                        lt("fechaLiberacion", fechaFin)
                    }
                }
                if(params.numero && params.numero!=""){
                    ilike("numero", "%"+params.numero+"%")
                }
                if(params.sort && params.sort!=""){
                    if(!externos.contains(params.sort)) {
                        order(params.sort, params.order)
                    }
                }
            }
            datos+=avales
        }

        if(proceso && proceso!=""){
            def datosTemp = []
            datos.each {av->

                if(av.proceso.nombre=~proceso){

                    datosTemp.add(av)
                }
            }
            datos=datosTemp
//            println "datos proceso "+datos
        }
        if(!band){
            switch (params.sort){
                case "proceso":
                    println "sort proceso"
                    datos=datos.sort{it.proceso.nombre}

                    break;
                case "proyecto":
                    datos=datos.sort{it.proceso.proyecto.nombre}
                    break;
            }
            if(params.order=="desc")
                datos=datos.reverse()

        }
        [datos:datos,estado:estado,sort:params.sort,order:params.order,now:now]
    }

    def historial = {
//        println "historial "+params
        def anio = Anio.get(params.anio).anio
        def numero = params.numero
        def proceso =params.proceso
        def datos = []
        def fechaInicio
        def fechaFin
        if(anio && anio!=""){
            fechaInicio= new Date().parse("dd-MM-yyyy hh:mm:ss","01-01-"+anio+" 00:01:01")
            fechaFin= new Date().parse("dd-MM-yyyy hh:mm:ss","31-12-"+anio+" 23:59:59")
//            println "inicio "+fechaInicio+"  fin  "+fechaFin
            datos += SolicitudAval.findAllByFechaBetween(fechaInicio,fechaFin)
//            println "datos fecha "+datos
        }
        if(numero && numero!=""){
//            println "buscando por numero ==> "+numero
            def datosTemp = []
            datos.each {sol->
                println "tiene aval? "+sol.aval
                if(sol.aval?.numero=~numero){
                    println "encontro "
                    datosTemp.add(sol)
                }
            }
            datos=datosTemp
//            println "datos numero "+datos
        }
        if(proceso && proceso!=""){
            def datosTemp = []
            datos.each {sol->

                if(sol.proceso.nombre=~proceso){
                    println "encontro "
                    datosTemp.add(sol)
                }
            }
            datos=datosTemp
//            println "datos proceso "+datos
        }
        [datos:datos.sort{it.fecha}]
    }

    def aprobarAval = {
        def solicitud = SolicitudAval.get(params.id)
        def band = false
        def usuario = Usro.get(session.usuario.id)
        /*todo validar quien puede*/
        band=true
        if (!band)
            response.sendError(403)
        [solicitud:solicitud]
    }

    def aprobarAnulacion = {
        def solicitud = SolicitudAval.get(params.id)
        def band = false
        def usuario = Usro.get(session.usuario.id)
        /*todo validar quien puede*/
        band=true
        if (!band)
            response.sendError(403)
        [solicitud:solicitud]
    }

    def guarDatosDoc = {
        println "guardar datos doc "+params
        def sol = SolicitudAval.get(params.id)
        sol.observaciones=params.obs
        sol.numero = params.aval
        sol.save(flush: true)
        render "ok"
    }


    def guardarLiberacion = {
        println "liberacion "+params
        def path = servletContext.getRealPath("/") + "avales/"
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

                flash.message="Ya existe un archivo con ese nombre. Por favor cámbielo."
                redirect(action: 'listaAvales')


            } else {
                def band = false
                def usuario = Usro.get(session.usuario.id)
                def aval = Aval.get(params.id)
                /*Todo aqui validar quien puede*/
                band = true
                if(band){
                    f.transferTo(new File(pathFile))
                    aval.pathLiberacion=fileName
                    aval.liberacion=aval.monto
                    aval.monto=params.monto.toDouble()
                    aval.estado=EstadoAval.findByCodigo("E05")
                    aval.save(flush: true)
                    flash.message="Aval "+aval.fechaAprobacion.format("yyyy")+"-CP No."+aval.numero+" Liberado"
                    redirect(action: 'listaAvales',controller: 'revisionAval')
                }else{
                    flash.message="Usted no tiene permisos para liberar avales"
                    redirect(controller: 'listaAvales',action: 'revisionAval')
                }
            }
        }
    }

    def caducarAval = {
        def aval = Aval.get(params.id)
        aval.estado=EstadoAval.findByCodigo("E06")
        aval.save(flush: true)
        redirect(action: "listaAvales")
    }


    def guardarAnulacion = {
        println "aprobar anulacion "+params
        def path = servletContext.getRealPath("/") + "avales/"
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
                def sol = SolicitudAval.get(params.id)
                flash.message="Ya existe un archivo con ese nombre. Por favor cámbielo."
                redirect(action: 'aprobarAval',params: [id:sol.id])


            } else {
                def band = false
                def usuario = Usro.get(session.usuario.id)
                def sol = SolicitudAval.get(params.id)
                /*Todo aqui validar quien puede*/
                band = true

                if(band){
                    f.transferTo(new File(pathFile))
                    def aval = sol.aval
                    aval.pathAnulacion=fileName
//                    aval.memo=sol.memo
                    aval.estado=EstadoAval.findByCodigo("E04")
//                    aval.monto=sol.monto
                    aval.save(flush: true)
                    sol.estado=EstadoAval.findByCodigo("E02")
                    sol.save(flush: true)
                    flash.message="Solciitud de anulación aprobada - Aval "+aval.fechaAprobacion.format("yyyy")+"-CP No."+aval.numero+" anulado"
                    redirect(action: 'pendientes',controller: 'revisionAval')
                }else{
                    flash.message="Usted no tiene permisos para aprobar esta solicitud"
                    redirect(controller: 'avales',action: 'listaProcesos')
                }
            }
        }
    }

    def guardarAprobacion = {
        /*TODO enviar alertas*/
        println "aprobar "+params
        def path = servletContext.getRealPath("/") + "avales/"
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
                def sol = SolicitudAval.get(params.id)
                flash.message="Ya existe un archivo con ese nombre. Por favor cámbielo."
                redirect(action: 'aprobarAval',params: [id:sol.id])


            } else {
                def band = false
                def usuario = Usro.get(session.usuario.id)
                def sol = SolicitudAval.get(params.id)
                /*Todo aqui validar quien puede*/
                band = true

                if(band){
                    f.transferTo(new File(pathFile))
                    def aval = new Aval()
                    aval.proceso=sol.proceso
                    aval.concepto=sol.concepto
                    aval.path=fileName
                    aval.memo=sol.memo
                    aval.numero=sol.numero
                    aval.fechaAprobacion=new Date()
                    aval.estado=EstadoAval.findByCodigo("E02")
                    aval.monto=sol.monto
                    aval.save(flush: true)
                    sol.aval=aval;
                    sol.estado=aval.estado
                    sol.save(flush: true)
                    flash.message="Solciitud aprobada"
                    redirect(action: 'pendientes',controller: 'revisionAval')
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


}
