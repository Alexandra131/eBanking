package org.poo.cb.Bani;

public interface JpyBuilder {
    JpyBuilder setEuro(float euro);
    JpyBuilder setGbp(float gbp);
    JpyBuilder setJpy(float jpy);
    JpyBuilder setCad(float cad);
    JpyBuilder setUsd(float usd);
    JpyBuilder setJpyName(String jpyName);
    Jpy build();
}
