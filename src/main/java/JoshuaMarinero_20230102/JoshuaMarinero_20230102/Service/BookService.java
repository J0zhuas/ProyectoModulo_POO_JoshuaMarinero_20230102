package JoshuaMarinero_20230102.JoshuaMarinero_20230102.Service;

import JoshuaMarinero_20230102.JoshuaMarinero_20230102.Entities.BookEntity;
import JoshuaMarinero_20230102.JoshuaMarinero_20230102.Models.BookDTO;
import JoshuaMarinero_20230102.JoshuaMarinero_20230102.Repositories.BookRepository;
import aj.org.objectweb.asm.commons.TryCatchBlockSorter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//Se le pone @Service para que sepa como se comportara
@Slf4j
@Service
public class BookService {
    //Se sobre escribe el repositorio para obtener todos los metodos
    @Autowired
    private BookRepository repo;

    //metodo para obtener una lista con todos los usuarios
    public List<BookDTO> getAllBooks() {
        List<BookEntity> books = repo.findAll();
        return books.stream()
                .map(this::ConvertToDTO)
                .collect(Collectors.toList());
    }
    //Logica para obtener un usuario con respecto al id
    public BookDTO getBookById (Long id){
        try {
            //esto se pone para saber si el usuario existe
            if (repo.existsById(id)){
                BookEntity entity = repo.getReferenceById(id);
                return ConvertToDTO(entity);
            }
            throw new IllegalArgumentException("El libro con el id" + id + ", no existe");
        }catch (Exception e){
            throw new RuntimeException("El libro no se encuentra" + e);
        }
    }
    //logica para insertar usuario
    public BookDTO insertBook(BookDTO dto) throws IllegalAccessError{
        if (dto == null){
            throw new IllegalArgumentException("Libro es nulo");
        }
        try {
            BookEntity entity = ConvertToEntity(dto);
            BookEntity bookSave = repo.save(entity);
            return ConvertToDTO(bookSave);
        }catch (Exception e){
            log.error("Error al registrar usuario" + e.getMessage());
            throw new RuntimeException ("El usuario no se creo");
        }
    }
    //logica para actualizar usuario
    public BookDTO updateBook (Long id, BookDTO dto){
        BookEntity libroexistente = repo.getReferenceById(id);
        libroexistente.setTitulo(dto.getTitulo());
        libroexistente.setIsbn(dto.getTitulo());
        libroexistente.setAnio_publicacion(dto.getAnio_publicacion());
        libroexistente.setGenero(dto.getGenero());
        libroexistente.setAutor_id(dto.getAutor_id());
        BookEntity updated = repo.save(libroexistente);
        return ConvertToDTO(updated);
    }
    public boolean deleteBook(Long id){
        try {
            //Eso se hace para saber si existe el usuario el cual se quiere eliminar
            if (repo.existsById(id)){
                repo.deleteById(id);
                return true;
            }else {
                return false;
            }
        }catch (EmptyResultDataAccessException e){
            throw new EmptyResultDataAccessException("El libro con id" + id + "No existe",1);
        }
    }
    //Metodo para convertir el Entity a DTO, esto es para ya mostrar los datos traidos de la base de datos
    private BookDTO ConvertToDTO(BookEntity entity) {
        BookDTO dto = new BookDTO();
        dto.setLibro_id(entity.getLibro_id());
        dto.setTitulo(entity.getTitulo());
        dto.setIsbn(entity.getIsbn());
        dto.setAnio_publicacion(entity.getAnio_publicacion());
        dto.setGenero(entity.getGenero());
        dto.setAutor_id(entity.getAutor_id());
        return dto;
    }
    //Metodo para convertir el DTO a Entity, esto es para ya mandar los datos a la base de datos
    private BookEntity ConvertToEntity(BookDTO dto) {
        BookEntity entity = new BookEntity();
        entity.setTitulo(dto.getTitulo());
        entity.setIsbn(dto.getIsbn());
        entity.setAnio_publicacion(dto.getAnio_publicacion());
        entity.setGenero(dto.getGenero());
        entity.setAutor_id(dto.getAutor_id());
        return entity;
    }
}
