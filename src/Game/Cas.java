package Game;

/**
 * @author "Vladimír Samojlov"
 * @class "2.E"
 */

/**
 * Třída sloužící k odpočítávání času, má stejnou funkci jako stopky
 */
public class Cas {

    private int sekunda;
    private int minuta;
    private int hodina;
    int celkovyCas;

    /**
     * Konstruktor zahrnující časové jednotky - sekundy, minuty a hodiny
     */
    public Cas(String cas) {

        String[] CelkovyCas = cas.split(":");
        hodina = Integer.parseInt(CelkovyCas[0]);
        minuta = Integer.parseInt(CelkovyCas[1]);
        sekunda = Integer.parseInt(CelkovyCas[2]);
    }

    /**
     * Změna časových jednotek.
     */
    public void Calendar() {

        sekunda++;
        celkovyCas++;

        if (sekunda == 60) {

            minuta++;
            sekunda = 0;

            if (minuta == 60) {

                hodina++;
                minuta = 0;
            }
        }
    }

    /**
     * Zformatování času
     */
    public String toString() {

        String vyslednaHodina = String.format("%02d", hodina);
        String vyslednaMinuta = String.format("%02d", minuta);
        String vyslednaSekunda = String.format("%02d", sekunda);

        return vyslednaHodina + ":" + vyslednaMinuta + ":" + vyslednaSekunda;
    }
}

