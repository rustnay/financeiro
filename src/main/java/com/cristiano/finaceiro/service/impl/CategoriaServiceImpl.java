package com.cristiano.finaceiro.service.impl;

import com.cristiano.finaceiro.dto.CategoriaDTO;
import com.cristiano.finaceiro.dto.GrupoCategoriaDTO;
import com.cristiano.finaceiro.exception.NotFoundException;
import com.cristiano.finaceiro.mapper.CategoriaMapper;
import com.cristiano.finaceiro.model.Categoria;
import com.cristiano.finaceiro.model.GrupoCategoria;
import com.cristiano.finaceiro.repository.CategoriaRepository;
import com.cristiano.finaceiro.repository.GrupoCategoriaRepository;
import com.cristiano.finaceiro.service.CategoriaService;
import com.cristiano.finaceiro.service.GrupoCategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.cristiano.finaceiro.mapper.CategoriaMapper.fromDTOToEntity;
import static com.cristiano.finaceiro.mapper.CategoriaMapper.fromEntityToDTO;
import static java.lang.String.format;


@RequiredArgsConstructor
@Service
public class CategoriaServiceImpl implements CategoriaService {

    private static final String MSG_NAO_ENCONTRADA = "Não foi encontrada categoria com id: %s para atualizar.";
    private final CategoriaRepository repository;
    private final GrupoCategoriaRepository grupoCategoriaRepository;

    @Override
    public CategoriaDTO findById(UUID id) {
        return fromEntityToDTO(repository.findById(id).orElseThrow());
    }

    @Override
    public List<CategoriaDTO> listPageable(Integer page, Integer size) {

        return repository.findAll(PageRequest.of(page, size))
                .get()
                .map(CategoriaMapper::fromEntityToDTO)
                .toList();
    }

    @Override
    public CategoriaDTO create(CategoriaDTO categoriaDTO) {

        GrupoCategoria grupo = grupoCategoriaRepository.findById(categoriaDTO.getGrupo().getId()).orElseThrow();
        Categoria categoria = fromDTOToEntity(categoriaDTO);
        categoria.setGrupo(grupo);
        return fromEntityToDTO(repository.save(categoria));
    }

    @Override
    public CategoriaDTO update(CategoriaDTO categoria) {
        Categoria categoriaRecuperada = getCategoria(categoria.getId());

        categoriaRecuperada.setNome(categoria.getNome());
        categoriaRecuperada.setDescricao(categoria.getDescricao());
        return fromEntityToDTO(repository.save(categoriaRecuperada));
    }

    @Override
    public void delete(UUID id) {
        getCategoria(id);
        repository.deleteById(id);
    }

    private Categoria getCategoria(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(format(MSG_NAO_ENCONTRADA, id)));
    }
}
