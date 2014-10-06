package yachay.proyectos

import grails.test.*

class SupuestoTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {

        def testInstances = []
        mockDomain(Supuesto, testInstances)

        def spt = new Supuesto(descripcion: 'pruebaSupuesto').save(flush: true)
        assertEquals(1, spt.count())

    }

    void testUpdate () {


        def testInstances = []
        mockDomain(Supuesto, testInstances)

        def spt = new Supuesto(descripcion: 'pruebaSupuesto').save(flush: true)
        assertEquals(1, spt.count())

        def spt2 = Supuesto.findByDescripcion('pruebaSupuesto')
        spt2.descripcion = 'nuevoSupuesto'
        spt2.save(flush: true)
        assertEquals(1, spt2.count())
        assertEquals('nuevoSupuesto', spt2.descripcion)

    }

    void testDelete () {


        def testInstances = []
        mockDomain(Supuesto, testInstances)

        def spt = new Supuesto(descripcion: 'pruebaSupuesto').save(flush: true)
        assertEquals(1, spt.count())
        spt.delete()
        assertEquals(0, spt.count())
    }
}
