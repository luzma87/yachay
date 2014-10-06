package yachay.proyectos

import grails.test.*

class AvanceTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {
        def testInstance = []
        mockDomain(Avance, testInstance)

        def avc = new Avance(descripcion: 'pruebaAvance').save(flush: true)
        assertEquals(1, avc.count())

    }

    void testUpdate () {
        def testInstance = []
        mockDomain(Avance, testInstance)
        def avc = new Avance(descripcion: 'pruebaAvance').save(flush: true)
        assertEquals(1, avc.count())

        def avc2 = Avance.findByDescripcion('pruebaAvance')
        avc2.descripcion = 'nuevoAvance'
        avc2.save(flush: true)
        assertEquals(1, avc2.count())
        assertEquals('nuevoAvance', avc2.descripcion)

    }

    void testDelete () {

        def testInstance = []
        mockDomain(Avance, testInstance)
        def avc = new Avance(descripcion: 'pruebaAvance').save(flush: true)
        assertEquals(1, avc.count())

       avc.delete()
        assertEquals(0, avc.count())
    }
}
