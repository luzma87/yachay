package yachay.poa

import grails.test.*

class ActividadTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {
        def testInstances = []
        mockDomain(Actividad, testInstances)
        def act = new Actividad(descripcion: 'pruebaActividad', codigo: '123').save(flush: true)
        assertEquals(1, act.count())

    }

    void testUpdate () {

        def testInstances = []
        mockDomain(Actividad, testInstances)
        def act = new Actividad(descripcion: 'pruebaActividad', codigo: '123').save(flush: true)
        assertEquals(1, act.count())
        def act2 = Actividad.findByDescripcion('pruebaActividad')
        act2.descripcion = 'nuevaActividad'
        act2.save(flush: true)
        assertEquals(1, act2.count())
        assertEquals('nuevaActividad', act2.descripcion)
    }

    void testDelete () {

        def testInstances = []
        mockDomain(Actividad, testInstances)
        def act = new Actividad(descripcion: 'pruebaActividad', codigo: '123').save(flush: true)
        assertEquals(1, act.count())
        act.delete()
        assertEquals(0, act.count())

    }
}
