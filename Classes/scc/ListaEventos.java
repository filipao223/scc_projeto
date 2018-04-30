package scc;

import java.util.*;

// Classe que contem, em cada instante, os eventos a serem executados, ordenados por instantes de ocorrencia crescentes.
// Funciona como uma agenda.
// Deriva da classe LinkedList.
public class ListaEventos extends LinkedList<Evento> {

    private Simulador s;  // Simulador a que pertence a lista de eventos
    private static final long serialVersionUID = 1; // numero para serializaçao

    // Construtor
    ListaEventos(Simulador s) {
        this.s = s;
    }

    // Método para inserir um evento na lista de eventos
    public void insereEvento(Evento e1) {
        int i = 0;
        // Determina posiçao correcta do evento e1 na lista
        // A lista e ordenada por ordem crescente dos instantes de ocorrencia dos eventos
        while (i < size() && ((Evento) get(i)).menor(e1)) {
            i++;
        }
        // Coloca evento e1 na lista
        add(i, e1);
        //if(this.size() <0) System.out.printf("\n%d\n", this.size());
    }

    // Método informativo apenas. Imprime o conteúdo da lista de eventos em cada instante
    public void print() {
        int i;
        System.out.println("--- Lista de eventos em " + s.getInstante() + " ---");
        for (i = 0; i < size(); i++) {
            System.out.println("Evento " + (i + 1) + " e uma " + (Evento) (get(i)));
        }
    }
}
