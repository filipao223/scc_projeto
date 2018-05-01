package scc;

import java.util.*;
import scc.Simulador;

// Classe que representa um serviço com uma fila de espera associada
public class Servico {

    private int estado; // Variável que regista o estado do serviço: 0 - livre; 1 - ocupado
    private int atendidos; // Número de clientes atendidos até ao momento
    private double temp_ult, soma_temp_esp, soma_temp_serv; // Variáveis para cálculos estatísticos
    private Vector<Cliente> fila; // Fila de espera do serviço
    private Simulador s; // Referencia para o simulador a que pertence o serviço
    private boolean tipoBalcao; //Se true -> empresa, false -> geral
    private int numEmpregados;
    private Servico outroServico;
    private Vector<Cliente> currentGeralClients;

    private ListaEventos listaEventos;
    private Evento eventoReturn;
    
    // Construtor
    Servico(Simulador s, boolean type, int numEmpregados) {
        this.s = s;
        fila = new Vector<Cliente>(); // Cria fila de espera
        estado = 0; // Livre
        temp_ult = s.getInstante(); // Tempo que passou desde o ultimo evento. Neste caso 0, porque a simulaçao ainda nao começou.
        atendidos = 0;  // Inicializaçao de variàveis
        soma_temp_esp = 0;
        soma_temp_serv = 0;
        currentGeralClients = new Vector<Cliente>();
        listaEventos = null;
        eventoReturn = null;
        this.tipoBalcao = type; //Porque cliente empresarial é do tipo false (0)
        this.numEmpregados = numEmpregados;

        //System.out.println("Criado serviço " +(tipoBalcao?"empresarial":"geral")+ " com " + numEmpregados + " empregados.");
    }

    // Método que insere cliente (c) no serviço
    public void insereServico(Cliente c) {
        if (estado < numEmpregados) { // Se serviço livre,
            estado++;     // fica ocupado e

            if(c.isGeral() && tipoBalcao) //Lista de clientes gerais no balcao empresarial
                currentGeralClients.addElement(c);

            // agenda saída do cliente c para daqui a s.getTempo instantes, dependendo do balcao onde se encontra
            //tipoBalcao é true se for um balcao geral e false se for um empresarial
            eventoReturn = s.insereEvento(new Saida(s.getTempo(c, tipoBalcao),s, tipoBalcao));

        } else {
            if(outroServico.getEstado() < outroServico.getNumEmpregados()) {
                //if(!tipoBalcao) System.out.println("Passou um cliente geral para balcao empresa");
                outroServico.insereServico(c);
            }

            else if(this.tipoBalcao && !c.isGeral() && !currentGeralClients.isEmpty()){
                listaEventos.remove(eventoReturn);
                outroServico.fila.add(currentGeralClients.firstElement());

                currentGeralClients.removeElementAt(0);
                this.insereServico(c);

                //Atendimento
                eventoReturn = s.insereEvento(new Saida(s.getTempo(c, tipoBalcao), s, tipoBalcao));
            }

            else if(!tipoBalcao && !c.isGeral()){
                outroServico.fila.add(c);
            }

            else fila.addElement(c); // Se serviço ocupado, o cliente vai para a fila de espera
        }
    }

    // Método que remove cliente do serviço
    public void removeServico() {
        atendidos++; // Regista que acabou de atender + 1 cliente
        //currentClients.remove(clientToRemove); //O cliente foi atendido, remove-o da lista dos atendidos atualmente
        if (fila.size() == 0) {
            estado--; // Se a fila esta vazia, liberta o serviço
        } else { // Se nao,
            // vai buscar proximo cliente à fila de espera e
                Cliente c = (Cliente)fila.firstElement();
            fila.removeElementAt(0);
            // agenda a sua saida para daqui a s.getMedia_serv() instantes
            eventoReturn = s.insereEvento(new Saida(s.getTempo(c, tipoBalcao), s, tipoBalcao));
        }
    }

    // Método que calcula valores para estatísticas, em cada passo da simulação ou evento
    public void act_stats() {
        // Calcula tempo que passou desde o ultimo evento
        double temp_desde_ult = s.getInstante() - temp_ult;
        // Actualiza variavel para o proximo passo/evento
        temp_ult = s.getInstante();
        // Contabiliza tempo de espera na fila
        // para todos os clientes que estiveram na fila durante o intervalo
        soma_temp_esp += fila.size() * temp_desde_ult;
        // Contabiliza tempo de atendimento
        soma_temp_serv += estado * temp_desde_ult;
    }

    // Metodo que calcula valores finais estatísticos
    public void relat() {
        // Tempo medio de espera na fila
        double temp_med_fila = soma_temp_esp / (atendidos + fila.size());
        // Comprimento medio da fila de espera
        // s.getInstante() neste momento e o valor do tempo de simulaçao,
        // uma vez que a simulaçao começou em 0 e este metodo so e chamdo no fim da simulaçao
        double comp_med_fila = soma_temp_esp / s.getInstante();
        // Tempo medio de atendimento no serviço
        double utilizacao_serv = (soma_temp_serv / s.getInstante()) / numEmpregados;
        // Apresenta resultados
        System.out.println("Tempo medio de espera " + temp_med_fila);
        System.out.println("Comp. medio da fila " + comp_med_fila);
        System.out.println("Utilizaçao do serviço " + utilizacao_serv);
        System.out.println("Tempo de simulaçao " + s.getInstante()); // Valor actual
        System.out.println("Numero de clientes atendidos " + atendidos);
        System.out.println("Numero de clientes na fila " + fila.size()); // Valor actual
    }

    // Metodo que devolve o numero de clientes atendidos no serviço ate ao momento
    public int getAtendidos() {
        return atendidos;
    }

    public int getEstado(){return this.estado; }
    public int getNumEmpregados(){return this.numEmpregados;}

    public void setOutroServico(Servico servico){
        this.outroServico = servico;
    }

    public void setListaEventos(ListaEventos lista){
        this.listaEventos = lista;
    }

    public boolean HaClientesGerais(Vector<Cliente> lista){
        for(Cliente c:lista){
            if(c.isGeral()) return true;
        }
        return false;
    }

    public Cliente firstGeral(Vector<Cliente> lista){
        for (Cliente c:lista){
            if(c.isGeral()) return c;
        }
        return null;
    }

    public void updateFunc(int value){
        this.numEmpregados = value;
    }
}