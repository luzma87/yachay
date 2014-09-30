package yachay.parametros

/**
 * Controlador que muestra las pantallas de manejo de cargos de personal
 */
class CargoPersonalController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST", delete: "GET"]

    /**
     * Acción que redirecciona a la acción List
     */
    def index = {
        redirect(action: "list", params: params)
    }

    /*Lista de cargos*/
    /**
     * Acción que muestra la lista de cargos
     */
    def list = {
        def title = g.message(code: "cargopersonal.list", default: "CargoPersonal List")
//        <g:message code="default.list.label" args="[entityName]" />

        params.max = Math.min(params.max ? params.int('max') : 10, 100)

        [cargoPersonalInstanceList: CargoPersonal.list(params), cargoPersonalInstanceTotal: CargoPersonal.count(), title: title, params: params]
    }

    /*Form para crear un nuevo cargo de personal*/
    /**
     * Acción que muestra el formulario de creación y edición de cargo
     */
    def form = {
        def title
        def cargoPersonalInstance

        if (params.source == "create") {
            cargoPersonalInstance = new CargoPersonal()
            cargoPersonalInstance.properties = params
            title = g.message(code: "cargopersonal.create", default: "Create CargoPersonal")
        } else if (params.source == "edit") {
            cargoPersonalInstance = CargoPersonal.get(params.id)
            if (!cargoPersonalInstance) {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'cargoPersonal.label', default: 'CargoPersonal'), params.id])}"
                redirect(action: "list")
            }
            title = g.message(code: "cargopersonal.edit", default: "Edit CargoPersonal")
        }

        return [cargoPersonalInstance: cargoPersonalInstance, title: title, source: params.source]
    }

    /**
     * Acción que redirecciona al formulario de creación (acción Form)
     */
    def create = {
        params.source = "create"
        redirect(action: "form", params: params)
    }

    /*Función para guardar los datos de un nuevo cargo de personal*/
    /**
     * Acción que guarda un cargo. Si guarda correctamente redirecciona a la acción Show, caso contrario a la acción Form y
     * muestra un mensaje
     * @params los parámetros enviados por el formulario
     */
    def save = {
        def title
        if (params.id) {
            title = g.message(code: "cargopersonal.edit", default: "Edit CargoPersonal")
            def cargoPersonalInstance = CargoPersonal.get(params.id)
            if (cargoPersonalInstance) {
                cargoPersonalInstance.properties = params
                if (!cargoPersonalInstance.hasErrors() && cargoPersonalInstance.save(flush: true)) {
                    flash.message = "${message(code: 'default.updated.message', args: [message(code: 'cargoPersonal.label', default: 'CargoPersonal'), cargoPersonalInstance.id])}"
                    redirect(action: "show", id: cargoPersonalInstance.id)
                } else {
                    render(view: "form", model: [cargoPersonalInstance: cargoPersonalInstance, title: title, source: "edit"])
                }
            } else {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'cargoPersonal.label', default: 'CargoPersonal'), params.id])}"
                redirect(action: "list")
            }
        } else {
            title = g.message(code: "cargopersonal.create", default: "Create CargoPersonal")
            def cargoPersonalInstance = new CargoPersonal(params)
            if (cargoPersonalInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.created.message', args: [message(code: 'cargoPersonal.label', default: 'CargoPersonal'), cargoPersonalInstance.id])}"
                redirect(action: "show", id: cargoPersonalInstance.id)
            } else {
                render(view: "form", model: [cargoPersonalInstance: cargoPersonalInstance, title: title, source: "create"])
            }
        }
    }

    /*Función para actualizar los datos de un cargo*/
    /**
     * Acción que guarda un cargo. Si guarda correctamente redirecciona a la acción Show, caso contrario a la acción Form y
     * muestra un mensaje
     * @params los parámetros enviados por el formulario
     */
    def update = {
        def cargoPersonalInstance = CargoPersonal.get(params.id)
        if (cargoPersonalInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (cargoPersonalInstance.version > version) {

                    cargoPersonalInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'cargoPersonal.label', default: 'CargoPersonal')] as Object[], "Another user has updated this CargoPersonal while you were editing")
                    render(view: "edit", model: [cargoPersonalInstance: cargoPersonalInstance])
                    return
                }
            }
            cargoPersonalInstance.properties = params
            if (!cargoPersonalInstance.hasErrors() && cargoPersonalInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'cargoPersonal.label', default: 'CargoPersonal'), cargoPersonalInstance.id])}"
                redirect(action: "show", id: cargoPersonalInstance.id)
            } else {
                render(view: "edit", model: [cargoPersonalInstance: cargoPersonalInstance])
            }
        } else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'cargoPersonal.label', default: 'CargoPersonal'), params.id])}"
            redirect(action: "list")
        }
    }

    /*Muestra los datos de un cargo específico*/
    /**
     * Acción que muestra los datos de un cargo
     */
    def show = {
        def cargoPersonalInstance = CargoPersonal.get(params.id)
        if (!cargoPersonalInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'cargoPersonal.label', default: 'CargoPersonal'), params.id])}"
            redirect(action: "list")
        } else {

            def title = g.message(code: "cargopersonal.show", default: "Show CargoPersonal")

            [cargoPersonalInstance: cargoPersonalInstance, title: title]
        }
    }

    /**
     * Acción que redirecciona al formulario de edición (acción Form)
     */
    def edit = {
        params.source = "edit"
        redirect(action: "form", params: params)
    }

    /*Función para borrar un cargo*/
    /**
     * Acción que permite eliminar un cargo y redirecciona a la acción List
     * @param id id del elemento a ser eliminado
     */
    def delete = {
        def cargoPersonalInstance = CargoPersonal.get(params.id)
        if (cargoPersonalInstance) {
            try {
                cargoPersonalInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'cargoPersonal.label', default: 'CargoPersonal'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'cargoPersonal.label', default: 'CargoPersonal'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        } else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'cargoPersonal.label', default: 'CargoPersonal'), params.id])}"
            redirect(action: "list")
        }
    }
}
