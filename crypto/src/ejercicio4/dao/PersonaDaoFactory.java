package ejercicio4.dao;

public abstract class PersonaDaoFactory {
	public static PersonaDao createPersonaDao() {
		return new PersonaDaoImpl();
	}
}
