package yachay.parametros

import grails.test.*

class FaseTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {
        def testInstances = []
        mockDomain(Fase, testInstances)
        def s = new Fase(descripcion: "FaseSave").save(flush: true)
        assertEquals(1,Fase.count())
    }

    void testUpdate() {
        def testInstances = []
        mockDomain(Fase, testInstances)
        new Fase(descripcion: "testUpdate").save(flush: true)
        assertEquals(1,Fase.count())
        def s = Fase.findByDescripcion('testUpdate')
        s.descripcion ="FaseUpdate"
        s.save(flush: true)
        assertEquals(1,Fase.count())
        assertEquals('FaseUpdate',s.descripcion)
    }

    void testDelete () {
        def testInstances=[]
        mockDomain(Fase, testInstances)
        def s = new Fase(descripcion: "FaseDelete")
        s.save(flush: true)
        assertEquals(1,Fase.count())
        s.delete()
        assertEquals (0,Fase.count())
    }
}
