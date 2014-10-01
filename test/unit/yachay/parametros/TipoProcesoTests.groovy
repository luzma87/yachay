package yachay.parametros

import grails.test.*

class TipoProcesoTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {
        def testInstances = []
        mockDomain(TipoProceso, testInstances)
        def a = new TipoProceso(descripcion: "procesoSave").save(flush: true)
        assertEquals(1,TipoProceso.count())
    }

    void testUpdate() {
        def testInstances = []
        mockDomain(TipoProceso, testInstances)
        new TipoProceso(descripcion: "procesoUpdate").save(flush: true)
        assertEquals(1,TipoProceso.count())
        def p = TipoProceso.findByDescripcion('procesoUpdate')
        p.descripcion ="procesoUpdate21"
        p.save(flush: true)
        assertEquals(1,TipoProceso.count())
        assertEquals('procesoUpdate21',p.descripcion)
    }

    void testDelete () {
        def testInstances=[]
        mockDomain(TipoProceso, testInstances)
        def p = new TipoProceso(descripcion: "procesoDelete")
        p.save(flush: true)
        assertEquals(1,TipoProceso.count())
        p.delete()
        assertEquals (0,TipoProceso.count())
    }
}
