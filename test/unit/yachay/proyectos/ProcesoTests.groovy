package yachay.proyectos

import grails.test.*

class ProcesoTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {
        def testInstances = []
        mockDomain(Proceso, testInstances)
        def prc = new Proceso(nombre: 'pruebaProceso').save(flush: true)
        assertEquals(1, prc.count())
    }

    void testUpdate () {

        def testInstances = []
        mockDomain(Proceso, testInstances)
        def prc = new Proceso(nombre: 'pruebaProceso').save(flush: true)
        assertEquals(1, prc.count())

        def prc2 = Proceso.findByNombre('pruebaProceso')
        prc2.nombre = 'nuevoProceso'
        prc2.save(flush: true)
        assertEquals(1, prc2.count())
        assertEquals('nuevoProceso', prc2.nombre)
    }

    void testDelete () {

        def testInstances = []
        mockDomain(Proceso, testInstances)
        def prc = new Proceso(nombre: 'pruebaProceso').save(flush: true)
        assertEquals(1, prc.count())
        prc.delete()
        assertEquals(0, prc.count())

    }
}
