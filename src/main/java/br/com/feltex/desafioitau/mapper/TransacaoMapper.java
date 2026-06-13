package br.com.feltex.desafioitau.mapper;

import br.com.feltex.desafioitau.domain.Transacao;
import br.com.feltex.desafioitau.request.TransacaoPostRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TransacaoMapper {
    Transacao toTransaction(TransacaoPostRequest request);
}
