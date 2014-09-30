package yachay.parametros

/**
 * Controlador que muestra las pantallas de manejo de etapas
 */
class EtapaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST", delete: "GET"]

    /**
     * Acción que redirecciona a la acción List
     */
    def index = {
        redirect(action: "list", params: params)
    }

    /**
     * Acción que muestra la lista de estapas
     */
    def list = {
        def title = g.message(code: "default.list.label", args: ["Etapa"], default: "Etapa List")
//        <g:message code="default.list.label" args="[entityName]" />

        params.max = Math.min(params.max ? params.int('max') : 10, 100)

        [etapaInstanceList: Etapa.list(params), etapaInstanceTotal: Etapa.count(), title: title, params: params]
    }

    /**
     * Acción que muestra el formulario de creación y edición de etapa
     */
    def form = {
        def title
        def etapaInstance

        if (params.source == "create") {
            etapaInstance = new Etapa()
            etapaInstance.properties = params
            title = "Crear Etapa de proyecto"
        } else if (params.source == "edit") {
            etapaInstance = Etapa.get(params.id)
            if (!etapaInstance) {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'etapa.label', default: 'Etapa'), params.id])}"
                redirect(action: "list")
            }
            title = "Editar Etapa de proyecto"
        }

        return [etapaInstance: etapaInstance, title: title, source: params.source]
    }

    /**
     * Acción que redirecciona al formulario de creación (acción Form)
     */
    def create = {
        params.source = "create"
        redirect(action: "form", params: params)
    }

    /**
     * Acción que guarda una etapa. Si guarda correctamente redirecciona a la acción Show, caso contrario a la acción Form y
     * muestra un mensaje
     * @params los parámetros enviados por el formulario
     */
    def save = {
        def title
        if (params.id) {
            title = g.message(code: "default.edit.label", args: ["Etapa"], default: "Edit Etapa")
            def etapaInstance = Etapa.get(params.id)
            if (etapaInstance) {
                etapaInstance.properties = params
                if (!etapaInstance.hasErrors() && etapaInstance.save(flush: true)) {
                    flash.message = "${message(code: 'default.updated.message', args: [message(code: 'etapa.label', default: 'Etapa'), etapaInstance.id])}"
                    redirect(action: "show", id: etapaInstance.id)
                } else {
                    render(view: "form", model: [etapaInstance: etapaInstance, title: title, source: "edit"])
                }
            } else {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'etapa.label', default: 'Etapa'), params.id])}"
                redirect(action: "list")
            }
        } else {
            title = g.message(code: "default.create.label", args: ["Etapa"], default: "Create Etapa")
            def etapaInstance = new Etapa(params)
            if (etapaInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.created.message', args: [message(code: 'etapa.label', default: 'Etapa'), etapaInstance.id])}"
                redirect(action: "show", id: etapaInstance.id)
            } else {
                render(view: "form", model: [etapaInstance: etapaInstance, title: title, source: "create"])
            }
        }
    }

    /**
     * Acción que guarda una etapa. Si guarda correctamente redirecciona a la acción Show, caso contrario a la acción Form y
     * muestra un mensaje
     * @params los parámetros enviados por el formulario
     */
    def update = {
        def etapaInstance = Etapa.get(params.id)
        if (etapaInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (etapaInstance.version > version) {

                    etapaInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'etapa.label', default: 'Etapa')] as Object[], "Another user has updated this Etapa while you were editing")
                    render(view: "edit", model: [etapaInstance: etapaInstance])
                    return
                }
            }
            etapaInstance.properties = params
            if (!etapaInstance.hasErrors() && etapaInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'etapa.label', default: 'Etapa'), etapaInstance.id])}"
                redirect(action: "show", id: etapaInstance.id)
            } else {
                render(view: "edit", model: [etapaInstance: etapaInstance])
            }
        } else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'etapa.label', default: 'Etapa'), params.id])}"
            redirect(action: "list")
        }
    }

    /**
     * Acción que muestra los datos de una etapa
     */
    def show = {
        def etapaInstance = Etapa.get(params.id)
        if (!etapaInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'etapa.label', default: 'Etapa'), params.id])}"
            redirect(action: "list")
        } else {

            def title = g.message(code: "default.show.label", args: ["Etapa"], default: "Show Etapa")

            [etapaInstance: etapaInstance, title: title]
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
     * Acción que permite eliminar una etapa y redirecciona a la acción List
     * @param id id del elemento a ser eliminado
     */
    def delete = {
        def etapaInstance = Etapa.get(params.id)
        if (etapaInstance) {
            try {
                etapaInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'etapa.label', default: 'Etapa'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'etapa.label', default: 'Etapa'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        } else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'etapa.label', default: 'Etapa'), params.id])}"
            redirect(action: "list")
        }
    }
}
