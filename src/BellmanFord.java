import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class BellmanFord {

    private int v;
    private int e;

    private boolean check;

    private ArrayList<wierzcholekKolejka>[] lista;
    private wierzcholekKolejka[] wierzcholek;
    private wierzcholekKolejka[] wynik;
    private Boolean[][] losowe;

    public BellmanFord(int v, int e){

        this.v = v;
        this.e = e;

        ustaw();

    }

    public BellmanFord(){

        check = false;

    }

    private void ustaw(){

        wierzcholek = new wierzcholekKolejka[v];
        wynik = new wierzcholekKolejka[v];
        losowe = new Boolean[v][v];
        lista = new ArrayList[v];

        check = false;

        for(int i = 0; i < v; i++){

            wynik[i] = new wierzcholekKolejka(Integer.MAX_VALUE/2,i);
            lista[i] = new ArrayList<>();
            wierzcholek[i] = new wierzcholekKolejka();

        }

        for(int i = 0; i < v; i++){

            for(int j = 0; j < v; j++){

                losowe[i][j] = false;

            }

        }

    }

    public void dodajKrawedz(int poczatek, int koniec, int waga){

        wierzcholekKolejka w = new wierzcholekKolejka(waga,poczatek,koniec);

        lista[poczatek].add(w);

        check = true;

    }

    public void wypiszKrawedzieLista(){

        if(check == true) {

            System.out.println("GRAF SKIEROWANY");

            for (int i = 0; i < v; i++) {

                for (int j = 0; j < lista[i].size(); j++) {

                    System.out.println("Poczatek: " + lista[i].get(j).getWierzcholek() + " Koniec: " + lista[i].get(j).getKoniec() + " Waga: " + lista[i].get(j).getWaga());

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

            int[][] macierz = new int[v][e];
            int pomoc = 0;

            for(int i = 0; i < v; i++){

                for(int j = 0; j < lista[i].size(); j++){

                    macierz[i][pomoc] = 1;
                    macierz[lista[i].get(j).getKoniec()][pomoc] = -1;
                    pomoc++;

                }

            }

            for (int i = 0; i < v; i++) {

                for (int j = 0; j < e; j++) {

                    System.out.print(macierz[i][j] + "  ");

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
            int waga = random.nextInt(200) - 100;

            if(losowe[poczatek][koniec] == false && losowe[koniec][poczatek] == false && poczatek != koniec) {
                dodajKrawedz(poczatek, koniec, waga);
            }
            else
                i--;

            losowe[poczatek][koniec] = true;
            losowe[koniec][poczatek] = true;

        }

    }

    public Boolean AlgorytmBF(int poczatek, int koniec){

        wynik[poczatek].setWaga(0);

        for(int i = 0; i < v; i++){

            for(int j = 0; j < lista[i].size(); j++){

                if(wynik[lista[i].get(j).getWierzcholek()].getWaga() + lista[i].get(j).getWaga() < wynik[lista[i].get(j).getKoniec()].getWaga()){
                    wynik[lista[i].get(j).getKoniec()].setWaga(wynik[lista[i].get(j).getWierzcholek()].getWaga() + lista[i].get(j).getWaga());
                }

            }

        }

        for(int i = 0; i < v; i++) {

            for (int j = 0; j < lista[i].size(); j++) {

                if (wynik[lista[i].get(j).getWierzcholek()].getWaga() + lista[i].get(j).getWaga() < wynik[lista[i].get(j).getKoniec()].getWaga()) {
                    return false;

                }

            }

        }

        return true;

    }

    public Boolean AlgorytmBF(){

        wynik[0].setWaga(0);

        for(int i = 0; i < v; i++){

            for(int j = 0; j < lista[i].size(); j++){

                if(wynik[lista[i].get(j).getWierzcholek()].getWaga() + lista[i].get(j).getWaga() < wynik[lista[i].get(j).getKoniec()].getWaga()){
                    wynik[lista[i].get(j).getKoniec()].setWaga(wynik[lista[i].get(j).getWierzcholek()].getWaga() + lista[i].get(j).getWaga());
                }

            }

        }

        for(int i = 0; i < v; i++) {

            for (int j = 0; j < lista[i].size(); j++) {

                if (wynik[lista[i].get(j).getWierzcholek()].getWaga() + lista[i].get(j).getWaga() < wynik[lista[i].get(j).getKoniec()].getWaga()) {
                    return false;

                }

            }

        }

        return true;

    }

    public void wypisz(){

        for(int i = 0; i < v; i++){
            System.out.println("Wierzcholek poczatkowy: 0 " + "Wierzcholek koncowy: " + i + " Waga najkrotszej sciezki: " + wynik[i].getWaga());
        }

    }

    public void wypisz(int poczatek, int koniec){

        System.out.println("Wierzcholek poczatkowy: " + poczatek + " Wierzcholek koncowy: " + koniec + " Waga najkrotszej sciezki: " + wynik[koniec].getWaga());

    }

    public void wczytajBF(String nazwaPliku){

        try{

            FileInputStream fstream = new FileInputStream(nazwaPliku);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            String line = br.readLine();
            String[] str = line.trim().split("\\s+");

            int v1 = Integer.parseInt(str[0]);
            int e1 = Integer.parseInt(str[1]);

            v = v1;
            e = e1;

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
