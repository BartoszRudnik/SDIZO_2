public class FordFulkerson {

    private int v;
    private int e;

    private wierzcholekKolejka[][] wierzcholek;
    private wierzcholekKolejka[] residual;

    public FordFulkerson(int v, int e){

        this.v = v;
        this.e = e;

        residual = new wierzcholekKolejka[e];
        wierzcholek = new wierzcholekKolejka[v][e];

        ustaw();

    }

    private void dfs(){

    }

    public void dodajKrawedz(int waga, int poczatek, int koniec){

        wierzcholekKolejka w = new wierzcholekKolejka(waga,poczatek,koniec);

        wierzcholek[poczatek][koniec] = w;

    }

    private void ustaw(){

        for(int i = 0; i < residual.length; i++)
            residual[i] = new wierzcholekKolejka();

        for(int i = 0; i < v; i++){

            for(int j = 0; j < e; j++)
                wierzcholek[i][j] = new wierzcholekKolejka();

        }

    }

    public void AlgorytmFF(){

    }

}
