import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class BellmanFord {

    private int v;
    private int e;

    private int index = 0;
    private boolean check;

    private ArrayList<wierzcholekKolejka>[] lista;
    private wierzcholekKolejka[] wynik;
    private Boolean[][] losowe;
    private Boolean[] spj;
    private int[][] macierz;

    //konstruktor klasy BellmanFord
    public BellmanFord(int v, int e){

        this.v = v;
        this.e = e;

        //funkcja tworzaca i inicjalizujaca struktury danych
        ustaw();

    }

    //konstruktor klasy BellmanFord
    public BellmanFord(){

        check = false;

    }

    //stworzenie i inicjalizacja pozniej wykorzystywanych struktur danych
    private void ustaw(){

        wynik = new wierzcholekKolejka[v];
        losowe = new Boolean[v][v];
        spj = new Boolean[v];
        lista = new ArrayList[v];
        macierz = new int[v][e];

        check = false;
        index  = 0;

        for(int i = 0; i < v; i++){

            wynik[i] = new wierzcholekKolejka(Short.MAX_VALUE,i);
            lista[i] = new ArrayList<>();
            spj[i] = false;

        }

        for(int i = 0; i < v; i++){

            for(int j = 0; j < v; j++){

                losowe[i][j] = false;

            }

        }

        for(int i = 0; i < v; i++){

            for(int j = 0; j < e; j++){

                macierz[i][j] = 0;

            }

        }

    }

    //wyczyszczenie struktury zawierajacej wyniki w celu pozniejszego ponownego uzycia
    public void wyczysc(){

        for(int i = 0; i < v; i++){

            wynik[i] = new wierzcholekKolejka(Integer.MAX_VALUE/2,i);

        }

    }

    //funkcja dodajaca nowa krawedz do listy i macierzy
    public void dodajKrawedz(int poczatek, int koniec, int waga){

        wierzcholekKolejka w = new wierzcholekKolejka(waga,poczatek,koniec);

        lista[poczatek].add(w);

        macierz[poczatek][index] = waga;
        macierz[koniec][index] = -waga;

        check = true;

        index++;

    }

    //funkcja wyswietlajaca wszystkie krawedzie w formie listy
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

    //funkcja wyswietlajaca wszystkie krawedzie w formie macierzy
    public void wypiszKrawedzieMacierz(){

        if(check == true) {

            System.out.println("GRAF SKIEROWANY");

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

    //funkcja tworzaca losowy graf
    public void losowyGraf(int liczbaWierzcholkow, int gestosc){

        v = liczbaWierzcholkow;
        e = (v * gestosc) / 100;

        //funkcja inicjalizujaca potrzebne struktury danych
        ustaw();

        int poczatek;
        int koniec;

        Random random = new Random();

        //jesli wierzcholek startowy i koncowy beda takie same losowanie zostanie powtorzone
        do {
            poczatek = random.nextInt(v);
            koniec = random.nextInt(v);
        } while(poczatek == koniec);

        //wartosc wag krawedzie losowana z zakresu (-100,100)
        int waga = random.nextInt(200) - 100;

        //w celu lepszej reprezentacji w macierzy Incydencji krawedzie nie moga miec wagi 0
        if(waga == 0)
            waga++;

        //dodanie pierwszej krawedzi
        dodajKrawedz(poczatek, koniec, waga);

        //w celu zachowania spojnosci grafu i unikniecia powtarzania sie krawedzi zaznaczane sa wykorzystane juz wierzcholki i krawedzie
        spj[koniec] = true;
        spj[poczatek] = true;
        losowe[poczatek][koniec] = true;
        losowe[koniec][poczatek] = true;

        //dodanie w petli pozostalych krawedzi
        for(int i = 0; i < e - 1; i++){

            poczatek = random.nextInt(v);
            koniec = random.nextInt(v);
            waga = random.nextInt(200) - 100;

            if(waga == 0)
                waga++;

            if(losowe[poczatek][koniec] == false && losowe[koniec][poczatek] == false && poczatek != koniec && (spj[poczatek] == true || spj[koniec] == true)) {
                dodajKrawedz(poczatek, koniec, waga);
                losowe[poczatek][koniec] = true;
                losowe[koniec][poczatek] = true;
                spj[koniec] = true;
                spj[poczatek] = true;
            }
            else
                i--;

        }

    }

    //algorytm Bellmana-Forda dla listy sasiedztwa
    public Boolean AlgorytmBF(int poczatek, int koniec){

        //wybranie wierzcholka poczatkowego
        wynik[poczatek].setWaga(0);

        //relaksacja krawedzi
        for(int i = 0; i < v; i++){

            for(int j = 0; j < lista[i].size(); j++){

                if(wynik[lista[i].get(j).getWierzcholek()].getWaga() + lista[i].get(j).getWaga() < wynik[lista[i].get(j).getKoniec()].getWaga()){
                    wynik[lista[i].get(j).getKoniec()].setWaga(wynik[lista[i].get(j).getWierzcholek()].getWaga() + lista[i].get(j).getWaga());
                }

            }

        }

        //sprawdzenie czy wystepuja ujemne cykle
        for(int i = 0; i < v; i++) {

            for (int j = 0; j < lista[i].size(); j++) {

                if (wynik[lista[i].get(j).getWierzcholek()].getWaga() + lista[i].get(j).getWaga() < wynik[lista[i].get(j).getKoniec()].getWaga()) {
                    return false;

                }

            }

        }

        return true;

    }

    //algorytm Bellmana-Forda dla macierzy incydencji
    public Boolean AlgorytmBFMacierz(int poczatek, int koniec){

        //wybranie wierzcholka poczatkowego
        wynik[poczatek].setWaga(0);

        //relaksacja krawedzi
        for(int g = 0; g < v - 1; g++) {

            for (int i = 0; i < v; i++) {

                for (int j = 0; j < v; j++) {

                    if (macierz[i][j] != 0) {

                        if (wynik[j].getWaga() + Math.abs(macierz[i][j]) < wynik[i].getWaga()) {

                            wynik[i].setWaga(wynik[j].getWaga() + Math.abs(macierz[i][j]));

                        }

                    }

                }

            }

        }

        //sprawdzenie czy wystepuja ujemne cykle
        for(int i = 0; i < v; i++) {

            for (int j = 0; j < v; j++) {

                if(macierz[i][j] != 0) {

                    if (wynik[j].getWaga() + Math.abs(macierz[i][j]) < wynik[i].getWaga()) {

                        return false;

                    }

                }

            }

        }

        return true;

    }

    //algorytm Bellmana-Forda bez podawania wierzcholka poczatkowego
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

    //funkcja najkrotsza sciezke pomiedzy wierzcholkiem startowym a koncowym
    public void wypisz(int poczatek, int koniec){

        if(wynik[koniec].getWaga() != Short.MAX_VALUE)
            System.out.println("Wierzcholek poczatkowy: " + poczatek + " Wierzcholek koncowy: " + koniec + " Waga najkrotszej sciezki: " + wynik[koniec].getWaga());
        else
            System.out.println("Brak sciezki pomiedzy tymi wierzcholkami");

    }

    //funkcja wczytujaca graf z pliku tekstowego
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

            //inicjalizacja potrzebnych struktur danych
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
