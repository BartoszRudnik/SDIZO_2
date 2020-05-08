public class main {

    public static void main(String args[]) {

        Dijkstra dijkstra = new Dijkstra(5);

        dijkstra.dodajKrawedz(5,0,1);
        dijkstra.dodajKrawedz(7,0,2);
        dijkstra.dodajKrawedz(9,0,3);
        dijkstra.dodajKrawedz(3,1,4);
        dijkstra.dodajKrawedz(2,3,4);
        dijkstra.dodajKrawedz(1,2,3);

        dijkstra.AlgorytmDijkstra();

        dijkstra.wypisz();

    }

}
