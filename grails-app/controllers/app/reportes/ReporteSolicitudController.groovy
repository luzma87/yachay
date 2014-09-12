package app.reportes

import app.Aprobacion
import app.CargoPersonal
import app.Solicitud
import app.seguridad.Sesn
import app.seguridad.Usro
import app.yachai.DetalleMontoSolicitud
import app.yachai.SolicitudAval

class ReporteSolicitudController {

    def index = {}

    def solicitudes = {
        println "solicitudes"
        def list = []
        list = Solicitud.findAll("from Solicitud order by unidadEjecutora.id,fecha")

        def anios = []



//        list = list.sort { it.aprobacion?.descripcion + it.unidadEjecutora?.nombre + it.fecha.format("dd-MM-yyyy") }
//        list = list.sort { a, b ->
//            ((a.aprobacion?.descripcion <=> b.aprobacion?.descripcion) ?:
//                    (a.unidadEjecutora?.nombre <=> b.unidadEjecutora?.nombre)) ?:
//                    (a.fecha?.format("dd-MM-yyyy") <=> b.fecha?.format("dd-MM-yyyy"))
//    }


        list.each {sol->
            DetalleMontoSolicitud.findAllBySolicitud(sol,[sort:"anio"]).each {d->
                if(!anios.contains(d.anio)){
                    anios.add(d.anio)
                }


            }
        }
        return [solicitudInstanceList: list,anios:anios]
    }

    def solicitudesReunion={
        def list = []
        list = Solicitud.findAll("from Solicitud where  incluirReunion='S' order by unidadEjecutora.id,fecha")
        def anios = []
        list.each {sol->
            DetalleMontoSolicitud.findAllBySolicitud(sol,[sort:"anio"]).each {d->
                if(!anios.contains(d.anio)){
                    anios.add(d.anio)
                }


            }
        }
        return [solicitudInstanceList: list,anios:anios]
    }

    def imprimirSolicitud = {
        def solicitud = Solicitud.get(params.id)

        def firmas = []

        if (solicitud.usuario) {
            firmas += [cargo: "Responsable unidad", usuario: solicitud.usuario]
        }
        return [solicitud: solicitud, firmas: firmas]
    }

    def imprimirActaAprobacion = {
        println "Acta aprobacion:::: " + params
        def solicitud = Solicitud.get(params.id)
        def aprobacion = Aprobacion.findBySolicitud(solicitud)

//        def cargoDirectorPlanificacion = CargoPersonal.findByCodigo("DRPL")
//        def cargoGerentePlanificacion = CargoPersonal.findByCodigo("GRPL")
//
//        def directorPlanificacion = Usro.findByCargoPersonal(cargoDirectorPlanificacion)
//        def gerentePlanificacion = Usro.findByCargoPersonal(cargoGerentePlanificacion)
//
//        def firmas = []
//        if (directorPlanificacion) {
//            firmas += [cargo: "Director de planificación", usuario: directorPlanificacion]
//        }
//        if (gerentePlanificacion) {
//            firmas += [cargo: "Gerente de planificación", usuario: gerentePlanificacion]
//        }
        def firmas = []
        if (params.fgp != "null") {
            def gerentePlanificacion = Usro.get(params.fgp)
            if (gerentePlanificacion) {
                firmas += [cargo: "Gerente de planificación", usuario: gerentePlanificacion]
            }
        }
        if (params.fdp != "null") {
            def directorPlanificacion = Usro.get(params.fdp)
            if (directorPlanificacion) {
                firmas += [cargo: "Director de planificación", usuario: directorPlanificacion]
            }
        }
        if (params.fgt != "null") {
            def gerenteTec = Usro.get(params.fgt)
            if (gerenteTec) {
                firmas += [cargo: "Gerente técnico", usuario: gerenteTec]
            }
        }
        if (params.frq != "null") {
            def req = Usro.get(params.frq)
            if (req) {
                firmas += [cargo: "Requirente", usuario: req]
            }
        }

        return [solicitud: solicitud, aprobacion: aprobacion, firmas: firmas]
    }

    def imprimirSolicitudAval = {
        println "impr sol " + params
        def solicitud = SolicitudAval.get(params.id)
        println "solcitud " + solicitud
        return [solicitud: solicitud]
    }
}
