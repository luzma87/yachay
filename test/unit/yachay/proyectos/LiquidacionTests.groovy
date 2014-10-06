package yachay.proyectos

import grails.test.*

class LiquidacionTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {

        def testInstances = []
        mockDomain(Liquidacion, testInstances)
        mockDomain(Obra, testInstances)

        def obr = new Obra(descripcion: 'pruebaObra').save(flush: true)
        assertEquals(1, obr.count())
        def liq = new Liquidacion(obra: obr,valor: 14567.80, fechaRegistro: new Date(), fechaAdjudicacion: new Date(), fechaFin: new Date(), fechaInicio: new Date()).save(flush: true)
        assertEquals(1, liq.count())

    }

    void testUpdate () {

        def testInstances = []
        mockDomain(Liquidacion, testInstances)
        mockDomain(Obra, testInstances)

        def obr = new Obra(descripcion: 'pruebaObra').save(flush: true)
        assertEquals(1, obr.count())
        def liq = new Liquidacion(obra: obr,valor: 14567.80, fechaRegistro: new Date(), fechaAdjudicacion: new Date(), fechaFin: new Date(), fechaInicio: new Date()).save(flush: true)
        assertEquals(1, liq.count())

        def liq2 = Liquidacion.findByObra(obr)
        liq2.valor = 2500
        liq2.save(flush: true)
        assertEquals(1, liq2.count())
        assertEquals(2500, liq2.valor)
    }

    void testDelete () {
        def testInstances = []
        mockDomain(Liquidacion, testInstances)
        mockDomain(Obra, testInstances)

        def obr = new Obra(descripcion: 'pruebaObra').save(flush: true)
        assertEquals(1, obr.count())
        def liq = new Liquidacion(obra: obr,valor: 14567.80, fechaRegistro: new Date(), fechaAdjudicacion: new Date(), fechaFin: new Date(), fechaInicio: new Date()).save(flush: true)
        assertEquals(1, liq.count())
    }
}
