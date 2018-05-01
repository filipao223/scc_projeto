package scc;

// Classe que representa um cliente
// Como são indistintos neste exemplo, está vazia
public class Cliente {
    
    private boolean type;
    private Evento evento;
    
    public Cliente(boolean type) {
        this.type = type;
    }

    public boolean getTipo() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }
    
    public void setEvento(Evento e1) {
        this.evento = e1;
    }

    public Evento getEvento() {
        return evento;
    }
    
}
