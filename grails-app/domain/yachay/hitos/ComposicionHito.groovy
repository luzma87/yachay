package yachay.hitos

import app.Cuantificable
import yachay.avales.ProcesoAsignacion
import yachay.proyectos.MarcoLogico
import yachay.proyectos.Proyecto

class ComposicionHito  {
    /*
    * Hito
    */
    Hito hito
    /*
    * Proyecto
    */
    Proyecto proyecto
    /*
    * MArco lógico
    */
    MarcoLogico marcoLogico
    /*
    * Proceso
    */
    ProcesoAsignacion proceso
    /*
    * Fecha de creación
    */
    Date fecha = new Date()


    /**
     * Define el mapeo entre los campos del dominio y las columnas de la base de datos
     */
    static mapping = {
        table 'cmht'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'cmht__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'cmht__id'
            hito column: 'hito__id'
            marcoLogico column: 'mrlg__id'
            proyecto column: 'proy__id'
            proceso column: 'poas__id'
            fecha column: 'cmhtfcha'
        }
    }
/**
 * Define las restricciones de cada uno de los campos
 */
    static constraints = {
        hito(blank: false, nullable: false)
        marcoLogico(blank: true, nullable: true)
        proyecto(blank: true, nullable: true)
        proceso(blank: true, nullable: true)
        fecha(blank: false, nullable: false)

    }

}
