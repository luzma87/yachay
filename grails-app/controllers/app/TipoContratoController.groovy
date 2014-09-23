package app

class TipoContratoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST", delete: "GET"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        def title = g.message(code: "tipocontrato.list", default: "TipoContrato List")
//        <g:message code="default.list.label" args="[entityName]" />

        params.max = Math.min(params.max ? params.int('max') : 10, 100)

        [tipoContratoInstanceList: TipoContrato.list(params), tipoContratoInstanceTotal: TipoContrato.count(), title: title, params: params]
    }

    def form = {
        def title
        def tipoContratoInstance

        if (params.source == "create") {
            tipoContratoInstance = new TipoContrato()
            tipoContratoInstance.properties = params
            title = g.message(code: "tipocontrato.create", default: "Create TipoContrato")
        } else if (params.source == "edit") {
            tipoContratoInstance = TipoContrato.get(params.id)
            if (!tipoContratoInstance) {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tipoContrato.label', default: 'TipoContrato'), params.id])}"
                redirect(action: "list")
            }
            title = g.message(code: "tipocontrato.edit", default: "Edit TipoContrato")
        }

        return [tipoContratoInstance: tipoContratoInstance, title: title, source: params.source]
    }

    def create = {
        params.source = "create"
        redirect(action: "form", params: params)
    }

    def save = {
        def title
        if (params.id) {
            title = g.message(code: "tipocontrato.edit", default: "Edit TipoContrato")
            def tipoContratoInstance = TipoContrato.get(params.id)
            if (tipoContratoInstance) {
                tipoContratoInstance.properties = params
                if (!tipoContratoInstance.hasErrors() && tipoContratoInstance.save(flush: true)) {
                    flash.message = "${message(code: 'default.updated.message', args: [message(code: 'tipoContrato.label', default: 'TipoContrato'), tipoContratoInstance.id])}"
                    redirect(action: "show", id: tipoContratoInstance.id)
                } else {
                    render(view: "form", model: [tipoContratoInstance: tipoContratoInstance, title: title, source: "edit"])
                }
            } else {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tipoContrato.label', default: 'TipoContrato'), params.id])}"
                redirect(action: "list")
            }
        } else {
            title = g.message(code: "tipocontrato.create", default: "Create TipoContrato")
            def tipoContratoInstance = new TipoContrato(params)
            if (tipoContratoInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.created.message', args: [message(code: 'tipoContrato.label', default: 'TipoContrato'), tipoContratoInstance.id])}"
                redirect(action: "show", id: tipoContratoInstance.id)
            } else {
                render(view: "form", model: [tipoContratoInstance: tipoContratoInstance, title: title, source: "create"])
            }
        }
    }

    def update = {
        def tipoContratoInstance = TipoContrato.get(params.id)
        if (tipoContratoInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (tipoContratoInstance.version > version) {

                    tipoContratoInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'tipoContrato.label', default: 'TipoContrato')] as Object[], "Another user has updated this TipoContrato while you were editing")
                    render(view: "edit", model: [tipoContratoInstance: tipoContratoInstance])
                    return
                }
            }
            tipoContratoInstance.properties = params
            if (!tipoContratoInstance.hasErrors() && tipoContratoInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'tipoContrato.label', default: 'TipoContrato'), tipoContratoInstance.id])}"
                redirect(action: "show", id: tipoContratoInstance.id)
            } else {
                render(view: "edit", model: [tipoContratoInstance: tipoContratoInstance])
            }
        } else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tipoContrato.label', default: 'TipoContrato'), params.id])}"
            redirect(action: "list")
        }
    }

    def show = {
        def tipoContratoInstance = TipoContrato.get(params.id)
        if (!tipoContratoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tipoContrato.label', default: 'TipoContrato'), params.id])}"
            redirect(action: "list")
        } else {

            def title = g.message(code: "tipocontrato.show", default: "Show TipoContrato")

            [tipoContratoInstance: tipoContratoInstance, title: title]
        }
    }

    def edit = {
        params.source = "edit"
        redirect(action: "form", params: params)
    }

    def delete = {
        def tipoContratoInstance = TipoContrato.get(params.id)
        if (tipoContratoInstance) {
            try {
                tipoContratoInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'tipoContrato.label', default: 'TipoContrato'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'tipoContrato.label', default: 'TipoContrato'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        } else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tipoContrato.label', default: 'TipoContrato'), params.id])}"
            redirect(action: "list")
        }
    }
}
