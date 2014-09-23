package yachay.avales

import yachay.parametros.poaPac.Anio
import yachay.poa.Asignacion
import yachay.proyectos.MarcoLogico
import yachay.proyectos.Proyecto
import yachay.parametros.UnidadEjecutora
import yachay.alertas.Alerta
import yachay.seguridad.Usro

class AvalesController extends yachay.seguridad.Shield {

    def procesos = {
        println "procesos " + params
        def proyecto = null
        if (params.proyecto)
            proyecto = Proyecto.get(params.proyecto)
        def procesos = []
        if (proyecto) {
            procesos = ProcesoAval.findAllByProyecto(proyecto)
        } else {
            procesos = ProcesoAval.list([sort: "nombre"])
        }
        [proyecto: proyecto, procesos: procesos]
    }

    def crearProceso = {
        def proceso
        def actual
        def band = true
        def proyectos = []
        def unidad = session.usuario.unidad
        Asignacion.findAllByUnidad(unidad).each {
            def p = it.marcoLogico.proyecto
            if (!proyectos.contains(p)) {
                proyectos.add(p)
            }
        }
        proyectos.sort { it.nombre }
        if (params.anio)
            actual = Anio.get(params.anio)
        else
            actual = Anio.findByAnio(new Date().format("yyyy"))
        if (params.id) {
            proceso = ProcesoAval.get(params.id)
            def aval = Aval.findAllByProceso(proceso)

            aval.each {
                //println "aval "+it.estado.descripcion+"  "+it.estado.codigo
                if (it.estado?.codigo == "E01" || it.estado?.codigo == "E02" || it.estado.codigo == "E05" || it.estado.codigo == "E06") {
                    band = false
                }
            }
        }

        [proyectos: proyectos, proceso: proceso, actual: actual, band: band, unidad: unidad]
    }

    def getDetalle = {
        def proceso
        def detalle = []
        proceso = ProcesoAval.get(params.id)
        detalle = ProcesoAsignacion.findAllByProceso(proceso, [sort: "id"])
        def aval = Aval.findAllByProceso(proceso)
        def band = true
        aval.each {
            //println "aval "+it.estado.descripcion+"  "+it.estado.codigo
            if (it.estado?.codigo == "E01" || it.estado?.codigo == "E02" || it.estado.codigo == "E05" || it.estado.codigo == "E06") {
                band = false
            }
        }
        [proceso: proceso, detalle: detalle, band: band]
    }

    def saveProceso = {
        println "save proceso " + params
        params.fechaInicio = new Date().parse("dd-MM-yyyy", params.fechaInicio)
        params.fechaFin = new Date().parse("dd-MM-yyyy", params.fechaFin)
        def proceso
        if (params.id)
            proceso = ProcesoAval.get(params.id)
        else
            proceso = new ProcesoAval()
        proceso.properties = params
        if (!proceso.save(flush: true)) {
            println "error save " + proceso.errors
            flash.message = "Error al guardar el proceso"
        } else {
            flash.message = "Datos guardados"
        }
        redirect(action: "crearProceso", id: proceso.id)
    }


    def agregarAsignacion = {
        println "agregar asg " + params
        def proceso = ProcesoAval.get(params.proceso)
        def asg = Asignacion.get(params.asg)
        def monto = params.monto.toDouble()
        def detalle = new ProcesoAsignacion()
        if (params.id && params.id != "")
            detalle = ProcesoAsignacion.get(params.id)
        detalle.asignacion = asg
        detalle.proceso = proceso
        detalle.monto = monto
        detalle.save(flush: true)
        render "ok"
    }

    def borrarDetalle = {
        println "borrar " + params
        def detalle = ProcesoAsignacion.get(params.id)
        def aval = Aval.findAllByProceso(detalle.proceso)
        def band = true
        aval.each {
            if (it.estado?.codigo == "E01" || it.estado?.codigo == "E02") {
                band = false
            }
        }
        if (band) {
            detalle.delete()
            render "ok"
        } else
            render "no"
    }

    def editarDetalle = {
        println "edtar " + params

        def detalle = ProcesoAsignacion.get(params.id)
        detalle.monto = params.monto.toDouble()
        detalle.save(flush: true)
        render "ok"
    }

    def cargarActividades = {
        def comp = MarcoLogico.get(params.id)
        def unidad = UnidadEjecutora.get(params.unidad)
        [acts: MarcoLogico.findAllByMarcoLogicoAndResponsable(comp, unidad, [sort: "numero"])]
    }
    def cargarAsignaciones = {
        println "cargar asg " + params
        def act = MarcoLogico.get(params.id)
        def anio = Anio.get(params.anio)
        [asgs: Asignacion.findAllByMarcoLogicoAndAnio(act, anio)]
    }

    def getMaximoAsg = {
//        println "get Maximo asg " +params
        def asg = Asignacion.get(params.id)
        def monto = asg.priorizado
        def usado = 0;
        ProcesoAsignacion.findAllByAsignacion(asg).each {
            usado += it.monto
        }

        render "" + (monto - usado)
    }

    def listaProcesos = {

        def procesos = ProcesoAval.list([sort: "id"])
        [procesos: procesos]

    }

    def avalesProceso = {
        def proceso = ProcesoAval.get(params.id)
        def avales = Aval.findAllByProceso(proceso)
        def solicitudes = SolicitudAval.findAllByProceso(proceso, [sort: "fecha"])
        [avales: avales, proceso: proceso, solicitudes: solicitudes]
    }
    def solicitarAval = {
        println "solicictar aval"
        def unidad = UnidadEjecutora.get(session.unidad.id)
        def personasFirma = Usro.findAllByUnidad(unidad)
        def numero = null
        def band=true
        numero = SolicitudAval.findAllByUnidad(session.usuario.unidad, [sort: "numero", order: "desc", max: 1])
        if (numero.size() > 0) {
            numero = numero?.pop()?.numero
        }
        if (!numero) {
            numero = 1
        } else {
            numero = numero + 1
        }
        def proceso = ProcesoAval.get(params.id)
        def now = new Date()
        if (proceso.fechaInicio < now) {
            flash.message = "Error: El proceso ${proceso.nombre}  (${proceso.fechaInicio.format('dd-MM-yyyy')} - ${proceso.fechaFin.format('dd-MM-yyyy')}) esta en ejecución, si desea solicitar un aval modifique las fechas de inicio y fin"
            redirect(action: "avalesProceso", id: proceso.id)
            return
        }

        def avales = Aval.findAllByProcesoAndEstadoInList(proceso, [EstadoAval.findByCodigo("E02"), EstadoAval.findByCodigo("E05")])
        def solicitudes = SolicitudAval.findAllByProcesoAndEstado(proceso, EstadoAval.findByCodigo("E01"))
        def disponible = proceso.getMonto()
        println "aval "+avales
        println "sols "+solicitudes
        avales.each {
            band=false
            disponible -= it.monto
        }
        solicitudes.each {
            band=false
            disponible -= it.monto
        }
        println "band "+band
        if(!band){
            flash.message="Este proceso ya tiene un aval vigente o tiene una solicitud pendiente, no puede solicitar otro."
            redirect(controller: "avales",action: "avalesProceso",id:proceso?.id)
            return
        }else{
            [proceso: proceso, disponible: disponible, personas: personasFirma, numero: numero]
        }


    }
    def solicitarAnulacion = {

        def aval = Aval.get(params.id)
        [aval: aval]

    }

    def guardarSolicitud = {
        println "solicitud aval " + params
        /*TODO enviar alertas*/

        if (params.monto) {
            params.monto = params.monto.replaceAll("\\.", "")
            params.monto = params.monto.replaceAll(",", ".")
        }

        def path = servletContext.getRealPath("/") + "pdf/solicitudAval/"
        new File(path).mkdirs()
        def f = request.getFile('file')
        def okContents = [
                'application/pdf'     : 'pdf',
                'application/download': 'pdf'
        ]
        def nombre = ""
        def pathFile
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

            ext = okContents[f.getContentType()]
            fileName = fileName.size() < 40 ? fileName : fileName[0..39]
            fileName = fileName.tr(/áéíóúñÑÜüÁÉÍÓÚàèìòùÀÈÌÒÙÇç .!¡¿?&#°"'/, "aeiounNUuAEIOUaeiouAEIOUCc_")
            if (!ext) {
                ext = "pdf"
            }
            nombre = fileName + "." + ext
            pathFile = path + nombre
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
                def proceso = ProcesoAval.get(params.proceso)

                def monto = params.monto
                monto = monto.toDouble()
                def concepto = params.concepto
                def momorando = params.memorando
                def sol = new SolicitudAval()
                sol.proceso = proceso
                if (params.aval)
                    sol.aval = Aval.get(params.aval)
                sol.usuario = session.usuario
                sol.numero = params.numero
                sol.monto = monto
                sol.concepto = concepto
                sol.memo = momorando
                sol.path = nombre
                sol.firma1 = Usro.get(params.firma1)
                sol.unidad = session.usuario.unidad
                if (params.tipo)
                    sol.tipo = params.tipo
                sol.fecha = new Date();
                sol.estado = EstadoAval.findByCodigo("E01")
                if (!sol.save(flush: true)) {
                    println "eror save " + sol.errors
                }
                def usuarios = Usro.findAllByUnidad(UnidadEjecutora.findByCodigo("DPI"))
                usuarios.each { usu ->
                    def alerta = new Alerta()
                    alerta.from = session.usuario
                    alerta.usro = usu
                    alerta.fec_envio = new Date()
                    alerta.mensaje = "Nueva solicitud de aval"
                    alerta.controlador = "revisionAval"
                    alerta.accion = "pendientes"
                    alerta.id_remoto = sol.id
                    if (!alerta.save(flush: true)) {
                        println "error alerta: " + alerta.errors
                    }
                }
                flash.message = "Solicitud enviada"
                redirect(action: 'avalesProceso', params: [id: params.proceso])
                //println pathFile
            } catch (e) {
                println "????????\n" + e + "\n???????????"
            }


        } else {
            flash.message = "Error: Seleccione un archivo valido"
            redirect(action: 'solicitarAval', params: [asg: params.asgn])
        }
        /* fin del upload */
    }
    def descargaSolicitud = {
        def sol = SolicitudAval.get(params.id)
//        println "path solicitud "+cer.pathSolicitud
        def path = servletContext.getRealPath("/") + "pdf/solicitudAval/" + sol.path

        def src = new File(path)
        if (src.exists()) {
            response.setContentType("application/octet-stream")
            response.setHeader("Content-disposition", "attachment;filename=${src.getName()}")

            response.outputStream << src.newInputStream()
        } else {
            render "archivo no encontrado"
        }
    }

    def descargaAval = {
//        println "descar aval "+params
        def aval = Aval.get(params.id)
//        println "path "+aval.path
        def path = servletContext.getRealPath("/") + "avales/" + aval.path

        def src = new File(path)
        if (src.exists()) {
            response.setContentType("application/octet-stream")
            response.setHeader("Content-disposition", "attachment;filename=${src.getName()}")

            response.outputStream << src.newInputStream()
        } else {
            render "archivo no encontrado"
        }
    }


}