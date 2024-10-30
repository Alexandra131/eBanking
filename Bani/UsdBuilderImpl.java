package org.poo.cb.Bani;

public class UsdBuilderImpl implements UsdBuilder {
    private String usdName;
    private float euro, gbp, jpy, cad, usd;
    public UsdBuilder setEuro(float euro) {
        this.euro = euro;
        return this;
    }
    public float getEuro() {
        return euro;
    }


    public UsdBuilder setGbp(float gbp) {
        this.gbp = gbp;
        return this;
    }
    public float getGbp() {
        return  gbp;
    }


    public UsdBuilder setJpy(float jpy) {
        this.jpy = jpy;
        return this;
    }

    public float getJpy() {
        return jpy;
    }

    public UsdBuilder setCad(float cad) {
        this.cad = cad;
        return this;
    }

    public float getCad() {
        return cad;
    }

    public UsdBuilder setUsd(float usd) {
        this.usd = usd;
        return this;
    }

    public float getUsd() {
        return usd;
    }

    public UsdBuilder setUsdName(String jpyName) {
        this.usdName = jpyName;
        return this;
    }

    public String getUsdName() {
        return usdName;
    }
    public Usd build() {
        return new Usd(this);
    }
}
