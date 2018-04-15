package scc;

import java.util.*;

// Classe que cont�m, em cada instante, os eventos a serem executados, ordenados por instantes de ocorr�ncia crescentes.
// Funciona como uma agenda.
// Deriva da classe LinkedList.
public class ListaEventos extends LinkedList<Evento> {

    private Simulador s;  // Simulador a que pertence a lista de eventos
    private static final long serialVersionUID = 1; // n�mero para serializa��o 

    // Construtor
    ListaEventos(Simulador s) {
        this.s = s;
    }

    // M�todo para inserir um evento na lista de eventos
    public void insereEvento(Evento e1) {
        int i = 0;
        // Determina posi��o correcta do evento e1 na lista
        // A lista � ordenada por ordem crescente dos instantes de ocorr�ncia dos eventos
        while (i < size() && ((Evento) get(i)).menor(e1)) {
            i++;
        }
        // Coloca evento e1 na lista
        add(i, e1);
    }

    // M�todo informativo apenas. Imprime o conte�do da lista de eventos em cada instante
    public void print() {
        int i;
        System.out.println("--- Lista de eventos em " + s.getInstante() + " ---");
        for (i = 0; i < size(); i++) {
            System.out.println("Evento " + (i + 1) + " � uma " + (Evento) (get(i)));
        }
    }
}
