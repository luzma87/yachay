package yachay.seguridad

/**
 * Controlador que muestra las pantallas de manejo de usuarios
 */
class UsroController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST", delete: "GET"]

    /**
     * Acción que redirecciona a list
     */
    def index = {
        redirect(action: "list", params: params)
    }

    /**
     * Acción que muestra una lista de los usuarios registrados en el sistema
     */
    def list = {
        def title = g.message(code:"usro.list", default:"Usro List")
//        <g:message code="default.list.label" args="[entityName]" />

        params.max = Math.min(params.max ? params.int('max') : 10, 100)

        [usroInstanceList: Usro.list(params), usroInstanceTotal: Usro.count(), title: title, params:params]
    }

    /**
     * Acción que muestra un formulario para la inserción o edición de usuarios
     */
    def form = {
        def title
        def usroInstance

        if(params.source == "create") {
            usroInstance = new Usro()
            usroInstance.properties = params
            title = g.message(code:"usro.create", default:"Create Usro")
        } else if(params.source == "edit") {
            usroInstance = Usro.get(params.id)
            if (!usroInstance) {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'usro.label', default: 'Usro'), params.id])}"
                redirect(action: "list")
            }
            title = g.message(code:"usro.edit", default:"Edit Usro")
        }

        return [usroInstance: usroInstance, title:title, source: params.source]
    }

    /**
     * Acción que redireciona a form
     */
    def create = {
        params.source = "create"
        redirect(action:"form", params:params)
    }

    /**
     * Acción que guarda los datos de un usuario
     * @param id es el identificador del usuario en caso de edición
     * @param params es un mapa con los nombres de los campos del dominio usuario y los valores ingresados por el usuario
     */
    def save = {
        def title
        if(params.usroPassword && params.usroPassword!="" && params.usroPassword!=" ")
            params.usroPassword = params.usroPassword.trim().encodeAsMD5()
        if(params.autorizacion && params.autorizacion!="" && params.autorizacion!=" ")
            params.autorizacion = params.autorizacion.trim().encodeAsMD5()
        if(params.id) {
            title = g.message(code:"usro.edit", default:"Edit Usro")
            def usroInstance = Usro.get(params.id)
            if (usroInstance) {
                usroInstance.properties = params
                if (!usroInstance.hasErrors() && usroInstance.save(flush: true)) {
                    flash.message = "${message(code: 'default.updated.message', args: [message(code: 'usro.label', default: 'Usro'), usroInstance.id])}"
                    redirect(action: "show", id: usroInstance.id)
                }
                else {
                    render(view: "form", model: [usroInstance: usroInstance, title:title, source: "edit"])
                }
            }
            else {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'usro.label', default: 'Usro'), params.id])}"
                redirect(action: "list")
            }
        } else {
            title = g.message(code:"usro.create", default:"Create Usro")
            def usroInstance = new Usro(params)
            if (usroInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.created.message', args: [message(code: 'usro.label', default: 'Usro'), usroInstance.id])}"
                redirect(action: "show", id: usroInstance.id)
            }
            else {
                render(view: "form", model: [usroInstance: usroInstance, title:title, source: "create"])
            }
        }
    }

    /**
     * Acción para actualizar los datos del usuario
     * @param id es el identificador del usuario
     * @param params es un mapa con los nombres de los campos del dominio usuario y los valores ingresados por el usuario
     */
    def update = {
        def usroInstance = Usro.get(params.id)
        if (usroInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (usroInstance.version > version) {

                    usroInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'usro.label', default: 'Usro')] as Object[], "Another user has updated this Usro while you were editing")
                    render(view: "edit", model: [usroInstance: usroInstance])
                    return
                }
            }
            usroInstance.properties = params
            if (!usroInstance.hasErrors() && usroInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'usro.label', default: 'Usro'), usroInstance.id])}"
                redirect(action: "show", id: usroInstance.id)
            }
            else {
                render(view: "edit", model: [usroInstance: usroInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'usro.label', default: 'Usro'), params.id])}"
            redirect(action: "list")
        }
    }

    /**
     * Acción que muestra los datos de un usuario
     * @param id es el identificador del usuario
     */
    def show = {
        def usroInstance = Usro.get(params.id)
        if (!usroInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'usro.label', default: 'Usro'), params.id])}"
            redirect(action: "list")
        }
        else {

            def title = g.message(code:"usro.show", default:"Show Usro")

            [usroInstance: usroInstance, title: title]
        }
    }

    /**
     * Acción que redirecciona a form
     */
    def edit = {
        params.source = "edit"
        redirect(action:"form", params:params)
    }

    /**
     * Acción que borra un usuario
     * @param id es el identificador del usuario
     */
    def delete = {
        def usroInstance = Usro.get(params.id)
        if (usroInstance) {
            try {
                usroInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'usro.label', default: 'Usro'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'usro.label', default: 'Usro'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'usro.label', default: 'Usro'), params.id])}"
            redirect(action: "list")
        }
    }
}
