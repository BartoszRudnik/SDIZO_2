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

    public void AlgorytmDijkstra(){

        odwiedzane[0] = true;
        wierzcholek[0].setWaga(0);

        for(int i = 0; i < v; i++)
            kolejka.dodaj(wierzcholek[i]);

        while(kolejka.getRozmiar() > 1){

            wierzcholekKolejka pomoc = kolejka.minKopiec();
            kolejka.usun(pomoc);

            odwiedzane[pomoc.getWierzcholek()] = true;

            for(wierzcholekKolejka w : lista[pomoc.getWierzcholek()]){

                if(odwiedzane[w.getWierzcholek()] == false && wierzcholek[pomoc.getWierzcholek()].getWaga() != Integer.MAX_VALUE && wierzcholek[w.getWierzcholek()].getWaga() > wierzcholek[pomoc.getWierzcholek()].getWaga() + w.getWaga()){

                    kolejka.usun(wierzcholek[w.getWierzcholek()]);
                    wierzcholek[w.getWierzcholek()].setWaga(pomoc.getWaga() + w.getWaga());
                    kolejka.dodaj(wierzcholek[w.getWierzcholek()]);


                }

            }

        }

    }

    public void wypisz(){

        for(int i = 0; i < v; i++){
            System.out.println(i + " " + wierzcholek[i].getWaga());
        }

    }

}
