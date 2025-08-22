package JoshuaMarinero_20230102.JoshuaMarinero_20230102.Repositories;

import JoshuaMarinero_20230102.JoshuaMarinero_20230102.Entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
