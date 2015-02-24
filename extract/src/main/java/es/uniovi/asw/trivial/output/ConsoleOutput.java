package es.uniovi.asw.trivial.output;

public class ConsoleOutput implements Output {

	@Override
	public void save(String out) {
		System.out.println(out);
	}
}
