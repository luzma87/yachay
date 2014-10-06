package yachay.avales

import grails.test.*
import yachay.parametros.poaPac.Anio
import yachay.parametros.poaPac.ProgramaPresupuestario
import yachay.parametros.proyectos.ObjetivoGobiernoResultado
import yachay.poa.Asignacion
import yachay.proyectos.MarcoLogico
import yachay.proyectos.Proyecto

class ProcesoAsignacionTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {

        def testInstances = []
        mockDomain(Anio, testInstances)
        mockDomain(MarcoLogico, testInstances)
        mockDomain(Asignacion, testInstances)
        mockDomain(ObjetivoGobiernoResultado, testInstances)
        mockDomain(ProgramaPresupuestario)
        mockDomain(Proyecto, testInstances)
        mockDomain(ProcesoAval, testInstances)
        mockDomain(ProcesoAsignacion, testInstances)

        def ani = new Anio(anio: '2014', estado: 1).save(flush: true)
        assertEquals(1,ani.count())
        def mar = new MarcoLogico(objeto: 'pruebaMar').save(flush: true)
        assertEquals(1,mar.count())
        def asg = new Asignacion(anio: ani, marcoLogico: mar).save(flush: true)
        assertEquals(1,asg.count())
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
        def pas = new ProcesoAsignacion(proceso: pra, asignacion: asg).save(flush: true)
        assertEquals(1, pas.count())

    }

    void testUpdate (){

        def testInstances = []
        mockDomain(Anio, testInstances)
        mockDomain(MarcoLogico, testInstances)
        mockDomain(Asignacion, testInstances)
        mockDomain(ObjetivoGobiernoResultado, testInstances)
        mockDomain(ProgramaPresupuestario)
        mockDomain(Proyecto, testInstances)
        mockDomain(ProcesoAval, testInstances)
        mockDomain(ProcesoAsignacion, testInstances)

        def ani = new Anio(anio: '2014', estado: 1).save(flush: true)
        assertEquals(1,ani.count())
        def mar = new MarcoLogico(objeto: 'pruebaMar').save(flush: true)
        assertEquals(1,mar.count())
        def asg = new Asignacion(anio: ani, marcoLogico: mar).save(flush: true)
        assertEquals(1,asg.count())
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
        def pra2 = new ProcesoAval(proyecto: pro, nombre: 'nuevoProcesoAval',
                fechaInicio: new Date(), fechaFin: new Date()).save(flush: true)
        assertEquals(2, pra2.count())
        def pas = new ProcesoAsignacion(proceso: pra, asignacion: asg).save(flush: true)
        assertEquals(1, pas.count())
        def pas2 = ProcesoAsignacion.findByProceso(pra)
        pas2.proceso = pra2
        assertEquals(1, pas2.count())
        assertEquals('nuevoProcesoAval', pas2.proceso.nombre)

    }

    void testDelete (){

        def testInstances = []
        mockDomain(Anio, testInstances)
        mockDomain(MarcoLogico, testInstances)
        mockDomain(Asignacion, testInstances)
        mockDomain(ObjetivoGobiernoResultado, testInstances)
        mockDomain(ProgramaPresupuestario)
        mockDomain(Proyecto, testInstances)
        mockDomain(ProcesoAval, testInstances)
        mockDomain(ProcesoAsignacion, testInstances)

        def ani = new Anio(anio: '2014', estado: 1).save(flush: true)
        assertEquals(1,ani.count())
        def mar = new MarcoLogico(objeto: 'pruebaMar').save(flush: true)
        assertEquals(1,mar.count())
        def asg = new Asignacion(anio: ani, marcoLogico: mar).save(flush: true)
        assertEquals(1,asg.count())
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
        def pas = new ProcesoAsignacion(proceso: pra, asignacion: asg).save(flush: true)
        assertEquals(1, pas.count())
        pas.delete()
        assertEquals(0,pas.count())

    }
}
