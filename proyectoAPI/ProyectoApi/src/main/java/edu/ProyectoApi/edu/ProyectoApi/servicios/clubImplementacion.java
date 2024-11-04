package edu.ProyectoApi.edu.ProyectoApi.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ProyectoApi.edu.ProyectoApi.Dtos.clubDto;
import edu.ProyectoApi.edu.ProyectoApi.utilidades.utiles;

@Service
public class clubImplementacion {
	utiles util = new utiles();

	/**
	 * Inyecta automaticamente el repositorio del club en esta clase
	 * 
	 * @author jpribio - 29/10/24
	 */
	@Autowired
	private clubRepositorio repositorioDelClub;
	
	//private Gson gson = new Gson();

	public clubDto crearClub(clubDto club) {
		entidadClub clubEntidad = new entidadClub();
		// Se establece los atributos del club desde el DTO
		clubEntidad.setNombreClub(club.getNombreClub());
		clubEntidad.setCorreoElectronicoClub(club.getCorreoElectronicoClub());
		clubEntidad.setPaisClub(club.getPaisClub());
		clubEntidad.setLocalidadClub(club.getLocalidadClub());
		clubEntidad.setSedePrincipal(club.getSedePrincipal());
		String contraEncriptada = util.encriptacionContra(club.getContraseniaClub());
		clubEntidad.setContraseniaClub(contraEncriptada);

		// Guarda el club y obtiene el club guardado
		entidadClub guardarClub = repositorioDelClub.save(clubEntidad);
		// Establece el ID del club guardado en el DTO.
		club.setId(guardarClub.getId());
		
		return club;
	}

	public Optional<clubDto> obtenerClub(String nombreClub) {
		 // Busca la entidad de club por el nombre
		 entidadClub clubEntidad = repositorioDelClub.findByNombreClub(nombreClub);
		 // Si se encuentra, convertirla a DTO y devolverla
	        if (clubEntidad != null) {
	            clubDto clubDTO = new clubDto();
	            clubDTO.setId(clubEntidad.getId());
	            clubDTO.setNombreClub(clubEntidad.getNombreClub());
	            clubDTO.setCorreoElectronicoClub(clubEntidad.getCorreoElectronicoClub());
	            clubDTO.setPaisClub(clubEntidad.getPaisClub());
	            clubDTO.setLocalidadClub(clubEntidad.getLocalidadClub());
	            clubDTO.setSedePrincipal(clubEntidad.getSedePrincipal());
	            return Optional.of(clubDTO); // Retorna el DTO envuelto en un Optional
	        } else {
	            return Optional.empty();  // Retorna un Optional vacío si no se encuentra el club
	        }
	}


	public void eliminarClub(String nombreClub) {
		  entidadClub clubEntidad = repositorioDelClub.findByNombreClub(nombreClub);
		// Si se encuentra, se elimina
	        if (clubEntidad != null) {
	            repositorioDelClub.delete(clubEntidad);
	        } else {
	        	 // Lanza una excepción si no se encuentra el club
	            throw new RuntimeException("Club no encontrado");
	        }
	    }


}
