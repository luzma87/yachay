package yachay.contratacion

import yachay.alertas.Alerta
import yachay.contratacion.Aprobacion
import yachay.parametros.UnidadEjecutora
import yachay.parametros.poaPac.Anio
import yachay.parametros.TipoElemento
import yachay.contratacion.Solicitud
import yachay.poa.Asignacion
import yachay.proyectos.MarcoLogico
import yachay.proyectos.Proyecto
import yachay.seguridad.Prfl
import yachay.seguridad.Sesn
import yachay.seguridad.Usro
import yachay.proyectos.Categoria
import yachay.contratacion.DetalleMontoSolicitud

import javax.imageio.ImageIO
import java.awt.image.BufferedImage

import static java.awt.RenderingHints.KEY_INTERPOLATION
import static java.awt.RenderingHints.VALUE_INTERPOLATION_BICUBIC

/**
 * Controlador que muestra las pantallas de manejo de solicitudes de contratación
 */
class SolicitudController extends yachay.seguridad.Shield {

    /**
     * Retorna el path para subir los archivos
     * @return el path para subir los archivos
     */
    def getPathBase() {
        return servletContext.getRealPath("/") + "solicitudes/"
    }

    /**
     * Redimensiona una imagen a un maximo de 800x800 manteniendo el aspec ratio
     * @param f el archivo a ser redimensionado
     * @param pathFile el path del nuevo archivo
     */
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

    /**
     * Valida si un archivo es válido
     * @param f el archivo a validar
     * @return true si el archivo es de un tipo válido
     */
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

    /**
     * Sube un archivo
     * @param tipo el tipo de archivo (tdr, oferta1, oferta2, oferta3, comparativo, analisis, revisiongaf, revisiongj, acta)
     * @param f el archivo a subir
     * @param objeto el objeto en el cual se va a guardar el path del archivo subido
     */
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

    /**
     * Permite descargar un archivo
     * @param tipo el tipo de archivo (tdr, oferta1, oferta2, oferta3, comparativo, analisis, revisiongaf, revisiongj, acta)
     * @param objeto el objeto en el cual está guardado el path del archivo
     */
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

    /**
     * Acción que redirecciona a la acción List
     */
    def index = {
        redirect(action: 'list')
    }

    /*Listado de solicitudes*/
    /**
     * Acción que muestra el listado de solicitudes de contratación
     */
    def list = {

        params.max = Math.min(params.max ? params.int('max') : 25, 100)

//        def unidad = UnidadEjecutora.get(session.unidad.id)

        def c = Solicitud.createCriteria()
        def lista = c.list(params) {
//            eq("unidadEjecutora", unidad)
            if (params.search) {
                ilike("nombreProceso", "%" + params.search + "%")
            }
        }
        def list2 = Solicitud.withCriteria {
//            eq("unidadEjecutora", unidad)
            if (params.search) {
                ilike("nombreProceso", "%" + params.search + "%")
            }
        }

        def title = g.message(code: "default.list.label", args: ["Solicitud"], default: "Solicitud List")
//        <g:message code="default.list.label" args="[entityName]" />

        [solicitudInstanceList: lista, solicitudInstanceTotal: list2.size(), title: title, params: params]
    }

    /**
     * Acción que muestra el listado de solicitudes de contratación listas para ser incluidas en la reunión de aprobación
     */
    def listAprobacion = {
        def title = g.message(code: "default.list.label", args: ["Solicitud"], default: "Solicitud List")
//        <g:message code="default.list.label" args="[entityName]" />

        params.max = Math.min(params.max ? params.int('max') : 25, 100)

        def list = Solicitud.findAllByIncluirReunion("S", params)
        def count = Solicitud.countByIncluirReunion("S")

        [solicitudInstanceList: list, solicitudInstanceTotal: count, title: title, params: params]
    }

    /*Permite ver los datos de la solicitud*/
    /**
     * Acción que muestra una pantalla que permite ver los datos de la solicitud
     */
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

    /*Función para guardar una nueva solicitud*/
    /**
     * Acción que guarda una nueva solicitud
     */
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

    /**
     * Acción que permite incluir o excluir una solicitud de la reunión de aprobación
     */
    def incluirReunion = {
        def solicitud = Solicitud.get(params.id)
        if (solicitud.incluirReunion != "S") {
            solicitud.incluirReunion = "S"
        } else {
            solicitud.incluirReunion = "N"
        }
        solicitud.fechaPeticionReunion = new Date()
        if (!solicitud.save(flush: true)) {
            println "error al incluir/excluir reunion " + solicitud.errors
        }
        redirect(action: "show", id: solicitud.id)
    }

    /**
     * Acción llamada con ajax que genera un combo box con los componentes de un proyecto
     * @Renders el combo box y el código ajax para cargar las actividades
     */
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

    /**
     * Crea un combo box de actividades
     * @param compId el componente padre
     * @param selected el valor seleccionado
     * @param width el ancho del combo box
     * @return
     */
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

    /**
     * Acción llamada con ajax que muestra un combo box de actividades para un cierto componente
     * @param id el id del componente
     * @param val el valor que debe ser seleccionado
     * @param width el ancho del combo box
     * @Returns el combo de actividades
     */
    def getActividadesByComponente = {
        render comboActividades(params.id, params.val, params.width)
    }

    /**
     * Acción llamada con ajax que busca los datos de una actividad
     * @param id el id de la actividad
     * @Renders el objeto de la actividad, el monto, las asignaciones y la duración en días
     */
    def getDatosActividad = {
        def actividad = MarcoLogico.get(params.id.toLong())
        def anio = Anio.findByAnio(new Date().format("yyyy"))
        def asignaciones = Asignacion.countByMarcoLogicoAndAnio(actividad, anio)
        def days = 0
        use(groovy.time.TimeCategory) {
            def duration = actividad.fechaFin - actividad.fechaInicio
            days = duration.days
        }

        render actividad.objeto + "||" + actividad.monto + "||" + asignaciones + "||" + days
    }

    /**
     * Acción llamada con ajax que crea una nueva actividad
     * @params los parámetros enviados por el formulario de cración de actividad
     * @Renders el combo de actividades con la nueva actividad seleccionada
     */
    def newActividad_ajax = {
        def usuario = Usro.get(session.usuario.id)
        def unidadEjecutora = usuario.unidad
        def tipoActividad = TipoElemento.get(3)
        def actividad = new MarcoLogico()

        def proyecto = Proyecto.get(params.proy.toLong())
        def componente = MarcoLogico.get(params.comp.toLong())
        def categoria = null
        if (params.cat.trim() != "") {
            categoria = Categoria.get(params.cat.toLong())
        }
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

    /**
     * Acción llamada con ajax que permite modificar el monto de una solicitud
     * @param id el de la solicitud
     * @param monto el nuevo monto de la solicitud
     * @Renders "OK_"+mensaje en caso de guardar correctamente, "NO_"+mensaje de error en caso contrario
     */
    def cambiarMax = {
        def solicitud = Solicitud.get(params.id)
        def nuevoVal = params.monto.toDouble()

        def msg = ""

        if (nuevoVal > solicitud.actividad.monto) {
            nuevoVal = solicitud.actividad.monto
            msg += "_No puede asignar más de " + formatNumber(number: solicitud.actividad.monto, type: "currency")
        }

        solicitud.montoSolicitado = nuevoVal
        if (solicitud.save(flush: true)) {
            if (msg == "") {
                msg += "_Valor máximo actualizado a " + formatNumber(number: nuevoVal, type: "currency")
            } else {
                msg += ", se ha actualizado al máximo permitido"
            }
            render "OK" + msg
        } else {
            render "NO_" + renderErrors(bean: solicitud)
        }
    }

    /**
     * Acción llamada con ajax que permite actualizar el monto de un detalle de la solicitud
     * @param id el id de la solicitud
     * @param valores los valores separados por ;
     * @Renders "OK_"+montoSolicitado+"_"+montoTotal en caso de guardar correctamente, una lista de los errores en caso contrario
     */
    def updateDetalleMonto_ajax = {
        def solicitud = Solicitud.get(params.id)
        def valores = params.valores
        def errores = ""

        def anios = []
        def total = 0

        (valores.split(";")).each { val ->
            def parts = val.split("_")
            if (parts.size() == 2) {
                def a = parts[0]
                def anio = Anio.findByAnio(a)
                anios += a
                def monto = parts[1].toDouble()
                def detalle = DetalleMontoSolicitud.findByAnioAndSolicitud(anio, solicitud)
                if (!detalle) {
                    detalle = new DetalleMontoSolicitud()
                    detalle.anio = anio
                    detalle.solicitud = solicitud
                }
                detalle.monto = monto
                total += monto
                if (!detalle.save(flush: true)) {
                    errores += "<li>" + renderErrors(bean: detalle) + "</li>"
                }
            }
        }

        def porEliminar = []
        //verifico si la asignacion para algun anio fue eliminada
        DetalleMontoSolicitud.findAllBySolicitud(solicitud).each { d ->
            if (!anios.contains(d.anio.anio)) {
                porEliminar += d.id
            }
        }
        porEliminar.each { id ->
            def det = DetalleMontoSolicitud.get(id)
            det.delete(flush: true)
        }
        if (errores == "") {
            render "OK_" + formatNumber(number: solicitud.montoSolicitado, maxFractionDigits: 2, minFractionDigits: 2) + "_" +
                    formatNumber(number: total, type: "currency")
        } else {
            render "<ul>" + errores + "</ul>"
        }
    }

    /**
     * Acción que muestra los detalles de planificación del monto de una solicitud por año
     * @param id el id de la solicitud
     */
    def detalleMonto = {
        def solicitud = Solicitud.get(params.id)
        def anio = new Date().format("yyyy").toInteger()
//        def anios = Anio.findAllByAnioGreaterThanEquals(anio, [sort: "anio"])
        def anios = []
        Anio.list([sort: "anio"]).each { a ->
            if (a.anio.toInteger() >= anio) {
                anios += a
            }
        }

        return [solicitud: solicitud, anios: anios]
    }

    /**
     * Acción llamada con ajax que agregar un detalle de monto de la solicitud
     * @param id el id de la solicitud
     * @param anio el id del año
     * @param monto el monto
     * @deprecated ahora se utiliza la acción updateDetalleMonto_ajax
     */
    @Deprecated
    def addDetalleMonto = {
        def solicitud = Solicitud.get(params.id)
        def anio = Anio.get(params.anio)
        def monto = params.monto/*.replaceAll("\\.", "")
        monto = monto.replaceAll(",", ".")*/
        monto = monto.toDouble()
        def detalle = DetalleMontoSolicitud.findAllByAnioAndSolicitud(anio, solicitud)
        if (detalle.size() == 1) {
            detalle = detalle.first()
        } else if (detalle.size() > 1) {
            println "hay ${detalle.size()} detalles anio: ${anio.anio} solicitud: ${solicitud.id}: " + detalle.id
            detalle = detalle.first()
        } else {
            detalle = new DetalleMontoSolicitud()
            detalle.solicitud = solicitud
            detalle.anio = anio
        }
        detalle.monto = monto
        if (detalle.save(flush: true)) {
            render "OK"
        } else {
            println "Error al guardar detalle monto solicitud: " + detalle.errors
            render "NO_" + renderErrors(bean: detalle)
        }
    }

    /**
     * Acción que muestra el formulario de creación o edición de una solicitud de contratación<br/>
     * Verifica según el perfil si puede o no ver el formulario. Si no puede, redirecciona a la acción Show o a List
     * dependiendo de si se envía o no el parámetro id
     * @param id el id de la solicitud en caso de que sea edición
     */
    def ingreso = {
        if (session.perfil.codigo == "RQ" || session.perfil.codigo == "DRRQ") {
            def usuario = Usro.get(session.usuario.id)
            def unidadEjecutora = usuario.unidad
            def solicitud = new Solicitud()
            def title = "Nueva"
            def asignado = 0
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

                DetalleMontoSolicitud.findAllBySolicitud(solicitud).each { d ->
                    asignado += d.monto
                }
            }
            title += " solicitud"
            return [unidadRequirente: unidadEjecutora, solicitud: solicitud, title: title,
                    asignado        : formatNumber(number: asignado, type: "currency"), perfil: session.perfil]
        } else {
            if (params.id) {
                redirect(action: "show", id: params.id)
            } else {
                redirect(action: "list")
            }
        }
    }

    /**
     * Acción que muestra la pantalla de revisión de solicitud<br/>
     * Según el perfil del usuario permite o no editar
     * @param id id de la solicitud
     */
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

    /**
     * Acción que guarda la revisión realizada y redirecciona a la acción Show
     * @params los parámetros enviados por el formulario
     */
    def saveRevision = {
        def solicitud = Solicitud.get(params.id)
        if (solicitud) {
            uploadFile("revisiongaf", request.getFile('archivogaf'), solicitud)
            uploadFile("revisiongj", request.getFile('archivogj'), solicitud)
//            uploadFile("revisiongdp", request.getFile('archivogdp'), solicitud)

            if (params.observacionesgj) {
                solicitud.observacionesJuridica = params.observacionesgj
            }
            if (params.observacionesgaf) {
                solicitud.observacionesAdministrativaFinanciera = params.observacionesgaf
            }
//            if (params.observacionesgdp) {
//                solicitud.observacionesDireccionProyectos = params.observacionesgdp
//            }
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
            if (params.vgj == "on") {
                solicitud.validadoJuridica = new Date()
            } else {
                if (params["_vgj"]) {
                    solicitud.validadoJuridica = null
                }
            }
            if (params.vgaf == "on") {
                solicitud.validadoAdministrativaFinanciera = new Date()
            } else {
                if (params["_vgaf"]) {
                    solicitud.validadoAdministrativaFinanciera = null
                }
            }
//            if (params.gdp == "on") {
//                solicitud.revisadoDireccionProyectos = new Date()
//            } else {
//                if (params["_gdp"]) {
//                    solicitud.revisadoDireccionProyectos = null
//                }
//            }

            if (!solicitud.save(flush: true)) {
                println "error al guardar revision: " + solicitud.errors
                flash.message = "Ha ocurrido un error al guardar su revisión."
            }
        }
        redirect(action: "show", id: solicitud.id)
    }

    /**
     * Acción que muestra la pantalla de aprobación de solicitud<br/>
     * Según el perfil del usuario permite o no editar
     * @param id el id de la solicitud
     */
    def aprobacion = {
        def solicitud = Solicitud.get(params.id.toLong())
        def perfil = Prfl.get(session.perfil.id)
        def title = "Aprobar solicitud"
        if (!(solicitud.revisadoJuridica && solicitud.revisadoAdministrativaFinanciera/* && solicitud.revisadoDireccionProyectos*/)) {
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

        def unidadGerenciaPlan = UnidadEjecutora.findByCodigo("DRPL") // GERENCIA DE PLANIFICACIÓN
        def unidadDireccionPlan = UnidadEjecutora.findByCodigo("DPI") // DIRECCIÓN DE PLANIFICACIÓN E INVERSIÓN
        def unidadGerenciaTec = UnidadEjecutora.findByCodigo("GT") // GERENCIA TÉCNICA
        def unidadRequirente = solicitud.unidadEjecutora

        def firmaGerenciaPlanif = Usro.findAllByUnidad(unidadGerenciaPlan)
        def firmaDireccionPlanif = Usro.findAllByUnidad(unidadDireccionPlan)
        def firmaGerenciaTec = Usro.findAllByUnidad(unidadGerenciaTec)
        def firmaRequirente = Usro.findAllByUnidad(unidadRequirente)

        return [solicitud           : solicitud, perfil: perfil, title: title, aprobacion: aprobacion, firmaGerenciaPlanif: firmaGerenciaPlanif,
                firmaDireccionPlanif: firmaDireccionPlanif, firmaGerenciaTec: firmaGerenciaTec, firmaRequirente: firmaRequirente]
    }

    /**
     * Acción que guarda la aprobación realizada y redirecciona a la acción Show
     * @params los parámetros enviados por el formulario
     */
    def saveAprobacion = {
        def aprobacion = new Aprobacion()
        if (params.id) {
            aprobacion = Aprobacion.get(params.id.toLong())
        }
        if (params.fecha) {
            params.fecha = new Date().parse(params.fecha, "dd-MM-yyyy")
        } else {
            params.fecha = new Date()
        }
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

    /**
     * Acción que permite cargar el acta firmada y redirecciona a la acción Show
     * @param id el id de la aprobación
     * @param pdf el request del archivo enviado
     */
    def uploadActa = {
        def aprobacion = Aprobacion.get(params.id.toLong())
        uploadFile("acta", request.getFile('pdf'), aprobacion)
        redirect(action: "show", id: aprobacion.solicitudId)
    }

    /**
     * Acción que permite descargar la solicitud
     * @param id el id de la solicitud
     * @param tipo el tipo de archivo a descargar
     */
    def downloadSolicitud = {
        def solicitud = Solicitud.get(params.id.toLong())
        downloadFile(params.tipo, solicitud)
    }

    /**
     * Acción que permite descargar el acta
     * @param id el id de la solicitud
     * @param tipo el tipo de archivo a descargar
     */
    def downloadActa = {
        def aprobacion = Aprobacion.get(params.id.toLong())
        downloadFile("acta", aprobacion)
    }
}
