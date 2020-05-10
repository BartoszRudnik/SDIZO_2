import java.util.ArrayList;

public class FordFulkerson {

    private int v;
    private int e;

    private ArrayList<wierzcholekKolejka>[] residual;
    private int[] sciezka;
    private boolean[] odwiedzone;
    private ArrayList<Integer> lista;

    public FordFulkerson(int v, int e){

        this.v = v;
        this.e = e;

        residual = new ArrayList[v];
        sciezka = new int[v];

        ustaw();

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

            for (int i = 0; i < residual[u].size(); i++) {

                if (residual[u].get(i).getWaga() > 0 && odwiedzone[residual[u].get(i).getKoniec()] == false) {

                    lista.add(residual[u].get(i).getKoniec());
                    sciezka[i] = u;
                    odwiedzone[residual[u].get(i).getKoniec()] = true;

                }

            }

        }

        if (odwiedzone[t] == true)
            return true;
         else
           return false;

    }

    public void dodajKrawedz(int waga, int poczatek, int koniec){

        wierzcholekKolejka w = new wierzcholekKolejka(waga,poczatek,koniec);

        residual[poczatek].add(w);

    }

    private void ustaw(){

        for(int i = 0; i < v; i++)
            residual[i] = new ArrayList<>();


        for(int i = 0; i < v; i++){
            residual[i].add(new wierzcholekKolejka(0,i));
        }

    }

    public int AlgorytmFF(int s, int t){

        for(int i = 0; i < v; i++){

            for(int j = residual[i].size(); j < v; j++)
                residual[i].add(j,new wierzcholekKolejka());

        }

        int wynik = 0;
        sciezka = new int[v];

        while(dfs(s,t) == true){

            int maks_przeplyw = Integer.MAX_VALUE;
            int t1 = t;

            while(t1 != s){

                int tmp = sciezka[t1];

                if(maks_przeplyw > residual[tmp].get(t1).getWaga())
                    maks_przeplyw = residual[tmp].get(t1).getWaga();

                t1 = tmp;

            }

            t1 = t;

            while(t1 != s){

                int tmp = sciezka[t1];

                int waga1 = residual[tmp].get(t1).getWaga();
                int waga2 = residual[t1].get(tmp).getWaga();

                waga1 -= maks_przeplyw;
                waga2 += maks_przeplyw;

                residual[tmp].get(t1).setWaga(waga1);
                residual[t1].get(tmp).setWaga(waga2);

                t1 = tmp;

            }

            wynik += maks_przeplyw;

        }

        return wynik;

    }

}
