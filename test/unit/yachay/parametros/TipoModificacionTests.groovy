package yachay.parametros

import grails.test.*

class TipoModificacionTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {
        def testInstances = []
        mockDomain(TipoModificacion, testInstances)
        def a = new TipoModificacion(descripcion: "modificacionSave").save(flush: true)
        assertEquals(1,TipoModificacion.count())
    }

    void testUpdate() {
        def testInstances = []
        mockDomain(TipoModificacion, testInstances)
        new TipoModificacion(descripcion: "modificacionUpdate").save(flush: true)
        assertEquals(1,TipoModificacion.count())
        def p = TipoModificacion.findByDescripcion('modificacionUpdate')
        p.descripcion ="modificacionUpdateAc"
        p.save(flush: true)
        assertEquals(1,TipoModificacion.count())
        assertEquals('modificacionUpdateAc',p.descripcion)
    }

    void testDelete () {
        def testInstances=[]
        mockDomain(TipoModificacion, testInstances)
        def p = new TipoModificacion(descripcion: "modificacionDelete")
        p.save(flush: true)
        assertEquals(1,TipoModificacion.count())
        p.delete()
        assertEquals (0,TipoModificacion.count())
    }
}
