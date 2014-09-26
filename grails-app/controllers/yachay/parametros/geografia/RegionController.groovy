package yachay.parametros.geografia

import yachay.parametros.geografia.Region

/**
 * Controlador
 */
class RegionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST", delete: "GET"]

    /**
     * Acción
     */
    def index = {
        redirect(action: "list", params: params)
    }

    /**
     * Acción
     */
    def list = {
        def title = g.message(code: "default.list.label", args: ["Region"], default: "Region List")
//        <g:message code="default.list.label" args="[entityName]" />

        params.max = Math.min(params.max ? params.int('max') : 25, 100)

        [regionInstanceList: Region.list(params), regionInstanceTotal: Region.count(), title: title, params: params]
    }

    /**
     * Acción
     */
    def form = {
        def title
        def regionInstance

        if (params.source == "create") {
            regionInstance = new Region()
            regionInstance.properties = params
            title = "Crear Región"
        } else if (params.source == "edit") {
            regionInstance = Region.get(params.id)
            if (!regionInstance) {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'region.label', default: 'Region'), params.id])}"
                redirect(action: "list")
            }
            title = "Editar Región"
        }

        return [regionInstance: regionInstance, title: title, source: params.source]
    }

    /**
     * Acción
     */
    def create = {
        params.source = "create"
        redirect(action: "form", params: params)
    }

    /**
     * Acción
     */
    def save = {
        def title
        if (params.id) {
            title = g.message(code: "default.edit.label", args: ["Region"], default: "Edit Region")
            def regionInstance = Region.get(params.id)
            if (regionInstance) {
                regionInstance.properties = params
                if (!regionInstance.hasErrors() && regionInstance.save(flush: true)) {
                    flash.message = "${message(code: 'default.updated.message', args: [message(code: 'region.label', default: 'Region'), regionInstance.id])}"
                    redirect(action: "show", id: regionInstance.id)
                }
                else {
                    render(view: "form", model: [regionInstance: regionInstance, title: title, source: "edit"])
                }
            }
            else {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'region.label', default: 'Region'), params.id])}"
                redirect(action: "list")
            }
        } else {
            title = g.message(code: "default.create.label", args: ["Region"], default: "Create Region")
            def regionInstance = new Region(params)
            if (regionInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.created.message', args: [message(code: 'region.label', default: 'Region'), regionInstance.id])}"
                redirect(action: "show", id: regionInstance.id)
            }
            else {
                render(view: "form", model: [regionInstance: regionInstance, title: title, source: "create"])
            }
        }
    }

    /**
     * Acción
     */
    def update = {
        def regionInstance = Region.get(params.id)
        if (regionInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (regionInstance.version > version) {

                    regionInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'region.label', default: 'Region')] as Object[], "Another user has updated this Region while you were editing")
                    render(view: "edit", model: [regionInstance: regionInstance])
                    return
                }
            }
            regionInstance.properties = params
            if (!regionInstance.hasErrors() && regionInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'region.label', default: 'Region'), regionInstance.id])}"
                redirect(action: "show", id: regionInstance.id)
            }
            else {
                render(view: "edit", model: [regionInstance: regionInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'region.label', default: 'Region'), params.id])}"
            redirect(action: "list")
        }
    }

    /**
     * Acción
     */
    def show = {
        def regionInstance = Region.get(params.id)
        if (!regionInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'region.label', default: 'Region'), params.id])}"
            redirect(action: "list")
        }
        else {

            def title = g.message(code: "default.show.label", args: ["Region"], default: "Show Region")

            [regionInstance: regionInstance, title: title]
        }
    }

    /**
     * Acción
     */
    def edit = {
        params.source = "edit"
        redirect(action: "form", params: params)
    }

    /**
     * Acción
     */
    def delete = {
        def regionInstance = Region.get(params.id)
        if (regionInstance) {
            try {
                regionInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'region.label', default: 'Region'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'region.label', default: 'Region'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'region.label', default: 'Region'), params.id])}"
            redirect(action: "list")
        }
    }
}
