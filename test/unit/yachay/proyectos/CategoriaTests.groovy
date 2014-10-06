package yachay.proyectos

import grails.test.*

class CategoriaTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSave() {
        def testInstance = []
        mockDomain(Categoria, testInstance)

        def ctg = new Categoria(descripcion: 'pruebaCategoria', codigo: '123').save(flush: true)
        assertEquals(1, ctg.count())

    }

    void testUpdate () {

        def testInstance = []
        mockDomain(Categoria, testInstance)

        def ctg = new Categoria(descripcion: 'pruebaCategoria', codigo: '123').save(flush: true)
        assertEquals(1, ctg.count())

        def ctg2 = Categoria.findByDescripcion('pruebaCategoria')
        ctg2.descripcion = 'nuevaCategoria'
        ctg2.save(flush: true)
        assertEquals(1, ctg2.count())
        assertEquals('nuevaCategoria', ctg2.descripcion)
    }

    void testDelete (){

        def testInstance = []
        mockDomain(Categoria, testInstance)

        def ctg = new Categoria(descripcion: 'pruebaCategoria', codigo: '123').save(flush: true)
        assertEquals(1, ctg.count())
        ctg.delete()
        assertEquals(0, ctg.count())

    }
}
