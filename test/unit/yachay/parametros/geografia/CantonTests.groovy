package yachay.parametros.geografia

import grails.test.*

class CantonTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave () {
        def testInstances = []
        mockDomain(Canton, testInstances)
        new Canton(nombre: "pruebaSave Canton").save(flush: true)
        assertEquals(1,Canton.count())
    }

    void testUpdate() {
        def testInstances = []
        mockDomain(Canton, testInstances)
        new Canton(nombre: 'pruebaUpdate Canton').save(flush: true)
        assertEquals(1,Canton.count())
        def c = Canton.findByNombre('pruebaUpdate Canton')
        c.numero = 12
        c.nombre ="nuevoCanton"
        c.save(flush: true)
        assertEquals(1,Canton.count())
        assertEquals("nuevoCanton",c.nombre)
        assertEquals(12, c.numero);


    }

    void testDelete () {
        def testInstances=[]
        mockDomain(Canton, testInstances)
        def c = new Canton(nombre: "pruebaDelete Canton")
        c.save(flush: true)
        assertEquals(1,Canton.count())
        c.delete()
        assertEquals (0,Canton.count())

    }
}
