package yachay.proyectos

import grails.test.*
import yachay.parametros.poaPac.ProgramaPresupuestario
import yachay.parametros.proyectos.ObjetivoGobiernoResultado

class GrupoDeAtencionTests extends GrailsUnitTestCase {
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
        mockDomain(GrupoDeAtencion, testInstances)

        def ogr = new ObjetivoGobiernoResultado(descripcion: 'pruebaOgr').save(flush: true)
        assertEquals(1,ogr.count())
        def prp = new ProgramaPresupuestario(codigo: '123', descripcion: 'pruebaPrp').save(flush: true)
        assertEquals(1, prp.count())
        def pro = new Proyecto(nombre: 'pruebaProyecto',
                objetivoGobiernoResultado: ogr, programaPresupuestario: prp).save(flush: true)
        assertEquals(1, pro.count())
        def gra = new GrupoDeAtencion(proyecto: pro, hombre: 10, mujer: 10).save(flush: true)
        assertEquals(1,gra.count())

    }

    void testUpdate (){

        def testInstances = []
        mockDomain(Proyecto, testInstances)
        mockDomain(ObjetivoGobiernoResultado, testInstances)
        mockDomain(ProgramaPresupuestario, testInstances)
        mockDomain(GrupoDeAtencion, testInstances)

        def ogr = new ObjetivoGobiernoResultado(descripcion: 'pruebaOgr').save(flush: true)
        assertEquals(1,ogr.count())
        def prp = new ProgramaPresupuestario(codigo: '123', descripcion: 'pruebaPrp').save(flush: true)
        assertEquals(1, prp.count())
        def pro = new Proyecto(nombre: 'pruebaProyecto',
                objetivoGobiernoResultado: ogr, programaPresupuestario: prp).save(flush: true)
        assertEquals(1, pro.count())
        def gra = new GrupoDeAtencion(proyecto: pro, hombre: 10, mujer: 10).save(flush: true)
        assertEquals(1,gra.count())

        def gra2 = GrupoDeAtencion.findByProyecto(pro)
        gra2.hombre = 11
        gra2.save(flush: true)
        assertEquals(1, gra2.count())
        assertEquals(11, gra2.hombre)


    }

    void testDelete () {

        def testInstances = []
        mockDomain(Proyecto, testInstances)
        mockDomain(ObjetivoGobiernoResultado, testInstances)
        mockDomain(ProgramaPresupuestario, testInstances)
        mockDomain(GrupoDeAtencion, testInstances)

        def ogr = new ObjetivoGobiernoResultado(descripcion: 'pruebaOgr').save(flush: true)
        assertEquals(1,ogr.count())
        def prp = new ProgramaPresupuestario(codigo: '123', descripcion: 'pruebaPrp').save(flush: true)
        assertEquals(1, prp.count())
        def pro = new Proyecto(nombre: 'pruebaProyecto',
                objetivoGobiernoResultado: ogr, programaPresupuestario: prp).save(flush: true)
        assertEquals(1, pro.count())
        def gra = new GrupoDeAtencion(proyecto: pro, hombre: 10, mujer: 10).save(flush: true)
        assertEquals(1,gra.count())
        gra.delete()
        assertEquals(0, gra.count())
    }
}
