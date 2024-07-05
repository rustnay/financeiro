package com.cristiano.finaceiro.api.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class CategoriaRequest {

    private UUID id;
    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;
    @NotNull
    private UUID grupoId;
}
