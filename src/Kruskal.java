public class Kruskal {

    private int v;
    private int e;
    private int index = 0;
    private int pozycja = 0;

    private wierzcholekKolejka[] wierzcholek;
    private wierzcholekKolejka[] mst;
    private ZbiorRozlaczny zr;
    private MergeSort sortowanie = new MergeSort();
    private Kopiec kolejka = new Kopiec();

    private void wyczysc(wierzcholekKolejka[] w){

        for(int i = 0; i < w.length; i++){
            w[i] = new wierzcholekKolejka();
        }

    }

    public Kruskal(int v, int e){

        this.v = v;
        this.e = e;

        wierzcholek = new wierzcholekKolejka[e];

        wyczysc(wierzcholek);

    }

    public void dodajWierzcholki(int waga, int poczatek, int koniec){

        wierzcholek[pozycja].setWaga(waga);
        wierzcholek[pozycja].setWierzcholek(poczatek);
        wierzcholek[pozycja].setKoniec(koniec);
        pozycja++;

    }

    public void AlgorytmKruskala(){

        mst = new wierzcholekKolejka[v];
        wierzcholekKolejka pomoc;
        zr = new ZbiorRozlaczny(v);
        index = 0;

        wyczysc(mst);

        for(int i = 0; i < e; i++)
            kolejka.dodaj(wierzcholek[i]);

        for(int i = 0; i < v; i++)
            zr.MakeSet(i);

        for(int i = 0; index < v - 1; i++){

            pomoc = kolejka.minWierzcholek();
            kolejka.usunKorzen();

            int p1 = zr.FindSet(pomoc.getWierzcholek());
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
            System.out.println("Waga: " + mst[i].getWaga() + " Poczatek: " + mst[i].getWierzcholek() + " Koniec: " + mst[i].getKoniec());
        }

    }

}
