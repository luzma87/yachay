package yachay.parametros

import grails.test.*

class TipoProcedenciaTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {
        def testInstances = []
        mockDomain(TipoProcedencia, testInstances)
        def a = new TipoProcedencia(descripcion: "procedenciaSave").save(flush: true)
        assertEquals(1,TipoProcedencia.count())
    }

    void testUpdate() {
        def testInstances = []
        mockDomain(TipoProcedencia, testInstances)
        new TipoProcedencia(descripcion: "procedenciaUpdate").save(flush: true)
        assertEquals(1,TipoProcedencia.count())
        def p = TipoProcedencia.findByDescripcion('procedenciaUpdate')
        p.descripcion ="procedenciaUpdate21"
        p.save(flush: true)
        assertEquals(1,TipoProcedencia.count())
        assertEquals('procedenciaUpdate21',p.descripcion)
    }

    void testDelete () {
        def testInstances=[]
        mockDomain(TipoProcedencia, testInstances)
        def p = new TipoProcedencia(descripcion: "procedenciaDelete")
        p.save(flush: true)
        assertEquals(1,TipoProcedencia.count())
        p.delete()
        assertEquals (0,TipoProcedencia.count())
    }
}
