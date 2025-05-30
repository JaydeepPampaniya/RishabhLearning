package com.luv2code.springmvc.repository;

import com.luv2code.springmvc.models.HistoryGrade;
import org.springframework.data.repository.CrudRepository;

public interface HistoryGradesDao extends CrudRepository<HistoryGrade,Integer> {
    Iterable<HistoryGrade> findGradeByStudentId(int i);

    void deleteByStudentId(int id);
}
