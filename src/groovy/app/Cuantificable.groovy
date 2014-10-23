package app

/**
 * Created by svt on 10/14/2014.
 */
abstract  class Cuantificable {
    /*
    * Aporte
    */
    double aporte = 0
    /*
    * Avance de cumplimiento
    */
    double avance = 0

    public void calculaAporte(montoPadre,monto){
        this.aporte=(monto*100)/montoPadre
        println "aporte ? "+this.aporte+"   [ "+montoPadre+" , "+monto+" ]"
    }

}
