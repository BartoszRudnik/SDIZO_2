public class main {

    public static void main(String args[]){

        MergeSort Merge = new MergeSort();

        int[] tab = {9,7,6,22,31};

        Merge.Sort(tab,0,4);

        for(int i = 0; i < 5; i++){
            System.out.println(tab[i]);
        }

    }

}
