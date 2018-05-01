package scc;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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

    //Verifica distribuiçao atual
    private boolean distrNormal;

    //Streams
    private int streamChegGeral;
    private int streamChegEmpr;
    private int streamServGeralGeral;
    private int streamServGeralEmpr;
    private int streamServEmprGeral;
    private int streamServEmprEmpr;

    ByteArrayOutputStream resultados;

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

        //Guarda em cada serviço o  outro
        servicoEmpresarial.outroServico(servicoGeral);
        servicoGeral.outroServico(servicoEmpresarial);

        //Variaveis interface
        distrNormal = true;

        Interface gui = new Interface(this);

        // Criaçãoo da lista de eventos
        lista = new ListaEventos(this);
        // Agendamento da primeira chegada
        // Se n�o for feito, o simulador n�o tem eventos para simular
        insereEvento(new Chegada(instante, this, false));
        insereEvento(new Chegada(instante, this, true));
    }

    public static void main(String[] args) {
        // Cria um simulador e
        Simulador s = new Simulador();
        // põe-o em marcha
        //s.executa();
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
        }
        relat();  // Apresenta resultados de simulação finais
        saveResultsString();
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

    public void updateMedia_cheg(boolean geral, double newValue){
        if(geral){
            this.media_cheg_geral = newValue;
        }
        else this.media_cheg_empresariais = newValue;
    }

    public void updateDP(boolean geral, double newValue){
        if(geral){
            this.dp_geral = newValue;
        }
        else
            this.dp_empresarial = newValue;
    }

    public void updateMedia_serv(boolean geral, boolean balcaoGeral, double newValue){
        if(geral){
            if(balcaoGeral){
                this.media_serv_geral = newValue;
            }
            else this.media_serv_geral_balcaoEmpresarial = newValue;
        }
        else{
            if(balcaoGeral){
                this.media_serv_empresariais_balcaoGeral = newValue;
            }
            else this.media_serv_empresariais = newValue;
        }
    }

    public void updateNumFunc(boolean balcaoGeral, int newValue){
        if(balcaoGeral){
            servicoGeral.updateNumFunc(newValue);
        }
        else servicoEmpresarial.updateNumFunc(newValue);
    }

    public void updateClientes(int newValue){
        this.n_clientes = newValue;
    }

    public void updateDistr(boolean normal){
        this.distrNormal = normal;
    }

    //Este método devolve o tempo de serviço correto para um determinado cliente (c) num determinado balcao (balcaoGeral)
    //Toma em conta também qual a distribuição selecionada
    public double getTempo(Cliente c, boolean balcaoGeral){
        if(c.getTipo() == true && balcaoGeral){
            if(distrNormal){                //balcaoGeral apenas guarda true ou false, ou é ou não é
                return getInstante() + Aleatorio.normal(getMediaServ(c, balcaoGeral), getDp(c.getTipo()), streamServGeralGeral);
            }
            else
                return getInstante() + Aleatorio.exponencial(getMediaServ(c, balcaoGeral)); //balcaoGeral apenas guarda true ou false, ou é ou não é
        }
        else if(c.getTipo() == true && !balcaoGeral){
            if(distrNormal){
                return getInstante() + Aleatorio.normal(getMediaServ(c, balcaoGeral), getDp(c.getTipo()), streamServGeralEmpr);
            }
            else
                return getInstante() + Aleatorio.exponencial(getMediaServ(c, balcaoGeral));
        }

        else if(c.getTipo() == false && balcaoGeral){
            if(distrNormal){
                return getInstante() + Aleatorio.normal(getMediaServ(c, balcaoGeral), getDp(c.getTipo()), streamServEmprGeral);
            }
            else
                return getInstante() + Aleatorio.exponencial(getMediaServ(c, balcaoGeral));
        }
        else{
            if(distrNormal){
                return getInstante() + Aleatorio.normal(getMediaServ(c, balcaoGeral), getDp(c.getTipo()), streamServEmprEmpr);
            }
            else
                return getInstante() + Aleatorio.exponencial(getMediaServ(c, balcaoGeral));
        }
    }

    public void saveResultsString(){
        resultados = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(resultados);

        PrintStream old = System.out;
        System.setOut(ps);

        System.out.println();
        System.out.println("------- Resultados finais -------");
        System.out.println();
        servicoEmpresarial.relat();

        System.out.println();
        System.out.println("------- Resultados finais -------");
        System.out.println();
        servicoGeral.relat();

        // Put things back
        System.out.flush();
        System.setOut(old);
    }

    public boolean isDistrNormal() {
        return distrNormal;
    }
}
