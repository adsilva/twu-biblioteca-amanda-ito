package com.twu.biblioteca.service;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.repository.BookRepositoryImpl;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class BibliotecaServiceTest {

    private BookRepositoryImpl bookRepository = new BookRepositoryImpl();
    private BookService bookService = new BookService(bookRepository);
    private BibliotecaService bibliotecaService = new BibliotecaService(bookService);

    @Test
    public void welcomeSuccessTest() {
        assertEquals("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore", bibliotecaService.callWelcomeMessage());
    }

    @Test
    public void listBooksWithSuccess() {
        List<Book> listOfBooks = bibliotecaService.getListOfBooks();
        assertNotNull(listOfBooks);
    }

    @Test
    public void listBooksWithAuthorAndPublication() {
        String dataBook = "1 | Nora Roberts | 2019-12-27";
        List<Book> listOfBooks = bibliotecaService.getListOfBooks();
        Book book = new Book("Nora Roberts", "TDD", LocalDate.of(2019, 12, 27), false);
        listOfBooks.add(book);
        assertTrue(listOfBooks.size() == 1);
        assertEquals(dataBook, bibliotecaService.listBooksWithColumns(listOfBooks).get(0));
    }

    @Test
    public void showMenuOptions(){
        String menuOptions = "Menu \n1 - List of books\n2 - Checkout book \n3 - Return book \n4 - Quit";
        assertEquals(menuOptions, bibliotecaService.listMenuOptions());
    }

    @Test
    public void showMessageCheckoutBookWithSuccess(){
        String successMessage = "Thank you! Enjoy the book";
        assertEquals(successMessage, bibliotecaService.getSUCCESS_CHECKOUT());
    }

    @Test
    public void showMessageCheckoutBookWithUn_Successful(){
        String successMessage = "Sorry, that book is not available";
        assertEquals(successMessage, bibliotecaService.getUN_SUCCESSFUL_CHECKOUT());
    }

    @Test
    public void showMessageReturnBookWithSuccess(){
        String successMessage = "Thank you for returning the book";
        assertEquals(successMessage, bibliotecaService.getSUCCESSFUL_RETURN());
    }

    @Test
    public void showMessageReturnBookWithUn_Success(){
        String successMessage = "That is not a valid book to return";
        assertEquals(successMessage, bibliotecaService.getUN_SUCCESSFUL_RETURN());
    }
}
