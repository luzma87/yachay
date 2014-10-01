package yachay.parametros

import grails.test.*

class TipoAdquisicionTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {
        def testInstances = []
        mockDomain(TipoAdquisicion, testInstances)
        def g = new TipoAdquisicion(descripcion: "adquisicionSave").save(flush: true)
        assertEquals(1,TipoAdquisicion.count())
    }

    void testUpdate() {
        def testInstances = []
        mockDomain(TipoAdquisicion, testInstances)
        new TipoAdquisicion(descripcion: "testUpdate").save(flush: true)
        assertEquals(1,TipoAdquisicion.count())
        def g = TipoAdquisicion.findByDescripcion('testUpdate')
        g.descripcion ="AdquisicionUpdate"
        g.save(flush: true)
        assertEquals(1,TipoAdquisicion.count())
        assertEquals('AdquisicionUpdate',g.descripcion)
    }

    void testDelete () {
        def testInstances=[]
        mockDomain(TipoAdquisicion, testInstances)
        def g = new TipoAdquisicion(descripcion: "AdquisicionDelete")
        g.save(flush: true)
        assertEquals(1,TipoAdquisicion.count())
        g.delete()
        assertEquals (0,TipoAdquisicion.count())
    }
}
