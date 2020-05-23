import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class Prim {

    private int v;
    private int e;

    private int index = 0;

    private wierzcholekKolejka[] wierzcholek;
    private ArrayList<wierzcholekKolejka>[] lista;
    private Boolean[] odwiedzane;
    private Kopiec kolejka;
    private wierzcholekKolejka[] mst;
    private wierzcholekKolejka[] wszystkie;
    private Boolean[][] losowe;
    int[][] macierz;

    public Prim(){

    }

    public Prim(int v, int e){

        this.v = v;
        this.e = e;

        ustaw();

    }

    public void dodajKrawedz(int poczatek, int koniec, int waga){

        wierzcholekKolejka w1 = new wierzcholekKolejka(waga,poczatek);
        wierzcholekKolejka w2 = new wierzcholekKolejka(waga,koniec);

        lista[poczatek].add(w2);
        lista[koniec].add(w1);

        macierz[poczatek][index] = waga;
        macierz[koniec][index] = waga;

        wszystkie[index].setWierzcholek(poczatek);
        wszystkie[index].setKoniec(koniec);
        wszystkie[index].setWaga(waga);

        index++;

    }

    private void ustaw(){

        wierzcholek = new wierzcholekKolejka[v];
        lista = new ArrayList[v];
        odwiedzane = new Boolean[v];
        mst = new wierzcholekKolejka[v];
        wszystkie = new wierzcholekKolejka[v];
        losowe = new Boolean[v][v];
        macierz = new int[v][e];

        index = 0;

        for(int i = 0; i < v; i++){

            for(int j = 0; j < v; j++){

                losowe[i][j] = false;

            }

        }

        for(int i = 0; i < v; i++){

            mst[i] = new wierzcholekKolejka(-1,-1);
            wierzcholek[i] = new wierzcholekKolejka(Integer.MAX_VALUE,i);
            odwiedzane[i] = false;
            wszystkie[i] = new wierzcholekKolejka();
            lista[i] = new ArrayList<>();

        }

        for(int i = 0; i < v; i++){

            for(int j = 0; j < e; j++){

                macierz[i][j] = -1;

            }

        }

    }

    public void wyczysc(){

        for(int i = 0; i < v; i++){

            mst[i] = new wierzcholekKolejka(-1,-1);
            wierzcholek[i] = new wierzcholekKolejka(Integer.MAX_VALUE,i);
            odwiedzane[i] = false;

        }

    }

    public void wypiszKrawedzieLista(){

        if(index != 0) {

            System.out.println("GRAF NIESKIEROWANY");

            for (int i = 0; i < e; i++) {

                System.out.println("Poczatek: " + wszystkie[i].getWierzcholek() + " Koniec: " + wszystkie[i].getKoniec() + " Waga: " + wszystkie[i].getWaga());

            }

        }
        else{

            System.out.println("Graf jest aktualnie pusty");

        }

    }

    public void wypiszKrawedzieMacierz(){

        if(index > 0) {

            System.out.println("GRAF NIESKIEROWANY");

            for (int i = 0; i < v; i++) {

                for (int j = 0; j < e; j++) {

                    System.out.print(macierz[i][j] + "     ");

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

    public void AlgorytmPrima(){

        odwiedzane[0] = true;
        wierzcholek[0].setWaga(0);

        kolejka = new Kopiec();

        for(int i = 0; i < e; i++)
            kolejka.dodaj(wierzcholek[i]);

        while(kolejka.getRozmiar() > 1){

            wierzcholekKolejka pomoc = kolejka.minWierzcholek();
            kolejka.usunKorzen();

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

    public void AlgorytmPrimaMacierz(){

        odwiedzane[0] = true;
        wierzcholek[0].setWaga(0);

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

                        if(macierz[j][i] > -1) {

                            if (odwiedzane[j] == false && wierzcholek[j].getWaga() > macierz[j][i]) {

                                kolejka.usun(wierzcholek[j]);
                                wierzcholek[j].setWaga(macierz[j][i]);
                                kolejka.dodaj(wierzcholek[j]);

                                mst[j] = pomoc;

                            }

                        }

                    }

                }

            }

        }

    }

    public void wypiszPrim(){

        for(int i = 1; i < v; i++) {
            int w = wWaga(mst[i].getWierzcholek(),i);
            System.out.println("Poczatek: " + i + " " + "Koniec: " + mst[i].getWierzcholek() + "  Waga: " + w);
        }

    }

    private int wWaga(int a, int b){

        for(int i = 0; i < lista[a].size(); i++){
            if(lista[a].get(i).getWierzcholek() == b)
                return lista[a].get(i).getWaga();
        }

        return -1;

    }

    public void wczytajPrim(String nazwaPliku){

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
