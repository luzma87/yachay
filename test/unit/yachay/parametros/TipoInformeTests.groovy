package yachay.parametros

import grails.test.*

class TipoInformeTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {

        def testInstances = []
        mockDomain(TipoInforme, testInstances)

        def tpi = new TipoInforme(descripcion: 'pruebaInforme').save(flush: true)
        assertEquals(1, tpi.count())

    }

    void testUpdate () {

        def testInstances = []
        mockDomain(TipoInforme, testInstances)

        def tpi = new TipoInforme(descripcion: 'pruebaInforme').save(flush: true)
        assertEquals(1, tpi.count())

        def tpi2 = TipoInforme.findByDescripcion('pruebaInforme')
        tpi2.descripcion = 'nuevoInforme'
        tpi2.save(flush: true)
        assertEquals(1, tpi2.count())
        assertEquals('nuevoInforme', tpi2.descripcion)


    }

    void testDelete () {

        def testInstances = []
        mockDomain(TipoInforme, testInstances)

        def tpi = new TipoInforme(descripcion: 'pruebaInforme').save(flush: true)
        assertEquals(1, tpi.count())
        tpi.delete()
        assertEquals(0, tpi.count())
    }
}
