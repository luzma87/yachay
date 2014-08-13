package app

import app.seguridad.Usro
import app.yachai.Categoria

class SolicitudController extends app.seguridad.Shield {

    def index = {}

    def show = {
        def solicitud = Solicitud.get(params.id)
        if (!solicitud) {
            flash.message = "No se encontró la solicitud"
        }
        return [solicitud: solicitud]
    }

    def save = {
        /* upload del PDF */
        def path = servletContext.getRealPath("/") + "pdf/solicitud/"
        new File(path).mkdirs()
        def f = request.getFile('pdf')
        def okContents = [
                'application/pdf': 'pdf',
        ]
        def nombre = ""
        if (f && !f.empty) {
            def fileName = f.getOriginalFilename() //nombre original del archivo
            def ext

            def parts = fileName.split("\\.")
            fileName = ""
            parts.eachWithIndex { obj, i ->
                if (i < parts.size() - 1) {
                    fileName += obj
                }
            }
            if (okContents.containsKey(f.getContentType())) {
                ext = okContents[f.getContentType()]
                fileName = fileName.size() < 40 ? fileName : fileName[0..39]
                fileName = fileName.tr(/áéíóúñÑÜüÁÉÍÓÚàèìòùÀÈÌÒÙÇç .!¡¿?&#°"'/, "aeiounNUuAEIOUaeiouAEIOUCc_")

                nombre = fileName + "." + ext
                def pathFile = path + nombre
                def fn = fileName
                def src = new File(pathFile)
                def i = 1
                while (src.exists()) {
                    nombre = fn + "_" + i + "." + ext
                    pathFile = path + nombre
                    src = new File(pathFile)
                    i++
                }
                try {
                    f.transferTo(new File(pathFile)) // guarda el archivo subido al nuevo path
                    //println pathFile
                } catch (e) {
                    println "????????\n" + e + "\n???????????"
                }
            }
        }
        /* fin del upload */

        def usuario = Usro.get(session.usuario.id)
        def unidadEjecutora = usuario.unidad

        def solicitud = new Solicitud()
        if (params.id) {
            solicitud = Solicitud.get(params.id.toLong())
        } else {
            solicitud.unidadEjecutora = unidadEjecutora
        }
        params.fecha = new Date().parse("dd-MM-yyyy", params.fecha)
        params.pathPdfTdr = nombre
        solicitud.properties = params
        if (!solicitud.save(flush: true)) {
            flash.message = "<h5>Ha ocurrido un error al crear la solicitud</h5>" + renderErrors(bean: solicitud)
        }
        redirect(action: 'show', id: solicitud.id)
    }

    def getComponentesByProyecto = {
        def tipoComponente = TipoElemento.get(2)

        def proyecto = Proyecto.get(params.id.toLong())
        def componentes = MarcoLogico.findAllByProyectoAndTipoElemento(proyecto, tipoComponente)

        def parms = [from     : componentes, name: "componente.id", id: "selComponente", class: "requiredCmb ui-widget-content ui-corner-all",
                     optionKey: "id", optionValue: "objeto"]
        if (params.val) {
            parms.value = params.val
        }

        def sel = g.select(parms)
        def js = "<script type=\"text/javascript\">" +
                "\$(\"#selComponente\").change(function() {" +
                "   loadActividades();" +
                "}).selectmenu({width : ${params.width}});" +
                "</script>"
        render sel + js
    }

    def comboActividades(compId, selected, width) {
        def tipoActividad = TipoElemento.get(3)

        def componente = MarcoLogico.get(compId)
        def actividades = []

        def anio = Anio.findByAnio(new Date().format("yyyy"))

        MarcoLogico.findAllByMarcoLogicoAndTipoElemento(componente, tipoActividad).each { act ->
            def tieneAsignacion = Asignacion.countByMarcoLogicoAndAnio(act, anio) > 0
            def id = act.id
            def str = act.objeto
            if (!tieneAsignacion) {
                str += " (*)"
            }
            actividades.add([id: id, objeto: str])
        }
        def parms = [from     : actividades, name: "actividad.id", id: "selActividad", class: "requiredCmb ui-widget-content ui-corner-all",
                     optionKey: "id", optionValue: "objeto"]
        if (selected) {
            parms.value = selected
        }
        def sel = g.select(parms)
        def btn = "<a href='#' id='btnAddActividad' style='margin-left: 3px;'>Agregar</a>"
        def js = "<script type=\"text/javascript\">" +
                "\$(\"#selActividad\").change(function() {" +
                "   loadDatosActividad();" +
                "}).selectmenu({width : ${width}});" +
                "\$('#btnAddActividad').button({text:false,icons:{primary:'ui-icon-plusthick'}}).click(function() {" +
                "crearActividad();" +
                "});" +
                "</script>"
        return sel + btn + js
    }

    def getActividadesByComponente = {
        render comboActividades(params.id, params.val, params.width)
    }

    def getDatosActividad = {
        def actividad = MarcoLogico.get(params.id.toLong())
        render actividad.objeto + "||" + actividad.monto
    }

    def newActividad_ajax = {
        def usuario = Usro.get(session.usuario.id)
        def unidadEjecutora = usuario.unidad
        def tipoActividad = TipoElemento.get(3)
        def actividad = new MarcoLogico()

        def proyecto = Proyecto.get(params.proy.toLong())
        def componente = MarcoLogico.get(params.comp.toLong())
        def categoria = Categoria.get(params.cat.toLong())
        def fechaInicio = new Date().parse("dd-MM-yyyy", params.fechaIni)
        def fechaFin = new Date().parse("dd-MM-yyyy", params.fechaFin)

        actividad.proyecto = proyecto
        actividad.tipoElemento = tipoActividad
        actividad.marcoLogico = componente
        actividad.objeto = params.actividad
        actividad.monto = params.monto.toDouble()
        actividad.estado = 0
        actividad.categoria = categoria
        actividad.fechaInicio = fechaInicio
        actividad.fechaFin = fechaFin
        actividad.responsable = unidadEjecutora
        actividad.aporte = params.aporte.toDouble()
        if (!actividad.save(flush: true)) {
            println "Error al guardar actividad: " + actividad.errors
        }
        render comboActividades(componente.id, actividad.id, params.width)
    }

    def downloadFile = {
        def solicitud = Solicitud.get(params.id.toLong())
        def path = servletContext.getRealPath("/") + "pdf/solicitud/" + solicitud.pathPdfTdr
        def tipo = solicitud.pathPdfTdr.split("\\.")
        tipo = tipo[1]
//            println "tipo " + tipo
        switch (tipo) {
            case "jpeg":
            case "gif":
            case "jpg":
            case "bmp":
            case "png":
                tipo = "application/image"
                break;
            case "pdf":
                tipo = "application/pdf"
                break;
            case "doc":
            case "docx":
            case "odt":
                tipo = "application/msword"
                break;
            case "xls":
            case "xlsx":
                tipo = "application/vnd.ms-excel"
                break;
            default:
                tipo = "application/pdf"
                break;
        }
        try {
            def file = new File(path)
            def b = file.getBytes()
            response.setContentType(tipo)
            response.setHeader("Content-disposition", "attachment; filename=" + (solicitud.pathPdfTdr))
            response.setContentLength(b.length)
            response.getOutputStream().write(b)
        } catch (e) {
            println "error en download"
            response.sendError(404)
        }
    }

    def ingreso = {
        def usuario = Usro.get(session.usuario.id)
        def unidadEjecutora = usuario.unidad
        def solicitud = new Solicitud()
        if (params.id) {
            solicitud = solicitud.get(params.id)
            if (!solicitud) {
                flash.message = "No se encontró la solicitud"
                solicitud = new Solicitud()
            }
        }
        return [unidadRequirente: unidadEjecutora, solicitud: solicitud]
    }
}
