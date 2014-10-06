package yachay.seguridad

import grails.test.*

class PrmsTests extends GrailsUnitTestCase {
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
        mockDomain(Prfl, testInstances)

        def prf = new Prfl(nombre: 'perfil', descripcion: 'pruebaPerfil', codigo: '123', observaciones: 'observacion').save(flush: true)
        assertEquals(1, prf.count())
        def ctr = new Ctrl(ctrlNombre: 'pruebaCtrl').save(flush: true)
        assertEquals(1, ctr.count())
        def mdl = new Modulo(nombre: 'nombreModulo', descripcion: 'pruebaModulo').save(flush: true)
        assertEquals(1, mdl.count())
        def tpa = new Tpac(tipo: '1').save(flush: true)
        def acc = new Accn(accnNombre: 'pruebaAccn', accnDescripcion: 'accn', modulo: mdl, accnAuditable: 1,
                control: ctr, tipo: tpa).save(flush: true)
        assertEquals(1, acc.count())
        def prm = new Prms(accion: acc, perfil: prf).save(flush: true)
        assertEquals(1, prm.count())

    }

    void testUpdate () {

        def testInstances = []
        mockDomain(Prms, testInstances)
        mockDomain(Accn, testInstances)
        mockDomain(Modulo, testInstances)
        mockDomain(Ctrl, testInstances)
        mockDomain(Tpac, testInstances)
        mockDomain(Prfl, testInstances)

        def prf = new Prfl(nombre: 'perfil', descripcion: 'pruebaPerfil', codigo: '123', observaciones: 'observacion').save(flush: true)
        assertEquals(1, prf.count())
        def ctr = new Ctrl(ctrlNombre: 'pruebaCtrl').save(flush: true)
        assertEquals(1, ctr.count())
        def mdl = new Modulo(nombre: 'nombreModulo', descripcion: 'pruebaModulo').save(flush: true)
        assertEquals(1, mdl.count())
        def tpa = new Tpac(tipo: '1').save(flush: true)
        def acc = new Accn(accnNombre: 'pruebaAccn', accnDescripcion: 'accn', modulo: mdl, accnAuditable: 1,
                control: ctr, tipo: tpa).save(flush: true)
        assertEquals(1, acc.count())
        def prm = new Prms(accion: acc, perfil: prf).save(flush: true)
        assertEquals(1, prm.count())

        def prm2 = Prms.findByAccion(acc)
        prm2.accion.accnNombre = 'nuevaAccion'
        prm2.save(flush: true)
        assertEquals(1, prm2.count())
        assertEquals('nuevaAccion', prm2.accion.accnNombre)


    }

    void testDelete () {

        def testInstances = []
        mockDomain(Prms, testInstances)
        mockDomain(Accn, testInstances)
        mockDomain(Modulo, testInstances)
        mockDomain(Ctrl, testInstances)
        mockDomain(Tpac, testInstances)
        mockDomain(Prfl, testInstances)

        def prf = new Prfl(nombre: 'perfil', descripcion: 'pruebaPerfil', codigo: '123', observaciones: 'observacion').save(flush: true)
        assertEquals(1, prf.count())
        def ctr = new Ctrl(ctrlNombre: 'pruebaCtrl').save(flush: true)
        assertEquals(1, ctr.count())
        def mdl = new Modulo(nombre: 'nombreModulo', descripcion: 'pruebaModulo').save(flush: true)
        assertEquals(1, mdl.count())
        def tpa = new Tpac(tipo: '1').save(flush: true)
        def acc = new Accn(accnNombre: 'pruebaAccn', accnDescripcion: 'accn', modulo: mdl, accnAuditable: 1,
                control: ctr, tipo: tpa).save(flush: true)
        assertEquals(1, acc.count())
        def prm = new Prms(accion: acc, perfil: prf).save(flush: true)
        assertEquals(1, prm.count())
        prm.delete()
        assertEquals(0, prm.count())

    }
}
