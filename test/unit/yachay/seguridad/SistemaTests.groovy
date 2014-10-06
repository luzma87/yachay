package yachay.seguridad

import grails.test.*

class SistemaTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {

        def testInstance = []
        mockDomain(Sistema, testInstance)

        def stm = new Sistema(nombre: 'sistema', descripcion: 'pruebaSistema').save(flush: true)
        assertEquals(1, stm.count())

    }

    void testUpdate () {

        def testInstance = []
        mockDomain(Sistema, testInstance)

        def stm = new Sistema(nombre: 'sistema', descripcion: 'pruebaSistema').save(flush: true)
        assertEquals(1, stm.count())

        def stm2 = Sistema.findByNombre('sistema')
        stm2.nombre = 'nuevoSistema'
        stm2.save(flush: true)
        assertEquals(1,stm2.count())
        assertEquals('nuevoSistema', stm2.nombre)

    }


    void testDelete () {

        def testInstance = []
        mockDomain(Sistema, testInstance)

        def stm = new Sistema(nombre: 'sistema', descripcion: 'pruebaSistema').save(flush: true)
        assertEquals(1, stm.count())
        stm.delete()
        assertEquals(0, stm.count())

    }
}
