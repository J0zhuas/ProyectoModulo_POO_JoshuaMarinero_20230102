package JoshuaMarinero_20230102.JoshuaMarinero_20230102.Service;

import JoshuaMarinero_20230102.JoshuaMarinero_20230102.Entities.BookEntity;
import JoshuaMarinero_20230102.JoshuaMarinero_20230102.Models.BookDTO;
import JoshuaMarinero_20230102.JoshuaMarinero_20230102.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    private BookDTO ConvertToDTO(BookEntity entity) {
        BookDTO dto = new BookDTO();
        dto.setLibro_id(entity.getLibro_id());
        dto.setTitulo(entity.getTitulo());
        dto.setIsbn(entity.getIsbn());
        dto.setAño_publicacion(entity.getAño_publicacion());
        dto.setGenero(entity.getGenero());
        dto.setAutor_id(entity.getAutor_id());
        return dto;
    }
}
