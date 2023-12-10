package pe.edu.trentino.taskflow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String email;
    private String clave;
    @Enumerated(EnumType.STRING)
    private Rol rol;
    @Column(name = "fecha_crea")
    private LocalDateTime fechaCrea;
    @JsonIgnore
    @OneToMany(mappedBy = "responsable", fetch = FetchType.LAZY)
    private List<Tarea> tareas = new ArrayList<>();

    public enum Rol {
        ADMIN, NORMAL
    }

}