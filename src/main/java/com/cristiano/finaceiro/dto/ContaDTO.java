package com.cristiano.finaceiro.dto;

import com.cristiano.finaceiro.enums.StatusConta;
import com.cristiano.finaceiro.enums.TipoConta;
import com.cristiano.finaceiro.model.Categoria;
import com.cristiano.finaceiro.model.FormaPagamento;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
public class ContaDTO {

    private UUID id;
    private TipoConta tipo;
    private String nome;
    private String descricao;
    private StatusConta status;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private Long amPagamento;
    private Long amReferencia;
    private BigDecimal valor;
    private CategoriaDTO categoria;
    private FormaPagamentoDTO formaPagamento;
    private String observacao;
}
