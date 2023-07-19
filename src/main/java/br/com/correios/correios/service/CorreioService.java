package br.com.correios.correios.service;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CorreioService {

    public String consultarCEPNoServicoDosCorreios(String cep){
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("https://viacep.com.br/ws/%s/json/", cep);
        return restTemplate.getForObject(url, String.class);
    }
}
