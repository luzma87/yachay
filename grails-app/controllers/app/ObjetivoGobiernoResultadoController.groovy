package app

class ObjetivoGobiernoResultadoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST", delete: "GET"]

    def index = {
        redirect(action: "list", params: params)
    }
     /*Lista de objetivos de gobierno x resultado*/
    def list = {
        def title = g.message(code: "objetivogobiernoresultado.list", default: "ObjetivoGobiernoResultado List")
//        <g:message code="default.list.label" args="[entityName]" />

        params.max = Math.min(params.max ? params.int('max') : 10, 100)

        [objetivoGobiernoResultadoInstanceList: ObjetivoGobiernoResultado.list(params), objetivoGobiernoResultadoInstanceTotal: ObjetivoGobiernoResultado.count(), title: title, params: params]
    }

    /*form para la creación de un nuevo objetivo gobierno x resultado*/
    def form = {
        def title
        def objetivoGobiernoResultadoInstance

        if (params.source == "create") {
            objetivoGobiernoResultadoInstance = new ObjetivoGobiernoResultado()
            objetivoGobiernoResultadoInstance.properties = params
            title = g.message(code: "objetivogobiernoresultado.create", default: "Crear Objetivos de Gobierno por Resultados")
        } else if (params.source == "edit") {
            objetivoGobiernoResultadoInstance = ObjetivoGobiernoResultado.get(params.id)
            if (!objetivoGobiernoResultadoInstance) {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'objetivoGobiernoResultado.label', default: 'ObjetivoGobiernoResultado'), params.id])}"
                redirect(action: "list")
            }
            title = g.message(code: "objetivogobiernoresultado.edit", default: "Edit ObjetivoGobiernoResultado")
        }

        return [objetivoGobiernoResultadoInstance: objetivoGobiernoResultadoInstance, title: title, source: params.source]
    }

    def create = {
        params.source = "create"
        redirect(action: "form", params: params)
    }

    /*función para guardar un nuevo objetivo*/
    def save = {
        def title
        if (params.id) {
            title = g.message(code: "objetivogobiernoresultado.edit", default: "Edit ObjetivoGobiernoResultado")
            def objetivoGobiernoResultadoInstance = ObjetivoGobiernoResultado.get(params.id)
            if (objetivoGobiernoResultadoInstance) {
                objetivoGobiernoResultadoInstance.properties = params
                if (!objetivoGobiernoResultadoInstance.hasErrors() && objetivoGobiernoResultadoInstance.save(flush: true)) {
                    flash.message = "${message(code: 'default.updated.message', args: [message(code: 'objetivoGobiernoResultado.label', default: 'ObjetivoGobiernoResultado'), objetivoGobiernoResultadoInstance.id])}"
                    redirect(action: "show", id: objetivoGobiernoResultadoInstance.id)
                }
                else {
                    render(view: "form", model: [objetivoGobiernoResultadoInstance: objetivoGobiernoResultadoInstance, title: title, source: "edit"])
                }
            }
            else {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'objetivoGobiernoResultado.label', default: 'ObjetivoGobiernoResultado'), params.id])}"
                redirect(action: "list")
            }
        } else {
            title = g.message(code: "objetivogobiernoresultado.create", default: "Create ObjetivoGobiernoResultado")
            def objetivoGobiernoResultadoInstance = new ObjetivoGobiernoResultado(params)
            if (objetivoGobiernoResultadoInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.created.message', args: [message(code: 'objetivoGobiernoResultado.label', default: 'ObjetivoGobiernoResultado'), objetivoGobiernoResultadoInstance.id])}"
                redirect(action: "show", id: objetivoGobiernoResultadoInstance.id)
            }
            else {
                render(view: "form", model: [objetivoGobiernoResultadoInstance: objetivoGobiernoResultadoInstance, title: title, source: "create"])
            }
        }
    }

    /*función para actualizar un objetivo*/
    def update = {
        def objetivoGobiernoResultadoInstance = ObjetivoGobiernoResultado.get(params.id)
        if (objetivoGobiernoResultadoInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (objetivoGobiernoResultadoInstance.version > version) {

                    objetivoGobiernoResultadoInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'objetivoGobiernoResultado.label', default: 'ObjetivoGobiernoResultado')] as Object[], "Another user has updated this ObjetivoGobiernoResultado while you were editing")
                    render(view: "edit", model: [objetivoGobiernoResultadoInstance: objetivoGobiernoResultadoInstance])
                    return
                }
            }
            objetivoGobiernoResultadoInstance.properties = params
            if (!objetivoGobiernoResultadoInstance.hasErrors() && objetivoGobiernoResultadoInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'objetivoGobiernoResultado.label', default: 'ObjetivoGobiernoResultado'), objetivoGobiernoResultadoInstance.id])}"
                redirect(action: "show", id: objetivoGobiernoResultadoInstance.id)
            }
            else {
                render(view: "edit", model: [objetivoGobiernoResultadoInstance: objetivoGobiernoResultadoInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'objetivoGobiernoResultado.label', default: 'ObjetivoGobiernoResultado'), params.id])}"
            redirect(action: "list")
        }
    }

    /*muestra el objetivo actual ha ser actualizado*/
    def show = {
        def objetivoGobiernoResultadoInstance = ObjetivoGobiernoResultado.get(params.id)
        if (!objetivoGobiernoResultadoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'objetivoGobiernoResultado.label', default: 'ObjetivoGobiernoResultado'), params.id])}"
            redirect(action: "list")
        }
        else {

            def title = g.message(code: "objetivogobiernoresultado.show", default: "Show ObjetivoGobiernoResultado")

            [objetivoGobiernoResultadoInstance: objetivoGobiernoResultadoInstance, title: title]
        }
    }

    def edit = {
        params.source = "edit"
        redirect(action: "form", params: params)
    }

    /*funcion para borrar un objetivo*/
    def delete = {
        def objetivoGobiernoResultadoInstance = ObjetivoGobiernoResultado.get(params.id)
        if (objetivoGobiernoResultadoInstance) {
            try {
                objetivoGobiernoResultadoInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'objetivoGobiernoResultado.label', default: 'ObjetivoGobiernoResultado'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'objetivoGobiernoResultado.label', default: 'ObjetivoGobiernoResultado'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'objetivoGobiernoResultado.label', default: 'ObjetivoGobiernoResultado'), params.id])}"
            redirect(action: "list")
        }
    }
}
