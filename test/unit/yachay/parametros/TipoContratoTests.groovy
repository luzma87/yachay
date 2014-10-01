package yachay.parametros

import grails.test.*

class TipoContratoTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {
        def testInstances = []
        mockDomain(TipoContrato, testInstances)
        def g = new TipoContrato(descripcion: "contratoSave").save(flush: true)
        assertEquals(1,TipoContrato.count())
    }

    void testUpdate() {
        def testInstances = []
        mockDomain(TipoContrato, testInstances)
        new TipoContrato(descripcion: "testUpdate").save(flush: true)
        assertEquals(1,TipoContrato.count())
        def g = TipoContrato.findByDescripcion('testUpdate')
        g.descripcion ="contratoUpdate"
        g.save(flush: true)
        assertEquals(1,TipoContrato.count())
        assertEquals('contratoUpdate',g.descripcion)
    }

    void testDelete () {
        def testInstances=[]
        mockDomain(TipoContrato, testInstances)
        def g = new TipoContrato(descripcion: "contratoDelete")
        g.save(flush: true)
        assertEquals(1,TipoContrato.count())
        g.delete()
        assertEquals (0,TipoContrato.count())
    }
}
