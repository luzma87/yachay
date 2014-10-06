package yachay.proyectos

import grails.test.*

class MarcoLogicoTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {

        def testInstances = []
        mockDomain(MarcoLogico, testInstances)

        def mar = new MarcoLogico(objeto: 'pruebaMar').save(flush: true)
        assertEquals(1,mar.count())

    }

    void testUpdate () {

        def testInstances = []
        mockDomain(MarcoLogico, testInstances)

        def mar = new MarcoLogico(objeto: 'pruebaMar').save(flush: true)
        assertEquals(1,mar.count())

        def mar2 = MarcoLogico.findByObjeto('pruebaMar')
        mar2.objeto = 'nuevoMarcoLogico'
        mar2.save(flush: true)
        assertEquals(1, mar2.count())
        assertEquals('nuevoMarcoLogico', mar2.objeto)
    }

    void testDelete () {

        def testInstances = []
        mockDomain(MarcoLogico, testInstances)

        def mar = new MarcoLogico(objeto: 'pruebaMar').save(flush: true)
        assertEquals(1,mar.count())
        mar.delete()
        assertEquals(0, mar.count())
    }
}
