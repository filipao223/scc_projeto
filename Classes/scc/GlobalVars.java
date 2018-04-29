package scc;

public class GlobalVars {

    boolean balcoesOcupados;
    boolean balcoesLivres;


    int estadoGeral;
    int estadoEmpresarial;

    public GlobalVars(){
        estadoGeral = 0;
        estadoEmpresarial = 0;
    }

    public void updateBalcoes(int estado, boolean tiposervico){

        if(tiposervico)
            estadoGeral = estado;
        else
            estadoEmpresarial = estado;
    }
}
