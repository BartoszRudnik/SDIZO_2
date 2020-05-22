import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class pPrim {

    Prim prim = new Prim();

    private boolean spr = true;

    Scanner scanner = new Scanner(System.in);

    private int iloscTestow = 200;

    public void pomiary(){

        while(spr){

            System.out.println("Wybierz operacje do przeprowadzenia pomiarow");
            System.out.println("1. Algorytm Prima - wersja listowa");
            System.out.println("2. Algorytm Prima - wersja macierzowa");
            System.out.println("0. Wyjscie");

            int nrOperacji = scanner.nextInt();

            System.out.println();

            int v;
            int e;

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

                    for(int i = 0; i < iloscTestow; i++){

                        prim.losowyGraf(v,e);

                        long sTime = System.nanoTime();
                        prim.AlgorytmPrima();
                        long fTime = System.nanoTime();
                        fTime -= sTime;
                        tab[i] = fTime;

                    }

                    zapisz("PrimLista.txt",tab);
                    break;

                case 2:
                    long[] tab2 = new long[iloscTestow];
                    System.out.println("Podaj ilosc wierzcholkow: ");
                    v = scanner.nextInt();
                    System.out.println("Podaj gestosc: ");
                    e = scanner.nextInt();

                    for(int i = 0; i < iloscTestow; i++) {

                        prim.losowyGraf(v,e);

                        long sTime = System.nanoTime();
                        //prim.AlgorytmPrimaMacierz();
                        long fTime = System.nanoTime();
                        fTime -= sTime;
                        tab2[i] = fTime;

                    }

                    zapisz("PrimMacierz.txt",tab2);
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


