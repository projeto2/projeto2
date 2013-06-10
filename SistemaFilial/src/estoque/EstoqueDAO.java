package estoque;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import filial.Filial;

import bd.Conexao;

public class EstoqueDAO 
{
	private Session session;
	
	public void salvar(Estoque e)
	{
		session = Conexao.getSession();
		session.getTransaction().begin();
		session.saveOrUpdate(e);
		session.getTransaction().commit();
	}
	
	public List<Estoque> pesquisar(String nome, String descricao, Integer id)
	{
		session = Conexao.getSession();
		Criteria criteria = session.createCriteria(Estoque.class);
		criteria.createAlias("filial", "f");
		criteria.createAlias("produto", "p");
		criteria.add(Restrictions.eq("f.id", id));
		criteria.add(Restrictions.like("p.nome", nome+"%"));
		criteria.add(Restrictions.like("p.descricao", descricao+"%"));
		
		return criteria.list();
	}
	
	public Estoque obter(Integer produto, Integer filial)
	{
		session = Conexao.getSession();
		Criteria criteria = session.createCriteria(Estoque.class);
		criteria.createAlias("filial", "f");
		criteria.createAlias("produto", "p");
		criteria.add(Restrictions.eq("f.id", filial));
		criteria.add(Restrictions.eq("p.id", produto));
		return (Estoque)criteria.uniqueResult();
	}
	
	public void salvarEntradaSaida(EntradaSaidaEstoque obj)
	{
		session = Conexao.getSession();
		session.getTransaction().begin();
		session.saveOrUpdate(obj);
		session.getTransaction().commit();
	}
}
