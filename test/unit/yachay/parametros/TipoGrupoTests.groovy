package yachay.parametros

import grails.test.*

class TipoGrupoTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {
        def testInstances = []
        mockDomain(TipoGrupo, testInstances)
        def g = new TipoGrupo(descripcion: "testGrupoSave").save(flush: true)
        assertEquals(1,TipoGrupo.count())
    }

    void testUpdate() {
        def testInstances = []
        mockDomain(TipoGrupo, testInstances)
        new TipoGrupo(descripcion: "testUpdate").save(flush: true)
        assertEquals(1,TipoGrupo.count())
        def g = TipoGrupo.findByDescripcion('testUpdate')
        g.descripcion ="grupoUpdate"
        g.save(flush: true)
        assertEquals(1,TipoGrupo.count())
        assertEquals('grupoUpdate',g.descripcion)
    }

    void testDelete () {
        def testInstances=[]
        mockDomain(TipoGrupo, testInstances)
        def g = new TipoGrupo(descripcion: "grupoDelete")
        g.save(flush: true)
        assertEquals(1,TipoGrupo.count())
        g.delete()
        assertEquals (0,TipoGrupo.count())
    }
}
