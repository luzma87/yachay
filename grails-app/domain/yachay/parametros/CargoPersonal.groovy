package yachay.parametros

class CargoPersonal implements Serializable {
    String descripcion
    String codigo
    static auditable = [ignore: []]
    static mapping = {
        table 'cgpr'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'cgpr__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'cgpr__id'
            descripcion column: 'cgprdscr'
            codigo column: 'cgprcdgo'
        }
    }
    static constraints = {
        descripcion(matches: /^[a-zA-Z0-9ñÑ .,áéíóúÁÉÍÚÓüÜ#_-]+$/, size: 1..63, blank: false, attributes: ["mensaje": "Descripción del cargo del personal"])
        codigo(blank: true, nullable: true, maxSize: 4)
    }

    String toString() {
        "${this.descripcion}"
    }
}