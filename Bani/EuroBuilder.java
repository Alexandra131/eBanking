package org.poo.cb.Bani;

public interface EuroBuilder {
    EuroBuilder setEuro(float euro);
    EuroBuilder setGbp(float gbp);
    EuroBuilder setJpy(float jpy);
    EuroBuilder setCad(float cad);
    EuroBuilder setUsd(float usd);
    EuroBuilder setEuroName(String usdName);
    Euro build();
}
