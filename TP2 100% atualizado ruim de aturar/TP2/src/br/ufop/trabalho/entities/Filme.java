
package br.ufop.trabalho.entities;

import java.io.Serializable;
import java.util.Objects;

/***
 * Falta implementar
 * @author filipe
 *
 */
public class Filme implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String titulo;
	private int anoLancado;
	private String genero;
	private int quantidadeDvds;
	private int quantidadeBluerays;
	private String tipoFilme;
	public static final String LANCAMENTOS = "lancamento";
	public static final String NOVOS = "novo";
	public static final String ANTIGOS = "antigo";
	
	
	public Filme(String titulo, int anoLancado, String genero, int quantidadeDvds, int quantidadeBluerays,
			String tipoFilme) {
		super();
		this.titulo = titulo;
		this.anoLancado = anoLancado;
		this.genero = genero;
		this.quantidadeDvds = quantidadeDvds;
		this.quantidadeBluerays = quantidadeBluerays;
		setTipoFilme(tipoFilme);
	}

	public Filme(){}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getAnoLancado() {
		return anoLancado;
	}

	public void setAnoLancado(int anoLancado) {
		this.anoLancado = anoLancado;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getQuantidadeDvds() {
		return quantidadeDvds;
	}

	public void setQuantidadeDvds(int quantidadeDvds) {
		this.quantidadeDvds = quantidadeDvds;
	}

	public int getQuantidadeBluerays() {
		return quantidadeBluerays;
	}

	public void setQuantidadeBluerays(int quantidadeBluerays) {
		this.quantidadeBluerays = quantidadeBluerays;
	}

	public String getTipoFilme() {
		return tipoFilme;
	}

	public boolean setTipoFilme(String tipoFilme) {
		this.tipoFilme = tipoFilme;
			return tipoFilme.equalsIgnoreCase(LANCAMENTOS) || tipoFilme.equalsIgnoreCase(NOVOS) || tipoFilme.equalsIgnoreCase(ANTIGOS);
	}

	@Override
	public String toString() {
		return "Titulo: " + titulo + " | Ano lançado: " + anoLancado + " | genero: " + genero + " | Quantida de DVD's: "
				+ quantidadeDvds + " | quantidadeBluerays: " + quantidadeBluerays + " | Tipo: " + tipoFilme;
	}

	@Override
	public int hashCode() {
		return Objects.hash(anoLancado, genero, titulo, quantidadeBluerays, quantidadeDvds, tipoFilme);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Filme other = (Filme) obj;
		return anoLancado == other.anoLancado && Objects.equals(genero, other.genero)
				&& Objects.equals(titulo, other.titulo) && quantidadeBluerays == other.quantidadeBluerays
				&& quantidadeDvds == other.quantidadeDvds && Objects.equals(tipoFilme, other.tipoFilme);
	}
	
	
	
}