package yachay.proyectos

import grails.test.*
import yachay.parametros.poaPac.Anio
import yachay.parametros.poaPac.Presupuesto

class CronogramaTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {

     def testInstance = []
     mockDomain(Cronograma, testInstance)
     mockDomain(Anio, testInstance)
     mockDomain(Presupuesto, testInstance)

     def ani = new Anio(anio: '2014', estado: 1).save(flush: true)
     assertEquals(1,ani.count())
     def prs = new Presupuesto(numero: '123', descripcion: 'pruebaPresupuesto', nivel: 1, movimiento: 1).save(flush: true)
     assertEquals(1,prs.count())

      def crn = new Cronograma(fechaFin: new Date(), fechaInicio: new Date(), anio: ani, valor: 150, valor2: 140,
              presupuesto: prs).save(flush: true)
      assertEquals(1, crn.count())

    }

    void testUpdate () {

        def testInstance = []
        mockDomain(Cronograma, testInstance)
        mockDomain(Anio, testInstance)
        mockDomain(Presupuesto, testInstance)

        def ani = new Anio(anio: '2014', estado: 1).save(flush: true)
        assertEquals(1,ani.count())
        def prs = new Presupuesto(numero: '123', descripcion: 'pruebaPresupuesto', nivel: 1, movimiento: 1).save(flush: true)
        assertEquals(1,prs.count())

        def crn = new Cronograma(fechaFin: new Date(), fechaInicio: new Date(), anio: ani, valor: 150, valor2: 140,
                presupuesto: prs).save(flush: true)
        assertEquals(1, crn.count())

        def crn2 = Cronograma.findByAnio(ani)
        crn2.valor = 250.50
        crn2.save(flush: true)
        assertEquals(1, crn2.count())
        assertEquals(250.50, crn2.valor)
    }

    void testDelete() {
        def testInstance = []
        mockDomain(Cronograma, testInstance)
        mockDomain(Anio, testInstance)
        mockDomain(Presupuesto, testInstance)

        def ani = new Anio(anio: '2014', estado: 1).save(flush: true)
        assertEquals(1,ani.count())
        def prs = new Presupuesto(numero: '123', descripcion: 'pruebaPresupuesto', nivel: 1, movimiento: 1).save(flush: true)
        assertEquals(1,prs.count())

        def crn = new Cronograma(fechaFin: new Date(), fechaInicio: new Date(), anio: ani, valor: 150, valor2: 140,
                presupuesto: prs).save(flush: true)
        assertEquals(1, crn.count())

        crn.delete()
        assertEquals(0, crn.count())

    }
}
