package org.poo.cb.Bani;

public class CadBuilderImpl implements CadBuilder {
    private String cadName;
    private float euro, gbp, jpy, cad, usd;
    public CadBuilder setEuro(float euro) {
        this.euro = euro;
        return this;
    }
    public float getEuro() {
        return euro;
    }


    public CadBuilder setGbp(float gbp) {
        this.gbp = gbp;
        return this;
    }
    public float getGbp() {
        return  gbp;
    }


    public CadBuilder setJpy(float jpy) {
        this.jpy = jpy;
        return this;
    }

    public float getJpy() {
        return jpy;
    }

    public CadBuilder setCad(float cad) {
        this.cad = cad;
        return this;
    }

    public float getCad() {
        return cad;
    }

    public CadBuilder setUsd(float usd) {
        this.usd = usd;
        return this;
    }

    public float getUsd() {
        return usd;
    }

    public CadBuilder setCadName(String jpyName) {
        this.cadName = jpyName;
        return this;
    }

    public String getCadName() {
        return cadName;
    }
    public Cad build() {
        return new Cad(this);
    }
}
