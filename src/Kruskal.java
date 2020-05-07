public class Kruskal {

    private int v;
    private int e;
    private int index = 0;

    private Wierzcholek[] wierzcholek;
    private Wierzcholek[] mst;
    private ZbiorRozlaczny zr;
    private MergeSort sortowanie = new MergeSort();

    private void wyczysc(Wierzcholek[] w){

        for(int i = 0; i < w.length; i++){
            w[i] = new Wierzcholek();
        }

    }

    public Kruskal(int v, int e){

        this.v = v;
        this.e = e;

        wierzcholek = new Wierzcholek[e];

        wyczysc(wierzcholek);

    }

    public void dodajWierzcholki(int pozycja, int waga, int poczatek, int koniec){

        wierzcholek[pozycja].setWaga(waga);
        wierzcholek[pozycja].setPoczatek(poczatek);
        wierzcholek[pozycja].setKoniec(koniec);

    }

    public void AlgorytmKruskala(){

        mst = new Wierzcholek[v];
        Wierzcholek pomoc;
        zr = new ZbiorRozlaczny(v);

        index = 0;

        wyczysc(mst);

        sortowanie.Sortuj(wierzcholek);

        for(int i = 0; i < v; i++)
            zr.MakeSet(i);

        for(int i = 0; index < v - 1; i++){

            pomoc = wierzcholek[i];

            int p1 = zr.FindSet(pomoc.getPoczatek());
            int p2 = zr.FindSet(pomoc.getKoniec());

            if(p1 != p2){

                mst[index] = pomoc;
                zr.UnionSet(pomoc);
                index++;

            }

        }

    }

    public void wypisz(){

        for(int i = 0; i < index; i++){
            System.out.println("Waga: " + mst[i].getWaga() + " Poczatek: " + mst[i].getPoczatek() + " Koniec: " + mst[i].getKoniec());
        }

    }

}
