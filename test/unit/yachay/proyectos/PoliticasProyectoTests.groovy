package yachay.proyectos

import grails.test.*
import yachay.parametros.proyectos.Politica

class PoliticasProyectoTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {

        def testInstances = []
        mockDomain(Politica, testInstances)
        mockDomain(PoliticasProyecto, testInstances)

        def plt = new Politica(descripcion: 'pruebaPolitica').save(flush: true)
        assertEquals(1, plt.count())
        def plp = new PoliticasProyecto(politica: plt).save(flush: true)
        assertEquals(1,plp.count())

    }

    void testUpdate (){

        def testInstances = []
        mockDomain(Politica, testInstances)
        mockDomain(PoliticasProyecto, testInstances)

        def plt = new Politica(descripcion: 'pruebaPolitica').save(flush: true)
        assertEquals(1, plt.count())
        def plp = new PoliticasProyecto(politica: plt).save(flush: true)
        assertEquals(1,plp.count())

        def plp2 = PoliticasProyecto.findByPolitica(plt)
        plp2.politica.descripcion = 'nuevaPolitica'
        plp2.save(flush: true)
        assertEquals(1, plp2.count())
        assertEquals('nuevaPolitica', plp2.politica.descripcion)
    }

    void testDelete () {

        def testInstances = []
        mockDomain(Politica, testInstances)
        mockDomain(PoliticasProyecto, testInstances)

        def plt = new Politica(descripcion: 'pruebaPolitica').save(flush: true)
        assertEquals(1, plt.count())
        def plp = new PoliticasProyecto(politica: plt).save(flush: true)
        assertEquals(1,plp.count())
        plp.delete()
        assertEquals(0, plp.count())
    }
}
