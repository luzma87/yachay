package yachay.proyectos

import yachay.parametros.UnidadEjecutora
import yachay.parametros.TipoElemento

/*Marco lógico con cada uno de sus componentes */

/**
 * Clase para conectar con la tabla 'mrlg' de la base de datos<br/>
 * Marco lógico con cada uno de sus componentes
 */
class MarcoLogico implements Serializable {
    /**
     * Proyecto del marco l&oacute;gico
     */
    Proyecto proyecto
    /**
     * Tipo de elemento del marco l&oacute;gico
     */
    TipoElemento tipoElemento
    /**
     * Marco l&oacute;gico padre del marco l&oacute;gico actual
     */
    MarcoLogico marcoLogico
    /**
     * Modificaci&oacute;n del marco l&oacute;gico
     */
    ModificacionProyecto modificacionProyecto
    /**
     * Objeto del marco l&oacute;gico
     */
    String objeto
    /**
     * Monto del marco l&oacute;gico
     */
    double monto
    /**
     * Padre de la modificaci&oacute;n del marco l&oacute;gico
     */
    MarcoLogico padreMod
    /**
     * Estado del marco l&oacute;gico (0: activo, 1: modificado)
     */
    int estado = 0 /* 0 -> activo por facilidad en la base de datos  1-> modificado*/
    /**
     * Categor&iacute;a del marco l&oacute;gico
     */
    Categoria categoria;
    /**
     * Fecha de inicio del marco l&oacute;gico
     */
    Date fechaInicio
    /**
     * Fecha de fin del marco l&oacute;gico
     */
    Date fechaFin
    /**
     * Unidad ejecutora responsable del marco l&oacute;gico
     */
    UnidadEjecutora responsable
    /**
     * Valor de aporte del marco l&oacute;gico
     */
    double aporte = 0;
    /**
     * Indica si tiene o no asignaci&oacute;n en el POA (S: s&iacute;, N: no)
     */
    String tieneAsignacion = "S" //'S' - > esta en el POA (asignacion), 'N' -> no esta
    /**
     * N&uacute;mero del marco l&oacute;gico
     */
    int numero = 0;
    /**
     * N&uacute;mero de componente del marco l&oacute;gico
     */
    String numeroComp;

    /**
     * Define los campos que se van a ignorar al momento de hacer logs
     */
    static auditable = [ignore: []]

    /**
     * Define el mapeo entre los campos del dominio y las columnas de la base de datos
     */
    static mapping = {
        table 'mrlg'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'mrlg__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'mrlg__id'
            proyecto column: 'proy__id'
            tipoElemento column: 'tpel__id'
            marcoLogico column: 'mrlgpdre'
            modificacionProyecto column: 'mdfc__id'
            objeto column: 'mrlgobjt'
            monto column: 'mrlgmnto'
            estado column: 'mrlgetdo'
            padreMod column: 'mrlgpdmd'
            categoria column: 'ctgr__id'
            fechaInicio column: 'mrlgfcin'
            fechaFin column: 'mrlgfcfn'
            responsable column: 'unej__id'
            aporte column: 'mrlgaprt'
            tieneAsignacion column: 'mrlgtnas'
            numero column: 'mrlgnmro'
            numeroComp column: 'mrlgnmcm'
        }
    }

    /**
     * Define las restricciones de cada uno de los campos
     */
    static constraints = {
        proyecto(blank: true, nullable: true, attributes: [mensaje: 'Proyecto'])
        tipoElemento(blank: true, nullable: true, attributes: [mensaje: 'Tipo de Elemento'])
        marcoLogico(blank: true, nullable: true, attributes: [mensaje: 'Marco lógico original (elemento) en caso de haber modificaciones'])
        modificacionProyecto(blank: true, nullable: true, attributes: [mensaje: 'Modificación'])
        objeto(size: 1..1023, blank: true, nullable: true, attributes: [mensaje: 'Objetivo, objeto o descripción del elemento'])
        monto(blank: true, nullable: true, attributes: [mensaje: 'Monto o valor planificado, se aplica sólo en actividades'])
        estado(nullable: false, blank: false)
        padreMod(nullable: true, blank: true)
        categoria(nullable: true, blank: true)
        responsable(nullable: true, blank: true)
        fechaFin(nullable: true, blank: true)
        fechaInicio(nullable: true, blank: true)
        tieneAsignacion(nullable: true, blank: true)
        numeroComp(nullable: true, blank: true)
    }

    /**
     * Genera un string para mostrar
     * @return el objeto limitado a 40 caracteres
     */
    String toString() {
        if (this.objeto.length() < 40)
            return this.objeto
        else
            return this.objeto.substring(0, 40) + "..."
    }

    /**
     * Genera un string para mostrar
     * @return el objeto completo
     */
    String toStringCompleto() {
        return this.objeto
    }

    /**
     * Calcula el total de inversi&oacute;n de las metas del marco l&oacute;gico para un a&ntilde;o dado
     * @param anio el a&ntilde;o para el cual se quiere calcular el total de las metas
     * @return el total de inversi&oacute;n de las metas del marco l&oacute;gico para el a&ntilde;o dado
     */
    double totalMetasAnio(anio) {
        def metas = Meta.findAllByMarcoLogicoAndAnio(this, anio)
        def total = 0
        metas.each { m ->
            total += m.inversion
        }
        return total
    }
}