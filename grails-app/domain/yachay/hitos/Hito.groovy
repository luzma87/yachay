package yachay.hitos

import app.Cuantificable

class Hito {
    /*
     * Descripción
     */
    String descripcion
    /*
    * Fecha de creación
    */
    Date fecha
    /*
   * Fecha de cumplimiento
   */
    Date fechaCumplimiento
    /*
  * Tipo de hito, financiero o físico
  */
    String tipo /* F--> fisico  I--> financiero  */
    /*
   * avance físico
   */
    double avanceFisico = 0
    /*
  * avance financiero
  */
    double avanceFinanciero = 0
    /**
   * Define el mapeo entre los campos del dominio y las columnas de la base de datos
   */
    static mapping = {
        table 'hito'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'hito__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'cmht__id'
            descripcion column: 'hitodscr'
            fecha column: 'hitofcha'
            fechaCumplimiento column: 'hitofccm'
            avanceFisico column: 'hitoavfs'
            avanceFinanciero column: 'hitoavfi'
        }
    }
/**
 * Define las restricciones de cada uno de los campos
 */
    static constraints = {

        fecha(blank: false, nullable: false)
        fechaCumplimiento(blank: true, nullable: true)
        descripcion(blank: false, nullable: false,size: 1..1024)
    }
}
