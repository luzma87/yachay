package yachay.parametros

import grails.test.*

class SectorTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {
        def testInstances = []
        mockDomain(Sector, testInstances)
        def s = new Sector(descripcion: "sectorSave").save(flush: true)
        assertEquals(1,Sector.count())
    }

    void testUpdate() {
        def testInstances = []
        mockDomain(Sector, testInstances)
        new Sector(descripcion: "testUpdate").save(flush: true)
        assertEquals(1,Sector.count())
        def s = Sector.findByDescripcion('testUpdate')
        s.descripcion ="sectorUpdate"
        s.save(flush: true)
        assertEquals(1,Sector.count())
        assertEquals('sectorUpdate',s.descripcion)
    }

    void testDelete () {
        def testInstances=[]
        mockDomain(Sector, testInstances)
        def s = new Sector(descripcion: "sectorDelete")
        s.save(flush: true)
        assertEquals(1,Sector.count())
        s.delete()
        assertEquals (0,Sector.count())
    }
}
