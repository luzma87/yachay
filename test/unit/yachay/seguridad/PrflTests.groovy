package yachay.seguridad

import grails.test.*
import sun.misc.Perf

class PrflTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {
        def testInstances = []
        mockDomain(Prfl, testInstances)

        def prf = new Prfl(nombre: 'perfil', descripcion: 'pruebaPerfil', codigo: '123', observaciones: 'observacion').save(flush: true)
        assertEquals(1, prf.count())

    }

    void testUpdate () {

        def testInstances = []
        mockDomain(Prfl, testInstances)

        def prf = new Prfl(nombre: 'perfil', descripcion: 'pruebaPerfil', codigo: '123', observaciones: 'observacion').save(flush: true)
        assertEquals(1, prf.count())

        def prf2 = Prfl.findByNombre('perfil')
        prf2.nombre = 'nuevoPerfil'
        prf2.save(flush: true)
        assertEquals(1, prf2.count())
        assertEquals('nuevoPerfil', prf2.nombre)


    }

    void testDelete () {

        def testInstances = []
        mockDomain(Prfl, testInstances)

        def prf = new Prfl(nombre: 'perfil', descripcion: 'pruebaPerfil', codigo: '123', observaciones: 'observacion').save(flush: true)
        assertEquals(1, prf.count())
        prf.delete()
        assertEquals(0, prf.count())
    }
}
