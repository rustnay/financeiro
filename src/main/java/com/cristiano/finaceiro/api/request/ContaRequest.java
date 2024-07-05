package com.cristiano.finaceiro.api.request;

import com.cristiano.finaceiro.enums.StatusConta;
import com.cristiano.finaceiro.enums.TipoConta;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;


@Getter
@Setter
@Builder
public class ContaRequest {

    private UUID id;
    private TipoConta tipo;
    private String nome;
    private String descricao;
    private StatusConta status;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private BigDecimal valor;
    private UUID categoriaId;
    private UUID formaPagamentoId;
    private String observacao;
}
