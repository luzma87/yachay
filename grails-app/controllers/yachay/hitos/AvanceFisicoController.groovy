package yachay.hitos

import yachay.avales.ProcesoAval

class AvanceFisicoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST", delete: "GET"]

    /**
     * Acción que muestra la lista de avances físicos de un proceso y permite agregar nuevos
     */
    def list = {
        def proceso = ProcesoAval.get(params.id)
        def ultimoAvance = AvanceFisico.findAllByProceso(proceso, [sort: "fecha", order: "desc"])
        def minAvance = 0
        def minDate = "";
        if (ultimoAvance.size() > 0) {
            def ua = ultimoAvance.first()
            minAvance = ua.avance
            minDate = (ua.fecha + 1).format("dd-MM-yyyy")
        }
        def maxAvance = 100 - minAvance
        return [proceso: proceso, minAvance: minAvance, maxAvance: maxAvance, minDate: minDate]
    }

    /**
     * Acción cargada con ajax que retorna la lista de avances físicos de un proceso
     */
    def avanceFisicoProceso_ajax = {
        def proceso = ProcesoAval.get(params.id)
        def avances = AvanceFisico.findAllByProceso(proceso)
        return [proceso: proceso, avances: avances]
    }
    /**
     * Acción llamada con ajax que agrega un avance físico a un proceso
     */
    def addAvanceFisicoProceso_ajax = {
        def proceso = ProcesoAval.get(params.id)
        def avance = new AvanceFisico()
        params.fecha = new Date().parse("dd/MM/yyyy", params.fecha)
        params.avance = params.avance.toString().toDouble()
        avance.properties = params
        avance.proceso = proceso
        if (avance.save(flush: true)) {
            def max = 100 - avance.avance
            def minDate = (avance.fecha + 1).format("dd-MM-yyyy")
            render "OK_" + avance.avance + "_" + max + "_" + minDate
        } else {
            render "NO"
        }
    }

    /**
     * Acción llamada con ajax que elimina un avance físico a un proceso
     */
    def deleteAvanceFisicoProceso_ajax = {
        def avance = AvanceFisico.get(params.id)
        def proceso = avance.proceso
        try {
            avance.delete(flush: true)

            def ultimoAvance = AvanceFisico.findAllByProceso(proceso, [sort: "fecha", order: "desc"])
            def minAvance = 0
            def minDate = "";
            if (ultimoAvance.size() > 0) {
                def ua = ultimoAvance.first()
                minAvance = ua.avance
                minDate = (ua.fecha + 1).format("dd-MM-yyyy")
            }
            def maxAvance = 100 - minAvance

            render "OK_" + minAvance + "_" + maxAvance + "_" + minDate
        } catch (Exception e) {
            e.printStackTrace()
            render "NO"
        }
    }

//    def index = {
//        redirect(action: "list", params: params)
//    }
//
//    def list = {
//        def title = g.message(code:"avancefisico.list", default:"AvanceFisico List")
////        <g:message code="default.list.label" args="[entityName]" />
//
//        params.max = Math.min(params.max ? params.int('max') : 10, 100)
//
//        [avanceFisicoInstanceList: AvanceFisico.list(params), avanceFisicoInstanceTotal: AvanceFisico.count(), title: title, params:params]
//    }
//
//    def form = {
//        def title
//        def avanceFisicoInstance
//
//        if(params.source == "create") {
//            avanceFisicoInstance = new AvanceFisico()
//            avanceFisicoInstance.properties = params
//            title = g.message(code:"avancefisico.create", default:"Create AvanceFisico")
//        } else if(params.source == "edit") {
//            avanceFisicoInstance = AvanceFisico.get(params.id)
//            if (!avanceFisicoInstance) {
//                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'avanceFisico.label', default: 'AvanceFisico'), params.id])}"
//                redirect(action: "list")
//            }
//            title = g.message(code:"avancefisico.edit", default:"Edit AvanceFisico")
//        }
//
//        return [avanceFisicoInstance: avanceFisicoInstance, title:title, source: params.source]
//    }
//
//    def create = {
//        params.source = "create"
//        redirect(action:"form", params:params)
//    }
//
//    def save = {
//        def title
//        if(params.id) {
//            title = g.message(code:"avancefisico.edit", default:"Edit AvanceFisico")
//            def avanceFisicoInstance = AvanceFisico.get(params.id)
//            if (avanceFisicoInstance) {
//                avanceFisicoInstance.properties = params
//                if (!avanceFisicoInstance.hasErrors() && avanceFisicoInstance.save(flush: true)) {
//                    flash.message = "${message(code: 'default.updated.message', args: [message(code: 'avanceFisico.label', default: 'AvanceFisico'), avanceFisicoInstance.id])}"
//                    redirect(action: "show", id: avanceFisicoInstance.id)
//                }
//                else {
//                    render(view: "form", model: [avanceFisicoInstance: avanceFisicoInstance, title:title, source: "edit"])
//                }
//            }
//            else {
//                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'avanceFisico.label', default: 'AvanceFisico'), params.id])}"
//                redirect(action: "list")
//            }
//        } else {
//            title = g.message(code:"avancefisico.create", default:"Create AvanceFisico")
//            def avanceFisicoInstance = new AvanceFisico(params)
//            if (avanceFisicoInstance.save(flush: true)) {
//                flash.message = "${message(code: 'default.created.message', args: [message(code: 'avanceFisico.label', default: 'AvanceFisico'), avanceFisicoInstance.id])}"
//                redirect(action: "show", id: avanceFisicoInstance.id)
//            }
//            else {
//                render(view: "form", model: [avanceFisicoInstance: avanceFisicoInstance, title:title, source: "create"])
//            }
//        }
//    }
//
//    def update = {
//        def avanceFisicoInstance = AvanceFisico.get(params.id)
//        if (avanceFisicoInstance) {
//            if (params.version) {
//                def version = params.version.toLong()
//                if (avanceFisicoInstance.version > version) {
//
//                    avanceFisicoInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'avanceFisico.label', default: 'AvanceFisico')] as Object[], "Another user has updated this AvanceFisico while you were editing")
//                    render(view: "edit", model: [avanceFisicoInstance: avanceFisicoInstance])
//                    return
//                }
//            }
//            avanceFisicoInstance.properties = params
//            if (!avanceFisicoInstance.hasErrors() && avanceFisicoInstance.save(flush: true)) {
//                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'avanceFisico.label', default: 'AvanceFisico'), avanceFisicoInstance.id])}"
//                redirect(action: "show", id: avanceFisicoInstance.id)
//            }
//            else {
//                render(view: "edit", model: [avanceFisicoInstance: avanceFisicoInstance])
//            }
//        }
//        else {
//            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'avanceFisico.label', default: 'AvanceFisico'), params.id])}"
//            redirect(action: "list")
//        }
//    }
//
//    def show = {
//        def avanceFisicoInstance = AvanceFisico.get(params.id)
//        if (!avanceFisicoInstance) {
//            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'avanceFisico.label', default: 'AvanceFisico'), params.id])}"
//            redirect(action: "list")
//        }
//        else {
//
//            def title = g.message(code:"avancefisico.show", default:"Show AvanceFisico")
//
//            [avanceFisicoInstance: avanceFisicoInstance, title: title]
//        }
//    }
//
//    def edit = {
//        params.source = "edit"
//        redirect(action:"form", params:params)
//    }
//
//    def delete = {
//        def avanceFisicoInstance = AvanceFisico.get(params.id)
//        if (avanceFisicoInstance) {
//            try {
//                avanceFisicoInstance.delete(flush: true)
//                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'avanceFisico.label', default: 'AvanceFisico'), params.id])}"
//                redirect(action: "list")
//            }
//            catch (org.springframework.dao.DataIntegrityViolationException e) {
//                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'avanceFisico.label', default: 'AvanceFisico'), params.id])}"
//                redirect(action: "show", id: params.id)
//            }
//        }
//        else {
//            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'avanceFisico.label', default: 'AvanceFisico'), params.id])}"
//            redirect(action: "list")
//        }
//    }
}
