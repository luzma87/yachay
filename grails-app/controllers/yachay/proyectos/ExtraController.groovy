package yachay.proyectos

import groovy.io.FileType
import yachay.proyectos.Proyecto

/**
 * Controlador
 */
class ExtraController {

    /**
     * Acción
     */
    def index = {}

    /**
     * Acción
     */
    def extra = {

        def proys = Proyecto.list()
//        println proys
        [proys: proys]

    }

}
