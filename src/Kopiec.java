import java.io.*;

public class Kopiec {

    private wierzcholekKolejka[] kolejka;
    private int index;
    private int rozmiar;

    private boolean sprawdz = false;

    public Kopiec() {

        rozmiar = 3;
        index = 1;
        kolejka = new wierzcholekKolejka[rozmiar + 1];

    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setRozmiar(int rozmiar) {
        this.rozmiar = rozmiar;
    }

    public int getIndex() {
        return index;
    }

    public int getRozmiar() {
        return rozmiar;
    }

    private void zwiekszIndex() {
        index++;
    }

    private void zmniejszIndex() {
        index--;
    }

    private int indexRodzic(int pozycja) {
        return (pozycja / 2);
    }

    private int indexLDziecko(int pozycja) {
        return (2 * pozycja);
    }

    private int indexPDziecko(int pozycja) {
        return (2 * pozycja + 1);
    }

    private void kopcuj(int pozycja) {

        int l = indexLDziecko(pozycja);
        int p = indexPDziecko(pozycja);
        int min;

        if (l < getRozmiar() && kolejka[l].getWaga() < kolejka[pozycja].getWaga()) {
            min = l;
        } else
            min = pozycja;

        if (p < getRozmiar() && kolejka[p].getWaga() < kolejka[min].getWaga()) {
            min = p;
        }

        if (min != pozycja) {

            wierzcholekKolejka pomoc;

            pomoc = kolejka[pozycja];

            kolejka[pozycja] = kolejka[min];
            kolejka[min] = pomoc;

            kopcuj(min);

        }

    }

    private void budujKopiec() {

        setRozmiar(getIndex());
        for (int i = getRozmiar() / 2; i >= 1; i--) {
            kopcuj(i);
        }

    }

    public void dodaj(wierzcholekKolejka wierzcholek) {

        if (getIndex() >= getRozmiar()) {
            powiekszKopiec();
        }

        kolejka[index] = wierzcholek;
        zwiekszIndex();
        budujKopiec();

    }

    public void usun(wierzcholekKolejka wierzcholek) {

        zmniejszKopiec(wierzcholek);
        if (sprawdz == true)
            zmniejszIndex();
        budujKopiec();

    }

    public void usunKorzen() {

        zmniejszKopiec(kolejka[1]);
        zmniejszIndex();
        budujKopiec();

    }

    public wierzcholekKolejka minWierzcholek(){

        return kolejka[1];

    }

    public void wyswietlKopiec() {

        for (int i = 1; i < getRozmiar(); i++) {
            System.out.println(kolejka[i].getWierzcholek() + " " + kolejka[i].getKoniec() + " " + kolejka[i].getWaga());
        }

    }

    private void powiekszKopiec() {

        wierzcholekKolejka pomocnicza[] = new wierzcholekKolejka[getRozmiar() + 1];

        for (int i = 1; i < getRozmiar(); i++) {

            pomocnicza[i] = kolejka[i];

        }

        setRozmiar(getRozmiar() + 1);

        kolejka = pomocnicza;

    }

    private void zmniejszKopiec(wierzcholekKolejka wierzcholek) {

        wierzcholekKolejka pomocnicza[] = new wierzcholekKolejka[getRozmiar() - 1];
        sprawdz = false;

        for (int i = 1; i < getRozmiar(); i++) {

            if (kolejka[i] == wierzcholek) {

                sprawdz = true;

                for (int j = 1; j < i; j++) {
                    pomocnicza[j] = kolejka[j];
                }

                for (int k = (i + 1); k < getRozmiar(); k++) {
                    pomocnicza[k - 1] = kolejka[k];
                }

            }

        }

        if (sprawdz == true) {
            setRozmiar(getRozmiar() - 1);
            kolejka = pomocnicza;
        } else
            return;

    }

}