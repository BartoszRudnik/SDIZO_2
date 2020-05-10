public class main {

    public static void main(String args[]) {

        FordFulkerson ff = new FordFulkerson(6,8);

        ff.dodajKrawedz(7,0,1);
        ff.dodajKrawedz(8,0,2);
        ff.dodajKrawedz(2,1,2);
        ff.dodajKrawedz(5,1,3);
        ff.dodajKrawedz(10,2,4);
        ff.dodajKrawedz(2,3,4);
        ff.dodajKrawedz(3,3,5);
        ff.dodajKrawedz(12,4,5);

        System.out.println(ff.dfs(2,3));
        System.out.println(ff.AlgorytmFF(0,5));

    }

}
