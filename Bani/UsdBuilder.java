package org.poo.cb.Bani;

public interface UsdBuilder {
    UsdBuilder setEuro(float euro);
    UsdBuilder setGbp(float gbp);
    UsdBuilder setJpy(float jpy);
    UsdBuilder setCad(float cad);
    UsdBuilder setUsd(float usd);
    UsdBuilder setUsdName(String usdName);
    Usd build();
}
