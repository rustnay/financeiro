package com.cristiano.finaceiro.mapper;

import com.cristiano.finaceiro.api.request.CategoriaRequest;
import com.cristiano.finaceiro.dto.CategoriaDTO;
import com.cristiano.finaceiro.dto.GrupoCategoriaDTO;
import com.cristiano.finaceiro.model.Categoria;
import com.cristiano.finaceiro.model.GrupoCategoria;

public class CategoriaMapper {

    public static CategoriaDTO fromRequestToDTO(CategoriaRequest request) {

        return CategoriaDTO.builder()
                .id(request.getId())
                .nome(request.getNome())
                .descricao(request.getDescricao())
                .grupo(GrupoCategoriaDTO.builder()
                        .id(request.getGrupoId())
                        .build())
                .build();
    }

    public static CategoriaDTO fromEntityToDTO(Categoria entity) {

        return CategoriaDTO.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .descricao(entity.getDescricao())
                .grupo(GrupoCategoriaDTO.builder()
                        .id(entity.getGrupo().getId())
                        .nome(entity.getGrupo().getNome())
                        .descricao(entity.getGrupo().getDescricao())
                        .build())
                .build();
    }
    public static Categoria fromDTOToEntity(CategoriaDTO dto) {

        Categoria categoria = new Categoria();
        categoria.setId(dto.getId());
        categoria.setNome(dto.getNome());
        categoria.setDescricao(dto.getDescricao());
        return categoria;
    }
}
