package com.example.my_library;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    private static final String ALL_BOOKS_KEY = "all_books";
    private static final String ALREADY_READ_BOOKS_KEY = "already_read_books";
    private static final String WANT_TO_READ_KEY = "want_to_read";
    private static final String CURRENTLY_READING_BOOKS_KEY = "currently_reading_books";
    private static final String FAVORITES_BOOKS_KEY = "favorites_books";

    private static Utils instance;
    private SharedPreferences sharedPreferences;


    private Utils(Context context) {

        sharedPreferences = context.getSharedPreferences("alternate_db", context.MODE_PRIVATE);

        if (getAllBooks() == null) {
            initData();
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        if (getAlreadyReadBooks() == null) {
            editor.putString(ALREADY_READ_BOOKS_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (getWantToReadBooks() == null) {
            editor.putString(WANT_TO_READ_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (getCurrentlyReadingBooks() == null) {
            editor.putString(CURRENTLY_READING_BOOKS_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (getFavoriteBooks() == null) {
            editor.putString(FAVORITES_BOOKS_KEY, gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
    }

    private void initData() {


        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "Dorian Grey", "Oscar Wilde", 486,
                "https://prodimage.images-bn.com/pimages/9781593080259_p0_v4_s550x406.jpg",
                "a work of maddening brilliance", "long description"));
        books.add(new Book(2, "The Fellowship of the Ring", "Tolkien", 486,
                "https://upload.wikimedia.org/wikipedia/en/8/8a/The_Lord_of_the_Rings_The_Fellowship_of_the_Ring_%282001%29.jpg",
                "a work of maddening brilliance", "long description"));
        books.add(new Book(3, "The Two Towers", "Tolkien", 486,
                "https://images-na.ssl-images-amazon.com/images/I/51THPZNW8VL._SX355_.jpg",
                "a work of maddening brilliance", "long description"));
        books.add(new Book(4, "The Return of the King", "Tolkien", 486,
                "https://images-na.ssl-images-amazon.com/images/I/51V6TZW28KL._SX361_BO1,204,203,200_.jpg",
                "a work of maddening brilliance", "long description"));

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.putString(ALL_BOOKS_KEY, gson.toJson(books));
        editor.commit();
    }

    public static Utils getInstance(Context context) {

        if (instance != null) {
            return instance;
        } else {
            return new Utils(context);
        }
    }

    public List<Book> getAllBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALL_BOOKS_KEY,
                null), type);
        return books;
    }

    public List<Book> getAlreadyReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALREADY_READ_BOOKS_KEY,
                null), type);
        return books;
    }

    public List<Book> getWantToReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(WANT_TO_READ_KEY,
                null), type);
        return books;
    }

    public List<Book> getCurrentlyReadingBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(CURRENTLY_READING_BOOKS_KEY,
                null), type);
        return books;
    }

    public List<Book> getFavoriteBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>() {
        }.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(FAVORITES_BOOKS_KEY,
                null), type);
        return books;
    }

    public Book getBookById(int id) {
        ArrayList<Book> books = (ArrayList<Book>) getAllBooks();
        if (books != null) {
            for (Book book : books) {
                if (book.getId() == id) {
                    return book;
                }
            }
        }

            return null;
    }

        public boolean AddToAlreadyRead (Book book){
            ArrayList<Book> books = (ArrayList<Book>) getAlreadyReadBooks();
            if (books != null) {
                if (books.add(book)) {
                    Gson gson = new Gson();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove(ALREADY_READ_BOOKS_KEY);
                    editor.putString(ALREADY_READ_BOOKS_KEY,gson.toJson(books));
                    editor.commit();
                    return  true;
                }

            }
            return false;
        }

        public boolean AddWantToRead (Book book){
            ArrayList<Book> books = (ArrayList<Book>) getWantToReadBooks();
            if (books != null) {
                if (books.add(book)) {
                    Gson gson = new Gson();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove(WANT_TO_READ_KEY);
                    editor.putString(WANT_TO_READ_KEY,gson.toJson(books));
                    editor.commit();
                    return  true;
                }

            }
            return false;
        }
        public boolean AddFavorites (Book book){
            ArrayList<Book> books = (ArrayList<Book>) getFavoriteBooks();
            if (books != null) {
                if (books.add(book)) {
                    Gson gson = new Gson();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove(FAVORITES_BOOKS_KEY);
                    editor.putString(FAVORITES_BOOKS_KEY,gson.toJson(books));
                    editor.commit();
                    return  true;
                }

            }
            return false;
        }
        public boolean AddCurrently (Book book){
            ArrayList<Book> books = (ArrayList<Book>) getCurrentlyReadingBooks();
            if (books != null) {
                if (books.add(book)) {
                    Gson gson = new Gson();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove(CURRENTLY_READING_BOOKS_KEY);
                    editor.putString(CURRENTLY_READING_BOOKS_KEY,gson.toJson(books));
                    editor.commit();
                    return  true;
                }

            }
            return false;
        }

        public boolean removeAlreadyRead (Book book) {
            ArrayList<Book> books = (ArrayList<Book>) getAlreadyReadBooks();
            if (books != null) {
                for (Book b : books) {
                    if (b.getId() == book.getId()) {
                        if (books.remove(b)) {
                            Gson gson = new Gson();
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.remove(ALREADY_READ_BOOKS_KEY);
                            editor.putString(ALREADY_READ_BOOKS_KEY, gson.toJson(books));
                            editor.commit();
                            return true;
                        }
                    }
                }
            }
            return false;
        }


        public boolean removeWantToRead (Book book){
            ArrayList<Book> books = (ArrayList<Book>) getWantToReadBooks();
            if (books != null) {
                for (Book b : books) {
                    if (b.getId() == book.getId()) {
                        if (books.remove(b)) {
                            Gson gson = new Gson();
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.remove(WANT_TO_READ_KEY);
                            editor.putString(WANT_TO_READ_KEY, gson.toJson(books));
                            editor.commit();
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        public boolean removeCurrently (Book book){
            ArrayList<Book> books = (ArrayList<Book>) getCurrentlyReadingBooks();
            if (books != null) {
                for (Book b : books) {
                    if (b.getId() == book.getId()) {
                        if (books.remove(b)) {
                            Gson gson = new Gson();
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.remove(CURRENTLY_READING_BOOKS_KEY);
                            editor.putString(CURRENTLY_READING_BOOKS_KEY, gson.toJson(books));
                            editor.commit();
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        public boolean removeFavorites (Book book){
            ArrayList<Book> books = (ArrayList<Book>) getFavoriteBooks();
            if (books != null) {
                for (Book b : books) {
                    if (b.getId() == book.getId()) {
                        if (books.remove(b)) {
                            Gson gson = new Gson();
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.remove(FAVORITES_BOOKS_KEY);
                            editor.putString(FAVORITES_BOOKS_KEY, gson.toJson(books));
                            editor.commit();
                            return true;
                        }
                    }
                }
            }
            return false;
        }

}

