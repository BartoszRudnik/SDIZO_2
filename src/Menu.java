import java.util.Scanner;

public class Menu {

    Scanner scanner = new Scanner(System.in);

    tUI test = new tUI();
    pUI pomiary = new pUI();

    public void menu() {

        while (true) {

            System.out.println("1. Testowanie");
            System.out.println("2. Pomiary");
            System.out.println("0. Zakoncz");

            int nrOperacji = scanner.nextInt();

            switch (nrOperacji) {

                case 0:
                    System.out.println("KONIEC PROGRAMU");
                    System.exit(0);
                    break;

                case 1:
                    test = new tUI();
                    test.stworz();
                    break;

                case 2:
                    pomiary = new pUI();
                    pomiary.stworz();
                    break;

                default:
                    System.out.println("Podano zly numer");
                    break;

            }

        }

    }

}