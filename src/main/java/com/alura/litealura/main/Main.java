package com.alura.litealura.main;

import com.alura.litealura.model.Author;
import com.alura.litealura.model.Book;
import com.alura.litealura.model.Language;
import com.alura.litealura.service.AuthorService;
import com.alura.litealura.service.BookService;
import com.alura.litealura.service.LanguageService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Scanner;

@Component
public class Main {
    private Scanner scanner = new Scanner(System.in);
    private ObjectMapper objectMapper = new ObjectMapper();
    private BookService bookService;
    private AuthorService authorService;
    private LanguageService languageService;

    @Autowired
    public Main(BookService bookService, AuthorService authorService, LanguageService languageService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.languageService = languageService;
    }

    public void showMenu() {
        boolean flag = true;
        String welcomeMessage = "" +
                "--------------------------------------------------------------" + "\n" +
                "| *********************** LITERALURA *********************** |" + "\n" +
                "| ---------------------------------------------------------- |" + "\n" +
                "| 1.- Buscar libro por título                                |" + "\n" +
                "| 2.- Listar libros registrados                              |" + "\n" +
                "| 3.- Listar autores registrados                             |" + "\n" +
                "| 4.- Listar autores vivos en un determinado año             |" + "\n" +
                "| 5.- Listar libros por idioma                               |" + "\n" +
                "| 6.- Salir                                                  |" + "\n" +
                "--------------------------------------------------------------" + "\n\n" +
                "Elija una opción válida: ";

        while (flag) {
            System.out.print(welcomeMessage);
            String option = scanner.next();

            switch (option) {
                case "1":
                    String bookName = getBookName();
                    getBookByName(bookName);
                    break;
                case "2":
                    printBooksRegistered();
                    break;
                case "3":
                    printAuthorsRegistered();
                    break;
                case "4":
                    Long year = getYear();
                    printAuthorsAliveByYear(year);
                    break;
                case "5":
                    String language = getLanguage();
                    printBooksByLanguage(language);
                    break;
                case "6":
                    System.out.println("¡Gracias por utilizar esta aplicación señor usuari@!");
                    flag = false;
                    break;
                default:
                    System.out.println("¡Opción inválida!");
                    break;
            }
        }
    }

    private String getBookName() {
        String message = "Ingrese el nombre del libro que desea buscar: ";
        System.out.print(message);

        scanner.nextLine();
        String bookName = scanner.nextLine();
        System.out.println();

        return bookName;
    }

    private Long getYear() {
        String message = "Ingrese el año vivo de autor(es) que desea buscar: ";
        System.out.print(message);

        scanner.nextLine();
        Long year = scanner.nextLong();
        System.out.println();

        return year;
    }

    private String getLanguage() {
        String message = "" +
                "-------------------------------------------" + "\n" +
                "| *************** IDIOMAS *************** |" + "\n" +
                "| --------------------------------------- |" + "\n" +
                "| es.- Español                            |" + "\n" +
                "| en.- Inglés                             |" + "\n" +
                "| fr.- Francés                            |" + "\n" +
                "| pt.- Portugués                          |" + "\n" +
                "-------------------------------------------" + "\n\n" +
                "Elija un idioma válido: ";
        System.out.print(message);

        scanner.nextLine();
        String language = scanner.nextLine();
        System.out.println();

        return language;
    }

    private void getBookByName(String bookName) {
        String API_URL = "https://gutendex.com/books/?search=" + bookName.replace(" ", "%20");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonNode jsonNode = objectMapper.readTree(response.body());
            String resultsJson = jsonNode.get("results").toString();
            List<Book> books = objectMapper.readValue(resultsJson, new TypeReference<List<Book>>() {});
            printBooks(books);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void printBooks(List<Book> books) {
        StringBuilder listOfBooks = new StringBuilder();

        books.forEach(book -> {
            StringBuilder authors = new StringBuilder();
            StringBuilder languages = new StringBuilder();
            Book isBookSaved = bookService.getBookByTitle(book.getTitle());

            if (isBookSaved == null) bookService.saveBook(book);

            for (int i = 0; i < book.getAuthors().size(); i++) {
                Author author = book.getAuthors().get(i);
                authors.append(author.getName());

                if (isBookSaved == null) {
                    Author isAuthorSaved = authorService.getAuthorByName(author.getName());

                    if (isAuthorSaved == null) authorService.saveAuthor(author);
                }

                if (i != (book.getAuthors().size() - 1)) authors.append(", ");
            }

            for (int i = 0; i < book.getLanguages().size(); i++) {
                Language language = book.getLanguages().get(i);
                languages.append(language.getLanguage());

                if (isBookSaved == null) {
                    Language isLanguageSaved = languageService.getLanguageByLanguage(language.getLanguage());

                    if (isLanguageSaved == null) languageService.saveLanguage(language);
                }

                if (i != (book.getLanguages().size() - 1)) languages.append(", ");
            }

            listOfBooks.append("--------------------------------------------------------------" + "\n" +
                    "| ************************* LIBRO ************************** |" + "\n" +
                    "| ---------------------------------------------------------- |" + "\n" +
                    "| Título: " + book.getTitle() + " ".repeat(60 - (9 + book.getTitle().length())) + "|" + "\n" +
                    "| Autores: " + authors + " ".repeat(60 - (10 + authors.length())) + "|" + "\n" +
                    "| Idiomas: " + languages + " ".repeat(60 - (10 + languages.length())) + "|" + "\n" +
                    "| Número de descargas: " + book.getDownload_count() + " ".repeat(60 - (22 + book.getDownload_count().toString().length())) + "|" + "\n" +
                    "--------------------------------------------------------------" + "\n\n");
        });

        System.out.println(listOfBooks);
    }

    private void printAuthors(List<Author> authors) {
        StringBuilder listOfAuthors = new StringBuilder();

        authors.forEach(author -> {
            String authorBooks = bookService.getAuthorBooks(author.getName());

            listOfAuthors.append("--------------------------------------------------------------" + "\n" +
                    "| ************************* AUTOR ************************** |" + "\n" +
                    "| ---------------------------------------------------------- |" + "\n" +
                    "| Autor: " + author.getName() + " ".repeat(60 - (8 + author.getName().length())) + "|" + "\n" +
                    "| Fecha de Nacimiento: " + author.getBirth_year() + " ".repeat(60 - (22 + author.getBirth_year().toString().length())) + "|" + "\n" +
                    "| Fecha de Fallecimiento: " + author.getDeath_year() + " ".repeat(60 - (25 + author.getDeath_year().toString().length())) + "|" + "\n" +
                    "| Libros: " + authorBooks + " ".repeat(60 - (9 + authorBooks.length())) + "|" + "\n" +
                    "--------------------------------------------------------------" + "\n\n");
        });

        System.out.println(listOfAuthors);
    }

    private void printBooksRegistered() {
        List<Book> booksRegistered = bookService.getTotalBooks();
        printBooks(booksRegistered);
    }

    private void printAuthorsRegistered() {
        List<Author> authorsRegistered = authorService.getTotalAuthors();
        printAuthors(authorsRegistered);
    }

    private void printAuthorsAliveByYear(Long year) {
        List<Author> authorsAlive = authorService.getTotalAuthorsAliveInYear(year);
        printAuthors(authorsAlive);
    }

    private void printBooksByLanguage(String language) {
        List<Book> booksByLanguage = bookService.getBooksByLanguage(language);
        printBooks(booksByLanguage);
    }
}
