import java.util.Scanner;

public class tUI {

    private tKruskal kruskal = new tKruskal();
    private tPrim prim = new tPrim();
    private tDijkstra dijkstra = new tDijkstra();
    private tBellmanFord bf = new tBellmanFord();
    private tFordFulkerson ff = new tFordFulkerson();

    private boolean spr = true;

    public void stworz() {

        Scanner scanner = new Scanner(System.in);

        while (spr) {

            System.out.println("Wybierz algorytm do testowania:");
            System.out.println("1. Algorytm Kruskala");
            System.out.println("2. Algorytm Prima");
            System.out.println("3. Algorytm Dijkstry");
            System.out.println("4. Algorytm Bellmana-Forda");
            System.out.println("5. Algorytm Forda-Fulkersona");
            System.out.println("0. Wyjdz");

            int nrAlg = scanner.nextInt();

            switch (nrAlg) {

                case 0:
                    spr = false;
                    break;

                case 1:
                    kruskal = new tKruskal();
                    kruskal.operacje();
                    break;

                case 2:
                    prim = new tPrim();
                    prim.operacje();
                    break;

                case 3:
                    dijkstra = new tDijkstra();
                    dijkstra.operacje();
                    break;

                case 4:
                    bf = new tBellmanFord();
                    bf.operacje();
                    break;

                case 5:
                    ff = new tFordFulkerson();
                    ff.operacje();
                    break;

                default:
                    System.out.println("Wybrano zly numer.");
                    break;

            }

        }

    }

}