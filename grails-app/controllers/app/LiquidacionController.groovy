package app

import yachay.proyectos.Liquidacion
import yachay.proyectos.Obra

class LiquidacionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST", delete: "GET"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        def title = g.message(code: "liquidacion.list", default: "Liquidacion List")
//        <g:message code="default.list.label" args="[entityName]" />

        params.max = Math.min(params.max ? params.int('max') : 10, 100)

        [liquidacionInstanceList: Liquidacion.list(params), liquidacionInstanceTotal: Liquidacion.count(), title: title, params: params]
    }

    def form = {
        def title
        def liquidacionInstance

        if (params.source == "create") {
            liquidacionInstance = new Liquidacion()
            liquidacionInstance.properties = params
            title = g.message(code: "liquidacion.create", default: "Create Liquidacion")
        } else if (params.source == "edit") {
            liquidacionInstance = Liquidacion.get(params.id)
            if (!liquidacionInstance) {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'liquidacion.label', default: 'Liquidacion'), params.id])}"
                redirect(action: "list")
            }
            title = g.message(code: "liquidacion.edit", default: "Edit Liquidacion")
        }

        return [liquidacionInstance: liquidacionInstance, title: title, source: params.source]
    }

    def create = {
        params.source = "create"
        redirect(action: "form", params: params)
    }

    def datosLiquidacion = {
        def obra = Obra.get(params.id)
        def liq= Liquidacion.findByObra(obra)
        def source = "create"
        if (liq)
            source="edit"
        [liq:liq,obra:obra,source: source]
    }

    def save = {
        def title
        if (params.id) {
            title = g.message(code: "liquidacion.edit", default: "Edit Liquidacion")
            def liquidacionInstance = Liquidacion.get(params.id)
            if (liquidacionInstance) {
                liquidacionInstance.properties = params
                if (!liquidacionInstance.hasErrors() && liquidacionInstance.save(flush: true)) {
                    flash.message = "${message(code: 'default.updated.message', args: [message(code: 'liquidacion.label', default: 'Liquidacion'), liquidacionInstance.id])}"
                    redirect(action: "show", id: liquidacionInstance.id)
                }
                else {
                    render(view: "form", model: [liquidacionInstance: liquidacionInstance, title: title, source: "edit"])
                }
            }
            else {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'liquidacion.label', default: 'Liquidacion'), params.id])}"
                redirect(action: "list")
            }
        } else {
            title = g.message(code: "liquidacion.create", default: "Create Liquidacion")
            def liquidacionInstance = new Liquidacion(params)
            if (liquidacionInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.created.message', args: [message(code: 'liquidacion.label', default: 'Liquidacion'), liquidacionInstance.id])}"
                redirect(action: "show", id: liquidacionInstance.id)
            }
            else {
                render(view: "form", model: [liquidacionInstance: liquidacionInstance, title: title, source: "create"])
            }
        }
    }




    def update = {
        def liquidacionInstance = Liquidacion.get(params.id)
        if (liquidacionInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (liquidacionInstance.version > version) {

                    liquidacionInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'liquidacion.label', default: 'Liquidacion')] as Object[], "Another user has updated this Liquidacion while you were editing")
                    render(view: "edit", model: [liquidacionInstance: liquidacionInstance])
                    return
                }
            }
            liquidacionInstance.properties = params
            if (!liquidacionInstance.hasErrors() && liquidacionInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'liquidacion.label', default: 'Liquidacion'), liquidacionInstance.id])}"
                redirect(action: "show", id: liquidacionInstance.id)
            }
            else {
                render(view: "edit", model: [liquidacionInstance: liquidacionInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'liquidacion.label', default: 'Liquidacion'), params.id])}"
            redirect(action: "list")
        }
    }

    def show = {
        def liquidacionInstance = Liquidacion.get(params.id)
        if (!liquidacionInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'liquidacion.label', default: 'Liquidacion'), params.id])}"
            redirect(action: "list")
        }
        else {

            def title = g.message(code: "liquidacion.show", default: "Show Liquidacion")

            [liquidacionInstance: liquidacionInstance, title: title]
        }
    }

    def edit = {
        params.source = "edit"
        redirect(action: "form", params: params)
    }

    def delete = {
        def liquidacionInstance = Liquidacion.get(params.id)
        if (liquidacionInstance) {
            try {
                liquidacionInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'liquidacion.label', default: 'Liquidacion'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'liquidacion.label', default: 'Liquidacion'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'liquidacion.label', default: 'Liquidacion'), params.id])}"
            redirect(action: "list")
        }
    }
}
