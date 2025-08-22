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

@Slf4j
@Service
public class BookService {
    @Autowired
    private BookRepository repo;

    public List<BookDTO> getAllBooks() {
        List<BookEntity> books = repo.findAll();
        return books.stream()
                .map(this::ConvertToDTO)
                .collect(Collectors.toList());
    }

    public BookDTO getBookById (Long id){
        try {
            if (repo.existsById(id)){
                BookEntity entity = repo.getReferenceById(id);
                return ConvertToDTO(entity);
            }
            throw new IllegalArgumentException("El libro con el id" + id + ", no existe");
        }catch (Exception e){
            throw new RuntimeException("El libro no se encuentra" + e);
        }
    }
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
            throw new RuntimeException ("El usuario no se encuentra");
        }
    }
    public boolean deleteBook(Long id){
        try {
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
    private BookEntity ConvertToEntity(BookDTO dto) {
        BookEntity entity = new BookEntity();
        entity.setLibro_id(entity.getLibro_id());
        entity.setTitulo(entity.getTitulo());
        entity.setIsbn(entity.getIsbn());
        entity.setAnio_publicacion(entity.getAnio_publicacion());
        entity.setGenero(entity.getGenero());
        entity.setAutor_id(entity.getAutor_id());
        return entity;
    }
}
