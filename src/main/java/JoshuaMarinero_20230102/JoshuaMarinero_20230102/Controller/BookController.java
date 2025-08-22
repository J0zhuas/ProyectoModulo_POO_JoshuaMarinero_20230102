package JoshuaMarinero_20230102.JoshuaMarinero_20230102.Controller;

import JoshuaMarinero_20230102.JoshuaMarinero_20230102.Models.BookDTO;
import JoshuaMarinero_20230102.JoshuaMarinero_20230102.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/apiBook")
public class BookController {
    @Autowired
    private BookService service;

    @GetMapping("/getAllBooks")
    public List<BookDTO>getallBooks(){
        
    }
}
