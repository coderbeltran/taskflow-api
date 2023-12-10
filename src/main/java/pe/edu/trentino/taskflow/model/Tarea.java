package pe.edu.trentino.taskflow.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
public class Tarea {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
     private String titulo;
     private String descripcion;
     @Enumerated(EnumType.STRING)
     private Estado estado;
     private int prioridad;
     @Column(name ="fecha_crea")
     private LocalDateTime fechaCrea;
    @Column(name ="fecha_limite")
    private LocalDate fechaLimite;
 @ManyToOne
 @JoinColumn(name = "responsable_id", referencedColumnName = "id")
 private Usuario responsable;
     public enum Estado{
         CREADO, ASIGNADO, FINALIZADO
     }
}
