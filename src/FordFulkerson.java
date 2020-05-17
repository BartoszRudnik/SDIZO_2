import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class FordFulkerson {

    private int v;
    private int e;

    private int index = 0;

    private ArrayList<wierzcholekKolejka>[] residual;
    private int[] sciezka;
    private boolean[] odwiedzone;
    private ArrayList<Integer> lista;
    private wierzcholekKolejka[] krawedzie;
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

        krawedzie[index].setWierzcholek(poczatek);
        krawedzie[index].setKoniec(koniec);
        krawedzie[index].setWaga(waga);

        index++;

    }

    public void wypiszKrawedzie(){

        if(index != 0) {

            System.out.println("GRAF SKIEROWANY");

            for (int i = 0; i < e; i++) {

                System.out.println("Poczatek: " + krawedzie[i].getWierzcholek() + " Koniec: " + krawedzie[i].getKoniec() + " Waga: " + krawedzie[i].getWaga());

            }

        }
        else {

            System.out.println("Graf jest aktualnie pusty");

        }

    }

    private void ustaw(){

        residual = new ArrayList[v];
        sciezka = new int[e];
        krawedzie = new wierzcholekKolejka[e];
        losowe = new Boolean[v][v];

        index  = 0;

        for(int i = 0; i < e; i++) {

            krawedzie[i] = new wierzcholekKolejka();

        }

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
        sciezka = new int[e];

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
