package yachay.proyectos

import yachay.proyectos.DocumentoProceso

class DocumentoProcesoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST", delete: "GET"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        def title = g.message(code: "documentoproceso.list", default: "DocumentoProceso List")
//        <g:message code="default.list.label" args="[entityName]" />

        params.max = Math.min(params.max ? params.int('max') : 10, 100)

        [documentoProcesoInstanceList: DocumentoProceso.list(params), documentoProcesoInstanceTotal: DocumentoProceso.count(), title: title, params: params]
    }

    def form = {
        def title
        def documentoProcesoInstance

        if (params.source == "create") {
            documentoProcesoInstance = new DocumentoProceso()
            documentoProcesoInstance.properties = params
            title = g.message(code: "Crear", default: "Create DocumentoProceso")
        } else if (params.source == "edit") {
            documentoProcesoInstance = DocumentoProceso.get(params.id)
            if (!documentoProcesoInstance) {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'documentoProceso.label', default: 'DocumentoProceso'), params.id])}"
                redirect(action: "list")
            }
            title = g.message(code: "documentoproceso.edit", default: "Edit DocumentoProceso")
        }

        return [documentoProcesoInstance: documentoProcesoInstance, title: title, source: params.source]
    }

    def create = {
        params.source = "create"
        redirect(action: "form", params: params)
    }

    def save = {
        def title
        if (params.id) {
            title = g.message(code: "documentoproceso.edit", default: "Edit DocumentoProceso")
            def documentoProcesoInstance = DocumentoProceso.get(params.id)
            if (documentoProcesoInstance) {
                documentoProcesoInstance.properties = params
                if (!documentoProcesoInstance.hasErrors() && documentoProcesoInstance.save(flush: true)) {
                    flash.message = "${message(code: 'default.updated.message', args: [message(code: 'documentoProceso.label', default: 'DocumentoProceso'), documentoProcesoInstance.id])}"
                    redirect(action: "show", id: documentoProcesoInstance.id)
                }
                else {
                    render(view: "form", model: [documentoProcesoInstance: documentoProcesoInstance, title: title, source: "edit"])
                }
            }
            else {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'documentoProceso.label', default: 'DocumentoProceso'), params.id])}"
                redirect(action: "list")
            }
        } else {
            title = g.message(code: "documentoproceso.create", default: "Create DocumentoProceso")
            def documentoProcesoInstance = new DocumentoProceso(params)
            if (documentoProcesoInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.created.message', args: [message(code: 'documentoProceso.label', default: 'DocumentoProceso'), documentoProcesoInstance.id])}"
                redirect(action: "show", id: documentoProcesoInstance.id)
            }
            else {
                render(view: "form", model: [documentoProcesoInstance: documentoProcesoInstance, title: title, source: "create"])
            }
        }
    }

    def update = {
        def documentoProcesoInstance = DocumentoProceso.get(params.id)
        if (documentoProcesoInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (documentoProcesoInstance.version > version) {

                    documentoProcesoInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'documentoProceso.label', default: 'DocumentoProceso')] as Object[], "Another user has updated this DocumentoProceso while you were editing")
                    render(view: "edit", model: [documentoProcesoInstance: documentoProcesoInstance])
                    return
                }
            }
            documentoProcesoInstance.properties = params
            if (!documentoProcesoInstance.hasErrors() && documentoProcesoInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'documentoProceso.label', default: 'DocumentoProceso'), documentoProcesoInstance.id])}"
                redirect(action: "show", id: documentoProcesoInstance.id)
            }
            else {
                render(view: "edit", model: [documentoProcesoInstance: documentoProcesoInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'documentoProceso.label', default: 'DocumentoProceso'), params.id])}"
            redirect(action: "list")
        }
    }

    def show = {
        def documentoProcesoInstance = DocumentoProceso.get(params.id)
        if (!documentoProcesoInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'documentoProceso.label', default: 'DocumentoProceso'), params.id])}"
            redirect(action: "list")
        }
        else {

            def title = g.message(code: "documentoproceso.show", default: "Show DocumentoProceso")

            [documentoProcesoInstance: documentoProcesoInstance, title: title]
        }
    }

    def edit = {
        params.source = "edit"
        redirect(action: "form", params: params)
    }

    def delete = {
        def documentoProcesoInstance = DocumentoProceso.get(params.id)
        if (documentoProcesoInstance) {
            try {
                documentoProcesoInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'documentoProceso.label', default: 'DocumentoProceso'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'documentoProceso.label', default: 'DocumentoProceso'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'documentoProceso.label', default: 'DocumentoProceso'), params.id])}"
            redirect(action: "list")
        }
    }
}
