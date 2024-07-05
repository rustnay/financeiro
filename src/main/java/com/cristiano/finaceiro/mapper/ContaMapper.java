package com.cristiano.finaceiro.mapper;

import com.cristiano.finaceiro.api.request.ContaRequest;
import com.cristiano.finaceiro.dto.CategoriaDTO;
import com.cristiano.finaceiro.dto.ContaDTO;
import com.cristiano.finaceiro.dto.FormaPagamentoDTO;
import com.cristiano.finaceiro.dto.GrupoCategoriaDTO;
import com.cristiano.finaceiro.model.Conta;

public class ContaMapper {

    public static ContaDTO fromRequestToDTO(ContaRequest request) {

        return ContaDTO.builder()
                .id(request.getId())
                .nome(request.getNome())
                .descricao(request.getDescricao())
                .dataPagamento(request.getDataPagamento())
                .dataVencimento(request.getDataVencimento())
                .valor(request.getValor())
                .status(request.getStatus())
                .tipo(request.getTipo())
                .observacao(request.getObservacao())
                .formaPagamento(FormaPagamentoDTO.builder()
                        .id(request.getFormaPagamentoId())
                        .build())
                .categoria(CategoriaDTO.builder()
                        .id(request.getCategoriaId())
                        .build())
                .build();
    }

    public static ContaDTO fromEntityToDTO(Conta entity) {

        return ContaDTO.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .descricao(entity.getDescricao())
                .dataPagamento(entity.getDataPagamento())
                .dataVencimento(entity.getDataVencimento())
                .amPagamento(entity.getAmPagamento())
                .amReferencia(entity.getAmReferencia())
                .valor(entity.getValor())
                .status(entity.getStatus())
                .tipo(entity.getTipo())
                .observacao(entity.getObservacao())
                .formaPagamento(FormaPagamentoDTO.builder()
                        .id(entity.getFormaPagamento().getId())
                        .nome(entity.getFormaPagamento().getNome())
                        .descricao(entity.getFormaPagamento().getDescricao())
                        .build())
                .categoria(CategoriaDTO.builder()
                        .id(entity.getCategoria().getId())
                        .nome(entity.getCategoria().getNome())
                        .descricao(entity.getCategoria().getDescricao())
                        .grupo(GrupoCategoriaDTO
                                .builder()
                                .id(entity.getCategoria().getGrupo().getId())
                                .nome(entity.getCategoria().getGrupo().getNome())
                                .descricao(entity.getCategoria().getGrupo().getDescricao())
                                .build())
                        .build())
                .build();
    }
    public static Conta fromDTOToEntity(ContaDTO dto) {

        Conta conta = new Conta();
        conta.setId(dto.getId());
        conta.setNome(dto.getNome());
        conta.setDescricao(dto.getDescricao());
        conta.setDataVencimento(dto.getDataVencimento());
        conta.setDataPagamento(dto.getDataPagamento());
        conta.setAmPagamento(dto.getAmPagamento());
        conta.setAmReferencia(dto.getAmReferencia());
        conta.setValor(dto.getValor());
        conta.setStatus(dto.getStatus());
        conta.setTipo(dto.getTipo());
        conta.setObservacao(dto.getObservacao());
        return conta;
    }
}
