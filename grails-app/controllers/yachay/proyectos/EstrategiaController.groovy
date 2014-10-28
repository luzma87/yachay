package yachay.proyectos

class EstrategiaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST", delete: "GET"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        def title = g.message(code: "estrategia.list", default: "Estrategia List")
//        <g:message code="default.list.label" args="[entityName]" />

        params.max = Math.min(params.max ? params.int('max') : 10, 100)

        [estrategiaInstanceList: Estrategia.list(params), estrategiaInstanceTotal: Estrategia.count(), title: title, params: params]
    }

    def form = {
        def title
        def estrategiaInstance

        if (params.source == "create") {
            estrategiaInstance = new Estrategia()
            estrategiaInstance.properties = params
            title = g.message(code: "estrategia.create", default: "Create Estrategia")
        } else if (params.source == "edit") {
            estrategiaInstance = Estrategia.get(params.id)
            if (!estrategiaInstance) {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'estrategia.label', default: 'Estrategia'), params.id])}"
                redirect(action: "list")
            }
            title = g.message(code: "estrategia.edit", default: "Edit Estrategia")
        }

        return [estrategiaInstance: estrategiaInstance, title: title, source: params.source]
    }

    def create = {
        params.source = "create"
        redirect(action: "form", params: params)
    }

    def save = {
        println params
        def title
        if (params.id) {
            title = g.message(code: "estrategia.edit", default: "Edit Estrategia")
            def estrategiaInstance = Estrategia.get(params.id)
            if (estrategiaInstance) {
                estrategiaInstance.properties = params
                if (!estrategiaInstance.hasErrors() && estrategiaInstance.save(flush: true)) {
                    flash.message = "${message(code: 'default.updated.message', args: [message(code: 'estrategia.label', default: 'Estrategia'), estrategiaInstance.id])}"
                    redirect(action: "show", id: estrategiaInstance.id)
                } else {
                    render(view: "form", model: [estrategiaInstance: estrategiaInstance, title: title, source: "edit"])
                }
            } else {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'estrategia.label', default: 'Estrategia'), params.id])}"
                redirect(action: "list")
            }
        } else {
            title = g.message(code: "estrategia.create", default: "Create Estrategia")
            def estrategiaInstance = new Estrategia(params)
            if (estrategiaInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.created.message', args: [message(code: 'estrategia.label', default: 'Estrategia'), estrategiaInstance.id])}"
                redirect(action: "show", id: estrategiaInstance.id)
            } else {
                render(view: "form", model: [estrategiaInstance: estrategiaInstance, title: title, source: "create"])
            }
        }
    }

    def update = {
        def estrategiaInstance = Estrategia.get(params.id)
        if (estrategiaInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (estrategiaInstance.version > version) {

                    estrategiaInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'estrategia.label', default: 'Estrategia')] as Object[], "Another user has updated this Estrategia while you were editing")
                    render(view: "edit", model: [estrategiaInstance: estrategiaInstance])
                    return
                }
            }
            estrategiaInstance.properties = params
            if (!estrategiaInstance.hasErrors() && estrategiaInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'estrategia.label', default: 'Estrategia'), estrategiaInstance.id])}"
                redirect(action: "show", id: estrategiaInstance.id)
            } else {
                render(view: "edit", model: [estrategiaInstance: estrategiaInstance])
            }
        } else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'estrategia.label', default: 'Estrategia'), params.id])}"
            redirect(action: "list")
        }
    }

    def show = {
        def estrategiaInstance = Estrategia.get(params.id)
        if (!estrategiaInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'estrategia.label', default: 'Estrategia'), params.id])}"
            redirect(action: "list")
        } else {

            def title = g.message(code: "estrategia.show", default: "Show Estrategia")

            [estrategiaInstance: estrategiaInstance, title: title]
        }
    }

    def edit = {
        params.source = "edit"
        redirect(action: "form", params: params)
    }

    def delete = {
        def estrategiaInstance = Estrategia.get(params.id)
        if (estrategiaInstance) {
            try {
                estrategiaInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'estrategia.label', default: 'Estrategia'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'estrategia.label', default: 'Estrategia'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        } else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'estrategia.label', default: 'Estrategia'), params.id])}"
            redirect(action: "list")
        }
    }
}
