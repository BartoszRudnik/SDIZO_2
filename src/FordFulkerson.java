import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class FordFulkerson {

    private int v;
    private int e;

    private boolean check;

    private ArrayList<wierzcholekKolejka>[] residual;
    private int[] sciezka;
    private boolean[] odwiedzone;
    private ArrayList<Integer> lista;
    private Boolean[][] losowe;

    public FordFulkerson(int v, int e){

        this.v = v;
        this.e = e;

        ustaw();

    }

    public FordFulkerson(){

    }

    public boolean dfs(int s, int t){

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

    public void dodajKrawedz(int poczatek, int koniec, int waga){

        wierzcholekKolejka w = new wierzcholekKolejka(waga,poczatek,koniec);

        residual[poczatek].add(koniec,w);

        check = true;

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

            int[][] macierz = new int[v][e];
            int pomoc = 0;

            for(int i = 0; i < v; i++){

                for(int j = 0; j < residual[i].size(); j++){

                    if(residual[i].get(j).getWierzcholek() != 0 || residual[i].get(j).getKoniec() != 0 || residual[i].get(j).getWaga() != 0) {
                        macierz[i][pomoc] = 1;
                        macierz[residual[i].get(j).getKoniec()][pomoc] = -1;
                        pomoc++;
                    }

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

    private void ustaw(){

        residual = new ArrayList[v];
        sciezka = new int[v];
        losowe = new Boolean[v][v];

        check = false;

        for(int i = 0; i < v; i++){

            residual[i] = new ArrayList<>();

        }

        for(int i = 0; i < v; i++){

            for(int j = 0; j < v; j++){

                residual[i].add(new wierzcholekKolejka());
                losowe[i][j] = false;

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

    public void AlgorytmFF(int s, int t){

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