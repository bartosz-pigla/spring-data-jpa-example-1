package com.example.models.builders;

import com.example.models.Child;
import com.example.models.Parent;

public class ChildBuilder {
    private Long childId;
    private Integer age;
    private Parent parent;

    public ChildBuilder setChildId(Long childId) {
        this.childId = childId;
        return this;
    }

    public ChildBuilder setAge(Integer age) {
        this.age = age;
        return this;
    }

    public ChildBuilder setParent(Parent parent) {
        this.parent = parent;
        return this;
    }

    public Child createChild() {
        return new Child(childId, age, parent);
    }
}