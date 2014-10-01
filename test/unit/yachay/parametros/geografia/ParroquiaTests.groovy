package yachay.parametros.geografia

import grails.test.*

class ParroquiaTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }
    void testSave () {
        def testInstances = []
        mockDomain(Parroquia, testInstances)
        new Parroquia(nombre: "pruebaSave Parroquia").save(flush: true)
        assertEquals(1,Parroquia.count())
    }

    void testUpdate() {
        def testInstances = []
        mockDomain(Parroquia, testInstances)
        new Parroquia(nombre: 'pruebaUpdate Parroquia').save(flush: true)
        assertEquals(1,Parroquia.count())
        def c = Parroquia.findByNombre('pruebaUpdate Parroquia')
        c.nombre ="nuevoParroquia"
        c.save(flush: true)
        assertEquals(1,Parroquia.count())
        assertEquals("nuevoParroquia",c.nombre)

    }

    void testDelete () {
        def testInstances=[]
        mockDomain(Parroquia, testInstances)
        def c = new Parroquia(nombre: "pruebaDelete Parroquia")
        c.save(flush: true)
        assertEquals(1,Parroquia.count())
        c.delete()
        assertEquals (0,Parroquia.count())

    }
}
