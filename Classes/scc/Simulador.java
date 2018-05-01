package scc;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Simulador {

    // Relógio de simulação - variàvel que contém o valor do tempo em cada instante
    private double instante;
    // Médias das distribuições de chegadas e de atendimento no serviço
    private double media_cheg_empresariais, media_cheg_geral, media_serv_empresariais, media_serv_geral,dp_empresarial,dp_geral;
    private double media_serv_empresariais_balcaoGeral, media_serv_geral_balcaoEmpresarial;
    // Número de clientes que vão ser atendidos
    private int n_clientes;
    // Serviço - pode haver mais do que um num simulador
    private static Servico servicoEmpresarial, servicoGeral;
    // Lista de eventos - onde ficam registados todos os eventos que vão ocorrer na simulação
    // Cada simulador só tem uma
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
        // Inicialização de parâmetros do simulador
        media_cheg_empresariais = 35;
        media_cheg_geral = 12;
        media_serv_empresariais = 23;
        media_serv_empresariais_balcaoGeral =
        media_serv_geral = 25;

        n_clientes = 1000;
        Geral = true;
        // Inicialização do relógio de simulação
        instante = 0;
        // Criação do serviço
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
        // Se não for feito, o simulador não tem eventos para simular
        insereEvento(new Chegada(instante, this, false));
        insereEvento(new Chegada(instante, this, true));
    }

    public static void main(String[] args) {
        // Cria um simulador e
        Simulador s = new Simulador();
        // põe-o em marcha
        //s.executa();
    }
    // Método que actualiza os valores estatísticos do simulador

    // Método que insere o evento e1 na lista de eventos
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
            //	lista.print();  // Mostra lista de eventos - desnecessário; é apenas informativo
            e1 = (Evento) (lista.removeFirst());  // Retira primeiro evento (é o mais iminente) da lista de eventos
            instante = e1.getInstante();         // Actualiza relógio de simulação
            act_stats(); // Actualiza valores estatísticos
            if (e1.getType() == !Geral) {
                e1.executa(servicoEmpresarial);                 // Executa evento
            } else {
                e1.executa(servicoGeral);                 // Executa evento
            }
        }
        relat();  // Apresenta resultados de simulação finais
        saveResultsString();
    }

    // Método que devolve o instante de simulação corrente
    public double getInstante() {
        return instante;
    }

    // Método que devolve a média dos intervalos de chegada
    public double getMedia_cheg(boolean type) {
        if (type == !Geral) {
            return media_cheg_empresariais;
        } else {
            return media_cheg_geral;
        }
    }

    // Método que devolve a média dos tempos de serviço
    public double getMedia_serv(boolean type) {
        if (type == !Geral) {
            return media_serv_empresariais;
        } else {
            return media_serv_geral;
        }
    }

    public double getMediaServ(Cliente c, boolean balcaoGeral){
        if(c.getTipo() == true){
            if(balcaoGeral){
                return media_serv_geral;
            }
            else return media_serv_geral_balcaoEmpresarial;
        }
        else{
            if(balcaoGeral){
                return media_serv_empresariais_balcaoGeral;
            }
            else return  media_serv_empresariais;
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
