public class MergeSort {

    private void Merge(wierzcholekKolejka[] wierzcholek, int p, int q, int r){

        int tab1_size = q - p + 1;
        int tab2_size = r - q;

        wierzcholekKolejka[] tab1 = new wierzcholekKolejka[tab1_size];
        wierzcholekKolejka[] tab2 = new wierzcholekKolejka[tab2_size];

        for(int i = 0; i < tab1_size; i++)
            tab1[i] = wierzcholek[p + i];

        for(int i = 0; i < tab2_size; i++)
            tab2[i] = wierzcholek[q + i + 1];

        int i = 0;
        int j = 0;
        int index = p;

        while( i < tab1_size && j < tab2_size){

            if(tab1[i].getWaga() > tab2[j].getWaga()){

                wierzcholek[index] = tab2[j];
                j++;
                index++;

            }
            else{

                wierzcholek[index] = tab1[i];
                i++;
                index++;

            }

        }

        if(i < tab1_size){

            while(i < tab1_size){

                wierzcholek[index] = tab1[i];
                index++;
                i++;

            }

        }
        else {

            while(j < tab2_size){

                wierzcholek[index] = tab2[j];
                index++;
                j++;

            }

        }

    }

    private void Sort(wierzcholekKolejka[] wierzcholek, int p, int r){

        if(p < r) {

            int q = (p + r)/2;

            Sort(wierzcholek, p, q);
            Sort(wierzcholek, q + 1, r);
            Merge(wierzcholek, p, q, r);

        }

    }

    public void Sortuj(wierzcholekKolejka[] wierzcholek){

        int p = 0;
        int r = wierzcholek.length  - 1;

        Sort(wierzcholek, p, r);

    }

}
