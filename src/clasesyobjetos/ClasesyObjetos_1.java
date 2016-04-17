/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasesyobjetos;

import java.util.*;
import Humanidad.*;
/**
 *
 * @author Martinor
 */
public class ClasesyObjetos_1 {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner ent = new Scanner( System.in );
        ArrayList<Persona> personas;
        personas = new ArrayList<>();
        
        personas.add(new Persona(25142999, 19, "Alfredo", 'M', 90, 1.80, "Mi direccion"));
        personas.add(crearPersona());
        
        int opcion;
        do {
            System.out.print("1. Crear Persona\n2. Ver personas\n3. Editar personas\n9. Salir\n\nOpcion: ");
            opcion = ent.nextInt();
            
            switch(opcion) {
                case 1:
                    personas.add(crearPersona());
                    break;
                case 2:
                    for(Persona p : personas) {
                        p.imprimirDatosPer();
                    }
                    break;
                case 3:
                    editarPersona(personas);
                case 9:
                    System.out.println("Hasta luego!");
                    break;
                default:
                    System.out.println("\"" + opcion + "\" no es un parametro valido");
                    break;
            }
        } while(opcion != 9);
        
    }
    
    public static Persona crearPersona() {
        //limpiarPant();
        Scanner ent = new Scanner(System.in);
        System.out.print("Nombre de la persona: ");
        String nombre = ent.nextLine();
        System.out.print("Edad: ");
        int edad = ent.nextInt();
        
        char sexo;
        do {
            System.out.print("Sexo: ");
            sexo = ent.next().charAt(0);
        } while (!Persona.comprobarSexo(sexo));
        
        System.out.print("Cedula: ");
        int cedula = ent.nextInt();
        System.out.print("Peso: ");
        double peso = ent.nextDouble();
        System.out.print("Altura: ");
        double altura = ent.nextDouble();
        String direccion = "";
        
        return new Persona(cedula, edad, nombre, sexo, peso, altura, direccion);
    }
    
    public static void editarPersona(ArrayList<Persona> personas) {
        Scanner ent = new Scanner(System.in);
        int i = 0, opcion;
        for(Persona p : personas) {
            System.out.println("|********* " + i++ + "********|");
            p.imprimirDatosPer();
        }
        System.out.print("\nOpcion: ");
        opcion = ent.nextInt();
        
        editarP(personas.get(opcion));
    }
    
    public static void editarP(Persona p) {
        Scanner ent = new Scanner( System.in );
        int opcion;
        
        do {
            System.out.print("\n1. Cambiar nombre\n2. Cambiar edad\n3. Cambiar cedula\n4. Cambiar sexo\n5. Cambiar peso\n6. Cambiar altura\n9. Salir\n\nOpcion: ");
            opcion = ent.nextInt();
            
            ent.nextLine();
            switch(opcion) {
                case 1:
                    System.out.print("Nuevo nombre: ");
                    p.setNombre(ent.nextLine());
                    break;
                case 2:
                    System.out.print("Nueva edad: ");
                    p.setEdad(ent.nextInt());
                    break;
                case 3:
                    System.out.print("Nueva cedula: ");
                    p.setCedula(ent.nextInt());
                    break;
                case 4:
                    System.out.print("Nuevo sexo: ");
                    p.setSexo(ent.next().charAt(0));
                    break;
                case 5:
                    System.out.print("Nuevo peso: ");
                    p.setPeso(ent.nextDouble());
                    break;
                case 6:
                    System.out.println("Nueva altura: ");
                    p.setAltura(ent.nextDouble());
                    break;
            }
        } while(opcion != 9);
    }
    
    public static void limpiarPant() {
        for(int i = 0; i < 25; i++)
            System.out.println();
    }
}
