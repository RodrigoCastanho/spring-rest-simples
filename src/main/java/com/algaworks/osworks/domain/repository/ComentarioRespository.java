package com.algaworks.osworks.domain.repository;

import com.algaworks.osworks.api.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rodrigo castanho
 */
@Repository
public interface ComentarioRespository extends JpaRepository<Comentario, Long> {
    
}
