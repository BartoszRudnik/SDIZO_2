import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class FordFulkerson {

    private int v;
    private int e;

    private int pozycja = 0;
    private boolean check;

    private ArrayList<wierzcholekKolejka>[] residual;
    private wierzcholekKolejka[] wszystkie;
    private int[] sciezka;
    private boolean[] odwiedzone;
    private ArrayList<Integer> lista;
    private Boolean[][] losowe;
    private int[][] macierz;
    private int[][] macierz2;

    public FordFulkerson(int v, int e){

        this.v = v;
        this.e = e;

        ustaw();

    }

    public FordFulkerson(){

    }

    private boolean bfs(int s, int t){

        lista = new ArrayList<>();
        odwiedzone = new boolean[v];

        for(int i = 0; i < v; i++)
            odwiedzone[i] = false;

        lista.add(s);
        sciezka[s] = -1;
        odwiedzone[s] = true;

        while(lista.size() > 0) {

            int u = lista.get(0);
            lista.remove(0);

            for (int i = 0; i < v; i++) {

                if (residual[u].get(i).getWaga() > 0 && odwiedzone[i] == false) {

                    lista.add(i);
                    sciezka[i] = u;
                    odwiedzone[i] = true;

                }

            }

        }

        if (odwiedzone[t] == true)
            return true;
        else
            return false;

    }

    private boolean dfs(int s, int t){

        lista = new ArrayList<>();
        odwiedzone = new boolean[v];

        for(int i = 0; i < v; i++)
            odwiedzone[i] = false;

        lista.add(s);
        sciezka[s] = -1;

        while(lista.size() > 0){

            int u = lista.get(0);
            lista.remove(0);

            if(odwiedzone[u] == false)
                odwiedzone[u] = true;

            for(int i = 0; i < residual[u].size(); i++){

                if(residual[u].get(i).getWaga() > 0 && odwiedzone[i] == false) {
                    lista.add(i);
                    sciezka[i] = u;
                }

            }

        }

        if(odwiedzone[t] == true)
            return true;
        else
            return false;

    }

    private boolean bfsMacierz(int s, int t){

        lista = new ArrayList<>();
        odwiedzone = new boolean[v];

        for(int i = 0; i < v; i++)
            odwiedzone[i] = false;

        lista.add(s);
        sciezka[s] = -1;
        odwiedzone[s] = true;

        while(lista.size() > 0) {

            int u = lista.get(0);
            lista.remove(0);

            for (int i = 0; i < v; i++) {

                int index = -1;

                for(int j = 0; j < e; j++){

                    if(macierz[u][j] != 0 && macierz[i][j] != 0)
                        index = j;

                }

                if (index != -1 && macierz[u][index] > 0 && odwiedzone[i] == false) {

                    lista.add(i);
                    sciezka[i] = u;
                    odwiedzone[i] = true;

                }

            }

        }

        if (odwiedzone[t] == true)
            return true;
        else
            return false;

    }

    private boolean dfsMacierz(int s, int t){

        lista = new ArrayList<>();
        odwiedzone = new boolean[v];

        for(int i = 0; i < v; i++)
            odwiedzone[i] = false;

        lista.add(s);
        sciezka[s] = -1;

        while(lista.size() > 0){

            int u = lista.get(0);
            lista.remove(0);

            if(odwiedzone[u] == false)
                odwiedzone[u] = true;

            for(int i = 0; i < v; i++) {

                int index = -1;

                for (int j = 0; j < e; j++) {

                    if(macierz[u][j] != 0 && macierz[i][j] != 0)
                        index = j;

                }

                if (index != -1 && macierz[u][index] > 0 && odwiedzone[i] == false) {
                    lista.add(i);
                    sciezka[i] = u;
                }

            }

        }

        if(odwiedzone[t] == true)
            return true;
        else
            return false;

    }

    public void dodajKrawedz(int poczatek, int koniec, int waga){

        wierzcholekKolejka w = new wierzcholekKolejka(waga,poczatek,koniec);
        wierzcholekKolejka w2 = new wierzcholekKolejka(waga,poczatek,koniec);

        residual[poczatek].add(koniec,w);

        wszystkie[pozycja] = w2;

        macierz[poczatek][pozycja] = waga;
        macierz[koniec][pozycja] = -waga;

        macierz2[poczatek][pozycja] = waga;
        macierz2[koniec][pozycja] = -waga;

        check = true;

        pozycja++;

    }

    public void wypiszKrawedzieLista(){

        if(check == true) {

            System.out.println("GRAF SKIEROWANY");

            for (int i = 0; i < v; i++) {

                for (int j = 0; j < residual[i].size(); j++) {

                    if(residual[i].get(j).getWierzcholek() != 0 || residual[i].get(j).getKoniec() != 0 || residual[i].get(j).getWaga() != 0)
                        System.out.println("Poczatek: " + residual[i].get(j).getWierzcholek() + " Koniec: " + residual[i].get(j).getKoniec() + " Waga: " + residual[i].get(j).getWaga());

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

                    System.out.print(macierz[i][j] + "  ");

                }

                System.out.println();

            }

        }
        else{

            System.out.println("Graf jest aktualnie pusty");

        }

    }

    private void ustaw(){

        residual = new ArrayList[v];
        wszystkie = new wierzcholekKolejka[e];
        sciezka = new int[v];
        losowe = new Boolean[v][v];
        macierz = new int[v][e];
        macierz2 = new int[v][e];

        check = false;
        pozycja = 0;

        for(int i = 0; i < v; i++){

            residual[i] = new ArrayList<>();

        }

        for(int i = 0; i < v; i++){

            for(int j = 0; j < v; j++){

                residual[i].add(new wierzcholekKolejka());
                losowe[i][j] = false;

            }

        }

        for(int i = 0; i < e; i++){

            wszystkie[i] = new wierzcholekKolejka();

        }

        for(int i = 0; i < v; i++){

            for(int j = 0; j < e; j++ ){

                macierz[i][j] = 0;
                macierz2[i][j] = 0;

            }

        }

    }

    public void wyczysc(){

        residual = new ArrayList[v];

        for(int i = 0; i < v; i++){

            residual[i] = new ArrayList<>();

        }

        for(int i = 0; i < v; i++){

            for(int j = 0; j < v; j++){

                residual[i].add(new wierzcholekKolejka());

            }

        }

        for(int i = 0; i < e; i++){

            residual[wszystkie[i].getWierzcholek()].get(wszystkie[i].getKoniec()).setWaga(wszystkie[i].getWaga());
            residual[wszystkie[i].getWierzcholek()].get(wszystkie[i].getKoniec()).setWierzcholek(wszystkie[i].getWierzcholek());
            residual[wszystkie[i].getWierzcholek()].get(wszystkie[i].getKoniec()).setKoniec(wszystkie[i].getKoniec());

        }

        for(int i = 0; i < v; i++){

            for(int j = 0; j < e; j++){

                macierz[i][j] = macierz2[i][j];

            }

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

    public void AlgorytmFFBfs(int s, int t){

        int wynik = 0;
        sciezka = new int[v];

        while(bfs(s, t) == true){

            int przeplyw = Integer.MAX_VALUE;

            int tmp1 = t;
            int tmp2;

            while(tmp1 != s){

                tmp2 = sciezka[tmp1];

                if(przeplyw > residual[tmp2].get(tmp1).getWaga())
                    przeplyw = residual[tmp2].get(tmp1).getWaga();

                tmp1 = tmp2;

            }

            tmp1 = t;

            while(tmp1 != s){

                tmp2 = sciezka[tmp1];

                int waga1 = residual[tmp2].get(tmp1).getWaga();
                int waga2 = residual[tmp1].get(tmp2).getWaga();

                waga1 -= przeplyw;
                waga2 += przeplyw;

                residual[tmp2].get(tmp1).setWaga(waga1);
                residual[tmp1].get(tmp2).setWaga(waga2);

                tmp1 = tmp2;

            }

            wynik += przeplyw;

        }

        System.out.println("Maksymalny przeplyw z wierzcholka " + s + " do wierzcholka " + t + " wynosi: " + wynik);

    }

    public void AlgorytmFFDfs(int s, int t){

        int wynik = 0;
        sciezka = new int[v];

        while(dfs(s, t) == true){

            int przeplyw = Integer.MAX_VALUE;

            int tmp1 = t;
            int tmp2;

            while(tmp1 != s){

                tmp2 = sciezka[tmp1];

                if(przeplyw > residual[tmp2].get(tmp1).getWaga())
                    przeplyw = residual[tmp2].get(tmp1).getWaga();

                tmp1 = tmp2;

            }

            tmp1 = t;

            while(tmp1 != s){

                tmp2 = sciezka[tmp1];

                int waga1 = residual[tmp2].get(tmp1).getWaga();
                int waga2 = residual[tmp1].get(tmp2).getWaga();

                waga1 -= przeplyw;
                waga2 += przeplyw;

                residual[tmp2].get(tmp1).setWaga(waga1);
                residual[tmp1].get(tmp2).setWaga(waga2);

                tmp1 = tmp2;

            }

            wynik += przeplyw;

        }

        System.out.println("Maksymalny przeplyw z wierzcholka " + s + " do wierzcholka " + t + " wynosi: " + wynik);

    }

    public void AlgorytmFFMacierzBfs(int s, int t){

        int wynik = 0;
        sciezka = new int[v];

        while(bfsMacierz(s, t) == true){

            int przeplyw = Integer.MAX_VALUE;

            int tmp1 = t;
            int tmp2;

            while(tmp1 != s){

                tmp2 = sciezka[tmp1];

                int index = -1;

                for(int i = 0; i < e; i++){

                    if(macierz[tmp2][i] != 0 && macierz[tmp1][i] != 0) {
                        index = i;
                        break;
                    }

                }

                if (index != -1 && przeplyw > Math.abs(macierz[tmp2][index]))
                    przeplyw = Math.abs(macierz[tmp2][index]);

                tmp1 = tmp2;

            }

            tmp1 = t;

            while(tmp1 != s){

                tmp2 = sciezka[tmp1];

                int index = -1;

                for(int i = 0; i < e; i++){

                    if(macierz[tmp2][i] != 0 && macierz[tmp1][i] != 0) {
                        index = i;
                        break;
                    }

                }

                if(index != -1) {

                    int waga1 = macierz[tmp2][index];
                    int waga2 = macierz[tmp1][index];

                    if(waga1 < 0)
                        waga1 = 0;
                    if(waga2 < 0)
                        waga2 = 0;

                    waga1 -= przeplyw;
                    waga2 += przeplyw;

                    macierz[tmp2][index] = waga1;
                    macierz[tmp1][index] = waga2;

                }

                tmp1 = tmp2;

            }

            wynik += przeplyw;

        }

        System.out.println("Maksymalny przeplyw z wierzcholka " + s + " do wierzcholka " + t + " wynosi: " + wynik);

    }

    public void AlgorytmFFMacierzDfs(int s, int t){

        int wynik = 0;
        sciezka = new int[v];

        while(dfsMacierz(s, t) == true){

            int przeplyw = Integer.MAX_VALUE;

            int tmp1 = t;
            int tmp2;

            while(tmp1 != s){

                tmp2 = sciezka[tmp1];

                int index = -1;

                for(int i = 0; i < e; i++){

                    if(macierz[tmp2][i] != 0 && macierz[tmp1][i] != 0) {
                        index = i;
                        break;
                    }

                }

                if (index != -1 && przeplyw > Math.abs(macierz[tmp2][index]))
                    przeplyw = Math.abs(macierz[tmp2][index]);

                tmp1 = tmp2;

            }

            tmp1 = t;

            while(tmp1 != s){

                tmp2 = sciezka[tmp1];

                int index = -1;

                for(int i = 0; i < e; i++){

                    if(macierz[tmp2][i] != 0 && macierz[tmp1][i] != 0) {
                        index = i;
                        break;
                    }

                }

                if(index != -1) {

                    int waga1 = macierz[tmp2][index];
                    int waga2 = macierz[tmp1][index];

                    if(waga1 < 0)
                        waga1 = 0;
                    if(waga2 < 0)
                        waga2 = 0;

                    waga1 -= przeplyw;
                    waga2 += przeplyw;

                    macierz[tmp2][index] = waga1;
                    macierz[tmp1][index] = waga2;

                }

                tmp1 = tmp2;

            }

            wynik += przeplyw;

        }

        System.out.println("Maksymalny przeplyw z wierzcholka " + s + " do wierzcholka " + t + " wynosi: " + wynik);

    }

    public void wczytajFF(String nazwaPliku){

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