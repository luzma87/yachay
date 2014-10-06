package yachay.proyectos

import grails.test.*
import yachay.avales.Aval
import yachay.avales.EstadoAval
import yachay.avales.ProcesoAval
import yachay.parametros.poaPac.ProgramaPresupuestario
import yachay.parametros.proyectos.ObjetivoGobiernoResultado

class ObjetivoEstrategicoTests extends GrailsUnitTestCase {
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
        mockDomain(ObjetivoEstrategico, testInstances)

        def ogr = new ObjetivoGobiernoResultado(descripcion: 'pruebaOgr').save(flush: true)
        assertEquals(1,ogr.count())
        def prp = new ProgramaPresupuestario(codigo: '123', descripcion: 'pruebaPrp').save(flush: true)
        assertEquals(1, prp.count())
        def pro = new Proyecto(nombre: 'pruebaProyecto',
                objetivoGobiernoResultado: ogr, programaPresupuestario: prp).save(flush: true)
        assertEquals(1, pro.count())

        def obe = new ObjetivoEstrategico(proyecto: pro).save(flush: true)
        assertEquals(1, obe.count())

    }

    void testUpdate () {

        def testInstances = []
        mockDomain(Proyecto, testInstances)
        mockDomain(ObjetivoGobiernoResultado, testInstances)
        mockDomain(ProgramaPresupuestario, testInstances)
        mockDomain(ObjetivoEstrategico, testInstances)

        def ogr = new ObjetivoGobiernoResultado(descripcion: 'pruebaOgr').save(flush: true)
        assertEquals(1,ogr.count())
        def prp = new ProgramaPresupuestario(codigo: '123', descripcion: 'pruebaPrp').save(flush: true)
        assertEquals(1, prp.count())
        def pro = new Proyecto(nombre: 'pruebaProyecto',
                objetivoGobiernoResultado: ogr, programaPresupuestario: prp).save(flush: true)
        assertEquals(1, pro.count())

        def obe = new ObjetivoEstrategico(proyecto: pro, objetivo: 'pruebaObjetivoEstrategico').save(flush: true)
        assertEquals(1, obe.count())

        def obe2 = ObjetivoEstrategico.findByProyecto(pro)
        obe2.objetivo = 'nuevoObjetivoEstrategico'
        obe2.save(flush: true)

        assertEquals(1, obe2.count())
        assertEquals('nuevoObjetivoEstrategico', obe2.objetivo)
    }

    void testDelete () {

        def testInstances = []
        mockDomain(Proyecto, testInstances)
        mockDomain(ObjetivoGobiernoResultado, testInstances)
        mockDomain(ProgramaPresupuestario, testInstances)
        mockDomain(ObjetivoEstrategico, testInstances)

        def ogr = new ObjetivoGobiernoResultado(descripcion: 'pruebaOgr').save(flush: true)
        assertEquals(1,ogr.count())
        def prp = new ProgramaPresupuestario(codigo: '123', descripcion: 'pruebaPrp').save(flush: true)
        assertEquals(1, prp.count())
        def pro = new Proyecto(nombre: 'pruebaProyecto',
                objetivoGobiernoResultado: ogr, programaPresupuestario: prp).save(flush: true)
        assertEquals(1, pro.count())

        def obe = new ObjetivoEstrategico(proyecto: pro).save(flush: true)
        assertEquals(1, obe.count())
        obe.delete()
        assertEquals(0, obe.count())
    }
}
