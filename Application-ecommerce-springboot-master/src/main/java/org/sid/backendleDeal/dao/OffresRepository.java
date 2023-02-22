package org.sid.backendleDeal.dao;

import java.util.List;

import org.sid.backendleDeal.entities.Category;
import org.sid.backendleDeal.entities.Offres;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("*")
@RepositoryRestResource
public interface OffresRepository extends JpaRepository<Offres, Long>{
	@RestResource(path="/offresByKeyword")
	public List<Offres>findByNameContains(@Param("mc")String mc);
	
	@RestResource(path="/byNamepage")
	public Page<Offres>findByNameContains(@Param("mc")String mc,Pageable pageable);
	
	
	@RestResource(path= "/selectedOffres")
	public List<Offres>findBySelectedIsTrue();
    
	@RestResource(path= "/promoOffres")
	public List<Offres> findByPromotionIsTrue();

	@RestResource(path= "/dispoOffres")
	public List<Offres>findByAvailableIsTrue();
	@Query("select  o from Offres o where o.name =:name")
	public  List<Offres> findData(String name);
}
