/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Estudiante
 */
public class Archivo {

    File matriz;
    File lista;

    public Archivo(File matriz, File lista) {
        this.matriz = matriz;
        this.lista = lista;
    }

    void guardarMatriz(JTable Matriz, File archivo) {
        // Crear un nuevo escritor
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(archivo))) {
            // Hallamos el numero de filas
            int filas = Matriz.getRowCount();

            // Tomamos el modelo
            DefaultTableModel model = (DefaultTableModel) Matriz.getModel();

            for (int i = 0; i < filas; ++i) {
                String row = "";
                for (int j = 0; j < filas; j++) {
                    row = row + model.getValueAt(i, j) + ",";
                }
                row = row.substring(0, row.length() - 1);
                bw.write(row);
                bw.newLine();
            }
        } catch (IOException ex) {
            // TODO enviar mensaje al usuario.
        }
    }

    void guardarLista(JList Lista, File archivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            DefaultListModel model = (DefaultListModel) Lista.getModel();
            int len = model.getSize();
            for (int i = 0; i < len; i++) {
                bw.write(model.getElementAt(i).toString());
                bw.newLine();
            }
        } catch (IOException ex) {

        }
    }

    void cargarMatriz(JTable Matriz, File archivo) {
        // Tomar el modelo de la tabla
        DefaultTableModel model = (DefaultTableModel) Matriz.getModel();
        try (Scanner lector = new Scanner(archivo)) {
            // Mientras el archivo tenga otra línea.
            int Vert = 0;
            while (lector.hasNextLine()) {
                Vert++;
                lector.nextLine();
            }
            
            for (int i = 0; i < Vert; i++) {
                model.addColumn(i+1);
            }
            Scanner leer = new Scanner(archivo);
            while (leer.hasNextLine()) {
                // Pedir la linea
                String linea = leer.nextLine();
                // Separar los datos
                Object[] datos = linea.split(",");
                model.addRow(datos);
            }
        } catch (FileNotFoundException ex) {
            // TODO enviar mensaje al usuario
        } catch (NumberFormatException ex) {
            // TODO enviar mensaje al usuario
        } catch (Exception ex) {
            // TODO enviar mensaje al usuario
        }
    }
    
    void cargarLista(JList lista, File archivo){
        Nodo ptr = new Nodo();
        try(Scanner lector = new Scanner(archivo)){
            while(lector.hasNextLine()){
                String linea = lector.nextLine();
                System.out.println(linea);
                int Ver = 0;
                for (int i = 0; i < linea.length(); i++) {
                    if (i==0) {
                        Ver = Integer.parseInt(linea.substring(0, 0));
                        ptr = ptr.agregarLista(ptr,Ver);
                    }
                    try{
                        System.out.println("entra aqui");
                        int Adj = Integer.parseInt(linea.substring(i, i));
                        int peso = Integer.parseInt(linea.substring(i+2, i+2));
                        ptr = ptr.agregarSublista(ptr, Ver, Adj, peso);
                    }catch(NumberFormatException ex){
                        System.out.println("No es un numero");
                    }
                }
            }
           ptr.mostrarMultilista(lista, ptr);
            
        }catch (FileNotFoundException ex) {
            // TODO enviar mensaje al usuario
        } catch (NumberFormatException ex) {
            // TODO enviar mensaje al usuario
        } catch (Exception ex) {
            // TODO enviar mensaje al usuario
        }
    }
    
}
