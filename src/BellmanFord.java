import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class BellmanFord {

    private int v;
    private int e;

    private int pozycja = 0;

    private wierzcholekKolejka[] wierzcholek;
    private wierzcholekKolejka[] wynik;
    private Boolean[][] losowe;

    public BellmanFord(int v, int e){

        this.v = v;
        this.e = e;

        ustaw();

    }

    public BellmanFord(){

    }

    private void ustaw(){

        wierzcholek = new wierzcholekKolejka[e];
        wynik = new wierzcholekKolejka[v];
        losowe = new Boolean[v][v];

        pozycja = 0;

        for(int i = 0; i < v; i++){
            wynik[i] = new wierzcholekKolejka(Integer.MAX_VALUE/2,i);
        }

        for(int i = 0; i < e; i++){
            wierzcholek[i] = new wierzcholekKolejka();
        }

        for(int i = 0; i < v; i++){

            for(int j = 0; j < v; j++){

                losowe[i][j] = false;

            }

        }

    }

    public void dodajKrawedz(int poczatek, int koniec, int waga){

        wierzcholek[pozycja].setWaga(waga);
        wierzcholek[pozycja].setWierzcholek(poczatek);
        wierzcholek[pozycja].setKoniec(koniec);
        pozycja++;

    }

    public void wypiszKrawedzie(){

        if(pozycja != 0) {

            System.out.println("GRAF SKIEROWANY");

            for (int i = 0; i < e; i++) {

                System.out.println("Poczatek: " + wierzcholek[i].getWierzcholek() + " Koniec: " + wierzcholek[i].getKoniec() + " Waga: " + wierzcholek[i].getWaga());

            }

        }
        else{

            System.out.println("Graf jest aktualnie pusty");

        }

    }

    public void wypiszKrawedzieMacierz(){

        if(pozycja > 0) {

            System.out.println("GRAF SKIEROWANY");

            int[][] macierz = new int[v][e];

            for(int i = 0; i < e; i++){

                macierz[wierzcholek[i].getWierzcholek()][i] = 1;
                macierz[wierzcholek[i].getKoniec()][i] = -1;

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

            for(int j = 0; j < e; j++){

                if(wynik[wierzcholek[j].getWierzcholek()].getWaga() + wierzcholek[j].getWaga() < wynik[wierzcholek[j].getKoniec()].getWaga()){
                    wynik[wierzcholek[j].getKoniec()].setWaga(wynik[wierzcholek[j].getWierzcholek()].getWaga() + wierzcholek[j].getWaga());
                }

            }

        }

        for(int i = 0; i < e; i++){

            if(wynik[wierzcholek[i].getWierzcholek()].getWaga() + wierzcholek[i].getWaga() < wynik[wierzcholek[i].getKoniec()].getWaga()){
                return false;
            }

        }

        return true;

    }

    public Boolean AlgorytmBF(){

        wynik[0].setWaga(0);

        for(int i = 0; i < v; i++){

            for(int j = 0; j < e; j++){

                if(wynik[wierzcholek[j].getWierzcholek()].getWaga() + wierzcholek[j].getWaga() < wynik[wierzcholek[j].getKoniec()].getWaga()){
                    wynik[wierzcholek[j].getKoniec()].setWaga(wynik[wierzcholek[j].getWierzcholek()].getWaga() + wierzcholek[j].getWaga());
                }

            }

        }

        for(int i = 0; i < e; i++){

            if(wynik[wierzcholek[i].getWierzcholek()].getWaga() + wierzcholek[i].getWaga() < wynik[wierzcholek[i].getKoniec()].getWaga()){
                return false;
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
