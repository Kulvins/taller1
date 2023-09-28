package usm.hdd.Taller1.services;

import usm.hdd.Taller1.entities.Cliente;

import java.util.List;

public interface ClientesService {
    Cliente crear(Cliente cliente);
    List<Cliente> listar();
    List<Cliente> filtrar(String filtro);
}
