package org.poo.cb.Bani;

public class Gbp extends Bani {
    String gbpName;
    public Gbp(GbpBuilderImpl builder) {
        this.gbpName = builder.getGbpName();
        super.euro = builder.getEuro();
        super.gbp = builder.getGbp();
        super.jpy = builder.getJpy();
        super.cad = builder.getCad();
        super.usd = builder.getUsd();
    }

    public static GbpBuilder builder() {
        return new GbpBuilderImpl();
    }
}
