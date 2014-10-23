package yachay.modificaciones

import yachay.parametros.TipoElemento
import yachay.parametros.UnidadEjecutora
import yachay.parametros.poaPac.Anio
import yachay.parametros.poaPac.Fuente
import yachay.parametros.poaPac.Presupuesto
import yachay.poa.Asignacion
import yachay.proyectos.MarcoLogico
import yachay.proyectos.Proyecto

class ModificacionesPoaController {

    def index = {}

    def solicitar ={
        def proyectos = []
        def unidad = session.usuario.unidad
        def actual
        Asignacion.findAllByUnidad(unidad).each {
            def p = it.marcoLogico.proyecto
            if (!proyectos.contains(p)) {
                proyectos.add(p)
            }
        }
        if (params.anio)
            actual = Anio.get(params.anio)
        else
            actual = Anio.findByAnio(new Date().format("yyyy"))
        [proyectos:proyectos,actual:actual]
    }


    def componentesProyecto = {
//        println "comp "+params
        def proyecto = Proyecto.get(params.id)
        def comps = yachay.proyectos.MarcoLogico.findAllByProyectoAndTipoElemento(proyecto, TipoElemento.get(2))
        [comps:comps]
    }

    def getValor = {
        def asg = Asignacion.get(params.id)
        def valor = asg.priorizado
        if(!valor)
            valor=0
        render ""+valor
    }

    def cargarActividades = {
        def comp = MarcoLogico.get(params.id)
        def unidad = session.usuario.unidad
        [acts: MarcoLogico.findAllByMarcoLogicoAndResponsable(comp, unidad, [sort: "numero"]),div:params.div,comboId:params.comboId]
    }

    def guardarSolicitudReasignacion = {
        println "params "+params

        def solicitud = new SolicitudModPoa()
        solicitud.concepto=params.concepto
        solicitud.usuario=session.usuario
        solicitud.tipo="R"
        solicitud.origen=Asignacion.get(params.origen)
        solicitud.destino=Asignacion.get(params.destino)
        solicitud.valor=params.monto?.toDouble()
        if(!solicitud.save(flush: true)){
            println "error save sol mod poa "+solicitud.errors
        }
        flash.message="Solicitud enviada"
        render "ok"
    }

    def guardarSolicitudNueva = {
        println "params nueva"+params

        def solicitud = new SolicitudModPoa()
        solicitud.concepto=params.concepto
        solicitud.usuario=session.usuario
        solicitud.tipo="N"
        solicitud.origen=Asignacion.get(params.origen)
        solicitud.fuente=Fuente.get(params.fuente)
        solicitud.marcoLogico = MarcoLogico.get(params.actividad)
        solicitud.presupuesto = Presupuesto.get(params.presupuesto)
        solicitud.valor=params.monto?.toDouble()
        if(!solicitud.save(flush: true)){
            println "error save sol mod poa "+solicitud.errors
        }
        flash.message="Solicitud enviada"
        render "ok"
    }

    def guardarSolicitudDerivada = {
        println "params derivada "+params

        def solicitud = new SolicitudModPoa()
        solicitud.concepto=params.concepto
        solicitud.usuario=session.usuario
        solicitud.tipo="D"
        solicitud.origen=Asignacion.get(params.origen)
        solicitud.valor=params.monto?.toDouble()
        if(!solicitud.save(flush: true)){
            println "error save sol mod poa "+solicitud.errors
        }
        flash.message="Solicitud enviada"
        render "ok"
    }

    def guardarSolicitudEliminar = {
        println "params eliminar "+params

        def solicitud = new SolicitudModPoa()
        solicitud.concepto=params.concepto
        solicitud.usuario=session.usuario
        solicitud.tipo="E"
        solicitud.origen=Asignacion.get(params.origen)
        if(!solicitud.save(flush: true)){
            println "error save sol mod poa "+solicitud.errors
        }
        flash.message="Solicitud enviada"
        render "ok"
    }

    def listaPendientes = {
        def sols = SolicitudModPoa.findAllByEstado(0)
        def historial = SolicitudModPoa.findAllByEstadoNotEqual(0)
        [solicitudes:sols,historial:historial]
    }

    def cargarAsignaciones = {
        def act = MarcoLogico.get(params.id)
        def anio = Anio.get(params.anio)
//        println "asgs "+ Asignacion.findAllByMarcoLogicoAndAnio(act, anio)
        [asgs: Asignacion.findAllByMarcoLogicoAndAnio(act, anio),div:params.div]
    }

    def verSolicitud = {
        def sol = SolicitudModPoa.get(params.id)
        [sol:sol]
    }


    def negar = {
        def sol = SolicitudModPoa.get(params.id)
        sol.estado=2
        sol.fechaRevision=new Date()
        sol.observaciones=params.obs
        sol.revisor=session.usuario
        sol.save(flush: true)
        flash.message="Solicitud negada"

        render "ok"
    }
    def aprobar = {
        def sol = SolicitudModPoa.get(params.id)
        sol.estado=1
        sol.fechaRevision=new Date()
        sol.observaciones=params.obs
        sol.revisor=session.usuario
        sol.save(flush: true)
        flash.message="Solicitud aprobada"
        render "ok"
    }
}
