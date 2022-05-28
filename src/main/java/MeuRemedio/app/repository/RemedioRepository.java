package MeuRemedio.app.repository;

import MeuRemedio.app.models.remedios.Remedio;
import MeuRemedio.app.models.usuarios.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface RemedioRepository extends CrudRepository<Remedio, Long>  {
    Remedio findById (long id);
    Iterable<Remedio> findAllByUsuario(Usuario usuario);
    Iterable<Remedio> findAllByUsuario(Long id);

}
