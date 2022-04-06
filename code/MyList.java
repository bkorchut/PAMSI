package code;

import java.util.*;
/* Lista wiazana dwukierunkowa ze straznikiem
Z zaimplementowanym iteratorem jednokierunkowym
Z mozliwoscia zmiany kolejnosci elementow, dodawaniem ich i usuwaniem
 */
public class MyList <E> implements Iterable <E> {

    @Override
    public Iterator <E> iterator() { // O(1)
        return new Iterator <E> () {

            Element <E> current = sentinel;
            @Override
            public boolean hasNext() {
                return current.next != sentinel;
            } // O(1)

            @Override
            public E next() { // O(1)
                if (current.next == sentinel) {
                    throw new NoSuchElementException("Iterator poza zakresem!");
                }
                current = current.next;
                return current.e;
            }
        };
    }

    private class Element <E> { //klasa wezla w liscie
        public E e;
        public Element <E> next = null;
        public Element <E> previous = null;

        public Element (E e) {this.e = e;} // O(1)
        public Element (E e, Element <E> next, Element <E> previous) { // O(1)
            this.e = e;
            this.next = next;
            this.previous = previous;
        }
    }

    Element <E> sentinel;
    private int size = 0;


    public MyList () { // O(1)
        sentinel = new Element <E> (null);
        sentinel.next = sentinel;
        sentinel.previous = sentinel;
    }

    public int size() {return size;} //funkcja zwracajaca rozmiar listy  O(1)

    public void add(E e) { // funkcja dodajaca elementy do listy    // O(1)
        Element <E> element = new Element <E> (e);
        size ++;

        if (sentinel.previous == sentinel) {
            sentinel.next = element;
            sentinel.previous = element;
            element.next = sentinel;
            element.previous = sentinel;
        } else {
            sentinel.previous.next = element;
            element.previous = sentinel.previous;
            element.next = sentinel;
            sentinel.previous = element;
        }
    }

    public E get(int position) { // funkcja zwracajaca element na podanej pozycji // O(n)
        if (position >= size)
            throw new ArrayIndexOutOfBoundsException();

        Iterator<E> iterator = this.iterator();
        for (int i = 0; i < position; i++)
            iterator.next();
        return iterator.next();
    }

    public void delete (int position) { // funkcja usuwajaca element z podanej pozycji // O(n)
        if (position >= size)
            throw new ArrayIndexOutOfBoundsException();

        Element<E> toDelete = sentinel;
        for (int i = 0; i <= position; i++)
            toDelete = toDelete.next;

        toDelete.previous.next = toDelete.next;
        toDelete.next.previous = toDelete.previous;
        size --;
    }

    public MyList <E> shuffle () { //funkcja zmieniajaca kolejnosc elementow w liscie w losowej kolejnosci // O(n^2)
        MyList<E> result = new MyList<E>();
        Random random = new Random();

        while (this.size > 0) {                     // O(n^2)
            int position = random.nextInt(size);    // O(1)
            E element = get(position);              // O(n)
            delete(position);                       // O(n)
            result.add(element);                    // O(1)
        }

        return result;
    }

    @Override
    public String toString () { //funkcja zwracajaca string w formacie [ 1,2,3 ] // O(n)
        String result = "[";
        if (size == 0)
            return result + "]";


        for (E element : this) // O(n)
            result += element.toString() + ", ";

        return result.substring(0, result.length() - 2) + "]"; // O(n)
    }
}
