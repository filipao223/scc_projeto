package scc;

// Classe de onde vao ser derivados todos os eventos.
// Contem apenas os atributos e metodos comuns a todos os eventos.
// Por isso e uma classe abstracta. Nao havera instancias desta classe num simulador.
public abstract class Evento {

    protected double instante;  // Instante de ocorrencia do evento
    protected Simulador s;      // Simulador onde ocorre o evento
    private boolean balcaoGeral;
    
    //Construtor
    Evento(double i, Simulador s, boolean tipo) {
        instante = i;
        this.s = s;
        this.balcaoGeral = tipo;
    }

    public boolean isBalcaoGeral() {
        return balcaoGeral;
    }

    // Método que determina se o evento corrente ocorre primeiro, ou nao, do que o evento e1
    // Se sim, devolve true; se nao, devolve false
    // Usado para ordenar por ordem crescente de instantes de ocorrencia a lista de eventos do simulador
    public boolean menor(Evento e1) {
        return (instante < e1.instante);
    }

    // Método que executa um evento; a ser definido em cada tipo de evento
    abstract Cliente executa(Servico s);

    // Metodo que devolve o instante de ocorrencia do evento
    public double getInstante() {
        return instante;
    }
}
