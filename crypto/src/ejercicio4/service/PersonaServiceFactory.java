package ejercicio4.service;

public abstract class PersonaServiceFactory {
	public static PersonaService createPersonaService() {
		return new PersonaServiceImpl();
	}
}
