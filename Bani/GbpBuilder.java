package org.poo.cb.Bani;

public interface GbpBuilder {
    GbpBuilder setEuro(float euro);
    GbpBuilder setGbp(float gbp);
    GbpBuilder setJpy(float jpy);
    GbpBuilder setCad(float cad);
    GbpBuilder setUsd(float usd);
    GbpBuilder setGbpName(String usdName);
    Gbp build();
}
