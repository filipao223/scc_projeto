package scc;

import com.sun.org.apache.bcel.internal.classfile.SourceFile;

import javax.sound.midi.Soundbank;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Simulador {

    // Relógio de simulação - variàvel que contém o valor do tempo em cada instante
    private double instante;
    // Médias das distribuições de chegadas e de atendimento no serviço
    private double media_cheg_empresariais, media_cheg_geral, media_serv_empresariais, media_serv_empresariais_balcaoGeral, media_serv_geral, media_serv_geral_balcaoEmpresarial;
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

    boolean distrExponencial;

    boolean Geral = true;

    ByteArrayOutputStream resultados;

    // Construtor
    public Simulador() {
        // Inicialização de parâmetros do simulador
        media_cheg_empresariais = 35;
        media_serv_empresariais = 20;
        media_serv_empresariais_balcaoGeral = 23;
        media_cheg_geral = 12;
        media_serv_geral = 30;
        media_serv_geral_balcaoEmpresarial = 25;

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

        distrExponencial = false;

        Interface gui = new Interface(this);
    }

    public static void main(String[] args){
        // Cria um simulador e
        Simulador s = new Simulador();
        // põe-o em marcha
    }

    // Métdo que actualiza os valores estatísticos do simulador

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
            if (e1.isBalcaoGeral()) {
                e1.executa(servicoGeral);               // Executa evento
            } else {
                e1.executa(servicoEmpresarial);                 // Executa evento
            }
        };
        relat();  // Apresenta resultados de simulação finais
        saveResultsString();
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

    public void updateMedia_cheg(boolean geral, double newValue){
        if (geral) {
            this.media_cheg_geral = newValue;
        } else {
            this.media_cheg_empresariais = newValue;
        }
    }

    // Método que devolve a média dos tempos de serviço
    public double getMedia_serv(boolean geral, boolean balcaoGeral) {
        if (geral) {
            if(balcaoGeral)
                return media_serv_geral;
            else
                return this.getInstante() + media_serv_geral_balcaoEmpresarial;
        } else {
            if(balcaoGeral)
                return this.getInstante() + media_serv_empresariais_balcaoGeral;
            else
                return media_serv_empresariais;
        }
    }

    public void updateMedia_serv(boolean geral, boolean balcaoGeral, double newValue){
        if (geral) {
            if(balcaoGeral)
                this.media_serv_geral = newValue;
            else
                this.media_serv_geral_balcaoEmpresarial = newValue;
        } else {
            if(balcaoGeral)
                this.media_serv_empresariais_balcaoGeral = newValue;
            else
                this.media_serv_empresariais = newValue;
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

    public void updateDP(boolean geral, double newValue){
        if (geral){
            this.dpGeral = newValue;
        }
        else{
            this.dpEmpresa = newValue;
        }
    }

    public void updateNumFunc(boolean geral, int value){
        if(geral){
            servicoGeral.updateFunc(value);
        }
        else
            servicoEmpresarial.updateFunc(value);
    }

    public void updateDistr(boolean distrExponencial){
        this.distrExponencial = distrExponencial;
    }

    public void clearListaEventos(){
        lista = new ListaEventos(this);
    }

    public double getTempo(Cliente c, boolean balcaoGeral){
        if(c.isGeral() && balcaoGeral){ //Cliente geral no balcao geral
            return getInstante() + getMedia_serv(c.isGeral(), balcaoGeral); //Media geral no balcao geral
        }
        else if(c.isGeral() && !balcaoGeral){ //Cliente geral no balcao empresarial
            return getInstante() + getMedia_serv(c.isGeral(), !balcaoGeral); //Media geral no balcao empresarial
        }
        else if(!c.isGeral() && balcaoGeral){ //Cliente empresarial no balcao geral
            return getInstante() + getMedia_serv(!c.isGeral(), balcaoGeral); //Media empresarial no balcao geral
        }
        else{ //Cliente empresarial no balcao empresarial
            return getInstante() + getMedia_serv(!c.isGeral(), !balcaoGeral); //Media empresarial no balcao empresarial
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

    public void printSimConfig(){
        System.out.println("\n\nConfiguraçao simulador:");
        System.out.println("Chegada: ");
        System.out.printf("Média chegada geral: %.0f\nMédia chegada empresarial: %.0f\nDesvio padrão geral: %.0f\nDesvio padrão empresarial: %.0f\nDistribuição: %s", media_cheg_geral, media_cheg_empresariais, dpGeral, dpEmpresa, (distrExponencial?"Exponencial":"Normal"));
    }
}
