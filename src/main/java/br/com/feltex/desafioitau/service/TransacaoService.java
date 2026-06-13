package br.com.feltex.desafioitau.service;

import br.com.feltex.desafioitau.domain.Transacao;
import br.com.feltex.desafioitau.repository.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class TransacaoService {
    private final TransacaoRepository repository;

    public void save(Transacao transacaoToSave) {
        validarTransacao(transacaoToSave);
        repository.save(transacaoToSave);
    }

    public void clean() {
        repository.limpar();
    }

    private void validarTransacao(Transacao transacao) {
        if (transacao == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        if (transacao.getValor().compareTo(BigDecimal.ZERO) < 0) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Valor da transação inválido!");
        }
        if (transacao.getDataHora().isAfter(OffsetDateTime.now())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Data da transação inválida");
        }
    }
}
