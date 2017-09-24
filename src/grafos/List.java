/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author moralesea
 */
public class List {
    Nodo ptr;

    public List(Nodo ptr) {
        this.ptr = ptr;
    }

    class Nodo {
        int num;
        Nodo linkNodo;
        Subnodo linkSubnodo;
    }

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

    Queue BFS(Nodo ptr) {
        Queue Recor = new LinkedList<>();
        if (ptr != null) {
            Queue c = new LinkedList<>();
            c.add(ptr.num);
            while (!c.isEmpty()) {
                Nodo p = ptr;
                int X = (int) c.remove();
                Recor.add(X);
                while (p != null && p.num != X) {
                    p = p.linkNodo;
                }
                Subnodo q = p.linkSubnodo;
                while (q != null) {
                    if (!c.contains(q.num)&& !Recor.contains(q.num)) {
                        c.add(q.num);
                    }
                    q = q.link;
                }
            }
        }
        return Recor;
    }
    
    Stack<String> DFS(Nodo ptr){
        Stack<String> Recor = new Stack<String>();
        if (ptr != null) {
            Stack<String> pila = new Stack<String>();
            pila.push(Integer.toString(ptr.num));
            while(!pila.empty()){
                Nodo p = ptr;
                String X = pila.pop();
                Recor.push(X);
                while (p != null && p.num != Integer.parseInt(X)) {
                    p = p.linkNodo;
                }
                Subnodo q = p.linkSubnodo;
                while (q != null) {
                    if (!Recor.contains(Integer.toString(q.num))&& !pila.contains(Integer.toString(q.num))) {
                        pila.push(Integer.toString(q.num));
                    }
                    q = q.link;
                }
            }
        }
        return Recor;
    }
   
    int [] Dijkstra(Nodo ptr,int Base){
        int[] Distancia = new int[100];
        Base = Base-1;
        if (ptr != null)  {
            Queue c = new LinkedList<>();
            Distancia[Base] = 0;
            c.add(ptr.num);
            int Ant = 0;
            int i = 0;
            while(!c.isEmpty()){
                Nodo p = ptr;
                int X = (int)c.remove();
                while (p != null && p.num != X) {
                    p = p.linkNodo;
                }
                Subnodo q = p.linkSubnodo;
                while (q != null) {
                    if (!c.contains(q.num)) {
                        c.add(q.num);
                        Base++;
                        Distancia[Base]=q.peso+ Ant;
                    }
                    if (c.contains(q.num)&&Distancia[Base]>(Ant+q.peso)) {
                        Distancia[Base] = q.peso+Ant;
                    }
                    q = q.link;
                }
                i++;
                Ant = Distancia[i];
            }
        }
        return Distancia;
    }
}
