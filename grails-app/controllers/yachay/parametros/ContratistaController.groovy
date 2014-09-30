package yachay.parametros

/**
 * Controlador que muestra las pantallas de manejo de contratistas
 */
class ContratistaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST", delete: "GET"]

    /**
     * Acción que redirecciona a la acción List
     */
    def index = {
        redirect(action: "list", params: params)
    }

    /**
     * Acción que muestra la lista de contratistas
     */
    def list = {
        def title = g.message(code: "contratista.list", default: "Contratista List")
//        <g:message code="default.list.label" args="[entityName]" />

        params.max = Math.min(params.max ? params.int('max') : 10, 100)

        [contratistaInstanceList: Contratista.list(params), contratistaInstanceTotal: Contratista.count(), title: title, params: params]
    }

    /**
     * Acción que muestra el formulario de creación y edición de contratista
     */
    def form = {
        def title
        def contratistaInstance

        if (params.source == "create") {
            contratistaInstance = new Contratista()
            contratistaInstance.properties = params
            title = g.message(code: "contratista.create", default: "Create Contratista")
        } else if (params.source == "edit") {
            contratistaInstance = Contratista.get(params.id)
            if (!contratistaInstance) {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'contratista.label', default: 'Contratista'), params.id])}"
                redirect(action: "list")
            }
            title = g.message(code: "contratista.edit", default: "Edit Contratista")
        }

        return [contratistaInstance: contratistaInstance, title: title, source: params.source]
    }

    /**
     * Acción que redirecciona al formulario de creación (acción Form)
     */
    def create = {
        params.source = "create"
        redirect(action: "form", params: params)
    }

    /**
     * Acción que guarda un contratista. Si guarda correctamente redirecciona a la acción Show, caso contrario a la acción Form y
     * muestra un mensaje
     * @params los parámetros enviados por el formulario
     */
    def save = {
        def title
        if (params.id) {
            title = g.message(code: "contratista.edit", default: "Edit Contratista")
            def contratistaInstance = Contratista.get(params.id)
            if (contratistaInstance) {
                contratistaInstance.properties = params
                if (!contratistaInstance.hasErrors() && contratistaInstance.save(flush: true)) {
                    flash.message = "${message(code: 'default.updated.message', args: [message(code: 'contratista.label', default: 'Contratista'), contratistaInstance.id])}"
                    redirect(action: "show", id: contratistaInstance.id)
                }
                else {
                    render(view: "form", model: [contratistaInstance: contratistaInstance, title: title, source: "edit"])
                }
            }
            else {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'contratista.label', default: 'Contratista'), params.id])}"
                redirect(action: "list")
            }
        } else {
            title = g.message(code: "contratista.create", default: "Create Contratista")
            def contratistaInstance = new Contratista(params)
            if (contratistaInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.created.message', args: [message(code: 'contratista.label', default: 'Contratista'), contratistaInstance.id])}"
                redirect(action: "show", id: contratistaInstance.id)
            }
            else {
                render(view: "form", model: [contratistaInstance: contratistaInstance, title: title, source: "create"])
            }
        }
    }

    /**
     * Acción que guarda un contratista. Si guarda correctamente redirecciona a la acción Show, caso contrario a la acción Form y
     * muestra un mensaje
     * @params los parámetros enviados por el formulario
     */
    def update = {
        def contratistaInstance = Contratista.get(params.id)
        if (contratistaInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (contratistaInstance.version > version) {

                    contratistaInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'contratista.label', default: 'Contratista')] as Object[], "Another user has updated this Contratista while you were editing")
                    render(view: "edit", model: [contratistaInstance: contratistaInstance])
                    return
                }
            }
            contratistaInstance.properties = params
            if (!contratistaInstance.hasErrors() && contratistaInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'contratista.label', default: 'Contratista'), contratistaInstance.id])}"
                redirect(action: "show", id: contratistaInstance.id)
            }
            else {
                render(view: "edit", model: [contratistaInstance: contratistaInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'contratista.label', default: 'Contratista'), params.id])}"
            redirect(action: "list")
        }
    }

    /**
     * Acción que muestra los datos de un contratista
     */
    def show = {
        def contratistaInstance = Contratista.get(params.id)
        if (!contratistaInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'contratista.label', default: 'Contratista'), params.id])}"
            redirect(action: "list")
        }
        else {

            def title = g.message(code: "contratista.show", default: "Show Contratista")

            [contratistaInstance: contratistaInstance, title: title]
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
     * Acción que permite eliminar un contratista y redirecciona a la acción List
     * @param id id del elemento a ser eliminado
     */
    def delete = {
        def contratistaInstance = Contratista.get(params.id)
        if (contratistaInstance) {
            try {
                contratistaInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'contratista.label', default: 'Contratista'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'contratista.label', default: 'Contratista'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'contratista.label', default: 'Contratista'), params.id])}"
            redirect(action: "list")
        }
    }
}
