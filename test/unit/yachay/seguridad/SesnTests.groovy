package yachay.seguridad

import grails.test.*
import yachay.avales.Certificacion
import yachay.parametros.poaPac.Anio
import yachay.poa.Asignacion
import yachay.proyectos.MarcoLogico

class SesnTests extends GrailsUnitTestCase {
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
        mockDomain(Prfl, testInstances)
        mockDomain(Sesn, testInstances)

        def per = new Persona(cedula: '1716473325', nombre: 'prueba',
                apellido: 'prueba', sexo: 'M', discapacitado: '1', direccion: 'prueba', telefono: '123', mail: 'a@a.com', fax: '123', observaciones: 'prueba').save(flush: true)
        assertEquals(1,per.count())
        def usu = new Usro(persona: per, usroLogin: 'pruebaLogin', usroPassword: '123', autorizacion: '123', sigla: 'usro', usroActivo: 1).save(flush: true)
        assertEquals(1,usu.count())
        def prf = new Prfl(nombre: 'perfil', descripcion: 'pruebaPerfil', codigo: '123', observaciones: 'observacion').save(flush: true)
        assertEquals(1, prf.count())
        def ses = new Sesn(perfil: prf, usuario: usu).save(flush: true)
        assertEquals(1, ses.count())

    }

    void testUpdate () {

        def testInstances = []
        mockDomain(Persona, testInstances)
        mockDomain(Usro, testInstances)
        mockDomain(Prfl, testInstances)
        mockDomain(Sesn, testInstances)

        def per = new Persona(cedula: '1716473325', nombre: 'prueba',
                apellido: 'prueba', sexo: 'M', discapacitado: '1', direccion: 'prueba', telefono: '123', mail: 'a@a.com', fax: '123', observaciones: 'prueba').save(flush: true)
        assertEquals(1,per.count())
        def usu = new Usro(persona: per, usroLogin: 'pruebaLogin', usroPassword: '123', autorizacion: '123', sigla: 'usro', usroActivo: 1).save(flush: true)
        assertEquals(1,usu.count())
        def prf = new Prfl(nombre: 'perfil', descripcion: 'pruebaPerfil', codigo: '123', observaciones: 'observacion').save(flush: true)
        assertEquals(1, prf.count())
        def ses = new Sesn(perfil: prf, usuario: usu).save(flush: true)
        assertEquals(1, ses.count())

        def ses2 = Sesn.findByUsuario(usu)
        ses2.usuario.persona.nombre = 'nuevoUsuario'
        ses2.save(flush: true)
        assertEquals(1, ses2.count())
        assertEquals('nuevoUsuario',ses2.usuario.persona.nombre)

    }

    void testDelete () {

        def testInstances = []
        mockDomain(Persona, testInstances)
        mockDomain(Usro, testInstances)
        mockDomain(Prfl, testInstances)
        mockDomain(Sesn, testInstances)

        def per = new Persona(cedula: '1716473325', nombre: 'prueba',
                apellido: 'prueba', sexo: 'M', discapacitado: '1', direccion: 'prueba', telefono: '123', mail: 'a@a.com', fax: '123', observaciones: 'prueba').save(flush: true)
        assertEquals(1,per.count())
        def usu = new Usro(persona: per, usroLogin: 'pruebaLogin', usroPassword: '123', autorizacion: '123', sigla: 'usro', usroActivo: 1).save(flush: true)
        assertEquals(1,usu.count())
        def prf = new Prfl(nombre: 'perfil', descripcion: 'pruebaPerfil', codigo: '123', observaciones: 'observacion').save(flush: true)
        assertEquals(1, prf.count())
        def ses = new Sesn(perfil: prf, usuario: usu).save(flush: true)
        assertEquals(1, ses.count())
        ses.delete()
        assertEquals(0, ses.count())

    }

}

