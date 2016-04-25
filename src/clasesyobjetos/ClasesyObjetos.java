/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasesyobjetos;

import java.util.*;
import Humanidad.*;
import Backand.*;
import java.util.concurrent.CountDownLatch;
/**
 *
 * @author Martinor
 */
public class ClasesyObjetos {
    static ArrayList<Persona> entrenadores;
    static ArrayList<Persona> deportistas;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner ent = new Scanner( System.in );
        CountDownLatch latch = new CountDownLatch(2);
        Bconnector connector;
        
        connector = new Bconnector("https://blistering-heat-7556.firebaseio.com", latch);
        entrenadores = connector.getData("Entrenadores"); //new ArrayList<>();
        deportistas =  connector.getData("Deportistas"); //new ArrayList<>();

        System.out.println("Sincronizando datos con el servidor...");
        try {
            latch.await();
        } catch (InterruptedException e) {}
        System.out.println("Datos sincronizados.");
        
        int opcion;
        do {
            System.out.print("\n1. Crear Persona\n2. Ver personas\n3. Editar entrenadores\n4. Editar deportistas\n5. Guardar Personas\n6. Eliminar Entrenadores\n7. Eliminar Deportistas\n0. Salir\n\nOpcion: ");
            opcion = ent.nextInt();
            
            switch(opcion) {
                case 1:
                    Persona persona = crearPersona();
                    if(Entrenador.class.isInstance(persona)) {
                        entrenadores.add((Entrenador)persona);
                    } else {
                        deportistas.add((Deportista)persona);
                    }
                    break;
                case 2:
                    System.out.println();
                    mostrarPersonas(entrenadores);
                    System.out.println();
                    mostrarPersonas(deportistas);
                    break;
                case 3:
                    editarPersonas(entrenadores);
                    break;
                case 4:
                    editarPersonas(deportistas);
                    break;
                case 5:
                    connector.guardarP(entrenadores, "Entrenadores");
                    connector.guardarP(deportistas, "Deportistas");
                    break;
                case 6:
                    eliminarPersonas(entrenadores);
                    break;
                case 7:
                    eliminarPersonas(deportistas);
                    break;
                case 0:
                    connector.disconnect();
                    System.out.println("Hasta luego!");
                    break;
                default:
                    System.out.println("\"" + opcion + "\" no es un parametro valido");
                    break;
            }
        } while(opcion != 0);
        
    }
    
    public static void mostrarPersonas(ArrayList<Persona> personas) {
        int i = 0;
        for(Persona p : personas) {
            System.out.println("|********* " + i++ + "********|");
            p.imprimirDatosPer();
        }
    }
    
    public static Persona crearPersona() {
        //limpiarPant();
        Scanner ent = new Scanner(System.in);
        Persona p;

        System.out.print("1 para crear un entrenador y 2 para crear un deportista: ");
        int type = ent.nextInt();
        System.out.println();
        if(type == 1) {
            p = new Entrenador();
        } else {
            p = new Deportista();
        }

        System.out.print("Nombre: ");
        String nombre = getLine();
        p.setNombre(nombre);

        System.out.print("Edad: ");
        int edad = ent.nextInt();
        p.setEdad(edad);
        
        char sexo;
        do {
            System.out.print("Sexo (M o F): ");
            sexo = ent.next().charAt(0);
        } while (!Persona.comprobarSexo(sexo));
        p.setSexo(sexo);
        
        System.out.print("Cedula: ");
        int cedula = ent.nextInt();
        p.setCedula(cedula);

        String direccion;
        do {
            System.out.print("Direccion: ");
            direccion = getLine();
        } while(!Persona.validarDireccion(direccion));
        p.setDireccion(direccion);

        System.out.print("Peso (kg): ");
        double peso = ent.nextDouble();
        p.setPeso(peso);

        System.out.print("Altura (m): ");
        double altura = ent.nextDouble();
        p.setAltura(altura);

        if(type == 1) {
            Entrenador e = (Entrenador)p;
            System.out.print("Experiencia: ");
            String experiencia = getLine();
            e.setExperienciaLaboral(experiencia);

            System.out.print("Especialidad: ");
            String especialidad = getLine();
            e.setEspecialidad(especialidad);

            return e;
        }

        if(type == 2) {
            Deportista d = (Deportista)p;
            System.out.print("Ritmo cardiaco: ");
            int ritmo = ent.nextInt();
            d.setRitmoCardiaco(ritmo);

            System.out.print("Tipo de ejercicios: ");
            String tipoDeEjercicio = getLine();
            d.setTipoDeEjercicio(tipoDeEjercicio);
            
            System.out.print("Frecuencia de entrenamiento (Diaria o Semanal): ");
            char frecuencia = ent.next().charAt(0);
            d.setFrecuenciaEnt(frecuencia);

            return d;
        }
        
        return p;
    }
    
    public static void editarPersonas(ArrayList<Persona> personas) {
        Scanner ent = new Scanner(System.in);
        int opcion;
        mostrarPersonas(personas);
        System.out.print("\nOpcion: ");
        opcion = ent.nextInt();
        
        editarP(personas.get(opcion));
    }

    public static void eliminarPersonas(ArrayList<Persona> personas) {
        Scanner ent = new Scanner(System.in);
        int i = 0, opcion;

        for(Persona p : personas) {
            System.out.println("|********* " + i++ + "********|");
            p.imprimirDatosPer();
        }
        System.out.println("\nADVERTENCIA: Esto solo eliminara a la persona de su lista local, para eliminarlo del sistema debe guardar la lista una vez haya eliminado a la persona.\n");
        System.out.print("\nOpcion: ");
        opcion = ent.nextInt();

        personas.remove(opcion);
    }
    
    public static void editarP(Persona p) {
        Scanner ent = new Scanner( System.in );
        int opcion;
        
        do {
            System.out.println("\n1. Cambiar nombre\n2. Cambiar edad\n3. Cambiar cedula\n4. Cambiar sexo\n5. Cambiar peso\n6. Cambiar altura\n7. Cambiar direccion");

            if(Entrenador.class.isInstance(p)) {
                System.out.println("8. Cambiar experiencia\n9. Cambiar especialidad");
            } else if(Deportista.class.isInstance(p)) {
                System.out.println("8. Cambiar ritmo cardiaco\n9. Cambiar frecuencia de entrenamiento\n10. Cambiar tipo de ejercicio\n11. Asignar entrenador");
            }

            System.out.print("0. Salir\n\nOpcion: ");
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
                    char sexo;
                    do {
                        System.out.print("Nuevo sexo: ");
                        sexo = ent.next().charAt(0);
                    } while(!Persona.comprobarSexo(sexo));
                    p.setSexo(sexo);
                    break;
                case 5:
                    System.out.print("Nuevo peso: ");
                    p.setPeso(ent.nextDouble());
                    break;
                case 6:
                    System.out.println("Nueva altura: ");
                    p.setAltura(ent.nextDouble());
                    break;
                case 7:
                    String direccion;
                    do {
                        System.out.print("Nueva direccion: ");
                        direccion = getLine();
                    } while(!Persona.validarDireccion(direccion));
                    p.setDireccion(direccion);
                    break;
            }

            if(Entrenador.class.isInstance(p)) {
                Entrenador e = (Entrenador)p;
                switch(opcion) {
                    case 8:
                        System.out.print("Experiencia laboral: ");
                        e.setExperienciaLaboral(ent.nextLine());
                        break;
                    case 9:
                        System.out.print("Especialidad: ");
                        e.setEspecialidad(ent.nextLine());
                        break;
                }
            } else if(Deportista.class.isInstance(p)) {
                Deportista d = (Deportista)p;
                switch(opcion) {
                    case 8:
                        System.out.print("Ritmo cardiaco en reposo: ");
                        d.setRitmoCardiaco(ent.nextInt());
                        break;
                    case 9:
                        System.out.print("Frecuencia de entrenamiento: ");
                        d.setFrecuenciaEnt(ent.next().charAt(0));
                        break;
                    case 10:
                        String nuevoTipo;
                        if(d.getEntrenador() == null) {
                            System.out.println("Recuerda que para un entrenamiento optimo, debes seleccionar a un entrenador que te guie");
                            System.out.print("Tipo de ejercicio: ");
                            nuevoTipo = ent.nextLine();
                        } else {
                            int determinar;
                            System.out.println("1. Definir ejercicio segun tu grasa\n2. Seleccionar ejercicios similares\n\nOpcion: ");
                            determinar = ent.nextInt();
                            System.out.println(d.getEntrenador().getNombre() + " dice:");
                            if(determinar == 1) {
                                nuevoTipo = d.getEntrenador().determinarRutina((int)d.calcularImc());
                            } else {
                                nuevoTipo = d.getEntrenador().determinarRutina(d.getTipoDeEjercicio());
                            }
                        }
                        d.setTipoDeEjercicio(nuevoTipo);
                        break;
                    case 11:
                        System.out.println("Seleccionar un entrenador de la lista:");
                        int entrenador;
                        mostrarPersonas(entrenadores);
                        System.out.print("Opcion: ");
                        entrenador = ent.nextInt();
                        d.setEntrenador(entrenadores.get(entrenador));
                        break;
                }
            }
        } while(opcion != 0);
    }

    public static String getLine() {
        Scanner ent = new Scanner(System.in);
        return ent.nextLine();
    }
    
    public static void limpiarPant() {
        for(int i = 0; i < 25; i++)
            System.out.println();
    }
}
