public class WezelLista {

    private int wartosc;
    private WezelLista nast;
    private WezelLista poprz;

    WezelLista(int wartosc) {
        this.wartosc = wartosc;
        nast = null;
        poprz = null;
    }

    public void setWartosc(int wartosc) {
        this.wartosc = wartosc;
    }

    public void setNast(WezelLista e) {
        nast = e;
    }

    public void setPoprz(WezelLista e) {
        poprz = e;
    }

    public int getWartosc() {
        return wartosc;
    }

    public WezelLista getNast() {
        return nast;
    }

    public WezelLista getPoprz() {
        return poprz;
    }

}
