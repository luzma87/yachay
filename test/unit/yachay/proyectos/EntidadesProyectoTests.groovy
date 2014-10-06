package yachay.proyectos

import grails.test.*

class EntidadesProyectoTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {
       def testInstances = []
        mockDomain(EntidadesProyecto, testInstances)

        def etp = new EntidadesProyecto(monto: 125.45).save(flush: true)
        assertEquals(1, etp.count())

    }

    void testUpdate () {

        def testInstances = []
        mockDomain(EntidadesProyecto, testInstances)

        def etp = new EntidadesProyecto(monto: 125.45).save(flush: true)
        assertEquals(1, etp.count())

        def etp2 = EntidadesProyecto.findByMonto(125.45)
        etp2.monto = 600
        etp2.save(flush: true)
        assertEquals(1, etp2.count())
        assertEquals(600, etp2.monto)
    }

    void testDelete (){

        def testInstances = []
        mockDomain(EntidadesProyecto, testInstances)

        def etp = new EntidadesProyecto(monto: 125.45).save(flush: true)
        assertEquals(1, etp.count())
        etp.delete()
        assertEquals(0, etp.count())
    }
}
