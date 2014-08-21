package app.yachai

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


}
