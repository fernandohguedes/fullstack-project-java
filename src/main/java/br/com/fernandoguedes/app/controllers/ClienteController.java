package br.com.fernandoguedes.app.controllers;


import br.com.fernandoguedes.app.domain.dtos.ClienteDTO;
import br.com.fernandoguedes.app.domain.dtos.ClienteInsertDTO;
import br.com.fernandoguedes.app.services.ClienteService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
@Api(tags = "Clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @PostMapping
    public ResponseEntity<String> inserir(@RequestBody @Valid ClienteInsertDTO dto) {
        Long id = service.inserir(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarPeloId(@PathVariable Long id) {
        ClienteDTO dto = service.buscarPeloId(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping()
    public ResponseEntity<List<ClienteDTO>> buscarTodos() {
        return ResponseEntity.ok().body(service.buscarTodos());
    }
}
