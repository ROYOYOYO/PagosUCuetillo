package org.openjpa.control.exceptions;

public class NoExisteEntidadException extends Exception {
    public NoExisteEntidadException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
    public NoExisteEntidadException(String mensaje) {
        super(mensaje);
    }
}
