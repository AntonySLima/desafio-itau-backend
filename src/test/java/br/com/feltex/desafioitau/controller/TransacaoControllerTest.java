package br.com.feltex.desafioitau.controller;

import br.com.feltex.desafioitau.commons.FileUtils;
import br.com.feltex.desafioitau.domain.Transacao;
import br.com.feltex.desafioitau.service.TransacaoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(controllers = TransacaoController.class)
@ComponentScan(basePackages = "br.com.feltex.desafioitau")
class TransacaoControllerTest {
    private static final String URL = "/transacao";
    @MockBean
    private TransacaoService service;
    @Autowired
    private FileUtils fileUtils;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("POST /transacao creates an transaction")
    void save_CreatesTransaction_WhenSuccessful() throws Exception {
        var request = fileUtils.readResourceFile("transacao/post-request-transaction-200.json");

        mockMvc.perform(MockMvcRequestBuilders
                        .post(URL)
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());

        BDDMockito.verify(service).save(any(Transacao.class));
    }

    @Test
    @DisplayName("DELETE /transacao creates an transaction")
    void clearTransactions_DeleteAllTransactions_WhenSuccessful() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .delete(URL)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        BDDMockito.verify(service).clean();
    }
}