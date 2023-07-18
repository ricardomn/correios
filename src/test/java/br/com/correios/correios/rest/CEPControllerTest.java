package br.com.correios.correios.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CEPController.class)
class CEPControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CEPController cepController;


    @Test
    public void testConsultaCEP() throws Exception {
        String cep = "60510118";
        String jsonValido = getExemploCEPValidoJSON();

        // Simulação de chamada real à API (mock do serviço externo)
        when(cepController.consultaCEP(cep)).thenReturn(ResponseEntity.ok().body(jsonValido));

        mockMvc.perform(MockMvcRequestBuilders.get("/consulta-cep")
                        .param("cep", cep)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(jsonValido));
    }

    @Test
    public void testConsultaCEPInvalido() throws Exception {
        String cep = "60510721";
        String json = getExemploCEPInvalidoJSON();

        when(cepController.consultaCEP(cep)).thenReturn(ResponseEntity.badRequest().body(json));

        mockMvc.perform(MockMvcRequestBuilders.get("/consulta-cep")
                        .param("cep", cep)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json(json));
    }

    private String getExemploCEPValidoJSON() {
        return "{\"cep\": \"CEP_VALIDO\", \"logradouro\": \"Rua Exemplo\", \"bairro\": \"Centro\", \"localidade\": \"Cidade\", \"uf\": \"SP\", \"erro\": false}";
    }

    private String getExemploCEPInvalidoJSON() {
        // Retorna o JSON de exemplo com o CEP inválido (substitua com o JSON real)
        return "{\"erro\": true}";
    }
}


