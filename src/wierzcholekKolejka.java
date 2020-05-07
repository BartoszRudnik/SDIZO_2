public class wierzcholekKolejka {

    private int waga;
    private int wierzcholek;

    public wierzcholekKolejka(){

    }

    public wierzcholekKolejka(int waga, int wierzcholek){

        this.waga = waga;
        this.wierzcholek = wierzcholek;

    }

    public void setWaga(int waga){
        this.waga = waga;
    }

    public void setWierzcholek(int wierzcholek){
        this.wierzcholek = wierzcholek;
    }

    public int getWaga(){
        return waga;
    }

    public int getWierzcholek(){
        return wierzcholek;
    }

}
