package scc;

import java.util.*;

// Classe que representa um servi�o com uma fila de espera associada
public class Servico {

    private int estado; // Vari�vel que regista o estado do servi�o: 0 - livre; 1 - ocupado
    private int atendidos; // N�mero de clientes atendidos at� ao momento
    private double temp_ult, soma_temp_esp, soma_temp_serv; // Vari�veis para c�lculos estat�sticos
    private Vector<Cliente> fila; // Fila de espera do servi�o
    private Simulador s; // Refer�ncia para o simulador a que pertence o servi�o
    private int numEmpregados;
    private Cliente current;
    private Servico anotherOne;

    // Construtor
    Servico(Simulador s, boolean type, int numEmpregados) {
        this.s = s;
        fila = new Vector<Cliente>(); // Cria fila de espera
        estado = 0; // Livre
        temp_ult = s.getInstante(); // Tempo que passou desde o �ltimo evento. Neste caso 0, porque a simula��o ainda n�o come�ou.
        atendidos = 0;  // Inicializa��o de vari�veis
        soma_temp_esp = 0;
        soma_temp_serv = 0;
        this.numEmpregados = numEmpregados;
    }

    // M�todo que insere cliente (c) no servi�o
    public void insereServico(Cliente c) {
        if (estado < numEmpregados) { // Se servi�o livre,
            c.setEvento(new Saida(s.getInstante() + Aleatorio.normal(s.getMedia_serv(c.getTipo()), s.getDp(c.getTipo()), 50), s, c.getTipo()));
            current = c;
            estado++;     // fica ocupado e
            // agenda sa�da do cliente c para daqui a s.getMedia_serv() instantes
            s.insereEvento(c.getEvento());
        } else {
            //TRUE É GERAL
            if (c.getTipo() == true && anotherOne.estado < anotherOne.numEmpregados) { //Quando o tipo é geral, e há espaço na empresarial
                    anotherOne.estado++;
                    c.setEvento(new Saida(s.getInstante() + Aleatorio.normal(s.getMedia_serv(false), s.getDp(false), 2), s, false));
                    anotherOne.current = c;
                    s.insereEvento(c.getEvento());

            }else if(c.getTipo() == true && anotherOne.estado == anotherOne.numEmpregados) { //Quando o tipo é geral, e não há espaço na empresarial
                    this.fila.add(c);
            }

            //TRUE É GERAL
            else if (c.getTipo() == false && s.getServicoEmpresarial().estado == 0) { //Quando o tipo é empresarial,está a voltar à fila empresarial e n tem gente
                    s.insereEvento(new Saida(s.getInstante() + Aleatorio.normal(s.getMedia_serv(false), s.getDp(false), 3), s, c.getTipo()));

            }else if (c.getTipo() == false && s.getServicoEmpresarial().estado != 0) { //Quando o tipo é empresarial,está a voltar e a fila está com gente

                if (s.getServicoEmpresarial().current != null && s.getServicoEmpresarial().current.getTipo() == true) { //Caso em que interrompe a fila empresarial
                    s.removeEvento(s.getServicoEmpresarial().current.getEvento());
                    anotherOne.fila.add(0, current);
                    this.current = c;
                    //JÁ TE DISSO QUE O TRUE ERA GERAL
                    s.insereEvento(new Saida(s.getInstante() + Aleatorio.normal(s.getMedia_serv(c.getTipo()), s.getDp(c.getTipo()), 4), s, c.getTipo()));

                } else if (s.getServicoEmpresarial().current != null && s.getServicoEmpresarial().current.getTipo() == false) { //Caso em que n interrompe a fila empresarial
                    this.fila.add(c);
                }
            }
        }
    }

    // M�todo que remove cliente do servi�o
    public void removeServico() {
        atendidos++; // Regista que acabou de atender + 1 cliente
        if (fila.size() == 0) {
            estado--; // Se a fila est� vazia, liberta o servi�o
        } else { // Se n�o,
            // vai buscar pr�ximo cliente � fila de espera e
            Cliente c = (Cliente) fila.firstElement();
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

    public void setAnotherOne(Servico anotherOne) {
        this.anotherOne = anotherOne;
    }
}
