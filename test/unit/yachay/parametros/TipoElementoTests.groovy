package yachay.parametros

import grails.test.*

class TipoElementoTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {
        def testInstances = []
        mockDomain(TipoElemento, testInstances)
        def g = new TipoElemento(descripcion: "testElemento").save(flush: true)
        assertEquals(1,TipoElemento.count())
    }

    void testUpdate() {
        def testInstances = []
        mockDomain(TipoElemento, testInstances)
        new TipoElemento(descripcion: "testUpdate").save(flush: true)
        assertEquals(1,TipoElemento.count())
        def g = TipoElemento.findByDescripcion('testUpdate')
        g.descripcion ="elementoUpdate"
        g.save(flush: true)
        assertEquals(1,TipoElemento.count())
        assertEquals('elementoUpdate',g.descripcion)
    }

    void testDelete () {
        def testInstances=[]
        mockDomain(TipoElemento, testInstances)
        def g = new TipoElemento(descripcion: "elementoDelete")
        g.save(flush: true)
        assertEquals(1,TipoElemento.count())
        g.delete()
        assertEquals (0,TipoElemento.count())
    }
}
