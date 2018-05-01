package scc;

// Classe que representa a chegada de um cliente. Deriva de Evento.
public class Chegada extends Evento {
    
    int stream;
    boolean exponencial;

    //Construtor
    Chegada(double i, Simulador s, boolean geral, boolean exponencial) {
        super(i, s, geral);
        this.stream = 2;
        this.exponencial = exponencial;
    }

    // Metodo que executa as acçoes correspondentes a chegada de um cliente
    Cliente executa(Servico serv) {
        Cliente c;
        // Coloca cliente no serviço - na fila ou a ser atendido, conforme o caso
        serv.insereServico(new Cliente(this.isBalcaoGeral()));

        // Agenda nova chegada para daqui a Aleatorio.exponencial(s.media_cheg) instantes
        //s.insereEvento(new Chegada(s.getInstante() + Aleatorio.exponencial(s.getMedia_cheg(this.getTipo())), s, this.getTipo()));
        if(exponencial)
            s.insereEvento(new Chegada(s.getInstante() + Aleatorio.exponencial(s.getMedia_cheg(this.isBalcaoGeral())), s, this.isBalcaoGeral(), true));
        else
            s.insereEvento(new Chegada(s.getInstante() + Aleatorio.normal(stream,s.getMedia_cheg(this.isBalcaoGeral()),s.getDP(this.isBalcaoGeral())), s, this.isBalcaoGeral(), false));

        return null;
    }

    // Metodo que descreve o evento.
    // Para ser usado na listagem da lista de eventos.
    public String toString() {
        return "Chegada em " + instante;
    }
}
