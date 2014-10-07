package yachay.parametros

class AuxiliarController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST", delete: "GET"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        def title = g.message(code: "auxiliar.list", default: "Auxiliar List")
//        <g:message code="default.list.label" args="[entityName]" />

        params.max = Math.min(params.max ? params.int('max') : 10, 100)

        [auxiliarInstanceList: Auxiliar.list(params), auxiliarInstanceTotal: Auxiliar.count(), title: title, params: params]
    }

    def form = {
        def title
        def auxiliarInstance

        if (params.source == "create") {
            auxiliarInstance = new Auxiliar()
            auxiliarInstance.properties = params
            title = g.message(code: "auxiliar.create", default: "Create Auxiliar")
        } else if (params.source == "edit") {
            auxiliarInstance = Auxiliar.get(params.id)
            if (!auxiliarInstance) {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'auxiliar.label', default: 'Auxiliar'), params.id])}"
                redirect(action: "list")
            }
            title = g.message(code: "auxiliar.edit", default: "Edit Auxiliar")
        }

        return [auxiliarInstance: auxiliarInstance, title: title, source: params.source]
    }

    def create = {
        params.source = "create"
        redirect(action: "form", params: params)
    }

    def save = {
        def title
        println "paras "+params
        params.presupuesto = params.presupuesto.toDouble()
        println "params 2 " +params
        if (params.id) {
            title = g.message(code: "auxiliar.edit", default: "Edit Auxiliar")
            def auxiliarInstance = Auxiliar.get(params.id)
            if (auxiliarInstance) {
                auxiliarInstance.properties = params
                if (!auxiliarInstance.hasErrors() && auxiliarInstance.save(flush: true)) {
                    flash.message = "${message(code: 'default.updated.message', args: [message(code: 'auxiliar.label', default: 'Auxiliar'), auxiliarInstance.id])}"
                    redirect(action: "show", id: auxiliarInstance.id)
                } else {
                    render(view: "form", model: [auxiliarInstance: auxiliarInstance, title: title, source: "edit"])
                }
            } else {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'auxiliar.label', default: 'Auxiliar'), params.id])}"
                redirect(action: "list")
            }
        } else {
            title = g.message(code: "auxiliar.create", default: "Create Auxiliar")
            def auxiliarInstance = new Auxiliar(params)
            if (auxiliarInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.created.message', args: [message(code: 'auxiliar.label', default: 'Auxiliar'), auxiliarInstance.id])}"
                redirect(action: "show", id: auxiliarInstance.id)
            } else {
                render(view: "form", model: [auxiliarInstance: auxiliarInstance, title: title, source: "create"])
            }
        }
    }

    def update = {
        def auxiliarInstance = Auxiliar.get(params.id)
        if (auxiliarInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (auxiliarInstance.version > version) {

                    auxiliarInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'auxiliar.label', default: 'Auxiliar')] as Object[], "Another user has updated this Auxiliar while you were editing")
                    render(view: "edit", model: [auxiliarInstance: auxiliarInstance])
                    return
                }
            }
            auxiliarInstance.properties = params
            if (!auxiliarInstance.hasErrors() && auxiliarInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'auxiliar.label', default: 'Auxiliar'), auxiliarInstance.id])}"
                redirect(action: "show", id: auxiliarInstance.id)
            } else {
                render(view: "edit", model: [auxiliarInstance: auxiliarInstance])
            }
        } else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'auxiliar.label', default: 'Auxiliar'), params.id])}"
            redirect(action: "list")
        }
    }

    def show = {
        def auxiliarInstance = Auxiliar.get(params.id)
        if (!auxiliarInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'auxiliar.label', default: 'Auxiliar'), params.id])}"
            redirect(action: "list")
        } else {

            def title = g.message(code: "auxiliar.show", default: "Show Auxiliar")

            [auxiliarInstance: auxiliarInstance, title: title]
        }
    }

    def edit = {
        params.source = "edit"
        redirect(action: "form", params: params)
    }

    def delete = {
        def auxiliarInstance = Auxiliar.get(params.id)
        if (auxiliarInstance) {
            try {
                auxiliarInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'auxiliar.label', default: 'Auxiliar'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'auxiliar.label', default: 'Auxiliar'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        } else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'auxiliar.label', default: 'Auxiliar'), params.id])}"
            redirect(action: "list")
        }
    }
}
