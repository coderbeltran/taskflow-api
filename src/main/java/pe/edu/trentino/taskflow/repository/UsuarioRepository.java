package pe.edu.trentino.taskflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.trentino.taskflow.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
