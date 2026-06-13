package br.com.feltex.desafioitau.service;

import br.com.feltex.desafioitau.domain.Transacao;
import br.com.feltex.desafioitau.repository.TransacaoRepository;
import br.com.feltex.desafioitau.response.EstatisticaResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.stream.DoubleStream;

@RequiredArgsConstructor
@Service
public class EstatisticaService {
    private final TransacaoRepository transacaoRepository;

    public EstatisticaResponse estatistica(OffsetDateTime horaInicial) {
        var transacoes = transacaoRepository.getTransacoes();

        if (transacoes.isEmpty()) {
            return new EstatisticaResponse();
        } else {

            final BigDecimal[] valoresFiltrados = transacoes.stream()
                    .filter(t -> t.getDataHora().isAfter(horaInicial) || t.getDataHora().equals(horaInicial))
                    .map(Transacao::getValor).toArray(BigDecimal[]::new);
            DoubleStream doubleStream = Arrays.stream(valoresFiltrados).mapToDouble(BigDecimal::doubleValue);
            return new EstatisticaResponse(doubleStream.summaryStatistics());
        }
    }
}
