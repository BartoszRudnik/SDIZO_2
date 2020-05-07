public class ZbiorRozlaczny {

    private ZrWezel[] zrWezel;

    public ZbiorRozlaczny(int rozmiar){

        zrWezel = new ZrWezel[rozmiar];
        wyczysc(zrWezel);

    }

    public ZbiorRozlaczny(){

    }

    private void wyczysc(ZrWezel[] zr){

        for(int i = 0; i < zr.length; i++){
            zr[i] = new ZrWezel();
        }

    }

    public int FindSet(int v){

        if(zrWezel[v].getRodzic() != v){
            zrWezel[v].setRodzic(FindSet(zrWezel[v].getRodzic()));
        }

        return zrWezel[v].getRodzic();

    }

    public void MakeSet(int v){

        zrWezel[v].setRodzic(v);
        zrWezel[v].setWaga(0);

    }

    public void UnionSet(Wierzcholek w){

        int p1 = FindSet(w.getPoczatek());
        int p2 = FindSet(w.getKoniec());

        if(p1 != p2){

            if(zrWezel[p1].getWaga() > zrWezel[p2].getWaga())
                zrWezel[p2].setRodzic(p1);

            else{

                zrWezel[p1].setRodzic(p2);

                if(zrWezel[p1].getWaga() == zrWezel[p2].getWaga()){

                    int waga = zrWezel[p2].getWaga();
                    waga++;

                    zrWezel[p2].setWaga(waga);

                }

            }

        }

    }

}
