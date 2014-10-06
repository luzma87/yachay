package yachay.proyectos

import grails.test.*

class ObjetivoEstrategicoProyectoTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {

        def testInstances = []
        mockDomain(ObjetivoEstrategicoProyecto, testInstances)

        def oep = new ObjetivoEstrategicoProyecto(orden: 1, descripcion: 'pruebaObjetivoEstrategicoProyecto').save(flush: true)
        assertEquals(1, oep.count())

    }

    void testUpdate () {

        def testInstances = []
        mockDomain(ObjetivoEstrategicoProyecto, testInstances)

        def oep = new ObjetivoEstrategicoProyecto(orden: 1, descripcion: 'pruebaObjetivoEstrategicoProyecto').save(flush: true)
        assertEquals(1, oep.count())

        def oep2 = ObjetivoEstrategicoProyecto.findByDescripcion('pruebaObjetivoEstrategicoProyecto')
        oep2.descripcion = 'nuevoObjetivoEstrategicoProyecto'
        oep2.save(flush: true)
        assertEquals(1, oep2.count())

    }

    void testDelete () {

        def testInstances = []
        mockDomain(ObjetivoEstrategicoProyecto, testInstances)

        def oep = new ObjetivoEstrategicoProyecto(orden: 1, descripcion: 'pruebaObjetivoEstrategicoProyecto').save(flush: true)
        assertEquals(1, oep.count())
        oep.delete()
        assertEquals(0, oep.count())
    }
}
