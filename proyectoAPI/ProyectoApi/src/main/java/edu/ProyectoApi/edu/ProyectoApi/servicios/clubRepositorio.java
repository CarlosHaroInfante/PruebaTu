package edu.ProyectoApi.edu.ProyectoApi.servicios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio que permite realizar operaciones CRUD
 * @author jpribio - 29/10/24
 */
@Repository
public interface clubRepositorio extends JpaRepository<entidadClub, Long>{
	
	 entidadClub findByNombreClub(String nombreClub);

}
