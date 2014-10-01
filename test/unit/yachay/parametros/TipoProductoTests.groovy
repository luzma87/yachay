package yachay.parametros

import grails.test.*

class TipoProductoTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {
        def testInstances = []
        mockDomain(TipoProducto, testInstances)
        def a = new TipoProducto(tipoProducto:"ProductoSave").save(flush: true)
        assertEquals(1,TipoProducto.count())
    }

    void testUpdate() {
        def testInstances = []
        mockDomain(TipoProducto, testInstances)
        new TipoProducto(tipoProducto:'productoUpdate').save(flush: true)
        assertEquals(1,TipoProducto.count())
        def p = TipoProducto.findByTipoProducto('productoUpdate')
        p.tipoProducto ="productoUpdate2"
        p.save(flush: true)
        assertEquals(1,TipoProducto.count())
        assertEquals('productoUpdate2',p.tipoProducto)
    }

    void testDelete () {
        def testInstances=[]
        mockDomain(TipoProducto, testInstances)
        def p = new TipoProducto(tipoProducto:"productoDelete")
        p.save(flush: true)
        assertEquals(1,TipoProducto.count())
        p.delete()
        assertEquals (0,TipoProducto.count())
    }
}
