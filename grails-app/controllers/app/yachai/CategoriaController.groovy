package app.yachai

import yachay.proyectos.Categoria

class CategoriaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST", delete: "GET"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        def title = g.message(code:"categoria.list", default:"Categoria List")
//        <g:message code="default.list.label" args="[entityName]" />
        
        params.max = Math.min(params.max ? params.int('max') : 10, 100)

        [categoriaInstanceList: Categoria.list(params), categoriaInstanceTotal: Categoria.count(), title: title, params:params]
    }

    def form = {
        def title
        def categoriaInstance

        if(params.source == "create") {
            categoriaInstance = new Categoria()
            categoriaInstance.properties = params
            title = g.message(code:"categoria.create", default:"Create Categoria")
        } else if(params.source == "edit") {
            categoriaInstance = Categoria.get(params.id)
            if (!categoriaInstance) {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'categoria.label', default: 'Categoria'), params.id])}"
                redirect(action: "list")
            }
            title = g.message(code:"categoria.edit", default:"Edit Categoria")
        }

        return [categoriaInstance: categoriaInstance, title:title, source: params.source]
    }

    def create = {
        params.source = "create"
        redirect(action:"form", params:params)
    }

    def save = {
        def title
        if(params.id) {
            title = g.message(code:"categoria.edit", default:"Edit Categoria")
            def categoriaInstance = Categoria.get(params.id)
            if (categoriaInstance) {
                categoriaInstance.properties = params
                if (!categoriaInstance.hasErrors() && categoriaInstance.save(flush: true)) {
                    flash.message = "${message(code: 'default.updated.message', args: [message(code: 'categoria.label', default: 'Categoria'), categoriaInstance.id])}"
                    redirect(action: "show", id: categoriaInstance.id)
                }
                else {
                    render(view: "form", model: [categoriaInstance: categoriaInstance, title:title, source: "edit"])
                }
            }
            else {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'categoria.label', default: 'Categoria'), params.id])}"
                redirect(action: "list")
            }
        } else {
            title = g.message(code:"categoria.create", default:"Create Categoria")
            def categoriaInstance = new Categoria(params)
            if (categoriaInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.created.message', args: [message(code: 'categoria.label', default: 'Categoria'), categoriaInstance.id])}"
                redirect(action: "show", id: categoriaInstance.id)
            }
            else {
                render(view: "form", model: [categoriaInstance: categoriaInstance, title:title, source: "create"])
            }
        }
    }

    def update = {
        def categoriaInstance = Categoria.get(params.id)
        if (categoriaInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (categoriaInstance.version > version) {
                    
                    categoriaInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'categoria.label', default: 'Categoria')] as Object[], "Another user has updated this Categoria while you were editing")
                    render(view: "edit", model: [categoriaInstance: categoriaInstance])
                    return
                }
            }
            categoriaInstance.properties = params
            if (!categoriaInstance.hasErrors() && categoriaInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'categoria.label', default: 'Categoria'), categoriaInstance.id])}"
                redirect(action: "show", id: categoriaInstance.id)
            }
            else {
                render(view: "edit", model: [categoriaInstance: categoriaInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'categoria.label', default: 'Categoria'), params.id])}"
            redirect(action: "list")
        }
    }

    def show = {
        def categoriaInstance = Categoria.get(params.id)
        if (!categoriaInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'categoria.label', default: 'Categoria'), params.id])}"
            redirect(action: "list")
        }
        else {

            def title = g.message(code:"categoria.show", default:"Show Categoria")

            [categoriaInstance: categoriaInstance, title: title]
        }
    }

    def edit = {
        params.source = "edit"
        redirect(action:"form", params:params)
    }

    def delete = {
        def categoriaInstance = Categoria.get(params.id)
        if (categoriaInstance) {
            try {
                categoriaInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'categoria.label', default: 'Categoria'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'categoria.label', default: 'Categoria'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'categoria.label', default: 'Categoria'), params.id])}"
            redirect(action: "list")
        }
    }
}
