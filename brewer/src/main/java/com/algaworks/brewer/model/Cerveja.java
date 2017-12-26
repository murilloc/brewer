package com.algaworks.brewer.model;

import org.hibernate.validator.constraints.NotBlank;

public class Cerveja {

	private String sku;
	private String nome;
	private String marca;
	
	@NotBlank
	public String getSku() {
		return sku;
	}

	
	public void setSku(String sku) {
		this.sku = sku;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}
	
}