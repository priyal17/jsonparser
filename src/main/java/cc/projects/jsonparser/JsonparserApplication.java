package cc.projects.jsonparser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import cc.projects.jsonparser.exception.JSONParserException;
import cc.projects.jsonparser.service.Parser;

@SpringBootApplication
public class JsonparserApplication {

	public static void main(String[] args) {
		SpringApplication.run(JsonparserApplication.class, args);
	}
	
	@Bean
	CommandLineRunner commandLineRunner() {
		
		return args -> {
			
			
			 if (args.length != 1) {
		            System.err.println("Usage: java -jar jsonparser.jar <json-file>");
		            System.exit(1);
		        }

		        String filePath = args[0];

		        try {
		            String json = Files.readString(Path.of(filePath));
		            Parser parser = new Parser(json);
		            parser.parse();
		            System.out.println("✅ JSON is valid");
		            System.exit(0);

		        } catch (NoSuchFileException e) {
		            System.err.println("❌ File not found: " + filePath);
		            System.exit(1);

		        } catch (JSONParserException e) {
		            System.err.println("❌ Invalid JSON: " + e.getMessage());
		            System.exit(1);

		        } catch (IOException e) {
		            System.err.println("❌ IO Error: " + e.getMessage());
		            System.exit(1);
		        }
			
		};
	}

}
