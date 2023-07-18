package br.com.correios.correios.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CEP {

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private boolean erro;

    public boolean isErro() {
        return erro;
    }
}
