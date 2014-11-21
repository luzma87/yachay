package yachay.hitos

import yachay.avales.ProcesoAval

class AvanceFinanciero {

    /*
    * Proceso al que pertenece
    */
    ProcesoAval proceso
    /*
    * Número de contrato
    */
    String contrato
    /*
    * Certificado presupuestario
    */
    String certificado
    /*
    * Monto del avance
    */
    Double monto = 0
    /*
    * Fecha de registro
    */
    Date fecha
    /*
    * Observaciones
    */
    String observaciones

    /**
     * Define el mapeo entre los campos del dominio y las columnas de la base de datos
     */
    static mapping = {
        table 'avfi'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'avfi__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'avfi__id'
            fecha column: 'avfifcha'
            proceso column: 'prco__id'
            contrato column: 'avficnto'
            monto column: 'avfimnto'
            observaciones column: 'avfiobsr'
            certificado column: 'avficrtf'
        }
    }
/**
 * Define las restricciones de cada uno de los campos
 */
    static constraints = {
        proceso(nullable: false,blank:false)
        observaciones(blank: true,nullable: true,size: 1..1024)
        contrato(blank: false,nullable: false,size: 1..30)
        certificado(blank: false,nullable: false,size: 1..30)
    }
}
