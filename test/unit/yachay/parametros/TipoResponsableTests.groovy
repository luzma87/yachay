package yachay.parametros

import grails.test.*

class TipoResponsableTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {
        def testInstances = []
        mockDomain(TipoResponsable, testInstances)
        def a = new TipoResponsable(codigo: 1, descripcion: "pruebaSave").save(flush: true)
        assertEquals(1,TipoResponsable.count())
    }

    void testUpdate() {
        def testInstances = []
        mockDomain(TipoResponsable, testInstances)
        new TipoResponsable(codigo: 1, descripcion: 'pruebaUpdate').save(flush: true)
        assertEquals(1,TipoResponsable.count())
        def u = TipoResponsable.findByDescripcion('pruebaUpdate')
        u.descripcion ="pruebaSave2"
        u.save(flush: true)
        assertEquals(1,TipoResponsable.count())
        assertEquals('pruebaSave2',u.descripcion)
    }

    void testDelete () {
        def testInstances=[]
        mockDomain(TipoResponsable, testInstances)
        def c = new TipoResponsable(codigo: 1, descripcion:"pruebaDelete")
        c.save(flush: true)
        assertEquals(1,TipoResponsable.count())
        c.delete()
        assertEquals (0,TipoResponsable.count())
    }
}
