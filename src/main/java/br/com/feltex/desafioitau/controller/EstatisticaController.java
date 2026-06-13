package br.com.feltex.desafioitau.controller;

import br.com.feltex.desafioitau.service.EstatisticaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@RestController
@RequestMapping(value = "/estatistica", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@RequiredArgsConstructor
public class EstatisticaController {
    private final EstatisticaService service;

    @GetMapping
    public ResponseEntity<Void> estatistica() {
        log.info("Calcular as estatisticas");
        int intervaloMaximoEmSegundos = 60;
        final var horaInicial = OffsetDateTime.now().minusSeconds(intervaloMaximoEmSegundos);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
