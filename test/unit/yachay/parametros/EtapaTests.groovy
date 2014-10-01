package yachay.parametros

import grails.test.*

class EtapaTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }


    void testSave() {
        def testInstances = []
        mockDomain(Etapa, testInstances)
        def s = new Etapa(descripcion: "EtapaSave").save(flush: true)
        assertEquals(1,Etapa.count())
    }

    void testUpdate() {
        def testInstances = []
        mockDomain(Etapa, testInstances)
        new Etapa(descripcion: "testUpdate").save(flush: true)
        assertEquals(1,Etapa.count())
        def s = Etapa.findByDescripcion('testUpdate')
        s.descripcion ="EtapaUpdate"
        s.save(flush: true)
        assertEquals(1,Etapa.count())
        assertEquals('EtapaUpdate',s.descripcion)
    }

    void testDelete () {
        def testInstances=[]
        mockDomain(Etapa, testInstances)
        def s = new Etapa(descripcion: "EtapaDelete")
        s.save(flush: true)
        assertEquals(1,Etapa.count())
        s.delete()
        assertEquals (0,Etapa.count())
    }
}
