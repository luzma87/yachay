package yachay.proyectos

import yachay.proyectos.Obra

/**
 * Clase para conectar con la tabla '' de la base de datos
 */
class Liquidacion {

    Obra obra
    double valor
    Date fechaRegistro  = new Date()
    Date fechaAdjudicacion
    Date fechaInicio
    Date fechaFin

    /**
     * Define los campos que se van a ignorar al momento de hacer logs
     */
    static auditable=[ignore:[]]
    /**
     * Define el mapeo entre los campos del dominio y las columnas de la base de datos
     */
    static mapping = {
        table 'lqdc'
        cache usage:'read-write', include:'non-lazy'
        id column:'lqdc__id'
        id generator:'identity'
        version false
        columns {
            id column:'lqdc__id'
            obra column: 'obra__id'
            valor column: 'lqdcvlor'
            fechaRegistro column:  'lqdcfcrg'
            fechaAdjudicacion column: 'lqdcfcad'
            fechaInicio column: 'lqdcfcin'
            fechaFin column: 'lqdcfcfn'
        }
    }
    /**
     * Define las restricciones de cada uno de los campos
     */
    static constraints = {
        obra(blank:false,nullable:false)
        valor(blank:false,nullable:false)
        fechaRegistro(blank:true,nullable:true)
        fechaAdjudicacion(blank:false,nullable:false)
        fechaInicio (blank:true,nullable:true)
        fechaFin (blank:true,nullable:true)

    }

    /**
     * Genera un string para mostrar
        * @return
     */
    String toString(){
        "${this.obra} valor: ${this.valor}"
    }
}
