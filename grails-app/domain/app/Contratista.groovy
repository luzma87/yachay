package app

import org.springframework.context.support.StaticApplicationContext

class Contratista {

    String direccion
    String ruc
    Date fecha
    String nombreCont
    String nombre
    String apellido
    String mail
    String observaciones
    String estado
    String telefono

    static auditable=[ignore:[]]
    static mapping = {

    table 'cntr'

        cache usage:'read-write', include:'non-lazy'
        id column:'cntr__id'
        id generator:'identity'
        version false
        columns {
            id column:'cntr__id'
            ruc column: 'cntr_ruc'
            direccion column: 'cntrdire'
            fecha column: 'cntrfech'
            nombreCont column: 'cntrnbct'
            nombre column: 'cntrnmbr'
            apellido column: 'cntrapll'
            mail column: 'cntrmail'
            observaciones column: 'cntrobsv'
            estado column: 'cntretdo'
            telefono column: 'cntrtlfn'

        }

    }


    static constraints = {

        ruc(size: 1..13, blank: false, nullable: false, attributes: [mensaje: 'Ruc del Contratista'])
        direccion(size: 1..255, blank: true, nullable: true, attributes: [mensaje: 'Direccion del Contratista'])
        fecha(max:  new Date(), blank: true, nullable: true, attributes: [mensaje: 'Fecha'])
        nombreCont(size: 1..63, blank: true, nullable: true, attributes: [mensaje: 'Nombre del Contratista'])
        nombre(size: 1..63, blank: false, nullable: false, attributes: [mensaje: 'Nombre'])
        apellido(size: 1..63, blank: true, nullable: true, attributes: [mensaje: 'Apellido'])
        observaciones(size: 1..255, blank: true, nullable: true, attributes: [mensaje: 'Observaciones'])
        telefono(size: 1..40, blank: true, nullable: true, attributes: [mensaje: 'Telefono del Contratista'])
        mail(size: 1..40, blank: true, nullable: true, attributes: [mensaje: 'Mail'])
        estado(blank: true, nullable: true, attributes: [mensaje: 'Estado del Contratista'])
    }
}
