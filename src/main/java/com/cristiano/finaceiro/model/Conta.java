package com.cristiano.finaceiro.model;

import com.cristiano.finaceiro.enums.StatusConta;
import com.cristiano.finaceiro.enums.TipoConta;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

import static java.lang.Long.valueOf;
import static java.time.format.DateTimeFormatter.ofPattern;
import static java.util.Objects.nonNull;

@Entity
@Table(name = "tb_conta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Conta {

    public static final String ANO_MES = "YYYYMM";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private TipoConta tipo;
    private String nome;
    private String descricao;
    private StatusConta status;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    protected Long amPagamento;
    protected Long amReferencia;
    private BigDecimal valor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catetoria_id", nullable = false)
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forma_pagamento_id", nullable = false)
    private FormaPagamento formaPagamento;

    private String observacao;

    @PrePersist
    void prePersist() {
        DateTimeFormatter formatter = ofPattern(ANO_MES);
        if (nonNull(dataPagamento)) {
            amPagamento = valueOf(dataPagamento.format(formatter));
        }
        amReferencia = valueOf(dataVencimento.format(formatter));
    }

    @PreUpdate
    void preUpdate() {
        DateTimeFormatter formatter = ofPattern(ANO_MES);
        if (nonNull(dataPagamento)) {
            amPagamento = valueOf(dataPagamento.format(formatter));
        }
        amReferencia = valueOf(dataVencimento.format(formatter));
    }
}
