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
    int index = 0;

    private wierzcholekKolejka[] wierzcholek;
    private ArrayList<wierzcholekKolejka>[] lista;
    private Boolean[] odwiedzane;
    private Kopiec kolejka;
    private Boolean[][] losowe;
    private int[][] macierz;

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

        macierz[poczatek][index] = waga;
        macierz[koniec][index] = -waga;

        lista[poczatek].add(w1);

        check = true;
        index++;

    }

    public void wypiszKrawedzieLista(){

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

    public void wypiszKrawedzieMacierz(){

        if(check == true) {

            System.out.println("GRAF SKIEROWANY");

            for (int i = 0; i < v; i++) {

                for (int j = 0; j < e; j++) {

                    System.out.print(macierz[i][j] + "    ");

                }

                System.out.println();

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

        wierzcholek = new wierzcholekKolejka[v];
        lista = new ArrayList[v];
        odwiedzane = new Boolean[v];
        losowe = new Boolean[v][v];
        macierz = new int[v][e];

        check = false;
        index = 0;

        for(int i = 0; i < v; i++) {

            for(int j = 0; j < v; j++){

                losowe[i][j] = false;

            }

        }

        for(int i = 0; i < v; i++){

            lista[i] = new ArrayList<>();
            wierzcholek[i] = new wierzcholekKolejka(Integer.MAX_VALUE,i);
            odwiedzane[i] = false;

        }

        for(int i = 0; i < v; i++){

            for(int j = 0; j < e; j++){

                macierz[i][j] = -1;

            }

        }

    }

    public void wyczysc(){

        for(int i = 0; i < v; i++) {

            wierzcholek[i] = new wierzcholekKolejka(Integer.MAX_VALUE, i);
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

    public void AlgorytmDijkstra(int poczatek, int  koniec){

        odwiedzane[poczatek] = true;
        wierzcholek[poczatek].setWaga(0);

        kolejka = new Kopiec();

        for(int i = 0; i < e; i++)
            kolejka.dodaj(wierzcholek[i]);

        while(kolejka.getRozmiar() > 1){

            wierzcholekKolejka pomoc = kolejka.minWierzcholek();
            kolejka.usunKorzen();

            odwiedzane[pomoc.getWierzcholek()] = true;

            for(wierzcholekKolejka w : lista[pomoc.getWierzcholek()])
                relax(pomoc,w);

            if(odwiedzane[koniec] == true)
                break;

        }

    }

    public void AlgorytmDijkstraMacierz(int poczatek, int  koniec){

        odwiedzane[poczatek] = true;
        wierzcholek[poczatek].setWaga(0);

        kolejka = new Kopiec();

        for(int i = 0; i < e; i++)
            kolejka.dodaj(wierzcholek[i]);

        while(kolejka.getRozmiar() > 1){

            wierzcholekKolejka pomoc = kolejka.minWierzcholek();
            kolejka.usunKorzen();

            odwiedzane[pomoc.getWierzcholek()] = true;

            for(int i = 0; i < e; i++){

                if(macierz[pomoc.getWierzcholek()][i] > -1) {

                    for (int j = 0; j < v; j++) {

                        if(macierz[j][i] < -1) {

                            if (odwiedzane[j] == false && wierzcholek[j].getWaga() > Math.abs(macierz[j][i]) + wierzcholek[pomoc.getWierzcholek()].getWaga()) {

                                kolejka.usun(wierzcholek[j]);
                                wierzcholek[j].setWaga(Math.abs(macierz[j][i]) + wierzcholek[pomoc.getWierzcholek()].getWaga());
                                kolejka.dodaj(wierzcholek[j]);

                            }

                        }

                    }

                }

            }

            if(odwiedzane[koniec] == true)
                break;

        }

    }

    public void AlgorytmDijkstra(){

        odwiedzane[0] = true;
        wierzcholek[0].setWaga(0);

        kolejka = new Kopiec();

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

    public void wypiszWynik(){

        for(int i = 1; i < v; i++){
            System.out.println("Wierzcholek poczatkowy: 0" + " Wierzcholek koncowy: " + i + " Waga najkrotszej sciezki: " + wierzcholek[i].getWaga());
        }

    }

    public void wypiszWynik(int poczatek, int koniec){

        System.out.println("Wierzcholek poczatkowy: " + poczatek + " Wierzcholek koncowy: "+ koniec  + " Waga najkrotszej sciezki: " + wierzcholek[koniec].getWaga());

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
