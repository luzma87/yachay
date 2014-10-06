package yachay.proyectos

import grails.test.*
import yachay.parametros.TipoResponsable

class ResponsableTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {

        def testInstances = []
        mockDomain(TipoResponsable, testInstances)
        mockDomain(Responsable, testInstances)

        def tpr = new TipoResponsable(codigo: '1', descripcion: 'pruebaTR').save(flush: true)
        assertEquals(1, tpr.count())
        def rps = new Responsable(nombre: 'pruebaRes', tipo: tpr).save(flush: true)
        assertEquals(1, rps.count())

    }

    void testUpdate () {

        def testInstances = []
        mockDomain(TipoResponsable, testInstances)
        mockDomain(Responsable, testInstances)

        def tpr = new TipoResponsable(codigo: '1', descripcion: 'pruebaTR').save(flush: true)
        assertEquals(1, tpr.count())
        def rps = new Responsable(nombre: 'pruebaRes', tipo: tpr).save(flush: true)
        assertEquals(1, rps.count())

        def rps2 = Responsable.findByNombre('pruebaRes')
        rps2.nombre = 'nuevoRes'
        rps2.save(flush: true)
        assertEquals(1, rps2.count())
        assertEquals('nuevoRes', rps2.nombre)


    }

    void testDelete () {

        def testInstances = []
        mockDomain(TipoResponsable, testInstances)
        mockDomain(Responsable, testInstances)

        def tpr = new TipoResponsable(codigo: '1', descripcion: 'pruebaTR').save(flush: true)
        assertEquals(1, tpr.count())
        def rps = new Responsable(nombre: 'pruebaRes', tipo: tpr).save(flush: true)
        assertEquals(1, rps.count())
        rps.delete()
        assertEquals(0, rps.count())

    }
}
