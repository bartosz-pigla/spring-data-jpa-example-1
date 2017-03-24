package com.example.repositories;

import com.example.models.Child;
import com.example.models.Parent;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by bartek on 3/24/17.
 */
public interface ChildRepository extends JpaRepository<Child,Long> {
    //Declare query at the query method using @Query

    @Query("SELECT child FROM Child child WHERE child.parent.id=:parametr1 AND child.age > :parametr2")
    List<Child> getChildOlderThan(@Param("parametr1")Long parentId, @Param("parametr2")Integer age);

    //Declare query at the query method using @Query
    //to samo
    @Query("SELECT child FROM Child child WHERE child.parent.id=?1 AND child.age > ?2")
    List<Child> getChildOlderThan2(Long parentId, Integer age);

    //Declare query at the query method using @Query
    //to samo
    @Query("SELECT child FROM Child child WHERE child.parent=?1 AND child.age > ?2")
    List<Child> getChildOlderThan3(Parent parent, Integer age);

    //Query creation from method names
    //to samo
    List<Child> findByAgeGreaterThanAndParentEquals(Integer age,Parent parent);

    //Query creation from method names
    //to samo
    List<Child> findByAgeGreaterThanAndParent_IdEquals(Integer age,Long parentId);


}

