package yachay.parametros

import yachay.parametros.TipoDocumento

class TipoDocumentoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST", delete: "GET"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        def title = g.message(code: "tipodocumento.list", default: "TipoDocumento List")
//        <g:message code="default.list.label" args="[entityName]" />

        params.max = Math.min(params.max ? params.int('max') : 10, 100)

        [tipoDocumentoInstanceList: TipoDocumento.list(params), tipoDocumentoInstanceTotal: TipoDocumento.count(), title: title, params: params]
    }

    def form = {
        def title
        def tipoDocumentoInstance

        if (params.source == "create") {
            tipoDocumentoInstance = new TipoDocumento()
            tipoDocumentoInstance.properties = params
            title = g.message(code: "tipodocumento.create", default: "Create TipoDocumento")
        } else if (params.source == "edit") {
            tipoDocumentoInstance = TipoDocumento.get(params.id)
            if (!tipoDocumentoInstance) {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tipoDocumento.label', default: 'TipoDocumento'), params.id])}"
                redirect(action: "list")
            }
            title = g.message(code: "tipodocumento.edit", default: "Edit TipoDocumento")
        }

        return [tipoDocumentoInstance: tipoDocumentoInstance, title: title, source: params.source]
    }

    def create = {
        params.source = "create"
        redirect(action: "form", params: params)
    }

    def save = {
        def title
        if (params.id) {
            title = g.message(code: "tipodocumento.edit", default: "Edit TipoDocumento")
            def tipoDocumentoInstance = TipoDocumento.get(params.id)
            if (tipoDocumentoInstance) {
                tipoDocumentoInstance.properties = params
                if (!tipoDocumentoInstance.hasErrors() && tipoDocumentoInstance.save(flush: true)) {
                    flash.message = "${message(code: 'default.updated.message', args: [message(code: 'tipoDocumento.label', default: 'TipoDocumento'), tipoDocumentoInstance.id])}"
                    redirect(action: "show", id: tipoDocumentoInstance.id)
                }
                else {
                    render(view: "form", model: [tipoDocumentoInstance: tipoDocumentoInstance, title: title, source: "edit"])
                }
            }
            else {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tipoDocumento.label', default: 'TipoDocumento'), params.id])}"
                redirect(action: "list")
            }
        } else {
            title = g.message(code: "tipodocumento.create", default: "Create TipoDocumento")
            def tipoDocumentoInstance = new TipoDocumento(params)
            if (tipoDocumentoInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.created.message', args: [message(code: 'tipoDocumento.label', default: 'TipoDocumento'), tipoDocumentoInstance.id])}"
                redirect(action: "show", id: tipoDocumentoInstance.id)
            }
            else {
                render(view: "form", model: [tipoDocumentoInstance: tipoDocumentoInstance, title: title, source: "create"])
            }
        }
    }

    def update = {
        def tipoDocumentoInstance = TipoDocumento.get(params.id)
        if (tipoDocumentoInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (tipoDocumentoInstance.version > version) {

                    tipoDocumentoInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'tipoDocumento.label', default: 'TipoDocumento')] as Object[], "Another user has updated this TipoDocumento while you were editing")
                    render(view: "edit", model: [tipoDocumentoInstance: tipoDocumentoInstance])
                    return
                }
            }
            tipoDocumentoInstance.properties = params
            if (!tipoDocumentoInstance.hasErrors() && tipoDocumentoInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'tipoDocumento.label', default: 'TipoDocumento'), tipoDocumentoInstance.id])}"
                redirect(action: "show", id: tipoDocumentoInstance.id)
            }
            else {
                render(view: "edit", model: [tipoDocumentoInstance: tipoDocumentoInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tipoDocumento.label', default: 'TipoDocumento'), params.id])}"
            redirect(action: "list")
        }
    }

    def show = {
        def tipoDocumentoInstance = TipoDocumento.get(params.id)
        if (!tipoDocumentoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tipoDocumento.label', default: 'TipoDocumento'), params.id])}"
            redirect(action: "list")
        }
        else {

            def title = g.message(code: "tipodocumento.show", default: "Show TipoDocumento")

            [tipoDocumentoInstance: tipoDocumentoInstance, title: title]
        }
    }

    def edit = {
        params.source = "edit"
        redirect(action: "form", params: params)
    }

    def delete = {
        def tipoDocumentoInstance = TipoDocumento.get(params.id)
        if (tipoDocumentoInstance) {
            try {
                tipoDocumentoInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'tipoDocumento.label', default: 'TipoDocumento'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'tipoDocumento.label', default: 'TipoDocumento'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'tipoDocumento.label', default: 'TipoDocumento'), params.id])}"
            redirect(action: "list")
        }
    }
}
