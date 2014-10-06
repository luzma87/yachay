package yachay.proyectos

import grails.test.*

class IndicadorTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {
        def testInstances = []
        mockDomain(Indicador, testInstances)

        def ind = new Indicador(descripcion: 'pruebaIndicador', estado: 0).save(flush: true)
        assertEquals(1, ind.count())

    }

    void testUpdate () {
        def testInstances = []
        mockDomain(Indicador, testInstances)

        def ind = new Indicador(descripcion: 'pruebaIndicador', estado: 0).save(flush: true)
        assertEquals(1, ind.count())
        def ind2 = Indicador.findByDescripcion('pruebaIndicador')
        ind2.descripcion = 'nuevoIndicador'
        ind2.save(flush: true)
        assertEquals(1, ind2.count())
        assertEquals('nuevoIndicador', ind2.descripcion)
    }

    void testDelete () {

        def testInstances = []
        mockDomain(Indicador, testInstances)

        def ind = new Indicador(descripcion: 'pruebaIndicador', estado: 0).save(flush: true)
        assertEquals(1, ind.count())
        ind.delete()
        assertEquals(0, ind.count())
    }
}
