package yachay.proyectos

import grails.test.*

class PoliticaBuenVivirTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {

        def testInstaces =[]
        mockDomain(ObjetivoBuenVivir, testInstaces)
        mockDomain(PoliticaBuenVivir, testInstaces)

        def obv = new ObjetivoBuenVivir(codigo: 123, descripcion: 'pruebaObjetivoBuenVivir').save(flush: true)
        assertEquals(1, obv.count())
        def pbv = new PoliticaBuenVivir(objetivo: obv, codigo: 456, descripcion: 'pruebaPoliticaBuenVivir').save(flush: true)
        assertEquals(1, pbv.count())

    }


    void testUpdate () {

        def testInstaces =[]
        mockDomain(ObjetivoBuenVivir, testInstaces)
        mockDomain(PoliticaBuenVivir, testInstaces)

        def obv = new ObjetivoBuenVivir(codigo: 123, descripcion: 'pruebaObjetivoBuenVivir').save(flush: true)
        assertEquals(1, obv.count())
        def pbv = new PoliticaBuenVivir(objetivo: obv, codigo: 456, descripcion: 'pruebaPoliticaBuenVivir').save(flush: true)
        assertEquals(1, pbv.count())

        def pbv2 = PoliticaBuenVivir.findByDescripcion('pruebaPoliticaBuenVivir')
        pbv2.descripcion = 'nuevaPoliticaBuenVivir'
        pbv2.save(flush: true)
        assertEquals(1, pbv2.count())
        assertEquals('nuevaPoliticaBuenVivir', pbv2.descripcion)

    }

    void testDelete () {


        def testInstaces =[]
        mockDomain(ObjetivoBuenVivir, testInstaces)
        mockDomain(PoliticaBuenVivir, testInstaces)

        def obv = new ObjetivoBuenVivir(codigo: 123, descripcion: 'pruebaObjetivoBuenVivir').save(flush: true)
        assertEquals(1, obv.count())
        def pbv = new PoliticaBuenVivir(objetivo: obv, codigo: 456, descripcion: 'pruebaPoliticaBuenVivir').save(flush: true)
        assertEquals(1, pbv.count())
        pbv.delete()
        assertEquals(0, pbv.count())
    }
}
