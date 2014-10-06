package yachay.avales

import grails.test.*
import yachay.parametros.TipoInstitucion
import yachay.parametros.UnidadEjecutora
import yachay.parametros.poaPac.ProgramaPresupuestario
import yachay.parametros.proyectos.ObjetivoGobiernoResultado
import yachay.proyectos.Proyecto
import yachay.seguridad.Persona
import yachay.seguridad.Usro

class SolicitudAvalTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {

        def testInstances = []
        mockDomain(Proyecto, testInstances)
        mockDomain(ObjetivoGobiernoResultado, testInstances)
        mockDomain(ProgramaPresupuestario, testInstances)
        mockDomain(ProcesoAval, testInstances)
        mockDomain(EstadoAval, testInstances)
        mockDomain(Aval, testInstances)
        mockDomain(TipoInstitucion, testInstances)
        mockDomain(UnidadEjecutora, testInstances)
        mockDomain(Persona, testInstances)
        mockDomain(Usro, testInstances)
        mockDomain(SolicitudAval, testInstances)

        def ogr = new ObjetivoGobiernoResultado(descripcion: 'pruebaOgr').save(flush: true)
        assertEquals(1,ogr.count())
        def prp = new ProgramaPresupuestario(codigo: '123', descripcion: 'pruebaPrp').save(flush: true)
        assertEquals(1, prp.count())
        def pro = new Proyecto(nombre: 'pruebaProyecto',
                objetivoGobiernoResultado: ogr, programaPresupuestario: prp).save(flush: true)
        assertEquals(1, pro.count())
        def pra = new ProcesoAval(proyecto: pro, nombre: 'pruebaProcesoAval',
                fechaInicio: new Date(), fechaFin: new Date()).save(flush: true)
        assertEquals(1,pra.count())
        def esa = new EstadoAval(descripcion: 'pruebaEstadoAval', codigo: '123').save(flush: true)
        assertEquals(1,esa.count())
        def ava = new Aval(proceso: pra, estado: esa).save(flush: true)
        assertEquals(1,ava.count())
        def ins = new TipoInstitucion(codigo: '12', descripcion: 'pruebaIns').save(flush: true)
        assertEquals(1,ins.count())
        def uni = new UnidadEjecutora(nombre: 'pruebaUnej', tipoInstitucion: ins).save(flush: true)
        assertEquals(1,uni.count())
        def per = new Persona(cedula: '1716473325', nombre: 'prueba',
                apellido: 'prueba', sexo: 'M', discapacitado: '1', direccion: 'prueba', telefono: '123', mail: 'a@a.com', fax: '123', observaciones: 'prueba').save(flush: true)
        assertEquals(1,per.count())
        def usu = new Usro(persona: per, usroLogin: 'pruebaLogin', usroPassword: '123', autorizacion: '123', sigla: 'usro', usroActivo: 1).save(flush: true)
        assertEquals(1,usu.count())
        def sla = new SolicitudAval(proceso: pra, unidad: uni, usuario: usu, aval: ava, estado: esa,
                fecha: new Date(), fechaRevision: new Date()).save(flush: true)
        assertEquals(1,sla.count())

    }

    void testUpdate () {
        def testInstances = []
        mockDomain(Proyecto, testInstances)
        mockDomain(ObjetivoGobiernoResultado, testInstances)
        mockDomain(ProgramaPresupuestario, testInstances)
        mockDomain(ProcesoAval, testInstances)
        mockDomain(EstadoAval, testInstances)
        mockDomain(Aval, testInstances)
        mockDomain(TipoInstitucion, testInstances)
        mockDomain(UnidadEjecutora, testInstances)
        mockDomain(Persona, testInstances)
        mockDomain(Usro, testInstances)
        mockDomain(SolicitudAval, testInstances)

        def ogr = new ObjetivoGobiernoResultado(descripcion: 'pruebaOgr').save(flush: true)
        assertEquals(1,ogr.count())
        def prp = new ProgramaPresupuestario(codigo: '123', descripcion: 'pruebaPrp').save(flush: true)
        assertEquals(1, prp.count())
        def pro = new Proyecto(nombre: 'pruebaProyecto',
                objetivoGobiernoResultado: ogr, programaPresupuestario: prp).save(flush: true)
        assertEquals(1, pro.count())
        def pra = new ProcesoAval(proyecto: pro, nombre: 'pruebaProcesoAval',
                fechaInicio: new Date(), fechaFin: new Date()).save(flush: true)
        assertEquals(1,pra.count())
        def esa = new EstadoAval(descripcion: 'pruebaEstadoAval', codigo: '123').save(flush: true)
        assertEquals(1,esa.count())
        def esa2 = new EstadoAval(descripcion: 'pruebaEstadoAval2', codigo: '123').save(flush: true)
        assertEquals(2,esa.count())
        def ava = new Aval(proceso: pra, estado: esa).save(flush: true)
        assertEquals(1,ava.count())
        def ins = new TipoInstitucion(codigo: '12', descripcion: 'pruebaIns').save(flush: true)
        assertEquals(1,ins.count())
        def uni = new UnidadEjecutora(nombre: 'pruebaUnej', tipoInstitucion: ins).save(flush: true)
        assertEquals(1,uni.count())
        def per = new Persona(cedula: '1716473325', nombre: 'prueba',
                apellido: 'prueba', sexo: 'M', discapacitado: '1', direccion: 'prueba', telefono: '123', mail: 'a@a.com', fax: '123', observaciones: 'prueba').save(flush: true)
        assertEquals(1,per.count())
        def usu = new Usro(persona: per, usroLogin: 'pruebaLogin', usroPassword: '123', autorizacion: '123', sigla: 'usro', usroActivo: 1).save(flush: true)
        assertEquals(1,usu.count())
        def sla = new SolicitudAval(proceso: pra, unidad: uni, usuario: usu, aval: ava, estado: esa,
                fecha: new Date(), fechaRevision: new Date()).save(flush: true)
        assertEquals(1,sla.count())

        def sla2 = SolicitudAval.findByProceso(pra)
        sla2.estado = esa2
        sla2.save(flush: true)
        assertEquals(1, sla2.count())
        assertEquals('pruebaEstadoAval2', sla2.estado.descripcion)

    }

    def testDelete () {

        def testInstances = []
        mockDomain(Proyecto, testInstances)
        mockDomain(ObjetivoGobiernoResultado, testInstances)
        mockDomain(ProgramaPresupuestario, testInstances)
        mockDomain(ProcesoAval, testInstances)
        mockDomain(EstadoAval, testInstances)
        mockDomain(Aval, testInstances)
        mockDomain(TipoInstitucion, testInstances)
        mockDomain(UnidadEjecutora, testInstances)
        mockDomain(Persona, testInstances)
        mockDomain(Usro, testInstances)
        mockDomain(SolicitudAval, testInstances)

        def ogr = new ObjetivoGobiernoResultado(descripcion: 'pruebaOgr').save(flush: true)
        assertEquals(1,ogr.count())
        def prp = new ProgramaPresupuestario(codigo: '123', descripcion: 'pruebaPrp').save(flush: true)
        assertEquals(1, prp.count())
        def pro = new Proyecto(nombre: 'pruebaProyecto',
                objetivoGobiernoResultado: ogr, programaPresupuestario: prp).save(flush: true)
        assertEquals(1, pro.count())
        def pra = new ProcesoAval(proyecto: pro, nombre: 'pruebaProcesoAval',
                fechaInicio: new Date(), fechaFin: new Date()).save(flush: true)
        assertEquals(1,pra.count())
        def esa = new EstadoAval(descripcion: 'pruebaEstadoAval', codigo: '123').save(flush: true)
        assertEquals(1,esa.count())
        def ava = new Aval(proceso: pra, estado: esa).save(flush: true)
        assertEquals(1,ava.count())
        def ins = new TipoInstitucion(codigo: '12', descripcion: 'pruebaIns').save(flush: true)
        assertEquals(1,ins.count())
        def uni = new UnidadEjecutora(nombre: 'pruebaUnej', tipoInstitucion: ins).save(flush: true)
        assertEquals(1,uni.count())
        def per = new Persona(cedula: '1716473325', nombre: 'prueba',
                apellido: 'prueba', sexo: 'M', discapacitado: '1', direccion: 'prueba', telefono: '123', mail: 'a@a.com', fax: '123', observaciones: 'prueba').save(flush: true)
        assertEquals(1,per.count())
        def usu = new Usro(persona: per, usroLogin: 'pruebaLogin', usroPassword: '123', autorizacion: '123', sigla: 'usro', usroActivo: 1).save(flush: true)
        assertEquals(1,usu.count())
        def sla = new SolicitudAval(proceso: pra, unidad: uni, usuario: usu, aval: ava, estado: esa,
                fecha: new Date(), fechaRevision: new Date()).save(flush: true)
        assertEquals(1,sla.count())
        sla.delete()
        assertEquals(0, sla.count())
    }
}
