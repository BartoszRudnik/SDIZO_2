import java.util.Scanner;

public class tKruskal {

    Kruskal kruskal = new Kruskal();

    Scanner scanner = new Scanner(System.in);

    private boolean spr = true;

    public void operacje(){

        while(spr){

            System.out.println("Podaj numer operacji do testowania:");
            System.out.println("1. Wczytaj dane z pliku");
            System.out.println("2. Wygeneruj graf losowo");
            System.out.println("3. Wyswietl graf listowo i macierzowo");
            System.out.println("4. Algorytm Kruskala listowo i macierzowo z wynikami");
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
                    kruskal.wczytajKruskal(nazwaPliku);
                    System.out.println();
                    break;

                case 2:
                    System.out.println("Podaj liczbe wierzcholkow: ");
                    wartosc1 = scanner.nextInt();
                    System.out.println("Podaj gestosc(liczba calkowita z zakresu 0-100): ");
                    wartosc2 = scanner.nextInt();
                    kruskal.losowyGraf(wartosc1,wartosc2);
                    System.out.println();
                    break;

                case 3:
                    kruskal.wypiszKrawedzieLista();
                    System.out.println();
                    kruskal.wypiszKrawedzieMacierz();
                    System.out.println();
                    break;

                case 4:
                    System.out.println("Algorytm Kruskala listowo: ");
                    kruskal.AlgorytmKruskala();
                    kruskal.wypisz();
                    System.out.println();

                    System.out.println("Algorytm Kruskala macierzowo: ");
                    kruskal.AlgorytmKruskalaMacierz();
                    kruskal.wypisz();
                    System.out.println();
                    break;

            }

        }

    }

}
