package yachay.proyectos

import yachay.proyectos.Proyecto

/**
 * Clase para conectar con la tabla '' de la base de datos
 */
class MetaBuenVivirProyecto implements Serializable {
    Proyecto proyecto
    MetaBuenVivir metaBuenVivir
    /**
     * Define los campos que se van a ignorar al momento de hacer logs
     */
    static auditable = [ignore: []]
    /**
     * Define el mapeo entre los campos del dominio y las columnas de la base de datos
     */
    static mapping = {
        table 'mtpy'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'mtpy__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'mtpy__id'
            proyecto column: 'proy__id'
            metaBuenVivir column: 'mtbv__id'
        }
    }
    /**
     * Define las restricciones de cada uno de los campos
     */
    static constraints = {
        proyecto(blank: false, nullable: false, attributes: [mensaje: 'Proyecto'])
        metaBuenVivir(blank: false, nullable: false, attributes: [mensaje: 'Meta de buen vivir'])
    }

    /**
     * Genera un string para mostrar
        * @return
     */
    String toString() {
        return this.proyecto.nombre + " - " + this.metaBuenVivir.descripcion
    }
}
