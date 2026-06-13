package br.com.feltex.desafioitau.controller;

import br.com.feltex.desafioitau.service.EstatisticaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final Integer intervaloMaximoEmSegundos = 60;

    @GetMapping
    public ResponseEntity estatistica() {
        log.info("Calcular as estatisticas");
        final var horaInicial = OffsetDateTime.now().minusSeconds(intervaloMaximoEmSegundos);
        return ResponseEntity.ok(service.estatistica(horaInicial));
    }

}
