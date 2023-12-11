package pe.edu.trentino.taskflow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotBlank
    @Size(max = 50)
    private String nombre;

    @NotNull
    @Email
    @Size(max = 50)
    private String email;

    @NotBlank
    @Size(min = 4, max = 32)
    private String clave;

    @NotNull
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