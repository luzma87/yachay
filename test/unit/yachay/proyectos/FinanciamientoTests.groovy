package yachay.proyectos

import grails.test.*
import yachay.parametros.poaPac.Presupuesto

class FinanciamientoTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {
        def testInstances = []

        mockDomain(Financiamiento, testInstances)

        def fnc = new Financiamiento(monto: 4567.89).save(flush: true)
        assertEquals(1, fnc.count())
    }
    void testUpdate () {
        def testInstances = []
        mockDomain(Financiamiento, testInstances)

        def fnc = new Financiamiento(monto: 4567.89).save(flush: true)
        assertEquals(1, fnc.count())

        def fnc2 = Financiamiento.findByMonto(4567.89)
        fnc2.monto = 5645
        fnc2.save(flush: true)
        assertEquals(1,fnc2.count())
        assertEquals(5645, fnc2.monto)

    }

    void testDelete () {
        def testInstances = []

        mockDomain(Financiamiento, testInstances)

        def fnc = new Financiamiento(monto: 4567.89).save(flush: true)
        assertEquals(1, fnc.count())
        fnc.delete()
        assertEquals(0, fnc.count())
    }
}
