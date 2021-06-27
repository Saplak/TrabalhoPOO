package ProjetoFacul;
import java.util.Random;

public class Carro extends Veiculo {

    private static final long serialVersionUID = 1L;
    private final int portamalas;

    public Carro(String marca, String modelo, int ano, int km, int portamalas) {
        super(marca, modelo, ano, km);
        this.segmento = "Passeio";
        this.portamalas = portamalas;

    }



    public String disponivel() {
        Random disp = new Random();
        if (disp.nextBoolean() == true) {return "Sim";}
        else return "Não";}

    @Override
    public String toString() {
        return super.toString() + "Portamalas: " + portamalas + " litros";

    }
}
