package yachay.parametros

import grails.test.*

class CoberturaTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {
        def testInstances = []
        mockDomain(Cobertura, testInstances)
        def s = new Cobertura(descripcion: "CoberturaSave").save(flush: true)
        assertEquals(1,Cobertura.count())
    }

    void testUpdate() {
        def testInstances = []
        mockDomain(Cobertura, testInstances)
        new Cobertura(descripcion: "testUpdate").save(flush: true)
        assertEquals(1,Cobertura.count())
        def s = Cobertura.findByDescripcion('testUpdate')
        s.descripcion ="CoberturaUpdate"
        s.save(flush: true)
        assertEquals(1,Cobertura.count())
        assertEquals('CoberturaUpdate',s.descripcion)
    }

    void testDelete () {
        def testInstances=[]
        mockDomain(Cobertura, testInstances)
        def s = new Cobertura(descripcion: "CoberturaDelete")
        s.save(flush: true)
        assertEquals(1,Cobertura.count())
        s.delete()
        assertEquals (0,Cobertura.count())
    }
}
