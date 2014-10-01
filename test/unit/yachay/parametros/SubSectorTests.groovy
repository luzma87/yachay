package yachay.parametros

import grails.test.*

class SubSectorTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {
        def testInstances = []
        mockDomain(SubSector, testInstances)
        def s = new SubSector(descripcion: "sbSectorSave").save(flush: true)
        assertEquals(1,SubSector.count())
    }

    void testUpdate() {
        def testInstances = []
        mockDomain(SubSector, testInstances)
        new SubSector(descripcion: "testUpdate").save(flush: true)
        assertEquals(1,SubSector.count())
        def s = SubSector.findByDescripcion('testUpdate')
        s.descripcion ="sbSectorUpdate"
        s.save(flush: true)
        assertEquals(1,SubSector.count())
        assertEquals('sbSectorUpdate',s.descripcion)
    }

    void testDelete () {
        def testInstances=[]
        mockDomain(SubSector, testInstances)
        def s = new SubSector(descripcion: "sbSectorDelete")
        s.save(flush: true)
        assertEquals(1,SubSector.count())
        s.delete()
        assertEquals (0,SubSector.count())
    }
}
