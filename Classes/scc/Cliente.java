package scc;

// Classe que representa um cliente
// Como sao indistintos neste exemplo, esta vazia
public class Cliente {
    
    private int tipo;
    
    public Cliente(int tipo) {
        this.tipo = tipo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
}
