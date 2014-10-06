package yachay.proyectos

import grails.test.*

class ModificacionProyectoTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {

        def testInstances=[]
        mockDomain(ModificacionProyecto, testInstances)

        def mdp = new ModificacionProyecto(descripcion: 'pruebaModificacionProyecto', estado: 0).save(flush: true)
        assertEquals(1, mdp.count())

    }

    void testUpdate () {

        def testInstances=[]
        mockDomain(ModificacionProyecto, testInstances)

        def mdp = new ModificacionProyecto(descripcion: 'pruebaModificacionProyecto', estado: 0).save(flush: true)
        assertEquals(1, mdp.count())

        def mdp2 = ModificacionProyecto.findByDescripcion('pruebaModificacionProyecto')
        mdp2.descripcion = 'nuevaModificacionProyecto'
        mdp2.save(flush: true)
        assertEquals(1, mdp2.count())
        assertEquals('nuevaModificacionProyecto', mdp2.descripcion)

    }

    void testDelete () {

        def testInstances=[]
        mockDomain(ModificacionProyecto, testInstances)

        def mdp = new ModificacionProyecto(descripcion: 'pruebaModificacionProyecto', estado: 0).save(flush: true)
        assertEquals(1, mdp.count())
        mdp.delete()
        assertEquals(0, mdp.count())
    }
}
