package br.com.feltex.desafioitau.repository;

import br.com.feltex.desafioitau.domain.Transacao;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Getter
public class TransacaoRepository {
    private final List<Transacao> transacoes = new ArrayList<>();

    public void save(Transacao transacao) {
        getTransacoes().add(transacao);
    }

    public void limpar() {
        getTransacoes().clear();
    }
}
