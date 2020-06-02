import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class pFordFulkerson {

    FordFulkerson ff = new FordFulkerson();

    private boolean spr = true;

    Scanner scanner = new Scanner(System.in);

    private int iloscTestow = 200;

    public void pomiary(){

        while(spr){

            System.out.println("Wybierz operacje do przeprowadzenia pomiarow");
            System.out.println("1. Algorytm FordaFulkersona - wersja listowa (BFS)");
            System.out.println("2. Algorytm FordaFulkersona - wersja macierzowa (BFS)");
            System.out.println("3. Algorytm FordaFulkersona - wersja listowa (DFS)");
            System.out.println("4. Algorytm FordaFulkersona - wersja macierzowa (DFS)");
            System.out.println("0. Wyjscie");

            int nrOperacji = scanner.nextInt();

            System.out.println();

            int v;
            int e;

            Random r = new Random();

            switch(nrOperacji){

                case 0:
                    spr = false;
                    break;

                case 1:
                    long[] tab = new long[iloscTestow];
                    System.out.println("Podaj ilosc wierzcholkow: ");
                    v = scanner.nextInt();
                    System.out.println("Podaj gestosc: ");
                    e = scanner.nextInt();
                    if(e > 100 || e < 0){
                        System.out.println("Podano zla wartosc");
                        break;
                    }

                    for(int i = 0; i < iloscTestow; i++){

                        ff.losowyGraf(v,e);

                        int poczatek = r.nextInt(v);
                        int koniec = r.nextInt(v);

                        if(poczatek != koniec) {

                            long sTime = System.nanoTime();
                            ff.AlgorytmFFBfs(poczatek, koniec);
                            long fTime = System.nanoTime();
                            fTime -= sTime;
                            tab[i] = fTime;

                        }

                    }

                    zapisz("FordFulkersonLista.txt",tab);
                    break;

                case 2:
                    long[] tab2 = new long[iloscTestow];
                    System.out.println("Podaj ilosc wierzcholkow: ");
                    v = scanner.nextInt();
                    System.out.println("Podaj gestosc: ");
                    e = scanner.nextInt();
                    if(e > 100 || e < 0){
                        System.out.println("Podano zla wartosc");
                        break;
                    }

                    for(int i = 0; i < iloscTestow; i++) {

                        ff.losowyGraf(v,e);

                        int poczatek = r.nextInt(v);
                        int koniec = r.nextInt(v);

                        if(poczatek != koniec) {

                            long sTime = System.nanoTime();
                            ff.AlgorytmFFMacierzBfs(poczatek, koniec);
                            long fTime = System.nanoTime();
                            fTime -= sTime;
                            tab2[i] = fTime;

                        }


                    }

                    zapisz("FordFulkersonMacierz.txt",tab2);
                    break;

                case 3:
                    long[] tab3 = new long[iloscTestow];
                    System.out.println("Podaj ilosc wierzcholkow: ");
                    v = scanner.nextInt();
                    System.out.println("Podaj gestosc: ");
                    e = scanner.nextInt();
                    if(e > 100 || e < 0){
                        System.out.println("Podano zla wartosc");
                        break;
                    }

                    for(int i = 0; i < iloscTestow; i++) {

                        ff.losowyGraf(v,e);

                        int poczatek = r.nextInt(v);
                        int koniec = r.nextInt(v);

                        if(poczatek != koniec) {

                            long sTime = System.nanoTime();
                            ff.AlgorytmFFDfs(poczatek, koniec);
                            long fTime = System.nanoTime();
                            fTime -= sTime;
                            tab3[i] = fTime;

                        }


                    }

                    zapisz("FordFulkersonListaDFS.txt",tab3);
                    break;

                case 4:
                    long[] tab4 = new long[iloscTestow];
                    System.out.println("Podaj ilosc wierzcholkow: ");
                    v = scanner.nextInt();
                    System.out.println("Podaj gestosc: ");
                    e = scanner.nextInt();
                    if(e > 100 || e < 0){
                        System.out.println("Podano zla wartosc");
                        break;
                    }

                    for(int i = 0; i < iloscTestow; i++) {

                        ff.losowyGraf(v,e);

                        int poczatek = r.nextInt(v);
                        int koniec = r.nextInt(v);

                        if(poczatek != koniec) {

                            long sTime = System.nanoTime();
                            ff.AlgorytmFFMacierzDfs(poczatek, koniec);
                            long fTime = System.nanoTime();
                            fTime -= sTime;
                            tab4[i] = fTime;

                        }


                    }

                    zapisz("FordFulkersonMacierzDFS.txt",tab4);
                    break;

            }

        }

    }

    public void zapisz(String nazwa, long tab[]) {

        try {

            BufferedWriter bw = new BufferedWriter(new FileWriter(nazwa, true));

            for (int i = 0; i < tab.length; i++) {
                bw.write(Long.toString(tab[i]));
                bw.newLine();
            }

            bw.newLine();
            bw.newLine();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}


