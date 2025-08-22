package JoshuaMarinero_20230102.JoshuaMarinero_20230102;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProyectoModuloJoshuaMarinero20230102Application {

	public static void main(String[] args) {
		//Leemos el archivo dot
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
		dotenv.entries().forEach(dotenvEntry ->
				System.setProperty(dotenvEntry.getKey(), dotenvEntry.getValue())
		);
		//viene por default
		SpringApplication.run(ProyectoModuloJoshuaMarinero20230102Application.class, args);
	}

}
