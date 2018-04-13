// Classe que representa a saída de um cliente. Deriva de Evento.

public class Saida extends Evento {

	//Construtor
	Saida (double i, Simulador s){
		super(i, s);
	}

	// Método que executa as acções correspondentes à saída de um cliente
	void executa (Servico serv){
		// Retira cliente do serviço
        serv.removeServico();
    }

    // Método que descreve o evento.
    // Para ser usado na listagem da lista de eventos.
    public String toString(){
         return "Saída em " + instante;
    }


}