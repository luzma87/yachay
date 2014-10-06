package yachay.poa

import grails.test.*
import yachay.avales.DistribucionAsignacion
import yachay.parametros.TipoInstitucion
import yachay.parametros.UnidadEjecutora
import yachay.parametros.poaPac.Anio
import yachay.proyectos.MarcoLogico

class AsignacionTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {

        def testInstances = []
        mockDomain(Anio,testInstances)
        mockDomain(MarcoLogico, testInstances)
        mockDomain(Asignacion, testInstances)

        def ani = new Anio(anio: '2014', estado: 1).save(flush: true)
        assertEquals(1,ani.count())
        def mar = new MarcoLogico(objeto: 'pruebaMar').save(flush: true)
        assertEquals(1,mar.count())
        def asg = new Asignacion(anio: ani, marcoLogico: mar).save(flush: true)
        assertEquals(1,asg.count())

    }

    void testUpdate () {

        def testInstances = []
        mockDomain(Anio,testInstances)
        mockDomain(MarcoLogico, testInstances)
        mockDomain(Asignacion, testInstances)

        def ani = new Anio(anio: '2014', estado: 1).save(flush: true)
        assertEquals(1,ani.count())
        def mar = new MarcoLogico(objeto: 'pruebaMar').save(flush: true)
        assertEquals(1,mar.count())
        def asg = new Asignacion(anio: ani, marcoLogico: mar).save(flush: true)
        assertEquals(1,asg.count())

        def asg2 = Asignacion.findByMarcoLogico(mar)
        asg2.marcoLogico.objeto = 'nuevoMarco'
        asg2.save(flush: true)
        assertEquals(1, asg2.count())
        assertEquals('nuevoMarco', asg2.marcoLogico.objeto)

    }

    void testDelete () {

        def testInstances = []
        mockDomain(Anio,testInstances)
        mockDomain(MarcoLogico, testInstances)
        mockDomain(Asignacion, testInstances)


        def ani = new Anio(anio: '2014', estado: 1).save(flush: true)
        assertEquals(1,ani.count())
        def mar = new MarcoLogico(objeto: 'pruebaMar').save(flush: true)
        assertEquals(1,mar.count())
        def asg = new Asignacion(anio: ani, marcoLogico: mar).save(flush: true)
        assertEquals(1,asg.count())

        asg.delete()
        assertEquals(0, asg.count())
    }
}
