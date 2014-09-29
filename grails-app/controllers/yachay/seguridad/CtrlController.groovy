package yachay.seguridad

/**
 * Controlador que muestra las pantallas para el manejo de controladores
 */
class CtrlController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST", delete: "GET"]

    /**
     * Acción que muestra una lista de todos los controladores registrados en  el sistema
     */
    def index = {
        redirect(action: "list", params: params)
    }

    /**
     * Acción que muestra una lista de todos los controladores registrados en  el sistema
     */
    def list = {
        def title = g.message(code: "ctrl.list", default: "Ctrl List")
//        <g:message code="default.list.label" args="[entityName]" />

        params.max = Math.min(params.max ? params.int('max') : 10, 100)

        [ctrlInstanceList: Ctrl.list(params), ctrlInstanceTotal: Ctrl.count(), title: title, params: params]
    }

    /**
     * Acción que muestra un formulario para el ingreso de un controlador
     */
    def form = {
        def title
        def ctrlInstance

        if (params.source == "create") {
            ctrlInstance = new Ctrl()
            ctrlInstance.properties = params
            title = g.message(code: "ctrl.create", default: "Create Ctrl")
        } else if (params.source == "edit") {
            ctrlInstance = Ctrl.get(params.id)
            if (!ctrlInstance) {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'ctrl.label', default: 'Ctrl'), params.id])}"
                redirect(action: "list")
            }
            title = g.message(code: "ctrl.edit", default: "Edit Ctrl")
        }

        return [ctrlInstance: ctrlInstance, title: title, source: params.source]
    }

    /**
     * Acción que redirecciona a form
     */
    def create = {
        params.source = "create"
        redirect(action: "form", params: params)
    }

    /**
     * Acción que guarda un controlador con los datos ingresados por el usuario en el formulario
     * @param id es el identificador del controlador en caso de tratarse de una edición
     * @param params es un mapa que contiene todos los atributos y los valores ingresados por el usuario en el formulario
     */
    def save = {
        def title
        if (params.id) {
            title = g.message(code: "ctrl.edit", default: "Edit Ctrl")
            def ctrlInstance = Ctrl.get(params.id)
            if (ctrlInstance) {
                ctrlInstance.properties = params
                if (!ctrlInstance.hasErrors() && ctrlInstance.save(flush: true)) {
                    flash.message = "${message(code: 'default.updated.message', args: [message(code: 'ctrl.label', default: 'Ctrl'), ctrlInstance.id])}"
                    redirect(action: "show", id: ctrlInstance.id)
                }
                else {
                    render(view: "form", model: [ctrlInstance: ctrlInstance, title: title, source: "edit"])
                }
            }
            else {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'ctrl.label', default: 'Ctrl'), params.id])}"
                redirect(action: "list")
            }
        } else {
            title = g.message(code: "ctrl.create", default: "Create Ctrl")
            def ctrlInstance = new Ctrl(params)
            if (ctrlInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.created.message', args: [message(code: 'ctrl.label', default: 'Ctrl'), ctrlInstance.id])}"
                redirect(action: "show", id: ctrlInstance.id)
            }
            else {
                render(view: "form", model: [ctrlInstance: ctrlInstance, title: title, source: "create"])
            }
        }
    }

    /**
     * Acción que muestra un formulario con la información de un controlador seleccionado para su edición
     * @param id es el identificador del controlador seleccionado
     */
    def update = {
        def ctrlInstance = Ctrl.get(params.id)
        if (ctrlInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (ctrlInstance.version > version) {

                    ctrlInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'ctrl.label', default: 'Ctrl')] as Object[], "Another user has updated this Ctrl while you were editing")
                    render(view: "edit", model: [ctrlInstance: ctrlInstance])
                    return
                }
            }
            ctrlInstance.properties = params
            if (!ctrlInstance.hasErrors() && ctrlInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'ctrl.label', default: 'Ctrl'), ctrlInstance.id])}"
                redirect(action: "show", id: ctrlInstance.id)
            }
            else {
                render(view: "edit", model: [ctrlInstance: ctrlInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'ctrl.label', default: 'Ctrl'), params.id])}"
            redirect(action: "list")
        }
    }

    /**
     * Acción que muestra los datos de un controlador seleccionado
     * @param id es el identificador del controlador
     */
    def show = {
        def ctrlInstance = Ctrl.get(params.id)
        if (!ctrlInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'ctrl.label', default: 'Ctrl'), params.id])}"
            redirect(action: "list")
        }
        else {

            def title = g.message(code: "ctrl.show", default: "Show Ctrl")

            [ctrlInstance: ctrlInstance, title: title]
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
     * Acción que borra un controlador seleccionado
     * @param id es el identificador del controlador
     */
    def delete = {
        def ctrlInstance = Ctrl.get(params.id)
        if (ctrlInstance) {
            try {
                ctrlInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'ctrl.label', default: 'Ctrl'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'ctrl.label', default: 'Ctrl'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'ctrl.label', default: 'Ctrl'), params.id])}"
            redirect(action: "list")
        }
    }
}
