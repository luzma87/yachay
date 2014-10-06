package yachay.proyectos

import grails.test.*
import yachay.avales.Certificacion
import yachay.parametros.poaPac.Anio
import yachay.poa.Asignacion
import yachay.seguridad.Persona
import yachay.seguridad.Usro

class ModificacionTechosTests extends GrailsUnitTestCase {
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
        mockDomain(ModificacionTechos, testInstances)

        def per = new Persona(cedula: '1716473325', nombre: 'prueba',
                apellido: 'prueba', sexo: 'M', discapacitado: '1', direccion: 'prueba', telefono: '123', mail: 'a@a.com', fax: '123', observaciones: 'prueba').save(flush: true)
        assertEquals(1,per.count())
        def usu = new Usro(persona: per, usroLogin: 'pruebaLogin', usroPassword: '123', autorizacion: '123', sigla: 'usro', usroActivo: 1).save(flush: true)
        assertEquals(1,usu.count())
        def mtc = new ModificacionTechos(usuario: usu).save(flush: true)
        assertEquals(1, mtc.count())

    }

    void testUpdate (){

        def testInstances = []

        mockDomain(Persona, testInstances)
        mockDomain(Usro, testInstances)
        mockDomain(ModificacionTechos, testInstances)

        def per = new Persona(cedula: '1716473325', nombre: 'prueba',
                apellido: 'prueba', sexo: 'M', discapacitado: '1', direccion: 'prueba', telefono: '123', mail: 'a@a.com', fax: '123', observaciones: 'prueba').save(flush: true)
        assertEquals(1,per.count())
        def usu = new Usro(persona: per, usroLogin: 'pruebaLogin', usroPassword: '123', autorizacion: '123', sigla: 'usro', usroActivo: 1).save(flush: true)
        assertEquals(1,usu.count())
        def mtc = new ModificacionTechos(usuario: usu, valor: 123).save(flush: true)
        assertEquals(1, mtc.count())

        def mtc2 = ModificacionTechos.findByUsuario(usu)
        mtc2.valor = 456
        mtc2.save(flush: true)
        assertEquals(1, mtc2.count())
        assertEquals(456, mtc2.valor)
    }

    void testDelete () {

        def testInstances = []

        mockDomain(Persona, testInstances)
        mockDomain(Usro, testInstances)
        mockDomain(ModificacionTechos, testInstances)

        def per = new Persona(cedula: '1716473325', nombre: 'prueba',
                apellido: 'prueba', sexo: 'M', discapacitado: '1', direccion: 'prueba', telefono: '123', mail: 'a@a.com', fax: '123', observaciones: 'prueba').save(flush: true)
        assertEquals(1,per.count())
        def usu = new Usro(persona: per, usroLogin: 'pruebaLogin', usroPassword: '123', autorizacion: '123', sigla: 'usro', usroActivo: 1).save(flush: true)
        assertEquals(1,usu.count())
        def mtc = new ModificacionTechos(usuario: usu).save(flush: true)
        assertEquals(1, mtc.count())
        mtc.delete()
        assertEquals(0, mtc.count())
    }
}
