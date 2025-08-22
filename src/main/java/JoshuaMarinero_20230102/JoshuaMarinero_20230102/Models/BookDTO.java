package JoshuaMarinero_20230102.JoshuaMarinero_20230102.Models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode @Getter @Setter
public class BookDTO {
    private int libro_id;
    private String titulo;
    private String isbn;
    private int año_publicacion;
    private String genero;
    private int autor_id;
}
