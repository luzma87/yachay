package yachay.avales

import grails.test.*
import yachay.parametros.poaPac.ProgramaPresupuestario
import yachay.parametros.proyectos.ObjetivoGobiernoResultado
import yachay.proyectos.Proyecto

class ProcesoAvalTests extends GrailsUnitTestCase {
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

    }

    void testUpdate () {

        def testInstances = []
        mockDomain(Proyecto, testInstances)
        mockDomain(ObjetivoGobiernoResultado, testInstances)
        mockDomain(ProgramaPresupuestario, testInstances)
        mockDomain(ProcesoAval, testInstances)

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

        def pra2 = ProcesoAval.findByNombre('pruebaProcesoAval')
        pra2.nombre = 'nuevoProcesoAval'
        pra2.save(flush: true)
        assertEquals(1, pra2.count())
        assertEquals('nuevoProcesoAval', pra2.nombre)

    }

    void testDelete () {

        def testInstances = []
        mockDomain(Proyecto, testInstances)
        mockDomain(ObjetivoGobiernoResultado, testInstances)
        mockDomain(ProgramaPresupuestario, testInstances)
        mockDomain(ProcesoAval, testInstances)

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
        pra.delete()
        assertEquals(0, pra.count())

    }
}
