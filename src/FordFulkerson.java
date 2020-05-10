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

    public void dodajKrawedz(int waga, int poczatek, int koniec){

        wierzcholekKolejka w = new wierzcholekKolejka(waga,poczatek,koniec);

        residual[poczatek].add(koniec,w);

    }

    private void ustaw(){

        for(int i = 0; i < v; i++)
            residual[i] = new ArrayList<>();


        for(int i = 0; i < v; i++){
            for(int j = 0; j < v; j++){
                residual[i].add(new wierzcholekKolejka());
            }
        }

    }

    public int AlgorytmFF(int s, int t){

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

        return wynik;

    }

}
