package yachay.proyectos

import grails.test.*

class PasoTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {

        def testInstances = []
        mockDomain(Paso, testInstances)

        def pas = new Paso(tabla: 'tabla', tablaEsp: 'tablaEsp').save(flush: true)
        assertEquals(1, pas.count())

    }

    void testUpdate () {
        def testInstances = []
        mockDomain(Paso, testInstances)
        def pas = new Paso(descripcion: 'pruebaPaso', tabla: 'tabla', tablaEsp: 'tablaEsp').save(flush: true)
        assertEquals(1, pas.count())
        def pas2 = Paso.findByDescripcion('pruebaPaso')
        pas2.descripcion = 'nuevoPaso'
        pas2.save(flush: true)
        assertEquals(1, pas2.count())
        assertEquals('nuevoPaso', pas2.descripcion)
    }

    void testDelete () {

        def testInstances = []
        mockDomain(Paso, testInstances)

        def pas = new Paso(tabla: 'tabla', tablaEsp: 'tablaEsp').save(flush: true)
        assertEquals(1, pas.count())
        pas.delete()
        assertEquals(0, pas.count())
    }
}
