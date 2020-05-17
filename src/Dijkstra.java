import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class Dijkstra {

    private int v;
    private int e;

    private Boolean check;

    private wierzcholekKolejka[] wierzcholek;
    private ArrayList<wierzcholekKolejka>[] lista;
    private Boolean[] odwiedzane;
    private Kopiec kolejka = new Kopiec();
    private Boolean[][] losowe;

    public Dijkstra(){

        check = false;

    }

    public Dijkstra(int v, int e){

        this.v = v;
        this.e = e;

        ustaw();

    }

    public void dodajKrawedz(int poczatek, int koniec, int waga){

        wierzcholekKolejka w1 = new wierzcholekKolejka(waga,koniec);

        lista[poczatek].add(w1);
        check = true;

    }

    public void wypiszKrawedzie(){

        if(check == true) {

            System.out.println("GRAF SKIEROWANY");

            for (int i = 0; i < v; i++) {

                for (int j = 0; j < lista[i].size(); j++) {

                    System.out.println("Poczatek: " + i + " Koniec: " + lista[i].get(j).getWierzcholek() + " Waga: " + lista[i].get(j).getWaga());

                }

            }

        }
        else{

            System.out.println("Graf jest aktualnie pusty");

        }

    }

    public void losowyGraf(int liczbaWierzcholkow, int gestosc){

        v = liczbaWierzcholkow;
        e = (v * gestosc) / 100;

        ustaw();

        Random random = new Random();

        for(int i = 0; i < e; i++){

            int poczatek = random.nextInt(v);
            int koniec = random.nextInt(v);
            int waga = random.nextInt(100);

            if(losowe[poczatek][koniec] == false && losowe[koniec][poczatek] == false && poczatek != koniec) {
                dodajKrawedz(poczatek, koniec, waga);
            }
            else
                i--;

            losowe[poczatek][koniec] = true;
            losowe[koniec][poczatek] = true;

        }

    }

    private void ustaw(){

        wierzcholek = new wierzcholekKolejka[e];
        lista = new ArrayList[v];
        odwiedzane = new Boolean[v];
        losowe = new Boolean[v][v];

        check = false;

        for(int i = 0; i < v; i++) {

            lista[i] = new ArrayList<>();
            odwiedzane[i] = false;

        }

        for(int i = 0; i < v; i++) {

            for(int j = 0; j < v; j++){

                losowe[i][j] = false;

            }

        }

        for(int i = 0; i < e; i++){

            wierzcholek[i] = new wierzcholekKolejka(Integer.MAX_VALUE,i);

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
