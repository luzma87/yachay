package yachay.proyectos

import grails.test.*
import yachay.parametros.poaPac.Anio
import yachay.parametros.poaPac.Mes
import yachay.poa.Asignacion

class EjecucionTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {

        def testInstances = []
        mockDomain(Anio,testInstances)
        mockDomain(Mes, testInstances)
        mockDomain(MarcoLogico, testInstances)
        mockDomain(Asignacion, testInstances)
        mockDomain(Sigef, testInstances)
        mockDomain(Ejecucion, testInstances)

        def ani = new Anio(anio: '2014', estado: 1).save(flush: true)
        assertEquals(1,ani.count())
        def mes = new Mes(numero: 1, descripcion: 'Enero')
        def mar = new MarcoLogico(objeto: 'pruebaMar').save(flush: true)
        assertEquals(1,mar.count())
        def asg = new Asignacion(anio: ani, marcoLogico: mar).save(flush: true)
        assertEquals(1,asg.count())
        def sig = new Sigef(anio: ani, mes: mes, fecha: new Date(), archivo: '123').save(flush: true)
        assertEquals(1, sig.count())
        def eje = new Ejecucion(asignacion: asg, vigente: 150.60, compromiso: 250, devengado: 350,
                pagado: 200, saldoPresupuesto: 100, saldoDisponible: 50, sigef: sig).save(flush: true)
        assertEquals(1, eje.count())

    }

    void testUpdate () {

        def testInstances = []
        mockDomain(Anio,testInstances)
        mockDomain(Mes, testInstances)
        mockDomain(MarcoLogico, testInstances)
        mockDomain(Asignacion, testInstances)
        mockDomain(Sigef, testInstances)
        mockDomain(Ejecucion, testInstances)

        def ani = new Anio(anio: '2014', estado: 1).save(flush: true)
        assertEquals(1,ani.count())
        def mes = new Mes(numero: 1, descripcion: 'Enero')
        def mar = new MarcoLogico(objeto: 'pruebaMar').save(flush: true)
        assertEquals(1,mar.count())
        def asg = new Asignacion(anio: ani, marcoLogico: mar).save(flush: true)
        assertEquals(1,asg.count())
        def sig = new Sigef(anio: ani, mes: mes, fecha: new Date(), archivo: '123').save(flush: true)
        assertEquals(1, sig.count())
        def eje = new Ejecucion(asignacion: asg, vigente: 150.60, compromiso: 250, devengado: 350,
                pagado: 200, saldoPresupuesto: 100, saldoDisponible: 50, sigef: sig).save(flush: true)
        assertEquals(1, eje.count())

        def eje2 = Ejecucion.findByVigente(150.60)
        eje2.vigente = 200.10
        eje2.save(flush: true)
        assertEquals(1, eje2.count())
        assertEquals(200.10, eje2.vigente)
    }

    void testDelete () {

        def testInstances = []
        mockDomain(Anio,testInstances)
        mockDomain(Mes, testInstances)
        mockDomain(MarcoLogico, testInstances)
        mockDomain(Asignacion, testInstances)
        mockDomain(Sigef, testInstances)
        mockDomain(Ejecucion, testInstances)

        def ani = new Anio(anio: '2014', estado: 1).save(flush: true)
        assertEquals(1,ani.count())
        def mes = new Mes(numero: 1, descripcion: 'Enero')
        def mar = new MarcoLogico(objeto: 'pruebaMar').save(flush: true)
        assertEquals(1,mar.count())
        def asg = new Asignacion(anio: ani, marcoLogico: mar).save(flush: true)
        assertEquals(1,asg.count())
        def sig = new Sigef(anio: ani, mes: mes, fecha: new Date(), archivo: '123').save(flush: true)
        assertEquals(1, sig.count())
        def eje = new Ejecucion(asignacion: asg, vigente: 150.60, compromiso: 250, devengado: 350,
                pagado: 200, saldoPresupuesto: 100, saldoDisponible: 50, sigef: sig).save(flush: true)
        assertEquals(1, eje.count())
        eje.delete()
        assertEquals(0, eje.count())

    }
}
