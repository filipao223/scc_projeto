package scc;

// Classe que representa a sa�da de um cliente. Deriva de Evento.
public class Saida extends Evento {

    //Construtor
    Saida(double i, Simulador s, boolean type) {
        super(i, s, type);
    }

    // M�todo que executa as ac��es correspondentes � sa�da de um cliente
    void executa(Servico serv) {
        // Retira cliente do servi�o
        serv.removeServico();
    }

    // M�todo que descreve o evento.
    // Para ser usado na listagem da lista de eventos.
    public String toString() {
        return "Sa�da em " + instante;
    }

}
