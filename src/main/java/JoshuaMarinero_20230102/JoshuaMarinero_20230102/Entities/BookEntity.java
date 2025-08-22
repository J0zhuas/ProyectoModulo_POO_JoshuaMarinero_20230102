package JoshuaMarinero_20230102.JoshuaMarinero_20230102.Entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
//Se pone la tabla de la tabla como se comportara
@Table(name = "LIBROS")
@Getter @Setter @EqualsAndHashCode @ToString
public class BookEntity {
    //con el @ID se define que ese valor sera el id
    @Id
    //Este se pone para saber de que forma se generara la secuencia del id
    @SequenceGenerator(name = "seq_libros", sequenceName = "seq_libros", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_libros")
    //Se pone el nombre de la columna de la tabla como la que se comportara
    @Column(name = "LIBRO_ID")
    private int libro_id;
    @Column(name = "TITULO")
    private String titulo;
    @Column(name = "isbn")
    private String isbn;
    @Column(name = "ANIO_PUBLICACION")
    private int anio_publicacion;
    @Column(name = "GENERO")
    private String genero;
    @Column(name = "AUTOR_ID")
    private int autor_id;
}
