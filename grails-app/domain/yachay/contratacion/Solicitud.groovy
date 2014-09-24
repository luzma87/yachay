package yachay.contratacion

import yachay.parametros.UnidadEjecutora
import yachay.parametros.TipoContrato
import yachay.proyectos.MarcoLogico
import yachay.seguridad.Usro

/**
 * Clase para conectar con la tabla 'slct' de la base de datos
 */
class Solicitud {
    /**
     * Usuario que genera la solicitud
     */
    Usro usuario
    /**
     * Unidad ejecutora que genera la solicitud
     */
    UnidadEjecutora unidadEjecutora
    /**
     * Actividad para la cual es la solicitud
     */
    MarcoLogico actividad
    /**
     * Fecha de solicitud
     */
    Date fecha
    /**
     * Observaciones de la solicitud
     */
    String observaciones
    /**
     * Fecha de revisi&oacute;n por parte del asistente de la gerencia Administrativa Financiera
     */
    Date revisadoAdministrativaFinanciera       //asistente
    /**
     * Fecha de revisi&oacute;n por parte del asistente de la gerencia Jur&iacute;dica
     */
    Date revisadoJuridica                       //asistente
    /**
     * Fecha de revisi&oacute;n por parte del asistente de la gerencia de Direcci&oacute;n de proyectos
     * @deprecated se pidi&oacute; la eliminaci&oacute;n de este campo
     */
    @Deprecated
    Date revisadoDireccionProyectos             //ya no vale
    /**
     * Fecha de validaci&oacute;n por parte de la gerencia Administrativa Financiera
     */
    Date validadoAdministrativaFinanciera       //gerente
    /**
     * Fecha de validaci&oacute;n por parte de la gerencia Jur&iacute;dica
     */
    Date validadoJuridica                       //gerente
    /**
     * Observaciones por parte del asistente de la gerencia Administrativa Financiera
     */
    String observacionesAdministrativaFinanciera
    /**
     * Observaciones por parte del asistente de la gerencia Jur&iacute;dica
     */
    String observacionesJuridica
    /**
     * Observaciones por parte del asistente de la gerencia de Direcci&oacute;n de proyectos
     * @deprecated se pidi&oacute; la eliminaci&oacute;n de este campo
     */
    @Deprecated
    String observacionesDireccionProyectos
    /**
     * Path del archivo de los t&eacute;rminos de referencia
     */
    String pathPdfTdr

    /**
     * Monto solicitado
     */
    Double montoSolicitado
    /**
     * Tipo de contrato
     */
    TipoContrato tipoContrato
    /**
     * Nombre de proceso
     */
    String nombreProceso
    /**
     * Objeto del contrato
     */
    String objetoContrato
    /**
     * Forma de pago
     */
    String formaPago
    /**
     * Plazo de ejecuci&oacute;n en d&iacute;s
     */
    Integer plazoEjecucion

    /**
     * Estado de la solicitud:<br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;P: pendiente, se puede modificar y revisar
     * &nbsp;&nbsp;&nbsp;&nbsp;A: ya se realiz&oacute; ;a reuni&oacute;n de aprobaci&oacute;n, ya no se pueden modificar los datos ni las fechas/observaciones de revisi&oacute;n
     */
    String estado = "P" // P->pendiente, se puede modificar y revisar
    // A->ya se hizo la reunion de aprobacion, ya no se pueden modificar los datos ni las fechas/observaciones de revision

    /**
     * Path del documento de la oferta 1
     */
    String pathOferta1
    /**
     * Path del documento de la oferta 2
     */
    String pathOferta2
    /**
     * Path del documento de la oferta 3
     */
    String pathOferta3

    /**
     * Path del documento del cuadro comparativo
     */
    String pathCuadroComparativo
    /**
     * Path del documento del &aacute;nalisis de costos
     */
    String pathAnalisisCostos

    /**
     * Indica si se incluir&aacute; o no en la reuni&oacute;n de aprovaci&oacute;n<br/>
     * &nbsp;&nbsp;&nbsp;&nbsp;S: s&iacute;
     */
    String incluirReunion

    /**
     * Path del documento de revisi&oacute;n por parte del asistente de gerencia Administrativa Financiera
     */
    String pathRevisionGAF
    /**
     * Path del documento de revisi&oacute;n por parte del asistente de gerencia Jur&iacute;dica
     */
    String pathRevisionGJ
    /**
     * Path del documento de revisi&oacute;n por parte del asistente de gerencia de Direcci&oacute;n de proyectos
     * @deprecated se pidi&oacute; la eliminaci&oacute;n de este campo
     */
    @Deprecated
    String pathRevisionGDP

    /**
     * Fecha en la que se solicit&oacute; la inclusi&oacute;n en la reuni&oacute;n de aprovaci&oacute;n
     */
    Date fechaPeticionReunion

    /**
     * Define las relaciones uno a varios
     */
    static hasMany = [detallesMonto: DetalleMontoSolicitud]

    /**
     * Define los campos que se van a ignorar al momento de hacer logs
     */
    static auditable = [ignore: []]

    /**
     * Define el mapeo entre los campos del dominio y las columnas de la base de datos
     */
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

            validadoAdministrativaFinanciera column: 'slctvlaf'
            validadoJuridica column: 'slctvljr'

            fechaPeticionReunion column: 'slctfcrn'
        }
    }

    /**
     * Define las restricciones de cada uno de los campos
     */
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

        validadoAdministrativaFinanciera(blank: true, nullable: true)
        validadoJuridica(blank: true, nullable: true)

        fechaPeticionReunion(blank: true, nullable: true)
    }
}
