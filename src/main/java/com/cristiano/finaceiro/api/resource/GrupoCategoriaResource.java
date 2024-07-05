package com.cristiano.finaceiro.api.resource;

import com.cristiano.finaceiro.api.doc.GrupoCategoriaDoc;
import com.cristiano.finaceiro.api.request.GrupoCategoriaRequest;
import com.cristiano.finaceiro.api.response.HttpResponse;
import com.cristiano.finaceiro.service.GrupoCategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

import static com.cristiano.finaceiro.mapper.GrupoCategoriaMapper.fromRequestToDTO;
import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.*;

@RequestMapping("/grupos-categorias")
@RestController
@RequiredArgsConstructor
public class GrupoCategoriaResource implements GrupoCategoriaDoc {

    private final GrupoCategoriaService service;

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<HttpResponse> findOne(@PathVariable @Valid UUID id) {

        return ResponseEntity.ok(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("grupoCategoria", service.findById(id)))
                        .message("Grupo recuperado")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

    @GetMapping("/list")
    @Override
    public ResponseEntity<HttpResponse> listPageable(@RequestParam Optional<Integer> page,
                                                     @RequestParam Optional<Integer> size) {
        return ResponseEntity.ok(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("page", service.listPageable(page.orElse(0), size.orElse(10))))
                        .message("Grupos recuperados")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

    @PostMapping
    @Override
    public ResponseEntity<HttpResponse> create(@RequestBody @Valid GrupoCategoriaRequest request) {
        return ResponseEntity.created(URI.create(""))
                .body(
                        HttpResponse.builder()
                                .timeStamp(now().toString())
                                .data(of("grupoCategoria", service.create(fromRequestToDTO(request))))
                                .message("Grupo criado com sucesso")
                                .status(CREATED)
                                .statusCode(CREATED.value())
                                .build());
    }

    @PutMapping
    @Override
    public ResponseEntity<HttpResponse> update(@RequestBody @Valid GrupoCategoriaRequest request) {
        return ResponseEntity.ok(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("grupoCategoria", service.update(fromRequestToDTO(request))))
                        .message("Grupo atualizado")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<HttpResponse> delete(@PathVariable @Valid UUID id) {

        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .message("Grupo excluído com sucesso")
                        .status(NO_CONTENT)
                        .statusCode(NO_CONTENT.value())
                        .build());
    }
}
