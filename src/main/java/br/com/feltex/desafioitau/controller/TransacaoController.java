package br.com.feltex.desafioitau.controller;

import br.com.feltex.desafioitau.domain.Transacao;
import br.com.feltex.desafioitau.mapper.TransacaoMapper;
import br.com.feltex.desafioitau.request.TransacaoPostRequest;
import br.com.feltex.desafioitau.service.TransacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/transacao")
@Slf4j
@RequiredArgsConstructor
public class TransacaoController {
    private final TransacaoService service;
    private final TransacaoMapper mapper;

    @PostMapping
    public ResponseEntity<Void> saveTransaction(@RequestBody @Valid TransacaoPostRequest request) {
        log.info("Requesting to save transaction: {}", request);
        Transacao transaction = mapper.toTransaction(request);
        service.save(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> clearTransactions(){
        log.info("Requesting to clear all transactions");
        service.clean();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
