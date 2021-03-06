package estoque;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import produto.Produto;
import filial.Filial;

@Entity
public class Estoque 
{
	@Id @GeneratedValue
	private Integer id;
	
	private Integer quantidade;
	
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private Filial filial;
	
	@OneToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private Produto produto;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Filial getFilial() {
		return filial;
	}
	public void setFilial(Filial filial) {
		this.filial = filial;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	
}
