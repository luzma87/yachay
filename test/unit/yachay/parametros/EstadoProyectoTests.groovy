package yachay.parametros

import grails.test.*

class EstadoProyectoTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {
        def testInstances = []
        mockDomain(EstadoProyecto, testInstances)
        def s = new EstadoProyecto(descripcion: "EstadoProyectoSave").save(flush: true)
        assertEquals(1,EstadoProyecto.count())
    }

    void testUpdate() {
        def testInstances = []
        mockDomain(EstadoProyecto, testInstances)
        new EstadoProyecto(descripcion: "testUpdate").save(flush: true)
        assertEquals(1,EstadoProyecto.count())
        def s = EstadoProyecto.findByDescripcion('testUpdate')
        s.descripcion ="EstadoProyectoUpdate"
        s.save(flush: true)
        assertEquals(1,EstadoProyecto.count())
        assertEquals('EstadoProyectoUpdate',s.descripcion)
    }

    void testDelete () {
        def testInstances=[]
        mockDomain(EstadoProyecto, testInstances)
        def s = new EstadoProyecto(descripcion: "EstadoProyectoDelete")
        s.save(flush: true)
        assertEquals(1,EstadoProyecto.count())
        s.delete()
        assertEquals (0,EstadoProyecto.count())
    }
}
