package yachay.proyectos

import yachay.poa.Asignacion
import yachay.proyectos.Sigef

/*Ejecución de la asignación presupuestaria.*/
/**
 * Clase para conectar con la tabla 'ejec' de la base de datos
 */
class Ejecucion implements Serializable {
    /**
     * Asignaci&oacute;n de la ejecuci&oacute;n
     */
    Asignacion asignacion
    /**
     * SIGEF
     */
    Sigef sigef
    /**
     * Valor vigente de la ejecuci&oacute;n
     */
    double vigente = 0
    /**
     * Valor de compromiso de la ejecuci&oacute;n
     */
    double compromiso = 0
    /**
     * Valor devengado de la ejecuci&oacute;n
     */
    double devengado = 0
    /**
     * Valor pagado de la ejecuci&oacute;n
     */
    double pagado = 0
    /**
     * Saldo de presupuesto de la ejecuci&oacute;n
     */
    double saldoPresupuesto = 0
    /**
     * Saldo disponible de la ejecuci&oacute;n
     */
    double saldoDisponible = 0

    /**
     * Define los campos que se van a ignorar al momento de hacer logs
     */
    static auditable = [ignore: []]

    /**
     * Define el mapeo entre los campos del dominio y las columnas de la base de datos
     */
    static mapping = {
        table 'ejec'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'ejec__id'
        id generator: 'identity'
        version false
        columns {
            asignacion column: 'asgn__id'
            sigef column: 'sigf__id'
            vigente column: 'ejecvgnt'
            compromiso column: 'ejeccmps'
            devengado column: 'ejecdvng'
            pagado column: 'ejecpgdo'
            saldoPresupuesto column: 'ejecslps'
            saldoDisponible column: 'ejecslds'
        }
    }

    /**
     * Define las restricciones de cada uno de los campos
     */
    static constraints = {
//        fecha(blank: true, nullable: true, attributes: [mensaje: 'Fecha de ingreso'])
//        mes(nullable: false, blank: false)
//        anio(nullable: false, blank: false)
        asignacion(nullable: false, blank: false)

    }
}