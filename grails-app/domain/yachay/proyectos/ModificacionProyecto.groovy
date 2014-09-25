package yachay.proyectos

import yachay.proyectos.Informe
import yachay.parametros.UnidadEjecutora
import yachay.parametros.poaPac.Anio
import yachay.parametros.TipoModificacion

/**
 * Clase para conectar con la tabla '' de la base de datos
 */
class ModificacionProyecto implements Serializable {
    Informe informe
    TipoModificacion tipoModificacion
    Proyecto proyecto
    UnidadEjecutora unidad
    double valor
    String descripcion
    Date fecha
    Date fechaAprobacion
    yachay.seguridad.Usro solicitante
    yachay.seguridad.Usro responsable
    int estado = 0 /* 0 -> solicitada | 1-> negada | 2-> aprobada  | 3-> usada*/
    String observaciones
    Anio anio
    /**
     * Define los campos que se van a ignorar al momento de hacer logs
     */
    static auditable = [ignore: []]
    /**
     * Define el mapeo entre los campos del dominio y las columnas de la base de datos
     */
    static mapping = {
        table 'mdfc'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'mdfc__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'mdfc__id'
            informe column: 'info__id'
            tipoModificacion column: 'tpmd__id'
            proyecto column: 'proy__id'
            unidad column: 'unej__id'
            valor column: 'mdfcvlor'
            descripcion column: 'mdfcdscr'
            fecha column: 'mdfcfcha'
            fechaAprobacion column: 'mdfcfcap'
            observaciones column: 'mdfcobsr'
            responsable column: 'usro__id'
            estado  column: 'mdfcetdo'
            anio column: 'anio__id'
            solicitante column: 'usroslct'
        }
    }
    /**
     * Define las restricciones de cada uno de los campos
     */
    static constraints = {
        informe(blank: true, nullable: true, attributes: [mensaje: 'Informe correspondiente que genera la modificación'])
        tipoModificacion(blank: true, nullable: true, attributes: [mensaje: 'Tipo de Modificación'])
        proyecto(blank: true,nullable: true)
        unidad(blank: true,nullable: true)
        valor(blank: true, nullable: true, attributes: [mensaje: 'Valor de la modificación si se trata de modificación en la inversión para actividades del marco lógico'])
        descripcion(size: 1..1023, blank: true, nullable: true, attributes: [mensaje: 'Descripción de la modificación de acuerdo a su tipo'])
        fecha(blank: true, nullable: true, attributes: [mensaje: 'Fecha de la modificación'])
        fechaAprobacion(blank: true, nullable: true, attributes: [mensaje: 'Fecha de aprobación de la modificación'])
        observaciones(size: 1..127, blank: true, nullable: true, attributes: [mensaje: 'Observaciones'])
        estado(nullable: false,blank: false)
        responsable(nullable: true,blank: true)
        solicitante(nullable: true,blank: true)
        anio(nullable: true,blank: true)
    }
}