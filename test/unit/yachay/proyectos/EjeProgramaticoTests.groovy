package yachay.proyectos

import grails.test.*

class EjeProgramaticoTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {

        def testInstances = []
        mockDomain(EjeProgramatico, testInstances)

        def ejp = new EjeProgramatico(descripcion: 'pruebaEje').save(flush: true)
        assertEquals(1, ejp.count())

    }

    void testUpdate () {

        def testInstances = []
        mockDomain(EjeProgramatico, testInstances)

        def ejp = new EjeProgramatico(descripcion: 'pruebaEje').save(flush: true)
        assertEquals(1, ejp.count())

        def ejp2 = EjeProgramatico.findByDescripcion('pruebaEje')
        ejp2.descripcion = 'nuevoEje'
        ejp2.save(flush: true)
        assertEquals(1, ejp2.count())
        assertEquals('nuevoEje', ejp2.descripcion)
    }

    void testDelete () {

        def testInstances = []
        mockDomain(EjeProgramatico, testInstances)

        def ejp = new EjeProgramatico(descripcion: 'pruebaEje').save(flush: true)
        assertEquals(1, ejp.count())
        ejp.delete()
        assertEquals(0, ejp.count())

    }
}
