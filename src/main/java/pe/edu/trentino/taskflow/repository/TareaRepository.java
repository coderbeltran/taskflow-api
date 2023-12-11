package pe.edu.trentino.taskflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.trentino.taskflow.model.Tarea;

import java.util.Optional;

public interface TareaRepository extends JpaRepository<Tarea, Integer> {
    public boolean existsById(Integer id);

}
