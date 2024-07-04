package com.cristiano.finaceiro.service.impl;

import com.cristiano.finaceiro.dto.FormaPagamentoDTO;
import com.cristiano.finaceiro.mapper.FormaPagamentoMapper;
import com.cristiano.finaceiro.model.FormaPagamento;
import com.cristiano.finaceiro.repository.FormaPagamentoRepository;
import com.cristiano.finaceiro.service.FormaPagamentoService;
import com.cristiano.finaceiro.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.cristiano.finaceiro.mapper.FormaPagamentoMapper.fromDTOToEntity;
import static com.cristiano.finaceiro.mapper.FormaPagamentoMapper.fromEntityToDTO;
import static java.lang.String.format;

@RequiredArgsConstructor
@Service
public class FormaPagamentoServiceImpl implements FormaPagamentoService {


    public static final String MSG_NAO_ENCONTRADA = "Não foi encontrada forma de pagamento com id: %s para atualizar.";
    private final FormaPagamentoRepository repository;


    @Override
    public FormaPagamentoDTO findById(UUID id) {
        return fromEntityToDTO(repository.findById(id).orElseThrow());
    }

    @Override
    public List<FormaPagamentoDTO> listPageable(Integer page, Integer size) {

       return repository.findAll(PageRequest.of(page, size))
               .get()
               .map(FormaPagamentoMapper::fromEntityToDTO)
               .toList();
    }

    @Override
    public FormaPagamentoDTO create(FormaPagamentoDTO formaPagamento) {
        return fromEntityToDTO(repository.save(fromDTOToEntity(formaPagamento)));
    }

    @Override
    public FormaPagamentoDTO update(FormaPagamentoDTO formaPagamento) {
        FormaPagamento formaPagamentoRecuperada = repository.findById(formaPagamento.getId())
                .orElseThrow(() -> new NotFoundException(format(MSG_NAO_ENCONTRADA, formaPagamento.getId())));

        formaPagamentoRecuperada.setNome(formaPagamento.getNome());
        formaPagamentoRecuperada.setDescricao(formaPagamento.getDescricao());
        return fromEntityToDTO(repository.save(formaPagamentoRecuperada));
    }
}
