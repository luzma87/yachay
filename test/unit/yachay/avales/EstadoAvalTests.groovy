package yachay.avales

import grails.test.*

class EstadoAvalTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {

        def testInstances = []
        mockDomain(EstadoAval, testInstances)
        def eta = new EstadoAval(descripcion: 'pruebaEstadoAval', codigo: '123').save(flush: true)
        assertEquals(1, eta.count())

    }

    void testUpdate (){

        def testInstances = []
        mockDomain(EstadoAval, testInstances)
        def eta = new EstadoAval(descripcion: 'pruebaEstadoAval', codigo: '123').save(flush: true)
        assertEquals(1, eta.count())
        def eta2 = EstadoAval.findByDescripcion('pruebaEstadoAval')
        eta2.descripcion = 'nuevoEstadoAval'
        eta2.save(flush: true)
        assertEquals(1, eta2.count())
        assertEquals('nuevoEstadoAval', eta2.descripcion)

    }

    void testDelete (){

        def testInstances = []
        mockDomain(EstadoAval, testInstances)
        def eta = new EstadoAval(descripcion: 'pruebaEstadoAval', codigo: '123').save(flush: true)
        assertEquals(1, eta.count())
        eta.delete()
        assertEquals(0, eta.count())

    }
}
