package app.yachai

import app.Solicitud
import app.seguridad.Usro

class RevisionAvalController {

    def pendientes = {
        def solicitudes = SolicitudAval.findAllByEstado(EstadoAval.findByCodigo("E01"))

        [solicitudes:solicitudes]
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

    def guarDatosDoc = {
        println "guardar datos doc "+params
        def sol = SolicitudAval.get(params.id)
        sol.observaciones=params.obs
        sol.numero = params.aval
        sol.save(flush: true)
        render "ok"
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
