package pe.edu.trentino.taskflow.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pe.edu.trentino.taskflow.model.Tarea;
import pe.edu.trentino.taskflow.model.Usuario;
import pe.edu.trentino.taskflow.repository.TareaRepository;
import pe.edu.trentino.taskflow.repository.UsuarioRepository;


import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/tareas")
@AllArgsConstructor
public class TareaController {
    private final TareaRepository tareaRepository;
    private final UsuarioRepository usuarioRepository;
    @GetMapping
    public List<Tarea> list(){
        return tareaRepository.findAll();
    }
    @GetMapping("/paginar")
    public Page<Tarea> listPage(Pageable pageable){
        return tareaRepository.findAll(pageable);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Tarea> getTaskflowId(@PathVariable Integer id){
        Tarea tarea= tareaRepository.findById(id).orElse(null);
        if(tarea==null){
           return ResponseEntity.notFound().build();
        }
       return ResponseEntity.ok().body(tarea);
    }
    @PostMapping()
    public ResponseEntity<Tarea> createTaskflow(@RequestBody  @Validated Tarea tarea){
        tarea.setFechaCrea(LocalDateTime.now());
        tarea.setEstado(Tarea.Estado.CREADO);
       tareaRepository.save(tarea);
       return ResponseEntity.created(URI.create("www.google.com")).body(tarea);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarea> updateTaskflow(@PathVariable Integer id, @RequestBody @Validated Tarea tareaForm){
        Tarea tareaDB=tareaRepository.findById(id).orElse(null);
        if(tareaDB==null){
            return ResponseEntity.notFound().build();
        }
        tareaDB.setTitulo(tareaForm.getTitulo());
        tareaDB.setDescripcion(tareaForm.getDescripcion());
        tareaDB.setEstado(tareaForm.getEstado());
        tareaDB.setPrioridad(tareaForm.getPrioridad());
        tareaDB.setFechaLimite(tareaForm.getFechaLimite());
        tareaRepository.save(tareaDB);
        return ResponseEntity.ok(tareaDB);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskflow(@PathVariable Integer id){
        Tarea tareaDB=tareaRepository.findById(id).orElse(null);
        if(tareaDB==null){
            return ResponseEntity.notFound().build();
        }

        tareaRepository.delete(tareaDB);
        return ResponseEntity.noContent().build();
    }
@PutMapping("/{idTarea}/asignarresponsable/{idUsuario}")
    public Tarea asignarTarea(@PathVariable Integer idTarea, @PathVariable Integer idUsuario){
Tarea tarea=tareaRepository.findById(idTarea).orElse(null);
    Usuario usuario=usuarioRepository.findById(idUsuario).orElse(null);
    tarea.setResponsable(usuario);
    tarea.setEstado(Tarea.Estado.ASIGNADO);
    return tareaRepository.save(tarea);

}

}
