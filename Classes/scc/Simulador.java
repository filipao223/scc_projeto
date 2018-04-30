package scc;

public class Simulador {

    // Relógio de simulação - variàvel que contém o valor do tempo em cada instante
    private double instante;
    // Médias das distribuições de chegadas e de atendimento no serviço
    private double media_cheg_empresariais, media_cheg_geral, media_serv_empresariais, media_serv_geral;
    //Desvios padroes
    private double dpEmpresa, dpGeral;
    // Número de clientes que vão ser atendidos
    private int n_clientes;
    // Serviço - pode haver mais do que um num simulador
    private static Servico servicoEmpresarial;
    private static Servico servicoGeral;
    // Lista de eventos - onde ficam registados todos os eventos que vão ocorrer na simulação
    // Cada simulador só tem uma
    private ListaEventos lista;

    boolean Geral = true;

    // Construtor
    public Simulador() {
        // Inicialização de parâmetros do simulador
        media_cheg_empresariais = 35;
        media_serv_empresariais = 20;
        media_cheg_geral = 12;
        media_serv_geral = 30;
        dpEmpresa = 4;
        dpGeral = 8;
        n_clientes = 1000;
        // Inicialização do relógio de simulação
        instante = 0;
        // Criação do serviço
        servicoEmpresarial = new Servico(this, !Geral, 1);
        servicoGeral = new Servico(this, Geral, 2);

        servicoEmpresarial.setOutroServico(servicoGeral);
        servicoGeral.setOutroServico(servicoEmpresarial);

        // Criação da lista de eventos
        lista = new ListaEventos(this);

        servicoEmpresarial.setListaEventos(lista);
        servicoGeral.setListaEventos(lista);

        // Agendamento da primeira chegada
        // Se não for feito, o simulador não tem eventos para simular
        insereEvento(new Chegada(instante, this, !Geral));
        insereEvento(new Chegada(instante, this, Geral));
    }

    // programa principal
    public static void main(String[] args) {
        // Cria um simulador e
        Simulador s = new Simulador();
        // põe-o em marcha
        s.executa();
    }
    // Método que actualiza os valores estatísticos do simulador

    // Método que insere o evento e1 na lista de eventos
    Evento insereEvento(Evento e1) {
        lista.insereEvento(e1);
        return e1;
    }

    private void act_stats() {
        servicoEmpresarial.act_stats();
        servicoGeral.act_stats();
    }

    // Método que apresenta os resultados de simulação finais
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

    // Método executivo do simulador
    public void executa() {
        Evento e1;
        // Enquanto não atender todos os clientes
        while (servicoEmpresarial.getAtendidos() + servicoGeral.getAtendidos() < n_clientes) {
            Cliente c = null;
            //	lista.print();  // Mostra lista de eventos - desnecessário; é apenas informativo
            e1 = (Evento) (lista.removeFirst());  // Retira primeiro evento (é o mais iminente) da lista de eventos
            instante = e1.getInstante();         // Actualiza relógio de simulação
            act_stats(); // Actualiza valores estatísticos
            if (e1.isGeral()) {
                e1.executa(servicoGeral);               // Executa evento
            } else {
                e1.executa(servicoEmpresarial);                 // Executa evento
            }
        };
        relat();  // Apresenta resultados de simulação finais
    }

    // Método que devolve o instante de simulação corrente
    public double getInstante() {
        return instante;
    }

    // Método que devolve a média dos intervalos de chegada
    public double getMedia_cheg(boolean geral) {
        if (geral) {
            return media_cheg_geral;
        } else {
            return media_cheg_empresariais;
        }
    }

    // Método que devolve a média dos tempos de serviço
    public double getMedia_serv(boolean geral) {
        if (geral) {
            return media_serv_geral;
        } else {
            return media_serv_empresariais;
        }
    }
    
    public double getDP(boolean geral){
        if (geral){
            return dpGeral;
        }
        else{
            return dpEmpresa;
        }
    }
}
