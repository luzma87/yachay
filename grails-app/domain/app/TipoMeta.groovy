package app
class TipoMeta implements Serializable {
    String descripcion
    static auditable = [ignore: []]
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
    static constraints = {
        descripcion(size: 1..63, blank: true, nullable: true, attributes: [mensaje: 'Descripción del tipo de meta'])
    }

    String toString() {
        "${this.descripcion}"
    }

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