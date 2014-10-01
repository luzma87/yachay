package yachay.parametros

import grails.test.*

class CargoPersonalTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {
        def testInstances = []
        mockDomain(CargoPersonal, testInstances)
        def s = new CargoPersonal(descripcion: "CargoPersonalSave").save(flush: true)
        assertEquals(1,CargoPersonal.count())
    }

    void testUpdate() {
        def testInstances = []
        mockDomain(CargoPersonal, testInstances)
        new CargoPersonal(descripcion: "testUpdate").save(flush: true)
        assertEquals(1,CargoPersonal.count())
        def s = CargoPersonal.findByDescripcion('testUpdate')
        s.descripcion ="CargoPersonalUpdate"
        s.save(flush: true)
        assertEquals(1,CargoPersonal.count())
        assertEquals('CargoPersonalUpdate',s.descripcion)
    }

    void testDelete () {
        def testInstances=[]
        mockDomain(CargoPersonal, testInstances)
        def s = new CargoPersonal(descripcion: "CargoPersonalDelete")
        s.save(flush: true)
        assertEquals(1,CargoPersonal.count())
        s.delete()
        assertEquals (0,CargoPersonal.count())
    }
}
