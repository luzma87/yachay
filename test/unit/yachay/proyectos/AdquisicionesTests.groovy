package yachay.proyectos

import grails.test.*

class AdquisicionesTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {

        def testInstance = []
        mockDomain(Adquisiciones, testInstance)
        def adq = new Adquisiciones(descripcion: 'pruebaAdquisiciones').save(flush: true)
        assertEquals(1, adq.count())
     }

    void testUpdate () {

        def testInstance = []
        mockDomain(Adquisiciones, testInstance)
        def adq = new Adquisiciones(descripcion: 'pruebaAdquisiciones').save(flush: true)
        assertEquals(1, adq.count())

        def adq2 = Adquisiciones.findByDescripcion('pruebaAdquisiciones')
        adq2.descripcion='nuevaAdquisiciones'
        adq2.save(flush: true)
        assertEquals(1, adq2.count())
        assertEquals('nuevaAdquisiciones', adq2.descripcion)
    }

    void testDelete () {


        def testInstance = []
        mockDomain(Adquisiciones, testInstance)
        def adq = new Adquisiciones(descripcion: 'pruebaAdquisiciones').save(flush: true)
        assertEquals(1, adq.count())

        adq.delete()
        assertEquals(0, adq.count())

    }
}
