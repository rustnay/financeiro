package com.cristiano.finaceiro.service.impl;

import com.cristiano.finaceiro.dto.ContaDTO;
import com.cristiano.finaceiro.exception.NotFoundException;
import com.cristiano.finaceiro.mapper.ContaMapper;
import com.cristiano.finaceiro.model.Categoria;
import com.cristiano.finaceiro.model.Conta;
import com.cristiano.finaceiro.model.FormaPagamento;
import com.cristiano.finaceiro.repository.CategoriaRepository;
import com.cristiano.finaceiro.repository.ContaRepository;
import com.cristiano.finaceiro.repository.FormaPagamentoRepository;
import com.cristiano.finaceiro.service.ContaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.cristiano.finaceiro.enums.StatusConta.ATIVO;
import static com.cristiano.finaceiro.mapper.ContaMapper.fromDTOToEntity;
import static com.cristiano.finaceiro.mapper.ContaMapper.fromEntityToDTO;
import static java.lang.String.format;


@RequiredArgsConstructor
@Service
public class ContaServiceImpl implements ContaService {

    private static final String MSG_NAO_ENCONTRADA = "Não foi encontrada conta com id: %s para atualizar.";

    private final ContaRepository repository;
    private final CategoriaRepository categoriaRepository;
    private final FormaPagamentoRepository formaPagamentoRepository;

    @Override
    public ContaDTO findById(UUID id) {
        return fromEntityToDTO(repository.findById(id).orElseThrow());
    }

    @Override
    public List<ContaDTO> listPageable(Integer page, Integer size) {

        return repository.findAll(PageRequest.of(page, size))
                .get()
                .map(ContaMapper::fromEntityToDTO)
                .toList();
    }

    @Override
    public ContaDTO create(ContaDTO contaDTO) {
        FormaPagamento formaPagamento = formaPagamentoRepository.findById(contaDTO.getFormaPagamento().getId()).orElseThrow();
        Categoria categoria = categoriaRepository.findById(contaDTO.getCategoria().getId()).orElseThrow();
        Conta conta = fromDTOToEntity(contaDTO);
        conta.setFormaPagamento(formaPagamento);
        conta.setCategoria(categoria);
        conta.setStatus(ATIVO);
        return fromEntityToDTO(repository.save(conta));
    }

    @Override
    public ContaDTO update(ContaDTO conta) {
        Conta contaRecuperada = getConta(conta.getId());

        contaRecuperada.setNome(conta.getNome());
        contaRecuperada.setDescricao(conta.getDescricao());
        return fromEntityToDTO(repository.save(contaRecuperada));
    }

    @Override
    public void delete(UUID id) {
        getConta(id);
        repository.deleteById(id);
    }

    private Conta getConta(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(format(MSG_NAO_ENCONTRADA, id)));
    }
}
