public class BellmanFord {

    private int v;
    private int e;

    private int pozycja = 0;

    private wierzcholekKolejka[] wierzcholek;
    private wierzcholekKolejka[] wynik;

    public BellmanFord(int v, int e){

        this.v = v;
        this.e = e;

        wierzcholek = new wierzcholekKolejka[e];
        wynik = new wierzcholekKolejka[v];

        ustaw();

    }

    private void ustaw(){

        for(int i = 0; i < v; i++){
            wynik[i] = new wierzcholekKolejka(Integer.MAX_VALUE,i);
        }

        for(int i = 0; i < e; i++){
            wierzcholek[i] = new wierzcholekKolejka();
        }

    }

    public void dodajKrawedz(int waga, int poczatek, int koniec){

        wierzcholek[pozycja].setWaga(waga);
        wierzcholek[pozycja].setWierzcholek(poczatek);
        wierzcholek[pozycja].setKoniec(koniec);
        pozycja++;

    }

    public Boolean AlgorytmBF(){

        wynik[0].setWaga(0);

        for(int i = 1; i < v; i++){

            for(int j = 0; j < e; j++){

                if(wynik[wierzcholek[j].getWierzcholek()].getWaga() + wierzcholek[j].getWaga() < wynik[wierzcholek[j].getKoniec()].getWaga()){
                    wynik[wierzcholek[j].getKoniec()].setWaga(wynik[wierzcholek[j].getWierzcholek()].getWaga() + wierzcholek[j].getWaga());
                }

            }

        }

        for(int i = 0; i < e; i++){

            if(wynik[wierzcholek[i].getWierzcholek()].getWaga() + wierzcholek[i].getWaga() < wynik[wierzcholek[i].getKoniec()].getWaga()){
                return false;
            }

        }

        return true;

    }

    public void wypisz(){

        for(int i = 0; i < v; i++){
            System.out.println(i + " " + wynik[i].getWaga());
        }

    }


}
