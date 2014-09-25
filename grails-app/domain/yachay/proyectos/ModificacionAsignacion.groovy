package yachay.proyectos

import yachay.poa.Asignacion
import yachay.parametros.UnidadEjecutora

/*La modificación de la asignación presupuestaria se refiere a la redistribución del valor asignado lo cual implica además una reprogramación.*/
/**
 * Clase para conectar con la tabla '' de la base de datos
 */
class ModificacionAsignacion implements Serializable {
    Asignacion desde
    Asignacion recibe
    ModificacionProyecto modificacionProyecto
    Date fecha
    double valor
    String pdf
    UnidadEjecutora unidad
    /**
     * Define los campos que se van a ignorar al momento de hacer logs
     */
    static auditable=[ignore:[]]
    /**
     * Define el mapeo entre los campos del dominio y las columnas de la base de datos
     */
    static mapping = {
        table 'mdas'
        cache usage:'read-write', include:'non-lazy'
        id column:'mdas__id'
        id generator:'identity'
        version false
        columns {
            id column:'mdas__id'
            desde column: 'asgndsde'
            recibe column: 'asgnrcbe'
            modificacionProyecto column: 'mdfc__id'
            fecha column: 'mdasfcha'
            valor column: 'mdasvlor'
            unidad column: 'unej__id'
        }
    }
    /**
     * Define las restricciones de cada uno de los campos
     */
    static constraints = {
        desde( blank:true, nullable:true ,attributes:[mensaje:'Asignación desde donde sale dinero'])
        recibe( blank:true, nullable:true ,attributes:[mensaje:'Asignación que recibe el dinero'])
        modificacionProyecto( blank:true, nullable:true ,attributes:[mensaje:'Modificación'])
        fecha( blank:true, nullable:true ,attributes:[mensaje:'Fecha'])
        valor( blank:true, nullable:true ,attributes:[mensaje:'Valor redistribuido, siempre en positivo'])
        pdf(blank: true,nullable: true,size: 1..250)
        unidad(blank: true,nullable: true)
    }
}