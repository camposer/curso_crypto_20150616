package ejercicio4.service;

import java.util.List;

import ejercicio4.model.Persona;

public interface PersonaService {
	void agregarPersona(Persona p);
	void modificarPersona(Persona p);
	void eliminarPersona(Long id);
	Persona obtenerPersona(Long id);
	List<Persona> obtenerPersonas();
}
