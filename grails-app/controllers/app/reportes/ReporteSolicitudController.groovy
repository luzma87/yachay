package app.reportes

import app.Aprobacion
import app.CargoPersonal
import app.Solicitud
import app.seguridad.Sesn
import app.seguridad.Usro
import app.yachai.SolicitudAval

class ReporteSolicitudController {

    def index = {}

    def imprimirSolicitud = {
        def solicitud = Solicitud.get(params.id)

        def firmas = []

        if (solicitud.usuario) {
            firmas += [cargo: "Responsable unidad", usuario: solicitud.usuario]
        }
        return [solicitud: solicitud, firmas: firmas]
    }

    def imprimirActaAprobacion = {
        def solicitud = Solicitud.get(params.id)
        def aprobacion = Aprobacion.findBySolicitud(solicitud)

        def cargoDirectorPlanificacion = CargoPersonal.findByCodigo("DRPL")
        def cargoGerentePlanificacion = CargoPersonal.findByCodigo("GRPL")

        def directorPlanificacion = Usro.findByCargoPersonal(cargoDirectorPlanificacion)
        def gerentePlanificacion = Usro.findByCargoPersonal(cargoGerentePlanificacion)

        def firmas = []
        if (directorPlanificacion) {
            firmas += [cargo: "Director de planificación", usuario: directorPlanificacion]
        }
        if (gerentePlanificacion) {
            firmas += [cargo: "Gerente de planificación", usuario: gerentePlanificacion]
        }

        return [solicitud: solicitud, aprobacion: aprobacion, firmas: firmas]
    }

    def imprimirSolicitudAval = {
        println "impr sol "+params
        def solicitud = SolicitudAval.get(params.id)
        println "solcitud "+solicitud
        return [solicitud: solicitud]
    }
}
