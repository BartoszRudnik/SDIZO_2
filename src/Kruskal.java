import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Kruskal {

    private int v;
    private int e;
    private int index = 0;

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

    }

    public Kruskal(){

    }

    public void dodajWierzcholek(int waga, int poczatek, int koniec){

        wierzcholekKolejka wierzcholek = new wierzcholekKolejka();

        wierzcholek.setWaga(waga);
        wierzcholek.setWierzcholek(poczatek);
        wierzcholek.setKoniec(koniec);

        kolejka.dodaj(wierzcholek);

    }

    public void AlgorytmKruskala(){

        mst = new wierzcholekKolejka[v];
        wierzcholekKolejka pomoc;
        zr = new ZbiorRozlaczny(v);
        index = 0;

        wyczysc(mst);

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

             for (int i = 0; i < e; i++){

                 line = br.readLine();
                 String[] st = line.trim().split("\\s+");

                 dodajWierzcholek(Integer.parseInt(st[2]), Integer.parseInt(st[0]), Integer.parseInt(st[1]));

             }

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void test(){

        kolejka.wyswietlKopiec();

    }

}
