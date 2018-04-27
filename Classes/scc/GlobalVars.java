package scc;

public class GlobalVars {

    boolean balcoesOcupados;
    boolean balcoesLivres;

    public GlobalVars(){
        this.balcoesOcupados = false;
        this.balcoesLivres = true;
    }

    public void updateBalcoes(boolean ocupado){
        if(ocupado){
            this.balcoesLivres = false;
            this.balcoesOcupados = true;
        }
        else{
            this.balcoesLivres = true;
            this.balcoesOcupados = false;
        }
    }
}
