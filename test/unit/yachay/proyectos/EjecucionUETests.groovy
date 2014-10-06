package yachay.proyectos

import grails.test.*
import yachay.parametros.TipoInstitucion
import yachay.parametros.UnidadEjecutora
import yachay.parametros.poaPac.Anio
import yachay.parametros.poaPac.Fuente
import yachay.parametros.poaPac.Mes
import yachay.parametros.poaPac.Presupuesto
import yachay.parametros.poaPac.ProgramaPresupuestario
import yachay.parametros.proyectos.ObjetivoGobiernoResultado

class EjecucionUETests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {

        def testInstances = []
        mockDomain(EjecucionUE, testInstances)
        mockDomain(Anio, testInstances)
        mockDomain(Mes, testInstances)
        mockDomain(Sigef, testInstances)
        mockDomain(Presupuesto, testInstances)
        mockDomain(Fuente, testInstances)
        mockDomain(Proyecto, testInstances)
        mockDomain(ObjetivoGobiernoResultado, testInstances)
        mockDomain(TipoInstitucion, testInstances)
        mockDomain(UnidadEjecutora, testInstances)
        mockDomain(ProgramaPresupuestario, testInstances)


        def ani = new Anio(anio: '2014', estado: 1).save(flush: true)
        assertEquals(1,ani.count())
        def mes = new Mes(numero: 1, descripcion: 'Enero').save(flush: true)
        def sig = new Sigef(anio: ani, mes: mes, fecha: new Date(), archivo: '123').save(flush: true)
        assertEquals(1, sig.count())
        def prs = new Presupuesto(numero: '1234', descripcion: 'pruebaPresupuesto', nivel: 1,
                movimiento: 1).save(flush: true)
        assertEquals(1, prs.count())
        def fnt = new Fuente(descripcion: 'pruebaFuente', codigo: '123').save(flush: true)
        assertEquals(1, fnt.count())
        def ogr = new ObjetivoGobiernoResultado(descripcion: 'pruebaOgr').save(flush: true)
        assertEquals(1,ogr.count())
        def prp = new ProgramaPresupuestario(codigo: '123', descripcion: 'pruebaPrp').save(flush: true)
        assertEquals(1, prp.count())
        def pro = new Proyecto(nombre: 'pruebaProyecto',
                objetivoGobiernoResultado: ogr, programaPresupuestario: prp).save(flush: true)
        assertEquals(1, pro.count())
        def ins = new TipoInstitucion(codigo: '12', descripcion: 'pruebaIns').save(flush: true)
        assertEquals(1,ins.count())
        def uni = new UnidadEjecutora(nombre: 'pruebaUnej', tipoInstitucion: ins).save(flush: true)
        assertEquals(1,uni.count())
        def eue = new EjecucionUE(sigef: sig, vigente: 320.20,
                comprometido: 150, presupuesto: prs, programa: prp, fuente: fnt, proyecto: pro, unidadEjecutora: uni).save(flush: true)
        assertEquals(1, eue.count())
    }

    void testUpdate () {
        def testInstances = []
        mockDomain(EjecucionUE, testInstances)
        mockDomain(Anio, testInstances)
        mockDomain(Mes, testInstances)
        mockDomain(Sigef, testInstances)
        mockDomain(Presupuesto, testInstances)
        mockDomain(Fuente, testInstances)
        mockDomain(Proyecto, testInstances)
        mockDomain(ObjetivoGobiernoResultado, testInstances)
        mockDomain(TipoInstitucion, testInstances)
        mockDomain(UnidadEjecutora, testInstances)
        mockDomain(ProgramaPresupuestario, testInstances)


        def ani = new Anio(anio: '2014', estado: 1).save(flush: true)
        assertEquals(1,ani.count())
        def mes = new Mes(numero: 1, descripcion: 'Enero').save(flush: true)
        def sig = new Sigef(anio: ani, mes: mes, fecha: new Date(), archivo: '123').save(flush: true)
        assertEquals(1, sig.count())
        def prs = new Presupuesto(numero: '1234', descripcion: 'pruebaPresupuesto', nivel: 1,
                movimiento: 1).save(flush: true)
        assertEquals(1, prs.count())
        def fnt = new Fuente(descripcion: 'pruebaFuente', codigo: '123').save(flush: true)
        assertEquals(1, fnt.count())
        def ogr = new ObjetivoGobiernoResultado(descripcion: 'pruebaOgr').save(flush: true)
        assertEquals(1,ogr.count())
        def prp = new ProgramaPresupuestario(codigo: '123', descripcion: 'pruebaPrp').save(flush: true)
        assertEquals(1, prp.count())
        def pro = new Proyecto(nombre: 'pruebaProyecto',
                objetivoGobiernoResultado: ogr, programaPresupuestario: prp).save(flush: true)
        assertEquals(1, pro.count())
        def ins = new TipoInstitucion(codigo: '12', descripcion: 'pruebaIns').save(flush: true)
        assertEquals(1,ins.count())
        def uni = new UnidadEjecutora(nombre: 'pruebaUnej', tipoInstitucion: ins).save(flush: true)
        assertEquals(1,uni.count())
        def eue = new EjecucionUE(sigef: sig, vigente: 320.20,
                comprometido: 150, presupuesto: prs, programa: prp, fuente: fnt, proyecto: pro, unidadEjecutora: uni).save(flush: true)
        assertEquals(1, eue.count())

        def eue2 = EjecucionUE.findBySigef(sig)
        eue2.vigente = 245.45
        eue2.save(flush: true)

        assertEquals(1, eue2.count())
        assertEquals(245.45, eue2.vigente)

    }

    void testDelete () {

        def testInstances = []
        mockDomain(EjecucionUE, testInstances)
        mockDomain(Anio, testInstances)
        mockDomain(Mes, testInstances)
        mockDomain(Sigef, testInstances)
        mockDomain(Presupuesto, testInstances)
        mockDomain(Fuente, testInstances)
        mockDomain(Proyecto, testInstances)
        mockDomain(ObjetivoGobiernoResultado, testInstances)
        mockDomain(TipoInstitucion, testInstances)
        mockDomain(UnidadEjecutora, testInstances)
        mockDomain(ProgramaPresupuestario, testInstances)


        def ani = new Anio(anio: '2014', estado: 1).save(flush: true)
        assertEquals(1,ani.count())
        def mes = new Mes(numero: 1, descripcion: 'Enero').save(flush: true)
        def sig = new Sigef(anio: ani, mes: mes, fecha: new Date(), archivo: '123').save(flush: true)
        assertEquals(1, sig.count())
        def prs = new Presupuesto(numero: '1234', descripcion: 'pruebaPresupuesto', nivel: 1,
                movimiento: 1).save(flush: true)
        assertEquals(1, prs.count())
        def fnt = new Fuente(descripcion: 'pruebaFuente', codigo: '123').save(flush: true)
        assertEquals(1, fnt.count())
        def ogr = new ObjetivoGobiernoResultado(descripcion: 'pruebaOgr').save(flush: true)
        assertEquals(1,ogr.count())
        def prp = new ProgramaPresupuestario(codigo: '123', descripcion: 'pruebaPrp').save(flush: true)
        assertEquals(1, prp.count())
        def pro = new Proyecto(nombre: 'pruebaProyecto',
                objetivoGobiernoResultado: ogr, programaPresupuestario: prp).save(flush: true)
        assertEquals(1, pro.count())
        def ins = new TipoInstitucion(codigo: '12', descripcion: 'pruebaIns').save(flush: true)
        assertEquals(1,ins.count())
        def uni = new UnidadEjecutora(nombre: 'pruebaUnej', tipoInstitucion: ins).save(flush: true)
        assertEquals(1,uni.count())
        def eue = new EjecucionUE(sigef: sig, vigente: 320.20,
                comprometido: 150, presupuesto: prs, programa: prp, fuente: fnt, proyecto: pro, unidadEjecutora: uni).save(flush: true)
        assertEquals(1, eue.count())
        eue.delete()
        assertEquals(0, eue.count())

    }
}
