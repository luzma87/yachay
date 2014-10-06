package yachay.proyectos

import grails.test.*
import yachay.avales.Aval
import yachay.avales.EstadoAval
import yachay.avales.ProcesoAval
import yachay.parametros.poaPac.ProgramaPresupuestario
import yachay.parametros.proyectos.ObjetivoGobiernoResultado

class MetaBuenVivirProyectoTests extends GrailsUnitTestCase {
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
        mockDomain(MetaBuenVivir, testInstances)
        mockDomain(ObjetivoBuenVivir, testInstances)
        mockDomain(PoliticaBuenVivir, testInstances)
        mockDomain(MetaBuenVivirProyecto, testInstances)

        def obv = new ObjetivoBuenVivir(codigo: 123, descripcion: 'pruebaObjetivoBuenVivir').save(flush: true)
        assertEquals(1, obv.count())
        def pbv = new PoliticaBuenVivir(objetivo: obv, codigo: 456, descripcion: 'pruebaPoliticaBuenVivir').save(flush: true)
        assertEquals(1, pbv.count())
        def mbv = new MetaBuenVivir(politica: pbv, codigo: 789, descripcion: 'pruebaMetaBuenVivir').save(flush: true)
        assertEquals(1, mbv.count())

        def ogr = new ObjetivoGobiernoResultado(descripcion: 'pruebaOgr').save(flush: true)
        assertEquals(1,ogr.count())
        def prp = new ProgramaPresupuestario(codigo: '123', descripcion: 'pruebaPrp').save(flush: true)
        assertEquals(1, prp.count())
        def pro = new Proyecto(nombre: 'pruebaProyecto',
                objetivoGobiernoResultado: ogr, programaPresupuestario: prp).save(flush: true)
        assertEquals(1, pro.count())
        def mbp = new MetaBuenVivirProyecto(proyecto: pro, metaBuenVivir: mbv).save(flush: true)
        assertEquals(1, mbp.count())

    }

    void testUpdate () {

        def testInstances = []
        mockDomain(Proyecto, testInstances)
        mockDomain(ObjetivoGobiernoResultado, testInstances)
        mockDomain(ProgramaPresupuestario, testInstances)
        mockDomain(MetaBuenVivir, testInstances)
        mockDomain(ObjetivoBuenVivir, testInstances)
        mockDomain(PoliticaBuenVivir, testInstances)
        mockDomain(MetaBuenVivirProyecto, testInstances)

        def obv = new ObjetivoBuenVivir(codigo: 123, descripcion: 'pruebaObjetivoBuenVivir').save(flush: true)
        assertEquals(1, obv.count())
        def pbv = new PoliticaBuenVivir(objetivo: obv, codigo: 456, descripcion: 'pruebaPoliticaBuenVivir').save(flush: true)
        assertEquals(1, pbv.count())
        def mbv = new MetaBuenVivir(politica: pbv, codigo: 789, descripcion: 'pruebaMetaBuenVivir').save(flush: true)
        assertEquals(1, mbv.count())

        def ogr = new ObjetivoGobiernoResultado(descripcion: 'pruebaOgr').save(flush: true)
        assertEquals(1,ogr.count())
        def prp = new ProgramaPresupuestario(codigo: '123', descripcion: 'pruebaPrp').save(flush: true)
        assertEquals(1, prp.count())
        def pro = new Proyecto(nombre: 'pruebaProyecto',
                objetivoGobiernoResultado: ogr, programaPresupuestario: prp).save(flush: true)
        assertEquals(1, pro.count())
        def mbp = new MetaBuenVivirProyecto(proyecto: pro, metaBuenVivir: mbv).save(flush: true)
        assertEquals(1, mbp.count())

        def mbp2 = MetaBuenVivirProyecto.findByProyecto(pro)
        mbp2.proyecto.descripcion = 'nuevaMetaBuenVivirProyecto'
        mbp2.save(flush: true)
        assertEquals(1, mbp2.count())
        assertEquals('nuevaMetaBuenVivirProyecto', mbp2.proyecto.descripcion)
    }

    void testDelete () {

        def testInstances = []
        mockDomain(Proyecto, testInstances)
        mockDomain(ObjetivoGobiernoResultado, testInstances)
        mockDomain(ProgramaPresupuestario, testInstances)
        mockDomain(MetaBuenVivir, testInstances)
        mockDomain(ObjetivoBuenVivir, testInstances)
        mockDomain(PoliticaBuenVivir, testInstances)
        mockDomain(MetaBuenVivirProyecto, testInstances)

        def obv = new ObjetivoBuenVivir(codigo: 123, descripcion: 'pruebaObjetivoBuenVivir').save(flush: true)
        assertEquals(1, obv.count())
        def pbv = new PoliticaBuenVivir(objetivo: obv, codigo: 456, descripcion: 'pruebaPoliticaBuenVivir').save(flush: true)
        assertEquals(1, pbv.count())
        def mbv = new MetaBuenVivir(politica: pbv, codigo: 789, descripcion: 'pruebaMetaBuenVivir').save(flush: true)
        assertEquals(1, mbv.count())

        def ogr = new ObjetivoGobiernoResultado(descripcion: 'pruebaOgr').save(flush: true)
        assertEquals(1,ogr.count())
        def prp = new ProgramaPresupuestario(codigo: '123', descripcion: 'pruebaPrp').save(flush: true)
        assertEquals(1, prp.count())
        def pro = new Proyecto(nombre: 'pruebaProyecto',
                objetivoGobiernoResultado: ogr, programaPresupuestario: prp).save(flush: true)
        assertEquals(1, pro.count())
        def mbp = new MetaBuenVivirProyecto(proyecto: pro, metaBuenVivir: mbv).save(flush: true)
        assertEquals(1, mbp.count())
        mbp.delete()
        assertEquals(0, mbp.count())


    }
}
