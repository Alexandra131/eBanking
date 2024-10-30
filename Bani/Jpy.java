package org.poo.cb.Bani;

public class Jpy extends Bani {
    String jpyName;
    public Jpy(JpyBuilderImpl builder) {
        this.jpyName = builder.getJpyName();
        this.euro = builder.getEuro();
        this.gbp = builder.getGbp();
        this.jpy = builder.getJpy();
        this.cad = builder.getCad();
        this.usd = builder.getUsd();
    }

    public static JpyBuilder builder() {
        return new JpyBuilderImpl();
    }
}
