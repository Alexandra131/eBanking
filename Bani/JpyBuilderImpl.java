package org.poo.cb.Bani;

class JpyBuilderImpl implements JpyBuilder {
    private String jpyName;
    private float euro, gbp, jpy, cad, usd;

    public JpyBuilder setEuro(float euro) {
        this.euro = euro;
        return this;
    }
    public float getEuro() {
        return euro;
    }


    public JpyBuilder setGbp(float gbp) {
        this.gbp = gbp;
        return this;
    }
    public float getGbp() {
        return  gbp;
    }


    public JpyBuilder setJpy(float jpy) {
        this.jpy = jpy;
        return this;
    }

    public float getJpy() {
        return jpy;
    }

    public JpyBuilder setCad(float cad) {
        this.cad = cad;
        return this;
    }

    public float getCad() {
        return cad;
    }

    public JpyBuilder setUsd(float usd) {
        this.usd = usd;
        return this;
    }

    public float getUsd() {
        return usd;
    }

    public JpyBuilder setJpyName(String jpyName) {
        this.jpyName = jpyName;
        return this;
    }

    public String getJpyName() {
        return jpyName;
    }

    public Jpy build() {
        return new Jpy(this);
    }
}
