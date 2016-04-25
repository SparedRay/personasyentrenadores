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
    
    public void determinarRutina(int grasaCorpo) {
        switch(grasaCorpo) {
            default:
                System.out.println("No hay ejercicio para ti hoy");
                break;
        }
    }
    
    public void determinarRutina(String tipoEjer) {
        tipoEjer = tipoEjer.toLowerCase();
        switch(tipoEjer) {
            case "tonificacion":
                System.out.println("Deberias hacer pesas y barras hoy");
                break;
            case "reduccion":
                System.out.println("Con un poco de spinning y cardio vas bien");
                break;
            case "":
                break;
            default:
                System.out.println("No hay objetivos definidos para hoy");
                break;
        }
    }
    
    @Override
    public void imprimirDatosPer() {
        System.out.println("Puesto: Entrenador");
        System.out.println("Especialidad: " + getEspecialidad());
        super.imprimirDatosPer();
    }
}
