import java.util.ArrayList;

public class Prim {

    private int v;

    private wierzcholekKolejka[] wierzcholek;
    private ArrayList<wierzcholekKolejka>[] lista;
    private Boolean[] odwiedzane;
    private Kopiec kolejka = new Kopiec();
    private wierzcholekKolejka[] mst;

    public Prim(){

    }

    public Prim(int v){

        this.v = v;

        wierzcholek = new wierzcholekKolejka[v];
        lista = new ArrayList[v];
        odwiedzane = new Boolean[v];
        mst = new wierzcholekKolejka[v];

        for(int i = 0; i < v; i++)
            lista[i] = new ArrayList<>();

        ustaw();

    }

    public void dodajWierzcholek(int waga, int poczatek, int koniec){

        wierzcholekKolejka w1 = new wierzcholekKolejka(waga,poczatek);
        wierzcholekKolejka w2 = new wierzcholekKolejka(waga,koniec);

        lista[poczatek].add(w2);
        lista[koniec].add(w1);

    }

    private void ustaw(){

        for(int i = 0; i < v; i++){

            wierzcholek[i] = new wierzcholekKolejka(Integer.MAX_VALUE,0);
            odwiedzane[i] = false;
            mst[i] = new wierzcholekKolejka(-1,-1);
        }

    }

    public void AlgorytmPrima(){

        odwiedzane[0] = true;
        wierzcholek[0].setWaga(0);

        for(int i = 0; i < v; i++)
            kolejka.dodaj(wierzcholek[i]);

        while(kolejka.getRozmiar() > 1){

            wierzcholekKolejka pomoc = kolejka.minKopiec();
            kolejka.usun(pomoc);

            odwiedzane[pomoc.getWierzcholek()] = true;

            for(wierzcholekKolejka w : lista[pomoc.getWierzcholek()]){

                if(odwiedzane[w.getWierzcholek()] == false && wierzcholek[w.getWierzcholek()].getWaga() > w.getWaga()){

                    kolejka.usun(wierzcholek[w.getWierzcholek()]);
                    wierzcholek[w.getWierzcholek()].setWaga(w.getWaga());
                    kolejka.dodaj(wierzcholek[w.getWierzcholek()]);

                    mst[w.getWierzcholek()] = pomoc;

                }

            }

        }

    }

    public void wypiszPrim(){

        for(int i = 1; i < v; i++)
            System.out.println(i + " " + mst[i].getWierzcholek());

    }

}
