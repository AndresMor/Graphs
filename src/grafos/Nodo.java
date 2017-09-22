/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos;

import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author moralesea
 */
public class Nodo {
    int num;
    Nodo linkNodo;
    Subnodo linkSubnodo;
    class Subnodo {
        int num;
        int peso;
        Subnodo link;
    }
     Nodo agregarLista(Nodo ptr, int elem) {
        Nodo p = new Nodo();
        p.num = elem;
        // Lista vacía -> Nueva lista
        if (ptr == null) {
            return p;
        }
        // Lista no vacía
        Nodo q = ptr;
        // Buscar último
        while (q.linkNodo != null) {
            q = q.linkNodo;
        }
        q.linkNodo = p; // Agregamos link
        return ptr;
    }

    Nodo agregarSublista(Nodo ptr, int lista, int elem, int peso) {
        Nodo p = ptr;
        while (p != null && p.num != lista) {
            p = p.linkNodo;
        }
        if (p == null) {
            return ptr;
        }
        Subnodo q = p.linkSubnodo;
        Subnodo r = new Subnodo();
        r.num = elem;
        r.peso = peso;

        // Lista vacía
        if (q == null) {
            p.linkSubnodo = r;
        } else {
            while (q.link != null) {
                q = q.link;
            }
            q.link = r;
        }
        return ptr;
    }

    void mostrarMultilista(JList lista, Nodo ptr) {
        DefaultListModel model
                = (DefaultListModel) lista.getModel();
        model.clear();
        Nodo p = ptr;
        while (p != null) {
            String Sub = "";
            Subnodo q = p.linkSubnodo;
            while (q != null) {
                Sub = Sub + "->" + q.num + "(" + q.peso + ")";
                q = q.link;
            }
            model.addElement(p.num + Sub);
            p = p.linkNodo;
        }
    }
    
}
