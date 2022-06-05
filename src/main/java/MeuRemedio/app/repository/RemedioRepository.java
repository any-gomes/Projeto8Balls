package MeuRemedio.app.repository;

import MeuRemedio.app.models.remedios.Remedio;
import MeuRemedio.app.models.usuarios.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RemedioRepository extends CrudRepository<Remedio, Long>  {
    Remedio findById (long id);
    List<Remedio> findAllByUsuario(Usuario usuario);
    Iterable<Remedio> findAllByUsuario(Long id);

}
