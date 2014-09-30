package yachay.seguridad

import yachay.seguridad.Persona

/**
 * Controlador que muestra las pantallas para el manejo de personas
 */
class PersonaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST", delete: "GET"]

    /**
     * Acción que redirecciona a list
     */
    def index = {
        redirect(action: "list", params: params)
    }

    /**
     * Acción que muestra una lista de las personas regitastradas en el sistema
     */
    def list = {
        def title = g.message(code: "persona.list", default: "Lista de personas")
//        <g:message code="default.list.label" args="[entityName]" />
        params.max = 20
        params.max = Math.min(params.max ? params.int('max') : 20, 100)

        [personaInstanceList: Persona.list(params), personaInstanceTotal: Persona.count(), title: title, params: params]
    }

    /**
     * Acción que muestra un formulario para la edición o inserción de personas
     */
    def form = {
        def title
        def personaInstance

        if (params.source == "edit") {
            personaInstance = Persona.get(params.id)
            if (!personaInstance) {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'persona.label', default: 'Persona'), params.id])}"
                redirect(action: "list")
            }
            title = g.message(code: "persona.edit", default: "Edit Persona")
        }  else  {
            personaInstance = new Persona()
            personaInstance.properties = params
            title = g.message(code: "persona.create", default: "Create Persona")
        }

        return [personaInstance: personaInstance, title: title, source: params.source]
    }

    /**
     * Acción que redirecciona a form
     */
    def create = {
        params.source = "create"
        redirect(action: "form", params: params)
    }

    /**
     * Acción que guarda los datos ingresados por el usuario en el dominio persona
     * @param id es el identificador de la persona en caso de edición
     * @param params es un mapa con el nombre de los campos del dominio persona y los datos ingresados por el usuario
     */
    def save = {
        def title
        if (params.id) {
            title = g.message(code: "persona.edit", default: "Edit Persona")
            def personaInstance = Persona.get(params.id)
            if (personaInstance) {
                personaInstance.properties = params
                if (!personaInstance.hasErrors() && personaInstance.save(flush: true)) {
                    flash.message = "${message(code: 'default.updated.message', args: [message(code: 'persona.label', default: 'Persona'), personaInstance.id])}"
                    redirect(action: "show", id: personaInstance.id)
                }
                else {
                    render(view: "form", model: [personaInstance: personaInstance, title: title, source: "edit"])
                }
            }
            else {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'persona.label', default: 'Persona'), params.id])}"
                redirect(action: "list")
            }
        } else {
            title = g.message(code: "persona.create", default: "Create Persona")
            def personaInstance = new Persona(params)
            if (personaInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.created.message', args: [message(code: 'persona.label', default: 'Persona'), personaInstance.id])}"
                redirect(action: "show", id: personaInstance.id)
            }
            else {
                render(view: "form", model: [personaInstance: personaInstance, title: title, source: "create"])
            }
        }
    }

    /**
     * Acción que actualiza los campos de una persona
     * @param id es el identificador de la persona
     * @param params es un mapa con el nombre de los campos del dominio persona y los datos ingresados por el usuario
     */
    def update = {
        def personaInstance = Persona.get(params.id)
        if (personaInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (personaInstance.version > version) {

                    personaInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'persona.label', default: 'Persona')] as Object[], "Another user has updated this Persona while you were editing")
                    render(view: "edit", model: [personaInstance: personaInstance])
                    return
                }
            }
            personaInstance.properties = params
            if (!personaInstance.hasErrors() && personaInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'persona.label', default: 'Persona'), personaInstance.id])}"
                redirect(action: "show", id: personaInstance.id)
            }
            else {
                render(view: "edit", model: [personaInstance: personaInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'persona.label', default: 'Persona'), params.id])}"
            redirect(action: "list")
        }
    }

    /**
     * Acción que muestra los datos de una persona
     * @param id es el identificador de la persona
     */
    def show = {
        def personaInstance = Persona.get(params.id)
        if (!personaInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'persona.label', default: 'Persona'), params.id])}"
            redirect(action: "list")
        }
        else {

            def title = g.message(code: "persona.show", default: "Show Persona")

            [personaInstance: personaInstance, title: title]
        }
    }

    /**
     * Acción que redirecciona a form
     */
    def edit = {
        params.source = "edit"
        redirect(action: "form", params: params)
    }

    /**
     * Acción que borra una persona
     * @param id es el identificador de la persona
     */
    def delete = {
        def personaInstance = Persona.get(params.id)
        if (personaInstance) {
            try {
                personaInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'persona.label', default: 'Persona'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'persona.label', default: 'Persona'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'persona.label', default: 'Persona'), params.id])}"
            redirect(action: "list")
        }
    }
}
