package yachay.parametros

import grails.test.*

class SubSecretariaTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {
        def testInstances = []
        mockDomain(SubSecretaria, testInstances)
        def s = new SubSecretaria(nombre: "sbSecretariaSave").save(flush: true)
        assertEquals(1,SubSecretaria.count())
    }

    void testUpdate() {
        def testInstances = []
        mockDomain(SubSecretaria, testInstances)
        new SubSecretaria(nombre: "testUpdate").save(flush: true)
        assertEquals(1,SubSecretaria.count())
        def s = SubSecretaria.findByNombre('testUpdate')
        s.nombre ="cbSecretariaUpdate"
        s.save(flush: true)
        assertEquals(1,SubSecretaria.count())
        assertEquals('cbSecretariaUpdate',s.nombre)
    }

    void testDelete () {
        def testInstances=[]
        mockDomain(SubSecretaria, testInstances)
        def s = new SubSecretaria(nombre: "sbSecretariaDelete")
        s.save(flush: true)
        assertEquals(1,SubSecretaria.count())
        s.delete()
        assertEquals (0,SubSecretaria.count())
    }
}
