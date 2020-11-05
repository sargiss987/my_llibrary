package com.example.my_library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    public static final String BOOK_ID_KEY = "bookId";
    private ImageView imageBook;
    private Button btnCurrentReading, btnAddToWantRead, btnAddToAlready, btnAddToFavorites;
    private TextView txtBookName, txtAuthor, txtPages, txtDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initView();



    Intent intent = getIntent();
    if(intent != null){
        int bookId = intent.getIntExtra(BOOK_ID_KEY, -1);
        if (bookId != -1){
            Book incomingBook = Utils.getInstance(this).getBookById(bookId);
            if (incomingBook != null){
                satDate(incomingBook);

                handelAlreadyRead(incomingBook);
                handelWantToReadBooks(incomingBook);
                handelCurrentlyReadBooks(incomingBook);
                handelFavoritesReadBooks(incomingBook);
            }
        }
    }


    }

    private void handelFavoritesReadBooks(final Book book) {
        ArrayList<Book> favoritesBooks = (ArrayList<Book>) Utils.getInstance(this).getFavoriteBooks();

        boolean existInFavoritesBooks = false;

        for (Book b : favoritesBooks){
            if (b.getId()  == book.getId()){
                existInFavoritesBooks = true;
            }
        }

        if (existInFavoritesBooks){
            btnAddToFavorites.setEnabled(false);
        }else {
            btnAddToFavorites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance(BookActivity.this).AddFavorites(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this,FavoritesBooksActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(BookActivity.this, "Something, wrong happened, try again", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }

    }

    private void handelWantToReadBooks(final Book book) {
        ArrayList<Book> wantToReadBooks = (ArrayList<Book>) Utils.getInstance(this).getWantToReadBooks();

        boolean existInWantToReadBooks = false;

        for (Book b : wantToReadBooks){
            if (b.getId()  == book.getId()){
                existInWantToReadBooks = true;
            }
        }

        if (existInWantToReadBooks){
            btnAddToWantRead.setEnabled(false);
        }else {
            btnAddToWantRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance(BookActivity.this).AddWantToRead(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this,WantToReadActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(BookActivity.this, "Something, wrong happened, try again", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }

    }

    private void handelCurrentlyReadBooks(final Book book) {
        ArrayList<Book> currentlyReadingBooks = (ArrayList<Book>) Utils.getInstance(this).getCurrentlyReadingBooks();

        boolean existInCurrentlyReadingBooks = false;

        for (Book b : currentlyReadingBooks){
            if (b.getId()  == book.getId()){
                existInCurrentlyReadingBooks = true;
            }
        }

        if (existInCurrentlyReadingBooks){
            btnCurrentReading.setEnabled(false);
        }else {
            btnCurrentReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance(BookActivity.this).AddCurrently(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this,CurrentlyReadingBooksActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(BookActivity.this, "Something, wrong happened, try again", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }
    }

    /**
     * Enable and Disable button
     * Add the book to Already Read Book Array list
     * @param book
     */

    private void handelAlreadyRead(final Book book) {

        ArrayList<Book> alreadyReadBooks = (ArrayList<Book>) Utils.getInstance(this).getAlreadyReadBooks();

        boolean existInAlreadyReadBooks = false;

        for (Book b : alreadyReadBooks){
            if (b.getId()  == book.getId()){
                existInAlreadyReadBooks = true;
            }
        }

        if (existInAlreadyReadBooks){
            btnAddToAlready.setEnabled(false);
        }else {
            btnAddToAlready.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance(BookActivity.this).AddToAlreadyRead(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BookActivity.this,AlreadyReadBooksActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(BookActivity.this, "Something, wrong happened, try again", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }


    }

    private void satDate(Book book) {

        txtBookName.setText(book.getName());
        txtAuthor.setText(book.getAuthor());
        txtPages.setText(String.valueOf(book.getPages()));
        txtDescription.setText(book.getLongDesc());
        Glide.with(this)
                .asBitmap()
                .load(book.getImageUrl())
                .into(imageBook);

    }


    private void initView() {
        imageBook = (ImageView) findViewById(R.id.imgBook);

        btnAddToAlready = (Button) findViewById(R.id.btnAlreadyRead);
        btnCurrentReading = (Button) findViewById(R.id.btnCurrentlyReading);
        btnAddToWantRead = (Button) findViewById(R.id.btnWantToRead);
        btnAddToFavorites = (Button) findViewById(R.id.btnAddToFavorites);

        txtBookName = (TextView) findViewById(R.id.txtMysterious);
        txtAuthor = (TextView) findViewById(R.id.AuthorText);
        txtPages = (TextView) findViewById(R.id.pagesText);
        txtDescription = (TextView) findViewById(R.id.longDescription);

    }
}
