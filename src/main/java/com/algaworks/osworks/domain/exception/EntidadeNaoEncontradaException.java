package com.algaworks.osworks.domain.exception;

/**
 *
 * @author rodrigo castanho
 */
public class EntidadeNaoEncontradaException extends NegocioException {
    
    private static final long serialVersionUID = 1L;
    
    public EntidadeNaoEncontradaException(String message) {
        super(message);
    }
      
}
