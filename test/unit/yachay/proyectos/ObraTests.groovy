package yachay.proyectos

import grails.test.*

class ObraTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {

        def testInstances = []
        mockDomain(Obra, testInstances)

        def obr = new Obra(descripcion: 'pruebaObra').save(flush: true)
        assertEquals(1, obr.count())

    }

    void testUpdate () {

        def testInstances = []
        mockDomain(Obra, testInstances)

        def obr = new Obra(descripcion: 'pruebaObra').save(flush: true)
        assertEquals(1, obr.count())

        def obr2 = Obra.findByDescripcion('pruebaObra')
        obr2.descripcion = 'nuevaObra'
        obr2.save(flush: true)
        assertEquals(1, obr2.count())
        assertEquals('nuevaObra', obr2.descripcion)

    }

    void testDelete () {
        def testInstances = []
        mockDomain(Obra, testInstances)
        def obr = new Obra(descripcion: 'pruebaObra').save(flush: true)
        assertEquals(1, obr.count())
        obr.delete()
        assertEquals(0, obr.count())
    }
}
