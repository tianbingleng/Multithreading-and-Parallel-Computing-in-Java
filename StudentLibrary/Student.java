package StudentLibrary;

import java.util.Random;

/**
 * Created by tianbingleng on 2/12/2017.
 */
public class Student implements Runnable{

    private int id;
    private Book[] books;

    public Student(int id, Book[] books) {
        this.id = id;
        this.books = books;
    }

    @Override
    public void run() {

        Random random = new Random();

        int bookId = random.nextInt(Constants.NUMBER_OF_BOOKS);

        while (true) {
            try {
                books[bookId].read(this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return "Student #"+id;
    }
}
