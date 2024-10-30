package org.poo.cb.Bani;


    public class BaniFactory {
        private static BaniFactory instantaUnica;
        public static BaniFactory Instanta() {
            if (instantaUnica == null) {
                instantaUnica = new BaniFactory();
            }
            return instantaUnica;
        }

        public Bani puneBani(String baniTip, float[] date) {
            switch (baniTip) {
                case "EUR":
                    return Euro.builder()
                                    .setEuroName(baniTip)
                                    .setEuro(date[1])
                                    .setGbp(date[2])
                                    .setJpy(date[3])
                                    .setCad(date[4])
                                    .setUsd(date[5])
                                    .build();
                case "GBP":
                    return Gbp.builder()
                                    .setGbpName(baniTip)
                                    .setEuro(date[1])
                                    .setGbp(date[2])
                                    .setJpy(date[3])
                                    .setCad(date[4])
                                    .setUsd(date[5])
                                    .build();
                case "JPY":
                    return Jpy.builder()
                                .setJpyName(baniTip)
                                .setEuro(date[1])
                                .setGbp(date[2])
                                .setJpy(date[3])
                                .setCad(date[4])
                                .setUsd(date[5])
                                .build();
                case "CAD":
                    return Cad.builder()
                                .setCadName(baniTip)
                                .setEuro(date[1])
                                .setGbp(date[2])
                                .setJpy(date[3])
                                .setCad(date[4])
                                .setUsd(date[5])
                                .build();
                case "USD":
                    return Usd.builder()
                                    .setUsdName(baniTip)
                                    .setEuro(date[1])
                                    .setGbp(date[2])
                                    .setJpy(date[3])
                                    .setCad(date[4])
                                    .setUsd(date[5])
                                    .build();
                default:
                    return null;
            }
        }
    }

