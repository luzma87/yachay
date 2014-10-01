package yachay.parametros

import grails.test.*

class CalificacionTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {
        def testInstances = []
        mockDomain(Calificacion, testInstances)
        def s = new Calificacion(descripcion: "CalfSave").save(flush: true)
        assertEquals(1,Calificacion.count())
    }

    void testUpdate() {
        def testInstances = []
        mockDomain(Calificacion, testInstances)
        new Calificacion(descripcion: "testUpdate").save(flush: true)
        assertEquals(1,Calificacion.count())
        def s = Calificacion.findByDescripcion('testUpdate')
        s.descripcion ="calfUpdate"
        s.save(flush: true)
        assertEquals(1,Calificacion.count())
        assertEquals('calfUpdate',s.descripcion)
    }

    void testDelete () {
        def testInstances=[]
        mockDomain(Calificacion, testInstances)
        def s = new Calificacion(descripcion: "calfDelete")
        s.save(flush: true)
        assertEquals(1,Calificacion.count())
        s.delete()
        assertEquals (0,Calificacion.count())
    }
}
