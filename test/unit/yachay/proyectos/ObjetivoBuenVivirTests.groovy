package yachay.proyectos

import grails.test.*

class ObjetivoBuenVivirTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {

        def testInstances = []
        mockDomain(ObjetivoBuenVivir, testInstances)

        def obv = new ObjetivoBuenVivir(codigo: 123, descripcion: 'pruebaObjetivoBuenVivir').save(flush: true)
        assertEquals(1, obv.count())

    }

    void testUpdate () {
        def testInstances = []
        mockDomain(ObjetivoBuenVivir, testInstances)

        def obv = new ObjetivoBuenVivir(codigo: 123, descripcion: 'pruebaObjetivoBuenVivir').save(flush: true)
        assertEquals(1, obv.count())

        def obv2 = ObjetivoBuenVivir.findByDescripcion('pruebaObjetivoBuenVivir')
        obv2.descripcion = 'nuevoObjetivoBuenVivir'
        obv2.save(flush: true)
        assertEquals(1, obv2.count())
        assertEquals('nuevoObjetivoBuenVivir', obv2.descripcion)

    }

    void testDelete () {

        def testInstances = []
        mockDomain(ObjetivoBuenVivir, testInstances)

        def obv = new ObjetivoBuenVivir(codigo: 123, descripcion: 'pruebaObjetivoBuenVivir').save(flush: true)
        assertEquals(1, obv.count())

        obv.delete()
        assertEquals(0, obv.count())
    }
}
