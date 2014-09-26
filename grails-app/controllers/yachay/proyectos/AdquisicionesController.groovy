package yachay.proyectos

import yachay.proyectos.Adquisiciones

/**
 * Controlador
 */
class AdquisicionesController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST", delete: "GET"]

    /**
     * Acción
     */
    def index = {
        redirect(action: "list", params: params)
    }

    /**
     * Acción
     */
    def list = {
        def title = g.message(code:"default.list.label", args:["Adquisiciones"], default:"Adquisiciones List")
//        <g:message code="default.list.label" args="[entityName]" />
        
        params.max = Math.min(params.max ? params.int('max') : 10, 100)

        [adquisicionesInstanceList: Adquisiciones.list(params), adquisicionesInstanceTotal: Adquisiciones.count(), title: title, params:params]
    }

    /**
     * Acción
     */
    def form = {
        def title
        def adquisicionesInstance

        if(params.source == "create") {
            adquisicionesInstance = new Adquisiciones()
            adquisicionesInstance.properties = params
            title = g.message(code:"default.create.label", args:["Adquisiciones"], default:"crearAdquisiciones")
        } else if(params.source == "edit") {
            adquisicionesInstance = Adquisiciones.get(params.id)
            if (!adquisicionesInstance) {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'adquisiciones.label', default: 'Adquisiciones'), params.id])}"
                redirect(action: "list")
            }
            title = g.message(code:"default.edit.label", args:["Adquisiciones"], default:"editarAdquisiciones")
        }

        return [adquisicionesInstance: adquisicionesInstance, title:title, source: params.source]
    }

    /**
     * Acción
     */
    def create = {
        params.source = "create"
        redirect(action:"form", params:params)
    }

    /**
     * Acción
     */
    def save = {
        def title
        if(params.id) {
            title = g.message(code:"default.edit.label", args:["Adquisiciones"], default:"Edit Adquisiciones")
            def adquisicionesInstance = Adquisiciones.get(params.id)
            if (adquisicionesInstance) {
                adquisicionesInstance.properties = params
                if (!adquisicionesInstance.hasErrors() && adquisicionesInstance.save(flush: true)) {
                    flash.message = "${message(code: 'default.updated.message', args: [message(code: 'adquisiciones.label', default: 'Adquisiciones'), adquisicionesInstance.id])}"
                    redirect(action: "show", id: adquisicionesInstance.id)
                }
                else {
                    render(view: "form", model: [adquisicionesInstance: adquisicionesInstance, title:title, source: "edit"])
                }
            }
            else {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'adquisiciones.label', default: 'Adquisiciones'), params.id])}"
                redirect(action: "list")
            }
        } else {
            title = g.message(code:"default.create.label", args:["Adquisiciones"], default:"Create Adquisiciones")
            def adquisicionesInstance = new Adquisiciones(params)
            if (adquisicionesInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.created.message', args: [message(code: 'adquisiciones.label', default: 'Adquisiciones'), adquisicionesInstance.id])}"
                redirect(action: "show", id: adquisicionesInstance.id)
            }
            else {
                render(view: "form", model: [adquisicionesInstance: adquisicionesInstance, title:title, source: "create"])
            }
        }
    }

    /**
     * Acción
     */
    def update = {
        def adquisicionesInstance = Adquisiciones.get(params.id)
        if (adquisicionesInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (adquisicionesInstance.version > version) {
                    
                    adquisicionesInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'adquisiciones.label', default: 'Adquisiciones')] as Object[], "Another user has updated this Adquisiciones while you were editing")
                    render(view: "edit", model: [adquisicionesInstance: adquisicionesInstance])
                    return
                }
            }
            adquisicionesInstance.properties = params
            if (!adquisicionesInstance.hasErrors() && adquisicionesInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'adquisiciones.label', default: 'Adquisiciones'), adquisicionesInstance.id])}"
                redirect(action: "show", id: adquisicionesInstance.id)
            }
            else {
                render(view: "edit", model: [adquisicionesInstance: adquisicionesInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'adquisiciones.label', default: 'Adquisiciones'), params.id])}"
            redirect(action: "list")
        }
    }

    /**
     * Acción
     */
    def show = {
        def adquisicionesInstance = Adquisiciones.get(params.id)
        if (!adquisicionesInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'adquisiciones.label', default: 'Adquisiciones'), params.id])}"
            redirect(action: "list")
        }
        else {

            def title = g.message(code:"default.show.label", args:["Adquisiciones"], default:"Show Adquisiciones")

            [adquisicionesInstance: adquisicionesInstance, title: title]
        }
    }

    /**
     * Acción
     */
    def edit = {
        params.source = "edit"
        redirect(action:"form", params:params)
    }

    /**
     * Acción
     */
    def delete = {
        def adquisicionesInstance = Adquisiciones.get(params.id)
        if (adquisicionesInstance) {
            try {
                adquisicionesInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'adquisiciones.label', default: 'Adquisiciones'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'adquisiciones.label', default: 'Adquisiciones'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'adquisiciones.label', default: 'Adquisiciones'), params.id])}"
            redirect(action: "list")
        }
    }
}
