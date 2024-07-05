package com.cristiano.finaceiro.api.doc;

import com.cristiano.finaceiro.api.request.FormaPagamentoRequest;
import com.cristiano.finaceiro.api.response.HttpResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.UUID;

public interface FormaPagamentoDoc {

    ResponseEntity<HttpResponse> findOne(@PathVariable UUID id);
    ResponseEntity<HttpResponse> listPageable(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size);
    ResponseEntity<HttpResponse> create(@RequestBody FormaPagamentoRequest request);
    ResponseEntity<HttpResponse> update(@RequestBody FormaPagamentoRequest request);
    ResponseEntity<HttpResponse> delete(@PathVariable UUID id);
}
