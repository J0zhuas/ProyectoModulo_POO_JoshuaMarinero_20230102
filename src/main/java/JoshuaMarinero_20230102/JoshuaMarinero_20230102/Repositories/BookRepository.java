package JoshuaMarinero_20230102.JoshuaMarinero_20230102.Repositories;

import JoshuaMarinero_20230102.JoshuaMarinero_20230102.Entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//En este apartado se conecta a JPA para tener todos los metodos GET, SET, PUT y delte
public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
