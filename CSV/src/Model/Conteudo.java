package Model;

public class Conteudo {
	private String cod, nome, desc, disc_aux;

	public Conteudo(String cod, String nome, String desc, String disc_aux) {
		super();
		this.cod = cod;
		this.nome = nome;
		this.desc = desc;
		this.disc_aux = disc_aux;
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

	public String getDisc_aux() {
		return disc_aux;
	}

	public void setDisc_aux(String disc_aux) {
		this.disc_aux = disc_aux;
	}	
}
