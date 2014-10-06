package yachay.avales

import grails.test.*
import yachay.parametros.poaPac.Anio
import yachay.poa.Asignacion
import yachay.proyectos.MarcoLogico
import yachay.seguridad.Persona
import yachay.seguridad.Usro

class CertificacionTests extends GrailsUnitTestCase {
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
        mockDomain(Anio,testInstances)
        mockDomain(MarcoLogico, testInstances)
        mockDomain(Asignacion, testInstances)
        mockDomain(Certificacion, testInstances)

        def per = new Persona(cedula: '1716473325', nombre: 'prueba',
                apellido: 'prueba', sexo: 'M', discapacitado: '1', direccion: 'prueba', telefono: '123', mail: 'a@a.com', fax: '123', observaciones: 'prueba').save(flush: true)
        assertEquals(1,per.count())
        def usu = new Usro(persona: per, usroLogin: 'pruebaLogin', usroPassword: '123', autorizacion: '123', sigla: 'usro', usroActivo: 1).save(flush: true)
        assertEquals(1,usu.count())
        def ani = new Anio(anio: '2014', estado: 1).save(flush: true)
        assertEquals(1,ani.count())
        def mar = new MarcoLogico(objeto: 'pruebaMar').save(flush: true)
        assertEquals(1,mar.count())
        def asg = new Asignacion(anio: ani, marcoLogico: mar).save(flush: true)
        assertEquals(1,asg.count())
        def cer = new Certificacion(usuario: usu, asignacion: asg, fecha: new Date(), monto: 150,
                estado: 1, memorandoSolicitud: '123', concepto: 'prueba').save(flush: true)
        assertEquals(1,cer.count())

    }

    void testUpdate () {

        def testInstances = []
        mockDomain(Persona, testInstances)
        mockDomain(Usro, testInstances)
        mockDomain(Anio,testInstances)
        mockDomain(MarcoLogico, testInstances)
        mockDomain(Asignacion, testInstances)
        mockDomain(Certificacion, testInstances)

        def per = new Persona(cedula: '1716473325', nombre: 'prueba',
                apellido: 'prueba', sexo: 'M', discapacitado: '1', direccion: 'prueba', telefono: '123', mail: 'a@a.com', fax: '123', observaciones: 'prueba').save(flush: true)
        assertEquals(1,per.count())
        def usu = new Usro(persona: per, usroLogin: 'pruebaLogin', usroPassword: '123', autorizacion: '123', sigla: 'usro', usroActivo: 1).save(flush: true)
        assertEquals(1,usu.count())
        def ani = new Anio(anio: '2014', estado: 1).save(flush: true)
        assertEquals(1,ani.count())
        def mar = new MarcoLogico(objeto: 'pruebaMar').save(flush: true)
        assertEquals(1,mar.count())
        def asg = new Asignacion(anio: ani, marcoLogico: mar).save(flush: true)
        assertEquals(1,asg.count())
        def cer = new Certificacion(usuario: usu, asignacion: asg, fecha: new Date(), monto: 150,
                estado: 1, memorandoSolicitud: '123', concepto: 'prueba').save(flush: true)
        assertEquals(1,cer.count())
        def cer2 = Certificacion.findByAsignacion(asg)
//        println("cert " + cer.memorandoSolicitud)
        cer2.memorandoSolicitud = '456'
        cer2.save(flush: true)
//        println("cert1 " + cer.memorandoSolicitud)
        assertEquals(1, cer2.count() )
        assertEquals("456", cer2.memorandoSolicitud)

    }

    void testDelete () {

        def testInstances = []
        mockDomain(Persona, testInstances)
        mockDomain(Usro, testInstances)
        mockDomain(Anio,testInstances)
        mockDomain(MarcoLogico, testInstances)
        mockDomain(Asignacion, testInstances)
        mockDomain(Certificacion, testInstances)

        def per = new Persona(cedula: '1716473325', nombre: 'prueba',
                apellido: 'prueba', sexo: 'M', discapacitado: '1', direccion: 'prueba', telefono: '123', mail: 'a@a.com', fax: '123', observaciones: 'prueba').save(flush: true)
        assertEquals(1,per.count())
        def usu = new Usro(persona: per, usroLogin: 'pruebaLogin', usroPassword: '123', autorizacion: '123', sigla: 'usro', usroActivo: 1).save(flush: true)
        assertEquals(1,usu.count())
        def ani = new Anio(anio: '2014', estado: 1).save(flush: true)
        assertEquals(1,ani.count())
        def mar = new MarcoLogico(objeto: 'pruebaMar').save(flush: true)
        assertEquals(1,mar.count())
        def asg = new Asignacion(anio: ani, marcoLogico: mar).save(flush: true)
        assertEquals(1,asg.count())
        def cer = new Certificacion(usuario: usu, asignacion: asg, fecha: new Date(), monto: 150,
                estado: 1, memorandoSolicitud: '123', concepto: 'prueba').save(flush: true)
        assertEquals(1,cer.count())
        cer.delete()
        assertEquals(0,cer.count())

    }
}
