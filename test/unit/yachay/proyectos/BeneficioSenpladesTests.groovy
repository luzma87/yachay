package yachay.proyectos

import grails.test.*
import yachay.avales.Aval
import yachay.avales.EstadoAval
import yachay.avales.ProcesoAval
import yachay.parametros.poaPac.ProgramaPresupuestario
import yachay.parametros.proyectos.ObjetivoGobiernoResultado

class BeneficioSenpladesTests extends GrailsUnitTestCase {
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
        mockDomain(BeneficioSenplades, testInstances)

        def ogr = new ObjetivoGobiernoResultado(descripcion: 'pruebaOgr').save(flush: true)
        assertEquals(1,ogr.count())
        def prp = new ProgramaPresupuestario(codigo: '123', descripcion: 'pruebaPrp').save(flush: true)
        assertEquals(1, prp.count())
        def pro = new Proyecto(nombre: 'pruebaProyecto',
                objetivoGobiernoResultado: ogr, programaPresupuestario: prp).save(flush: true)
        assertEquals(1, pro.count())

        def bfs = new BeneficioSenplades(proyecto: pro, beneficiariosDirectosHombres: 500).save(flush: true)
        assertEquals(1, bfs.count())

    }

    void testUpdate () {

        def testInstances = []
        mockDomain(Proyecto, testInstances)
        mockDomain(ObjetivoGobiernoResultado, testInstances)
        mockDomain(ProgramaPresupuestario, testInstances)
        mockDomain(BeneficioSenplades, testInstances)

        def ogr = new ObjetivoGobiernoResultado(descripcion: 'pruebaOgr').save(flush: true)
        assertEquals(1,ogr.count())
        def prp = new ProgramaPresupuestario(codigo: '123', descripcion: 'pruebaPrp').save(flush: true)
        assertEquals(1, prp.count())
        def pro = new Proyecto(nombre: 'pruebaProyecto',
                objetivoGobiernoResultado: ogr, programaPresupuestario: prp).save(flush: true)
        assertEquals(1, pro.count())

        def bfs = new BeneficioSenplades(proyecto: pro, beneficiariosDirectosHombres: 500).save(flush: true)
        assertEquals(1, bfs.count())

        def bfs2 = BeneficioSenplades.findByProyecto(pro)
        bfs2.beneficiariosDirectosHombres = 600
        bfs2.save(flush: true)
        assertEquals(1, bfs2.count())
        assertEquals(600, bfs2.beneficiariosDirectosHombres)


    }

    void testDelete () {

        def testInstances = []
        mockDomain(Proyecto, testInstances)
        mockDomain(ObjetivoGobiernoResultado, testInstances)
        mockDomain(ProgramaPresupuestario, testInstances)
        mockDomain(BeneficioSenplades, testInstances)

        def ogr = new ObjetivoGobiernoResultado(descripcion: 'pruebaOgr').save(flush: true)
        assertEquals(1,ogr.count())
        def prp = new ProgramaPresupuestario(codigo: '123', descripcion: 'pruebaPrp').save(flush: true)
        assertEquals(1, prp.count())
        def pro = new Proyecto(nombre: 'pruebaProyecto',
                objetivoGobiernoResultado: ogr, programaPresupuestario: prp).save(flush: true)
        assertEquals(1, pro.count())

        def bfs = new BeneficioSenplades(proyecto: pro, beneficiariosDirectosHombres: 500).save(flush: true)
        assertEquals(1, bfs.count())
        bfs.delete()
        assertEquals(0, bfs.count())
    }
}
