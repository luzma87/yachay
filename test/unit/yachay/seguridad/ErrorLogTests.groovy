package yachay.seguridad

import grails.test.*
import yachay.avales.Certificacion
import yachay.parametros.poaPac.Anio
import yachay.poa.Asignacion
import yachay.proyectos.MarcoLogico

class ErrorLogTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {

        def testInstances = []
        mockDomain(ErrorLog, testInstances)
        mockDomain(Persona, testInstances)
        mockDomain(Usro, testInstances)
        mockDomain(Anio,testInstances)

        def per = new Persona(cedula: '1716473325', nombre: 'prueba',
                apellido: 'prueba', sexo: 'M', discapacitado: '1', direccion: 'prueba', telefono: '123', mail: 'a@a.com', fax: '123', observaciones: 'prueba').save(flush: true)
        assertEquals(1,per.count())
        def usu = new Usro(persona: per, usroLogin: 'pruebaLogin', usroPassword: '123', autorizacion: '123', sigla: 'usro', usroActivo: 1).save(flush: true)
        assertEquals(1,usu.count())

        def erl = new ErrorLog(error: 'pruebaError', fecha: new Date(), usuario: usu, causa: 'pruebaCausa', url: 'pruebaUrl').save(flush: true)
        assertEquals(1, erl.count())

    }

    void testUpdate () {

        def testInstances = []
        mockDomain(ErrorLog, testInstances)
        mockDomain(Persona, testInstances)
        mockDomain(Usro, testInstances)
        mockDomain(Anio,testInstances)

        def per = new Persona(cedula: '1716473325', nombre: 'prueba',
                apellido: 'prueba', sexo: 'M', discapacitado: '1', direccion: 'prueba', telefono: '123', mail: 'a@a.com', fax: '123', observaciones: 'prueba').save(flush: true)
        assertEquals(1,per.count())
        def usu = new Usro(persona: per, usroLogin: 'pruebaLogin', usroPassword: '123', autorizacion: '123', sigla: 'usro', usroActivo: 1).save(flush: true)
        assertEquals(1,usu.count())

        def erl = new ErrorLog(error: 'pruebaError', fecha: new Date(), usuario: usu, causa: 'pruebaCausa', url: 'pruebaUrl').save(flush: true)
        assertEquals(1, erl.count())
        def erl2 = ErrorLog.findByError('pruebaError')
        erl2.error = 'nuevoError'
        erl2.save(flush: true)
        assertEquals(1, erl2.count())
        assertEquals('nuevoError', erl2.error)
    }

    void testDelete () {


        def testInstances = []
        mockDomain(ErrorLog, testInstances)
        mockDomain(Persona, testInstances)
        mockDomain(Usro, testInstances)
        mockDomain(Anio,testInstances)

        def per = new Persona(cedula: '1716473325', nombre: 'prueba',
                apellido: 'prueba', sexo: 'M', discapacitado: '1', direccion: 'prueba', telefono: '123', mail: 'a@a.com', fax: '123', observaciones: 'prueba').save(flush: true)
        assertEquals(1,per.count())
        def usu = new Usro(persona: per, usroLogin: 'pruebaLogin', usroPassword: '123', autorizacion: '123', sigla: 'usro', usroActivo: 1).save(flush: true)
        assertEquals(1,usu.count())

        def erl = new ErrorLog(error: 'pruebaError', fecha: new Date(), usuario: usu, causa: 'pruebaCausa', url: 'pruebaUrl').save(flush: true)
        assertEquals(1, erl.count())
        erl.delete()
        assertEquals(0, erl.count())
    }
}
