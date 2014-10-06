package yachay.proyectos

import grails.test.*
import yachay.parametros.proyectos.PoliticaAgendaSocial

class PoliticasAgendaProyectoTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {

        def testInstances = []
        mockDomain(PoliticaAgendaSocial, testInstances)
        mockDomain(PoliticasAgendaProyecto, testInstances)

        def pas = new PoliticaAgendaSocial(descripcion: 'pruebaPoliticaAgendaSocial').save(flush: true)
        assertEquals(1, pas.count())
        def pap = new PoliticasAgendaProyecto(politicaAgendaSocial: pas).save(flush: true)
        assertEquals(1,pap.count())

    }

    void testUpdate () {

        def testInstances = []
        mockDomain(PoliticaAgendaSocial, testInstances)
        mockDomain(PoliticasAgendaProyecto, testInstances)

        def pas = new PoliticaAgendaSocial(descripcion: 'pruebaPoliticaAgendaSocial').save(flush: true)
        assertEquals(1, pas.count())
        def pap = new PoliticasAgendaProyecto(politicaAgendaSocial: pas).save(flush: true)
        assertEquals(1,pap.count())

        def pap2 = PoliticasAgendaProyecto.findByPoliticaAgendaSocial(pas)
        pap2.politicaAgendaSocial.descripcion = 'nuevoPAS'
        pap2.save(flush: true)
        assertEquals(1, pap2.count())
        assertEquals('nuevoPAS', pap2.politicaAgendaSocial.descripcion)
    }

    void testDelete () {

        def testInstances = []
        mockDomain(PoliticaAgendaSocial, testInstances)
        mockDomain(PoliticasAgendaProyecto, testInstances)

        def pas = new PoliticaAgendaSocial(descripcion: 'pruebaPoliticaAgendaSocial').save(flush: true)
        assertEquals(1, pas.count())
        def pap = new PoliticasAgendaProyecto(politicaAgendaSocial: pas).save(flush: true)
        assertEquals(1,pap.count())

    }
}
