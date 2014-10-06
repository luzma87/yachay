package yachay.proyectos

import grails.test.*
import yachay.parametros.TipoInstitucion
import yachay.parametros.UnidadEjecutora
import yachay.parametros.poaPac.Anio
import yachay.parametros.poaPac.Fuente
import yachay.parametros.poaPac.Presupuesto
import yachay.parametros.poaPac.ProgramaPresupuestario

class DatosEsigefTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {

        def testInstance = []
        mockDomain(DatosEsigef, testInstance)
        mockDomain(Anio, testInstance)
        mockDomain(Presupuesto, testInstance)
        mockDomain(Fuente, testInstance)
        mockDomain(ProgramaPresupuestario, testInstance)
        mockDomain(TipoInstitucion, testInstance)
        mockDomain(UnidadEjecutora, testInstance)

        def ani = new Anio(anio: '2014', estado: 1).save(flush: true)
        assertEquals(1,ani.count())
        def prs = new Presupuesto(numero: '123', descripcion: 'pruebaPresupuesto', nivel: 1, movimiento: 1).save(flush: true)
        assertEquals(1,prs.count())
        def fte = new Fuente(descripcion: 'pruebaFuente', codigo: '123').save(flush: true)
        assertEquals(1, fte.count())
        def prp = new ProgramaPresupuestario(codigo: '123', descripcion: 'pruebaProgramaPresupuestario').save(flush: true)
        assertEquals(1, prp.count())
        def ins = new TipoInstitucion(codigo: '12', descripcion: 'pruebaIns').save(flush: true)
        assertEquals(1,ins.count())
        def uni = new UnidadEjecutora(nombre: 'pruebaUnej', tipoInstitucion: ins).save(flush: true)
        assertEquals(1,uni.count())

        def dte = new DatosEsigef(vigente: 180.90, comprometido: 200.14, presupuesto: prs, fuente: fte,
                fecha: new Date(), programa: prp, unidad: uni).save(flush: true)
        assertEquals(1, dte.count())

    }

    void testUpdate () {

        def testInstance = []
        mockDomain(DatosEsigef, testInstance)
        mockDomain(Anio, testInstance)
        mockDomain(Presupuesto, testInstance)
        mockDomain(Fuente, testInstance)
        mockDomain(ProgramaPresupuestario, testInstance)
        mockDomain(TipoInstitucion, testInstance)
        mockDomain(UnidadEjecutora, testInstance)

        def ani = new Anio(anio: '2014', estado: 1).save(flush: true)
        assertEquals(1,ani.count())
        def prs = new Presupuesto(numero: '123', descripcion: 'pruebaPresupuesto', nivel: 1, movimiento: 1).save(flush: true)
        assertEquals(1,prs.count())
        def fte = new Fuente(descripcion: 'pruebaFuente', codigo: '123').save(flush: true)
        assertEquals(1, fte.count())
        def prp = new ProgramaPresupuestario(codigo: '123', descripcion: 'pruebaProgramaPresupuestario').save(flush: true)
        assertEquals(1, prp.count())
        def ins = new TipoInstitucion(codigo: '12', descripcion: 'pruebaIns').save(flush: true)
        assertEquals(1,ins.count())
        def uni = new UnidadEjecutora(nombre: 'pruebaUnej', tipoInstitucion: ins).save(flush: true)
        assertEquals(1,uni.count())

        def dte = new DatosEsigef(vigente: 180.90, comprometido: 200.14, presupuesto: prs, fuente: fte,
                fecha: new Date(), programa: prp, unidad: uni).save(flush: true)
        assertEquals(1, dte.count())

        def dte2 = DatosEsigef.findByVigente(180.90)
        dte2.vigente = 200.30
        dte2.save(flush: true)
        assertEquals(1, dte2.count())
        assertEquals(200.30, dte2.vigente)

    }

    void testDelete () {

        def testInstance = []
        mockDomain(DatosEsigef, testInstance)
        mockDomain(Anio, testInstance)
        mockDomain(Presupuesto, testInstance)
        mockDomain(Fuente, testInstance)
        mockDomain(ProgramaPresupuestario, testInstance)
        mockDomain(TipoInstitucion, testInstance)
        mockDomain(UnidadEjecutora, testInstance)

        def ani = new Anio(anio: '2014', estado: 1).save(flush: true)
        assertEquals(1,ani.count())
        def prs = new Presupuesto(numero: '123', descripcion: 'pruebaPresupuesto', nivel: 1, movimiento: 1).save(flush: true)
        assertEquals(1,prs.count())
        def fte = new Fuente(descripcion: 'pruebaFuente', codigo: '123').save(flush: true)
        assertEquals(1, fte.count())
        def prp = new ProgramaPresupuestario(codigo: '123', descripcion: 'pruebaProgramaPresupuestario').save(flush: true)
        assertEquals(1, prp.count())
        def ins = new TipoInstitucion(codigo: '12', descripcion: 'pruebaIns').save(flush: true)
        assertEquals(1,ins.count())
        def uni = new UnidadEjecutora(nombre: 'pruebaUnej', tipoInstitucion: ins).save(flush: true)
        assertEquals(1,uni.count())

        def dte = new DatosEsigef(vigente: 180.90, comprometido: 200.14, presupuesto: prs, fuente: fte,
                fecha: new Date(), programa: prp, unidad: uni).save(flush: true)
        assertEquals(1, dte.count())

        dte.delete()
        assertEquals(0, dte.count())

    }
}
