public class main {

    public static void main(String args[]) {

        FordFulkerson ff = new FordFulkerson();

        ff.wczytajFF("wejscie.txt");

        ff.wypiszKrawedzieLista();
        System.out.println();
        ff.wypiszKrawedzieMacierz();

    }

}
