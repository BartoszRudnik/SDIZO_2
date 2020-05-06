public class Wierzcholek {

    private int waga;
    private int poczatek;
    private int koniec;

    public Wierzcholek(){

        waga = 0;
        poczatek = 0;
        koniec = 0;

    }

    public Wierzcholek(int waga, int poczatek, int koniec){

        this.waga = waga;
        this.poczatek = poczatek;
        this.koniec = koniec;

    }

    public int getWaga(){
        return waga;
    }

    public int getPoczatek(){
        return poczatek;
    }

    public int getKoniec(){
        return koniec;
    }

    public void setWaga(int waga){
        this.waga = waga;
    }

    public void setPoczatek(int poczatek){
        this.poczatek = poczatek;
    }

    public void setKoniec(int koniec){
        this.koniec = koniec;
    }

}
