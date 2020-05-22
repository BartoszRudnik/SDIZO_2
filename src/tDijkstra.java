import java.util.Scanner;

public class tDijkstra {

    Dijkstra dijkstra = new Dijkstra();

    Scanner scanner = new Scanner(System.in);

    private boolean spr = true;

    public void operacje(){

        while(spr){

            System.out.println("Podaj numer operacji do testowania:");
            System.out.println("1. Wczytaj dane z pliku");
            System.out.println("2. Wygeneruj graf losowo");
            System.out.println("3. Wyswietl graf listowo");
            System.out.println("4. Wyswietl graf macierzowo");
            System.out.println("5. Algorytm Dijkstry listowo z wynikami");
            System.out.println("6. Algorytm Dijkstry macierzowo z wynikami");
            System.out.println("0. Wyjscie");

            int nrOperacji = scanner.nextInt();

            int wartosc1,wartosc2;

            System.out.println();

            String nazwaPliku;

            switch (nrOperacji){

                case 0:
                    spr = false;
                    break;

                case 1:
                    System.out.println("Podaj nazwe pliku: ");
                    nazwaPliku = scanner.nextLine();
                    dijkstra.wczytajDijkstra(nazwaPliku);
                    System.out.println();
                    break;

                case 2:
                    System.out.println("Podaj liczbe wierzcholkow: ");
                    wartosc1 = scanner.nextInt();
                    System.out.println("Podaj gestosc(liczba calkowita z zakresu 0-100): ");
                    wartosc2 = scanner.nextInt();
                    dijkstra.losowyGraf(wartosc1,wartosc2);
                    System.out.println();
                    break;

                case 3:
                    dijkstra.wypiszKrawedzieLista();
                    System.out.println();
                    break;

                case 4:
                    dijkstra.wypiszKrawedzieMacierz();
                    System.out.println();
                    break;

                case 5:
                    System.out.println("Podaj wierzcholek startowy: ");
                    wartosc1 = scanner.nextInt();
                    System.out.println("Podaj wierzcholek koncowy: ");
                    wartosc2 = scanner.nextInt();
                    dijkstra.AlgorytmDijkstra(wartosc1,wartosc2);
                    dijkstra.wypiszWynik(wartosc1,wartosc2);
                    System.out.println();
                    break;

                case 6:
                    System.out.println();
                    break;

            }

        }

    }

}
