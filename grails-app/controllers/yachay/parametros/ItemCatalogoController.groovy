package yachay.parametros

class ItemCatalogoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST", delete: "GET"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        def title = g.message(code: "itemcatalogo.list", default: "ItemCatalogo List")
//        <g:message code="default.list.label" args="[entityName]" />

        params.max = Math.min(params.max ? params.int('max') : 10, 100)

        [itemCatalogoInstanceList: ItemCatalogo.list(params), itemCatalogoInstanceTotal: ItemCatalogo.count(), title: title, params: params]
    }

    def form = {
        def title
        def itemCatalogoInstance

        if (params.source == "create") {
            itemCatalogoInstance = new ItemCatalogo()
            itemCatalogoInstance.properties = params
            title = g.message(code: "itemcatalogo.create", default: "Create ItemCatalogo")
        } else if (params.source == "edit") {
            itemCatalogoInstance = ItemCatalogo.get(params.id)
            if (!itemCatalogoInstance) {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'itemCatalogo.label', default: 'ItemCatalogo'), params.id])}"
                redirect(action: "list")
            }
            title = g.message(code: "itemcatalogo.edit", default: "Edit ItemCatalogo")
        }

        return [itemCatalogoInstance: itemCatalogoInstance, title: title, source: params.source]
    }

    def create = {
        params.source = "create"
        redirect(action: "form", params: params)
    }

    def save = {
        println("params " + params)
        def title
        if (params.id) {
            title = g.message(code: "itemcatalogo.edit", default: "Edit ItemCatalogo")
            def itemCatalogoInstance = ItemCatalogo.get(params.id)
            if (itemCatalogoInstance) {
                itemCatalogoInstance.properties = params
                if (!itemCatalogoInstance.hasErrors() && itemCatalogoInstance.save(flush: true)) {
                    flash.message = "${message(code: 'default.updated.message', args: [message(code: 'itemCatalogo.label', default: 'ItemCatalogo'), itemCatalogoInstance.id])}"
                    redirect(action: "show", id: itemCatalogoInstance.id)
                } else {
                    render(view: "form", model: [itemCatalogoInstance: itemCatalogoInstance, title: title, source: "edit"])
                }
            } else {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'itemCatalogo.label', default: 'ItemCatalogo'), params.id])}"
                redirect(action: "list")
            }
        } else {
            title = g.message(code: "itemcatalogo.create", default: "Create ItemCatalogo")
            def itemCatalogoInstance = new ItemCatalogo(params)
            if (itemCatalogoInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.created.message', args: [message(code: 'itemCatalogo.label', default: 'ItemCatalogo'), itemCatalogoInstance.id])}"
                redirect(action: "show", id: itemCatalogoInstance.id)
            } else {
                render(view: "form", model: [itemCatalogoInstance: itemCatalogoInstance, title: title, source: "create"])
            }
        }
    }

    def update = {
        def itemCatalogoInstance = ItemCatalogo.get(params.id)
        if (itemCatalogoInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (itemCatalogoInstance.version > version) {

                    itemCatalogoInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'itemCatalogo.label', default: 'ItemCatalogo')] as Object[], "Another user has updated this ItemCatalogo while you were editing")
                    render(view: "edit", model: [itemCatalogoInstance: itemCatalogoInstance])
                    return
                }
            }
            itemCatalogoInstance.properties = params
            if (!itemCatalogoInstance.hasErrors() && itemCatalogoInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'itemCatalogo.label', default: 'ItemCatalogo'), itemCatalogoInstance.id])}"
                redirect(action: "show", id: itemCatalogoInstance.id)
            } else {
                render(view: "edit", model: [itemCatalogoInstance: itemCatalogoInstance])
            }
        } else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'itemCatalogo.label', default: 'ItemCatalogo'), params.id])}"
            redirect(action: "list")
        }
    }

    def show = {
        def itemCatalogoInstance = ItemCatalogo.get(params.id)
        if (!itemCatalogoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'itemCatalogo.label', default: 'ItemCatalogo'), params.id])}"
            redirect(action: "list")
        } else {

            def title = g.message(code: "itemcatalogo.show", default: "Show ItemCatalogo")

            [itemCatalogoInstance: itemCatalogoInstance, title: title]
        }
    }

    def edit = {
        params.source = "edit"
        redirect(action: "form", params: params)
    }

    def delete = {
        def itemCatalogoInstance = ItemCatalogo.get(params.id)
        if (itemCatalogoInstance) {
            try {
                itemCatalogoInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'itemCatalogo.label', default: 'ItemCatalogo'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'itemCatalogo.label', default: 'ItemCatalogo'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        } else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'itemCatalogo.label', default: 'ItemCatalogo'), params.id])}"
            redirect(action: "list")


        }
    }

    /**
     * Acción que muestra un formulario para la creación de un nuevo catálogo
     */
    def creaItem = {
        println "creaItem: " + params
        def itemCatalogoInstance = new ItemCatalogo()
        //catalogoInstance.properties = params
        render(view: 'crear', model: ['itemCatalogoInstance': itemCatalogoInstance])
    }

    /*Acción de edición de items*/

    def editarItem = {

    }

    def saveItem = {

        println("params " + params)

        def catalogo = Catalogo.findById(params.cata)
        def itemInstance = ItemCatalogo.get(params.id)

        if(itemInstance){
            println("item " +  itemInstance)

            if(params.codigo){
                itemInstance.codigo = params.codigo
            }else{
                itemInstance.codigo = ''
            }

            itemInstance.descripcion = params.descripcion
            if(params.estado){
                itemInstance.estado = params.estado.toInteger()
            }else{
                itemInstance.estado = 1
            }

            if(params.orden){
                itemInstance.orden = params.orden.toInteger()
            }else{
                itemInstance.orden = 0
            }

            if(params.original){
                itemInstance.original = params.original.toInteger()
            }else{
                itemInstance.original = 0
            }

            itemInstance.nombre = params.descripcion

            if(itemInstance.save(flush: true)){
                println("grabo")
                render("ok_Item actualizado correctamente")

            }else{
                println("error" + itemInstance.errors)
                render("no_Error al actualizar los datos")
            }

        }



    }

    def borrarItem = {

        println("params borrado " + params)
        def itemInstance = ItemCatalogo.get(params.id)

        println("por borrar " + itemInstance)

        if(itemInstance){
            if(itemInstance.delete(flush: true)){
                println("borro")
                render("ok_Item borrado correctamente")
            }else{
                println("error" + itemInstance.errors)
                render("no_Error al borrar los datos")
            }
        }else{
            println("error")
        }
    }

}
