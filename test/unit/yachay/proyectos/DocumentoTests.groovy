package yachay.proyectos

import grails.test.*

class DocumentoTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {
        def testInstance = []
        mockDomain(Documento, testInstance)

        def doc = new Documento(descripcion: 'pruebaDocumento', clave: '123').save(flush: true)
        assertEquals(1, doc.count())

    }

    void testUpdate () {

        def testInstance = []
        mockDomain(Documento, testInstance)
        def doc = new Documento(descripcion: 'pruebaDocumento', clave: '123').save(flush: true)
        assertEquals(1, doc.count())

        def doc2 = Documento.findByDescripcion('pruebaDocumento')
        doc2.descripcion = 'nuevoDocumento'
        doc2.save(flush: true)
        assertEquals(1, doc2.count())
        assertEquals('nuevoDocumento', doc2.descripcion)
    }

    void testDelete() {
        def testInstance = []
        mockDomain(Documento, testInstance)

        def doc = new Documento(descripcion: 'pruebaDocumento', clave: '123').save(flush: true)
        assertEquals(1, doc.count())
        doc.delete()
        assertEquals(0, doc.count())

    }
}
