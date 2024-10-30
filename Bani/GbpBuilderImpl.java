package org.poo.cb.Bani;

public class GbpBuilderImpl implements GbpBuilder {
    private String gbpName;
    private float euro, gbp, jpy, cad, usd;
    public GbpBuilder setEuro(float euro) {
        this.euro = euro;
        return this;
    }
    public float getEuro() {
        return euro;
    }


    public GbpBuilder setGbp(float gbp) {
        this.gbp = gbp;
        return this;
    }
    public float getGbp() {
        return  gbp;
    }


    public GbpBuilder setJpy(float jpy) {
        this.jpy = jpy;
        return this;
    }

    public float getJpy() {
        return jpy;
    }

    public GbpBuilder setCad(float cad) {
        this.cad = cad;
        return this;
    }

    public float getCad() {
        return cad;
    }

    public GbpBuilder setUsd(float usd) {
        this.usd = usd;
        return this;
    }

    public float getUsd() {
        return usd;
    }

    public GbpBuilder setGbpName(String gbpName) {
        this.gbpName = gbpName;
        return this;
    }

    public String getGbpName() {
        return gbpName;
    }
    public Gbp build() {
        return new Gbp(this);
    }
}
