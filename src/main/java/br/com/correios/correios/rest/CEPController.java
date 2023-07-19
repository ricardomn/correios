package br.com.correios.correios.rest;

import br.com.correios.correios.model.CEP;
import br.com.correios.correios.service.CorreioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CEPController {

    @Autowired
    private CorreioService correioService;

    @GetMapping("/consulta-cep")
    public ResponseEntity<Object> consultaCEP(@RequestParam("cep") String cep) {
        String json = correioService.consultarCEPNoServicoDosCorreios(cep);

        if (json.contains("\"erro\": false")) {
            return ResponseEntity.ok().body(json);
        } else {
            return ResponseEntity.badRequest().body(json);
        }
    }


}
