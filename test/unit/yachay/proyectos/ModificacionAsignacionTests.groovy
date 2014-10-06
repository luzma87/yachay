package yachay.proyectos

import grails.test.*
import yachay.parametros.TipoInstitucion
import yachay.parametros.UnidadEjecutora
import yachay.parametros.poaPac.Anio
import yachay.poa.Asignacion

class ModificacionAsignacionTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {

        def testInstance = []
        mockDomain(ModificacionAsignacion, testInstance)
        mockDomain(Anio, testInstance)
        mockDomain(MarcoLogico, testInstance)
        mockDomain(Asignacion, testInstance)
        mockDomain(UnidadEjecutora, testInstance)
        mockDomain(TipoInstitucion, testInstance)

        def ani = new Anio(anio: '2014', estado: 1).save(flush: true)
        assertEquals(1,ani.count())
        def mar = new MarcoLogico(objeto: 'pruebaMar').save(flush: true)
        assertEquals(1,mar.count())
        def asg = new Asignacion(anio: ani, marcoLogico: mar).save(flush: true)
        assertEquals(1,asg.count())
        def ins = new TipoInstitucion(codigo: '12', descripcion: 'pruebaIns').save(flush: true)
        assertEquals(1,ins.count())
        def uni = new UnidadEjecutora(nombre: 'pruebaUnej', tipoInstitucion: ins).save(flush: true)
        assertEquals(1,uni.count())

        def mas = new ModificacionAsignacion(fecha: new Date(), valor: 4567.89, desde: asg, recibe: asg, unidad: uni).save(flush: true)
        assertEquals(1, mas.count())

    }

    void testUpdate () {


        def testInstance = []
        mockDomain(ModificacionAsignacion, testInstance)
        mockDomain(Anio, testInstance)
        mockDomain(MarcoLogico, testInstance)
        mockDomain(Asignacion, testInstance)
        mockDomain(UnidadEjecutora, testInstance)
        mockDomain(TipoInstitucion, testInstance)

        def ani = new Anio(anio: '2014', estado: 1).save(flush: true)
        assertEquals(1,ani.count())
        def mar = new MarcoLogico(objeto: 'pruebaMar').save(flush: true)
        assertEquals(1,mar.count())
        def asg = new Asignacion(anio: ani, marcoLogico: mar).save(flush: true)
        assertEquals(1,asg.count())
        def ins = new TipoInstitucion(codigo: '12', descripcion: 'pruebaIns').save(flush: true)
        assertEquals(1,ins.count())
        def uni = new UnidadEjecutora(nombre: 'pruebaUnej', tipoInstitucion: ins).save(flush: true)
        assertEquals(1,uni.count())

        def mas = new ModificacionAsignacion(fecha: new Date(), valor: 4567.89, desde: asg, recibe: asg, unidad: uni).save(flush: true)
        assertEquals(1, mas.count())

        def mas2 = ModificacionAsignacion.findByValor(4567.89)
        mas2.valor = 1250
        assertEquals(1, mas2.count())
        assertEquals(1250, mas.valor)

    }

    void testDelete () {

        def testInstance = []
        mockDomain(ModificacionAsignacion, testInstance)
        mockDomain(Anio, testInstance)
        mockDomain(MarcoLogico, testInstance)
        mockDomain(Asignacion, testInstance)
        mockDomain(UnidadEjecutora, testInstance)
        mockDomain(TipoInstitucion, testInstance)

        def ani = new Anio(anio: '2014', estado: 1).save(flush: true)
        assertEquals(1,ani.count())
        def mar = new MarcoLogico(objeto: 'pruebaMar').save(flush: true)
        assertEquals(1,mar.count())
        def asg = new Asignacion(anio: ani, marcoLogico: mar).save(flush: true)
        assertEquals(1,asg.count())
        def ins = new TipoInstitucion(codigo: '12', descripcion: 'pruebaIns').save(flush: true)
        assertEquals(1,ins.count())
        def uni = new UnidadEjecutora(nombre: 'pruebaUnej', tipoInstitucion: ins).save(flush: true)
        assertEquals(1,uni.count())

        def mas = new ModificacionAsignacion(fecha: new Date(), valor: 4567.89, desde: asg, recibe: asg, unidad: uni).save(flush: true)
        assertEquals(1, mas.count())
        mas.delete()
        assertEquals(0, mas.count())
    }
}
