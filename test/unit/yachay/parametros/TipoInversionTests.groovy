package yachay.parametros

import grails.test.*

class TipoInversionTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {
        def testInstances = []
        mockDomain(TipoInversion, testInstances)
        def a = new TipoInversion(descripcion: "inversionSave").save(flush: true)
        assertEquals(1,TipoInversion.count())
    }

    void testUpdate() {
        def testInstances = []
        mockDomain(TipoInversion, testInstances)
        new TipoInversion(descripcion: "inversionUpdate").save(flush: true)
        assertEquals(1,TipoInversion.count())
        def p = TipoInversion.findByDescripcion('inversionUpdate')
        p.descripcion ="inversionUpdateAc"
        p.save(flush: true)
        assertEquals(1,TipoInversion.count())
        assertEquals('inversionUpdateAc',p.descripcion)
    }

    void testDelete () {
        def testInstances=[]
        mockDomain(TipoInversion, testInstances)
        def p = new TipoInversion(descripcion: "inversionDelete")
        p.save(flush: true)
        assertEquals(1,TipoInversion.count())
        p.delete()
        assertEquals (0,TipoInversion.count())
    }
}
