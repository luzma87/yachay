package yachay.proyectos

import yachay.parametros.UnidadEjecutora
import yachay.parametros.TipoElemento

/*Marco lógico con cada uno de sus componentes */

/**
 * Clase para conectar con la tabla '' de la base de datos
 */
class MarcoLogico implements Serializable {
    Proyecto proyecto
    TipoElemento tipoElemento
    MarcoLogico marcoLogico
    ModificacionProyecto modificacionProyecto
    String objeto
    double monto
    MarcoLogico padreMod
    int estado = 0 /* 0 -> activo por facilidad en la base de datos  1-> modificado*/
    Categoria categoria;
    Date fechaInicio
    Date fechaFin
    UnidadEjecutora responsable
    double aporte = 0;
    String tieneAsignacion = "S" //'S' - > esta en el POA (asignacion), 'N' -> no esta
    /**
     * Define los campos que se van a ignorar al momento de hacer logs
     */
    static auditable = [ignore: []]
    int numero = 0;

    String numeroComp;
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
        * @return
     */
    String toString() {
        if (this.objeto.length() < 40)
            return this.objeto
        else
            return this.objeto.substring(0, 40) + "..."
    }

    /**
     * Genera un string para mostrar
        * @return
     */
    String toStringCompleto() {
        return this.objeto
    }


    double totalMetasAnio(anio) {

        def metas = Meta.findAllByMarcoLogicoAndAnio(this, anio)

        def total = 0
        metas.each { m ->
            total += m.inversion
        }
        return total
    }
}