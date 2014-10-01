package yachay.parametros

import grails.test.*

class EntidadTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {
        def testInstances = []
        mockDomain(Entidad, testInstances)
        def s = new Entidad(nombre: "EntidadSave").save(flush: true)
        assertEquals(1,Entidad.count())
    }

    void testUpdate() {
        def testInstances = []
        mockDomain(Entidad, testInstances)
        new Entidad(nombre: "testUpdate").save(flush: true)
        assertEquals(1,Entidad.count())
        def s = Entidad.findByNombre('testUpdate')
        s.nombre ="EntidadUpdate"
        s.save(flush: true)
        assertEquals(1,Entidad.count())
        assertEquals('EntidadUpdate',s.nombre)
    }

    void testDelete () {
        def testInstances=[]
        mockDomain(Entidad, testInstances)
        def s = new Entidad(nombre: "EntidadDelete")
        s.save(flush: true)
        assertEquals(1,Entidad.count())
        s.delete()
        assertEquals (0,Entidad.count())
    }
}
