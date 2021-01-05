package com.chandu.SpringBootCrud;

import com.chandu.SpringBootCrud.dao.BookRepository;
import com.chandu.SpringBootCrud.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootCrudApplication implements CommandLineRunner {

	@Autowired
private BookRepository bookRepository;
	public static void main(String[] args) {
		SpringApplication.run(SpringBootCrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		bookRepository.save(new Book("Java"));
		bookRepository.save(new Book("Oracle"));
		bookRepository.save(new Book("Python"));

		System.out.println("\nfindAll()");
        bookRepository.findAll().forEach(x -> System.out.println(x));

		System.out.println("\nfindById(1L)");
		bookRepository.findById(1l).ifPresent(x -> System.out.println(x));

		System.out.println("\nfindByName('Java')");
		bookRepository.findByName("Java").forEach(x -> System.out.println(x));
	}
}
