package yachay.parametros

/**
 * Controlador que muestra las pantallas de manejo de sectores
 */
class SectorController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST", delete: "GET"]

    /**
     * Acción que redirecciona a la acción List
     */
    def index = {
        redirect(action: "list", params: params)
    }

    /**
     * Acción que muestra la lista de sectores
     */
    def list = {
        def title = g.message(code: "default.list.label", args: ["Sector"], default: "Sector List")
//        <g:message code="default.list.label" args="[entityName]" />

        params.max = Math.min(params.max ? params.int('max') : 25, 100)

        [sectorInstanceList: Sector.list(params), sectorInstanceTotal: Sector.count(), title: title, params: params]
    }

    /**
     * Acción que muestra el formulario de creación y edición de sector
     */
    def form = {
        def title
        def sectorInstance

        if (params.source == "create") {
            sectorInstance = new Sector()
            sectorInstance.properties = params
            title = "Crear Sector"
        } else if (params.source == "edit") {
            sectorInstance = Sector.get(params.id)
            if (!sectorInstance) {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'sector.label', default: 'Sector'), params.id])}"
                redirect(action: "list")
            }
            title = "Editar Sector"
        }

        return [sectorInstance: sectorInstance, title: title, source: params.source]
    }

    /**
     * Acción que redirecciona al formulario de creación (acción Form)
     */
    def create = {
        params.source = "create"
        redirect(action: "form", params: params)
    }

    /**
     * Acción que guarda un sector. Si guarda correctamente redirecciona a la acción Show, caso contrario a la acción Form y
     * muestra un mensaje
     * @params los parámetros enviados por el formulario
     */
    def save = {
        def title
        if (params.id) {
            title = g.message(code: "default.edit.label", args: ["Sector"], default: "Edit Sector")
            def sectorInstance = Sector.get(params.id)
            if (sectorInstance) {
                sectorInstance.properties = params
                if (!sectorInstance.hasErrors() && sectorInstance.save(flush: true)) {
                    flash.message = "${message(code: 'default.updated.message', args: [message(code: 'sector.label', default: 'Sector'), sectorInstance.id])}"
                    redirect(action: "show", id: sectorInstance.id)
                } else {
                    render(view: "form", model: [sectorInstance: sectorInstance, title: title, source: "edit"])
                }
            } else {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'sector.label', default: 'Sector'), params.id])}"
                redirect(action: "list")
            }
        } else {
            title = g.message(code: "default.create.label", args: ["Sector"], default: "Create Sector")
            def sectorInstance = new Sector(params)
            if (sectorInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.created.message', args: [message(code: 'sector.label', default: 'Sector'), sectorInstance.id])}"
                redirect(action: "show", id: sectorInstance.id)
            } else {
                render(view: "form", model: [sectorInstance: sectorInstance, title: title, source: "create"])
            }
        }
    }

    /**
     * Acción que guarda un sector. Si guarda correctamente redirecciona a la acción Show, caso contrario a la acción Form y
     * muestra un mensaje
     * @params los parámetros enviados por el formulario
     */
    def update = {
        def sectorInstance = Sector.get(params.id)
        if (sectorInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (sectorInstance.version > version) {

                    sectorInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'sector.label', default: 'Sector')] as Object[], "Another user has updated this Sector while you were editing")
                    render(view: "edit", model: [sectorInstance: sectorInstance])
                    return
                }
            }
            sectorInstance.properties = params
            if (!sectorInstance.hasErrors() && sectorInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'sector.label', default: 'Sector'), sectorInstance.id])}"
                redirect(action: "show", id: sectorInstance.id)
            } else {
                render(view: "edit", model: [sectorInstance: sectorInstance])
            }
        } else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'sector.label', default: 'Sector'), params.id])}"
            redirect(action: "list")
        }
    }

    /**
     * Acción que muestra los datos de un sector
     */
    def show = {
        def sectorInstance = Sector.get(params.id)
        if (!sectorInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'sector.label', default: 'Sector'), params.id])}"
            redirect(action: "list")
        } else {

            def title = g.message(code: "default.show.label", args: ["Sector"], default: "Show Sector")

            [sectorInstance: sectorInstance, title: title]
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
 * Acción que permite eliminar un sector y redirecciona a la acción List
 * @param id id del elemento a ser eliminado
 */
    def delete = {
        def sectorInstance = Sector.get(params.id)
        if (sectorInstance) {
            try {
                sectorInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'sector.label', default: 'Sector'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'sector.label', default: 'Sector'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        } else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'sector.label', default: 'Sector'), params.id])}"
            redirect(action: "list")
        }
    }
}
