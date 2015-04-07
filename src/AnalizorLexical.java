import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

/**
 * Created by Adelina on 04.04.2015.
 */
public class AnalizorLexical {

    final private String[] LIT = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    final private String[] CIF = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    final private String[] NOTSTAR = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "+", "-", "=", "/", "\\", "<", ">", ".", "(", ")", "[", "]", "{", "}", "|", "^", "%", "!", "?", ":", ";", "~", "`", "@", "#", "$", "&", "'", "\"", " ", Character.toString('\n'), Character.toString('\r'), Character.toString('\0')};
    final private String[] NOTSLASH = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "+", "-", "=", "*", "\\", "<", ">", ".", "(", ")", "[", "]", "{", "}", "|", "^", "%", "!", "?", ":", ";", "~", "`", "@", "#", "$", "&", "'", "\"", " ", Character.toString('\n'), Character.toString('\r'), Character.toString('\0')};
    final private String[] NOTSLASHANDSTAR = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "+", "-", "=", "\\", "<", ">", ".", "(", ")", "[", "]", "{", "}", "|", "^", "%", "!", "?", ":", ";", "~", "`", "@", "#", "$", "&", "'", "\"", " ", Character.toString('\n'), Character.toString('\r'), Character.toString('\0')};
    final private String[] NOTAPOSTROPHE = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "+", "-", "=", "*", "/", "\\", "<", ">", ".", "(", ")", "[", "]", "{", "}", "|", "^", "%", "!", "?", ":", ";", "~", "`", "@", "#", "$", "&", "\"", " "};
    final private String[] NOTQUOTATION = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "+", "-", "=", "*", "/", "\\", "<", ">", ".", "(", ")", "[", "]", "{", "}", "|", "^", "%", "!", "?", ":", ";", "~", "`", "@", "#", "$", "&", "'", " "};
    final private String[] NOTENTER = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "+", "-", "=", "*", "/", "\\", "<", ">", ".", "(", ")", "[", "]", "{", "}", "|", "^", "%", "!", "?", ":", ";", "~", "`", "@", "#", "$", "&", "'", "\"", " "};

    private String[] tokens = {"identificator", "literal intreg", "literal flotant", "operator.", "literal caracter", "literal string", "operator+", "operator++", "operator+=", "operator-", "operator--", "operator-=", "operator->", "operator*", "operator*=", "operator/", "operator/=", "operator%", "operator%=", "operator&", "operator&&", "operator&=", "operator^", "operator^=", "operator|", "operator||", "operator|=", "operator<", "operator<=", "operator<<", "operator<<=", "operator>", "operator>=", "operator>>", "operator>>=", "operator=", "operator==", "operator:", "operator::", "operator!", "operator!=", "operator?", "operator~", "operator(", "operator)", "operator[", "operator]", "separator", "comentariu", "spatiu", "eroare", "cuvant cheie"};
    private String[] keys = {"int", "char", "float", "string", "if", "else","for", "while", "do", "return", "using", "include", "cin", "cout", "main", "case", "switch", "private", "public", "protected", "argc", "argv"};
    private String[] values = new String[10000];

    private String q0 = "0";
    private String[] F = {"1", "2", "3", "4", "7", "8", "9", "10", "13", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "62", "64", "65", "59", "63"};
    private String[][] delta;

    private int positionInFile = 0;
    private int size = 0;
    private int valuesSize = 0;

    AnalizorLexical(){

        delta = new String[10000][3];

        //identificator
        delta[size][0] = "0";
        delta[size][1] = "_";
        delta[size++][2] = "1";
        for(int i = 0; i < LIT.length; i++){
            delta[size][0] = "0";
            delta[size][1] =  LIT[i];
            delta[size++][2] = "1";
        }
        for(int i = 0; i < LIT.length; i++){
            delta[size][0] = "1";
            delta[size][1] =  LIT[i];
            delta[size++][2] = "1";
        }
        for(int i = 0; i < CIF.length; i++){
            delta[size][0] = "1";
            delta[size][1] =  CIF[i];
            delta[size++][2] = "1";
        }
        delta[size][0] = "1";
        delta[size][1] =  "_";
        delta[size++][2] = "1";

        //literal intreg
        for(int i = 0; i < CIF.length; i++){
            delta[size][0] = "0";
            delta[size][1] =  CIF[i];
            delta[size++][2] = "2";
        }
        for(int i = 0; i < CIF.length; i++){
            delta[size][0] = "2";
            delta[size][1] =  CIF[i];
            delta[size++][2] = "2";
        }

        //literal flotant
        delta[size][0] = "2";
        delta[size][1] =  ".";
        delta[size++][2] = "3";
        delta[size][0] = "2";
        delta[size][1] =  "e";
        delta[size++][2] = "5";
        for(int i = 0; i < CIF.length; i++){
            delta[size][0] = "3";
            delta[size][1] =  CIF[i];
            delta[size++][2] = "4";
        }
        delta[size][0] = "3";
        delta[size][1] =  "e";
        delta[size++][2] = "5";
        for(int i = 0; i < CIF.length; i++){
            delta[size][0] = "4";
            delta[size][1] =  CIF[i];
            delta[size++][2] = "4";
        }
        delta[size][0] = "4";
        delta[size][1] =  "e";
        delta[size++][2] = "5";
        delta[size][0] = "5";
        delta[size][1] =  "+";
        delta[size++][2] = "6";
        delta[size][0] = "5";
        delta[size][1] =  "-";
        delta[size++][2] = "6";
        for(int i = 0; i < CIF.length; i++){
            delta[size][0] = "5";
            delta[size][1] =  CIF[i];
            delta[size++][2] = "7";
        }
        for(int i = 0; i < CIF.length; i++){
            delta[size][0] = "6";
            delta[size][1] =  CIF[i];
            delta[size++][2] = "8";
        }
        for(int i = 0; i < CIF.length; i++){
            delta[size][0] = "7";
            delta[size][1] =  CIF[i];
            delta[size++][2] = "7";
        }
        for(int i = 0; i < CIF.length; i++){
            delta[size][0] = "8";
            delta[size][1] =  CIF[i];
            delta[size++][2] = "8";
        }
        delta[size][0] = "0";
        delta[size][1] =  ".";
        delta[size++][2] = "9";
        for(int i = 0; i < CIF.length; i++){
            delta[size][0] = "9";
            delta[size][1] =  CIF[i];
            delta[size++][2] = "10";
        }
        delta[size][0] = "10";
        delta[size][1] =  "e";
        delta[size++][2] = "5";
        for(int i = 0; i < CIF.length; i++){
            delta[size][0] = "10";
            delta[size][1] =  CIF[i];
            delta[size++][2] = "10";
        }

        //literal caracter
        delta[size][0] = "0";
        delta[size][1] =  "'";
        delta[size++][2] = "11";
        for(int i = 0; i < NOTAPOSTROPHE.length; i++){
            delta[size][0] = "11";
            delta[size][1] =  NOTAPOSTROPHE[i];
            delta[size++][2] = "12";
        }
        delta[size][0] = "11";
        delta[size][1] =  "\\";
        delta[size++][2] = "14";
        delta[size][0] = "12";
        delta[size][1] =  "'";
        delta[size++][2] = "13";
        delta[size][0] = "14";
        delta[size][1] =  "n";
        delta[size++][2] = "12";
        delta[size][0] = "14";
        delta[size][1] =  "0";
        delta[size++][2] = "12";
        delta[size][0] = "14";
        delta[size][1] =  "r";
        delta[size++][2] = "12";


        //literal string
        delta[size][0] = "0";
        delta[size][1] =  "\"";
        delta[size++][2] = "15";
        for(int i = 0; i < NOTQUOTATION.length; i++){
            delta[size][0] = "15";
            delta[size][1] =  NOTQUOTATION[i];
            delta[size++][2] = "15";
        }
        delta[size][0] = "15";
        delta[size][1] =  "\"";
        delta[size++][2] = "16";

        //operator +
        delta[size][0] = "0";
        delta[size][1] =  "+";
        delta[size++][2] = "17";

        //operator ++
        delta[size][0] = "17";
        delta[size][1] =  "+";
        delta[size++][2] = "18";

        //operator +=
        delta[size][0] = "17";
        delta[size][1] =  "=";
        delta[size++][2] = "19";

        //operator -
        delta[size][0] = "0";
        delta[size][1] =  "-";
        delta[size++][2] = "20";

        //operator --
        delta[size][0] = "20";
        delta[size][1] =  "-";
        delta[size++][2] = "21";

        //operator -=
        delta[size][0] = "20";
        delta[size][1] =  "=";
        delta[size++][2] = "22";

        //operator ->
        delta[size][0] = "20";
        delta[size][1] =  "-";
        delta[size++][2] = "23";

        //operator *
        delta[size][0] = "0";
        delta[size][1] =  "*";
        delta[size++][2] = "24";

        //operator *=
        delta[size][0] = "24";
        delta[size][1] =  "=";
        delta[size++][2] = "25";

        //operator %
        delta[size][0] = "0";
        delta[size][1] =  "%";
        delta[size++][2] = "28";

        //operator %=
        delta[size][0] = "28";
        delta[size][1] =  "=";
        delta[size++][2] = "29";

        //operator &
        delta[size][0] = "0";
        delta[size][1] =  "&";
        delta[size++][2] = "30";

        //operator &&
        delta[size][0] = "30";
        delta[size][1] =  "&";
        delta[size++][2] = "31";

        //operator &=
        delta[size][0] = "30";
        delta[size][1] =  "=";
        delta[size++][2] = "32";

        //operator ^
        delta[size][0] = "0";
        delta[size][1] =  "^";
        delta[size++][2] = "33";

        //operator ^=
        delta[size][0] = "33";
        delta[size][1] =  "=";
        delta[size++][2] = "34";

        //operator |
        delta[size][0] = "0";
        delta[size][1] =  "|";
        delta[size++][2] = "35";

        //operator ||
        delta[size][0] = "35";
        delta[size][1] =  "|";
        delta[size++][2] = "36";

        //operator |=
        delta[size][0] = "35";
        delta[size][1] =  "=";
        delta[size++][2] = "37";

        //operator <
        delta[size][0] = "0";
        delta[size][1] =  "<";
        delta[size++][2] = "38";

        //operator <=
        delta[size][0] = "38";
        delta[size][1] =  "=";
        delta[size++][2] = "39";

        //operator <<
        delta[size][0] = "38";
        delta[size][1] =  "<";
        delta[size++][2] = "40";

        //operator <<=
        delta[size][0] = "40";
        delta[size][1] =  "=";
        delta[size++][2] = "41";

        //operator >
        delta[size][0] = "0";
        delta[size][1] =  ">";
        delta[size++][2] = "42";

        //operator >=
        delta[size][0] = "42";
        delta[size][1] =  "=";
        delta[size++][2] = "43";

        //operator >>
        delta[size][0] = "42";
        delta[size][1] =  ">";
        delta[size++][2] = "44";

        //operator >>=
        delta[size][0] = "44";
        delta[size][1] =  "=";
        delta[size++][2] = "45";

        //operator =
        delta[size][0] = "0";
        delta[size][1] =  "=";
        delta[size++][2] = "46";

        //operator ==
        delta[size][0] = "46";
        delta[size][1] =  "=";
        delta[size++][2] = "47";

        //operator :
        delta[size][0] = "0";
        delta[size][1] =  ":";
        delta[size++][2] = "48";

        //oeprator ::
        delta[size][0] = "48";
        delta[size][1] =  ":";
        delta[size++][2] = "49";

        //operator !
        delta[size][0] = "0";
        delta[size][1] =  "!";
        delta[size++][2] = "50";

        //operator !=
        delta[size][0] = "50";
        delta[size][1] =  "=";
        delta[size++][2] = "51";

        //operator ?
        delta[size][0] = "0";
        delta[size][1] =  "?";
        delta[size++][2] = "52";

        //operator ~
        delta[size][0] = "0";
        delta[size][1] =  "~";
        delta[size++][2] = "53";

        //oeprator (
        delta[size][0] = "0";
        delta[size][1] =  "(";
        delta[size++][2] = "54";

        //operator )
        delta[size][0] = "0";
        delta[size][1] =  ")";
        delta[size++][2] = "55";

        //operator [
        delta[size][0] = "0";
        delta[size][1] =  "[";
        delta[size++][2] = "56";

        //operator ]
        delta[size][0] = "0";
        delta[size][1] =  "]";
        delta[size++][2] = "57";

        //operator ;
        delta[size][0] = "0";
        delta[size][1] =  ";";
        delta[size++][2] = "58";

        //operator ,
        delta[size][0] = "0";
        delta[size][1] =  ",";
        delta[size++][2] = "58";

        //operator {
        delta[size][0] = "0";
        delta[size][1] =  "{";
        delta[size++][2] = "58";

        //operator }
        delta[size][0] = "0";
        delta[size][1] =  "}";
        delta[size++][2] = "58";

        //operator /
        delta[size][0] = "0";
        delta[size][1] =  "/";
        delta[size++][2] = "59";

        //comentariu /*
        delta[size][0] = "59";
        delta[size][1] =  "*";
        delta[size++][2] = "60";
        for(int i = 0; i < NOTSTAR.length; i++){
            delta[size][0] = "60";
            delta[size][1] =  NOTSTAR[i];
            delta[size++][2] = "60";
        }
        delta[size][0] = "60";
        delta[size][1] =  "*";
        delta[size++][2] = "61";
        delta[size][0] = "61";
        delta[size][1] =  "*";
        delta[size++][2] = "61";
        for(int i = 0; i < NOTSLASHANDSTAR.length; i++){
            delta[size][0] = "61";
            delta[size][1] =  NOTSLASHANDSTAR[i];
            delta[size++][2] = "60";
        }
        delta[size][0] = "61";
        delta[size][1] =  "/";
        delta[size++][2] = "62";

        //comentariu //
        delta[size][0] = "59";
        delta[size][1] =  "/";
        delta[size++][2] = "63";
        for(int i = 0; i < NOTENTER.length; i++){
            delta[size][0] = "63";
            delta[size][1] =  NOTENTER[i];
            delta[size++][2] = "63";
        }

        //operator /=
        delta[size][0] = "59";
        delta[size][1] =  "=";
        delta[size++][2] = "65";

        //spatiu
        delta[size][0] = "0";
        delta[size][1] =  " ";
        delta[size++][2] = "64";
        delta[size][0] = "0";
        delta[size][1] =  Character.toString('\n');
        delta[size++][2] = "64";
        delta[size][0] = "0";
        delta[size][1] =  Character.toString('\r');
        delta[size++][2] = "64";
        delta[size][0] = "0";
        delta[size][1] =  Character.toString('\0');
        delta[size++][2] = "64";


    }

    private String verification(String state, String character){
       for(int i = 0; i < size; i++){
           if(delta[i][0].equals(state) && delta[i][1].equals(character)){
               return delta[i][2];
           }
       }
       return null;
    }

    private int getTypeForToken(String state){
       switch (Integer.parseInt(state)){
           case 1: return 0;
           case 2: return 1;
           case 3: return 2;
           case 4: return 2;
           case 7: return 2;
           case 8: return 2;
           case 10: return 2;
           case 9: return 3;
           case 13: return 4;
           case 14: return 4;
           case 16: return 5;
           case 17: return 6;
           case 18: return 7;
           case 19: return 8;
           case 20: return 9;
           case 21: return 10;
           case 22: return 11;
           case 23: return 12;
           case 24: return 13;
           case 25: return 14;
           case 28: return 17;
           case 29: return 18;
           case 30: return 19;
           case 31: return 20;
           case 32: return 21;
           case 33: return 22;
           case 34: return 23;
           case 35: return 24;
           case 36: return 25;
           case 37: return 26;
           case 38: return 27;
           case 39: return 28;
           case 40: return 29;
           case 41: return 30;
           case 42: return 31;
           case 43: return 32;
           case 44: return 33;
           case 45: return 34;
           case 46: return 35;
           case 47: return 36;
           case 48: return 37;
           case 49: return 38;
           case 50: return 39;
           case 51: return 40;
           case 52: return 41;
           case 53: return 42;
           case 54: return 43;
           case 55: return 44;
           case 56: return 45;
           case 57: return 46;
           case 58: return 47;
           case 62: return 48;
           case 64: return 49;
           case 59: return 15;
           case 65: return 16;
           case 63: return 48;

           default: return -1;
       }

    }

    private boolean seeIfFinalState(String state){
        for(int i = 0; i < F.length; i++)
            if(F[i].equals(state))
                return true;
        return false;
    }

    private String buildStringFromArray(String[] array, int size){
        StringBuilder builder = new StringBuilder();

        for(int j = 0; j < size; j++) {
            builder.append(array[j]);
        }

        return builder.toString();
    }

    public Token getToken(String fileName) throws Exception{
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        //TODO: am parcurs fisierul fara sa am erori
        if(positionInFile == -1) return null;
        reader.skip(positionInFile);

        int val;
        int valueSize = 0;
        int statesSize = 1;
        String[] states = new String[1000];
        String[] value = new String[1000];
        states[0] = q0;

        int valoare = reader.read();
        //TODO: fisierul e gol
        if(valoare == -1) return null;
        String character = Character.toString((char) valoare);
        states[statesSize] = verification(q0, character);
        if(states[statesSize] != null){
            value[valueSize] = character;
            valueSize++;
        }

        statesSize++;
        while((val =  reader.read()) != -1 && states[statesSize - 1] != null){
            character = Character.toString((char) val);
            states[statesSize] = verification(states[statesSize - 1], character);
            if(states[statesSize] != null){
                value[valueSize] = character;
                valueSize++;
            }
            statesSize++;
        }

        //TODO: m-am blocat intr-o stare si am doua posibilitati: sunt in stare finala, caz in care afisez sau nu sunt in stare finala, caz in care am eroare
        if(states[statesSize - 1] == null){

            if(seeIfFinalState(states[statesSize - 2])){

                positionInFile += statesSize - 2;

                String valueForToken = buildStringFromArray(value, valueSize);

                //TODO: daca am gasit un identificator, iar acesta este un cuvant cheie, returnez direct token-ul corespunzator cuvantului cheie
                if(states[statesSize - 2].equals("1") && Arrays.asList(keys).contains(valueForToken))
                    return new Token(51, Arrays.asList(keys).indexOf(valueForToken));

                //TODO: daca am gasit un token al carui valoare se regaseste in tabelul de stringuri, returnez direct token-ul corespunzator
                else if(Arrays.asList(values).contains(valueForToken))
                    return new Token(getTypeForToken(states[statesSize - 2]), Arrays.asList(values).indexOf(valueForToken));

                else if(states[statesSize - 2].equals("64") || states[statesSize - 2].equals("62") || states[statesSize - 2].equals("63"))
                     return getToken(fileName);
                //TODO: altfel adaug valoarea noua in tabelul de stringuri
                values[valuesSize] = valueForToken;
                valuesSize++;
                return new Token(getTypeForToken(states[statesSize - 2]), valuesSize - 1);

                }

            // TODO: eroare
            return new Token(50, positionInFile + statesSize);

        }



        // TODO: s-a terminat fisierul, caz in care am doua posibilitati: sunt in stare finala, caz in care afisez sau nu sunt in stare finala, caz in care am eroare
        if(seeIfFinalState(states[statesSize - 1])){

            positionInFile = -1;

            String valueForToken = buildStringFromArray(value, valueSize);

            if(states[statesSize - 1].equals("1") && Arrays.asList(keys).contains(valueForToken))
                return new Token(51, Arrays.asList(keys).indexOf(valueForToken));

            else if(Arrays.asList(values).contains(valueForToken))
                return new Token(getTypeForToken(states[statesSize - 1]), Arrays.asList(values).indexOf(valueForToken));

            else if(states[statesSize - 1].equals("64") || states[statesSize - 1].equals("62") || states[statesSize - 1].equals("63"))
                return null;

            values[valuesSize] = valueForToken;
            valuesSize++;
            return new Token(getTypeForToken(states[statesSize - 1]), valuesSize - 1);
        }

        // TODO: eroare
        return new Token(50, positionInFile + statesSize - 1);

    }

    public String getValueForTip(int tip){
        return tokens[tip];
    }

    public String getKeyForValoare(int tip){
        return keys[tip];
    }

    public String getValueForValoare(int valoare){
        return values[valoare];
    }

}
