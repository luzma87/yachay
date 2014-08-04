package app

class DocumentoProceso {

    Liquidacion liquidacion
    TipoDocumento tipo
    String descripcion
    String clave
    String resumen
    String documento

    static auditable = [ignore: []]
    static mapping = {
        table 'docp'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'docp__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'docp__id'
            liquidacion column: 'lqdc__id'
            tipo column: 'tpdc__id'
            descripcion column: 'dcmtdscr'
            clave column: 'dcmtclve'
            resumen column: 'dcmtrsmn'
            documento column: 'dcmtdcmt'
        }
    }
    static constraints = {
        liquidacion(nullable: false,blank:false)
        descripcion(size: 1..63, blank: true, nullable: true, attributes: [mensaje: 'Descripción del documento'])
        tipo(nullable: false,blank:false, attributes: [mensaje: 'Tipo del documento'])
        clave(size: 1..63, blank: true, nullable: true, attributes: [mensaje: 'Palabras clave'])
        resumen(size: 1..1024, blank: true, nullable: true, attributes: [mensaje: 'Resumen'])
        documento(size: 1..255, blank: true, nullable: true, attributes: [mensaje: 'Ruta del documento'])
    }

    String toString() {
        return this.descripcion
    }
}
