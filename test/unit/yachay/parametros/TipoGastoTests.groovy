package yachay.parametros

import grails.test.*

class TipoGastoTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }
    void testSave() {
        def testInstances = []
        mockDomain(TipoGasto, testInstances)
        def g = new TipoGasto(descripcion: "testGastoSave").save(flush: true)
        assertEquals(1,TipoGasto.count())
    }

    void testUpdate() {
        def testInstances = []
        mockDomain(TipoGasto, testInstances)
        new TipoGasto(descripcion: "testUpdate").save(flush: true)
        assertEquals(1,TipoGasto.count())
        def g = TipoGasto.findByDescripcion('testUpdate')
        g.descripcion ="gastoUpdate"
        g.save(flush: true)
        assertEquals(1,TipoGasto.count())
        assertEquals('gastoUpdate',g.descripcion)
    }

    void testDelete () {
        def testInstances=[]
        mockDomain(TipoGasto, testInstances)
        def g = new TipoGasto(descripcion: "gastoDelete")
        g.save(flush: true)
        assertEquals(1,TipoGasto.count())
        g.delete()
        assertEquals (0,TipoGasto.count())
    }
}
