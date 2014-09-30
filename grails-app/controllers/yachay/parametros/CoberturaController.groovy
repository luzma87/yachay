package yachay.parametros

/**
 * Controlador que muestra las pantallas de manejo de coberturas
 */
class CoberturaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST", delete: "GET"]

    /**
     * Acción que redirecciona a la acción List
     */
    def index = {
        redirect(action: "list", params: params)
    }

    /**
     * Acción que muestra la lista de coberturas
     */
    def list = {
        def title = g.message(code: "default.list.label", args: ["Cobertura"], default: "Cobertura List")
//        <g:message code="default.list.label" args="[entityName]" />

        params.max = Math.min(params.max ? params.int('max') : 25, 100)

        [coberturaInstanceList: Cobertura.list(params), coberturaInstanceTotal: Cobertura.count(), title: title, params: params]
    }

    /**
     * Acción que muestra el formulario de creación y edición de cobertura
     */
    def form = {
        def title
        def coberturaInstance

        if (params.source == "create") {
            coberturaInstance = new Cobertura()
            coberturaInstance.properties = params
            title = "Crear Cobertura de proyecto"
        } else if (params.source == "edit") {
            coberturaInstance = Cobertura.get(params.id)
            if (!coberturaInstance) {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'cobertura.label', default: 'Cobertura'), params.id])}"
                redirect(action: "list")
            }
            title = "Editar Cobertura de proyecto"
        }

        return [coberturaInstance: coberturaInstance, title: title, source: params.source]
    }

    /**
     * Acción que redirecciona al formulario de creación (acción Form)
     */
    def create = {
        params.source = "create"
        redirect(action: "form", params: params)
    }

    /**
     * Acción que guarda una cobertura. Si guarda correctamente redirecciona a la acción Show, caso contrario a la acción Form y
     * muestra un mensaje
     * @params los parámetros enviados por el formulario
     */
    def save = {
        def title
        if (params.id) {
            title = g.message(code: "default.edit.label", args: ["Cobertura"], default: "Edit Cobertura")
            def coberturaInstance = Cobertura.get(params.id)
            if (coberturaInstance) {
                coberturaInstance.properties = params
                if (!coberturaInstance.hasErrors() && coberturaInstance.save(flush: true)) {
                    flash.message = "${message(code: 'default.updated.message', args: [message(code: 'cobertura.label', default: 'Cobertura'), coberturaInstance.id])}"
                    redirect(action: "show", id: coberturaInstance.id)
                }
                else {
                    render(view: "form", model: [coberturaInstance: coberturaInstance, title: title, source: "edit"])
                }
            }
            else {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'cobertura.label', default: 'Cobertura'), params.id])}"
                redirect(action: "list")
            }
        } else {
            title = g.message(code: "default.create.label", args: ["Cobertura"], default: "Create Cobertura")
            def coberturaInstance = new Cobertura(params)
            if (coberturaInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.created.message', args: [message(code: 'cobertura.label', default: 'Cobertura'), coberturaInstance.id])}"
                redirect(action: "show", id: coberturaInstance.id)
            }
            else {
                render(view: "form", model: [coberturaInstance: coberturaInstance, title: title, source: "create"])
            }
        }
    }

    /**
     * Acción que guarda una cobertura. Si guarda correctamente redirecciona a la acción Show, caso contrario a la acción Form y
     * muestra un mensaje
     * @params los parámetros enviados por el formulario
     */
    def update = {
        def coberturaInstance = Cobertura.get(params.id)
        if (coberturaInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (coberturaInstance.version > version) {

                    coberturaInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'cobertura.label', default: 'Cobertura')] as Object[], "Another user has updated this Cobertura while you were editing")
                    render(view: "edit", model: [coberturaInstance: coberturaInstance])
                    return
                }
            }
            coberturaInstance.properties = params
            if (!coberturaInstance.hasErrors() && coberturaInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'cobertura.label', default: 'Cobertura'), coberturaInstance.id])}"
                redirect(action: "show", id: coberturaInstance.id)
            }
            else {
                render(view: "edit", model: [coberturaInstance: coberturaInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'cobertura.label', default: 'Cobertura'), params.id])}"
            redirect(action: "list")
        }
    }

    /**
     * Acción que muestra los datos de una cobertura
     */
    def show = {
        def coberturaInstance = Cobertura.get(params.id)
        if (!coberturaInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'cobertura.label', default: 'Cobertura'), params.id])}"
            redirect(action: "list")
        }
        else {

            def title = g.message(code: "default.show.label", args: ["Cobertura"], default: "Show Cobertura")

            [coberturaInstance: coberturaInstance, title: title]
        }
    }

    /**
     * Acción que redirecciona al formulario de edición (acción Form)
     */
    def edit = {
        params.source = "edit"
        redirect(action: "form", params: params)
    }

    /**
     * Acción que permite eliminar una cobertura y redirecciona a la acción List
     * @param id id del elemento a ser eliminado
     */
    def delete = {
        def coberturaInstance = Cobertura.get(params.id)
        if (coberturaInstance) {
            try {
                coberturaInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'cobertura.label', default: 'Cobertura'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'cobertura.label', default: 'Cobertura'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'cobertura.label', default: 'Cobertura'), params.id])}"
            redirect(action: "list")
        }
    }
}
