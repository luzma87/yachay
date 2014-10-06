package yachay.avales

import grails.test.*
import yachay.parametros.poaPac.ProgramaPresupuestario
import yachay.parametros.proyectos.ObjetivoGobiernoResultado
import yachay.proyectos.Proyecto

class AvalTests extends GrailsUnitTestCase {
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
    }

    void testUpdate () {

        def testInstances = []
        mockDomain(Proyecto, testInstances)
        mockDomain(ObjetivoGobiernoResultado, testInstances)
        mockDomain(ProgramaPresupuestario, testInstances)
        mockDomain(ProcesoAval, testInstances)
        mockDomain(EstadoAval, testInstances)
        mockDomain(Aval, testInstances)

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
        def esa2 = new EstadoAval(descripcion: 'pruebaEstadoAval2', codigo: '456').save(flush: true)
        assertEquals(2,esa2.count())
        def ava = new Aval(proceso: pra, estado: esa).save(flush: true)
        assertEquals(1,ava.count())
//        println("aval " + ava.estado.codigo)
        ava.estado = esa2
        ava.save(flush: true)
//        println("aval1 " + ava.estado.codigo)
        assertEquals(1,ava.count())
        assertEquals("456", ava.estado.codigo)

    }

    void testDelete () {

        def testInstances = []
        mockDomain(Proyecto, testInstances)
        mockDomain(ObjetivoGobiernoResultado, testInstances)
        mockDomain(ProgramaPresupuestario, testInstances)
        mockDomain(ProcesoAval, testInstances)
        mockDomain(EstadoAval, testInstances)
        mockDomain(Aval, testInstances)

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
        ava.delete()
        assertEquals(0,ava.count())

    }


}
