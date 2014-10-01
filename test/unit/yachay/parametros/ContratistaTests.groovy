package yachay.parametros

import grails.test.*

class ContratistaTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {
        def testInstances = []
        mockDomain(Contratista, testInstances)
        def s = new Contratista(ruc: 0406065172, nombre: "ContratistaSave").save(flush: true)
        assertEquals(1,Contratista.count())
    }

    void testUpdate() {
        def testInstances = []
        mockDomain(Contratista, testInstances)
        new Contratista(ruc: 0406065172, nombre: "testUpdate").save(flush: true)
        assertEquals(1,Contratista.count())
        def s = Contratista.findByNombre('testUpdate')
        s.nombre ="ContratistaUpdate"
        s.save(flush: true)
        assertEquals(1,Contratista.count())
        assertEquals('ContratistaUpdate',s.nombre)
    }

    void testDelete () {
        def testInstances=[]
        mockDomain(Contratista, testInstances)
        def s = new Contratista(ruc: 0406065172, nombre: "ContratistaDelete")
        s.save(flush: true)
        assertEquals(1,Contratista.count())
        s.delete()
        assertEquals (0,Contratista.count())
    }
}
