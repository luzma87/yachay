package app

class ObjetivoUnidadController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST", delete: "GET"]

    def index = {
        redirect(action: "list", params: params)
    }

    /*Listado de los objetivos x Unidad*/
    def list = {
        def title = g.message(code: "objetivounidad.list", default: "ObjetivoUnidad List")
//        <g:message code="default.list.label" args="[entityName]" />

        params.max = Math.min(params.max ? params.int('max') : 10, 100)

        [objetivoUnidadInstanceList: ObjetivoUnidad.list(params), objetivoUnidadInstanceTotal: ObjetivoUnidad.count(), title: title, params: params]
    }

    /*Forma para crear nuevos objetivos*/
    def form = {
        def title
        def objetivoUnidadInstance

        if (params.source == "create") {
            objetivoUnidadInstance = new ObjetivoUnidad()
            objetivoUnidadInstance.properties = params
            title = g.message(code: "objetivounidad.create", default: "Create ObjetivoUnidad")
        } else if (params.source == "edit") {
            objetivoUnidadInstance = ObjetivoUnidad.get(params.id)
            if (!objetivoUnidadInstance) {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'objetivoUnidad.label', default: 'ObjetivoUnidad'), params.id])}"
                redirect(action: "list")
            }
            title = g.message(code: "objetivounidad.edit", default: "Edit ObjetivoUnidad")
        }

        return [objetivoUnidadInstance: objetivoUnidadInstance, title: title, source: params.source]
    }

    def create = {
        params.source = "create"
        redirect(action: "form", params: params)
    }
        /*Función para guardar nuevos objetivos*/
    def save = {
        def title
        if (params.id) {
            title = g.message(code: "objetivounidad.edit", default: "Edit ObjetivoUnidad")
            def objetivoUnidadInstance = ObjetivoUnidad.get(params.id)
            if (objetivoUnidadInstance) {
                objetivoUnidadInstance.properties = params
                if (!objetivoUnidadInstance.hasErrors() && objetivoUnidadInstance.save(flush: true)) {
                    flash.message = "${message(code: 'default.updated.message', args: [message(code: 'objetivoUnidad.label', default: 'ObjetivoUnidad'), objetivoUnidadInstance.id])}"
                    redirect(action: "show", id: objetivoUnidadInstance.id)
                } else {
                    render(view: "form", model: [objetivoUnidadInstance: objetivoUnidadInstance, title: title, source: "edit"])
                }
            } else {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'objetivoUnidad.label', default: 'ObjetivoUnidad'), params.id])}"
                redirect(action: "list")
            }
        } else {
            title = g.message(code: "objetivounidad.create", default: "Create ObjetivoUnidad")
            def objetivoUnidadInstance = new ObjetivoUnidad(params)
            if (objetivoUnidadInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.created.message', args: [message(code: 'objetivoUnidad.label', default: 'ObjetivoUnidad'), objetivoUnidadInstance.id])}"
                redirect(action: "show", id: objetivoUnidadInstance.id)
            } else {
                render(view: "form", model: [objetivoUnidadInstance: objetivoUnidadInstance, title: title, source: "create"])
            }
        }
    }
    /*Función para actualizar objetivos*/
    def update = {
        def objetivoUnidadInstance = ObjetivoUnidad.get(params.id)
        if (objetivoUnidadInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (objetivoUnidadInstance.version > version) {

                    objetivoUnidadInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'objetivoUnidad.label', default: 'ObjetivoUnidad')] as Object[], "Another user has updated this ObjetivoUnidad while you were editing")
                    render(view: "edit", model: [objetivoUnidadInstance: objetivoUnidadInstance])
                    return
                }
            }
            objetivoUnidadInstance.properties = params
            if (!objetivoUnidadInstance.hasErrors() && objetivoUnidadInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'objetivoUnidad.label', default: 'ObjetivoUnidad'), objetivoUnidadInstance.id])}"
                redirect(action: "show", id: objetivoUnidadInstance.id)
            } else {
                render(view: "edit", model: [objetivoUnidadInstance: objetivoUnidadInstance])
            }
        } else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'objetivoUnidad.label', default: 'ObjetivoUnidad'), params.id])}"
            redirect(action: "list")
        }
    }
    /*Muestra los datos de un objetivo específico*/
    def show = {
        def objetivoUnidadInstance = ObjetivoUnidad.get(params.id)
        if (!objetivoUnidadInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'objetivoUnidad.label', default: 'ObjetivoUnidad'), params.id])}"
            redirect(action: "list")
        } else {

            def title = g.message(code: "objetivounidad.show", default: "Show ObjetivoUnidad")

            [objetivoUnidadInstance: objetivoUnidadInstance, title: title]
        }
    }

    def edit = {
        params.source = "edit"
        redirect(action: "form", params: params)
    }
    /*Función para borrar un objetivo*/
    def delete = {
        def objetivoUnidadInstance = ObjetivoUnidad.get(params.id)
        if (objetivoUnidadInstance) {
            try {
                objetivoUnidadInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'objetivoUnidad.label', default: 'ObjetivoUnidad'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'objetivoUnidad.label', default: 'ObjetivoUnidad'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        } else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'objetivoUnidad.label', default: 'ObjetivoUnidad'), params.id])}"
            redirect(action: "list")
        }
    }
}
