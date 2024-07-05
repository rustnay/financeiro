package com.cristiano.finaceiro.mapper;

import com.cristiano.finaceiro.api.request.GrupoCategoriaRequest;
import com.cristiano.finaceiro.dto.GrupoCategoriaDTO;
import com.cristiano.finaceiro.model.GrupoCategoria;

public class GrupoCategoriaMapper {

    public static GrupoCategoriaDTO fromRequestToDTO(GrupoCategoriaRequest request) {

        return GrupoCategoriaDTO.builder()
                .id(request.getId())
                .nome(request.getNome())
                .descricao(request.getDescricao())
                .build();
    }

    public static GrupoCategoriaDTO fromEntityToDTO(GrupoCategoria entity) {

        return GrupoCategoriaDTO.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .descricao(entity.getDescricao())
                .build();
    }
    public static GrupoCategoria fromDTOToEntity(GrupoCategoriaDTO dto) {

        GrupoCategoria grupoCategoria = new GrupoCategoria();
        grupoCategoria.setId(dto.getId());
        grupoCategoria.setNome(dto.getNome());
        grupoCategoria.setDescricao(dto.getDescricao());
        return grupoCategoria;
    }
}
