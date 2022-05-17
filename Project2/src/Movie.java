public class Movie implements Comparable<Movie>{ //klasa przechowujaca informacje o filmie

    public String name;
    public Float rating;

    public Movie(String name, float rating) {
        this.name = name;
        this.rating = rating;
    }

    @Override
    public int compareTo(Movie other) { //porownanie filmow na podstawie rankingu i nazwy w celu nie przeciazania pamieci
        return 2*rating.compareTo(other.rating) + (int)Math.signum(name.compareTo(other.name));
    }
    @Override
    public String toString() {
        return "Name: " + name + " Rating: " + rating + "\n";
    }
}
