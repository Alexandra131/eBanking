package org.poo.cb.Bani;

public class Cad extends Bani {
    String cadName;
    public Cad(CadBuilderImpl builder) {
        this.cadName = builder.getCadName();;
        super.euro = builder.getEuro();;
        super.gbp = builder.getGbp();
        super.jpy = builder.getJpy();
        super.cad = builder.getCad();
        super.usd = builder.getUsd();
    }
    public static CadBuilder builder() {
        return new CadBuilderImpl();
    }
}