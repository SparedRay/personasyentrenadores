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
public class Entrenador extends Persona {
    private String experienciaLaboral;
    private String especialidad;

    public String getExperienciaLaboral() {
        return experienciaLaboral;
    }

    public void setExperienciaLaboral(String experienciaLaboral) {
        this.experienciaLaboral = experienciaLaboral;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    
    public Entrenador(int cedula, int edad, String nombre, char sexo, String experienciaLaboral, String especialidad)
    {
        super(cedula, edad, nombre, sexo);
        this.experienciaLaboral = experienciaLaboral;
        this.especialidad = especialidad;
    }

    public Entrenador(int cedula, int edad, String nombre, char sexo, double peso, double altura, String direccion, String experienciaLaboral, String especialidad)
    {
        super(cedula, edad, nombre, sexo, peso, altura, direccion);
        this.experienciaLaboral = experienciaLaboral;
        this.especialidad = especialidad;
    }
    
    public Entrenador() {
        this(0, 0, "", '\0', "", "");
    }
    
    public void determinarRutina(int grasa) {
        switch(grasa) {
            default:
                System.out.println("No hay ejercicio para ti hoy");
                break;
        }
    }
    
    public void determinarRutina(String tipoDeEjercicio) {
        
    }
    
    @Override
    public void imprimirDatosPer() {
        System.out.println("Puesto: Entrenador");
        super.imprimirDatosPer();
    }
}
