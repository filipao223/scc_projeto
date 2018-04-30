package scc;

// Classe que representa um cliente
// Como sao indistintos neste exemplo, esta vazia
public class Cliente {
    
    private boolean geral;
    
    public Cliente(boolean tipo) {
        this.geral = tipo;
    }

    public boolean isGeral() {
        return geral;
    }

    public void setTipo(boolean tipo) {
        this.geral = tipo;
    }
    
}
