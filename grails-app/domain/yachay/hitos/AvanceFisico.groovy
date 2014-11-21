package yachay.hitos

import yachay.avales.ProcesoAval

class AvanceFisico {

    /*
    * Proceso
    */
    ProcesoAval proceso
    /*
    * Fecha de registro
    */
    Date fecha = new Date()
    /*
    * Avance
    */
    Double avance = 0
    /*
   * Observaciones
   */
    String observaciones
    /*
    * Fecha en la que se completo la actividad
    */
    Date completado

    /**
     * Define el mapeo entre los campos del dominio y las columnas de la base de datos
     */
    static mapping = {
        table 'avfs'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'avfs__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'avfs__id'
            fecha column: 'avfsfcha'
            proceso column: 'prco__id'
            avance column: 'avfsavnc'
            observaciones column: 'avfsobsv'
            completado column: 'avfsfccm'
        }
    }
/**
 * Define las restricciones de cada uno de los campos
 */
    static constraints = {
        proceso(nullable: false,blank:false)
        observaciones(blank: true,nullable: true,size: 1..1024)
        completado(blank:true,nullable: true)
    }
}
