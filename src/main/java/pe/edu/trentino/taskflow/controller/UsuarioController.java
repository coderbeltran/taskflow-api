package pe.edu.trentino.taskflow.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.trentino.taskflow.model.Tarea;
import pe.edu.trentino.taskflow.model.Usuario;
import pe.edu.trentino.taskflow.repository.TareaRepository;
import pe.edu.trentino.taskflow.repository.UsuarioRepository;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@AllArgsConstructor
public class UsuarioController {
    private final UsuarioRepository usuarioRepository;
    private final TareaRepository tareaRepository;
    @GetMapping
    public List<Usuario> list(){
        return usuarioRepository.findAll();
    }
    @GetMapping("/paginar")
    public Page<Usuario> listPage(Pageable pageable){
        return usuarioRepository.findAll(pageable);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUserId(@PathVariable Integer id){
        Usuario usuario= usuarioRepository.findById(id).orElse(null);
        if(usuario==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(usuario);
    }
    @PostMapping()
    public ResponseEntity<Usuario> createUser(@RequestBody Usuario usuario){
        usuario.setFechaCrea(LocalDateTime.now());
        usuario.setFechaCrea(LocalDateTime.now());
        usuarioRepository.save(usuario);
        return ResponseEntity.created(URI.create("www.google.com")).body(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUser(@PathVariable Integer id, @RequestBody Usuario tareaForm){
        Usuario usuarioDB=usuarioRepository.findById(id).orElse(null);
        if(usuarioDB==null){
            return ResponseEntity.notFound().build();
        }
        usuarioDB.setNombre(tareaForm.getNombre());
        usuarioDB.setEmail(tareaForm.getEmail());
        usuarioDB.setClave(tareaForm.getClave());
        usuarioDB.setRol(tareaForm.getRol());
        usuarioRepository.save(usuarioDB);
        return ResponseEntity.ok(usuarioDB);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id){
        Usuario usuarioDB=usuarioRepository.findById(id).orElse(null);
        if(usuarioDB==null){
            return ResponseEntity.notFound().build();
        }

        usuarioRepository.delete(usuarioDB);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{idUsuario}/tareas")
    public List<Tarea> listTasflowUser(@PathVariable Integer idUsuario){
        Usuario usuario=usuarioRepository.findById(idUsuario).orElse(null);
        return usuario.getTareas();

    }
}
