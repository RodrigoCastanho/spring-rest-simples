package com.algaworks.osworks.domain.model;

import com.algaworks.osworks.api.model.Comentario;
import com.algaworks.osworks.domain.ValidationGroups;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

/**
 *
 * @author rodrigo castanho
 */
@Entity
public class OrdemServico {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Valid
    @ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class)
    @NotNull
    @ManyToOne
    private Cliente cliente;
    
    @NotBlank
    private String descricao;
    @NotNull
    private BigDecimal preco;
    
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Enumerated(EnumType.STRING)
    private StatusOrdemServico status;
    
    //Mudou de LocalDateTime para OffsetDateTime(deixa no padrão ISO e Offset do UTC)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dataAbertura; 
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dataFinalizacao;
    @OneToMany(mappedBy = "ordemServico")
    private List<Comentario> comentarios;
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public StatusOrdemServico getStatus() {
        return status;
    }

    public void setStatus(StatusOrdemServico status) {
        this.status = status;
    }

    public OffsetDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(OffsetDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public OffsetDateTime getDataFinalizacao() {
        return dataFinalizacao;
    }

    public void setDataFinalizacao(OffsetDateTime dataFinalizacao) {
        this.dataFinalizacao = dataFinalizacao;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
    
    public boolean podeSerFinalizada() {
       return StatusOrdemServico.ABERTA.equals(getStatus());
    }
    
    public boolean naoPodeSerFinalizada() {
       return !podeSerFinalizada();
    }
    
    public void finalizar() {
       if(naoPodeSerFinalizada()) {
           try {
               throw new Exception("Ordem de serviço não pode ser finalizada");
           } catch (Exception ex) {
               Logger.getLogger(OrdemServico.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
       
       setStatus(StatusOrdemServico.FINALIZADA);
       setDataFinalizacao(OffsetDateTime.now());
   
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrdemServico other = (OrdemServico) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

  
}
