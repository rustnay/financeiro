package com.cristiano.finaceiro.api.resource;

import com.cristiano.finaceiro.api.doc.FormaPagamentoResourceDoc;
import com.cristiano.finaceiro.api.request.FormaPagamentoRequest;
import com.cristiano.finaceiro.api.response.HttpResponse;
import com.cristiano.finaceiro.service.FormaPagamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

import static com.cristiano.finaceiro.mapper.FormaPagamentoMapper.fromRequestToDTO;
import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.*;

@RequestMapping("/formas-pagamentos")
@RestController
@RequiredArgsConstructor
public class FormaPagamentoResource implements FormaPagamentoResourceDoc {

    private final FormaPagamentoService service;

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<HttpResponse> findOne(@PathVariable @Valid UUID id) {

        return ResponseEntity.ok(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("formaPagamento", service.findById(id)))
                        .message("Forma de pagamento recuperada")
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
                        .message("Formas de pagamentos recuperadas")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

    @PostMapping
    @Override
    public ResponseEntity<HttpResponse> create(@RequestBody @Valid FormaPagamentoRequest request) {
        return ResponseEntity.created(URI.create(""))
                .body(
                        HttpResponse.builder()
                                .timeStamp(now().toString())
                                .data(of("formaPagamento", service.create(fromRequestToDTO(request))))
                                .message("Forma de pagamento criada com sucesso")
                                .status(CREATED)
                                .statusCode(CREATED.value())
                                .build());
    }

    @PutMapping
    @Override
    public ResponseEntity<HttpResponse> update(@RequestBody @Valid FormaPagamentoRequest request) {
        return ResponseEntity.ok(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("formaPagamento", service.update(fromRequestToDTO(request))))
                        .message("Customer updated")
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
                        .message("Forma de pagamento excluída com sucesso")
                        .status(NO_CONTENT)
                        .statusCode(NO_CONTENT.value())
                        .build());
    }

    /*@RequestMapping("/error")
    public ResponseEntity<HttpResponse> handleError(HttpServletRequest request) {
        return ResponseEntity.badRequest().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .reason("There is no mapping for a " + request.getMethod() + " request for this path on the server")
                        .status(BAD_REQUEST)
                        .statusCode(BAD_REQUEST.value())
                        .build());
    }*/
}
