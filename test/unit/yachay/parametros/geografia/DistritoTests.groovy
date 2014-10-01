package yachay.parametros.geografia

import grails.test.*

class DistritoTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave () {
        def testInstances = []
        mockDomain(Distrito, testInstances)
        new Distrito(nombre: "pruebaSave Distrito").save(flush: true)
        assertEquals(1,Distrito.count())
    }

    void testUpdate() {
        def testInstances = []
        mockDomain(Distrito, testInstances)
        new Distrito(nombre: 'pruebaUpdate Distrito').save(flush: true)
        assertEquals(1,Distrito.count())
        def c = Distrito.findByNombre('pruebaUpdate Distrito')
        c.nombre ="nuevoDistrito"
        c.save(flush: true)
        assertEquals(1,Distrito.count())
    }

    void testDelete () {
        def testInstances=[]
        mockDomain(Distrito, testInstances)
        def c = new Distrito(nombre: "pruebaDelete Distrito")
        c.save(flush: true)
        assertEquals(1,Distrito.count())
        c.delete()
        assertEquals (0,Distrito.count())

    }
}
