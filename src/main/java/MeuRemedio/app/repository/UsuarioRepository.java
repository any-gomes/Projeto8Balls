package MeuRemedio.app.repository;

import MeuRemedio.app.models.usuarios.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    Usuario findByEmail(String email);
}
