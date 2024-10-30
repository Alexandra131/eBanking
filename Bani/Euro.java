package org.poo.cb.Bani;

public class Euro extends  Bani{
    String euroName;
    public Euro(EuroBuilderImpl builder) {
        this.euroName = builder.getEuroName();
        super.euro = builder.getEuro();
        super.gbp = builder.getGbp();
        super.jpy = builder.getJpy();
        super.cad = builder.getCad();
        super.usd = builder.getUsd();
    }
    public float getEuro() {
        return super.euro;
    }
    public String toString() {
        return euroName;
    }
    public static EuroBuilder builder() {
        return new EuroBuilderImpl();
    }

}
