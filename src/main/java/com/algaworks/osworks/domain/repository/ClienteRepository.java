package com.algaworks.osworks.domain.repository;

import com.algaworks.osworks.domain.model.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rodrigo castanho
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
    List<Cliente> findByNome(String nome);
    List<Cliente> findByNomeContaining(String nome);
    Cliente findByEmail(String email);
    
}
