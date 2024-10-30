package org.poo.cb.Bani;

public class EuroBuilderImpl implements EuroBuilder {
    private String euroName;
    private float euro, gbp, jpy, cad, usd;
    public EuroBuilder setEuro(float euro) {
        this.euro = euro;
        return this;
    }
    public float getEuro() {
        return euro;
    }


    public EuroBuilder setGbp(float gbp) {
        this.gbp = gbp;
        return this;
    }
    public float getGbp() {
        return  gbp;
    }


    public EuroBuilder setJpy(float jpy) {
        this.jpy = jpy;
        return this;
    }

    public float getJpy() {
        return jpy;
    }

    public EuroBuilder setCad(float cad) {
        this.cad = cad;
        return this;
    }

    public float getCad() {
        return cad;
    }

    public EuroBuilder setUsd(float usd) {
        this.usd = usd;
        return this;
    }

    public float getUsd() {
        return usd;
    }

    public EuroBuilder setEuroName(String euroName) {
        this.euroName = euroName;
        return this;
    }

    public String getEuroName() {
        return euroName;
    }
    public Euro build() {
       return new Euro(this);
    }
}
