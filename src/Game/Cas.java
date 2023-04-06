package Game;

public class Cas {

    private int sekunda;
    private int minuta;
    private int hodina;
    int celkovyCas;

    public Cas(String cas) {

        String[] CelkovyCas = cas.split(":");
        hodina = Integer.parseInt(CelkovyCas[0]);
        minuta = Integer.parseInt(CelkovyCas[1]);
        sekunda = Integer.parseInt(CelkovyCas[2]);
    }

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

    public String toString() {

        String vyslednaHodina = String.format("%02d", hodina);
        String vyslednaMinuta = String.format("%02d", minuta);
        String vyslednaSekunda = String.format("%02d", sekunda);

        return vyslednaHodina + ":" + vyslednaMinuta + ":" + vyslednaSekunda;
    }
}

