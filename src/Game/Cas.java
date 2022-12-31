package Game;

public class Cas {

    private int sekunda = 0;
    private int minuta = 0;
    private int hodina = 0;

    public Cas(String cas) {

        String[] CelkovyCas = cas.split(":");
        hodina = Integer.parseInt(CelkovyCas[0]);
        minuta = Integer.parseInt(CelkovyCas[1]);
        sekunda = Integer.parseInt(CelkovyCas[2]);
    }

    public void Calendar() {

        sekunda++;

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

        if (hodina < 10 && minuta < 10 && sekunda < 10)

            return "0" + hodina + ":0" + minuta + ":0" + sekunda;

        if (hodina < 10 && minuta < 10 && sekunda >= 10)

            return "0" + hodina + ":0" + minuta + ":" + sekunda;

        if (hodina < 10 && minuta >= 10 && sekunda < 10)

            return "0" + hodina + ":" + minuta + ":0" + sekunda;

        if (hodina < 10 && minuta >= 10 && sekunda >= 10)

            return "0" + hodina + ":" + minuta + ":" + sekunda;

        if (hodina >= 10 && minuta < 10 && sekunda < 10)

            return hodina + ":0" + minuta + ":0" + sekunda;

        if (hodina >= 10 && minuta < 10 && sekunda >= 10)

            return hodina + ":0" + minuta + ":" + sekunda;

        if (hodina >= 10 && minuta >= 10 && sekunda < 10)

            return hodina + ":" + minuta + ":0" + sekunda;

        if (hodina >= 10 && minuta >= 10 && sekunda >= 10)

            return hodina + ":" + minuta + ":" + sekunda;

        return null;
    }
}

