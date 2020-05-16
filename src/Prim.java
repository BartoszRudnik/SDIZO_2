import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Prim {

    private int v;
    private int e;

    private wierzcholekKolejka[] wierzcholek;
    private ArrayList<wierzcholekKolejka>[] lista;
    private Boolean[] odwiedzane;
    private Kopiec kolejka = new Kopiec();
    private wierzcholekKolejka[] mst;

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

    }

    private void ustaw(){

        wierzcholek = new wierzcholekKolejka[e];
        lista = new ArrayList[e];
        odwiedzane = new Boolean[e];
        mst = new wierzcholekKolejka[v];

        for(int i = 0; i < e; i++)
            lista[i] = new ArrayList<>();

        for(int i = 0; i < v; i++){

            mst[i] = new wierzcholekKolejka(-1,-1);

        }

        for(int i = 0; i < e; i++){

            odwiedzane[i] = false;
            wierzcholek[i] = new wierzcholekKolejka(Integer.MAX_VALUE,i);

        }

    }

    public void AlgorytmPrima(){

        odwiedzane[0] = true;
        wierzcholek[0].setWaga(0);

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
