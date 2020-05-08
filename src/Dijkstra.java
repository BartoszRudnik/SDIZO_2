import java.util.ArrayList;

public class Dijkstra {

    private int v;

    private wierzcholekKolejka[] wierzcholek;
    private ArrayList<wierzcholekKolejka>[] lista;
    private Boolean[] odwiedzane;
    private Kopiec kolejka = new Kopiec();

    public Dijkstra(){

    }

    public Dijkstra(int v){

        this.v = v;

        wierzcholek = new wierzcholekKolejka[v];
        lista = new ArrayList[v];
        odwiedzane = new Boolean[v];

        for(int i = 0; i < v; i++)
            lista[i] = new ArrayList<>();

        ustaw();

    }

    public void dodajKrawedz(int waga, int poczatek, int koniec){

        wierzcholekKolejka w1 = new wierzcholekKolejka(waga,koniec);

        lista[poczatek].add(w1);

    }

    private void ustaw(){

        for(int i = 0; i < v; i++){

            wierzcholek[i] = new wierzcholekKolejka(Integer.MAX_VALUE,i);
            odwiedzane[i] = false;

        }

    }

    private void relax(wierzcholekKolejka u, wierzcholekKolejka adj){

        if(odwiedzane[adj.getWierzcholek()] == false && wierzcholek[adj.getWierzcholek()].getWaga() > wierzcholek[u.getWierzcholek()].getWaga() + adj.getWaga()){

            kolejka.usun(wierzcholek[adj.getWierzcholek()]);
            wierzcholek[adj.getWierzcholek()].setWaga(u.getWaga() + adj.getWaga());
            kolejka.dodaj(wierzcholek[adj.getWierzcholek()]);

        }

    }

    public void AlgorytmDijkstra(){

        odwiedzane[0] = true;
        wierzcholek[0].setWaga(0);

        for(int i = 0; i < v; i++)
            kolejka.dodaj(wierzcholek[i]);

        while(kolejka.getRozmiar() > 1){

            wierzcholekKolejka pomoc = kolejka.minKopiec();
            kolejka.usun(pomoc);

            odwiedzane[pomoc.getWierzcholek()] = true;

            for(wierzcholekKolejka w : lista[pomoc.getWierzcholek()])
                relax(pomoc,w);

        }

    }

    public void wypisz(){

        for(int i = 0; i < v; i++){
            System.out.println(i + " " + wierzcholek[i].getWaga());
        }

    }

}
