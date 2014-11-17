package yachay.contratacion

import yachay.parametros.TipoAprobacion
import yachay.parametros.UnidadEjecutora
import yachay.seguridad.Prfl
import yachay.seguridad.Usro

/**
 * Controlador que muestra las pantallas de manejo de reuniones de aprobación
 */
class AprobacionController extends yachay.seguridad.Shield {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST", delete: "GET"]

    /**
     * Acción que redirecciona a la acción List
     */
    def index = {
        redirect(action: "list", params: params)
    }
    /**
     * Acción que muestra el listado de reuniones de aprobación
     */
    def list = {
        def title = g.message(code: "aprobacion.list", default: "Aprobacion List")
//        <g:message code="default.list.label" args="[entityName]" />
        params.sort = "fecha"
        params.order = "desc"
        params.max = Math.min(params.max ? params.int('max') : 10, 100)

        [aprobacionInstanceList: Aprobacion.list(params), aprobacionInstanceTotal: Aprobacion.count(), title: title, params: params]
    }

    /**
     * Acción llamada con ajax que muestra la lista de las solicitudes agendadas para una reunión de aprobación
     */
    def solicitudes_ajax = {
        def reunion = Aprobacion.get(params.id)
        def solicitudes = Solicitud.findAllByAprobacion(reunion)
        def anios = []
        solicitudes.each { sol ->
            DetalleMontoSolicitud.findAllBySolicitud(sol, [sort: "anio"]).each { d ->
                if (!anios.contains(d.anio)) {
                    anios.add(d.anio)
                }
            }
        }
        return [aprobacion: reunion, solicitudes: solicitudes, anios: anios]
    }

    /**
     * Acción llamada con ajax que muestra el diálogo para agendar reunión de contratación: fecha, hora, comentarios para cada solicitud
     */
    def agendarReunion_ajax = {
        println "Agendar reunion:::: " + params
        def reunion = new Aprobacion()
        if (params.id) {
            reunion = Aprobacion.get(params.id.toLong())
            if (!reunion) {
                reunion = new Aprobacion()
            }
        }

        def ids = params.ids.split("_")
        if (ids instanceof String) {
            ids = [ids]
        }
        ids = ids*.toLong()

        def solicitudes = Solicitud.findAllByIdInList(ids)
        if (reunion.id) {
            solicitudes = Solicitud.findAllByIdInListOrAprobacion(ids, reunion)
        }

        return [reunion: reunion, solicitudes: solicitudes]
    }

    /**
     * Acción que muestra una pantalla donde se pueden seleccionar las solicitudes que se van a tratar en la reunión de aprobación
     */
    def prepararReunionAprobacion = {
        def title = g.message(code: "default.list.label", args: ["Solicitud"], default: "Solicitud List")
//        <g:message code="default.list.label" args="[entityName]" />

        params.max = Math.min(params.max ? params.int('max') : 25, 100)

        def list = Solicitud.findAllByIncluirReunion("S", params)
        def count = Solicitud.countByIncluirReunion("S")
        def reunion = null
        if (params.id) {
            reunion = Aprobacion.get(params.id.toLong())
            flash.message = "<div class='ui-state-highlight ui-corner-all' style='padding:5px;'>" +
                    "<span style=\"float: left; margin-right: .3em;\" class=\"ui-icon ui-icon-info\"></span>" +
                    "Preparar reunión del " + reunion.fecha.format("dd-MM-yyyy HH:mm") +
                    "</div>"
        }

        [solicitudInstanceList: list, solicitudInstanceTotal: count, title: title, params: params, reunion: reunion]
    }

    /**
     * Acción llamada con ajax que crea una reunión de aprobación asignando una lista de solicitudes a tratar
     */
    def agendarReunion = {
//        println params
        def f = params.fecha
        def h = params.horas.toString().toInteger()
        def m = params.minutos.toString().toInteger() * 5
        def fecha = new Date().parse("dd-MM-yyyy HH:mm", f + " " + h + ":" + m)

        def ok = true
        def aprobacion = Aprobacion.findByFecha(fecha)
        if (!aprobacion) {
            aprobacion = new Aprobacion()
            aprobacion.fecha = fecha
            if (!aprobacion.save(flush: true)) {
                ok = false
                println "Error al crear reunion de aprobacion: " + aprobacion.errors
            }
        }
        if (ok) {
            params.each { k, v ->
                if (k.toString().startsWith("revision")) {
                    def parts = k.split("_");
                    if (parts.size() == 2) {
                        def id = parts[1].toLong()
                        def solicitud = Solicitud.get(id)
                        solicitud.aprobacion = aprobacion
                        solicitud.revisionDireccionPlanificacionInversion = v
                        if (!solicitud.save(flush: true)) {
                            ok = false
                            println "Error asignando aprobacion a la solicitud: " + solicitud.errors
                        }
                    }
                }
            }
//            ids.each { id ->
//                def solicitud = Solicitud.get(id)
//                solicitud.aprobacion = aprobacion
//                if (!solicitud.save(flush: true)) {
//                    ok = false
//                    println "Error asignando aprobacion a la solicitud: " + solicitud.errors
//                }
//            }
        }
        render(ok ? "OK" : "NO")
    }

    /**
     * Acción que muestra los datos de un sector
     */
    def show = {
        def aprobacionInstance = Aprobacion.get(params.id)
        if (!aprobacionInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'aprobacion.label', default: 'Aprobacion'), params.id])}"
            redirect(action: "list")
        } else {

            def title = g.message(code: "aprobacion.show", default: "Show Aprobacion")

            [aprobacionInstance: aprobacionInstance, title: title]
        }
    }

    /**
     * Acción que permite eliminar un sector y redirecciona a la acción List
     * @param id id del elemento a ser eliminado
     */
    def delete = {
        def aprobacionInstance = Aprobacion.get(params.id)
        if (aprobacionInstance) {
            try {
                aprobacionInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'aprobacion.label', default: 'Aprobacion'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'aprobacion.label', default: 'Aprobacion'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        } else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'aprobacion.label', default: 'Aprobacion'), params.id])}"
            redirect(action: "list")
        }
    }

    /**
     * Acción que muestra una lista de las actas subidas
     */
    def listaActas = {
        /*
        Gerencia de Planificación, Dirección Planificación y  Dirección de Seguimiento,
                GP                      DP                          DS
        Dirección de Contratación Pública y la Dirección Administrativa, Gerencia Técnica
                DCP                                  DA                      GT
            TODAS
         */

        def perfil = session.perfil
        def usuario = Usro.get(session.usuario.id)
        def unidad = usuario.unidad
        def todos = ["GP", "DP", "DS", "DCP", "DA", "GT"]
        def aprobaciones = []
        def a = Aprobacion.withCriteria {
            isNotNull("fechaRealizacion")
            isNotNull("pathPdf")
        }
        if (todos.contains(perfil.codigo)) {
            aprobaciones = a
        }

        return [aprobaciones: aprobaciones]
    }

    /**
     * Acción que muestra la pantalla de reunión de aprobación
     */
    def reunion = {
        def ahora = new Date()
        def hoy1 = new Date().parse("dd-MM-yyyy HH:mm", ahora.format("dd-MM-yyyy") + " 00:00")
        def hoy2 = new Date().parse("dd-MM-yyyy HH:mm", ahora.format("dd-MM-yyyy") + " 23:59")

        def perfil = Prfl.get(session.perfil.id)
        def reuniones, reunion = null
        if (params.id) {
            reunion = Aprobacion.get(params.id.toLong())
        }
        if (!reunion) {
            reuniones = Aprobacion.findAllByFechaBetween(hoy1, hoy2)
        } else {
            reuniones = [reunion]
        }
        if (reuniones.size() == 1) {

            reunion = reuniones.first()
            if (reunion.aprobada == 'A') {
                params.show = 1
            }

            //MOVIDO AL SAVE (aprobar)
//            if (!reunion.numero) {
////                println "Generar numero de reunion"
//                def numero = 1
//                def maxNum = Aprobacion.list().numero*.toInteger().max()
//                if (maxNum) {
//                    numero = maxNum.toInteger() + 1
//                }
//                reunion.numero = numero.toString()
//                if (!reunion.save(flush: true)) {
//                    println "error al asignar numero a la reunion::: " + reunion.errors
//                }
//            }

            def unidadGerenciaPlan = UnidadEjecutora.findByCodigo("DRPL") // GERENCIA DE PLANIFICACIÓN
            def unidadDireccionPlan = UnidadEjecutora.findByCodigo("DPI") // DIRECCIÓN DE PLANIFICACIÓN E INVERSIÓN
            def unidadGerenciaTec = UnidadEjecutora.findByCodigo("GT") // GERENCIA TÉCNICA
//            def unidadRequirente = solicitud.unidadEjecutora

            def firmaGerenciaPlanif = Usro.findAllByUnidad(unidadGerenciaPlan)
            def firmaDireccionPlanif = Usro.findAllByUnidad(unidadDireccionPlan)
            def firmaGerenciaTec = Usro.findAllByUnidad(unidadGerenciaTec)
//            def firmaRequirente = Usro.findAllByUnidad(unidadRequirente)


            return [reunion             : reunion, params: params, perfil: perfil, firmaGerenciaPlanif: firmaGerenciaPlanif,
                    firmaDireccionPlanif: firmaDireccionPlanif, firmaGerenciaTec: firmaGerenciaTec/*, firmaRequirente: firmaRequirente*/]
        } else if (reuniones.size() == 0) {
            flash.message = "<div class='ui-state-error ui-corner-all' style='padding:5px;'>" +
                    "<span style=\"float: left; margin-right: .3em;\" class=\"ui-icon ui-icon-alert\"></span>" +
                    "No hay reuniones programadas para el día de hoy. Seleccione una o cree una nueva." +
                    "</div>"
        } else {
            flash.message = "Hay ${reuniones.size()} reuniones programadas para el día de hoy. Seleccione una."
        }
        redirect(action: "list")
    }

    /**
     * Acción que guarda los datos de la reunión de aprobación
     */
    def saveAprobacion = {
//        println "SAVE APROBACION>>>>> " + params
        /*
            [
            10_observaciones:bbbbbbbbbbbbbbbbb,
            12_asistentes:cccccccccccccccccccccccccccc3,
            1_tipoAprobacion.id:1,
            1_tipoAprobacion:[id:1],
            12_observaciones:cccccccccccccccccccccccccccccc,
            10_tipoAprobacion.id:1,
            10_tipoAprobacion:[id:1],
            10_asistentes:bbbbbbbbbbbbbbbbbbb2,
            observaciones:a fsd fasd,
            1_observaciones:aaaaaaaa,
            12_tipoAprobacion.id:1,
            12_tipoAprobacion:[id:1],
            1_asistentes:aaaaaaaaaaaaaaaaaa1,
            id:9,
            action:saveAprobacion, controller:aprobacion]
         */

        def reunion = Aprobacion.get(params.id.toLong())
        reunion.observaciones = params.observaciones.trim()
//        reunion.asistentes = params.asistentes.trim()
        reunion.fechaRealizacion = new Date()
        reunion.aprobada = params.aprobada

        if (!reunion.numero) {
//                println "Generar numero de reunion"
            def numero = 1
            def maxNum = Aprobacion.list().numero*.toInteger().max()
            if (maxNum) {
                numero = maxNum.toInteger() + 1
            }
            reunion.numero = numero.toString()
            if (!reunion.save(flush: true)) {
                println "error al asignar numero a la reunion::: " + reunion.errors
            }
        }

        if (!reunion.save(flush: true)) {
            println "Error al guardar la reunion: " + reunion.errors
        } else {
            def solicitudes = [:]
            params.each { k, v ->
                def parts = k.split("_")
                if (parts.size() == 2) {
                    def id = parts[0].toLong()
                    def campo = parts[1]
                    if (!solicitudes[id]) {
                        solicitudes[id] = Solicitud.get(id)
                    }
                    if (campo == "observaciones") {
                        solicitudes[id].observacionesAprobacion = solicitudes[id].observacionesAprobacion ?
                                solicitudes[id].observacionesAprobacion + "; " + v.trim() : v.trim()
                    } else if (campo == "asistentes") {
                        solicitudes[id].asistentesAprobacion = v.trim()
                    } else if (campo == "tipoAprobacion.id") {
                        solicitudes[id].tipoAprobacion = TipoAprobacion.get(v.toLong())
                    }
                }
            }
            solicitudes.each { id, solicitud ->
                solicitud.estado = "A" // --> ya fue tratada en una reunión, ya no se puede modificar...
                if (!solicitud.save(flush: true)) {
                    println "error al guardar la solicitud: " + solicitud.errors
                }
            }
        }
        flash.message = "Datos guardados correctamente"
        if (params.aprobada == 'A') {
            redirect(action: "reunion", id: reunion.id, params: [show: 1])
        } else {
            redirect(action: "reunion", id: reunion.id)
        }
    }

}
