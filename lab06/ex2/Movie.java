package ex2; // to complete

import java.util.List;

public class Movie {
   private String title;
   private int year;
   private Person director;
   private Person writer;
   private String series;
   private List<Person> cast;
   private List<Place> locations;
   private List<String> languages;
   private List<String> genres;
   private boolean isTelevision;
   private boolean isNetflix;
   private boolean isIndependent;

   public Movie(){

   }

   public Movie(
      final String movieTitle,
      final int movieYear,
      final Person movieDirector,
      final Person movieWriter,
      final String movieSeries,
      final List<Person> movieCast,
      final List<Place> movieLocations,
      final List<String> movieLanguages,
      final List<String> movieGenres,
      final boolean television,
      final boolean netflix,
      final boolean independent)    {
         this.title = movieTitle;
         this.year = movieYear;
         this.director = movieDirector;
         this.writer = movieWriter;
         this.series = movieSeries;
         this.cast = movieCast;
         this.locations = movieLocations;
         this.languages = movieLanguages;
         this.genres = movieGenres;
         this.isTelevision = television;
         this.isNetflix = netflix;
         this.isIndependent = independent;
   }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public Person getDirector() {
        return director;
    }

    public Person getWriter() {
        return writer;
    }

    public String getSeries() {
        return series;
    }

    public List<Person> getCast() {
        return cast;
    }

    public List<Place> getLocations() {
        return locations;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public List<String> getGenres() {
        return genres;
    }

    public boolean isTelevision() {
        return isTelevision;
    }

    public boolean isNetflix() {
        return isNetflix;
    }

    public boolean isIndependent() {
        return isIndependent;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", year=" + year +
                ", director=" + director +
                ", writer=" + writer +
                ", series='" + series + '\'' +
                ", cast=" + cast +
                ", locations=" + locations +
                ", languages=" + languages +
                ", genres=" + genres +
                ", isTelevision=" + isTelevision +
                ", isNetflix=" + isNetflix +
                ", isIndependent=" + isIndependent +
                '}';
    }

    public static class Builder{

       private final Movie movie;

        public Builder() {
            this.movie = new Movie();
        }

        public Builder setTitle(String title) {
            this.movie.title = title;
            return this;
        }

        public Builder setYear(int year) {
            this.movie.year = year;
            return this;
        }

        public Builder setDirector(Person director) {
            this.movie.director = director;
            return this;
        }

        public Builder setWriter(Person writer) {
            this.movie.writer = writer;
            return this;
        }

        public Builder setSeries(String series) {
            this.movie.series = series;
            return this;
        }

        public Builder setCast(List<Person> cast) {
            this.movie.cast = cast;
            return this;
        }

        public Builder setLocations(List<Place> locations) {
            this.movie.locations = locations;
            return this;
        }

        public Builder setLanguages(List<String> languages) {
            this.movie.languages = languages;
            return this;
        }

        public Builder setGenres(List<String> genres) {
            this.movie.genres = genres;
            return this;
        }

        public Builder setTelevision(boolean television) {
            movie.isTelevision = television;
            return this;
        }

        public Builder setNetflix(boolean netflix) {
            movie.isNetflix = netflix;
            return this;
        }

        public Builder setIndependent(boolean independent) {
            movie.isIndependent = independent;
            return this;
        }

        public Movie build(){
           return movie;
        }
    }
}
