
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DesafioValidaLinha {

    public static void main (String[] args){
        FileReader arquivo;
        BufferedReader leArquivo;
        FileWriter arq;
        PrintWriter escreve;
        ArrayList <String> entrada = new ArrayList<String>();

        try{

            arquivo = new FileReader(args[0]);
            leArquivo = new BufferedReader(arquivo);          
            escreve = new PrintWriter("prog-check.txt");

            String linha = leArquivo.readLine().trim();

            while (linha != null) {

                boolean verifica = true;

                if(!linha.equals("")){

                    char [] caracteres = linha.toCharArray();
                    
                    for(char c : caracteres){

                        if(c == '[' || c == '(' || c == '{'){
                            entrada.add(Character.toString(c));
                        }
                        else if(entrada.isEmpty()){ //Se sobrar apenas "},],)" e evita Exception
                            verifica = false;
                            break;
                        }
                        else if(entrada.get(entrada.size()-1).equals("[") && c ==']'){
                            entrada.remove(entrada.size()-1);
                        }
                        else if (entrada.get(entrada.size()-1).equals("(") && c ==')'){
                            entrada.remove(entrada.size()-1);
                        }
                        else if (entrada.get(entrada.size()-1).equals("{") && c =='}'){
                            entrada.remove(entrada.size()-1);
                        }
                        else{ // caso tenha um elemento fora do alfabeto
                            verifica = false;
                            break;
                        }
                    }                  
                }
                else{ //Se a linha for nulo

                    verifica = false;
                }

                if(verifica && entrada.size() == 0){
                    escreve.println( linha + "- Válido  ");
                }
                else{
                    escreve.println( linha + "-Inválido  ");
                }
                
                entrada.clear();

                linha = leArquivo.readLine();

                if(linha == null)
                {
                    break;
                }
                linha = linha.trim();
            }
            escreve.close();

        } catch(Exception e){
            System.err.printf(e.getMessage());
        }
    }
}