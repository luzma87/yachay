package app

import app.alertas.Alerta
import app.seguridad.Prfl
import app.seguridad.Sesn
import app.seguridad.Usro
import app.yachai.Categoria

import javax.imageio.ImageIO
import java.awt.image.BufferedImage

import static java.awt.RenderingHints.KEY_INTERPOLATION
import static java.awt.RenderingHints.VALUE_INTERPOLATION_BICUBIC

class SolicitudController extends app.seguridad.Shield {

    def getPathBase() {
        return servletContext.getRealPath("/") + "solicitudes/"
    }

    def resizeImage(f, pathFile) {
        def imageContent = ['image/png': "png", 'image/jpeg': "jpeg", 'image/jpg': "jpg"]
        def ext = ""
        if (imageContent.containsKey(f.getContentType())) {
            ext = imageContent[f.getContentType()]
            /* RESIZE */
            def img = ImageIO.read(new File(pathFile))

            def scale = 0.5

            def minW = 200
            def minH = 200

            def maxW = minW * 4
            def maxH = minH * 4

            def w = img.width
            def h = img.height

            if (w > maxW || h > maxH) {
                int newW = w * scale
                int newH = h * scale
                int r = 1
                if (w > h) {
                    r = w / maxW
                    newW = maxW
                    newH = h / r
                } else {
                    r = h / maxH
                    newH = maxH
                    newW = w / r
                }

                new BufferedImage(newW, newH, img.type).with { j ->
                    createGraphics().with {
                        setRenderingHint(KEY_INTERPOLATION, VALUE_INTERPOLATION_BICUBIC)
                        drawImage(img, 0, 0, newW, newH, null)
                        dispose()
                    }
                    ImageIO.write(j, ext, new File(pathFile))
                }
            }
            /* fin resize */
        } //si es imagen hace resize para que no exceda 800x800
    }

    def validateContent(f) {
        return true
//        def okContents = [
//                'image/png'                                                                : "png",
//                'image/jpeg'                                                               : "jpeg",
//                'image/jpg'                                                                : "jpg",
//
//                'application/pdf'                                                          : 'pdf',
//
//                'application/excel'                                                        : 'xls',
//                'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'        : 'xlsx',
//
//                'application/mspowerpoint'                                                 : 'pps',
//                'application/vnd.ms-powerpoint'                                            : 'pps',
//                'application/powerpoint'                                                   : 'ppt',
//                'application/x-mspowerpoint'                                               : 'ppt',
//                'application/vnd.openxmlformats-officedocument.presentationml.slideshow'   : 'ppsx',
//                'application/vnd.openxmlformats-officedocument.presentationml.presentation': 'pptx',
//
//                'application/msword'                                                       : 'doc',
//                'application/vnd.openxmlformats-officedocument.wordprocessingml.document'  : 'docx',
//
//                'application/vnd.oasis.opendocument.text'                                  : 'odt',
//
//                'application/vnd.oasis.opendocument.presentation'                          : 'odp',
//
//                'application/vnd.oasis.opendocument.spreadsheet'                           : 'ods'
//        ]
//        if (okContents.containsKey(f.getContentType())) {
//            return true
//        } else {
//            return false
//        }
    }

    def uploadFile(tipo, f, objeto) {
        String pathBaseArchivos = getPathBase()
        String archivoTdr = "tdr"
        String archivoTdrGaf = "tdr_gaf"
        String archivoTdrGj = "tdr_gj"
        String archivoTdrGdp = "tdr_gdp"
        String archivoOferta = "oferta"
        String archivoComparativo = "cuadroComparativo"
        String archivoAnalisis = "analisisCostos"
        String archivoActa = "actaAprobacion"

        def path = ""
        def pathArchivo = ""
        def nuevoArchivo = ""

        switch (tipo) {
            case "tdr":
                path = pathBaseArchivos + "${objeto.id}/"
                pathArchivo = objeto.pathPdfTdr
                nuevoArchivo = archivoTdr
                break;
            case "oferta1":
                path = pathBaseArchivos + "${objeto.id}/"
                pathArchivo = objeto.pathOferta1
                nuevoArchivo = archivoOferta + "1"
                break;
            case "oferta2":
                path = pathBaseArchivos + "${objeto.id}/"
                pathArchivo = objeto.pathOferta2
                nuevoArchivo = archivoOferta + "2"
                break;
            case "oferta3":
                path = pathBaseArchivos + "${objeto.id}/"
                pathArchivo = objeto.pathOferta3
                nuevoArchivo = archivoOferta + "3"
                break;
            case "comparativo":
                path = pathBaseArchivos + "${objeto.id}/"
                pathArchivo = objeto.pathCuadroComparativo
                nuevoArchivo = archivoComparativo
                break;
            case "analisis":
                path = pathBaseArchivos + "${objeto.id}/"
                pathArchivo = objeto.pathAnalisisCostos
                nuevoArchivo = archivoAnalisis
                break;
            case "revisiongaf":
                path = pathBaseArchivos + "${objeto.id}/"
                pathArchivo = objeto.pathRevisionGAF
                nuevoArchivo = archivoTdrGaf
                break;
            case "revisiongj":
                path = pathBaseArchivos + "${objeto.id}/"
                pathArchivo = objeto.pathRevisionGJ
                nuevoArchivo = archivoTdrGj
                break;
            case "revisiongdp":
                path = pathBaseArchivos + "${objeto.id}/"
                pathArchivo = objeto.pathRevisionGDP
                nuevoArchivo = archivoTdrGdp
                break;
            case "acta":
                path = pathBaseArchivos + "${objeto.id}/"
                pathArchivo = objeto.pathPdf
                nuevoArchivo = archivoActa
                break;
        }

        /* upload */
        new File(path).mkdirs()
        if (f && !f.empty) {
            if (validateContent(f)) {
                if (pathArchivo) {
                    //si ya existe un archivo lo elimino
                    def oldFile = new File(path + pathArchivo)
                    if (oldFile.exists()) {
                        oldFile.delete()
                    }
                }
                def fileName = f.getOriginalFilename() //nombre original del archivo
                def ext = "pdf"

                def parts = fileName.split("\\.")
                fileName = ""
                parts.eachWithIndex { obj, i ->
                    if (i < parts.size() - 1) {
                        fileName += obj
                    } else {
                        ext = obj
                    }
                }
                def pathFile = path + nuevoArchivo + "." + ext
                try {
                    f.transferTo(new File(pathFile)) // guarda el archivo subido al nuevo path
                } catch (e) {
                    println "????????\n" + e + "\n???????????"
                }

                switch (tipo) {
                    case "tdr":
                        objeto.pathPdfTdr = nuevoArchivo + "." + ext
                        break;
                    case "oferta1":
                        objeto.pathOferta1 = nuevoArchivo + "." + ext
                        break;
                    case "oferta2":
                        objeto.pathOferta2 = nuevoArchivo + "." + ext
                        break;
                    case "oferta3":
                        objeto.pathOferta3 = nuevoArchivo + "." + ext
                        break;
                    case "comparativo":
                        objeto.pathCuadroComparativo = nuevoArchivo + "." + ext
                        break;
                    case "analisis":
                        objeto.pathAnalisisCostos = nuevoArchivo + "." + ext
                        break;
                    case "revisiongaf":
                        objeto.pathRevisionGAF = nuevoArchivo + "." + ext
                        break;
                    case "revisiongj":
                        objeto.pathRevisionGJ = nuevoArchivo + "." + ext
                        break;
                    case "revisiongdp":
                        objeto.pathRevisionGDP = nuevoArchivo + "." + ext
                        break;
                    case "acta":
                        objeto.pathPdf = nuevoArchivo + "." + ext
                        break;
                }
                if (!objeto.save(flush: true)) {
                    println "error al guardar objeto (${tipo}): " + objeto.errors
                }
            }
        }
        /* fin del upload */
    }

    def downloadFile(tipo, objeto) {
        def filename = ""
        String pathBaseArchivos = getPathBase()
        switch (tipo) {
            case "tdr":
                filename = objeto.pathPdfTdr
                break;
            case "oferta1":
                filename = objeto.pathOferta1
                break;
            case "oferta2":
                filename = objeto.pathOferta2
                break;
            case "oferta3":
                filename = objeto.pathOferta3
                break;
            case "comparativo":
                filename = objeto.pathCuadroComparativo
                break;
            case "analisis":
                filename = objeto.pathAnalisisCostos
                break;
            case "revisiongaf":
                filename = objeto.pathRevisionGAF
                break;
            case "revisiongj":
                filename = objeto.pathRevisionGJ
                break;
            case "revisiongdp":
                filename = objeto.pathRevisionGDP
                break;
            case "acta":
                filename = objeto.pathPdf
                break;
        }
        def path = pathBaseArchivos + "${objeto.id}/" + filename
        def tipoArchivo = filename.split("\\.")
        switch (tipoArchivo) {
            case "jpeg":
            case "gif":
            case "jpg":
            case "bmp":
            case "png":
                tipoArchivo = "application/image"
                break;
            case "pdf":
                tipoArchivo = "application/pdf"
                break;
            case "doc":
            case "docx":
            case "odt":
                tipoArchivo = "application/msword"
                break;
            case "xls":
            case "xlsx":
                tipoArchivo = "application/vnd.ms-excel"
                break;
            default:
                tipoArchivo = "application/pdf"
                break;
        }
        try {
            def file = new File(path)
            def b = file.getBytes()
            response.setContentType(tipoArchivo)
            response.setHeader("Content-disposition", "attachment; filename=" + (filename))
            response.setContentLength(b.length)
            response.getOutputStream().write(b)
        } catch (e) {
            println "error en download"
            response.sendError(404)
        }
    }

    def index = {
        redirect(action: 'list')
    }

    def list = {
        def title = g.message(code: "default.list.label", args: ["Solicitud"], default: "Solicitud List")
//        <g:message code="default.list.label" args="[entityName]" />

        params.max = Math.min(params.max ? params.int('max') : 25, 100)

        [solicitudInstanceList: Solicitud.list(params), solicitudInstanceTotal: Solicitud.count(), title: title, params: params]
    }

    def listAprobacion = {
        def title = g.message(code: "default.list.label", args: ["Solicitud"], default: "Solicitud List")
//        <g:message code="default.list.label" args="[entityName]" />

        params.max = Math.min(params.max ? params.int('max') : 25, 100)

        def list = Solicitud.findAllByIncluirReunion("S", params)
        def count = Solicitud.countByIncluirReunion("S")

        [solicitudInstanceList: list, solicitudInstanceTotal: count, title: title, params: params]
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
        }
        if (!solicitud.usuario) {
            solicitud.usuario = usuario
        }
        params.fecha = new Date().parse("dd-MM-yyyy", params.fecha)
        solicitud.properties = params
        if (!solicitud.save(flush: true)) {
            println "error save1 solicitud " + solicitud.errors
        }

        uploadFile("tdr", request.getFile('tdr'), solicitud)
        uploadFile("oferta1", request.getFile('oferta1'), solicitud)
        uploadFile("oferta2", request.getFile('oferta2'), solicitud)
        uploadFile("oferta3", request.getFile('oferta3'), solicitud)
        uploadFile("comparativo", request.getFile('comparativo'), solicitud)
        uploadFile("analisis", request.getFile('analisis'), solicitud)

        if (!solicitud.save(flush: true)) {
            flash.message = "<h5>Ha ocurrido un error al crear la solicitud</h5>" + renderErrors(bean: solicitud)
        } else {
            println solicitud.formaPago
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

    def incluirReunion = {
        def solicitud = Solicitud.get(params.id)
        if (solicitud.incluirReunion != "S") {
            solicitud.incluirReunion = "S"
        } else {
            solicitud.incluirReunion = "N"
        }
        if (!solicitud.save(flush: true)) {
            println "error al incluir/excluir reunion " + solicitud.errors
        }
        redirect(action: "show", id: solicitud.id)
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
        def anio = Anio.findByAnio(new Date().format("yyyy"))
        def asignaciones = Asignacion.countByMarcoLogicoAndAnio(actividad, anio)
        render actividad.objeto + "||" + actividad.monto + "||" + asignaciones
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

        params.monto = params.monto.replaceAll("\\.", "")
        params.monto = params.monto.replaceAll(",", ".")

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
//        actividad.aporte = params.aporte.toDouble()
        actividad.tieneAsignacion = "N"
        if (!actividad.save(flush: true)) {
            println "Error al guardar actividad: " + actividad.errors
        }
        render comboActividades(componente.id, actividad.id, params.width)
    }

    def ingreso = {
        if (session.perfil.codigo == "RQ") {
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
        } else {
            if (params.id) {
                redirect(action: "show", id: params.id)
            } else {
                redirect(action: "list")
            }
        }
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
            uploadFile("revisiongaf", request.getFile('archivogaf'), solicitud)
            uploadFile("revisiongj", request.getFile('archivogj'), solicitud)
            uploadFile("revisiongdp", request.getFile('archivogdp'), solicitud)

            if (params.observacionesgj) {
                solicitud.observacionesJuridica = params.observacionesgj
            }
            if (params.observacionesgaf) {
                solicitud.observacionesAdministrativaFinanciera = params.observacionesgaf
            }
            if (params.observacionesgdp) {
                solicitud.observacionesDireccionProyectos = params.observacionesgdp
            }
            if (params.gj == "on") {
                solicitud.revisadoJuridica = new Date()
            } else {
                if (params["_gj"]) {
                    solicitud.revisadoJuridica = null
                }
            }
            if (params.gaf == "on") {
                solicitud.revisadoAdministrativaFinanciera = new Date()
            } else {
                if (params["_gaf"]) {
                    solicitud.revisadoAdministrativaFinanciera = null
                }
            }
            if (params.gdp == "on") {
                solicitud.revisadoDireccionProyectos = new Date()
            } else {
                if (params["_gdp"]) {
                    solicitud.revisadoDireccionProyectos = null
                }
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
        uploadFile("acta", request.getFile('pdf'), aprobacion)
        redirect(action: "show", id: aprobacion.solicitudId)
    }

    def downloadSolicitud = {
        def solicitud = Solicitud.get(params.id.toLong())
        downloadFile(params.tipo, solicitud)
    }

    def downloadActa = {
        def aprobacion = Aprobacion.get(params.id.toLong())
        downloadFile("acta", aprobacion)
    }
}
