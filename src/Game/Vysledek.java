package Game;

import java.io.Serializable;

/**
 * @author "Vladimír Samojlov"
 * @class "2.E"
 */

/**
 * Třída zapisující jednotlivá data do tabulky po skončení hry
 */
public class Vysledek implements Serializable {

    private String JmenoObrazku;
    private String level;
    private String cas;
    private Integer napovedaUsed;
    private Integer skore;

    /**
     * Konstruktor třídy Vysledek
     */
    public Vysledek(String JmenoObrazku, String level, String cas, Integer napovedaUsed, Integer skore) {

        this.JmenoObrazku = JmenoObrazku;
        this.level = level;
        this.cas = cas;
        this.napovedaUsed = napovedaUsed;
        this.skore = skore;
    }

    /**
     * Metody pro zapisování informací do tabulky
     */
    public String getJmenoObrazku() {
        return JmenoObrazku;
    }

    public String getLevel() {
        return level;
    }

    public String getCas() {
        return cas;
    }

    public Integer getNapovedaUsed() {
        return napovedaUsed;
    }

    public Integer getSkore() {
        return skore;
    }

    /**
     * Metoda pro vypsání jednotlivých informací
     */
    @Override
    public String toString() {
        return "Vysledek{" +
                "JmenoObrazku='" + JmenoObrazku + '\'' +
                ", level='" + level + '\'' +
                ", cas='" + cas + '\'' +
                ", napovedaUsed=" + napovedaUsed +
                ", skore=" + skore +
                '}';
    }
}