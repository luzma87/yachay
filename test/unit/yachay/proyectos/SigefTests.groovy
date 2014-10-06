package yachay.proyectos

import grails.test.*
import yachay.parametros.poaPac.Anio
import yachay.parametros.poaPac.Mes

class SigefTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {

        def testInstances = []
        mockDomain(Anio, testInstances)
        mockDomain(Sigef, testInstances)
        mockDomain(Mes, testInstances)

        def ani = new Anio(anio: '2014', estado: 1).save(flush: true)
        assertEquals(1,ani.count())
        def mes = new Mes(numero: 1, descripcion: 'Enero').save(flush: true)
        assertEquals(1 , mes.count())
        def sgf = new Sigef(anio: ani, mes: mes, fecha: new Date(), archivo: 'archivo').save(flush: true)
        assertEquals(1, sgf.count())

    }

    void testUpdate () {

        def testInstances = []
        mockDomain(Anio, testInstances)
        mockDomain(Sigef, testInstances)
        mockDomain(Mes, testInstances)

        def ani = new Anio(anio: '2014', estado: 1).save(flush: true)
        assertEquals(1,ani.count())
        def mes = new Mes(numero: 1, descripcion: 'Enero').save(flush: true)
        assertEquals(1 , mes.count())
        def sgf = new Sigef(anio: ani, mes: mes, fecha: new Date(), archivo: 'archivo').save(flush: true)
        assertEquals(1, sgf.count())

        def sgf2 = Sigef.findByAnio(ani)
        sgf2.archivo = 'nuevoArchivo'
        sgf2.save(flush: true)
        assertEquals(1, sgf2.count())
        assertEquals('nuevoArchivo', sgf2.archivo)

    }


    void testDelete () {

        def testInstances = []
        mockDomain(Anio, testInstances)
        mockDomain(Sigef, testInstances)
        mockDomain(Mes, testInstances)

        def ani = new Anio(anio: '2014', estado: 1).save(flush: true)
        assertEquals(1,ani.count())
        def mes = new Mes(numero: 1, descripcion: 'Enero').save(flush: true)
        assertEquals(1 , mes.count())
        def sgf = new Sigef(anio: ani, mes: mes, fecha: new Date(), archivo: 'archivo').save(flush: true)
        assertEquals(1, sgf.count())
        sgf.delete()
        assertEquals(0, sgf.count())


    }
}
