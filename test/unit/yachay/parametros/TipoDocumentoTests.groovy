package yachay.parametros

import grails.test.*

class TipoDocumentoTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {
        def testInstances = []
        mockDomain(TipoDocumento, testInstances)
        def g = new TipoDocumento(descripcion: "testDocSave").save(flush: true)
        assertEquals(1,TipoDocumento.count())
    }

    void testUpdate() {
        def testInstances = []
        mockDomain(TipoDocumento, testInstances)
        new TipoDocumento(descripcion: "testUpdate").save(flush: true)
        assertEquals(1,TipoDocumento.count())
        def g = TipoDocumento.findByDescripcion('testUpdate')
        g.descripcion ="docUpdate"
        g.save(flush: true)
        assertEquals(1,TipoDocumento.count())
        assertEquals('docUpdate',g.descripcion)
    }

    void testDelete () {
        def testInstances=[]
        mockDomain(TipoDocumento, testInstances)
        def g = new TipoDocumento(descripcion: "docDelete")
        g.save(flush: true)
        assertEquals(1,TipoDocumento.count())
        g.delete()
        assertEquals (0,TipoDocumento.count())
    }
}
