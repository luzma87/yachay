package yachay.parametros

import yachay.parametros.geografia.Provincia
import yachay.parametros.TipoInstitucion
import yachay.parametros.proyectos.ObjetivoUnidad

/*Unidades ejecutoras de los proyectos, generalmente adscritas a los ministerios*/
/**
 * Clase para conectar con la tabla 'unej' de la base de datos<br/>
 * Unidades ejecutoras de los proyectos, generalmente adscritas a los ministerios
 */
class UnidadEjecutora implements Serializable {
    /**
     * Tipo de instituci&oacute;n de la unidad ejecutora
     */
    TipoInstitucion tipoInstitucion
    /**
     * Provincia en la cual se encuentra la unidad ejecutora
     */
    Provincia provincia
    /**
     * C&oacute;digo de la unidad ejecutora
     */
    String codigo
    /**
     * Fecha de inicio
     */
    Date fechaInicio
    /**
     * Fecha de fin
     */
    Date fechaFin

    /**
     * Unidad ejecutora padre de la actual
     */
    UnidadEjecutora padre

    /**
     * Nombre de la unidad ejecutora
     */
    String nombre
    /**
     * Direcci&oacute;n de la unidad ejecutora
     */
    String direccion
    /**
     * Sigla de la unidad ejecutora
     */
    String sigla
    /**
     * Objetivo de la unidad ejecutora
     */
    String objetivo
    /**
     * N&uacute;mero de tel&eacute;fono de la unidad ejecutora
     */
    String telefono
    /**
     * N&uacute;mero de fax de la unidad ejecutora
     */
    String fax
    /**
     * Direcci&oacute;n e-mail de la unidad ejecutora
     */
    String email
    /**
     * Observaciones
     */
    String observaciones

    /**
     * Orden para mostrar
     */
    int orden

    /**
     * Objetivo de la unidad
     */
    ObjetivoUnidad objetivoUnidad

    /**
     * Define los campos que se van a ignorar al momento de hacer logs
     */
    static auditable = [ignore: []]

    /**
     * Define el mapeo entre los campos del dominio y las columnas de la base de datos
     */
    static mapping = {
        table 'unej'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'unej__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'unej__id'
            tipoInstitucion column: 'tpin__id'
            provincia column: 'prov__id'
            codigo column: 'unejcdgo'
            fechaInicio column: 'unejfcin'
            fechaFin column: 'unejfcfn'
            padre column: 'unejpdre'

            nombre column: 'unejnmbr'
            direccion column: 'unejdire'
            sigla column: 'unejsgla'
            objetivo column: 'unejobjt'
            telefono column: 'unejtelf'
            fax column: 'unejfaxx'
            email column: 'unejmail'
            observaciones column: 'unejobsr'

            orden column: 'unejordn'

            objetivoUnidad column: 'obun__id'
        }
    }

    /**
     * Define las restricciones de cada uno de los campos
     */
    static constraints = {
        tipoInstitucion(blank: false, nullable: false, attributes: [mensaje: 'Tipo de institución'])
        provincia(blank: true, nullable: true, attributes: [mensaje: 'Provincia de la unidad ejecutora'])
        codigo(size: 1..4, blank: true, nullable: true, attributes: [mensaje: 'Código según el ESIGEF'])
        fechaInicio(blank: true, nullable: true, attributes: [mensaje: 'Fecha de creación'])
        fechaFin(blank: true, nullable: true, attributes: [mensaje: 'Fecha de cierre o final'])
        padre(blank: true, nullable: true, attributes: [mensaje: 'Unidad Ejecutora padre'])

        nombre(size: 1..127, blank: false, attributes: [mensaje: 'Nombre de la entidad o ministerio'])
        direccion(size: 1..127, blank: true, nullable: true, attributes: [mensaje: 'Dirección de la entidad o ministerio'])
        sigla(size: 1..7, blank: true, nullable: true, attributes: [mensaje: 'Sigla identificativa'])
        objetivo(size: 1..1023, blank: true, nullable: true, attributes: [mensaje: 'Objetivo institucional o de la entidad'])
        telefono(size: 1..63, blank: true, nullable: true, attributes: [mensaje: 'Teléfonos, se los separa con “;”'])
        fax(size: 1..63, blank: true, nullable: true, attributes: [mensaje: 'Números de fax, se los separa con “;”'])
        email(size: 1..63, blank: true, nullable: true, attributes: [mensaje: 'Dirección de correo electrónico institucional'])
        observaciones(size: 1..127, blank: true, nullable: true, attributes: [mensaje: 'Observaciones'])

        objetivoUnidad(blank: true, nullable: true, attributes: [mensaje: "Objetivo de la unidad"])
    }

    /**
     * Genera un string para mostrar
     * @return el nombre
     */
    String toString() {
        return this.nombre
    }
}