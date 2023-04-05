package Game;

import java.io.Serializable;

public class Vysledek implements Serializable {

    private String JmenoObrazku;
    private String level;
    private String cas;
    private Integer napovedaUsed;
    private Integer skore;

    public Vysledek(String JmenoObrazku, String level, String cas, Integer napovedaUsed, Integer skore) {

        this.JmenoObrazku = JmenoObrazku;
        this.level = level;
        this.cas = cas;
        this.napovedaUsed = napovedaUsed;
        this.skore = skore;
    }

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