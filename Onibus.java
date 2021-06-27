package ProjetoFacul;

import java.util.Random;

public class Onibus extends Veiculo{
    private static final long serialVersionUID = 1L;
    private int passageiro;

    public int getPassageiro() {
        return passageiro;
    }

    public Onibus (String marca, String modelo, int ano, int km, int passageiro) {
        super(marca, modelo, ano, km);
        this.passageiro = passageiro;
        this.passageiro = getPassageiro();
        this.segmento = "Transporte Coletivo";

    }

    public String disponivel() {
        Random disp = new Random();
        if (disp.nextBoolean() == true) {return "Sim";}
        else return "Não";}
    public String toString() {
        return super.toString() + "Passageiros: "+ passageiro +"\n";
    }


}

