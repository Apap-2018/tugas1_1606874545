package com.apap.tugas1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;



@Repository
public interface JabatanDB extends JpaRepository<JabatanModel, Long> {
	Optional<JabatanModel> findById(long id);
	
	
/*	@Query(value=
			"select top 1 id from (select sum(score) scoresum, student.id from studypoint join student on student.id=studypoint.student_id group by student.id) order by scoresum desc", nativeQuery=true)
   
	select * 
	from PegawaiModel
	where age = (PegawaiModel.idselect max(age) as max_age from members);
	limit 1
	
	Integer findStudentIdWithMaxScore();*/
	
	
}
