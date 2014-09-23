package yachay.avales

import yachay.avales.EstadoAval

class EstadoAvalController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST", delete: "GET"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        def title = g.message(code: "estadoaval.list", default: "EstadoAval List")
//        <g:message code="default.list.label" args="[entityName]" />

        params.max = Math.min(params.max ? params.int('max') : 10, 100)

        [estadoAvalInstanceList: EstadoAval.list(params), estadoAvalInstanceTotal: EstadoAval.count(), title: title, params: params]
    }

    def form = {
        def title
        def estadoAvalInstance

        if (params.source == "create") {
            estadoAvalInstance = new EstadoAval()
            estadoAvalInstance.properties = params
            title = g.message(code: "estadoaval.create", default: "Create EstadoAval")
        } else if (params.source == "edit") {
            estadoAvalInstance = EstadoAval.get(params.id)
            if (!estadoAvalInstance) {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'estadoAval.label', default: 'EstadoAval'), params.id])}"
                redirect(action: "list")
            }
            title = g.message(code: "estadoaval.edit", default: "Edit EstadoAval")
        }

        return [estadoAvalInstance: estadoAvalInstance, title: title, source: params.source]
    }

    def create = {
        params.source = "create"
        redirect(action: "form", params: params)
    }

    def save = {
        def title
        if (params.id) {
            title = g.message(code: "estadoaval.edit", default: "Edit EstadoAval")
            def estadoAvalInstance = EstadoAval.get(params.id)
            if (estadoAvalInstance) {
                estadoAvalInstance.properties = params
                if (!estadoAvalInstance.hasErrors() && estadoAvalInstance.save(flush: true)) {
                    flash.message = "${message(code: 'default.updated.message', args: [message(code: 'estadoAval.label', default: 'EstadoAval'), estadoAvalInstance.id])}"
                    redirect(action: "show", id: estadoAvalInstance.id)
                } else {
                    render(view: "form", model: [estadoAvalInstance: estadoAvalInstance, title: title, source: "edit"])
                }
            } else {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'estadoAval.label', default: 'EstadoAval'), params.id])}"
                redirect(action: "list")
            }
        } else {
            title = g.message(code: "estadoaval.create", default: "Create EstadoAval")
            def estadoAvalInstance = new EstadoAval(params)
            if (estadoAvalInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.created.message', args: [message(code: 'estadoAval.label', default: 'EstadoAval'), estadoAvalInstance.id])}"
                redirect(action: "show", id: estadoAvalInstance.id)
            } else {
                render(view: "form", model: [estadoAvalInstance: estadoAvalInstance, title: title, source: "create"])
            }
        }
    }

    def update = {
        def estadoAvalInstance = EstadoAval.get(params.id)
        if (estadoAvalInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (estadoAvalInstance.version > version) {

                    estadoAvalInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'estadoAval.label', default: 'EstadoAval')] as Object[], "Another user has updated this EstadoAval while you were editing")
                    render(view: "edit", model: [estadoAvalInstance: estadoAvalInstance])
                    return
                }
            }
            estadoAvalInstance.properties = params
            if (!estadoAvalInstance.hasErrors() && estadoAvalInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'estadoAval.label', default: 'EstadoAval'), estadoAvalInstance.id])}"
                redirect(action: "show", id: estadoAvalInstance.id)
            } else {
                render(view: "edit", model: [estadoAvalInstance: estadoAvalInstance])
            }
        } else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'estadoAval.label', default: 'EstadoAval'), params.id])}"
            redirect(action: "list")
        }
    }

    def show = {
        def estadoAvalInstance = EstadoAval.get(params.id)
        if (!estadoAvalInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'estadoAval.label', default: 'EstadoAval'), params.id])}"
            redirect(action: "list")
        } else {

            def title = g.message(code: "estadoaval.show", default: "Show EstadoAval")

            [estadoAvalInstance: estadoAvalInstance, title: title]
        }
    }

    def edit = {
        params.source = "edit"
        redirect(action: "form", params: params)
    }

    def delete = {
        def estadoAvalInstance = EstadoAval.get(params.id)
        if (estadoAvalInstance) {
            try {
                estadoAvalInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'estadoAval.label', default: 'EstadoAval'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'estadoAval.label', default: 'EstadoAval'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        } else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'estadoAval.label', default: 'EstadoAval'), params.id])}"
            redirect(action: "list")
        }
    }
}
