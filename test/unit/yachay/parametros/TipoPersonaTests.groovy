package yachay.parametros

import grails.test.*

class TipoPersonaTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {
        def testInstances = []
        mockDomain(TipoPersona, testInstances)
        def a = new TipoPersona(descripcion: "personaSave").save(flush: true)
        assertEquals(1,TipoPersona.count())
    }

    void testUpdate() {
        def testInstances = []
        mockDomain(TipoPersona, testInstances)
        new TipoPersona(descripcion: "personaUpdate").save(flush: true)
        assertEquals(1,TipoPersona.count())
        def p = TipoPersona.findByDescripcion('personaUpdate')
        p.descripcion ="personaUpdateAc"
        p.save(flush: true)
        assertEquals(1,TipoPersona.count())
        assertEquals('personaUpdateAc',p.descripcion)
    }

    void testDelete () {
        def testInstances=[]
        mockDomain(TipoPersona, testInstances)
        def p = new TipoPersona(descripcion: "personaDelete")
        p.save(flush: true)
        assertEquals(1,TipoPersona.count())
        p.delete()
        assertEquals (0,TipoPersona.count())
    }
}
