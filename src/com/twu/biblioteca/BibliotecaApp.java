package com.twu.biblioteca;

import com.twu.biblioteca.model.*;
import com.twu.biblioteca.repository.*;
import com.twu.biblioteca.service.*;

import javax.jws.soap.SOAPBinding;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BibliotecaApp {

    public static void main(String[] args) {

        List<Book> listOfBooks = new ArrayList<>();
        BookRepository bookRepository = new BookRepositoryImpl(listOfBooks);
        BookService bookService = new BookService(bookRepository);

        List<Movie> listOfMovies = new ArrayList<>();
        MovieRepository movieRepository = new MovieRepositoryImpl(listOfMovies);
        MovieService movieService = new MovieService(movieRepository);

        User user = new User("teste", "luiza", "lgmaraes2@gmail.com", "1234543422", UserType.CUSTOMER);

        List<User> listOfUsers = new ArrayList<>();
        listOfUsers.add(user);
        UserRepository userRepository = new UserRepositoryImpl(listOfUsers);
        UserService userService = new UserService(userRepository);

        BibliotecaService bibliotecaService = new BibliotecaService(bookService, movieService);
        MenuService menuService = new MenuService(bibliotecaService, userService);

        Book bookTDD = new Book("Nora Roberts", "Testes Unitários",
                LocalDate.of(2019, 12, 27), false);

        Book bookDev = new Book("Jorge Amado", "Desenvolvimento Web",
                LocalDate.of(2018, 2, 17), false);

        listOfBooks.add(bookDev);
        listOfBooks.add(bookTDD);

        Movie movie = new Movie("Guerra Mundial Z",
                LocalDate.of(2012, 1, 12), "Tom Testes", Rating.DEZ, false);

        listOfMovies.add(movie);

        System.out.println(menuService.callWelcomeMessage());

        System.out.println(menuService.listMenuOptions());

        int option = 0;

        while (option != 6) {
            option = menuService.chooseMenuOption();

            if (option == 1) {
                bibliotecaService.listBooksWithColumns().forEach(System.out::println);
            } else if (option == 2) {
                bibliotecaService.listOfMoviesWithColumns().forEach(System.out::println);
            } else if (option == 3) {
                menuService.interactionToLogin();
                if (menuService.getUser() == null) {
                    System.out.println("Library number or password wrong");
                }else {
                    menuService.interactionToCheckoutBook();
                }
            } else if (option == 4) {
                menuService.interactionToLogin();
                if (menuService.getUser() == null) {
                    System.out.println("Library number or password wrong");
                }else {
                    menuService.interactionToReturnBook();
                }
            } else if (option == 5) {
                menuService.interactionToCheckoutMovie();
            } else if (option != 6) {
                System.out.println("Please select valid option");
            }
        }

    }
}
