package scc;

public class Simulador {

    // Rel�gio de simula��o - vari�vel que cont�m o valor do tempo em cada instante
    private double instante;
    // M�dias das distribui��es de chegadas e de atendimento no servi�o
    private double media_cheg_empresariais, media_cheg_geral, media_serv_empresariais, media_serv_geral,dp_empresarial,dp_geral;
    // N�mero de clientes que v�o ser atendidos
    private int n_clientes;
    // Servi�o - pode haver mais do que um num simulador
    private static Servico servicoEmpresarial, servicoGeral;
    // Lista de eventos - onde ficam registados todos os eventos que v�o ocorrer na simula��o
    // Cada simulador s� tem uma
    private ListaEventos lista;

    public boolean Geral;

    // Construtor
    public Simulador() {
        // Inicializa��o de par�metros do simulador
        media_cheg_empresariais = 35;
        media_cheg_geral = 12;
        media_serv_empresariais = 23;
        media_serv_geral = 25;

        n_clientes = 1000;
        Geral = true;
        // Inicializa��o do rel�gio de simula��o
        instante = 0;
        // Cria��o do servi�o
        servicoEmpresarial = new Servico(this, !Geral, 1);
        servicoGeral = new Servico(this, Geral, 2);

        servicoEmpresarial.setAnotherOne(servicoGeral);
        servicoGeral.setAnotherOne(servicoEmpresarial);
        // Cria��o da lista de eventos
        lista = new ListaEventos(this);
        // Agendamento da primeira chegada
        // Se n�o for feito, o simulador n�o tem eventos para simular
        insereEvento(new Chegada(instante, this, false));
        insereEvento(new Chegada(instante, this, true));
    }

    public static void main(String[] args) {
        // Cria um simulador e
        Simulador s = new Simulador();
        // p�e-o em marcha
        s.executa();
    }
    // M�todo que actualiza os valores estat�sticos do simulador

    // M�todo que insere o evento e1 na lista de eventos
    void insereEvento(Evento e1) {
        lista.insereEvento(e1);
    }
    
    void removeEvento(Evento e1) {
        lista.removeEvento(e1);
    }

    private void act_stats() {
        servicoEmpresarial.act_stats();
        servicoGeral.act_stats();
    }

    // M�todo que apresenta os resultados de simula��o finais
    private void relat() {
        System.out.println();
        System.out.println("------- Resultados finais -------");
        System.out.println();
        servicoEmpresarial.relat();

        System.out.println();
        System.out.println("------- Resultados finais -------");
        System.out.println();
        servicoGeral.relat();
    }

    // M�todo executivo do simulador
    public void executa() {
        Evento e1;
        // Enquanto n�o atender todos os clientes
        while (servicoEmpresarial.getAtendidos() + servicoGeral.getAtendidos() < n_clientes) {
            //	lista.print();  // Mostra lista de eventos - desnecess�rio; � apenas informativo
            e1 = (Evento) (lista.removeFirst());  // Retira primeiro evento (� o mais iminente) da lista de eventos
            instante = e1.getInstante();         // Actualiza rel�gio de simula��o
            act_stats(); // Actualiza valores estat�sticos
            if (e1.getType() == !Geral) {
                e1.executa(servicoEmpresarial);                 // Executa evento
            } else {
                e1.executa(servicoGeral);                 // Executa evento
            }
        };
        relat();  // Apresenta resultados de simula��o finais
    }

    // M�todo que devolve o instante de simula��o corrente
    public double getInstante() {
        return instante;
    }

    // M�todo que devolve a m�dia dos intervalos de chegada
    public double getMedia_cheg(boolean type) {
        if (type == !Geral) {
            return media_cheg_empresariais;
        } else {
            return media_cheg_geral;
        }
    }

    // M�todo que devolve a m�dia dos tempos de servi�o
    public double getMedia_serv(boolean type) {
        if (type == !Geral) {
            return media_serv_empresariais;
        } else {
            return media_serv_geral;
        }
    }

    public double getDp(boolean type){
        if(type == !Geral){
            return dp_empresarial;
        }
        else{
            return dp_geral;
        }
    }

    public Servico getServicoEmpresarial() {
        return servicoEmpresarial;
    }

}
