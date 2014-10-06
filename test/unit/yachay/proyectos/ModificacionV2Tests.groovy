package yachay.proyectos

import grails.test.*
import yachay.avales.Certificacion
import yachay.parametros.poaPac.Anio
import yachay.poa.Asignacion
import yachay.seguridad.Persona
import yachay.seguridad.Usro

class ModificacionV2Tests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {

        def testInstances = []
        mockDomain(ModificacionV2, testInstances)
        mockDomain(Persona, testInstances)
        mockDomain(Usro, testInstances)

        def per = new Persona(cedula: '1716473325', nombre: 'prueba',
                apellido: 'prueba', sexo: 'M', discapacitado: '1', direccion: 'prueba', telefono: '123', mail: 'a@a.com', fax: '123', observaciones: 'prueba').save(flush: true)
        assertEquals(1,per.count())
        def usu = new Usro(persona: per, usroLogin: 'pruebaLogin', usroPassword: '123', autorizacion: '123', sigla: 'usro', usroActivo: 1).save(flush: true)
        assertEquals(1,usu.count())

        def mdv = new ModificacionV2(dominio: 'pruebaDominio', fecha: new Date(), usuario: usu).save(flush: true)
        assertEquals(1, mdv.count())

    }

    void testUpdate () {

        def testInstances = []
        mockDomain(ModificacionV2, testInstances)
        mockDomain(Persona, testInstances)
        mockDomain(Usro, testInstances)

        def per = new Persona(cedula: '1716473325', nombre: 'prueba',
                apellido: 'prueba', sexo: 'M', discapacitado: '1', direccion: 'prueba', telefono: '123', mail: 'a@a.com', fax: '123', observaciones: 'prueba').save(flush: true)
        assertEquals(1,per.count())
        def usu = new Usro(persona: per, usroLogin: 'pruebaLogin', usroPassword: '123', autorizacion: '123', sigla: 'usro', usroActivo: 1).save(flush: true)
        assertEquals(1,usu.count())

        def mdv = new ModificacionV2(dominio: 'pruebaDominio', fecha: new Date(), usuario: usu).save(flush: true)
        assertEquals(1, mdv.count())

        def mdv2 = ModificacionV2.findByUsuario(usu)
        mdv2.dominio = 'nuevoDominio'
        mdv2.save(flush: true)
        assertEquals(1, mdv2.count())
        assertEquals('nuevoDominio', mdv2.dominio)
    }

    void testDelete () {


        def testInstances = []
        mockDomain(ModificacionV2, testInstances)
        mockDomain(Persona, testInstances)
        mockDomain(Usro, testInstances)

        def per = new Persona(cedula: '1716473325', nombre: 'prueba',
                apellido: 'prueba', sexo: 'M', discapacitado: '1', direccion: 'prueba', telefono: '123', mail: 'a@a.com', fax: '123', observaciones: 'prueba').save(flush: true)
        assertEquals(1,per.count())
        def usu = new Usro(persona: per, usroLogin: 'pruebaLogin', usroPassword: '123', autorizacion: '123', sigla: 'usro', usroActivo: 1).save(flush: true)
        assertEquals(1,usu.count())

        def mdv = new ModificacionV2(dominio: 'pruebaDominio', fecha: new Date(), usuario: usu).save(flush: true)
        assertEquals(1, mdv.count())
        mdv.delete()
        assertEquals(0, mdv.count())

    }
}
