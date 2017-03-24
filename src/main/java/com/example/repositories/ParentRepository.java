package com.example.repositories;

import com.example.models.Child;
import com.example.models.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by bartek on 3/24/17.
 */
public interface ParentRepository extends JpaRepository<Parent,Long> {

}
