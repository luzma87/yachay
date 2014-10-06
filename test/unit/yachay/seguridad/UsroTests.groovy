package yachay.seguridad

import grails.test.*
import yachay.avales.Certificacion
import yachay.parametros.poaPac.Anio
import yachay.poa.Asignacion
import yachay.proyectos.MarcoLogico

class UsroTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {

        def testInstances = []
        mockDomain(Persona, testInstances)
        mockDomain(Usro, testInstances)

        def per = new Persona(cedula: '1716473325', nombre: 'prueba',
                apellido: 'prueba', sexo: 'M', discapacitado: '1', direccion: 'prueba', telefono: '123', mail: 'a@a.com', fax: '123', observaciones: 'prueba').save(flush: true)
        assertEquals(1,per.count())
        def usu = new Usro(persona: per, usroLogin: 'pruebaLogin', usroPassword: '123', autorizacion: '123', sigla: 'usro', usroActivo: 1).save(flush: true)
        assertEquals(1,usu.count())

    }

    void testUpdate () {


        def testInstances = []
        mockDomain(Persona, testInstances)
        mockDomain(Usro, testInstances)

        def per = new Persona(cedula: '1716473325', nombre: 'prueba',
                apellido: 'prueba', sexo: 'M', discapacitado: '1', direccion: 'prueba', telefono: '123', mail: 'a@a.com', fax: '123', observaciones: 'prueba').save(flush: true)
        assertEquals(1,per.count())
        def usu = new Usro(persona: per, usroLogin: 'pruebaLogin', usroPassword: '123', autorizacion: '123', sigla: 'usro', usroActivo: 1).save(flush: true)
        assertEquals(1,usu.count())

        def usu2 = Usro.findByUsroPassword('123')
        usu2.usroPassword = '456'
        usu2.save(flush: true)
        assertEquals(1, usu2.count())
        assertEquals('456', usu2.usroPassword)

    }


    void testDelete () {


        def testInstances = []
        mockDomain(Persona, testInstances)
        mockDomain(Usro, testInstances)

        def per = new Persona(cedula: '1716473325', nombre: 'prueba',
                apellido: 'prueba', sexo: 'M', discapacitado: '1', direccion: 'prueba', telefono: '123', mail: 'a@a.com', fax: '123', observaciones: 'prueba').save(flush: true)
        assertEquals(1,per.count())
        def usu = new Usro(persona: per, usroLogin: 'pruebaLogin', usroPassword: '123', autorizacion: '123', sigla: 'usro', usroActivo: 1).save(flush: true)
        assertEquals(1,usu.count())
        usu.delete()
        assertEquals(0, usu.count())

    }
}
