package yachay.proyectos

import grails.test.*

class MetaBuenVivirTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {

        def testInstaces =[]
        mockDomain(MetaBuenVivir, testInstaces)
        mockDomain(ObjetivoBuenVivir, testInstaces)
        mockDomain(PoliticaBuenVivir, testInstaces)

        def obv = new ObjetivoBuenVivir(codigo: 123, descripcion: 'pruebaObjetivoBuenVivir').save(flush: true)
        assertEquals(1, obv.count())
        def pbv = new PoliticaBuenVivir(objetivo: obv, codigo: 456, descripcion: 'pruebaPoliticaBuenVivir').save(flush: true)
        assertEquals(1, pbv.count())
        def mbv = new MetaBuenVivir(politica: pbv, codigo: 789, descripcion: 'pruebaMetaBuenVivir').save(flush: true)
        assertEquals(1, mbv.count())

    }

    void testUpdate () {
        def testInstaces =[]
        mockDomain(MetaBuenVivir, testInstaces)
        mockDomain(ObjetivoBuenVivir, testInstaces)
        mockDomain(PoliticaBuenVivir, testInstaces)

        def obv = new ObjetivoBuenVivir(codigo: 123, descripcion: 'pruebaObjetivoBuenVivir').save(flush: true)
        assertEquals(1, obv.count())
        def pbv = new PoliticaBuenVivir(objetivo: obv, codigo: 456, descripcion: 'pruebaPoliticaBuenVivir').save(flush: true)
        assertEquals(1, pbv.count())
        def mbv = new MetaBuenVivir(politica: pbv, codigo: 789, descripcion: 'pruebaMetaBuenVivir').save(flush: true)
        assertEquals(1, mbv.count())

        def mbv2 = MetaBuenVivir.findByDescripcion('pruebaMetaBuenVivir')
        mbv2.descripcion = 'nuevaMetaBuenVivir'
        mbv2.save(flush: true)
        assertEquals(1, mbv2.count())
        assertEquals('nuevaMetaBuenVivir', mbv2.descripcion)

    }

    void testDelete () {

        def testInstaces =[]
        mockDomain(MetaBuenVivir, testInstaces)
        mockDomain(ObjetivoBuenVivir, testInstaces)
        mockDomain(PoliticaBuenVivir, testInstaces)

        def obv = new ObjetivoBuenVivir(codigo: 123, descripcion: 'pruebaObjetivoBuenVivir').save(flush: true)
        assertEquals(1, obv.count())
        def pbv = new PoliticaBuenVivir(objetivo: obv, codigo: 456, descripcion: 'pruebaPoliticaBuenVivir').save(flush: true)
        assertEquals(1, pbv.count())
        def mbv = new MetaBuenVivir(politica: pbv, codigo: 789, descripcion: 'pruebaMetaBuenVivir').save(flush: true)
        assertEquals(1, mbv.count())
        mbv.delete()
        assertEquals(0, mbv.count())

    }
}
