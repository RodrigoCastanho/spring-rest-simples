package com.algaworks.osworks.servico;

import com.algaworks.osworks.domain.exception.NegocioException;
import com.algaworks.osworks.api.model.Comentario;
import com.algaworks.osworks.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.osworks.domain.model.Cliente;
import com.algaworks.osworks.domain.model.OrdemServico;
import com.algaworks.osworks.domain.model.StatusOrdemServico;
import com.algaworks.osworks.domain.repository.ClienteRepository;
import com.algaworks.osworks.domain.repository.ComentarioRespository;
import com.algaworks.osworks.domain.repository.OrdemServicoRepository;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author rodrigo castanho
 */
@Service
public class GestaoOrdemServicoService {
    
    @Autowired
    private OrdemServicoRepository ordemServicoRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private ComentarioRespository comentarioRespository;
            
    public OrdemServico criar(OrdemServico ordemServico) { 
        Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
                .orElseThrow(() -> new NegocioException("Cliente não encontrado")); 
            
        ordemServico.setCliente(cliente);
        ordemServico.setStatus(StatusOrdemServico.ABERTA);
        ordemServico.setDataAbertura(OffsetDateTime.now());

        return ordemServicoRepository.save(ordemServico);
    }
    
    public void finalizar(Long ordemServicoId) { 
        OrdemServico ordemServico = buscar(ordemServicoId);
        ordemServico.finalizar();
        
        ordemServicoRepository.save(ordemServico);
    
    }
    
    public Comentario adicionarComentario(Long ordemServicoId, String descricao) {
        OrdemServico ordemServico = buscar(ordemServicoId);
        
        Comentario comentario = new Comentario();  
        comentario.setDataEnvio(OffsetDateTime.now());
        comentario.setDescricao(descricao);
        comentario.setOrdemServico(ordemServico);

        return comentarioRespository.save(comentario);
        
    }
    
    private OrdemServico buscar(Long ordemServicoId) {
         return ordemServicoRepository.findById(ordemServicoId)
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de servico não encontrada"));  
    }
    
    
    
    
   
    
}
