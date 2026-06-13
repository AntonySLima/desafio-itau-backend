package br.com.feltex.desafioitau.service;

import br.com.feltex.desafioitau.domain.Transacao;
import br.com.feltex.desafioitau.repository.TransacaoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@ExtendWith(MockitoExtension.class)
class TransacaoServiceTest {
    @InjectMocks
    private TransacaoService service;
    @Mock
    private TransacaoRepository repository;

    @Test
    @DisplayName("save saves transaction when successful")
    void save_SavesTransaction_WhenSuccessful() {
        Transacao transactionToSave = Transacao.builder().valor(BigDecimal.valueOf(30.00D)).dataHora(OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.UTC)).build();

        service.save(transactionToSave);

        BDDMockito
                .verify(repository)
                .save(transactionToSave);
    }

    @Test
    @DisplayName("save throws ResponseStatusExecption 400 when transaction is null")
    void save_ThrowsBadRequest_WhenTransactionIsNull() {

        Assertions
                .assertThatThrownBy(() -> service.save(null))
                .isInstanceOf(ResponseStatusException.class);

        BDDMockito.verifyNoInteractions(repository);
    }

    @Test
    @DisplayName("save throws ResponseStatusExecption 422 when value is below zero")
    void save_ThrowsUnprocessableEntity_WhenTransactionValueIsBelowZero() {
        Transacao transactionToSave = Transacao.builder().valor(BigDecimal.valueOf(-5)).dataHora(OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.UTC)).build();

        Assertions
                .assertThatThrownBy(() -> service.save(transactionToSave))
                .isInstanceOf(ResponseStatusException.class);

        BDDMockito.verifyNoInteractions(repository);
    }

    @Test
    @DisplayName("save throws ResponseStatusExecption 422 when transaction is null")
    void save_ThrowsUnprocessableEntity_WhenTransactionDateIsInFuture() {
        Transacao transactionToSave = Transacao.builder().valor(BigDecimal.valueOf(30.00D)).dataHora(OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.UTC).plusDays(2L)).build();

        Assertions
                .assertThatThrownBy(() -> service.save(transactionToSave))
                .isInstanceOf(ResponseStatusException.class);

        BDDMockito.verifyNoInteractions(repository);
    }

    @Test
    @DisplayName("clean deletes transactions when successful")
    void clean_DeletesTransactions_WhenSuccessful() {
        service.clean();

        BDDMockito
                .verify(repository)
                .limpar();
    }
}