package com.example.controllers;

import com.example.models.Child;
import com.example.models.Parent;
import com.example.models.builders.ChildBuilder;
import com.example.repositories.ChildRepository;
import com.example.repositories.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Created by bartek on 3/24/17.
 */
@RestController
public class ExampleController {
    @Autowired
    ChildRepository childRepository;

    @Autowired
    ParentRepository parentRepository;

    @RequestMapping("/")
    public List<Child> create(){
        Parent parent=new Parent();

        Child child1 = new ChildBuilder()
            .setParent(parent)
            .setAge(12)
            .createChild();

        Child child2 = new ChildBuilder()
                .setParent(parent)
                .setAge(19)
                .createChild();

        Child child3 = new ChildBuilder()
                .setParent(parent)
                .setAge(21)
                .createChild();

        List<Child> children=Arrays.asList(child1,child2,child3);
        childRepository.save(children);

        //lub pojedynczo childRepository.save(child1)

        return children;
    }

    @RequestMapping("/deleteParentFromChild/{childId}")
    public Child delete(@PathVariable("childId")long childId){
        Child c=childRepository.findOne(childId);
        c.setParent(null);
        childRepository.save(c);
        return childRepository.findOne(childId);
    }

    @RequestMapping("/readChild/{childId}")
    public Child readChild(@PathVariable("childId")long childId){
        return childRepository.findOne(childId);

    }

    @RequestMapping("/readParent/{parentId}")
    public Parent readParent(@PathVariable("parentId")long parentId){
        Parent p=parentRepository.findOne(parentId);
        return p;
    }

    //przykladowe wywolanie: http://localhost:8080/readAdultChildren/1
    @RequestMapping("/readAdultChildren/{parentId}")
    public List<Child> readAdultChildren(@PathVariable("parentId")Long parentId){
        //return childRepository.findByAgeGreaterThanAndParentEquals(18,parentRepository.findOne(parentId));
        return childRepository.getChildOlderThan2(parentId,18);
    }

    //przykladowe wywolanie: http://localhost:8080/readChildren?pageSize=1&pageNumber=1
    @RequestMapping("/readChildren")
    public List<Child> readAdultChildrenList(@RequestParam(value="pageSize") int pageSize, @RequestParam(value="pageNumber") int pageNumber) {
        return childRepository.findAll(new PageRequest(pageNumber-1,pageSize)).getContent();
    }
}
