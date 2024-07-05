package com.cristiano.finaceiro.service;

import com.cristiano.finaceiro.dto.ContaDTO;

import java.util.List;
import java.util.UUID;

public interface ContaService {

    ContaDTO findById(UUID id);
    List<ContaDTO> listPageable(Integer page, Integer size);
    ContaDTO create(ContaDTO conta);
    ContaDTO update(ContaDTO conta);
    void delete(UUID id);
}
