package yachay.avales

import grails.test.*
import yachay.parametros.TipoInstitucion
import yachay.parametros.UnidadEjecutora
import yachay.parametros.poaPac.Anio
import yachay.poa.Asignacion
import yachay.proyectos.MarcoLogico


class DistribucionAsignacionTests extends GrailsUnitTestCase {
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
        mockDomain(TipoInstitucion, testInstances)
        mockDomain(UnidadEjecutora, testInstances)
        mockDomain(DistribucionAsignacion, testInstances)

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
        def das = new DistribucionAsignacion(asignacion: asg, unidadEjecutora: uni, valor: 150.55).save(flush: true)
        assertEquals(1,das.count())

    }

    void testUpdate () {
        def testInstances = []
        mockDomain(Anio,testInstances)
        mockDomain(MarcoLogico, testInstances)
        mockDomain(Asignacion, testInstances)
        mockDomain(TipoInstitucion, testInstances)
        mockDomain(UnidadEjecutora, testInstances)
        mockDomain(DistribucionAsignacion, testInstances)

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
        def das = new DistribucionAsignacion(asignacion: asg, unidadEjecutora: uni, valor: 150.55).save(flush: true)
        assertEquals(1,das.count())
        def das2 = DistribucionAsignacion.findByAsignacion(asg)
//        println("Dis " + das2.valor)
        das2.valor = 220.40
        das2.save(flush: true)
//        println("dis1 " + das2.valor)
        assertEquals(1, das2.count())
        assertEquals(220.40, das2.valor)

    }


    void testDelete () {

        def testInstances = []
        mockDomain(Anio,testInstances)
        mockDomain(MarcoLogico, testInstances)
        mockDomain(Asignacion, testInstances)
        mockDomain(TipoInstitucion, testInstances)
        mockDomain(UnidadEjecutora, testInstances)
        mockDomain(DistribucionAsignacion, testInstances)

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
        def das = new DistribucionAsignacion(asignacion: asg, unidadEjecutora: uni, valor: 150.55).save(flush: true)
        assertEquals(1,das.count())
        das.delete()
        assertEquals(0,das.count())

    }
}
