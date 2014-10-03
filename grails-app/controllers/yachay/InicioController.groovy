package yachay

import org.codehaus.groovy.grails.plugins.qrcode.QrCodeService
import yachay.alertas.Alerta
import yachay.avales.DistribucionAsignacion
import yachay.poa.Asignacion
import yachay.proyectos.ModificacionAsignacion

/**
 * Controlador que muestra la pantalla inicial del sistema y contabiliza alertas
 */
class InicioController extends yachay.seguridad.Shield {


    /**
     * Retorna el valor real de la asignación teniendo en cuenta la reubicación
     * @param aa asignación
     * @return el valor real
     */
    def getValorReal(aa) {
        if (aa.reubicada == "S") {
            if (aa.planificado == 0)
                return aa.planificado
//            println "ASIGNACION aa --> "+aa.id+" val "+aa.planificado
            def dist = DistribucionAsignacion.findAllByAsignacion(aa)

//            def valor = getValorSinModificacion(aa)
            def valor = aa.planificado
//            println "valor inicial "+valor
            Asignacion.findAllByPadreAndUnidadNotEqual(aa, aa.marcoLogico.proyecto.unidadEjecutora, [sort: "id"]).each { hd ->
//                println "hijo directo ------------------>  "+hd.id+" sumando "+hd.planificado
                valor += getValorHijo(hd)
            }
//            println "valor hijos "+valor
//            def vs = getValorSinModificacion(aa)
            def vs = 0
            def mas = ModificacionAsignacion.findAllByDesde(aa)
            def menos = ModificacionAsignacion.findAllByRecibe(aa)

            mas.each {
//                println "asignacion ${aa.id} tienen modificaciones 1 ${it.id}"


                if (it.recibe?.padre?.id == it.desde.id) {
//                    println "sumo"
                    vs += it.valor
                }
//                else
//                    println "no sumo"


            }
//            menos.each {
//                println "asignacion ${aa.id} tienen modificaciones 2 ${it.id}"
//
//                    vs-=it.valor
//                    println "resto"
//
//
//            }
//            println "valor de la original sin mods "+vs
//
//            println "valor total "+(valor+vs)
            valor += vs
            dist.each {
                println "restando distribucion " + it.id + " -->  " + it.valor
                valor = valor - it.valor
            }
//            println "valor "+valor
//            println "-------------------------------"

            if (valor > aa.planificado)
                valor = aa.planificado
            if (valor < 0)
                valor = 0

            return valor
        } else {
            return aa.planificado
        }

    }

    /**
     * Retorna el valor de los hijos de la asignación
     * @param asg asignación
     * @return el valor de los hijos
     */
    def getValorHijo(asg) {
        // println "get valor hijo "+asg.id
        def hijos = Asignacion.findAllByPadre(asg)
        //println "hijos "+hijos
        def val = 0
        hijos.each {
            val += getValorHijo(it)
        }
        // println "return "+(val+asg.planificado)
        val = val + getValorSinModificacion(asg)
//        println "valor hijo "+asg.id+"  --> "+val
//        println ""
        return val
    }

    /**
     * Retorna el valor de la asignación sin modificaciones
     * @param asg asignación
     * @return valor el valor sin modificaciones
     */
    def getValorSinModificacion(asg) {

        def valor = asg.planificado
        def mas = ModificacionAsignacion.findAllByDesde(asg)
        def menos = ModificacionAsignacion.findAllByRecibe(asg)

        mas.each {
//            println "asignacion ${asg.id} tienen modificaciones 1 ${it.id}"


            if (it.recibe?.padre?.id != it.desde.id) {
                println "sumo"
                valor += it.valor
            }
//
//            }else
//                println "no sumo"


        }
        menos.each {
//            println "asignacion ${asg.id} tienen modificaciones 2 ${it.id}"

            if (it.recibe?.padre?.id != it.desde.id && it.desde?.padre?.id != it.recibe.id) {
                valor -= it.valor
            }
//                println "resto"
//            }else
//                println "no sumo"


        }

//        println "valor mods  "+asg.id+"  --> "+valor

        return valor


    }

    /**
     * Acción que muestra la pantalla de inicio del sistema <br/>
     * si no se encuentra la sesión redirecciona a logout
     */
    def index = {

        if (!session.unidad) {
            redirect(controller: "login", action: "logout")
        }
       // firmasService.firmarDocumento(session.usuario.id,"inicio","index","","prueba","123")

//
//        def aa = Asignacion.get(4469)
//        println "get valor real "+getValorReal(aa)
//        def act = TipoElemento.get(3)
//
//        Proyecto.list().eachWithIndex{proy,i->
//            proy.nombre="Proyecto "+i
//            proy.save(flush: true)
//            MarcoLogico.findAllByProyectoAndTipoElemento(proy,act).each {ac->
//                def com =ac.marcoLogico
//                Meta.findAllByMarcoLogico(ac).each{meta->
//                    meta.marcoLogico=com
//                    meta.save(flush: true)
//
//                }
//            }
//
//        }
    }

    /**
     * Acción que muestra la pantalla de alertas con las alertas del usuario que no han sido recibidas
     */
    def mostrarAlertas = {
        //ejemplo de como mandar alertas
        //kerberosService.generarAlerta(session.usuario,finix.seguridad.Usro.get(48),"que eres pal burro","proceso","show",8)
        //        println "alertas "+alertas
        def alertas = Alerta.findAllByUsroAndFec_recibido(session.usuario, null, [sort: "fec_envio"])
        return [alertas: alertas]
    }

    /**
     * Acción que redirecciona a la acción necesaria según la alerta
     */
    def showAlerta = {
        def alerta = Alerta.get(params.id)
        alerta.fec_recibido = new Date()
        alerta.save(flush: true)
        params.id = alerta.id_remoto
        redirect(controller: alerta.controlador, action: alerta.accion, params: params)
    }

    /**
     * Acción que muestra la pantalla de parámetros
     */
    def parametros = {

    }

    /**
     * Acción llamada con ajax que verifica que exista la sesión para saber si redireccionar a logout o no
     * @Renders 'ok' o 'no' según exista o no la sesión
     */
    def verificarSession = {
        println "verificando session "
        if (session.usuario && session.perfil)
            render "ok"
        else
            render "no"
    }

    /**
     * Acción que permite cambiar el color de la aplicación
     * @deprecated la nueva versión ya no permite cambios de color
     */
    @Deprecated
    def cambiarColor = {
        println "cambiar color " + params
        session.color = params.color
        render "ok"
    }
}
