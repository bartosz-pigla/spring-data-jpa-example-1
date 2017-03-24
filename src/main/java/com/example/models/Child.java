package com.example.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by bartek on 3/24/17.
 */
@Entity
public class Child {
    private Long childId;
    private Integer age;

    @JsonIgnore
    private Parent parent;

    public Child(Long childId, Integer age, Parent parent) {
        this.childId = childId;
        this.age = age;
        this.parent = parent;
    }

    public Child() {
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//do generowania klucza glownego
    public Long getChildId() {
        return childId;
    }

    public void setChildId(Long childId) {
        this.childId = childId;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST}, optional = true)
    //dzieki CascadeType.PERSIST automatycznie zapisywany jest child przy zapisywaniu parent: parentRepository.save(child)
    //bez CascadeType.PERSIST trzeba by bylo zapisac najpierw rodzica potem dziecko
    //Przy probie zapisania tylko dziecka zostalby rzucony wyjatek
    //cascade
    //optional =true - kolumna moze miec NULL : domyslnie ta wartosc jest na true takze w tym przykladzie rownie dobrze
    //moglem tego nie uzywac
    @JoinColumn(name = "parentId")
    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Child{" +
                "childId=" + childId +
                ", age=" + age +
                ", parent=" + parent +
                '}';
    }
}
