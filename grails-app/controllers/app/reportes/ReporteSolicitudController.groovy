package app.reportes

import app.Aprobacion
import app.Solicitud

class ReporteSolicitudController {

    def index = {}

    def imprimirSolicitud = {
        def solicitud = Solicitud.get(params.id)
        return [solicitud: solicitud]
    }

    def imprimirActaAprobacion = {
        def solicitud = Solicitud.get(params.id)
        def aprobacion = Aprobacion.findBySolicitud(solicitud)
        return [solicitud: solicitud, aprobacion: aprobacion]
    }
}
