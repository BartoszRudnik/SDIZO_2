public class wierzcholekKolejka {

    private int waga;
    private int wierzcholek;
    private int koniec;

    public wierzcholekKolejka(){

        waga = 0;
        wierzcholek = 0;
        koniec = 0;

    }

    public wierzcholekKolejka(int waga, int wierzcholek){

        this.waga = waga;
        this.wierzcholek = wierzcholek;

    }

    public wierzcholekKolejka(int waga, int wierzcholek, int koniec){

        this.waga = waga;
        this.wierzcholek = wierzcholek;
        this.koniec = koniec;

    }

    public void setWaga(int waga){
        this.waga = waga;
    }

    public void setWierzcholek(int wierzcholek){
        this.wierzcholek = wierzcholek;
    }

    public void setKoniec(int koniec){
        this.koniec = koniec;
    }

    public int getWaga(){
        return waga;
    }

    public int getWierzcholek(){
        return wierzcholek;
    }

    public int getKoniec(){
        return koniec;
    }

}
