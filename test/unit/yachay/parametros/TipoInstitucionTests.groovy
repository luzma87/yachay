package yachay.parametros

import grails.test.*

class TipoInstitucionTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {
        def testInstances = []
        mockDomain(TipoInstitucion, testInstances)
        def a = new TipoInstitucion(codigo: "iS").save(flush: true)
        assertEquals(1,TipoInstitucion.count())
    }

    void testUpdate() {
        def testInstances = []
        mockDomain(TipoInstitucion, testInstances)
        new TipoInstitucion(codigo: "iU").save(flush: true)
        assertEquals(1,TipoInstitucion.count())
        def p = TipoInstitucion.findByCodigo('iU')
        p.codigo ="i2"
        p.save(flush: true)
        assertEquals(1,TipoInstitucion.count())
        assertEquals('i2',p.codigo)
    }

    void testDelete () {
        def testInstances=[]
        mockDomain(TipoInstitucion, testInstances)
        def p = new TipoInstitucion(codigo: "iD")
        p.save(flush: true)
        assertEquals(1,TipoInstitucion.count())
        p.delete()
        assertEquals (0,TipoInstitucion.count())
    }
}
