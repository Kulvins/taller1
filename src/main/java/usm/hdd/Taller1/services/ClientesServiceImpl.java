package usm.hdd.Taller1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usm.hdd.Taller1.entities.Cliente;
import usm.hdd.Taller1.repository.ClientesRepository;

import java.util.List;

@Service
public class ClientesServiceImpl implements ClientesService{
    @Autowired
    ClientesRepository clientesRepository;
    @Override
    public Cliente crear(Cliente cliente) {
        return clientesRepository.save(cliente);
    }

    @Override
    public List<Cliente> listar() {
        return clientesRepository.findAll();
    }

    @Override
    public List<Cliente> filtrar(String estado) {
        return clientesRepository.findAllByEstado(estado);
    }
}
