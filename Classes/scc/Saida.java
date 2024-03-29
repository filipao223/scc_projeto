package scc;

// Classe que representa a saída de um cliente. Deriva de Evento.
public class Saida extends Evento {

    //Construtor
    Saida(double i, Simulador s, boolean tipo) {
        super(i, s, tipo);
    }

    // Método que executa as acçoes correspondentes à saída de um cliente
    Cliente executa(Servico serv) {
        // Retira cliente do serviço
            serv.removeServico();
        return null;
    }

    // Método que descreve o evento.
    // Para ser usado na listagem da lista de eventos.
    public String toString() {
        return "Saída em " + instante;
    }

}
