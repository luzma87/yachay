package yachay.parametros

import grails.test.*

class UnidadTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {
        def testInstances = []
        mockDomain(Unidad, testInstances)
        def a = new Unidad(descripcion: "pruebaSave").save(flush: true)
        assertEquals(1,Unidad.count())
    }

    void testUpdate() {
        def testInstances = []
        mockDomain(Unidad, testInstances)
        new Unidad(descripcion: 'pruebaUpdate Unidad').save(flush: true)
        assertEquals(1,Unidad.count())
        def u = Unidad.findByDescripcion('pruebaUpdate Unidad')
        u.descripcion ="pruebaUnidad"
        u.save(flush: true)
        assertEquals(1,Unidad.count())
        assertEquals('pruebaUnidad',u.descripcion)
    }

    void testDelete () {
        def testInstances=[]
        mockDomain(Unidad, testInstances)
        def c = new Unidad(descripcion:"pruebaDelete Unidad")
        c.save(flush: true)
        assertEquals(1,Unidad.count())
        c.delete()
        assertEquals (0,Unidad.count())
    }
}
