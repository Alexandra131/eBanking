package org.poo.cb.Bani;

public interface CadBuilder {
    CadBuilder setEuro(float euro);
    CadBuilder setGbp(float gbp);
    CadBuilder setJpy(float jpy);
    CadBuilder setCad(float cad);
    CadBuilder setUsd(float usd);
    CadBuilder setCadName(String usdName);
    Cad build();
}
