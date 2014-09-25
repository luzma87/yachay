package yachay.parametros.proyectos

import yachay.proyectos.Meta

/*Tipo de meta para agregar o agrupar metas.*/

/**
 * Clase para conectar con la tabla 'tpmt' de la base de datos<br/>
 * Tipo de meta para agregar o agrupar metas
 */
class TipoMeta implements Serializable {
    /**
     * Descripci&oacute;n del tipo de meta
     */
    String descripcion

    /**
     * Define los campos que se van a ignorar al momento de hacer logs
     */
    static auditable = [ignore: []]

    /**
     * Define el mapeo entre los campos del dominio y las columnas de la base de datos
     */
    static mapping = {
        table 'tpmt'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'tpmt__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'tpmt__id'
            descripcion column: 'mcacdscr'
        }
    }

    /**
     * Define las restricciones de cada uno de los campos
     */
    static constraints = {
        descripcion(size: 1..63, blank: true, nullable: true, attributes: [mensaje: 'Descripción del tipo de meta'])
    }

    /**
     * Genera un string para mostrar
     * @return la descripci&oacute;n
     */
    String toString() {
        "${this.descripcion}"
    }

    /**
     * Busca las metas de un cierto tipo o que no tengan ubicaci&oacute;n geogr&aacute;fica
     * @return un mapa: [metasCoords: las coordenadas de las metas, metasTotal: las metas]
     */
    def getMetas() {
        def metasCoords = Meta.withCriteria {
            eq('tipoMeta', this)
            or {
                ne('latitud', 0.toDouble())
                ne('longitud', 0.toDouble())
            }
        }
        def metas = Meta.findAllByTipoMeta(this)

        return [metasCoords: metasCoords, metasTotal: metas]
    }

}