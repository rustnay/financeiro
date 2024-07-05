package com.cristiano.finaceiro.service;

import com.cristiano.finaceiro.dto.GrupoCategoriaDTO;

import java.util.List;
import java.util.UUID;

public interface GrupoCategoriaService {

    GrupoCategoriaDTO findById(UUID id);
    List<GrupoCategoriaDTO> listPageable(Integer page, Integer size);
    GrupoCategoriaDTO create(GrupoCategoriaDTO grupoCategoria);
    GrupoCategoriaDTO update(GrupoCategoriaDTO grupoCategoria);
    void delete(UUID id);
}
