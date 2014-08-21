package app.pdf

import app.Anio
import app.Asignacion
import app.MarcoLogico
import app.Proceso
import app.Proyecto
import app.TipoElemento
import app.yachai.Aval
import app.yachai.EstadoAval
import app.yachai.ProcesoAsignacion
import app.yachai.ProcesoAval
import app.yachai.SolicitudAval

class AvalesController {

    def procesos = {
        println "procesos "+params
        def proyecto=null
        if(params.proyecto)
            proyecto=Proyecto.get(params.proyecto)
        def procesos = []
        if(proyecto){
            procesos=ProcesoAval.findAllByProyecto(proyecto)
        }else{
            procesos=ProcesoAval.list([sort: "nombre"])
        }
        [proyecto:proyecto,procesos:procesos]
    }

    def crearProceso = {
        def proceso
        def actual
        def band=true
        if (params.anio)
            actual = Anio.get(params.anio)
        else
            actual = Anio.findByAnio(new Date().format("yyyy"))
        if(params.id) {
            proceso = ProcesoAval.get(params.id)
            def aval = Aval.findAllByProceso(proceso)

            aval.each {
                if(it.estado?.codigo=="E01" || it.estado?.codigo=="E02"){
                    band=false
                }
            }
        }

        [proceso:proceso,actual:actual,band:band]
    }

    def getDetalle = {
        def proceso
        def detalle = []
        proceso = ProcesoAval.get(params.id)
        detalle = ProcesoAsignacion.findAllByProceso(proceso,[sort: "id"])
        [proceso:proceso,detalle:detalle]
    }

    def saveProceso = {
        println "save proceso "+params
        params.fechaInicio = new Date().parse("dd-MM-yyyy",params.fechaInicio)
        params.fechaFin = new Date().parse("dd-MM-yyyy",params.fechaFin)
        def proceso
        if(params.id)
            proceso=ProcesoAval.get(params.id)
        else
            proceso=new ProcesoAval()
        proceso.properties=params
        if(!proceso.save(flush:true)) {
            println "error save "+proceso.errors
            flash.message = "Error al guardar el proceso"
        }else {
            flash.message = "Datos guardados"
        }
        redirect(action: "crearProceso",id:proceso.id)
    }


    def agregarAsignacion = {
        println "agregar asg "+params
        def proceso = ProcesoAval.get(params.proceso)
        def asg = Asignacion.get(params.asg)
        def monto = params.monto.toDouble()
        def detalle = new ProcesoAsignacion()
        if(params.id && params.id!="")
            detalle= ProcesoAsignacion.get(params.id)
        detalle.asignacion=asg
        detalle.proceso=proceso
        detalle.monto=monto
        detalle.save(flush: true)
        render "ok"
    }

    def borrarDetalle = {
        println "borrar "+params
        def detalle = ProcesoAsignacion.get(params.id)
        def aval = Aval.findAllByProceso(detalle.proceso)
        def band=true
        aval.each {
            if(it.estado?.codigo=="E01" || it.estado?.codigo=="E02"){
                band=false
            }
        }
        if(band) {
            detalle.delete()
            render "ok"
        }else
            render "no"
    }

    def editarDetalle = {
        println "edtar "+params

        def detalle = ProcesoAsignacion.get(params.id)
        detalle.monto=params.monto.toDouble()
        detalle.save(flush: true)
        render "ok"
    }

    def cargarActividades ={
        def comp = MarcoLogico.get(params.id)
        [acts:MarcoLogico.findAllByMarcoLogico(comp,[sort:"numero"])]
    }
    def cargarAsignaciones ={
        println "cargar asg "+params
        def act =  MarcoLogico.get(params.id)
        def anio = Anio.get(params.anio)
        [asgs:Asignacion.findAllByMarcoLogicoAndAnio(act,anio)]
    }

    def getMaximoAsg={
//        println "get Maximo asg " +params
        def asg = Asignacion.get(params.id)
        def monto = asg.priorizado
        def usado = 0;
        ProcesoAsignacion.findAllByAsignacion(asg).each {
            usado+=it.monto
        }

        render ""+(monto-usado)
    }

    def listaProcesos = {

        def procesos = ProcesoAval.list([sort:"id"])
        [procesos:procesos]

    }

    def avalesProceso = {
        def proceso = ProcesoAval.get(params.id)
        def avales = Aval.findAllByProceso(proceso)
        def solicitudes = SolicitudAval.findAllByProceso(proceso)
        [avales:avales,proceso: proceso,solicitudes:solicitudes]
    }
    def solicitarAval = {
        def proceso = ProcesoAval.get(params.id)
        def now = new Date()
        if(proceso.fechaInicio<now){
            response.sendError(403)
        }
        def estados = [0,1]
        def avales = Aval.findAllByProcesoAndEstadoInList(proceso,[EstadoAval.findByCodigo("E02"),EstadoAval.findByCodigo("E05")])
        def solicitudes = SolicitudAval.findAllByProcesoAndEstado(proceso,EstadoAval.findByCodigo("E01"))
        def disponible = proceso.getMonto()
        avales.each {
            disponible-=it.monto
        }
        solicitudes.each {
            disponible-=it.monto
        }
        [proceso:proceso, disponible:disponible]

    }

    def guardarSolicitud = {
        println "solicitud aval "+params
        /*TODO enviar alertas*/

        def path = servletContext.getRealPath("/") + "pdf/solicitudAval/"
        new File(path).mkdirs()
        def f = request.getFile('file')
        def okContents = [
                'application/pdf': 'pdf',
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
            if(!ext){
                ext="pdf"
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
                sol.proceso=proceso
                sol.usuario=session.usuario
                sol.monto=monto
                sol.concepto=concepto
                sol.memo=momorando
                sol.path=nombre
                sol.fecha = new Date();
                sol.estado=EstadoAval.findByCodigo("E01")
                if(!sol.save(flush: true)){
                    println "eror save "+sol.errors
                }
                flash.message="Solicitud enviada"
                redirect(action: 'avalesProceso',params: [id: params.proceso])
                //println pathFile
            } catch (e) {
                println "????????\n" + e + "\n???????????"
            }


        }else{
            flash.message="Error: Seleccione un archivo valido"
            redirect(action: 'solicitarAval',params: [asg: params.asgn])
        }
        /* fin del upload */





    }

}
