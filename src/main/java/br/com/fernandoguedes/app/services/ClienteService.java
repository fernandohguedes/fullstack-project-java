package br.com.fernandoguedes.app.services;


import br.com.fernandoguedes.app.domain.dtos.ClienteDTO;
import br.com.fernandoguedes.app.domain.dtos.ClienteInsertDTO;
import br.com.fernandoguedes.app.domain.entities.Cliente;
import br.com.fernandoguedes.app.repositories.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Long inserir(ClienteInsertDTO dto) {
        Cliente cliente = new ModelMapper().map(dto, Cliente.class);
        cliente = repository.save(cliente);
        return cliente.getId();
    }

    public ClienteDTO buscarPeloId(Long id) {
        Optional<Cliente> cliente = repository.findById(id);
        return new ModelMapper().map(cliente.get(), ClienteDTO.class);
    }

    public List<ClienteDTO> buscarTodos() {
        List<Cliente> clientes = repository.findAll();
        return clientes.stream().map(
                cliente -> new ModelMapper().map(cliente, ClienteDTO.class))
                .collect(Collectors.toList());
    }

}
