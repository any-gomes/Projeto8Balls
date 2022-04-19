package MeuRemedio.app.repository;

import MeuRemedio.app.model.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {
    //Usuario findByLogin(long id);
}
