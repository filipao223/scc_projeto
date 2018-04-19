package scc;

// Classe que representa a chegada de um cliente. Deriva de Evento.
public class Chegada extends Evento {
    
    int S;
    //Construtor
    Chegada(double i, Simulador s, int type) {
        super(i, s, type);
        this.S = 2;
    }

    // Metodo que executa as acçoes correspondentes a chegada de um cliente
    void executa(Servico serv) {
        // Coloca cliente no serviço - na fila ou a ser atendido, conforme o caso
        serv.insereServico(new Cliente(this.getTipo()));
        // Agenda nova chegada para daqui a Aleatorio.exponencial(s.media_cheg) instantes
        s.insereEvento(new Chegada(s.getInstante() + Aleatorio.exponencial(s.getMedia_cheg(this.getTipo())), s, this.getTipo()));
        //s.insereEvento(new Chegada(s.getInstante() + Aleatorio.normal(S,s.getMedia_cheg(this.getType()), 3), s, this.getType()));
    }

    // Metodo que descreve o evento.
    // Para ser usado na listagem da lista de eventos.
    public String toString() {
        return "Chegada em " + instante;
    }
}
