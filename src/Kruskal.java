public class Kruskal {

    private int v;
    private int e;

    private Wierzcholek wierzcholek[];

    public Kruskal(int v, int e){

        this.v = v;
        this.e = e;

        wierzcholek = new Wierzcholek[e];

        for(Wierzcholek w: wierzcholek){
            w = new Wierzcholek();
        }

    }

}
