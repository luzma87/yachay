package yachay.parametros

import grails.test.*

class UnidadEjecutoraTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {
        def testInstances = []
        mockDomain(UnidadEjecutora, testInstances)
        def a = new UnidadEjecutora(tipoInstitucion: new TipoInstitucion(codigo: 123), nombre: "pruebaSave UnidadEjecutora").save(flush: true)
        assertEquals(1,UnidadEjecutora.count())
    }

    void testUpdate() {
        def testInstances = []
        mockDomain(UnidadEjecutora, testInstances)
        new UnidadEjecutora(tipoInstitucion: new TipoInstitucion(codigo: 123), nombre: 'pruebaUpdate UnidadEjecutora').save(flush: true)
        assertEquals(1,UnidadEjecutora.count())
        def c = UnidadEjecutora.findByNombre('pruebaUpdate UnidadEjecutora')
        c.tipoInstitucion = new TipoInstitucion(codigo: 987)
        c.save(flush: true)
        assertEquals(1,UnidadEjecutora.count())
        assertEquals('987',c.tipoInstitucion.codigo)
    }

    void testDelete () {
        def testInstances=[]
        mockDomain(UnidadEjecutora, testInstances)
        def c = new UnidadEjecutora(tipoInstitucion: new TipoInstitucion(codigo: 123), nombre: "pruebaDelete UnidadEjecutora")
        c.save(flush: true)
        assertEquals(1,UnidadEjecutora.count())
        c.delete()
        assertEquals (0,UnidadEjecutora.count())
    }
}
