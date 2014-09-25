package yachay.proyectos

import yachay.proyectos.Sigef
import yachay.parametros.UnidadEjecutora
import yachay.parametros.poaPac.Fuente
import yachay.parametros.poaPac.Presupuesto
import yachay.parametros.poaPac.ProgramaPresupuestario

/**
 * Clase para conectar con la tabla 'ejun' de la base de datos
 */
class EjecucionUE implements Serializable {
    /**
     * SIGEF
     */
    Sigef sigef
    /**
     * Presupuesto de la ejecuci&oacute;n
     */
    Presupuesto presupuesto
    /**
     * Programa presupuestario de la ejecuci&oacute;n
     */
    ProgramaPresupuestario programa
    /**
     * Fuente de la ejecuci&oacute;n
     */
    Fuente fuente
    /**
     * Proyecto de la ejecuci&oacute;n
     */
    Proyecto proyecto
    /**
     * Unidad ejecutora de la ejecuci&oacute;n
     */
    UnidadEjecutora unidadEjecutora
    /**
     * Valor vigente de la ejecuci&oacute;n
     */
    double vigente = 0
    /**
     * Valor comprometido de la ejecuci&oacute;n
     */
    double comprometido = 0

    /**
     * Define los campos que se van a ignorar al momento de hacer logs
     */
    static auditable = [ignore: []]

    /**
     * Define el mapeo entre los campos del dominio y las columnas de la base de datos
     */
    static mapping = {
        table 'ejun'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'ejun__id'
        id generator: 'identity'
        version false
        columns {
            sief column: 'sigf__id'
            presupuesto column: 'prsp__id'
            programa column: 'pgps__id'
            fuente column: 'fnte__id'
            proyecto column: 'proy__id'
            unidadEjecutora column: 'unej__id'
            vigente column: 'ejunvgnt'
            comprometido column: 'ejuncmps'
        }
    }

    /**
     * Define las restricciones de cada uno de los campos
     */
    static constraints = {
        vigente(nullable: false, blank: false)
        comprometido(nullable: false, blank: false)
    }
}