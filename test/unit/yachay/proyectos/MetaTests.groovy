package yachay.proyectos

import grails.test.*
import yachay.parametros.Unidad
import yachay.parametros.geografia.Parroquia
import yachay.parametros.poaPac.Anio
import yachay.parametros.proyectos.TipoMeta
import yachay.poa.Asignacion

class MetaTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {

        def testInstances = []
        mockDomain(Parroquia, testInstances)
        mockDomain(Meta, testInstances)
        mockDomain(MarcoLogico, testInstances)
        mockDomain(TipoMeta, testInstances)
        mockDomain(Anio, testInstances)
        mockDomain(Asignacion, testInstances)
        mockDomain(Unidad, testInstances)

        def ani = new Anio(anio: '2014', estado: 1).save(flush: true)
        assertEquals(1,ani.count())
        def mar = new MarcoLogico(objeto: 'pruebaMar').save(flush: true)
        assertEquals(1,mar.count())
        def asg = new Asignacion(anio: ani, marcoLogico: mar).save(flush: true)
        assertEquals(1,asg.count())
        def par = new Parroquia(numero: 1, nombre: 'Eloy Alfaro').save(flush: true)
        assertEquals(1, par.count())
        def tpm = new TipoMeta(descripcion: 'pruebaTipoMeta').save(flush: true)
        assertEquals(1, tpm.count())
        def und = new Unidad(codigo: '123', descripcion: 'pruebaUnidad').save(flush: true)
        assertEquals(1, und.count())
        def met = new Meta(parroquia: par, estado: 0, marcoLogico: mar, asignacion: asg, unidad: und,
                anio: ani, descripcion: 'pruebaMeta').save(flush: true)
        assertEquals(1, met.count())

    }

    void testUpdate () {

        def testInstances = []
        mockDomain(Parroquia, testInstances)
        mockDomain(Meta, testInstances)
        mockDomain(MarcoLogico, testInstances)
        mockDomain(TipoMeta, testInstances)
        mockDomain(Anio, testInstances)
        mockDomain(Asignacion, testInstances)
        mockDomain(Unidad, testInstances)

        def ani = new Anio(anio: '2014', estado: 1).save(flush: true)
        assertEquals(1,ani.count())
        def mar = new MarcoLogico(objeto: 'pruebaMar').save(flush: true)
        assertEquals(1,mar.count())
        def asg = new Asignacion(anio: ani, marcoLogico: mar).save(flush: true)
        assertEquals(1,asg.count())
        def par = new Parroquia(numero: 1, nombre: 'Eloy Alfaro').save(flush: true)
        assertEquals(1, par.count())
        def tpm = new TipoMeta(descripcion: 'pruebaTipoMeta').save(flush: true)
        assertEquals(1, tpm.count())
        def und = new Unidad(codigo: '123', descripcion: 'pruebaUnidad').save(flush: true)
        assertEquals(1, und.count())
        def met = new Meta(parroquia: par, estado: 0, marcoLogico: mar, asignacion: asg, unidad: und,
                anio: ani, descripcion: 'pruebaMeta').save(flush: true)
        assertEquals(1, met.count())

        def met2 = Meta.findByDescripcion('pruebaMeta')
        met2.descripcion = 'nuevaMeta'
        met2.save(flush: true)
        assertEquals(1, met2.count())
        assertEquals('nuevaMeta', met2.descripcion)

    }

    void testDelete () {

        def testInstances = []
        mockDomain(Parroquia, testInstances)
        mockDomain(Meta, testInstances)
        mockDomain(MarcoLogico, testInstances)
        mockDomain(TipoMeta, testInstances)
        mockDomain(Anio, testInstances)
        mockDomain(Asignacion, testInstances)
        mockDomain(Unidad, testInstances)

        def ani = new Anio(anio: '2014', estado: 1).save(flush: true)
        assertEquals(1,ani.count())
        def mar = new MarcoLogico(objeto: 'pruebaMar').save(flush: true)
        assertEquals(1,mar.count())
        def asg = new Asignacion(anio: ani, marcoLogico: mar).save(flush: true)
        assertEquals(1,asg.count())
        def par = new Parroquia(numero: 1, nombre: 'Eloy Alfaro').save(flush: true)
        assertEquals(1, par.count())
        def tpm = new TipoMeta(descripcion: 'pruebaTipoMeta').save(flush: true)
        assertEquals(1, tpm.count())
        def und = new Unidad(codigo: '123', descripcion: 'pruebaUnidad').save(flush: true)
        assertEquals(1, und.count())
        def met = new Meta(parroquia: par, estado: 0, marcoLogico: mar, asignacion: asg, unidad: und,
                anio: ani, descripcion: 'pruebaMeta').save(flush: true)
        assertEquals(1, met.count())
        met.delete()
        assertEquals(0, met.count())

    }
}
