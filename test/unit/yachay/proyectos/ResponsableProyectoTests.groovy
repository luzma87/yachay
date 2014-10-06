package yachay.proyectos

import grails.test.*
import yachay.parametros.TipoResponsable
import yachay.parametros.poaPac.ProgramaPresupuestario
import yachay.parametros.proyectos.ObjetivoGobiernoResultado

class ResponsableProyectoTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {

        def testInstances = []
        mockDomain(ResponsableProyecto, testInstances)
        mockDomain(Proyecto, testInstances)
        mockDomain(ObjetivoGobiernoResultado, testInstances)
        mockDomain(ProgramaPresupuestario, testInstances)
        mockDomain(TipoResponsable, testInstances)


        def tpr = new TipoResponsable(codigo: '1', descripcion: 'pruebaTR').save(flush: true)
        assertEquals(1, tpr.count())

        def ogr = new ObjetivoGobiernoResultado(descripcion: 'pruebaOgr').save(flush: true)
        assertEquals(1,ogr.count())
        def prp = new ProgramaPresupuestario(codigo: '123', descripcion: 'pruebaPrp').save(flush: true)
        assertEquals(1, prp.count())
        def pro = new Proyecto(nombre: 'pruebaProyecto',
                objetivoGobiernoResultado: ogr, programaPresupuestario: prp).save(flush: true)
        assertEquals(1, pro.count())
        def rpp = new ResponsableProyecto(observaciones: 'observaciones', proyecto: pro,
                desde: new Date(), hasta: new Date(), tipo: tpr).save(flush: true)
        assertEquals(1, rpp.count())

    }


    void testUpdate () {

        def testInstances = []
        mockDomain(ResponsableProyecto, testInstances)
        mockDomain(Proyecto, testInstances)
        mockDomain(ObjetivoGobiernoResultado, testInstances)
        mockDomain(ProgramaPresupuestario, testInstances)
        mockDomain(TipoResponsable, testInstances)

        def tpr = new TipoResponsable(codigo: '1', descripcion: 'pruebaTR').save(flush: true)
        assertEquals(1, tpr.count())

        def ogr = new ObjetivoGobiernoResultado(descripcion: 'pruebaOgr').save(flush: true)
        assertEquals(1,ogr.count())
        def prp = new ProgramaPresupuestario(codigo: '123', descripcion: 'pruebaPrp').save(flush: true)
        assertEquals(1, prp.count())
        def pro = new Proyecto(nombre: 'pruebaProyecto',
                objetivoGobiernoResultado: ogr, programaPresupuestario: prp).save(flush: true)
        assertEquals(1, pro.count())
        def rpp = new ResponsableProyecto(observaciones: 'observaciones', proyecto: pro,
                desde: new Date(), hasta: new Date(), tipo: tpr).save(flush: true)
        assertEquals(1, rpp.count())

        def rpp2 = ResponsableProyecto.findByProyecto(pro)
        rpp2.observaciones = 'nuevaObservacion'
        rpp2.save(flush: true)
        assertEquals(1, rpp2.count())
        assertEquals('nuevaObservacion', rpp2.observaciones)

    }

    void testDelete () {

        def testInstances = []
        mockDomain(ResponsableProyecto, testInstances)
        mockDomain(Proyecto, testInstances)
        mockDomain(ObjetivoGobiernoResultado, testInstances)
        mockDomain(ProgramaPresupuestario, testInstances)
        mockDomain(TipoResponsable, testInstances)


        def tpr = new TipoResponsable(codigo: '1', descripcion: 'pruebaTR').save(flush: true)
        assertEquals(1, tpr.count())

        def ogr = new ObjetivoGobiernoResultado(descripcion: 'pruebaOgr').save(flush: true)
        assertEquals(1,ogr.count())
        def prp = new ProgramaPresupuestario(codigo: '123', descripcion: 'pruebaPrp').save(flush: true)
        assertEquals(1, prp.count())
        def pro = new Proyecto(nombre: 'pruebaProyecto',
                objetivoGobiernoResultado: ogr, programaPresupuestario: prp).save(flush: true)
        assertEquals(1, pro.count())
        def rpp = new ResponsableProyecto(observaciones: 'observaciones', proyecto: pro,
                desde: new Date(), hasta: new Date(), tipo: tpr).save(flush: true)
        assertEquals(1, rpp.count())
        rpp.delete()
        assertEquals(0, rpp.count())
    }
}
