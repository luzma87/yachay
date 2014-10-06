package yachay.proyectos

import grails.test.*

class InformeTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {

        def testInstances = []
        mockDomain(Informe, testInstances)

        def inf = new Informe(fecha: new Date(), avance: 'avanceInforme').save(flush: true)
        assertEquals(1,inf.count())

    }

    void testUpdate () {
        def testInstances = []
        mockDomain(Informe, testInstances)

        def inf = new Informe(fecha: new Date(), avance: 'avanceInforme').save(flush: true)
        assertEquals(1,inf.count())

        def inf2 = Informe.findByAvance('avanceInforme')
        inf2.avance = 'nuevoAvanceInforme'
        inf2.save(flush: true)
        assertEquals(1, inf2.count())
        assertEquals('nuevoAvanceInforme', inf2.avance)

    }

    void testDelete () {

        def testInstances = []
        mockDomain(Informe, testInstances)

        def inf = new Informe(fecha: new Date(), avance: 'avanceInforme').save(flush: true)
        assertEquals(1,inf.count())
        inf.delete()
        assertEquals(0, inf.count())
    }
}
