package com.cristiano.finaceiro.service.impl;

import com.cristiano.finaceiro.dto.GrupoCategoriaDTO;
import com.cristiano.finaceiro.exception.NotFoundException;
import com.cristiano.finaceiro.mapper.GrupoCategoriaMapper;
import com.cristiano.finaceiro.model.GrupoCategoria;
import com.cristiano.finaceiro.repository.GrupoCategoriaRepository;
import com.cristiano.finaceiro.service.GrupoCategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.cristiano.finaceiro.mapper.GrupoCategoriaMapper.fromDTOToEntity;
import static com.cristiano.finaceiro.mapper.GrupoCategoriaMapper.fromEntityToDTO;
import static java.lang.String.format;

@RequiredArgsConstructor
@Service
public class GrupoCategoriaServiceImpl implements GrupoCategoriaService {

    private static final String MSG_NAO_ENCONTRADA = "Não foi encontrado um grupo com id: %s para atualizar.";
    private final GrupoCategoriaRepository repository;


    @Override
    public GrupoCategoriaDTO findById(UUID id) {
        return fromEntityToDTO(repository.findById(id).orElseThrow());
    }

    @Override
    public List<GrupoCategoriaDTO> listPageable(Integer page, Integer size) {

       return repository.findAll(PageRequest.of(page, size))
               .get()
               .map(GrupoCategoriaMapper::fromEntityToDTO)
               .toList();
    }

    @Override
    public GrupoCategoriaDTO create(GrupoCategoriaDTO grupoCategoria) {
        return fromEntityToDTO(repository.save(fromDTOToEntity(grupoCategoria)));
    }

    @Override
    public GrupoCategoriaDTO update(GrupoCategoriaDTO grupoCategoria) {
        GrupoCategoria grupoCategoriaRecuperada = getGrupoCategoria(grupoCategoria.getId());

        grupoCategoriaRecuperada.setNome(grupoCategoria.getNome());
        grupoCategoriaRecuperada.setDescricao(grupoCategoria.getDescricao());
        return fromEntityToDTO(repository.save(grupoCategoriaRecuperada));
    }

    @Override
    public void delete(UUID id) {
        getGrupoCategoria(id);
        repository.deleteById(id);
    }

    private GrupoCategoria getGrupoCategoria(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(format(MSG_NAO_ENCONTRADA, id)));
    }
}
