package yachay.proyectos

import yachay.parametros.Sector
import yachay.parametros.SubSector
import yachay.proyectos.Proyecto

/**
 * Clase para conectar con la tabla '' de la base de datos
 */
class Intervencion implements Serializable {
    Proyecto proyecto
    Sector sector
    SubSector subSector
    /**
     * Define los campos que se van a ignorar al momento de hacer logs
     */
    static auditable = [ignore: []]
    /**
     * Define el mapeo entre los campos del dominio y las columnas de la base de datos
     */
    static mapping = {
        table 'intv'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'intv__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'intv__id'
            proyecto column: 'proy__id'
            subSector column: 'sbst__id'
            sector column: 'sctr__id'
        }
    }
    /**
     * Define las restricciones de cada uno de los campos
     */
    static constraints = {
        proyecto(blank: true, nullable: true, attributes: [mensaje: 'Proyecto'])
        subSector(blank: true, nullable: true, attributes: [mensaje: 'Subsector'])
        sector(blank: true, nullable: true, attributes: [mensaje: 'Sector'])
    }
}