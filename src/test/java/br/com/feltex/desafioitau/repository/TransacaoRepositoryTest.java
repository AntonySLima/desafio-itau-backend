package br.com.feltex.desafioitau.repository;

import br.com.feltex.desafioitau.domain.Transacao;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

class TransacaoRepositoryTest {
    private TransacaoRepository repository;

    @BeforeEach
    void init() {
        repository = new TransacaoRepository();
    }

    @Test
    @DisplayName("save adds a transaction when successful")
    void save_AddsTransaction_WhenSuccessful() {
        Transacao transactionToSave = Transacao.builder().valor(BigDecimal.valueOf(30.00D)).dataHora(OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.UTC)).build();

        repository.save(transactionToSave);

        Assertions
                .assertThat(repository.getTransacoes())
                .contains(transactionToSave);
    }

    @Test
    @DisplayName("limpar deletes all transactions when successful")
    void limpar_DeleteAllTransactions_WhenSuccessful() {
        Transacao transactionToSave = Transacao.builder().valor(BigDecimal.valueOf(30.00D)).dataHora(OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.UTC)).build();

        repository.save(transactionToSave);
        repository.limpar();

        Assertions
                .assertThat(repository.getTransacoes())
                .isEmpty();
    }
}