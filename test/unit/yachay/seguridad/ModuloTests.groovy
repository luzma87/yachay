package yachay.seguridad

import grails.test.*

class ModuloTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {

        def testInstances =[]
        mockDomain(Modulo, testInstances)

        def mdl = new Modulo(nombre: 'nombreModulo', descripcion: 'pruebaModulo').save(flush: true)
        assertEquals(1, mdl.count())

    }

    void testUpdate () {

        def testInstances =[]
        mockDomain(Modulo, testInstances)

        def mdl = new Modulo(nombre: 'nombreModulo', descripcion: 'pruebaModulo').save(flush: true)
        assertEquals(1, mdl.count())

        def mdl2 = Modulo.findByNombre('nombreModulo')
        mdl2.nombre = 'nuevoModulo'
        mdl2.save(flush: true)
        assertEquals(1, mdl2.count())
        assertEquals('nuevoModulo', mdl2.nombre)

    }


    void testDelete () {

        def testInstances =[]
        mockDomain(Modulo, testInstances)

        def mdl = new Modulo(nombre: 'nombreModulo', descripcion: 'pruebaModulo').save(flush: true)
        assertEquals(1, mdl.count())
        mdl.delete()
        assertEquals(0,mdl.count())
    }
}
