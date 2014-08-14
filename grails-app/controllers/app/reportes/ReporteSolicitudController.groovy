package app.reportes

import app.Solicitud

class ReporteSolicitudController {

    def index = {}

    def imprimirSolicitud = {
        println ".....:::::" + params
        def solicitud = Solicitud.get(params.id)

        return [solicitud: solicitud]
    }
}
