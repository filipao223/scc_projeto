public class Simulador {

 	// Rel�gio de simula��o - vari�vel que cont�m o valor do tempo em cada instante
    private double instante;
    // M�dias das distribui��es de chegadas e de atendimento no servi�o
	private double media_cheg, media_serv;
	// N�mero de clientes que v�o ser atendidos
	private int n_clientes;
	//Desvio padrao
    private int desv_padr_empres;
    private int desv_padr_cliente;
	// Servi�o - pode haver mais do que um num simulador
    private Servico servicoGeral;
    private Servico servicoEmpresa;
    // Lista de eventos - onde ficam registados todos os eventos que v�o ocorrer na simula��o
    // Cada simulador s� tem uma
	private ListaEventos lista;

    // Construtor
    public Simulador() {
		// Inicializa��o de par�metros do simulador
        media_cheg = 1;
		media_serv = 1.5;
		n_clientes = 100;
		desv_padr_empres = 4;
		desv_padr_cliente = 8;
		// Inicializa��o do rel�gio de simula��o
		instante = 0;
		// Cria��o do servi�o
		servicoGeral = new Servico (this);
		servicoEmpresa = new Servico(this);
		// Cria��o da lista de eventos
		lista = new ListaEventos(this);
		// Agendamento da primeira chegada
        // Se n�o for feito, o simulador n�o tem eventos para simular
		insereEvento (new Chegada(instante, this));
    }

        // programa principal
        public static void main(String[] args) {
            // Cria um simulador e
            Simulador s = new Simulador();
            // p�e-o em marcha
            s.executa();
        }

    // M�todo que insere o evento e1 na lista de eventos
	void insereEvento (Evento e1){
		lista.insereEvento (e1);
	}

    // M�todo que actualiza os valores estat�sticos do simulador
	private void act_stats(){
		servicoGeral.act_stats();
		servicoEmpresa.act_stats();
	}

    // M�todo que apresenta os resultados de simula��o finais
	private void relat (){
    	System.out.println();
    	System.out.println("------- Resultados finais -------");
    	System.out.println();
		servicoGeral.relat();
		servicoEmpresa.relat();
	}

    // M�todo executivo do simulador
	public void executa (){
		Evento e1;
		// Enquanto n�o atender todos os clientes
		while ((servicoGeral.getAtendidos() + servicoEmpresa.getAtendidos()) < n_clientes){
    	//	lista.print();  // Mostra lista de eventos - desnecess�rio; � apenas informativo
			e1 = (Evento)(lista.removeFirst());  // Retira primeiro evento (� o mais iminente) da lista de eventos
			instante = e1.getInstante();         // Actualiza rel�gio de simula��o
			act_stats();                         // Actualiza valores estat�sticos
			e1.executa(servicoEmpresa);                 // Executa evento
		};
		relat();  // Apresenta resultados de simula��o finais
	}

    // M�todo que devolve o instante de simula��o corrente
    public double getInstante() {
        return instante;
    }

    // M�todo que devolve a m�dia dos intervalos de chegada
    public double getMedia_cheg() {
        return media_cheg;
    }

    // M�todo que devolve a m�dia dos tempos de servi�o
    public double getMedia_serv() {
        return media_serv;
    }

}