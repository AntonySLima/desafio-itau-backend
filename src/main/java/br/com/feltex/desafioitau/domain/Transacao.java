package br.com.feltex.desafioitau.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Builder
@EqualsAndHashCode
public class Transacao {
    private BigDecimal valor;
    private OffsetDateTime dataHora;
}
