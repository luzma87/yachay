package yachay.hitos

import yachay.avales.ProcesoAval
import yachay.parametros.TipoElemento
import yachay.proyectos.MarcoLogico
import yachay.proyectos.Proyecto

class HitoController {

    def crearHito = {

        def hito = null
        if(params.id)
            hito = Hito.get(params.id)

        def proys = Proyecto.list([sort: "nombre"])
        [hito:hito,proyectos:proys]

    }

    def saveHito = {
        def hito
        if(params.id)
            hito = Hito.get(params.id)
        else
            hito = new Hito()
        hito.descripcion = params.descripcion
        if(!hito.fecha)
            hito.fecha=new Date()
        hito.tipo="A"
        if(!hito.save(flush: true)){
            println "error save hito "+hito
        }
        flash.message="Datos guardados"
        redirect(action: "crearHito",id: hito.id)
    }

    def agregarComp = {
        println "agregar comp "+params
        def hito = Hito.get(params.id)
        switch (params.tipo){
            case "proy":
                def comp = new ComposicionHito()
                comp.hito=hito
                comp.proyecto = Proyecto.get(params.componente)
                comp.save(flush: true)
                break;
            case "ml":
                def comp = new ComposicionHito()
                comp.hito=hito
                comp.marcoLogico = MarcoLogico.get(params.componente)
                comp.save(flush: true)
                break;
            case "proc":
                def comp = new ComposicionHito()
                comp.hito=hito
                comp.proceso = ProcesoAval.get(params.componente)
                comp.save(flush: true)
                break;
            default:
                println "wtf"
                break
        }
        redirect(action: "composicion",id: hito.id)

    }
    
    def composicion = {
        def hito = Hito.get(params.id)
        return [comp:ComposicionHito.findAllByHito(hito,[sort: "id"])]
    }

    def componentesProyecto = {

        def proyecto = Proyecto.get(params.id)
        def comps = yachay.proyectos.MarcoLogico.findAllByProyectoAndTipoElemento(proyecto, TipoElemento.get(2))
        [comps:comps]
    }
    def cargarActividades = {

        def comp = MarcoLogico.get(params.id)
        def acts = MarcoLogico.findAllByMarcoLogico(comp)
        [acts:acts]
    }

    def borrarDetalle = {
        def ch = ComposicionHito.get(params.id)
        ch.delete()
        render "ok"
    }


}
