package app

import app.alertas.Alerta
import app.seguridad.Prfl
import app.seguridad.Sesn
import app.seguridad.Usro
import app.yachai.Categoria

class SolicitudController extends app.seguridad.Shield {

    def index = {
        redirect(action: 'list')
    }

    def list = {
        def title = g.message(code: "default.list.label", args: ["Solicitud"], default: "Solicitud List")
//        <g:message code="default.list.label" args="[entityName]" />

        params.max = Math.min(params.max ? params.int('max') : 25, 100)

        [solicitudInstanceList: Solicitud.list(params), solicitudInstanceTotal: Solicitud.count(), title: title, params: params]
    }

    def show = {
        def solicitud = Solicitud.get(params.id)
        if (!solicitud) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), params.id])}"
            redirect(action: "list")
        } else {
            def title = g.message(code: "default.show.label", args: ["Solicitud"], default: "Show Solicitud")
            [solicitud: solicitud, title: title]
        }
    }

    def save = {
        def usuario = Usro.get(session.usuario.id)
        def unidadEjecutora = usuario.unidad

        def solicitud = new Solicitud()
        if (params.id) {
            solicitud = Solicitud.get(params.id.toLong())
        } else {
            solicitud.unidadEjecutora = unidadEjecutora
            solicitud.usuario = usuario
        }

        /* upload del PDF */
        def path = servletContext.getRealPath("/") + "pdf/solicitud/"
        new File(path).mkdirs()
        def f = request.getFile('pdf')
        def okContents = [
                'application/pdf': 'pdf',
        ]
        def nombre = ""
        if (f && !f.empty) {
            if (solicitud.pathPdfTdr) {
                //si ya existe un archivo para esta solicitud lo elimino
                def oldFile = new File(path + solicitud.pathPdfTdr)
                if (oldFile.exists()) {
                    oldFile.delete()
                }
            }
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
                    params.pathPdfTdr = nombre
                    //println pathFile
                } catch (e) {
                    println "????????\n" + e + "\n???????????"
                }
            }
        }
        /* fin del upload */
        params.fecha = new Date().parse("dd-MM-yyyy", params.fecha)
        solicitud.properties = params
        if (!solicitud.save(flush: true)) {
            flash.message = "<h5>Ha ocurrido un error al crear la solicitud</h5>" + renderErrors(bean: solicitud)
        } else {
            def perfilGAF = Prfl.findByCodigo("GAF")
            def perfilGJ = Prfl.findByCodigo("GJ")
            def perfilGDP = Prfl.findByCodigo("GDP")

            def usuariosGAF = Sesn.findAllByPerfil(perfilGAF).usuario
            def usuariosGJ = Sesn.findAllByPerfil(perfilGJ).usuario
            def usuariosGDP = Sesn.findAllByPerfil(perfilGDP).usuario

            def from = Usro.get(session.usuario.id)
            def envio = new Date()
            def mensaje = from.persona.nombre + " " + from.persona.apellido + " ha ${params.id ? 'modificado' : 'creado'} una solicitud"
            def controlador = "solicitud"
            def action = "revision"
            def idRemoto = solicitud.id

            (usuariosGAF + usuariosGJ + usuariosGDP).each { usu ->
                def alerta = new Alerta()
                alerta.from = from
                alerta.usro = usu
                alerta.fec_envio = envio
                alerta.mensaje = mensaje
                alerta.controlador = controlador
                alerta.accion = action
                alerta.id_remoto = idRemoto
                if (!alerta.save(flush: true)) {
                    println "error alerta: " + alerta.errors
                }
            }
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
                act.tieneAsignacion = 'N'
                act.save(flush: true)
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
        actividad.tieneAsignacion = "N"
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

    def downloadActa = {
        def aprobacion = Aprobacion.get(params.id.toLong())
        def path = servletContext.getRealPath("/") + "pdf/actasAprobacion/" + aprobacion.pathPdf
        def tipo = aprobacion.pathPdf.split("\\.")
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
            response.setHeader("Content-disposition", "attachment; filename=" + (aprobacion.pathPdf))
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
        def title = "Nueva"
        if (params.id) {
            solicitud = solicitud.get(params.id)
            if (solicitud.estado == 'A') {
                redirect(action: "show", id: solicitud.id)
                return
            }
            title = "Modificar"
            if (!solicitud) {
                flash.message = "No se encontró la solicitud"
                solicitud = new Solicitud()
            }
        }
        title += " solicitud"
        return [unidadRequirente: unidadEjecutora, solicitud: solicitud, title: title]
    }

    def revision = {
        def perfil = Prfl.get(session.perfil.id)
        def solicitud = Solicitud.get(params.id)
        if (!solicitud) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'solicitud.label', default: 'Solicitud'), params.id])}"
            redirect(action: "list")
        } else {
            def title = "Revisar solicitud"
            [solicitud: solicitud, title: title, perfil: perfil]
        }
    }

    def saveRevision = {
        def solicitud = Solicitud.get(params.id)
        if (solicitud) {
            if (params.observacionesJuridica) {
                solicitud.observacionesJuridica = params.observacionesJuridica
            }
            if (params.observacionesAdministrativaFinanciera) {
                solicitud.observacionesAdministrativaFinanciera = params.observacionesAdministrativaFinanciera
            }
            if (params.observacionesDireccionProyectos) {
                solicitud.observacionesDireccionProyectos = params.observacionesDireccionProyectos
            }
            if (params.gj == "on") {
                solicitud.revisadoJuridica = new Date()
            }
            if (params.gaf == "on") {
                solicitud.revisadoAdministrativaFinanciera = new Date()
            }
            if (params.gdp == "on") {
                solicitud.revisadoDireccionProyectos = new Date()
            }
            if (!solicitud.save(flush: true)) {
                println "error al guardar revision: " + solicitud.errors
                flash.message = "Ha ocurrido un error al guardar su revisión."
            }
        }
        redirect(action: "show", id: solicitud.id)
    }

    def aprobacion = {
        def solicitud = Solicitud.get(params.id.toLong())
        def perfil = Prfl.get(session.perfil.id)
        def title = "Aprobar solicitud"
        if (!(solicitud.revisadoJuridica && solicitud.revisadoAdministrativaFinanciera && solicitud.revisadoDireccionProyectos)) {
            redirect(action: "show", id: solicitud.id)
            return
        }
        Aprobacion aprobacion = new Aprobacion()
        if (solicitud) {
            aprobacion = Aprobacion.findBySolicitud(solicitud)
            if (!aprobacion) {
                aprobacion = new Aprobacion()
            }
        }

        return [solicitud: solicitud, perfil: perfil, title: title, aprobacion: aprobacion]
    }

    def saveAprobacion = {
        def aprobacion = new Aprobacion()
        if (params.id) {
            aprobacion = Aprobacion.get(params.id.toLong())
        }
        params.fecha = new Date()
        aprobacion.properties = params
        if (aprobacion.save(flush: true)) {
            aprobacion.solicitud.estado = "A"
            if (!aprobacion.solicitud.save(flush: true)) {
                println "error al cambiar de estado la solicitud: " + aprobacion.solicitud.errors
            }
        } else {
            println "error al guardar aprobacion: " + aprobacion.errors
        }
        redirect(action: "show", id: aprobacion.solicitudId)
    }

    def uploadActa = {
        def aprobacion = Aprobacion.get(params.id.toLong())

        /* upload del PDF */
        def path = servletContext.getRealPath("/") + "pdf/actasAprobacion/"
        new File(path).mkdirs()
        def f = request.getFile('pdf')
        def okContents = [
                'application/pdf': 'pdf',
        ]
        def nombre = ""
        if (f && !f.empty) {
            if (aprobacion.pathPdf) {
                //si ya existe un archivo para esta aprobacion lo elimino
                def oldFile = new File(path + aprobacion.pathPdf)
                if (oldFile.exists()) {
                    oldFile.delete()
                }
            }
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
                    aprobacion.pathPdf = nombre
                    //println pathFile
                } catch (e) {
                    println "????????\n" + e + "\n???????????"
                }
            }
        }
        /* fin del upload */
        if (!aprobacion.save(flush: true)) {
            println aprobacion.errors
        }
        redirect(action: "show", id: aprobacion.solicitudId)
    }
}
