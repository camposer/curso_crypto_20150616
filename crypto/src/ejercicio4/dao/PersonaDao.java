package ejercicio4.dao;

import java.util.List;

import ejercicio4.model.Persona;

public interface PersonaDao {
	void agregar(Persona p);
	void modificar(Persona p);
	void eliminar(Long id);
	Persona obtener(Long id);
	List<Persona> obtenerTodos();
}
