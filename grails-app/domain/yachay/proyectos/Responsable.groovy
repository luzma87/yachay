package yachay.proyectos

import yachay.parametros.UnidadEjecutora
import yachay.parametros.TipoResponsable

/**
 * Clase para conectar con la tabla '' de la base de datos
 */
class Responsable implements Serializable {
    UnidadEjecutora unidadEjecutora
    String nombre
    String apellido
    String cedula
    String email
    String email2
    String telefono
    String cargo
    String observaciones

    TipoResponsable tipo

    /**
     * Define los campos que se van a ignorar al momento de hacer logs
     */
    static auditable = [ignore: []]
    /**
     * Define el mapeo entre los campos del dominio y las columnas de la base de datos
     */
    static mapping = {
        table 'resp'
        cache usage: 'read-write', include: 'non-lazy'
        id column: 'resp__id'
        id generator: 'identity'
        version false
        columns {
            id column: 'resp__id'
            unidadEjecutora column: 'unej__id'
            nombre column: 'respnmbr'
            apellido column: 'respapll'
            cedula column: 'respcdla'
            email column: 'respml01'
            email2 column: 'respml02'
            telefono column: 'resptelf'
            cargo column: 'respcrgo'
            observaciones column: 'respobsr'

            tipo column: 'tprp__id'
        }
    }
    /**
     * Define las restricciones de cada uno de los campos
     */
    static constraints = {
        unidadEjecutora(blank: true, nullable: true, attributes: [mensaje: 'unidadEjecutora'])
        nombre(size: 1..31, blank: false, attributes: [mensaje: 'nombre'])
        apellido(size: 1..31, blank: true, nullable: true, attributes: [mensaje: 'apellido'])
        cedula(size: 1..10, blank: true, nullable: true, attributes: [mensaje: 'cedula'])
        email(size: 1..31, blank: true, nullable: true, attributes: [mensaje: 'email'])
        email2(size: 1..31, blank: true, nullable: true, attributes: [mensaje: 'email2'])
        telefono(size: 1..63, blank: true, nullable: true, attributes: [mensaje: 'telefono'])
        cargo(size: 1..63, blank: true, nullable: true, attributes: [mensaje: 'cargo'])
        observaciones(size: 1..127, blank: true, nullable: true, attributes: [mensaje: 'observaciones'])
        tipo(blank: false, nullable: false, attributes: [mensaje: 'tipo de responsable'])
    }
}