

package MeuRemedio.app.repository;

import MeuRemedio.app.models.usuarios.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {
    Usuario findByEmail(String email);
    Usuario findBySenha(String email);
}