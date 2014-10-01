package yachay.parametros

import grails.test.*

class LineaTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {
        def testInstances = []
        mockDomain(Linea, testInstances)
        def s = new Linea(descripcion: "LineaSave").save(flush: true)
        assertEquals(1,Linea.count())
    }

    void testUpdate() {
        def testInstances = []
        mockDomain(Linea, testInstances)
        new Linea(descripcion: "testUpdate").save(flush: true)
        assertEquals(1,Linea.count())
        def s = Linea.findByDescripcion('testUpdate')
        s.descripcion ="LineaUpdate"
        s.save(flush: true)
        assertEquals(1,Linea.count())
        assertEquals('LineaUpdate',s.descripcion)
    }

    void testDelete () {
        def testInstances=[]
        mockDomain(Linea, testInstances)
        def s = new Linea(descripcion: "LineaDelete")
        s.save(flush: true)
        assertEquals(1,Linea.count())
        s.delete()
        assertEquals (0,Linea.count())
    }
}
