package ejercicio4.service;

import java.util.List;

import ejercicio4.dao.PersonaDao;
import ejercicio4.dao.PersonaDaoFactory;
import ejercicio4.model.Persona;

class PersonaServiceImpl implements PersonaService {
	private PersonaDao personaDao;
	
	public PersonaServiceImpl() {
		personaDao = PersonaDaoFactory.createPersonaDao();
	}

	@Override
	public void agregarPersona(Persona p) {
		personaDao.agregar(p);
	}

	@Override
	public void modificarPersona(Persona p) {
		personaDao.modificar(p);
	}

	@Override
	public void eliminarPersona(Long id) {
		personaDao.eliminar(id);
	}

	@Override
	public Persona obtenerPersona(Long id) {
		return personaDao.obtener(id);
	}

	@Override
	public List<Persona> obtenerPersonas() {
		return personaDao.obtenerTodos();
	}

}
