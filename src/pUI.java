import java.util.Scanner;

public class pUI {

    private pKruskal kruskal = new pKruskal();
    private pPrim prim = new pPrim();
    private pDijkstra dijkstra = new pDijkstra();
    private pBellmanFord bf = new pBellmanFord();
    private pFordFulkerson ff = new pFordFulkerson();

    Scanner scanner = new Scanner(System.in);

    private boolean spr = true;

    public void stworz(){

        while(spr){

            System.out.println("Wybierz algorytm do przeprowadzenia pomiarow");
            System.out.println("1. Algorytm Kruskala");
            System.out.println("2. Algorytm Prima");
            System.out.println("3. Algorytm Dijkstry");
            System.out.println("4. Algorytm Bellmana-Forda");
            System.out.println("5. Algorytm Forda-Fulkersona");
            System.out.println("0. Wyjscie");

            int nrAlg = scanner.nextInt();

            switch(nrAlg){

                case 0:
                    spr = false;
                    break;

                case 1:
                    kruskal = new pKruskal();
                    kruskal.pomiary();
                    break;

                case 2:
                    prim = new pPrim();
                    prim.pomiary();
                    break;

                case 3:
                    dijkstra = new pDijkstra();
                    dijkstra.pomiary();
                    break;

                case 4:
                    bf = new pBellmanFord();
                    bf.pomiary();
                    break;

                case 5:
                    ff = new pFordFulkerson();
                    ff.pomiary();
                    break;

            }

        }

    }

}
