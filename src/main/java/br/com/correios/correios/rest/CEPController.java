package br.com.correios.correios.rest;

import br.com.correios.correios.model.CEP;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CEPController {

    @GetMapping("/consulta-cep")
    public ResponseEntity<Object> consultaCEP(@RequestParam("cep") String cep) {
        // Lógica para consultar o CEP (substitua pelo seu código real)
        String json = consultarCEPNoServicoDosCorreios(cep);

        // Verifica se o CEP é válido ou inválido
        if (json.contains("\"erro\": false")) {
            // Retorna 200 OK para CEP válido
            return ResponseEntity.ok().body(json);
        } else {
            // Retorna 400 Bad Request para CEP inválido
            return ResponseEntity.badRequest().body(json);
        }
    }

    private String consultarCEPNoServicoDosCorreios(String cep){
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("https://viacep.com.br/ws/%s/json/", cep);
        return restTemplate.getForObject(url, String.class);
    }
}
