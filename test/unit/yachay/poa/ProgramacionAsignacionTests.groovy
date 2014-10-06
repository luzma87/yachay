package yachay.poa

import grails.test.*
import yachay.avales.DistribucionAsignacion
import yachay.parametros.TipoInstitucion
import yachay.parametros.UnidadEjecutora
import yachay.parametros.poaPac.Anio
import yachay.parametros.poaPac.Mes
import yachay.proyectos.MarcoLogico

class ProgramacionAsignacionTests extends GrailsUnitTestCase {
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
        mockDomain(Mes, testInstances)
        mockDomain(TipoInstitucion, testInstances)
        mockDomain(UnidadEjecutora, testInstances)
        mockDomain(DistribucionAsignacion, testInstances)
        mockDomain(ProgramacionAsignacion, testInstances)

        def ani = new Anio(anio: '2014', estado: 1).save(flush: true)
        assertEquals(1,ani.count())
        def mar = new MarcoLogico(objeto: 'pruebaMar').save(flush: true)
        assertEquals(1,mar.count())
        def asg = new Asignacion(anio: ani, marcoLogico: mar).save(flush: true)
        assertEquals(1,asg.count())
        def mes = new Mes(descripcion: 'Enero', numero: 1).save(flush: true)
        assertEquals(1, mes.count())
        def ins = new TipoInstitucion(codigo: '12', descripcion: 'pruebaIns').save(flush: true)
        assertEquals(1,ins.count())
        def uni = new UnidadEjecutora(nombre: 'pruebaUnej', tipoInstitucion: ins).save(flush: true)
        assertEquals(1,uni.count())
        def das = new DistribucionAsignacion(asignacion: asg, unidadEjecutora: uni, valor: 150.55).save(flush: true)
        assertEquals(1,das.count())

        def pra =  new ProgramacionAsignacion(asignacion: asg, mes: mes, estado: 1,
                distribucion: das, valor: 1500.50).save(flush: true)
        assertEquals(1, pra.count())

    }

    void testUpdate () {

        def testInstances = []
        mockDomain(Anio,testInstances)
        mockDomain(MarcoLogico, testInstances)
        mockDomain(Asignacion, testInstances)
        mockDomain(Mes, testInstances)
        mockDomain(TipoInstitucion, testInstances)
        mockDomain(UnidadEjecutora, testInstances)
        mockDomain(DistribucionAsignacion, testInstances)
        mockDomain(ProgramacionAsignacion, testInstances)

        def ani = new Anio(anio: '2014', estado: 1).save(flush: true)
        assertEquals(1,ani.count())
        def mar = new MarcoLogico(objeto: 'pruebaMar').save(flush: true)
        assertEquals(1,mar.count())
        def asg = new Asignacion(anio: ani, marcoLogico: mar).save(flush: true)
        assertEquals(1,asg.count())
        def mes = new Mes(descripcion: 'Enero', numero: 1).save(flush: true)
        assertEquals(1, mes.count())
        def ins = new TipoInstitucion(codigo: '12', descripcion: 'pruebaIns').save(flush: true)
        assertEquals(1,ins.count())
        def uni = new UnidadEjecutora(nombre: 'pruebaUnej', tipoInstitucion: ins).save(flush: true)
        assertEquals(1,uni.count())
        def das = new DistribucionAsignacion(asignacion: asg, unidadEjecutora: uni, valor: 150.55).save(flush: true)
        assertEquals(1,das.count())

        def pra =  new ProgramacionAsignacion(asignacion: asg, mes: mes, estado: 1,
                distribucion: das, valor: 1500.50).save(flush: true)
        assertEquals(1, pra.count())

        def pra2 = ProgramacionAsignacion.findByAsignacion(asg)
        pra2.estado = 0
        pra2.save(flush: true)
        assertEquals(1, pra2.count())
        assertEquals(0, pra2.estado)

    }

    void testDelete () {

        def testInstances = []
        mockDomain(Anio,testInstances)
        mockDomain(MarcoLogico, testInstances)
        mockDomain(Asignacion, testInstances)
        mockDomain(Mes, testInstances)
        mockDomain(TipoInstitucion, testInstances)
        mockDomain(UnidadEjecutora, testInstances)
        mockDomain(DistribucionAsignacion, testInstances)
        mockDomain(ProgramacionAsignacion, testInstances)

        def ani = new Anio(anio: '2014', estado: 1).save(flush: true)
        assertEquals(1,ani.count())
        def mar = new MarcoLogico(objeto: 'pruebaMar').save(flush: true)
        assertEquals(1,mar.count())
        def asg = new Asignacion(anio: ani, marcoLogico: mar).save(flush: true)
        assertEquals(1,asg.count())
        def mes = new Mes(descripcion: 'Enero', numero: 1).save(flush: true)
        assertEquals(1, mes.count())
        def ins = new TipoInstitucion(codigo: '12', descripcion: 'pruebaIns').save(flush: true)
        assertEquals(1,ins.count())
        def uni = new UnidadEjecutora(nombre: 'pruebaUnej', tipoInstitucion: ins).save(flush: true)
        assertEquals(1,uni.count())
        def das = new DistribucionAsignacion(asignacion: asg, unidadEjecutora: uni, valor: 150.55).save(flush: true)
        assertEquals(1,das.count())

        def pra =  new ProgramacionAsignacion(asignacion: asg, mes: mes, estado: 1,
                distribucion: das, valor: 1500.50).save(flush: true)
        assertEquals(1, pra.count())

        pra.delete()
        assertEquals(0, pra.count())


    }
}
