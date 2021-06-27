package ProjetoFacul;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;


public class Frota {
    private ArrayList<Veiculo> veiculos;

    public Frota() {
        this.veiculos = new ArrayList<Veiculo>();
    }
    public String[] leValores (String [] dadosIn){
        String [] dadosOut = new String [dadosIn.length];

        for (int i = 0; i < dadosIn.length; i++)
            dadosOut[i] = JOptionPane.showInputDialog  ("Entre com " + dadosIn[i]+ ": ");

        return dadosOut;
    }

    public Carro leCarro (){

        String [] valores = new String [5];
        String [] nomeVal = {"Marca", "Modelo", "Ano", "Km", "Portamalas"};
        valores = leValores (nomeVal);

        int ano = this.retornaInteiro(valores[2]);
        int km = this.retornaInteiro(valores[3]);
        int portamalas = this.retornaInteiro(valores[4]);

        Carro carro = new Carro (valores[0],valores[1],ano,km, portamalas);
        return carro;
    }

    public Van leVan (){

        String [] valores = new String [5];
        String [] nomeVal = {"Marca", "Modelo", "Ano", "Km", "Passageiros"};
        valores = leValores (nomeVal);

        int ano = this.retornaInteiro(valores[2]);
        int km = this.retornaInteiro(valores[3]);
        int p = this.retornaInteiro(valores[4]);

        Van van = new Van (valores[0],valores[1],ano,km,p);
        return van;
    }
    public CaminhaoFurgao leCaminhaoFurgao (){

        String [] valores = new String [5];
        String [] nomeVal = {"Marca", "Modelo", "Ano", "Km", "Carga"};
        valores = leValores (nomeVal);

        int ano = this.retornaInteiro(valores[2]);
        int km = this.retornaInteiro(valores[3]);
        double carga = this.retornaInteiro(valores[4]);

        CaminhaoFurgao caminhao = new CaminhaoFurgao (valores[0],valores[1],ano,km, carga);
        return caminhao;
    }


    private boolean intValido(String s) {
        try {
            Integer.parseInt(s); // Método estático, que tenta tranformar uma string em inteiro
            return true;
        } catch (NumberFormatException e) { // Não conseguiu tranformar em inteiro e gera erro
            return false;
        }
    }
    public int retornaInteiro(String entrada) { // retorna um valor inteiro
        int numInt;

        //Enquanto não for possível converter o valor de entrada para inteiro, permanece no loop
        while (!this.intValido(entrada)) {
            entrada = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um número inteiro.");
        }
        return Integer.parseInt(entrada);
    }

    public void frotaFileSave (ArrayList<Veiculo> veiculos){
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream
                    (new FileOutputStream("c:\\temp\\frota.dados"));
            for (int i=0; i < veiculos.size(); i++)
                outputStream.writeObject(veiculos.get(i));
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"Impossível criar arquivo!");
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {  //Close the ObjectOutputStream
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @SuppressWarnings("finally")
    public ArrayList<Veiculo> frotaFileOpen (){
        ArrayList<Veiculo> veiculosTemp = new ArrayList<Veiculo>();

        ObjectInputStream inputStream = null;

        try {
            inputStream = new ObjectInputStream
                    (new FileInputStream("c:\\temp\\frota.dados"));
            Object obj = null;
            while ((obj = inputStream.readObject()) != null) {
                if (obj instanceof Veiculo) {
                    veiculosTemp.add((Veiculo) obj);
                }
            }
        } catch (EOFException ex) { // when EOF is reached
            System.out.println("Fim de arquivo.");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"Arquivo com veículos NÃO existe!");
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {  //Close the ObjectInputStream
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
            return veiculosTemp;
        }
    }

    public void menuFrota (){

        String menu = "";
        String entrada;
        int    opc1, opc2;

        do {
            menu = "Controle Frota\n" +
                    "Opções:\n" +
                    "1. Cadastrar Veículos\n" +
                    "2. Exibir Veículos\n" +
                    "3. Apagar Veículos\n" +
                    "4. Salvar Veículos\n" +
                    "5. Recuperar Veículos\n" +
                    "9. Sair";
            entrada = JOptionPane.showInputDialog (menu + "\n\n");
            opc1 = this.retornaInteiro(entrada);

            switch (opc1) {
                case 1:// Entrar dados
                    menu = "Entrada de Segmento de Veículos\n" +
                            "Opções:\n" +
                            "1. Passeio\n" +
                            "2. Transporte\n" +
                            "3. Carga\n";

                    entrada = JOptionPane.showInputDialog (menu + "\n\n");
                    opc2 = this.retornaInteiro(entrada);

                    switch (opc2){
                        case 1: veiculos.add((Veiculo)leCarro());
                            break;
                        case 2: veiculos.add((Veiculo)leVan());
                            break;
                        case 3:
                            veiculos.add((Veiculo)leCaminhaoFurgao());
                            break;
                        default:
                            JOptionPane.showMessageDialog(null,"Segmento de veículo para entrada NÃO escolhido!");
                    }

                    break;
                case 2: // Exibir dados
                    if (veiculos.size() == 0) {
                        JOptionPane.showMessageDialog(null,"Entre com o segmento do veiculo primeiramente");
                        break;
                    }
                    String dados = "";
                    for (int i=0; i < veiculos.size(); i++)	{
                        dados += veiculos.get(i).toString() + "\n---------------\n";
                    }
                    JOptionPane.showMessageDialog(null,dados);
                    break;
                case 3: // Limpar Dados
                    if (veiculos.size() == 0) {
                        JOptionPane.showMessageDialog(null,"Entre com o segmento do veiculo primeiramente");
                        break;
                    }
                    veiculos.clear();
                    JOptionPane.showMessageDialog(null,"Dados LIMPOS com sucesso!");
                    break;
                case 4: // Grava Dados
                    if (veiculos.size() == 0) {
                        JOptionPane.showMessageDialog(null,"Entre com o segmento do veiculo primeiramente");
                        break;
                    }
                    frotaFileSave(veiculos);
                    JOptionPane.showMessageDialog(null,"Dados SALVOS com sucesso!");
                    break;
                case 5: // Recupera Dados
                    veiculos = frotaFileOpen();
                    if (veiculos.size() == 0) {
                        JOptionPane.showMessageDialog(null,"Sem dados para apresentar.");
                        break;
                    }
                    JOptionPane.showMessageDialog(null,"Dados RECUPERADOS com sucesso!");
                    break;
                case 9:
                    JOptionPane.showMessageDialog(null,"Fim do aplicativo FROTA");
                    break;
            }
        } while (opc1 != 9);
    }


    public static void main (String [] args){

        Frota frota = new Frota ();
        frota.menuFrota();

    }

}

