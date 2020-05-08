import java.io.*;

public class Lista {

    private WezelLista glowa;

    public WezelLista getGlowa() {
        return glowa;
    }

    public void setGlowa(WezelLista glowa) {
        this.glowa = glowa;
    }

    public void dodajPoczatek(int liczba) {

        WezelLista element = new WezelLista(liczba);

        element.setPoprz(null);
        element.setNast(getGlowa());
        if (getGlowa() != null)
            getGlowa().setPoprz(element);
        setGlowa(element);

    }

    public void dodajKoniec(int liczba) {

        WezelLista element = new WezelLista(liczba);
        WezelLista pomocniczy;

        element.setNast(null);

        if (getGlowa() == null) {
            element.setPoprz(null);
            setGlowa(element);
            return;
        }

        pomocniczy = getGlowa();

        while (pomocniczy.getNast() != null) {
            pomocniczy = pomocniczy.getNast();
        }

        pomocniczy.setNast(element);
        element.setPoprz(pomocniczy);

    }

    public void dodaj(int pozycja, int liczba) {

        WezelLista element = new WezelLista(liczba);
        WezelLista pomocniczy = getGlowa();

        if (pozycja < 0)
            return;

        if (pozycja > rozmiarListy())
            pozycja = rozmiarListy();

        if (pozycja == 0)
            dodajPoczatek(liczba);
        else {
            for (int i = 1; i < pozycja; i++) {
                if (pomocniczy != null)
                    pomocniczy = pomocniczy.getNast();
                else
                    return;
            }

            element.setNast(pomocniczy.getNast());
            pomocniczy.setNast(element);
            element.setPoprz(pomocniczy);

            if (element.getNast() != null)
                element.getNast().setPoprz(element);
        }

    }

    private WezelLista usun(WezelLista glowa, WezelLista usuwany) {

        if (glowa == null || usuwany == null)
            return null;

        if (glowa == usuwany) {
            glowa.setNast(usuwany.getNast());
        }

        if (usuwany.getNast() != null)
            usuwany.getNast().setPoprz(usuwany.getPoprz());

        if (usuwany.getPoprz() != null)
            usuwany.getPoprz().setNast(usuwany.getNast());

        usuwany = null;

        return glowa;

    }

    public void usunPoczatek() {

        if (getGlowa() == null)
            return;

        if (getGlowa().getPoprz() != null)
            getGlowa().setPoprz(null);

        setGlowa(getGlowa().getNast());
    }

    public void usunKoniec() {

        if (getGlowa() == null)
            return;

        WezelLista element = getGlowa();

        if (element.getNast() == null)
            usunPoczatek();

        while (element.getNast() != null) {
            element = element.getNast();
        }

        if (null != element.getPoprz())
            element.getPoprz().setNast(null);

    }

    public void usunWskazany(int pozycja) {

        if (getGlowa() == null || pozycja < 0)
            return;

        if (pozycja == 0)
            usunPoczatek();

        WezelLista element = getGlowa();

        for (int i = 1; i <= pozycja; i++) {
            if (element != null) {
                element = element.getNast();
            } else {
                return;
            }
        }

        usun(getGlowa(), element);

    }

    public void wyswietlElement(int pozycja) {

        if (getGlowa() == null || pozycja < 0)
            return;

        WezelLista element = getGlowa();

        for (int i = 0; i < pozycja; i++) {
            if (element != null)
                element = element.getNast();
            else
                return;
        }

        System.out.println(element.getWartosc());

    }

    public boolean sprawdz(int wartosc) {

        if (getGlowa() == null)
            return false;

        WezelLista element = getGlowa();

        while (element.getNast() != null) {

            if (element.getWartosc() == wartosc)
                return true;

            element = element.getNast();

        }

        return false;

    }


    public void wyswietlLista() {

        WezelLista testElement = getGlowa();

        while (testElement != null) {
            System.out.println(testElement.getWartosc());
            testElement = testElement.getNast();
        }

    }

    public void wyswietlLista(WezelLista element) {

        while (element != null) {
            System.out.println(element.getWartosc());
            element = element.getNast();
        }

    }

    private int rozmiarListy() {

        int rozmiar = 0;
        WezelLista element = getGlowa();

        while (element != null) {
            element = element.getNast();
            rozmiar++;
        }

        return rozmiar;

    }

    public void wczytajLista(String nazwaPliku) {

        try {
            FileInputStream fstream = new FileInputStream(nazwaPliku);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            String line;
            int rozmiarPliku = 0;

            if ((line = br.readLine()) != null) {
                rozmiarPliku = Integer.parseInt(line);
            }

            for (int i = 0; i < rozmiarPliku; i++) {
                if ((line = br.readLine()) != null) {
                    dodaj(rozmiarListy(), Integer.parseInt(line));
                }
            }

            fstream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void zapiszLista(String nazwaPliku) {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(nazwaPliku));

            bw.write(Integer.toString(rozmiarListy()));
            bw.newLine();

            WezelLista element = getGlowa();

            for (int i = 0; i < rozmiarListy(); i++) {
                bw.write(Integer.toString(element.getWartosc()));
                bw.newLine();
                element = element.getNast();
            }

            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void maxLista() {

        WezelLista wezel = glowa;

        if (glowa == null)
            return;

        int max = wezel.getWartosc();

        if (wezel.getNast() != null)
            wezel = wezel.getNast();

        for (int i = 1; i < rozmiarListy(); i++) {

            if (wezel.getWartosc() > max)
                max = wezel.getWartosc();

            wezel = wezel.getNast();

        }

        System.out.println(max);

    }

    public void minLista() {

        WezelLista wezel = glowa;

        if (glowa == null)
            return;

        int min = wezel.getWartosc();

        if (wezel.getNast() != null) {
            wezel = wezel.getNast();
        }

        for (int i = 1; i < rozmiarListy(); i++) {

            if (wezel.getWartosc() < min) {
                min = wezel.getWartosc();
            }

            wezel = wezel.getNast();

        }

        System.out.println(min);

    }

    public void wyczysc() {

        int rozmiar = rozmiarListy();

        while (rozmiar > 0) {
            usunKoniec();
            rozmiar--;
        }

    }

}
