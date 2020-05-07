public class main {

    public static void main(String args[]) {

        Prim prim = new Prim(4);

        prim.dodajWierzcholek(10,0,1);
        prim.dodajWierzcholek(6,0,2);
        prim.dodajWierzcholek(5,0,3);
        prim.dodajWierzcholek(15,1,3);
        prim.dodajWierzcholek(4,2,3);

        prim.AlgorytmPrima();

        prim.wypiszPrim();

    }

}
