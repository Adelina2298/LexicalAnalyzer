/**
 * Created by Adelina on 04.04.2015.
 */
public class Token {

    private int tip;

    private int valoare;

    Token(int tip, int valoare){
        this.tip = tip;
        this.valoare = valoare;
    }

    public int getTip() {
        return tip;
    }

    public void setTip(int tip) {
        this.tip = tip;
    }

    public int getValoare() {
        return valoare;
    }

    public void setValoare(int valoare) {
        this.valoare = valoare;
    }
}

