package br.fiap.assistencia.controller;

import br.fiap.assistencia.dto.ClienteRequest;
import br.fiap.assistencia.dto.ClienteResponse;
import br.fiap.assistencia.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController // se colocar @Controler é caso tenha html
@RequestMapping("/cliente") // mapeamento que vai vir a partir de uma requisição
@AllArgsConstructor
public class ClienteController {
    private final ClienteService service;

    @PostMapping("/salvar") //para as informações sensíveis não ficarem aparentes no cabeçalho
    public ClienteResponse salvar(@RequestBody ClienteRequest dto) {
        return service.salvar(dto);
    };

    @GetMapping("/pesquisar/{id}")
    public ClienteResponse pesquisar(@PathVariable Long id) {
        return service.pesquisar(id);
    }

}
