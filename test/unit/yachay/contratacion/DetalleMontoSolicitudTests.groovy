package yachay.contratacion

import grails.test.*
import yachay.parametros.TipoAprobacion
import yachay.parametros.TipoContrato
import yachay.parametros.TipoInstitucion
import yachay.parametros.UnidadEjecutora
import yachay.parametros.poaPac.Anio
import yachay.proyectos.MarcoLogico
import yachay.seguridad.Persona
import yachay.seguridad.Usro

class DetalleMontoSolicitudTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {
        def testInstances = []
        mockDomain(TipoInstitucion, testInstances)
        mockDomain(UnidadEjecutora, testInstances)
        mockDomain(MarcoLogico, testInstances)
        mockDomain(Persona, testInstances)
        mockDomain(Usro, testInstances)
        mockDomain(TipoContrato, testInstances)
        mockDomain(Solicitud, testInstances)
        mockDomain(Anio, testInstances)
        mockDomain(DetalleMontoSolicitud, testInstances)

        def ins = new TipoInstitucion(codigo: '12', descripcion: 'pruebaIns').save(flush: true)
        assertEquals(1,ins.count())
        def uni = new UnidadEjecutora(nombre: 'pruebaUnej', tipoInstitucion: ins).save(flush: true)
        assertEquals(1,uni.count())
        def mar = new MarcoLogico(objeto: 'pruebaMar').save(flush: true)
        assertEquals(1,mar.count())
        def per = new Persona(cedula: '1716473325', nombre: 'prueba',
                apellido: 'prueba', sexo: 'M', discapacitado: '1', direccion: 'prueba', telefono: '123', mail: 'a@a.com', fax: '123', observaciones: 'prueba').save(flush: true)
        assertEquals(1,per.count())
        def usu = new Usro(persona: per, usroLogin: 'pruebaLogin', usroPassword: '123', autorizacion: '123', sigla: 'usro', usroActivo: 1).save(flush: true)
        assertEquals(1,usu.count())
        def tcn = new TipoContrato(descripcion: 'pruebaTipoContrato').save(flush: true)
        assertEquals(1, tcn.count())
        def sol = new Solicitud(unidadEjecutora: uni, actividad: mar,
                formaPago: 'monedas', objetoContrato: 'prueba',
                usuario: usu, plazoEjecucion: 1, nombreProceso: 'prueba',
                tipoContrato: tcn, montoSolicitado: 123, fecha: new Date() ).save(flush: true)
        assertEquals(1,sol.count())
        def ani = new Anio(anio: '2014', estado: 1).save(flush: true)
        assertEquals(1,ani.count())
        def dms = new DetalleMontoSolicitud(anio: ani, solicitud: sol, monto: 120.12).save(flush: true)
        assertEquals(1,dms.count())
    }

    void testUpdate () {

        def testInstances = []
        mockDomain(TipoInstitucion, testInstances)
        mockDomain(UnidadEjecutora, testInstances)
        mockDomain(MarcoLogico, testInstances)
        mockDomain(Persona, testInstances)
        mockDomain(Usro, testInstances)
        mockDomain(TipoContrato, testInstances)
        mockDomain(Solicitud, testInstances)
        mockDomain(Anio, testInstances)
        mockDomain(DetalleMontoSolicitud, testInstances)

        def ins = new TipoInstitucion(codigo: '12', descripcion: 'pruebaIns').save(flush: true)
        assertEquals(1,ins.count())
        def uni = new UnidadEjecutora(nombre: 'pruebaUnej', tipoInstitucion: ins).save(flush: true)
        assertEquals(1,uni.count())
        def mar = new MarcoLogico(objeto: 'pruebaMar').save(flush: true)
        assertEquals(1,mar.count())
        def per = new Persona(cedula: '1716473325', nombre: 'prueba',
                apellido: 'prueba', sexo: 'M', discapacitado: '1', direccion: 'prueba', telefono: '123', mail: 'a@a.com', fax: '123', observaciones: 'prueba').save(flush: true)
        assertEquals(1,per.count())
        def usu = new Usro(persona: per, usroLogin: 'pruebaLogin', usroPassword: '123', autorizacion: '123', sigla: 'usro', usroActivo: 1).save(flush: true)
        assertEquals(1,usu.count())
        def tcn = new TipoContrato(descripcion: 'pruebaTipoContrato').save(flush: true)
        assertEquals(1, tcn.count())
        def sol = new Solicitud(unidadEjecutora: uni, actividad: mar,
                formaPago: 'monedas', objetoContrato: 'prueba',
                usuario: usu, plazoEjecucion: 1, nombreProceso: 'prueba',
                tipoContrato: tcn, montoSolicitado: 123, fecha: new Date() ).save(flush: true)
        assertEquals(1,sol.count())
        def ani = new Anio(anio: '2014', estado: 1).save(flush: true)
        assertEquals(1,ani.count())
        def dms = new DetalleMontoSolicitud(anio: ani, solicitud: sol, monto: 120.12).save(flush: true)
        assertEquals(1,dms.count())
        def dms2 = DetalleMontoSolicitud.findBySolicitud(sol)
//        println("dms " + dms.monto)
        dms2.monto = 150.50
        dms2.save(flush: true)
//        println("dms1 "  + dms.monto)
        assertEquals(150.50, dms2.monto)

    }

    void testDelete () {

        def testInstances = []
        mockDomain(TipoInstitucion, testInstances)
        mockDomain(UnidadEjecutora, testInstances)
        mockDomain(MarcoLogico, testInstances)
        mockDomain(Persona, testInstances)
        mockDomain(Usro, testInstances)
        mockDomain(TipoContrato, testInstances)
        mockDomain(Solicitud, testInstances)
        mockDomain(Anio, testInstances)
        mockDomain(DetalleMontoSolicitud, testInstances)

        def ins = new TipoInstitucion(codigo: '12', descripcion: 'pruebaIns').save(flush: true)
        assertEquals(1,ins.count())
        def uni = new UnidadEjecutora(nombre: 'pruebaUnej', tipoInstitucion: ins).save(flush: true)
        assertEquals(1,uni.count())
        def mar = new MarcoLogico(objeto: 'pruebaMar').save(flush: true)
        assertEquals(1,mar.count())
        def per = new Persona(cedula: '1716473325', nombre: 'prueba',
                apellido: 'prueba', sexo: 'M', discapacitado: '1', direccion: 'prueba', telefono: '123', mail: 'a@a.com', fax: '123', observaciones: 'prueba').save(flush: true)
        assertEquals(1,per.count())
        def usu = new Usro(persona: per, usroLogin: 'pruebaLogin', usroPassword: '123', autorizacion: '123', sigla: 'usro', usroActivo: 1).save(flush: true)
        assertEquals(1,usu.count())
        def tcn = new TipoContrato(descripcion: 'pruebaTipoContrato').save(flush: true)
        assertEquals(1, tcn.count())
        def sol = new Solicitud(unidadEjecutora: uni, actividad: mar,
                formaPago: 'monedas', objetoContrato: 'prueba',
                usuario: usu, plazoEjecucion: 1, nombreProceso: 'prueba',
                tipoContrato: tcn, montoSolicitado: 123, fecha: new Date() ).save(flush: true)
        assertEquals(1,sol.count())
        def ani = new Anio(anio: '2014', estado: 1).save(flush: true)
        assertEquals(1,ani.count())
        def dms = new DetalleMontoSolicitud(anio: ani, solicitud: sol, monto: 120.12).save(flush: true)
        assertEquals(1,dms.count())
        dms.delete()
        assertEquals(0,dms.count())

    }
}
