public class MergeSort {

    private void Merge(int[] tab, int p, int q, int r){

        int tab1_size = q - p + 1;
        int tab2_size = r - q;

        int[] tab1 = new int[tab1_size];
        int[] tab2 = new int[tab2_size];

        for(int i = 0; i < tab1_size; i++)
            tab1[i] = tab[p + i];

        for(int i = 0; i < tab2_size; i++)
            tab2[i] = tab[q + i + 1];

        int i = 0;
        int j = 0;
        int index = p;

        while( i < tab1_size && j < tab2_size){

            if(tab1[i] > tab2[j]){

                tab[index] = tab2[j];
                j++;
                index++;

            }
            else{

                tab[index] = tab1[i];
                i++;
                index++;

            }

        }

        if(i < tab1_size){

            while(i < tab1_size){

                tab[index] = tab1[i];
                index++;
                i++;

            }

        }
        else {

            while(j < tab2_size){

                tab[index] = tab2[j];
                index++;
                j++;

            }

        }

    }

    public void Sort(int[] tab, int p, int r){

        if(p < r) {

            int q = (p + r)/2;

            Sort(tab, p, q);
            Sort(tab, q + 1, r);
            Merge(tab, p, q, r);

        }

    }


}
