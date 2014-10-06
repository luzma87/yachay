package yachay.seguridad

import grails.test.*
import yachay.avales.Certificacion
import yachay.parametros.poaPac.Anio
import yachay.poa.Asignacion
import yachay.proyectos.MarcoLogico

class AccsTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {

        def testInstances = []
        mockDomain(Accs, testInstances)
        mockDomain(Persona, testInstances)
        mockDomain(Usro, testInstances)

        def per = new Persona(cedula: '1716473325', nombre: 'prueba',
                apellido: 'prueba', sexo: 'M', discapacitado: '1', direccion: 'prueba', telefono: '123', mail: 'a@a.com', fax: '123', observaciones: 'prueba').save(flush: true)
        assertEquals(1,per.count())
        def usu = new Usro(persona: per, usroLogin: 'pruebaLogin', usroPassword: '123', autorizacion: '123', sigla: 'usro', usroActivo: 1).save(flush: true)
        assertEquals(1,usu.count())

        def acc = new Accs(accsFechaFinal: new Date(), accsFechaInicial: new Date(),
                accsObservaciones: 'pruebaAccs', usuario: usu).save(flush: true)
        assertEquals(1, acc.count())

    }


    void testUpdate () {
        def testInstances = []
        mockDomain(Accs, testInstances)
        mockDomain(Persona, testInstances)
        mockDomain(Usro, testInstances)

        def per = new Persona(cedula: '1716473325', nombre: 'prueba',
                apellido: 'prueba', sexo: 'M', discapacitado: '1', direccion: 'prueba', telefono: '123', mail: 'a@a.com', fax: '123', observaciones: 'prueba').save(flush: true)
        assertEquals(1,per.count())
        def usu = new Usro(persona: per, usroLogin: 'pruebaLogin', usroPassword: '123', autorizacion: '123', sigla: 'usro', usroActivo: 1).save(flush: true)
        assertEquals(1,usu.count())

        def acc = new Accs(accsFechaFinal: new Date(), accsFechaInicial: new Date(),
                accsObservaciones: 'pruebaAccs', usuario: usu).save(flush: true)
        assertEquals(1, acc.count())

        def acc2 = Accs.findByAccsObservaciones('pruebaAccs')
        acc2.accsObservaciones = 'nuevoAccs'
        acc2.save(flush: true)
        assertEquals(1,acc2.count())
        assertEquals('nuevoAccs', acc2.accsObservaciones)


    }

    void testDelete () {

        def testInstances = []
        mockDomain(Accs, testInstances)
        mockDomain(Persona, testInstances)
        mockDomain(Usro, testInstances)

        def per = new Persona(cedula: '1716473325', nombre: 'prueba',
                apellido: 'prueba', sexo: 'M', discapacitado: '1', direccion: 'prueba', telefono: '123', mail: 'a@a.com', fax: '123', observaciones: 'prueba').save(flush: true)
        assertEquals(1,per.count())
        def usu = new Usro(persona: per, usroLogin: 'pruebaLogin', usroPassword: '123', autorizacion: '123', sigla: 'usro', usroActivo: 1).save(flush: true)
        assertEquals(1,usu.count())

        def acc = new Accs(accsFechaFinal: new Date(), accsFechaInicial: new Date(),
                accsObservaciones: 'pruebaAccs', usuario: usu).save(flush: true)
        assertEquals(1, acc.count())
        acc.delete()
        assertEquals(0, acc.count())
    }
}
