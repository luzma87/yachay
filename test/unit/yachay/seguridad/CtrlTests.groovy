package yachay.seguridad

import grails.test.*

class CtrlTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {

        def testInstances = []
        mockDomain(Ctrl, testInstances)

        def ctr = new Ctrl(ctrlNombre: 'pruebaCtrl').save(flush: true)
        assertEquals(1, ctr.count())

    }

    void testUpdate () {
        def testInstances = []
        mockDomain(Ctrl, testInstances)

        def ctr = new Ctrl(ctrlNombre: 'pruebaCtrl').save(flush: true)
        assertEquals(1, ctr.count())
        def ctr2 = Ctrl.findByCtrlNombre('pruebaCtrl')
        ctr2.ctrlNombre =  'nuevoCtrl'
        ctr2.save(flush: true)
        assertEquals(1, ctr2.count())
        assertEquals('nuevoCtrl', ctr2.ctrlNombre)

    }

    void testDelete () {

        def testInstances = []
        mockDomain(Ctrl, testInstances)

        def ctr = new Ctrl(ctrlNombre: 'pruebaCtrl').save(flush: true)
        assertEquals(1, ctr.count())
        ctr.delete()
        assertEquals(0, ctr.count())

    }
}
