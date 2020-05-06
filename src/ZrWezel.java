public class ZrWezel {

    private int rodzic;
    private int waga;

    public ZrWezel(int rodzic, int waga){

        this.rodzic = rodzic;
        this.waga = waga;

    }

    public void setRodzic(int rodzic){
        this.rodzic = rodzic;
    }

    public void setWaga(int waga){
        this.waga = waga;
    }

    public int getRodzic(){
        return rodzic;
    }

    public int getWaga(){
        return waga;
    }

}
