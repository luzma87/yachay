package app

class CantonController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST", delete: "GET"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        def title = g.message(code: "default.list.label", args: ["Canton"], default: "Canton List")
//        <g:message code="default.list.label" args="[entityName]" />

        params.max = Math.min(params.max ? params.int('max') : 10, 100)

        [cantonInstanceList: Canton.list(params), cantonInstanceTotal: Canton.count(), title: title, params: params]
    }

    def form = {
        def title
        def cantonInstance

        if (params.source == "create") {
            cantonInstance = new Canton()
            cantonInstance.properties = params
            title = g.message(code: "default.create.label", args: ["Canton"], default: "crearCanton")
        } else if (params.source == "edit") {
            cantonInstance = Canton.get(params.id)
            if (!cantonInstance) {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'canton.label', default: 'Canton'), params.id])}"
                redirect(action: "list")
            }
            title = g.message(code: "default.edit.label", args: ["Canton"], default: "editarCanton")
        }

        return [cantonInstance: cantonInstance, title: title, source: params.source]
    }

    def create = {
        params.source = "create"
        redirect(action: "form", params: params)
    }

    def save = {
        def title
        if (params.id) {
            title = g.message(code: "default.edit.label", args: ["Canton"], default: "Edit Canton")
            def cantonInstance = Canton.get(params.id)
            if (cantonInstance) {
                cantonInstance.properties = params
                if (!cantonInstance.hasErrors() && cantonInstance.save(flush: true)) {
                    flash.message = "${message(code: 'default.updated.message', args: [message(code: 'canton.label', default: 'Canton'), cantonInstance.id])}"
                    redirect(action: "show", id: cantonInstance.id)
                }
                else {
                    render(view: "form", model: [cantonInstance: cantonInstance, title: title, source: "edit"])
                }
            }
            else {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'canton.label', default: 'Canton'), params.id])}"
                redirect(action: "list")
            }
        } else {
            title = g.message(code: "default.create.label", args: ["Canton"], default: "Create Canton")
            def cantonInstance = new Canton(params)
            if (cantonInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.created.message', args: [message(code: 'canton.label', default: 'Canton'), cantonInstance.id])}"
                redirect(action: "show", id: cantonInstance.id)
            }
            else {
                render(view: "form", model: [cantonInstance: cantonInstance, title: title, source: "create"])
            }
        }
    }

    def update = {
        def cantonInstance = Canton.get(params.id)
        if (cantonInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (cantonInstance.version > version) {

                    cantonInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'canton.label', default: 'Canton')] as Object[], "Another user has updated this Canton while you were editing")
                    render(view: "edit", model: [cantonInstance: cantonInstance])
                    return
                }
            }
            cantonInstance.properties = params
            if (!cantonInstance.hasErrors() && cantonInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'canton.label', default: 'Canton'), cantonInstance.id])}"
                redirect(action: "show", id: cantonInstance.id)
            }
            else {
                render(view: "edit", model: [cantonInstance: cantonInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'canton.label', default: 'Canton'), params.id])}"
            redirect(action: "list")
        }
    }

    def show = {
        def cantonInstance = Canton.get(params.id)
        if (!cantonInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'canton.label', default: 'Canton'), params.id])}"
            redirect(action: "list")
        }
        else {

            def title = g.message(code: "default.show.label", args: ["Canton"], default: "Show Canton")

            [cantonInstance: cantonInstance, title: title]
        }
    }

    def edit = {
        params.source = "edit"
        redirect(action: "form", params: params)
    }

    def delete = {
        def cantonInstance = Canton.get(params.id)
        if (cantonInstance) {
            try {
                cantonInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'canton.label', default: 'Canton'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'canton.label', default: 'Canton'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'canton.label', default: 'Canton'), params.id])}"
            redirect(action: "list")
        }
    }
}
