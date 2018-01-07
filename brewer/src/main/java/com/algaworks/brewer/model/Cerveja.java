package com.algaworks.brewer.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.algaworks.brewer.validation.SKU;

@Entity
@Table(name = "cerveja")
public class Cerveja implements Serializable {
	/**
	 * Serialização
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * Propriedades
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@SKU
	@NotBlank(message = "O campo SKU é obrigatório")
	private String sku;

	@NotBlank(message = "O campo Nome é obrigatório")
	@Size(max = 50, message = "O campo Nome deve conter no máximo 50 caracteres")
	private String nome;

	@NotBlank(message = "O campo Descrição é obrigatório")
	@Size(max = 50, message = "O campo Descrição deve conter no máximo 50 caracteres")
	private String descricao;

	@NotNull(message = "O campo Valor é obrigatório")
	@DecimalMin(value = "0.01", message = "O valor mínimo da cerveja deve ser de R$0,01")
	@DecimalMax(value = "99999999.99", message = "O valor da cervaje não deve ultrapassar R$9.999.999,99")
	private BigDecimal valor;

	@NotNull(message = "O campo Teor alcoólico é obrigatório")
	@Column(name = "teor_alcoolico")
	@DecimalMax(value = "100", message = "O valor do teor alcoólico deve ser menor que 100")
	private BigDecimal teorAlcoolico;

	@NotNull(message = "O campo Comissão é obrigatório")
	@DecimalMax(value = "100", message = "O valor da comissão deve ser menor que 100")
	private BigDecimal comissao;

	@NotNull(message = "O campo Estoque é obrigatório")
	@Max(value = 9999, message = "A quantidade de estoque deve ser menor que 9.999")
	@Column(name = "quantidade_estoque")
	private Integer quantidadeEstoque;

	/*
	 * Relacionamentos
	 */
	@NotNull(message = "O campo Origem é obrigatório")
	@Enumerated(EnumType.STRING)
	private Origem origem;

	@NotNull(message = "O campo Sabor é obrigatório")
	@Enumerated(EnumType.STRING)
	private Sabor sabor;

	@NotNull(message = "O campo Estilo é obrigatório")
	@ManyToOne
	@JoinColumn(name = "codigo_estilo")
	private Estilo estilo;

	// Metodos de acesso
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getTeorAlcoolico() {
		return teorAlcoolico;
	}

	public void setTeorAlcoolico(BigDecimal teorAlcoolico) {
		this.teorAlcoolico = teorAlcoolico;
	}

	public BigDecimal getComissao() {
		return comissao;
	}

	public void setComissao(BigDecimal comissao) {
		this.comissao = comissao;
	}

	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public Origem getOrigem() {
		return origem;
	}

	public void setOrigem(Origem origem) {
		this.origem = origem;
	}

	public Sabor getSabor() {
		return sabor;
	}

	public void setSabor(Sabor sabor) {
		this.sabor = sabor;
	}

	public Estilo getEstilo() {
		return estilo;
	}

	public void setEstilo(Estilo estilo) {
		this.estilo = estilo;
	}

	/*
	 * Hash & Equal
	 */

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cerveja other = (Cerveja) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}