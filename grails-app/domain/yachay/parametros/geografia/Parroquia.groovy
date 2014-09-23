package yachay.parametros.geografia

class Parroquia implements Serializable {
    Canton canton
    Integer numero
    String nombre
    String codigo

    double longitud
    double latitud

    double diff
    double zoom

    static auditable = [ignore: []]
    static mapping = {
        table 'parr'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'parr__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'parr__id'
            canton column: 'cntn__id'
            numero column: 'parrnmro'
            nombre column: 'parrnmbr'
            codigo column: 'parrcdgo'

            longitud column: 'parrlong'
            latitud column: 'parrlatt'
            diff column: 'parrdiff'
            zoom column: 'parrzoom'
        }
    }
    static constraints = {
        canton(blank: true, nullable: true, attributes: [mensaje: 'Cantón al que pertenece la parroquia'])
        numero(blank: true, nullable: true, attributes: [mensaje: 'Número de la parroquia'])
        nombre(size: 1..63, blank: true, nullable: true, attributes: [mensaje: 'Nombre de la parroquia'])
        codigo(size: 1..6, blank: true, nullable: true, attributes: [mensaje: 'Código de la parroquia'])
    }

    String toString() {
        return this.nombre
    }
}