package scc;

// Classe que representa a chegada de um cliente. Deriva de Evento.
public class Chegada extends Evento {
    
    //Construtor
    Chegada(double i, Simulador s, boolean type) {
        super(i, s, type);
    }

    // M�todo que executa as ac��es correspondentes � chegada de um cliente
    void executa(Servico serv) {
        // Coloca cliente no servi�o - na fila ou a ser atendido, conforme o caso
        serv.insereServico(new Cliente(this.getType()));
        // Agenda nova chegada para daqui a Aleatorio.exponencial(s.media_cheg) instantes
        s.insereEvento(new Chegada(s.getInstante() + Aleatorio.exponencial(s.getMedia_cheg(this.getType())), s, this.getType()));
    }

    // M�todo que descreve o evento.
    // Para ser usado na listagem da lista de eventos.
    public String toString() {
        return "Chegada em " + instante;
    }
}
