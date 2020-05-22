import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class Kruskal {

    private int v;
    private int e;
    private int index = 0;
    private int pozycja = 0;

    private wierzcholekKolejka[] wierzcholek;
    private ArrayList<wierzcholekKolejka>[] lista;
    private wierzcholekKolejka[] mst;
    private ZbiorRozlaczny zr;
    private Kopiec kolejka = new Kopiec();
    private Boolean[][] odwiedzane;
    int[][] macierz;

    private void wyczysc(wierzcholekKolejka[] w){

        for(int i = 0; i < w.length; i++){
            w[i] = new wierzcholekKolejka();
        }

    }

    private void wyczysc(Boolean[][] b){

        for(int i = 0; i < b.length; i++){

            for(int j = 0; j < b[i].length; j++){

                b[i][j] = false;

            }

        }

    }

    private void ustaw(){

        wierzcholek = new wierzcholekKolejka[e];
        lista = new ArrayList[v];
        macierz = new int[v][e];

        for(int i = 0; i < v; i++){
            lista[i] = new ArrayList<>();
        }

        for(int i = 0; i < v; i++){

            for(int j = 0; j < e; j++){

                macierz[i][j] = -1;

            }

        }

    }

    public Kruskal(int v, int e){

        this.v = v;
        this.e = e;

        pozycja = 0;

        ustaw();
        wyczysc(wierzcholek);

    }

    public Kruskal(){

    }

    public void dodajWierzcholek(int poczatek, int koniec, int waga){

        wierzcholekKolejka w1 = new wierzcholekKolejka(waga,poczatek,koniec);
        wierzcholekKolejka w2 = new wierzcholekKolejka(waga,koniec,poczatek);

        lista[poczatek].add(w2);
        lista[koniec].add(w1);

        macierz[poczatek][pozycja] = waga;
        macierz[koniec][pozycja] = waga;

        wierzcholek[pozycja].setWaga(waga);
        wierzcholek[pozycja].setWierzcholek(poczatek);
        wierzcholek[pozycja].setKoniec(koniec);

        pozycja++;

    }

    public void losowyGraf(int liczbaWierzcholkow, int gestosc){

        v = liczbaWierzcholkow;
        e = (v * gestosc) / 100;

        odwiedzane = new Boolean[v][v];

        ustaw();

        pozycja = 0;

        wyczysc(wierzcholek);
        wyczysc(odwiedzane);

        Random random = new Random();

        for(int i = 0; i < e; i++){

            int poczatek = random.nextInt(v);
            int koniec = random.nextInt(v);
            int waga = random.nextInt(100);

            if(odwiedzane[poczatek][koniec] == false && odwiedzane[koniec][poczatek] == false && poczatek != koniec) {
                dodajWierzcholek(poczatek, koniec, waga);
            }
            else
                i--;

            odwiedzane[poczatek][koniec] = true;
            odwiedzane[koniec][poczatek] = true;

        }

    }

    public void AlgorytmKruskalaMacierz(){

        mst = new wierzcholekKolejka[v];
        wierzcholekKolejka pomoc;
        zr = new ZbiorRozlaczny(v);
        index = 0;

        wyczysc(mst);

        for(int i = 0; i < e; i++) {

            int pozycja = 0;
            int tab[] = new int[2];
            int waga = 0;

            for (int j = 0; j < v; j++) {

                if(macierz[j][i] > -1){

                    waga = macierz[j][i];
                    tab[pozycja] = j;
                    pozycja++;

                }

            }

            wierzcholekKolejka w1 = new wierzcholekKolejka(waga,tab[0],tab[1]);
            wierzcholekKolejka w2 = new wierzcholekKolejka(waga,tab[1],tab[0]);

            kolejka.dodaj(w1);
            kolejka.dodaj(w2);

        }

        for(int i = 0; i < v; i++)
            zr.MakeSet(i);

        while(kolejka.getRozmiar() > 1){

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

    public void AlgorytmKruskala(){

        mst = new wierzcholekKolejka[v];
        wierzcholekKolejka pomoc;
        zr = new ZbiorRozlaczny(v);
        index = 0;

        wyczysc(mst);

        for(int i = 0; i < v; i++) {
            for (int j = 0; j < lista[i].size(); j++) {
                kolejka.dodaj(lista[i].get(j));
            }
        }

        for(int i = 0; i < v; i++)
            zr.MakeSet(i);

        while(kolejka.getRozmiar() > 1){

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

    public void wypiszWszystkieKrawedzieLista(){

        if(pozycja > 0) {

            System.out.println("GRAF NIESKIEROWANY");

            for (int i = 0; i < e; i++) {

                System.out.println("Poczatek: " + wierzcholek[i].getWierzcholek() + " Koniec: " + wierzcholek[i].getKoniec() + " Waga: " + wierzcholek[i].getWaga());

            }

        }
        else{

            System.out.println("Graf jest aktualnie pusty");

        }

    }

    public void wypiszWszystkieKrawedzieMacierz(){

        if(pozycja > 0) {

            System.out.println("GRAF NIESKIEROWANY");

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

    public void wczytajKruskal(String nazwaPliku){

        try{

            FileInputStream fstream = new FileInputStream(nazwaPliku);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            String line = br.readLine();
            String[] str = line.trim().split("\\s+");

             int v1 = Integer.parseInt(str[0]);
             int e1 = Integer.parseInt(str[1]);

             this.v = v1;
             this.e = e1;

             pozycja = 0;

             ustaw();
             wyczysc(wierzcholek);

             for (int i = 0; i < e; i++){

                 line = br.readLine();
                 String[] st = line.trim().split("\\s+");

                 dodajWierzcholek(Integer.parseInt(st[0]), Integer.parseInt(st[1]), Integer.parseInt(st[2]));

             }

             fstream.close();

        }catch (IOException e){
            e.printStackTrace();
        }

    }

}
