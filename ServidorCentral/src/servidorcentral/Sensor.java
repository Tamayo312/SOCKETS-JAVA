/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorcentral;

/**
 *
 * @author alumnoAD
 */
public class Sensor {
 
    private String tipo, medida, tiempo;

    public Sensor(String tipo, String medida, String tiempo) {
        this.tipo = tipo;
        this.medida = medida;
        this.tiempo = tiempo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }
    
    public String getCadena(){
        return tipo+"/"+medida;
    }
}
