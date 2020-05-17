import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Kruskal {

    private int v;
    private int e;
    private int index = 0;
    private int pozycja = 0;

    private wierzcholekKolejka[] wierzcholek;
    private wierzcholekKolejka[] mst;
    private ZbiorRozlaczny zr;
    private Kopiec kolejka = new Kopiec();
    private Boolean[][] odwiedzane;

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

    public Kruskal(int v, int e){

        this.v = v;
        this.e = e;

        pozycja = 0;

        wierzcholek = new wierzcholekKolejka[e];

    }

    public Kruskal(){

    }

    public void dodajWierzcholek(int poczatek, int koniec, int waga){

        wierzcholek[pozycja].setWaga(waga);
        wierzcholek[pozycja].setWierzcholek(poczatek);
        wierzcholek[pozycja].setKoniec(koniec);
        pozycja++;

    }

    public void losowyGraf(int liczbaWierzcholkow, int gestosc){

        v = liczbaWierzcholkow;
        e = (v * gestosc) / 100;

        odwiedzane = new Boolean[v][v];
        wierzcholek = new wierzcholekKolejka[e];

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

    public void wypiszWszystkieKrawedzie(){

        if(pozycja != 0) {


            System.out.println("GRAF NIESKIEROWANY");

            for (int i = 0; i < e; i++) {

                System.out.println("Poczatek: " + wierzcholek[i].getWierzcholek() + " Koniec: " + wierzcholek[i].getKoniec() + " Waga: " + wierzcholek[i].getWaga());

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

             wierzcholek = new wierzcholekKolejka[e];
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
