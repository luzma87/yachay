package yachay.proyectos

import grails.test.*

class MedioVerificacionTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {

        def testInstances = []
        mockDomain(Indicador, testInstances)
        mockDomain(MedioVerificacion, testInstances)

        def ind = new Indicador(descripcion: 'pruebaIndicador', estado: 0).save(flush: true)
        assertEquals(1, ind.count())
        def mvr = new MedioVerificacion(indicador: ind, descripcion: 'pruebaMedioVerificacion', estado: 0).save(flush: true)
        assertEquals(1, mvr.count())

    }

    void testUpdate() {


        def testInstances = []
        mockDomain(Indicador, testInstances)
        mockDomain(MedioVerificacion, testInstances)

        def ind = new Indicador(descripcion: 'pruebaIndicador', estado: 0).save(flush: true)
        assertEquals(1, ind.count())
        def mvr = new MedioVerificacion(indicador: ind, descripcion: 'pruebaMedioVerificacion', estado: 0).save(flush: true)
        assertEquals(1, mvr.count())

        def mvr2 = MedioVerificacion.findByDescripcion('pruebaMedioVerificacion')
        mvr2.descripcion = 'nuevoMedioVerificacion'
        mvr2.save(flush: true)
        assertEquals(1, mvr2.count())
        assertEquals('nuevoMedioVerificacion', mvr2.descripcion)

    }

    void testDelete () {

        def testInstances = []
        mockDomain(Indicador, testInstances)
        mockDomain(MedioVerificacion, testInstances)

        def ind = new Indicador(descripcion: 'pruebaIndicador', estado: 0).save(flush: true)
        assertEquals(1, ind.count())
        def mvr = new MedioVerificacion(indicador: ind, descripcion: 'pruebaMedioVerificacion', estado: 0).save(flush: true)
        assertEquals(1, mvr.count())
        mvr.delete()
        assertEquals(0, mvr.count())

    }
}
