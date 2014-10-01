package yachay.parametros.geografia

import grails.test.*

class RegionTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave () {
        def testInstances = []
        mockDomain(Region, testInstances)
        new Region(nombre: "pruebaSave ").save(flush: true)
        assertEquals(1,Region.count())
    }

    void testUpdate() {
        def testInstances = []
        mockDomain(Region, testInstances)
        new Region(nombre: 'pruebaUpdate').save(flush: true)
        assertEquals(1,Region.count())
        def c = Region.findByNombre('pruebaUpdate')
        c.nombre ="nuevoRegion"
        c.save(flush: true)
        assertEquals(1,Region.count())
        assertEquals("nuevoRegion",c.nombre)

    }

    void testDelete () {
        def testInstances=[]
        mockDomain(Region, testInstances)
        def c = new Region(nombre: "pruebaDelete")
        c.save(flush: true)
        assertEquals(1,Region.count())
        c.delete()
        assertEquals (0,Region.count())

    }
}
