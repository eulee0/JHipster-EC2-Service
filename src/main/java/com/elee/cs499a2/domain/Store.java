package com.elee.cs499a2.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Store.
 */
@Entity
@Table(name = "store")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Store implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "store_name")
    private String storeName;

    @Column(name = "addres")
    private String addres;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "store_book",
               joinColumns = @JoinColumn(name="stores_id", referencedColumnName="ID"),
               inverseJoinColumns = @JoinColumn(name="books_id", referencedColumnName="ID"))
    private Set<Book> books = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public Store storeName(String storeName) {
        this.storeName = storeName;
        return this;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getAddres() {
        return addres;
    }

    public Store addres(String addres) {
        this.addres = addres;
        return this;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public Store books(Set<Book> books) {
        this.books = books;
        return this;
    }

    public Store addBook(Book book) {
        books.add(book);
        book.getStores().add(this);
        return this;
    }

    public Store removeBook(Book book) {
        books.remove(book);
        book.getStores().remove(this);
        return this;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Store store = (Store) o;
        if (store.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, store.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Store{" +
            "id=" + id +
            ", storeName='" + storeName + "'" +
            ", addres='" + addres + "'" +
            '}';
    }
}
