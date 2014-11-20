package yachay.parametros

class CatalogoController {

    def dbConnectionService
    def kerberosService


    static allowedMethods = [save: "POST", update: "POST", delete: "POST", delete: "GET"]

    def index = {
        redirect(action: "lsta", params: params)
    }

    def items = {

    }

    def list = {
        def title = g.message(code: "catalogo.list", default: "Catalogo List")
//        <g:message code="default.list.label" args="[entityName]" />

        params.max = Math.min(params.max ? params.int('max') : 10, 100)

        [catalogoInstanceList: Catalogo.list(params), catalogoInstanceTotal: Catalogo.count(), title: title, params: params]
    }

    def form = {
        def title
        def catalogoInstance

        if (params.source == "create") {
            catalogoInstance = new Catalogo()
            catalogoInstance.properties = params
            title = g.message(code: "catalogo.create", default: "Create Catalogo")
        } else if (params.source == "edit") {
            catalogoInstance = Catalogo.get(params.id)
            if (!catalogoInstance) {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'catalogo.label', default: 'Catalogo'), params.id])}"
                redirect(action: "list")
            }
            title = g.message(code: "catalogo.edit", default: "Edit Catalogo")
        }

        return [catalogoInstance: catalogoInstance, title: title, source: params.source]
    }

    def create = {
        params.source = "create"
        redirect(action: "form", params: params)
    }

    def save = {
        def title
        if (params.id) {
            title = g.message(code: "catalogo.edit", default: "Edit Catalogo")
            def catalogoInstance = Catalogo.get(params.id)
            if (catalogoInstance) {
                catalogoInstance.properties = params
                if (!catalogoInstance.hasErrors() && catalogoInstance.save(flush: true)) {
                    flash.message = "${message(code: 'default.updated.message', args: [message(code: 'catalogo.label', default: 'Catalogo'), catalogoInstance.id])}"
                    redirect(action: "show", id: catalogoInstance.id)
                } else {
                    render(view: "form", model: [catalogoInstance: catalogoInstance, title: title, source: "edit"])
                }
            } else {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'catalogo.label', default: 'Catalogo'), params.id])}"
                redirect(action: "list")
            }
        } else {
            title = g.message(code: "catalogo.create", default: "Create Catalogo")
            def catalogoInstance = new Catalogo(params)
            if (catalogoInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.created.message', args: [message(code: 'catalogo.label', default: 'Catalogo'), catalogoInstance.id])}"
                redirect(action: "show", id: catalogoInstance.id)
            } else {
                render(view: "form", model: [catalogoInstance: catalogoInstance, title: title, source: "create"])
            }
        }
    }

    def update = {
        def catalogoInstance = Catalogo.get(params.id)
        if (catalogoInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (catalogoInstance.version > version) {

                    catalogoInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'catalogo.label', default: 'Catalogo')] as Object[], "Another user has updated this Catalogo while you were editing")
                    render(view: "edit", model: [catalogoInstance: catalogoInstance])
                    return
                }
            }
            catalogoInstance.properties = params
            if (!catalogoInstance.hasErrors() && catalogoInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'catalogo.label', default: 'Catalogo'), catalogoInstance.id])}"
                redirect(action: "show", id: catalogoInstance.id)
            } else {
                render(view: "edit", model: [catalogoInstance: catalogoInstance])
            }
        } else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'catalogo.label', default: 'Catalogo'), params.id])}"
            redirect(action: "list")
        }
    }

    def show = {
        def catalogoInstance = Catalogo.get(params.id)
        if (!catalogoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'catalogo.label', default: 'Catalogo'), params.id])}"
            redirect(action: "list")
        } else {

            def title = g.message(code: "catalogo.show", default: "Show Catalogo")

            [catalogoInstance: catalogoInstance, title: title]
        }
    }

    def edit = {
        params.source = "edit"
        redirect(action: "form", params: params)
    }

    def delete = {
        def catalogoInstance = Catalogo.get(params.id)
        if (catalogoInstance) {
            try {
                catalogoInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'catalogo.label', default: 'Catalogo'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'catalogo.label', default: 'Catalogo'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        } else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'catalogo.label', default: 'Catalogo'), params.id])}"
            redirect(action: "list")
        }
    }

    /**
     * Acción que muestra una lista de las acciones que puede ver un determinado catálogo
     * @param Catalogo es el identificador del catálogo
     * @param tpac es el identificador del tipo de acción.
     * @param ids es la lista de los módulos
     */
    def cargaItem = {
        println "---------parametros: ${params}"
        def ctlg = params.ctlg.toInteger()
        def resultado = []
        def i = 0
        def ids = params.ids

        if (params.menu?.size() > 0) ids = params.menu
        if (params.grabar) {
            //println "a grabar... ${Catalogo}, ${ids}"
        }

        def cn = dbConnectionService.getConnection()
        def tx = ""
        // selecciona las acciones que no se han consedido permisos
        tx = "select itct__id, itctcdgo, itctdscr, itctetdo, itctordn, itctorgn " +
                "from itct " +
                "where ctlg__id = " + ctlg + " order by itctnmbr"

        println "ajaxPermisos SQL: ${tx}"
        cn.eachRow(tx) { d ->
            resultado[i] = [d.itct__id] + [d.itctcdgo] + [d.itctdscr] + [d.itctetdo] + [d.itctordn] + [d.itctorgn]
            i++
        }
        cn.close()
        //println "-------------------------" + resultado
        render(view: 'lsta', model: [datos: resultado, mdlo__id: ids, catalogo: params.ctlg])
    }

    /**
     * Acción que muestra un formulario para la creación de un nuevo catálogo
     */
    def creaCtlg = {
        def catalogoInstance = new Catalogo()
        //catalogoInstance.properties = params
        render(view: 'crear', model: ['catalogoInstance': catalogoInstance])
    }

    /**
     * Acción que muestra los datos de un catálogo en un formulario para su edición
     * @param id es el identificador del catálogo
     */
    def editCtlg = {
//      println "------editCatalogo: " + params
        def catalogoInstance = Catalogo.get(params.id.toInteger())
        render(view: 'crear', model: ['catalogoInstance': catalogoInstance])
    }


    def saveItems = {

    }

    /**
     * Acción que borra un catálogo
     * @param id es el identificador del catálogo
     */
    def borraCtlg = {
//      println "------editCatalogo: " + params
        params.controllerName = controllerName
        params.actionName = "delete"
        kerberosService.delete(params, Catalogo, session.perfil, session.usuario)
        render('borrado: ${params.id}')
    }

    /**
     * Acción que guarda los datos de un catálogo
     * @param id es el identificador del catálogo en caso de edición
     * @param params es un mapa con los nombres de los campos del dominio catálogo y los valores ingresados por el usuario
     */
    def grabaCtlg = {
        println "+++++parametros: ${params}"
        if (!params.id) {
            def catalogoInstance = new Catalogo()
            params.controllerName = controllerName
            params.actionName = "save"
            catalogoInstance = kerberosService.save(params, Catalogo, session.perfil, session.usuario)
            if (catalogoInstance.properties.errors.getErrorCount() > 0) {
                println "---- save ${catalogoInstance.errors}"
                render("El catálogo no ha podido crearse")
            } else {
                render("El catálogo ${params.nmbr} ha sido grabado en el sistema")
            }
        } else {
            println "<<< Update >>> : ${params.id}"
            def catalogoInstance = Catalogo.get(params.id)
            params.controllerName = controllerName
            params.actionName = "Update"
            catalogoInstance = kerberosService.save(params, Catalogo, session.perfil, session.usuario)
            if (catalogoInstance.properties.errors.getErrorCount() > 0) {
                println "---- save ${catalogoInstance}"
                render("El catálogo no ha podido actualizar")
            } else {
                render("ok")
            }
        }
    }


}
