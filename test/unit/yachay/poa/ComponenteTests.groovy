package yachay.poa

import grails.test.*

class ComponenteTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {

        def testInstance = []
        mockDomain(Componente,testInstance)
        def cmp = new Componente(descripcion: 'pruebaComponente').save(flush: true)
        assertEquals(1, cmp.count())

    }

    void testUpdate () {

        def testInstance = []
        mockDomain(Componente,testInstance)
        def cmp = new Componente(descripcion: 'pruebaComponente').save(flush: true)
        assertEquals(1, cmp.count())

        def cmp2 = Componente.findByDescripcion('pruebaComponente')
        cmp2.descripcion = 'nuevoComponente'
        cmp2.save(flush: true)
        assertEquals(1, cmp2.count())
        assertEquals('nuevoComponente', cmp2.descripcion)
    }

    void testDelete () {

        def testInstance = []
        mockDomain(Componente,testInstance)
        def cmp = new Componente(descripcion: 'pruebaComponente').save(flush: true)
        assertEquals(1, cmp.count())
        cmp.delete()
        assertEquals(0, cmp.count())
    }


}
