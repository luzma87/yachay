package yachay.parametros.geografia

import grails.test.*

class ProvinciaTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

//    void testSomething() {
//        def testInstances=[]
//        mockDomain(Provincia, testInstances)
//        new Provincia(nombre: "prueba").save()
//        assertEquals(1, Provincia.count())
//    }

    void testSave () {
        def testInstances = []
        mockDomain(Provincia, testInstances)
        new Provincia(nombre: "prueba").save(flush: true)
        assertEquals(1,Provincia.count())
    }

    void testUpdate() {
        def testInstances = []
        mockDomain(Provincia, testInstances)
        new Provincia(nombre: 'pruebaUpdate').save(flush: true)
        assertEquals(1,Provincia.count())
        def p = Provincia.findByNombre('pruebaUpdate')
        println("p " + p.longitud)
        p.longitud = 1
        p.save(flush: true)
        println("p1 " + p.longitud)
        assertEquals(1,Provincia.count())
    }

    void testDelete () {
        def testInstances=[]
        mockDomain(Provincia, testInstances)
        def p = new Provincia(nombre: "pruebaDelete")
        p.save(flush: true)
//        assertEquals(1,Provincia.list().size())
        assertEquals false, p.deleted
        p.delete()
//        assertEquals (1,Provincia.list().size())
        assertEquals true,p.deleted
    }
}
