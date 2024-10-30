package org.poo.cb.Bani;

public class Usd extends Bani {
    String usdName;
    public Usd (UsdBuilderImpl builder) {
        this.usdName = builder.getUsdName();
        super.euro = builder.getEuro();
        super.gbp = builder.getGbp();
        super.jpy = builder.getJpy();
        super.cad = builder.getCad();
        super.usd = builder.getUsd();
    }
    public static UsdBuilder builder() {
        return new UsdBuilderImpl();
    }
}