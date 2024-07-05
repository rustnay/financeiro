package com.cristiano.finaceiro.service;

import com.cristiano.finaceiro.dto.FormaPagamentoDTO;

import java.util.List;
import java.util.UUID;

public interface FormaPagamentoService {

    FormaPagamentoDTO findById(UUID id);
    List<FormaPagamentoDTO> listPageable(Integer page, Integer size);
    FormaPagamentoDTO create(FormaPagamentoDTO formaPagamento);
    FormaPagamentoDTO update(FormaPagamentoDTO formaPagamentoDTO);
    void delete(UUID id);
}
