package br.com.fernandoguedes.config;


import br.com.fernandoguedes.app.domain.entities.Cliente;
import br.com.fernandoguedes.app.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private ClienteRepository clienteRepository;

    public void instantiateTestDatabase() {
        Cliente cliente1 = new Cliente(1l, "Claudio");
        Cliente cliente2 = new Cliente(2l, "Marcos");
        Cliente cliente3 = new Cliente(3l, "José");

        clienteRepository.saveAll(Arrays.asList(cliente1, cliente2, cliente3));
    }
}
