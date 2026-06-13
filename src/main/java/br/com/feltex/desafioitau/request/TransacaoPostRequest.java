package br.com.feltex.desafioitau.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@ToString
public class TransacaoPostRequest {
    @NotNull(message = "Campo 'valor' é obrigatório")
    private BigDecimal valor;
    @NotNull(message = "Campo 'dataHora' é obrigatório")
    @PastOrPresent
    private OffsetDateTime dataHora;
}
