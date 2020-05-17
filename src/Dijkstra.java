import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Dijkstra {

    private int v;
    private int e;

    private wierzcholekKolejka[] wierzcholek;
    private ArrayList<wierzcholekKolejka>[] lista;
    private Boolean[] odwiedzane;
    private Kopiec kolejka = new Kopiec();

    public Dijkstra(){

    }

    public Dijkstra(int v, int e){

        this.v = v;
        this.e = e;

        ustaw();

    }

    public void dodajKrawedz(int poczatek, int koniec, int waga){

        wierzcholekKolejka w1 = new wierzcholekKolejka(waga,koniec);

        lista[poczatek].add(w1);

    }

    public void wypiszKrawedzie(){

        System.out.println("GRAF SKIEROWANY");

        for(int i = 0; i < v; i++){

            for(int j = 0; j < lista[i].size(); j++){

                System.out.println("Poczatek: " + i + " Koniec: " + lista[i].get(j).getWierzcholek() + " Waga: " + lista[i].get(j).getWaga());

            }

        }

    }

    private void ustaw(){

        wierzcholek = new wierzcholekKolejka[e];
        lista = new ArrayList[e];
        odwiedzane = new Boolean[e];

        for(int i = 0; i < e; i++)
            lista[i] = new ArrayList<>();


        for(int i = 0; i < e; i++){

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

        for(int i = 0; i < e; i++)
            kolejka.dodaj(wierzcholek[i]);

        while(kolejka.getRozmiar() > 1){

            wierzcholekKolejka pomoc = kolejka.minWierzcholek();
            kolejka.usunKorzen();

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

    public void wczytajDijkstra(String nazwaPliku){

        try{

            FileInputStream fstream = new FileInputStream(nazwaPliku);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            String line = br.readLine();
            String[] str = line.trim().split("\\s+");

            int v1 = Integer.parseInt(str[0]);
            int e1 = Integer.parseInt(str[1]);

            this.v = v1;
            this.e = e1;

            ustaw();

            for (int i = 0; i < e; i++){

                line = br.readLine();
                String[] st = line.trim().split("\\s+");

                dodajKrawedz(Integer.parseInt(st[0]), Integer.parseInt(st[1]), Integer.parseInt(st[2]));

            }

            fstream.close();

        }catch (IOException e){
            e.printStackTrace();
        }

    }

}
