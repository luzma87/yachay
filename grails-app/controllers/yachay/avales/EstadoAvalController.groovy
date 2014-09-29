package yachay.avales

import yachay.avales.EstadoAval

/**
 * Controlador que muestra las pantallas de manejo de estados de aval
 */
class EstadoAvalController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST", delete: "GET"]

    /**
     * Acción que redirecciona a la lista de estados de aval
     */
    def index = {
        redirect(action: "list", params: params)
    }

    /**
     * Acción que muestra una lista de los estados de aval disponibles
     */
    def list = {
        def title = g.message(code: "estadoaval.list", default: "EstadoAval List")
//        <g:message code="default.list.label" args="[entityName]" />

        params.max = Math.min(params.max ? params.int('max') : 10, 100)

        [estadoAvalInstanceList: EstadoAval.list(params), estadoAvalInstanceTotal: EstadoAval.count(), title: title, params: params]
    }

    /**
     * Acción que muestra un formulario para crear un nuevo estado o modificar uno existente<br/>
     * Si es edición y no se encuentra un estado con el id enviado, redirecciona a la lista y muestra un mensaje de error
     * @param id el id del estado de aval a editar
     */
    def form = {
        def title
        def estadoAvalInstance

        if (params.source == "create") {
            estadoAvalInstance = new EstadoAval()
            estadoAvalInstance.properties = params
            title = g.message(code: "estadoaval.create", default: "Create EstadoAval")
        } else if (params.source == "edit") {
            estadoAvalInstance = EstadoAval.get(params.id)
            if (!estadoAvalInstance) {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'estadoAval.label', default: 'EstadoAval'), params.id])}"
                redirect(action: "list")
            }
            title = g.message(code: "estadoaval.edit", default: "Edit EstadoAval")
        }

        return [estadoAvalInstance: estadoAvalInstance, title: title, source: params.source]
    }

    /**
     * Acción que redirecciona al formulario de creación (acción Form)
     */
    def create = {
        params.source = "create"
        redirect(action: "form", params: params)
    }

    /**
     * Acción que guarda los datos ingresados en el formulario<br/>
     * Si el estado de aval se guarda correctamente se redirecciona a la acción Show, si se encuentra un error en los datos
     * se redirecciona a la acción Form, si el id que recibe no corresponde a ningún tipo de aval, redirecciona a la acción List
     * @params los parámetros enviados por el submit del formulario
     */
    def save = {
        def title
        if (params.id) {
            title = g.message(code: "estadoaval.edit", default: "Edit EstadoAval")
            def estadoAvalInstance = EstadoAval.get(params.id)
            if (estadoAvalInstance) {
                estadoAvalInstance.properties = params
                if (!estadoAvalInstance.hasErrors() && estadoAvalInstance.save(flush: true)) {
                    flash.message = "${message(code: 'default.updated.message', args: [message(code: 'estadoAval.label', default: 'EstadoAval'), estadoAvalInstance.id])}"
                    redirect(action: "show", id: estadoAvalInstance.id)
                } else {
                    render(view: "form", model: [estadoAvalInstance: estadoAvalInstance, title: title, source: "edit"])
                }
            } else {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'estadoAval.label', default: 'EstadoAval'), params.id])}"
                redirect(action: "list")
            }
        } else {
            title = g.message(code: "estadoaval.create", default: "Create EstadoAval")
            def estadoAvalInstance = new EstadoAval(params)
            if (estadoAvalInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.created.message', args: [message(code: 'estadoAval.label', default: 'EstadoAval'), estadoAvalInstance.id])}"
                redirect(action: "show", id: estadoAvalInstance.id)
            } else {
                render(view: "form", model: [estadoAvalInstance: estadoAvalInstance, title: title, source: "create"])
            }
        }
    }

    /**
     * Acción que guarda los datos ingresados en el formulario<br/>
     * Si el estado de aval se guarda correctamente se redirecciona a la acción Show, si se encuentra un error en los datos
     * se redirecciona a la acción Form, si el id que recibe no corresponde a ningún tipo de aval, redirecciona a la acción List
     * @params los parámetros enviados por el submit del formulario
     */
    def update = {
        def estadoAvalInstance = EstadoAval.get(params.id)
        if (estadoAvalInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (estadoAvalInstance.version > version) {

                    estadoAvalInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'estadoAval.label', default: 'EstadoAval')] as Object[], "Another user has updated this EstadoAval while you were editing")
                    render(view: "edit", model: [estadoAvalInstance: estadoAvalInstance])
                    return
                }
            }
            estadoAvalInstance.properties = params
            if (!estadoAvalInstance.hasErrors() && estadoAvalInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'estadoAval.label', default: 'EstadoAval'), estadoAvalInstance.id])}"
                redirect(action: "show", id: estadoAvalInstance.id)
            } else {
                render(view: "edit", model: [estadoAvalInstance: estadoAvalInstance])
            }
        } else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'estadoAval.label', default: 'EstadoAval'), params.id])}"
            redirect(action: "list")
        }
    }

    /**
     * Acción que muestra los datos de un estado de aval en particular<br/>
     * Si el id enviado no corresponde a ningún estado de aval redirecciona a la acción List
     * @param id el id del estado aval
     */
    def show = {
        def estadoAvalInstance = EstadoAval.get(params.id)
        if (!estadoAvalInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'estadoAval.label', default: 'EstadoAval'), params.id])}"
            redirect(action: "list")
        } else {

            def title = g.message(code: "estadoaval.show", default: "Show EstadoAval")

            [estadoAvalInstance: estadoAvalInstance, title: title]
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
     * Acción que elimina un estado aval<br/>
     * Si la eliminación fue exitosa redirecciona a la acción List, caso contrario redirecciona a la acción Show.
     * @param id el id del estado aval a eliminar
     */
    def delete = {
        def estadoAvalInstance = EstadoAval.get(params.id)
        if (estadoAvalInstance) {
            try {
                estadoAvalInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'estadoAval.label', default: 'EstadoAval'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'estadoAval.label', default: 'EstadoAval'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        } else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'estadoAval.label', default: 'EstadoAval'), params.id])}"
            redirect(action: "list")
        }
    }
}
