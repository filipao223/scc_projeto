public class Simulador {

 	// Relógio de simulação - variável que contém o valor do tempo em cada instante
    private double instante;
    // Médias das distribuições de chegadas e de atendimento no serviço
	private double media_cheg, media_serv;
	// Número de clientes que vão ser atendidos
	private int n_clientes;
	//Desvio padrao
    private int desv_padr_empres;
    private int desv_padr_cliente;
	// Serviço - pode haver mais do que um num simulador
    private Servico servicoGeral;
    private Servico servicoEmpresa;
    // Lista de eventos - onde ficam registados todos os eventos que vão ocorrer na simulação
    // Cada simulador só tem uma
	private ListaEventos lista;

    // Construtor
    public Simulador() {
		// Inicialização de parâmetros do simulador
        media_cheg = 1;
		media_serv = 1.5;
		n_clientes = 100;
		desv_padr_empres = 4;
		desv_padr_cliente = 8;
		// Inicialização do relógio de simulação
		instante = 0;
		// Criação do serviço
		servicoGeral = new Servico (this);
		servicoEmpresa = new Servico(this);
		// Criação da lista de eventos
		lista = new ListaEventos(this);
		// Agendamento da primeira chegada
        // Se não for feito, o simulador não tem eventos para simular
		insereEvento (new Chegada(instante, this));
    }

        // programa principal
        public static void main(String[] args) {
            // Cria um simulador e
            Simulador s = new Simulador();
            // põe-o em marcha
            s.executa();
        }

    // Método que insere o evento e1 na lista de eventos
	void insereEvento (Evento e1){
		lista.insereEvento (e1);
	}

    // Método que actualiza os valores estatísticos do simulador
	private void act_stats(){
		servicoGeral.act_stats();
		servicoEmpresa.act_stats();
	}

    // Método que apresenta os resultados de simulação finais
	private void relat (){
    	System.out.println();
    	System.out.println("------- Resultados finais -------");
    	System.out.println();
		servicoGeral.relat();
		servicoEmpresa.relat();
	}

    // Método executivo do simulador
	public void executa (){
		Evento e1;
		// Enquanto não atender todos os clientes
		while ((servicoGeral.getAtendidos() + servicoEmpresa.getAtendidos()) < n_clientes){
    	//	lista.print();  // Mostra lista de eventos - desnecessário; é apenas informativo
			e1 = (Evento)(lista.removeFirst());  // Retira primeiro evento (é o mais iminente) da lista de eventos
			instante = e1.getInstante();         // Actualiza relógio de simulação
			act_stats();                         // Actualiza valores estatísticos
			e1.executa(servicoEmpresa);                 // Executa evento
		};
		relat();  // Apresenta resultados de simulação finais
	}

    // Método que devolve o instante de simulação corrente
    public double getInstante() {
        return instante;
    }

    // Método que devolve a média dos intervalos de chegada
    public double getMedia_cheg() {
        return media_cheg;
    }

    // Método que devolve a média dos tempos de serviço
    public double getMedia_serv() {
        return media_serv;
    }

}