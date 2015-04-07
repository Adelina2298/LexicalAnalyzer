import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/**
 * Created by Adelina on 04.04.2015.
 */
public class Main {

    public static void main(String[] args){

        AnalizorLexical analizorLexical = new AnalizorLexical();

        System.out.print("Numele fisierului de intrare: ");
        Scanner sc = new Scanner(System.in);
        String fileName = sc.next();
        System.out.print("Numele fisierului de intrare: ");
        Scanner sc2 = new Scanner(System.in);
        String fileNameOut = sc2.next();
        File fout = new File(fileNameOut);
        FileOutputStream fos = null;
        BufferedWriter bw = null;
        try{
            fos = new FileOutputStream(fout);
            bw = new BufferedWriter(new OutputStreamWriter(fos));
            while(true){
                Token token = analizorLexical.getToken(fileName);

                //TODO: am parcurs fisierul cu succes
                if(token == null) break;

                //TODO: am primit un token valid, deci il scriu in fisier
                //TODO: cuvant cheie
                if(token.getTip() == 51){
                    bw.write(analizorLexical.getValueForTip(token.getTip()) + " - " + analizorLexical.getKeyForValoare(token.getValoare()));
                    bw.newLine();
                }

                //TODO: altceva mai putin eroare
                else if(token.getTip() != 50){
                    bw.write(analizorLexical.getValueForTip(token.getTip()) + " - " + analizorLexical.getValueForValoare(token.getValoare()));
                    bw.newLine();
                }

                //TODO: am primit un token de eroare, deci programul s-a terminat
                else if(token.getTip() == 50){
                    bw.write(analizorLexical.getValueForTip(token.getTip()) + " - pozitita " + token.getValoare());
                    break;
                }
            }
        }
        catch(Exception e) {e.printStackTrace();}
        finally {
            try{
                bw.close();
            }
            catch(Exception e){e.printStackTrace();}
        }
    }
}
