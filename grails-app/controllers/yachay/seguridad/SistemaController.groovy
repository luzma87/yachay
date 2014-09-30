package yachay.seguridad

/**
 * Controlador que muestra las pantallas para el manejo del dominio sistema
 */
class SistemaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST", delete: "GET"]

    /**
     * Acción que redirecciona a lista
     */
    def index = {
        redirect(action: "list", params: params)
    }

    /**
     * Acción que muestra una lista con los sistemas registrados
     */
    def list = {
        def title = g.message(code: "sistema.list", default: "Sistema List")
//        <g:message code="default.list.label" args="[entityName]" />

        params.max = Math.min(params.max ? params.int('max') : 10, 100)

        [sistemaInstanceList: Sistema.list(params), sistemaInstanceTotal: Sistema.count(), title: title, params: params]
    }

    /**
     * Acción que muestra un formulario para la inserción y edición de un sistema
     */
    def form = {
        def title
        def sistemaInstance

        if (params.source == "create") {
            sistemaInstance = new Sistema()
            sistemaInstance.properties = params
            title = g.message(code: "sistema.create", default: "Create Sistema")
        } else if (params.source == "edit") {
            sistemaInstance = Sistema.get(params.id)
            if (!sistemaInstance) {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'sistema.label', default: 'Sistema'), params.id])}"
                redirect(action: "list")
            }
            title = g.message(code: "sistema.edit", default: "Edit Sistema")
        }

        return [sistemaInstance: sistemaInstance, title: title, source: params.source]
    }

    /**
     * Acción que redirecciona a form
     */
    def create = {
        params.source = "create"
        redirect(action: "form", params: params)
    }

    /**
     * Acción que guarda los datos de un sistema
     * @param id es el identificador del sistema en caso de edición
     * @param params es un mapa con los nombre de los campos del dominio sistema y los valores ingresados por el usuario
     */
    def save = {
        def title
        if (params.id) {
            title = g.message(code: "sistema.edit", default: "Edit Sistema")
            def sistemaInstance = Sistema.get(params.id)
            if (sistemaInstance) {
                sistemaInstance.properties = params
                if (!sistemaInstance.hasErrors() && sistemaInstance.save(flush: true)) {
                    flash.message = "${message(code: 'default.updated.message', args: [message(code: 'sistema.label', default: 'Sistema'), sistemaInstance.id])}"
                    redirect(action: "show", id: sistemaInstance.id)
                }
                else {
                    render(view: "form", model: [sistemaInstance: sistemaInstance, title: title, source: "edit"])
                }
            }
            else {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'sistema.label', default: 'Sistema'), params.id])}"
                redirect(action: "list")
            }
        } else {
            title = g.message(code: "sistema.create", default: "Create Sistema")
            def sistemaInstance = new Sistema(params)
            if (sistemaInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.created.message', args: [message(code: 'sistema.label', default: 'Sistema'), sistemaInstance.id])}"
                redirect(action: "show", id: sistemaInstance.id)
            }
            else {
                render(view: "form", model: [sistemaInstance: sistemaInstance, title: title, source: "create"])
            }
        }
    }

    /**
     * Acción que actualiza los datos de un sistema
     * @param id es el identificador del sistema
     * @param params es un mapa con los nombre de los campos del dominio sistema y los valores ingresados por el usuario
     */
    def update = {
        def sistemaInstance = Sistema.get(params.id)
        if (sistemaInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (sistemaInstance.version > version) {

                    sistemaInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'sistema.label', default: 'Sistema')] as Object[], "Another user has updated this Sistema while you were editing")
                    render(view: "edit", model: [sistemaInstance: sistemaInstance])
                    return
                }
            }
            sistemaInstance.properties = params
            if (!sistemaInstance.hasErrors() && sistemaInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'sistema.label', default: 'Sistema'), sistemaInstance.id])}"
                redirect(action: "show", id: sistemaInstance.id)
            }
            else {
                render(view: "edit", model: [sistemaInstance: sistemaInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'sistema.label', default: 'Sistema'), params.id])}"
            redirect(action: "list")
        }
    }

    /**
     * Acción que muestra los datos un sistema
     * @param es el identificador del sistema
     */
    def show = {
        def sistemaInstance = Sistema.get(params.id)
        if (!sistemaInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'sistema.label', default: 'Sistema'), params.id])}"
            redirect(action: "list")
        }
        else {

            def title = g.message(code: "sistema.show", default: "Show Sistema")

            [sistemaInstance: sistemaInstance, title: title]
        }
    }

    /**
     * Acción que redireciona a form
     */
    def edit = {
        params.source = "edit"
        redirect(action: "form", params: params)
    }

    /**
     * Acción que borra un sistema
     * @param id es el identificador del sistema
     */
    def delete = {
        def sistemaInstance = Sistema.get(params.id)
        if (sistemaInstance) {
            try {
                sistemaInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'sistema.label', default: 'Sistema'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'sistema.label', default: 'Sistema'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'sistema.label', default: 'Sistema'), params.id])}"
            redirect(action: "list")
        }
    }
}
