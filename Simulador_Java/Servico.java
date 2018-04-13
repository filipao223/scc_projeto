import java.util.*;

// Classe que representa um serviço com uma fila de espera associada

public class Servico {
    private int estado; // Variável que regista o estado do serviço: 0 - livre; 1 - ocupado
    private int atendidos; // Número de clientes atendidos até ao momento
    private double temp_ult, soma_temp_esp, soma_temp_serv; // Variáveis para cálculos estatísticos
    private Vector<Cliente> fila; // Fila de espera do serviço
    private Simulador s; // Referência para o simulador a que pertence o serviço

    // Construtor
    Servico (Simulador s){
        this.s = s;
        fila = new Vector <Cliente>(); // Cria fila de espera para empresarios
        estado = 0; // Livre
        temp_ult = s.getInstante(); // Tempo que passou desde o último evento. Neste caso 0, porque a simulação ainda não começou.
        atendidos = 0;  // Inicialização de variáveis
        soma_temp_esp = 0;
        soma_temp_serv = 0;
    }

    // Método que insere cliente (c) no serviço
    public void insereServico (Cliente c){
        if (estado == 0) { // Se serviço livre,
            estado ++;     // fica ocupado e
            // agenda saída do cliente c para daqui a s.getMedia_serv() instantes
            s.insereEvento (new Saida(s.getInstante()+s.getMedia_serv(), s));
        }
        else fila.addElement(c); // Se serviço ocupado, o cliente vai para a fila de espera
    }

    // Método que remove cliente do serviço
    public void removeServico (){
        atendidos++; // Regista que acabou de atender + 1 cliente
        if (fila.size()== 0) estado --; // Se a fila está vazia, liberta o serviço
        else { // Se não,
            // vai buscar próximo cliente à fila de espera e
            // Cliente c = (Cliente)fila.firstElement();
            fila.removeElementAt(0);
            // agenda a sua saida para daqui a s.getMedia_serv() instantes
            s.insereEvento (new Saida(s.getInstante()+s.getMedia_serv(),s));
        }
    }

    // Método que calcula valores para estatísticas, em cada passo da simulação ou evento
    public void act_stats(){
        // Calcula tempo que passou desde o último evento
        double temp_desde_ult = s.getInstante() - temp_ult;
        // Actualiza variável para o próximo passo/evento
        temp_ult = s.getInstante();
        // Contabiliza tempo de espera na fila
        // para todos os clientes que estiveram na fila durante o intervalo
        soma_temp_esp += fila.size() * temp_desde_ult;
        // Contabiliza tempo de atendimento
        soma_temp_serv += estado * temp_desde_ult;
    }

    // Método que calcula valores finais estatísticos
    public void relat (){
        // Tempo médio de espera na fila
        double temp_med_fila = soma_temp_esp / (atendidos+fila.size());
        // Comprimento médio da fila de espera
        // s.getInstante() neste momento é o valor do tempo de simulação,
        // uma vez que a simulação começou em 0 e este método só é chamdo no fim da simulação
        double comp_med_fila = soma_temp_esp / s.getInstante();
        // Tempo médio de atendimento no serviço
        double utilizacao_serv = soma_temp_serv / s.getInstante();
        // Apresenta resultados
        System.out.println("Tempo médio de espera "+temp_med_fila);
        System.out.println("Comp. médio da fila "+comp_med_fila);
        System.out.println("Utilização do serviço "+utilizacao_serv);
        System.out.println("Tempo de simulação "+s.getInstante()); // Valor actual
        System.out.println("Número de clientes atendidos "+atendidos);
        System.out.println("Número de clientes na fila "+fila.size()); // Valor actual
    }

    // Método que devolve o número de clientes atendidos no serviço até ao momento
    public int getAtendidos() {
        return atendidos;
    }
}
