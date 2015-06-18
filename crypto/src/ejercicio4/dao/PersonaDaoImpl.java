package ejercicio4.dao;

import java.util.ArrayList;
import java.util.List;

import ejercicio4.model.Persona;

class PersonaDaoImpl implements PersonaDao {
	private List<Persona> personas;
	private long contador = 1L;

	public PersonaDaoImpl() {
		personas = new ArrayList<Persona>();
	}
	
	@Override
	public void agregar(Persona p) {
		p.setId(contador++);
		personas.add(p);
	}

	@Override
	public void modificar(Persona p) {
		Persona persona = obtener(p.getId());
		if (persona != null)
			personas.set(personas.indexOf(persona), p);
	}

	@Override
	public void eliminar(Long id) {
		Persona p = obtener(id);
		if (p != null)
			personas.remove(p);
	}

	@Override
	public Persona obtener(Long id) {
		for (Persona p : personas)
			if (p.getId().equals(id))
				return p;
		return null;
	}

	@Override
	public List<Persona> obtenerTodos() {
		return personas;
	}

}
