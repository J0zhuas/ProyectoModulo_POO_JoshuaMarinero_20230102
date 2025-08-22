package JoshuaMarinero_20230102.JoshuaMarinero_20230102.Entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "LIBROS")
@Getter @Setter @EqualsAndHashCode @ToString
public class BookEntity {
    @Id
    @SequenceGenerator(name = "seq_libros", sequenceName = "seq_libros", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_libros")
    @Column(name = "LIBRO_ID")
    private int libro_id;
    @Column(name = "TITULO")
    private String titulo;
    @Column(name = "isbn")
    private String isbn;
    @Column(name = "AÑO_PUBLICACION")
    private int año_publicacion;
    @Column(name = "GENERO")
    private String genero;
    @Column(name = "AUTOR_ID")
    private int autor_id;
}
