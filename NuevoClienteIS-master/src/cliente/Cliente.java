/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class Cliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //System.out.println("PRUEBAS ADMINISTRADOR DE ENTRADAS");
        //Pelicula
        
        System.out.print("Introduzca un id: ");
        String identificador= "";
        Scanner entradaID = new Scanner (System.in); 
        identificador = entradaID.nextLine ();
        
        System.out.println("Peliculas disponibles: "+ new AdministradorSalas().getAllPeliculas_JSON());
        System.out.print("Introduzca la película: ");
        String pelicula = "";
        Scanner entradaEscaner = new Scanner (System.in); 
        pelicula = entradaEscaner.nextLine ();
        
        //Salas
        System.out.println("Salas disponibles: "+new AdministradorSalas().getSala_JSON(pelicula));
        System.out.print("Introduzca la sala: ");
        String sala = "";
        Scanner entradaSala = new Scanner (System.in); 
        sala = entradaSala.nextLine ();
        
        //Butacas
        System.out.print("Introduzca la fila [0-25]: ");
        String fila = "";
        Scanner entradaFila = new Scanner (System.in); 
        fila = entradaFila.nextLine();
        System.out.println("Butacas ocupadas: "+new AdministradorEntradas().getButacasByFilaSala_JSON(sala, fila));
        
        System.out.print("Introduzca una butaca [0-30]: ");
        String butaca = "";
        Scanner entradaButaca = new Scanner (System.in); 
        butaca = entradaButaca.nextLine();
        
        new AdministradorEntradas().putEntrada_JSON(butaca,identificador, pelicula, sala, fila, butaca);
        
        
        /*System.out.println("getButacasByFilaSala: "+new AdministradorEntradas().getButacasByFilaSala_JSON("Sala 1", "12"));
        System.out.println("getButacasOcupadas: "+new AdministradorEntradas().getButacasOcupadas_JSON("Sala 1"));
        System.out.println("getDetallesEntrada: "+new AdministradorEntradas().getDetallesEntrada_JSON("1"));
        
        System.out.println("PRUEBAS ADMINISTRADOR DE SALAS");
        System.out.println("getAllPeliculas: "+new AdministradorSalas().getAllPeliculas_JSON());
        System.out.println("getAllSala: "+new AdministradorSalas().getAllSala_JSON());
        System.out.println("getPelicula: "+new AdministradorSalas().getPelicula_JSON("Sala 1"));
        System.out.println("getSala: "+new AdministradorSalas().getSala_JSON("El más alla"));*/

    }
    
}