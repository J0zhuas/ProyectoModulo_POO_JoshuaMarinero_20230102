package JoshuaMarinero_20230102.JoshuaMarinero_20230102.Controller;

import JoshuaMarinero_20230102.JoshuaMarinero_20230102.Models.BookDTO;
import JoshuaMarinero_20230102.JoshuaMarinero_20230102.Service.BookService;
import jakarta.validation.Valid;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
//Se pone para asignarle una ruta a la api
@RequestMapping("/apiBook")
public class BookController {
    //Se sobre escribe para tener acceso a la clase
    @Autowired
    private BookService service;

    //logica para obtener los datos
    @GetMapping("/getAllBooks")
    public List<BookDTO>getallBooks(){
        return service.getAllBooks();
    }
    //Se pone el {id} para encontrar el usuario que se desea buscar, es la ruta
    @GetMapping("/getBookById/{id}")
    public BookDTO getbookbyid(@PathVariable Long id){
         return service.getBookById(id);
    }
    //logica de insert
    @PostMapping("/insertBook")
    public ResponseEntity<Map<String, Object>> registrar(@Valid @RequestBody BookDTO libro){
        try {
            BookDTO respuesta = service.insertBook(libro);
            if (respuesta == null){
                return ResponseEntity.badRequest().body(Map.of(
                   "status", "Insercion incorrecta",
                   "errorType", "Validation_error",
                   "message", "datos del usuario invalidos"
                ));
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "status", "Succes",
                    "data",respuesta));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "status", "Insercion incorrecta",
                            "message", "Error al registrar",
                            "detail", e.getMessage()
                    ));
        }
    }
    //logica de update
    @PutMapping("/updateBook/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id, @Valid @RequestBody BookDTO dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(Map.of("Error", bindingResult));
        }
        try {
            BookDTO answer = service.updateBook(id, dto);
            if (answer == null){
                return ResponseEntity.badRequest().body(Map.of("Error", "Datos nulos"));
            }
            return ResponseEntity.badRequest().body(Map.of("Error", "Succes", "datos", dto));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(Map.of("Error", e.getMessage()));
        }
    }
    //logica de eliminar
    @DeleteMapping("/deleteBook/{id}")
    public ResponseEntity<Map<String, Object>> eliminar(@PathVariable Long id){
        try {
            if (!service.deleteBook(id)){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).header("X-Mensaje-Error","Usuario no encontrado").body(Map.of(
                        "error", "not found ",
                        "message", "El usuario no ha sido encontrado",
                        "timestamp", Instant.now().toString()
                ));
            }
            return ResponseEntity.ok().body(Map.of(
                    "status", "Succes",
                    "data","Usuario eliminado exotosamente"
            ));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(Map.of(
                    "status", "Error",
                    "message", "Error al eliminar",
                    "detail", e.getMessage()
            ));
        }
    }
}
