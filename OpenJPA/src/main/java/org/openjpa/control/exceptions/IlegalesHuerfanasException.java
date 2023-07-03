package org.openjpa.control.exceptions;

import java.util.ArrayList;
import java.util.List;

public class IlegalesHuerfanasException extends Exception {
    
    private final List<String> mensajes;
    
    public IlegalesHuerfanasException(List<String> mensajes) {
        super((mensajes != null && !mensajes.isEmpty() ? mensajes.get(0) : null));
        if (mensajes == null) {
            this.mensajes = new ArrayList<>();
        }
        else {
            this.mensajes = mensajes;
        }
    }
    
    public List<String> getMensajes() {
        return mensajes;
    }
}
