package MeuRemedio.app.repository;

import MeuRemedio.app.model.EmailModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository <EmailModel, Long>{
}
