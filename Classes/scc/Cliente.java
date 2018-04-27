package scc;

// Classe que representa um cliente
// Como sao indistintos neste exemplo, esta vazia
public class Cliente {
    
    private boolean geral;
    private boolean empresarial;
    
    public Cliente(boolean tipo) {
        this.geral = tipo;this.empresarial = !tipo;
    }

    public boolean isGeral() {
        return geral;
    }
    public boolean isEmpresarial() {
        return empresarial;
    }

    public void setTipo(boolean tipo) {
        this.geral = tipo;
    }
    
}
