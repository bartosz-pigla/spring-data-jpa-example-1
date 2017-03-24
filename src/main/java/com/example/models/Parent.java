package com.example.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Created by bartek on 3/24/17.
 */
@Entity
public class Parent {
    private Long id;
    private String firstName;

    @JsonIgnore
    private List<Child> children;

    public Parent() {
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//do generowania klucza glownego
    @Column(name="parentId")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //jezeli nie ma adnotacji @Column(name="firstName") to w bazie danych zostanie wygenerowany atrybut
    // o nazwie takiej jak nazwa tego pola czyli firstName
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @OneToMany(mappedBy="parent", fetch = FetchType.LAZY)
    //FetchType.LAZY przy pobieraniu rodzica nie pobiera dzieci
    //FetchType.EAGER przy pobieranu rodzica pobiera dzieci
    //zobacz problem N+1
    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Parent{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}
