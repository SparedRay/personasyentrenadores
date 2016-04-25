/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Humanidad;

/**
 *
 * @author Martinor
 */
public class Deportista extends Persona {
    private int ritmoCardiaco;
    private char frecuenciaEnt;
    private String tipoDeEjercicio;
    private Entrenador entrenador;
    
    public Entrenador getEntrenador() {
        return this.entrenador;
    }
    
    public void setEntrenador(Persona e) {
        this.entrenador = (Entrenador)e;
    }

    public int getRitmoCardiaco() {
        return ritmoCardiaco;
    }

    public void setRitmoCardiaco(int ritmoCardiaco) {
        this.ritmoCardiaco = ritmoCardiaco;
    }

    public char getFrecuenciaEnt() {
        return Character.toUpperCase(frecuenciaEnt);
    }

    public void setFrecuenciaEnt(char frecuenciaEnt) {
        this.frecuenciaEnt = Character.toUpperCase(frecuenciaEnt);
    }

    public String getTipoDeEjercicio() {
        return tipoDeEjercicio;
    }

    public void setTipoDeEjercicio(String tipoDeEjercicio) {
        this.tipoDeEjercicio = tipoDeEjercicio;
    }
    
    public Deportista(int cedula, int edad, String nombre, char sexo, int ritmoCardiaco, char frecuenciaEnt, String tipoDeEjercicio) {
        super(cedula, edad, nombre, sexo);
        this.ritmoCardiaco = ritmoCardiaco;
        this.frecuenciaEnt = frecuenciaEnt;
        this.tipoDeEjercicio = tipoDeEjercicio;
    }
    
    public Deportista() {
        this(0, 0, "", '\0', 0, '\0', "");
    }
    
    public void verificarRitmo() {
        if(ritmoCardiaco > 100) {
            System.out.println("No deberia realizar entrenamiento el dia de hoy");
        } else {
            System.out.println("Su ritmo caridaco es normal, puede realizar entrenamiento");
        }
    }
    
    @Override
    public double calcularImc() {
        double grasa, imc; 
        int sexo = this.getSexo() == 'M' ? 1 : 0;
        
        imc = super.calcularImc();
        grasa = (1.20*imc)+(0.23*this.getEdad())-(10.8*sexo)-5.4;

        return grasa;
    }

    @Override
    public int definirImc(int imc) {
        int sexo, porcentaje;
        sexo = this.getSexo() == 'M' ? 1 : 0;
        imc = imc == 0 ? (int)this.calcularImc() : 0;

        if (sexo == 1) {
            // Hombre
            porcentaje = imc < 15 ? -1 : imc < 20 ? 0 : 1;
        } else {
            // Mujer
            porcentaje = imc < 25 ? -1 : imc < 30 ? 0 : 1;
        }
        return porcentaje;
    }
    
    @Override
    public void imprimirDatosPer() {
        System.out.println("Puesto: Deportista");
        if(null != this.entrenador) {
            System.out.println("Entrenador asignado: " + this.entrenador.getNombre());
        }
        System.out.println("Ritmo cardiaco: " + getRitmoCardiaco());
        System.out.println("Tipo de ejercicio actual: " + getTipoDeEjercicio());
        System.out.println("Frecuencia de entrenamiento: " + (getFrecuenciaEnt() == 'D' ? "Diario" : "Semanal"));
        super.imprimirDatosPer();
    }
}
