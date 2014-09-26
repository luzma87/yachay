package yachay.proyectos

/**
 * Clase para conectar con la tabla 'obet' de la base de datos
 */
class ObjetivoEstrategico implements Serializable {
    /**
     * Proyecto del objetivo estrat&eacute;gico
     */
    Proyecto proyecto
    /**
     * Instituci&oacute;n del objetivo estrat&eacute;gico
     */
    String institucion
    /**
     * Objetivo
     */
    String objetivo
    /**
     * Pol&iacute;tica del objetivo estrat&eacute;gico
     */
    String politica
    /**
     * Meta del objetivo estrat&eacute;gico
     */
    String meta

    /**
     * Define los campos que se van a ignorar al momento de hacer logs
     */
    static auditable = [ignore: []]

    /**
     * Define el mapeo entre los campos del dominio y las columnas de la base de datos
     */
    static mapping = {
        table 'obet'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'obet__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'obet__id'
            proyecto column: 'proy__id'
            institucion column: 'obetinst'
            objetivo column: 'obetobjt'
            politica column: 'obetpltc'
            meta column: 'obetmeta'
        }
    }

    /**
     * Define las restricciones de cada uno de los campos
     */
    static constraints = {
        proyecto(blank: true, nullable: true, attributes: [mensaje: 'Proyecto'])
        institucion(size: 1..255, blank: true, nullable: true, attributes: [mensaje: 'Objetivo estratégico institucional'])
        objetivo(size: 1..255, blank: true, nullable: true, attributes: [mensaje: 'Objetivo'])
        politica(size: 1..255, blank: true, nullable: true, attributes: [mensaje: 'Política'])
        meta(size: 1..255, blank: true, nullable: true, attributes: [mensaje: 'Meta'])
    }
}