package yachay.proyectos

import grails.test.*

class PoliticaAplicaProyectoTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {

        def testInstances = []
        mockDomain(PoliticaAplicaProyecto, testInstances)

        def pap = new PoliticaAplicaProyecto(descripcion: 'pruebaPAP').save(flush: true)
        assertEquals(1, pap.count())

    }

    void testUpdate () {

        def testInstances = []
        mockDomain(PoliticaAplicaProyecto, testInstances)

        def pap = new PoliticaAplicaProyecto(descripcion: 'pruebaPAP').save(flush: true)
        assertEquals(1, pap.count())

        def pap2 = PoliticaAplicaProyecto.findByDescripcion('pruebaPAP')
        pap2.descripcion = 'nuevaPAP'
        pap2.save(flush: true)
        assertEquals(1, pap2.count())
        assertEquals('nuevaPAP', pap2.descripcion)

    }

    void testDelete () {

        def testInstances = []
        mockDomain(PoliticaAplicaProyecto, testInstances)

        def pap = new PoliticaAplicaProyecto(descripcion: 'pruebaPAP').save(flush: true)
        assertEquals(1, pap.count())
        pap.delete()
        assertEquals(0, pap.count())
    }
}
