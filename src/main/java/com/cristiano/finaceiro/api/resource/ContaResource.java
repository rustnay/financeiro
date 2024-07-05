package com.cristiano.finaceiro.api.resource;

import com.cristiano.finaceiro.api.doc.ContaDoc;
import com.cristiano.finaceiro.api.request.ContaRequest;
import com.cristiano.finaceiro.api.response.HttpResponse;
import com.cristiano.finaceiro.service.ContaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

import static com.cristiano.finaceiro.mapper.ContaMapper.fromRequestToDTO;
import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.*;

@RequestMapping("/contas")
@RestController
@RequiredArgsConstructor
public class ContaResource implements ContaDoc {

    private final ContaService service;

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<HttpResponse> findOne(@PathVariable @Valid UUID id) {

        return ResponseEntity.ok(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("conta", service.findById(id)))
                        .message("Conta recuperada")
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
                        .message("Contas recuperadas")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

    @PostMapping
    @Override
    public ResponseEntity<HttpResponse> create(@RequestBody @Valid ContaRequest request) {
        return ResponseEntity.created(URI.create(""))
                .body(
                        HttpResponse.builder()
                                .timeStamp(now().toString())
                                .data(of("conta", service.create(fromRequestToDTO(request))))
                                .message("Conta criada com sucesso")
                                .status(CREATED)
                                .statusCode(CREATED.value())
                                .build());
    }

    @PutMapping
    @Override
    public ResponseEntity<HttpResponse> update(@RequestBody @Valid ContaRequest request) {
        return ResponseEntity.ok(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("conta", service.update(fromRequestToDTO(request))))
                        .message("Conta atualizada")
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
                        .message("Conta excluída com sucesso")
                        .status(NO_CONTENT)
                        .statusCode(NO_CONTENT.value())
                        .build());
    }
}
