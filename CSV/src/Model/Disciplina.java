package Model;

public class Disciplina {
	private String cod, nome, desc, cod_area;

	public Disciplina(String cod, String nome, String desc, String cod_area) {
		super();
		this.cod = cod;
		this.nome = nome;
		this.desc = desc;
		this.cod_area = cod_area;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCod_area() {
		return cod_area;
	}

	public void setCod_area(String cod_area) {
		this.cod_area = cod_area;
	}

	@Override
	public String toString() {
		return "Disciplina [cod=" + cod + ", nome=" + nome + ", desc=" + desc + ", cod_area=" + cod_area + "]";
	}
}
