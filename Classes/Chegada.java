package scc;

// Classe que representa a chegada de um cliente. Deriva de Evento.
public class Chegada extends Evento {
    
    int S;
    //Construtor
    Chegada(double i, Simulador s, int type) {
        super(i, s, type);
        this.S = 2;
    }

    // M�todo que executa as ac��es correspondentes � chegada de um cliente
    void executa(Servico serv) {
        // Coloca cliente no servi�o - na fila ou a ser atendido, conforme o caso
        serv.insereServico(new Cliente(this.getTipo()));
        // Agenda nova chegada para daqui a Aleatorio.exponencial(s.media_cheg) instantes
        s.insereEvento(new Chegada(s.getInstante() + Aleatorio.exponencial(s.getMedia_cheg(this.getTipo())), s, this.getTipo()));
        //s.insereEvento(new Chegada(s.getInstante() + Aleatorio.normal(S,s.getMedia_cheg(this.getType()), 3), s, this.getType()));
    }

    // M�todo que descreve o evento.
    // Para ser usado na listagem da lista de eventos.
    public String toString() {
        return "Chegada em " + instante;
    }
}
