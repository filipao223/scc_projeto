package scc;

import java.util.*;
import scc.Simulador;

// Classe que representa um servi�o com uma fila de espera associada
public class Servico {

    private int estado; // Vari�vel que regista o estado do servi�o: 0 - livre; 1 - ocupado
    private int atendidos; // N�mero de clientes atendidos at� ao momento
    private double temp_ult, soma_temp_esp, soma_temp_serv; // Vari�veis para c�lculos estat�sticos
    private Vector<Cliente> fila; // Fila de espera do servi�o
    private Simulador s; // Refer�ncia para o simulador a que pertence o servi�o
    private int type, numEmpregados;
    
    // Construtor
    Servico(Simulador s, int type, int numEmpregados) {
        this.s = s;
        fila = new Vector<Cliente>(); // Cria fila de espera
        estado = 0; // Livre
        temp_ult = s.getInstante(); // Tempo que passou desde o �ltimo evento. Neste caso 0, porque a simula��o ainda n�o come�ou.
        atendidos = 0;  // Inicializa��o de vari�veis
        soma_temp_esp = 0;
        soma_temp_serv = 0;
        this.type = type;
        this.numEmpregados = numEmpregados;
    }

    // M�todo que insere cliente (c) no servi�o
    public void insereServico(Cliente c) {
        if (estado < numEmpregados) { // Se servi�o livre,
            estado++;     // fica ocupado e
            // agenda sa�da do cliente c para daqui a s.getMedia_serv() instantes
            s.insereEvento(new Saida(s.getInstante() + s.getMedia_serv(c.getTipo()), s, c.getTipo()));
        } else {
            fila.addElement(c); // Se servi�o ocupado, o cliente vai para a fila de espera
        }
    }

    // M�todo que remove cliente do servi�o
    public void removeServico() {
        atendidos++; // Regista que acabou de atender + 1 cliente
        if (fila.size() == 0) {
            estado--; // Se a fila est� vazia, liberta o servi�o
        } else { // Se n�o,
            // vai buscar pr�ximo cliente � fila de espera e
                Cliente c = (Cliente)fila.firstElement();
            fila.removeElementAt(0);
            // agenda a sua saida para daqui a s.getMedia_serv() instantes
            s.insereEvento(new Saida(s.getInstante() + s.getMedia_serv(c.getTipo()), s, c.getTipo()));
        }
    }

    // M�todo que calcula valores para estat�sticas, em cada passo da simula��o ou evento
    public void act_stats() {
        // Calcula tempo que passou desde o �ltimo evento
        double temp_desde_ult = s.getInstante() - temp_ult;
        // Actualiza vari�vel para o pr�ximo passo/evento
        temp_ult = s.getInstante();
        // Contabiliza tempo de espera na fila
        // para todos os clientes que estiveram na fila durante o intervalo
        soma_temp_esp += fila.size() * temp_desde_ult;
        // Contabiliza tempo de atendimento
        soma_temp_serv += estado * temp_desde_ult;
    }

    // M�todo que calcula valores finais estat�sticos
    public void relat() {
        // Tempo m�dio de espera na fila
        double temp_med_fila = soma_temp_esp / (atendidos + fila.size());
        // Comprimento m�dio da fila de espera
        // s.getInstante() neste momento � o valor do tempo de simula��o,
        // uma vez que a simula��o come�ou em 0 e este m�todo s� � chamdo no fim da simula��o
        double comp_med_fila = soma_temp_esp / s.getInstante();
        // Tempo m�dio de atendimento no servi�o
        double utilizacao_serv = (soma_temp_serv / s.getInstante()) / numEmpregados;
        // Apresenta resultados
        System.out.println("Tempo m�dio de espera " + temp_med_fila);
        System.out.println("Comp. m�dio da fila " + comp_med_fila);
        System.out.println("Utiliza��o do servi�o " + utilizacao_serv);
        System.out.println("Tempo de simula��o " + s.getInstante()); // Valor actual
        System.out.println("N�mero de clientes atendidos " + atendidos);
        System.out.println("N�mero de clientes na fila " + fila.size()); // Valor actual
    }

    // M�todo que devolve o n�mero de clientes atendidos no servi�o at� ao momento
    public int getAtendidos() {
        return atendidos;
    }

}