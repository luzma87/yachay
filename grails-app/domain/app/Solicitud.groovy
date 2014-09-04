package app

import app.seguridad.Usro
import app.yachai.DetalleMontoSolicitud

class Solicitud {

    Usro usuario
    UnidadEjecutora unidadEjecutora
    MarcoLogico actividad
    Date fecha
    String observaciones
    Date revisadoAdministrativaFinanciera
    Date revisadoJuridica
    Date revisadoDireccionProyectos
    String observacionesAdministrativaFinanciera
    String observacionesJuridica
    String observacionesDireccionProyectos
    String pathPdfTdr

    Double montoSolicitado
    TipoContrato tipoContrato
    String nombreProceso
    String objetoContrato
    String formaPago
    Integer plazoEjecucion

    String estado = "P" // P->pendiente, se puede modificar y revisar
    // A->ya se hizo la reunion de aprobacion, ya no se pueden modificar los datos ni las fechas/observaciones de revision

    String pathOferta1
    String pathOferta2
    String pathOferta3

    String pathCuadroComparativo
    String pathAnalisisCostos

    String incluirReunion

    String pathRevisionGAF
    String pathRevisionGJ
    String pathRevisionGDP

    static hasMany = [detallesMonto: DetalleMontoSolicitud]

    static auditable = [ignore: []]
    static mapping = {
        table 'slct'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'slct__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'slct__id'
            usuario column: 'usro__id'
            unidadEjecutora column: 'unej__id'
            actividad column: 'mrlg__id'
            fecha column: 'slctfcha'
            observaciones column: 'slctobsr'
            revisadoAdministrativaFinanciera column: 'slctrvaf'
            revisadoJuridica column: 'slctrvgj'
            revisadoDireccionProyectos column: 'slctrvdp'
            observacionesAdministrativaFinanciera column: 'slctobaf'
            observacionesJuridica column: 'slctobgj'
            observacionesDireccionProyectos column: 'slctobdp'
            pathPdfTdr column: 'slct_tdr'

            montoSolicitado column: 'slctmnsl'
            tipoContrato column: 'tpcn__id'
            nombreProceso column: 'slctnmpr'
            objetoContrato column: 'slctobcn'
            objetoContrato type: 'text'
            formaPago column: 'slctfrpg'
            plazoEjecucion column: 'slctplej'

            estado column: 'slctetdo'

            pathOferta1 column: 'slctpto1'
            pathOferta2 column: 'slctpto2'
            pathOferta3 column: 'slctpto3'

            pathCuadroComparativo column: 'slctptcc'
            pathAnalisisCostos column: 'slctptac'

            incluirReunion column: 'slctinrn'

            pathRevisionGAF column: 'slctptr1'
            pathRevisionGJ column: 'slctptr2'
            pathRevisionGDP column: 'slctptr3'
        }
    }

    static constraints = {
        observaciones(blank: true, nullable: true, maxSize: 1023)
        revisadoAdministrativaFinanciera(blank: true, nullable: true)
        revisadoJuridica(blank: true, nullable: true)
        revisadoDireccionProyectos(blank: true, nullable: true)
        observacionesAdministrativaFinanciera(blank: true, nullable: true, maxSize: 1023)
        observacionesJuridica(blank: true, nullable: true, maxSize: 1023)
        observacionesDireccionProyectos(blank: true, nullable: true, maxSize: 1023)
        pathPdfTdr(blank: true, nullable: true, maxSize: 255)

        estado(blank: true, nullable: true, inList: ["P", "A"])

        pathOferta1(blank: true, nullable: true)
        pathOferta2(blank: true, nullable: true)
        pathOferta3(blank: true, nullable: true)

        pathCuadroComparativo(blank: true, nullable: true)
        pathAnalisisCostos(blank: true, nullable: true)

        incluirReunion(blank: true, nullable: true)

        pathRevisionGAF(blank: true, nullable: true)
        pathRevisionGJ(blank: true, nullable: true)
        pathRevisionGDP(blank: true, nullable: true)
    }
}
