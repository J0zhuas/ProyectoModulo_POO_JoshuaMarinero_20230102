package JoshuaMarinero_20230102.JoshuaMarinero_20230102.Controller;

import JoshuaMarinero_20230102.JoshuaMarinero_20230102.Models.BookDTO;
import JoshuaMarinero_20230102.JoshuaMarinero_20230102.Service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/apiBook")
public class BookController {
    @Autowired
    private BookService service;

    @GetMapping("/getAllBooks")
    public List<BookDTO>getallBooks(){
        return service.getAllBooks();
    }
    @GetMapping("/getBookById")
    public BookDTO getbookbyid(@PathVariable Long id){
         return service.getBookById(id);
    }
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
}
