package yachay.proyectos

import yachay.parametros.proyectos.PoliticaAgendaSocial

/**
 * Clase para conectar con la tabla '' de la base de datos
 */
class PoliticasAgendaProyecto implements Serializable {
    PoliticaAgendaSocial politicaAgendaSocial
    Proyecto proyecto
    /**
     * Define los campos que se van a ignorar al momento de hacer logs
     */
    static auditable = [ignore: []]
    /**
     * Define el mapeo entre los campos del dominio y las columnas de la base de datos
     */
    static mapping = {
        table 'papy'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'papy__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'papy__id'
            politicaAgendaSocial column: 'plas__id'
            proyecto column: 'proy__id'
        }
    }
    /**
     * Define las restricciones de cada uno de los campos
     */
    static constraints = {
        politicaAgendaSocial(blank: true, nullable: true, attributes: [mensaje: 'Política'])
        proyecto(blank: true, nullable: true, attributes: [mensaje: 'Proyecto'])
    }
}
