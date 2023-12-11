package pe.edu.trentino.taskflow.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Size(min = 3, max = 100)
    private String titulo;

    @Size( max = 255)
    private String descripcion;


    @Enumerated(EnumType.STRING)
    private Estado estado;

    @NotNull
    @Max(5)
    @Min(1)
    private int prioridad;

    @Column(name = "fecha_crea")
    private LocalDateTime fechaCrea;

    @NotNull
    @FutureOrPresent
    @Column(name = "fecha_limite")
    private LocalDate fechaLimite;
    @ManyToOne
    @JoinColumn(name = "responsable_id", referencedColumnName = "id")
    private Usuario responsable;

    public enum Estado {
        CREADO, ASIGNADO, FINALIZADO
    }
}
