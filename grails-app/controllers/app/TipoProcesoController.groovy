package app

import yachay.parametros.TipoProceso

class TipoProcesoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST", delete: "GET"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        def title = g.message(code: "tipoproceso.list", default: "TipoProceso List")
//        <g:message code="default.list.label" args="[entityName]" />

        params.max = Math.min(params.max ? params.int('max') : 10, 100)

        [tipoProcesoInstanceList: TipoProceso.list(params), tipoProcesoInstanceTotal: TipoProceso.count(), title: title, params: params]
    }

    def form = {
        def title
        def tipoProcesoInstance

        if (params.source == "create") {
            tipoProcesoInstance = new TipoProceso()
            tipoProcesoInstance.properties = params
            title = g.message(code: "tipoproceso.create", default: "Create TipoProceso")
        } else if (params.source == "edit") {
            tipoProcesoInstance = TipoProceso.get(params.id)
            if (!tipoProcesoInstance) {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tipoProceso.label', default: 'TipoProceso'), params.id])}"
                redirect(action: "list")
            }
            title = g.message(code: "tipoproceso.edit", default: "Edit TipoProceso")
        }

        return [tipoProcesoInstance: tipoProcesoInstance, title: title, source: params.source]
    }

    def create = {
        params.source = "create"
        redirect(action: "form", params: params)
    }

    def save = {
        def title
        if (params.id) {
            title = g.message(code: "tipoproceso.edit", default: "Edit TipoProceso")
            def tipoProcesoInstance = TipoProceso.get(params.id)
            if (tipoProcesoInstance) {
                tipoProcesoInstance.properties = params
                if (!tipoProcesoInstance.hasErrors() && tipoProcesoInstance.save(flush: true)) {
                    flash.message = "${message(code: 'default.updated.message', args: [message(code: 'tipoProceso.label', default: 'TipoProceso'), tipoProcesoInstance.id])}"
                    redirect(action: "show", id: tipoProcesoInstance.id)
                }
                else {
                    render(view: "form", model: [tipoProcesoInstance: tipoProcesoInstance, title: title, source: "edit"])
                }
            }
            else {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tipoProceso.label', default: 'TipoProceso'), params.id])}"
                redirect(action: "list")
            }
        } else {
            title = g.message(code: "tipoproceso.create", default: "Create TipoProceso")
            def tipoProcesoInstance = new TipoProceso(params)
            if (tipoProcesoInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.created.message', args: [message(code: 'tipoProceso.label', default: 'TipoProceso'), tipoProcesoInstance.id])}"
                redirect(action: "show", id: tipoProcesoInstance.id)
            }
            else {
                render(view: "form", model: [tipoProcesoInstance: tipoProcesoInstance, title: title, source: "create"])
            }
        }
    }

    def update = {
        def tipoProcesoInstance = TipoProceso.get(params.id)
        if (tipoProcesoInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (tipoProcesoInstance.version > version) {

                    tipoProcesoInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'tipoProceso.label', default: 'TipoProceso')] as Object[], "Another user has updated this TipoProceso while you were editing")
                    render(view: "edit", model: [tipoProcesoInstance: tipoProcesoInstance])
                    return
                }
            }
            tipoProcesoInstance.properties = params
            if (!tipoProcesoInstance.hasErrors() && tipoProcesoInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'tipoProceso.label', default: 'TipoProceso'), tipoProcesoInstance.id])}"
                redirect(action: "show", id: tipoProcesoInstance.id)
            }
            else {
                render(view: "edit", model: [tipoProcesoInstance: tipoProcesoInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tipoProceso.label', default: 'TipoProceso'), params.id])}"
            redirect(action: "list")
        }
    }

    def show = {
        def tipoProcesoInstance = TipoProceso.get(params.id)
        if (!tipoProcesoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tipoProceso.label', default: 'TipoProceso'), params.id])}"
            redirect(action: "list")
        }
        else {

            def title = g.message(code: "tipoproceso.show", default: "Show TipoProceso")

            [tipoProcesoInstance: tipoProcesoInstance, title: title]
        }
    }

    def edit = {
        params.source = "edit"
        redirect(action: "form", params: params)
    }

    def delete = {
        def tipoProcesoInstance = TipoProceso.get(params.id)
        if (tipoProcesoInstance) {
            try {
                tipoProcesoInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'tipoProceso.label', default: 'TipoProceso'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'tipoProceso.label', default: 'TipoProceso'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tipoProceso.label', default: 'TipoProceso'), params.id])}"
            redirect(action: "list")
        }
    }
}
