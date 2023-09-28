package usm.hdd.Taller1.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import usm.hdd.Taller1.entities.Cliente;

import java.util.List;

public interface ClientesRepository extends MongoRepository<Cliente,String> {
    @Query(value="{estado:'?0'}")
    List<Cliente> findAllByEstado(String estado);

    public long count();

}
