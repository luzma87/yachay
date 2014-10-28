package yachay.modificaciones

import yachay.parametros.TipoElemento
import yachay.parametros.UnidadEjecutora
import yachay.parametros.poaPac.Anio
import yachay.parametros.poaPac.Fuente
import yachay.parametros.poaPac.Mes
import yachay.parametros.poaPac.Presupuesto
import yachay.poa.Asignacion
import yachay.poa.ProgramacionAsignacion
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
//        println "params "+params

        def solicitud = new SolicitudModPoa()
        solicitud.concepto=params.concepto
        solicitud.usuario=session.usuario
        solicitud.tipo="R"
        solicitud.origen=Asignacion.get(params.origen)
        solicitud.destino=Asignacion.get(params.destino)
        solicitud.valorOrigenSolicitado=solicitud.origen.priorizado
        solicitud.valorDestinoSolicitado=solicitud.destino.priorizado
        solicitud.valor=params.monto?.toDouble()
        if(!solicitud.save(flush: true)){
            println "error save sol mod poa "+solicitud.errors
        }
        flash.message="Solicitud enviada"
        render "ok"
    }

    def guardarSolicitudNueva = {
//        println "params nueva"+params
        def inicio = new Date().parse("dd-MM-yyyy", params.inicio)
        def fin = new Date().parse("dd-MM-yyyy", params.fin)
        def solicitud = new SolicitudModPoa()
        solicitud.concepto=params.concepto
        solicitud.usuario=session.usuario
        solicitud.tipo="N"
        solicitud.inicio = inicio
        solicitud.fin = fin
        solicitud.origen=Asignacion.get(params.origen)
        solicitud.fuente=Fuente.get(params.fuente)
        solicitud.actividad=params.actividad
        solicitud.valorOrigenSolicitado=solicitud.origen.priorizado
        solicitud.valorDestinoSolicitado=0
//        solicitud.marcoLogico = MarcoLogico.get(params.actividad)
        solicitud.presupuesto = Presupuesto.get(params.presupuesto)
        solicitud.valor=params.monto?.toDouble()
        if(!solicitud.save(flush: true)){
            println "error save sol mod poa "+solicitud.errors
        }
        flash.message="Solicitud enviada"
        render "ok"
    }

    def guardarSolicitudDerivada = {
//        println "params derivada "+params

        def solicitud = new SolicitudModPoa()
        solicitud.concepto=params.concepto
        solicitud.usuario=session.usuario
        solicitud.tipo="D"
        solicitud.origen=Asignacion.get(params.origen)
        solicitud.valor=params.monto?.toDouble()
        solicitud.valorOrigenSolicitado=solicitud.origen.priorizado
        solicitud.valorDestinoSolicitado=0
        if(!solicitud.save(flush: true)){
            println "error save sol mod poa "+solicitud.errors
        }
        flash.message="Solicitud enviada"
        render "ok"
    }

    def guardarSolicitudEliminar = {
//        println "params eliminar "+params

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
        def historial = SolicitudModPoa.findAllByEstadoNotEqual(0,[sort: "fechaRevision",order: "desc"])
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
        ejecutarSolicitud(sol)
        flash.message="Solicitud aprobada"
        render "ok"
    }

    def ejecutarSolicitud(SolicitudModPoa solicitud){
        def msg =""
        def origen = solicitud.origen

        if(origen.priorizado==0){
            msg = "No tiene valor priorizado"
            return [false,msg]
        }
        solicitud.valorOrigen = solicitud.origen.priorizado
        println "tipo "+solicitud.tipo
        switch (solicitud.tipo){
            case "R":
                def destino = solicitud.destino
                solicitud.valorDestino=solicitud.destino.priorizado
                if(destino){
                    ProgramacionAsignacion.findAllByAsignacion(origen).each {
                        it.delete()
                    }
                    origen.priorizado -= solicitud.valor
                    origen.save(flush: true)
                    guardarPrasPrio(origen)
                    ProgramacionAsignacion.findAllByAsignacion(destino).each {
                        it.delete()
                    }
                    destino.priorizado+=solicitud.valor
                    destino.save(flush: true)
                    guardarPrasPrio(destino)
                    solicitud.estado=3
                    solicitud.save(flush: true)
                    return [true,""]

                }else{
                    msg = "No hay asignación de destino"
                    return [false,msg]
                }
                break;
            case "N":
                def nueva = new MarcoLogico()
                solicitud.valorDestino=0
                nueva.marcoLogico = origen.marcoLogico.marcoLogico
                nueva.tipoElemento = origen.marcoLogico.tipoElemento
                nueva.monto = solicitud.valor
                nueva.objeto = solicitud.actividad
                nueva.responsable = solicitud.usuario.unidad
                nueva.fechaInicio = solicitud.inicio
                nueva.fechaFin = solicitud.fin
                nueva.proyecto = origen.marcoLogico.proyecto
                nueva.categoria = origen.marcoLogico.categoria
                def maxNum = MarcoLogico.list([sort: "numero", order: "desc", max: 1])?.pop()?.numero
                if (maxNum)
                    maxNum = maxNum + 1
                else
                    maxNum = 1
                nueva.numero = maxNum
                if(!nueva.save(flush: true)){
                    println "error save actividad "+nueva.errors
                    msg = "Error al crear la actividad"
                    return [false,msg]
                }else{
                    println "grabo "+nueva.id
                    nueva.marcoLogico.monto+=solicitud.valor
                    nueva.marcoLogico.save(flush: true)
                    def asg = new Asignacion()
                    asg.marcoLogico=nueva
                    asg.planificado=solicitud.valor
                    asg.priorizado=solicitud.valor
                    asg.anio = origen.anio
                    asg.fuente = solicitud.fuente
                    asg.unidad = nueva.responsable
                    asg.presupuesto = solicitud.presupuesto
                    if(!asg.save(flush: true)){
                        println "error save asg "+asg.errors
                        nueva.delete()
                        msg = "Error al crear la asignación"
                        return [false,msg]
                    }else{
//                        origen.priorizado-=solicitud.valor
//                        origen.save(flush: true)
                        ProgramacionAsignacion.findAllByAsignacion(origen).each {
                            it.delete()
                        }
                        guardarPrasPrio(asg)
                        origen.priorizado -= solicitud.valor
                        origen.save(flush: true)
                        guardarPrasPrio(origen)
                        solicitud.estado=3
                        solicitud.save(flush: true)
                        return [true,""]
                    }

                }

                break;
            case "D":
                def nueva = new Asignacion()
                def valor = solicitud.valor
                solicitud.valorDestino=0
//                def asgn = origen
                def fnte = origen.fuente
                def prsp = origen.presupuesto
                def resultado = 0
                // debe borrar el registro actual de pras y crear uno nuevo con los nuevos valores

                nueva.marcoLogico = origen.marcoLogico
                nueva.programa = origen.programa
                nueva.actividad = origen.actividad
                nueva.anio = origen.anio
                nueva.indicador = origen.indicador
                nueva.meta = origen.meta
                nueva.componente = origen.componente
                nueva.padre = origen
                nueva.fuente = fnte
                nueva.presupuesto = prsp
                nueva.planificado = valor
                nueva.priorizado = valor
                nueva.unidad = origen.unidad
                /*me quede aqui... falta probar*/
//            println "pone padre: ${nueva.padre}  ${nueva.unidad}"
                nueva.save(flush: true)
                if (nueva.errors.getErrorCount() == 0) {
                    println "crea la progrmaación de " + nueva.id
                    guardarPrasPrio(nueva)
                    ProgramacionAsignacion.findAllByAsignacion(origen).each {
                        it.delete()
                    }
                    origen.priorizado -= valor
                    origen.save(flush: true)
                    guardarPrasPrio(origen)
                    solicitud.estado=3
                    solicitud.save(flush: true)
                    return [true,""]
                } else {
                    println "error save asg nueva "+nueva.errors
                    msg = "Error al crear la asignación derivada"
                    return [false,msg]
                }

                break;
            default:
                println "wtf tipo--> "+ solicitud.tipo+"  "+solicitud.id+" "+new Date()
                msg="Error interno"
                return [false,msg]
                break;
        }

    }

    def guardarPrasPrio(asg) {
        if (asg) {
            def total = asg.priorizado
            def valor = (total / 12).toFloat().round(2)
            def residuo = 0
            if (valor * 12 != total) {
                residuo = (total.toDouble() - valor.toDouble() * 12).toFloat().round(2)
            }
            println "total " + total + " valor " + valor + " res " + residuo
//            println "calc "+ (valor.toDouble()*12)
//            println "calc 2 "+ (total-valor*12).toFloat().round(2)

            12.times {
                def mes = Mes.get(it + 1)
                ProgramacionAsignacion.findByAsignacionAndMes(asg, mes)?.delete(flush: true)
                def programacion = new ProgramacionAsignacion()
                programacion.asignacion = asg
                programacion.mes = mes
                if (it < 11) {
                    programacion.valor = valor
                } else {
                    programacion.valor = valor + residuo
                }
                programacion.save(flush: true)
            }
            return asg.id
        } else {
            return 0
        }
    }

}
