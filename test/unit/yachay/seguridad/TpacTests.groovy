package yachay.seguridad

import grails.test.*

class TpacTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {

        def testInstances =[]
        mockDomain(Tpac, testInstances)

        def tpa = new Tpac(tipo: 'pruebaTpac').save(flush: true)
        assertEquals(1, tpa.count())

    }

    void testUpdate () {

        def testInstances =[]
        mockDomain(Tpac, testInstances)

        def tpa = new Tpac(tipo: 'pruebaTpac').save(flush: true)
        assertEquals(1, tpa.count())

        def tpa2 = Tpac.findByTipo('pruebaTpac')
        tpa2.tipo = 'nuevaTpac'
        tpa2.save(flush: true)
        assertEquals(1, tpa2.count())
        assertEquals('nuevaTpac', tpa2.tipo)

    }


    void testDelete () {

        def testInstances =[]
        mockDomain(Tpac, testInstances)

        def tpa = new Tpac(tipo: 'pruebaTpac').save(flush: true)
        assertEquals(1, tpa.count())
        tpa.delete()
        assertEquals(0, tpa.count())

    }
}
