package com.cristiano.finaceiro.api.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class FormaPagamentoRequest {

    private UUID id;
    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;
}
