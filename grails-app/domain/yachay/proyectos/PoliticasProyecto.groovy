package yachay.proyectos

import yachay.parametros.proyectos.Politica

/*Políticas que se aplican en el proyecto*/
class PoliticasProyecto implements Serializable {
    Politica politica
    Proyecto proyecto
    static auditable = [ignore: []]
    static mapping = {
        table 'plpy'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'plpy__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'plpy__id'
            politica column: 'pltc__id'
            proyecto column: 'proy__id'
        }
    }
    static constraints = {
        politica(blank: true, nullable: true, attributes: [mensaje: 'Política'])
        proyecto(blank: true, nullable: true, attributes: [mensaje: 'Proyecto'])
    }
}