package com.cristiano.finaceiro.service;

import com.cristiano.finaceiro.dto.CategoriaDTO;

import java.util.List;
import java.util.UUID;

public interface CategoriaService {

    CategoriaDTO findById(UUID id);
    List<CategoriaDTO> listPageable(Integer page, Integer size);
    CategoriaDTO create(CategoriaDTO categoria);
    CategoriaDTO update(CategoriaDTO categoria);
    void delete(UUID id);
}
