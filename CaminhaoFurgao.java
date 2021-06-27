package ProjetoFacul;

import java.util.Random;

public class CaminhaoFurgao extends Veiculo{
    private static final long serialVersionUID = 1L;
    private double carga;

    public CaminhaoFurgao(String marca, String modelo, int ano, int km) {
        super(marca, modelo, ano, km);
    }

    public double getCarga() {
        return carga;
    }

    public CaminhaoFurgao (String marca, String modelo, int ano, int km, double carga) {
        super(marca, modelo, ano, km);
        this.carga = carga;
        this.carga = getCarga();
        this.segmento = "Tranporte de cargas";

    }

    public String disponivel() {
        Random disp = new Random();
        if (disp.nextBoolean() == true) {return "Sim";}
        else return "Não";}
    public String toString() {
        return super.toString() + "Capacidade: "+ carga +" Toneladas";
    }


}