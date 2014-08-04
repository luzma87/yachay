package app
class PoliticaAplicaProyecto implements Serializable {
String descripcion
static auditable=[ignore:[]]
static mapping = {
table 'pltc'
cache usage:'read-write', include:'non-lazy'
id column:'pltc__id'
id generator:'identity'
version false
columns {
id column:'pltc__id'
descripcion column: 'pltcdscr'
}
}
static constraints = {
descripcion(size:1..1023,blank:false,attributes:[mensaje:'descripcion'])
}
}