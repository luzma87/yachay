package yachay.proyectos

import grails.test.*

class ModificablesTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {

        def testInstances=[]
        mockDomain(Modificables, testInstances)
        mockDomain(ModificacionProyecto, testInstances)

        def mdp = new ModificacionProyecto(descripcion: 'pruebaModificacionProyecto', estado: 0).save(flush: true)
        assertEquals(1, mdp.count())
        def mdb = new Modificables(modificacion: mdp, fecha: new Date(), id_remoto: 123, tipo: 1).save(flush: true)
        assertEquals(1, mdb.count())

    }

    void testUpdate () {

        def testInstances=[]
        mockDomain(Modificables, testInstances)
        mockDomain(ModificacionProyecto, testInstances)

        def mdp = new ModificacionProyecto(descripcion: 'pruebaModificacionProyecto', estado: 0).save(flush: true)
        assertEquals(1, mdp.count())
        def mdb = new Modificables(modificacion: mdp, fecha: new Date(), id_remoto: 123, tipo: 1).save(flush: true)
        assertEquals(1, mdb.count())

        def mdb2 = Modificables.findByModificacion(mdp)
        mdb2.tipo = 2
        mdb2.save(flush: true)
        assertEquals(1, mdb2.count())
        assertEquals(2, mdb2.tipo)

    }

    void testDelete () {

        def testInstances=[]
        mockDomain(Modificables, testInstances)
        mockDomain(ModificacionProyecto, testInstances)

        def mdp = new ModificacionProyecto(descripcion: 'pruebaModificacionProyecto', estado: 0).save(flush: true)
        assertEquals(1, mdp.count())
        def mdb = new Modificables(modificacion: mdp, fecha: new Date(), id_remoto: 123, tipo: 1).save(flush: true)
        assertEquals(1, mdb.count())
        mdb.delete()
        assertEquals(0, mdb.count())
    }
}
