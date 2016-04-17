/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Humanidad;

import java.util.regex.*;
/**
 *
 * @author Martinor
 */
public class Persona {
    private int cedula;
    private String nombre;
    private int edad;
    private char sexo;
    private double peso;
    private double altura;
    private String direccion;

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public char getSexo() {
        return Character.toUpperCase(sexo);
    }

    public void setSexo(char sexo) {
        this.sexo = Character.toUpperCase(sexo);
    }
    
    public Persona(int cedula, int edad, String nombre, char sexo) {
        this.cedula = cedula;
        this.edad = edad;
        this.nombre = nombre;
        this.sexo = sexo;
    }

    public Persona(int cedula, int edad, String nombre, char sexo, double peso, double altura, String direccion) {
        this.cedula = cedula;
        this.edad = edad;
        this.nombre = nombre;
        this.sexo = sexo;
        this.peso = peso;
        this.altura = altura;
        this.direccion = direccion;
    }
    
    public Persona() {
        this(0, 0, "", '\0');
    }
    
    public double calcularImc() {
       return (this.peso / Math.pow(this.altura, 2));
    }

    public int definirImc(int imc) {
        if (imc == 0) {
            imc = (int)calcularImc();
        }
        return imc < 18 ? -1 : imc < 25 ? 0 : imc < 35 ? 1 : 2;
    }
    
    public static boolean validarDireccion(String direccion) {
        Pattern letrasSignos;
        Matcher matcher;
        String patron = "[A-Za-z0-9#\\. ]*"; // "\\w*\\#*\\.*\\s*"

        letrasSignos = Pattern.compile(patron);
        matcher = letrasSignos.matcher(direccion);
        
        if(!matcher.matches()) {
            System.out.println("Direccion invalida, solo letras, numeros y los caracteres \'#\' y \'.\' son permitidos");
        }

        return matcher.matches();
    }
    
    public Boolean esMayorDeEdad() {
        return edad >= 18;
    }
    
    public static Boolean comprobarSexo(char sexo) {
        boolean valido = (Character.toUpperCase(sexo) == 'F' || Character.toUpperCase(sexo) == 'M');

        if(!valido) {
            System.out.println("'" + sexo + "' no es un sexo valido");
        }
        return valido;
    }
    
    public void imprimirDatosPer() {
        String imc = definirImc(0) == 0 ? "Ideal" : definirImc(0) < 0 ? "Critico" :  definirImc(0) < 2 ? "Sobrepeso" : "Obesidad";

        System.out.println("Nombre: " + this.nombre);
        System.out.println("Edad: " + this.edad + " Mayor de edad?: " + (esMayorDeEdad() ? "Si" : "No"));
        System.out.println("Direccion: " + this.direccion);
        System.out.println("Sexo: " + this.sexo);
        System.out.println("Peso: " + this.peso + " Estado IMC: " + imc);
        System.out.println("Altura: " + this.altura);
        System.out.println("----------------------------------------------\n");
    }
}
