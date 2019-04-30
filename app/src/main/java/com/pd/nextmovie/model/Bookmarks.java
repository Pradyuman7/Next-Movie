package com.pd.nextmovie.model;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class Bookmarks {

    private ArrayList<Movie> bookmarkedMovies;

    public Bookmarks(){
        bookmarkedMovies = new ArrayList<>();
    }

    public void addMovie(Movie movie){
        bookmarkedMovies.add(movie);
    }

    public ArrayList<Movie> getBookmarkedMovies(){
        return bookmarkedMovies;
    }

    public Movie getMovieAtPosition(int position){
        if(position > bookmarkedMovies.size() || position < 0)
            return null;

        return bookmarkedMovies.get(position);
    }

    public void addBookmarksToDatabase(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users").child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid()));
        reference.child("bookmarks").setValue(bookmarkedMovies);
    }


    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();

        for(Movie movie : bookmarkedMovies){
            stringBuilder.append(movie.getTitle()).append(",");
        }

        return stringBuilder.toString();
    }
}
