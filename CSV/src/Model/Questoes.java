package Model;

public class Questoes {
	private String cod, enunciado, referencia;

	public Questoes(String cod, String enunciado, String referencia) {
		super();
		this.cod = cod;
		this.enunciado = enunciado;
		this.referencia = referencia;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	@Override
	public String toString() {
		return "Cod: " + cod + "\nENU: " + enunciado + "\nREF: " + referencia + ".";
	}

}
