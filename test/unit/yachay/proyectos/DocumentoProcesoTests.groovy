package yachay.proyectos

import grails.test.*
import yachay.parametros.TipoDocumento

class DocumentoProcesoTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {

        def testInstance = []
        mockDomain(TipoDocumento, testInstance)
        mockDomain(Obra, testInstance)
        mockDomain(Liquidacion, testInstance)
        mockDomain(DocumentoProceso, testInstance)

        def tpd = new TipoDocumento(descripcion: 'pruebaTipoDocumento').save(flush: true)
        assertEquals(1,tpd.count())
        def obr = new Obra(descripcion: 'pruebaObra').save(flush: true)
        assertEquals(1, obr.count())
        def liq = new Liquidacion(obra: obr, valor: 345.50,
        fechaAdjudicacion: new Date(), fechaRegistro: new Date(), fechaInicio: new Date(), fechaFin: new Date())

        def dcp = new DocumentoProceso(liquidacion: liq, tipo: tpd,descripcion: 'pruebaDocumentoProceso' ).save(flush: true)
        assertEquals(1, dcp.count())

    }

    void testUpdate () {

        def testInstance = []
        mockDomain(TipoDocumento, testInstance)
        mockDomain(Obra, testInstance)
        mockDomain(Liquidacion, testInstance)
        mockDomain(DocumentoProceso, testInstance)

        def tpd = new TipoDocumento(descripcion: 'pruebaTipoDocumento').save(flush: true)
        assertEquals(1,tpd.count())
        def obr = new Obra(descripcion: 'pruebaObra').save(flush: true)
        assertEquals(1, obr.count())
        def liq = new Liquidacion(obra: obr, valor: 345.50,
                fechaAdjudicacion: new Date(), fechaRegistro: new Date(), fechaInicio: new Date(), fechaFin: new Date())
        def dcp = new DocumentoProceso(liquidacion: liq, tipo: tpd,descripcion: 'pruebaDocumentoProceso' ).save(flush: true)
        assertEquals(1, dcp.count())

        def dcp2 = DocumentoProceso.findByDescripcion('pruebaDocumentoProceso')
        dcp2.descripcion = 'nuevoDocumentoProceso'
        dcp2.save(flush: true)
        assertEquals(1, dcp2.count())
        assertEquals('nuevoDocumentoProceso', dcp2.descripcion)

    }

    void testDelete () {

        def testInstance = []
        mockDomain(TipoDocumento, testInstance)
        mockDomain(Obra, testInstance)
        mockDomain(Liquidacion, testInstance)
        mockDomain(DocumentoProceso, testInstance)

        def tpd = new TipoDocumento(descripcion: 'pruebaTipoDocumento').save(flush: true)
        assertEquals(1,tpd.count())
        def obr = new Obra(descripcion: 'pruebaObra').save(flush: true)
        assertEquals(1, obr.count())
        def liq = new Liquidacion(obra: obr, valor: 345.50,
                fechaAdjudicacion: new Date(), fechaRegistro: new Date(), fechaInicio: new Date(), fechaFin: new Date())
        def dcp = new DocumentoProceso(liquidacion: liq, tipo: tpd,descripcion: 'pruebaDocumentoProceso' ).save(flush: true)
        assertEquals(1, dcp.count())
        dcp.delete()
        assertEquals(0, dcp.count())

    }
}
