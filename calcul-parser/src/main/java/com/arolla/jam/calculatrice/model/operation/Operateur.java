package com.arolla.jam.calculatrice.model.operation;

public enum Operateur {
    MULTIPLICATION("*"),
    DIVISION("/"),
    ADDITION("+"),
    SOUSTRACTION("-");

    private final String sign;

    Operateur(final String sign) {
        this.sign=sign;
    }

    public String signe() {
        return sign;
    }

}
