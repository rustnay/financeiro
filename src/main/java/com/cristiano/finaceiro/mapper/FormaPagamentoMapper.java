package com.cristiano.finaceiro.mapper;

import com.cristiano.finaceiro.api.request.FormaPagamentoRequest;
import com.cristiano.finaceiro.dto.FormaPagamentoDTO;
import com.cristiano.finaceiro.model.FormaPagamento;

public class FormaPagamentoMapper {

    public static FormaPagamentoDTO fromRequestToDTO(FormaPagamentoRequest request) {

        return FormaPagamentoDTO.builder()
                .id(request.getId())
                .nome(request.getNome())
                .descricao(request.getDescricao())
                .build();
    }

    public static FormaPagamentoDTO fromEntityToDTO(FormaPagamento entity) {

        return FormaPagamentoDTO.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .descricao(entity.getDescricao())
                .build();
    }
    public static FormaPagamento fromDTOToEntity(FormaPagamentoDTO dto) {

        FormaPagamento formaPagamento = new FormaPagamento();
        formaPagamento.setId(dto.getId());
        formaPagamento.setNome(dto.getNome());
        formaPagamento.setDescricao(dto.getDescricao());
        return formaPagamento;
    }
}
