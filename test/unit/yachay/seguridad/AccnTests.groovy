package yachay.seguridad

import grails.test.*
import yachay.avales.Aval
import yachay.avales.EstadoAval
import yachay.avales.ProcesoAval
import yachay.parametros.poaPac.ProgramaPresupuestario
import yachay.parametros.proyectos.ObjetivoGobiernoResultado
import yachay.proyectos.PoliticasAgendaProyecto
import yachay.proyectos.Proyecto

class AccnTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {

        def testInstances = []
        mockDomain(Prms, testInstances)
        mockDomain(Accn, testInstances)
        mockDomain(Modulo, testInstances)
        mockDomain(Ctrl, testInstances)
        mockDomain(Tpac, testInstances)

        def ctr = new Ctrl(ctrlNombre: 'pruebaCtrl').save(flush: true)
        assertEquals(1, ctr.count())
        def mdl = new Modulo(nombre: 'nombreModulo', descripcion: 'pruebaModulo').save(flush: true)
        assertEquals(1, mdl.count())
        def tpa = new Tpac(tipo: '1').save(flush: true)
        def acc = new Accn(accnNombre: 'pruebaAccn', accnDescripcion: 'accn', modulo: mdl, accnAuditable: 1,
                control: ctr, tipo: tpa).save(flush: true)
        assertEquals(1, acc.count())


    }

    void testUpdate () {

        def testInstances = []
        mockDomain(Prms, testInstances)
        mockDomain(Accn, testInstances)
        mockDomain(Modulo, testInstances)
        mockDomain(Ctrl, testInstances)
        mockDomain(Tpac, testInstances)

        def ctr = new Ctrl(ctrlNombre: 'pruebaCtrl').save(flush: true)
        assertEquals(1, ctr.count())
        def mdl = new Modulo(nombre: 'nombreModulo', descripcion: 'pruebaModulo').save(flush: true)
        assertEquals(1, mdl.count())
        def tpa = new Tpac(tipo: '1').save(flush: true)
        def acc = new Accn(accnNombre: 'pruebaAccn', accnDescripcion: 'accn', modulo: mdl, accnAuditable: 1,
                control: ctr, tipo: tpa).save(flush: true)
        assertEquals(1, acc.count())
        def acc2 = Accn.findByAccnNombre('pruebaAccn')
        acc2.accnNombre = 'nuevoAccn'
        acc2.save(flush: true)
        assertEquals(1, acc2.count())
        assertEquals('nuevoAccn', acc2.accnNombre)

    }

    void testDelete () {

        def testInstances = []
        mockDomain(Prms, testInstances)
        mockDomain(Accn, testInstances)
        mockDomain(Modulo, testInstances)
        mockDomain(Ctrl, testInstances)
        mockDomain(Tpac, testInstances)

        def ctr = new Ctrl(ctrlNombre: 'pruebaCtrl').save(flush: true)
        assertEquals(1, ctr.count())
        def mdl = new Modulo(nombre: 'nombreModulo', descripcion: 'pruebaModulo').save(flush: true)
        assertEquals(1, mdl.count())
        def tpa = new Tpac(tipo: '1').save(flush: true)
        def acc = new Accn(accnNombre: 'pruebaAccn', accnDescripcion: 'accn', modulo: mdl, accnAuditable: 1,
                control: ctr, tipo: tpa).save(flush: true)
        assertEquals(1, acc.count())
        acc.delete()
        assertEquals(0, acc.count())

    }
}
