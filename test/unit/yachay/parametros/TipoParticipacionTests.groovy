package yachay.parametros

import grails.test.*

class TipoParticipacionTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {
        def testInstances = []
        mockDomain(TipoParticipacion, testInstances)
        def a = new TipoParticipacion(descripcion: "participacionSave").save(flush: true)
        assertEquals(1,TipoParticipacion.count())
    }

    void testUpdate() {
        def testInstances = []
        mockDomain(TipoParticipacion, testInstances)
        new TipoParticipacion(descripcion: "participacionUpdate").save(flush: true)
        assertEquals(1,TipoParticipacion.count())
        def p = TipoParticipacion.findByDescripcion('participacionUpdate')
        p.descripcion ="participacionUpdateAc"
        p.save(flush: true)
        assertEquals(1,TipoParticipacion.count())
        assertEquals('participacionUpdateAc',p.descripcion)
    }

    void testDelete () {
        def testInstances=[]
        mockDomain(TipoParticipacion, testInstances)
        def p = new TipoParticipacion(descripcion: "participacionDelete")
        p.save(flush: true)
        assertEquals(1,TipoParticipacion.count())
        p.delete()
        assertEquals (0,TipoParticipacion.count())
    }
}
