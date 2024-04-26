/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mongoaeropuerto;

import datos.DAOAerolineas;
import java.util.ArrayList;
import modelos.Aerolinea;

/**
 *
 * @author lv1825
 */
public class MongoAeropuerto {

    public static void main(String[] args) {
        DAOAerolineas a = new DAOAerolineas();
        
        Aerolinea nuevaAerolinea = new Aerolinea("AeroSky", "GB", "GBP", true);
        a.agregarAerolinea(nuevaAerolinea);
        
        ArrayList<Aerolinea> lista = a.obtenerAerolineas();
        for (Aerolinea arg : lista) {
            System.out.println(arg.toString());
        }
        
        
        System.out.println("Reimprimir lista");
        
        String idAEliminar = "662af4316b043853ade4e684";
        boolean eliminado = a.eliminarAerolinea(idAEliminar);
        if (eliminado) {
            System.out.println("Aerolínea eliminada con éxito.");
        } else {
            System.out.println("La aerolínea no se pudo eliminar o no se encontró.");
        }
        
        ArrayList<Aerolinea> listav2 = a.obtenerAerolineas();
        for (Aerolinea aerolinea : listav2) {
            System.out.println(aerolinea.toString());
        }
    }
}
