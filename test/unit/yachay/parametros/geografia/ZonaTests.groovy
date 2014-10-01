package yachay.parametros.geografia

import grails.test.*

class ZonaTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }
    void testSave () {
        def testInstances = []
        mockDomain(Zona, testInstances)
        new Zona(nombre: "pruebaSave Zona").save(flush: true)
        assertEquals(1,Zona.count())
    }

    void testUpdate() {
        def testInstances = []
        mockDomain(Zona, testInstances)
        new Zona(nombre: 'pruebaUpdate Zona').save(flush: true)
        assertEquals(1,Zona.count())
        def c = Zona.findByNombre('pruebaUpdate Zona')
        c.nombre ="nuevoZona"
        c.save(flush: true)
        assertEquals(1,Zona.count())
        assertEquals("nuevoZona",c.nombre)

    }

    void testDelete () {
        def testInstances=[]
        mockDomain(Zona, testInstances)
        def c = new Zona(nombre: "pruebaDelete Zona")
        c.save(flush: true)
        assertEquals(1,Zona.count())
        c.delete()
        assertEquals (0,Zona.count())

    }
}
