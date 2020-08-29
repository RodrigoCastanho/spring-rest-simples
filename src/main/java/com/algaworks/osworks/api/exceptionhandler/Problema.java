package com.algaworks.osworks.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

/**
 *
 * @author rodrigo castanho
 */
@JsonInclude(Include.NON_NULL) //Não exibi campos null, na requisição
public class Problema {
    
  private Integer statu;
  private OffsetDateTime dataHora; 
  private String titulo;
  private List<Campo> campos;
  
  public static class Campo {
    private String nome;
    private String mensagem;
    
     public Campo(String nome, String mensagem) {
            this.nome = nome;
            this.mensagem = mensagem;
     }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getMensagem() {
            return mensagem;
        }

        public void setMensagem(String mensagem) {
            this.mensagem = mensagem;
        }
    
  }

    public Integer getStatu() {
        return statu;
    }

    public void setStatu(Integer statu) {
        this.statu = statu;
    }

    public OffsetDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(OffsetDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Campo> getCampos() {
        return campos;
    }

    public void setCampos(List<Campo> campos) {
        this.campos = campos;
    }
    
    
   
}
