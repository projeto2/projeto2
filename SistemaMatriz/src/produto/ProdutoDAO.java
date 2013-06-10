package produto;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import bd.Conexao;

public class ProdutoDAO 
{
	private Session session;
	
	public Produto salvar(Produto produto)
	{
		session = Conexao.getSession();
		session.getTransaction().begin();
		produto = (Produto)session.merge(produto);
		session.getTransaction().commit();
		
		return produto;
	}
	
	public Produto obter(Integer id)
	{
		session = Conexao.getSession();
		Criteria criteria = session.createCriteria(Produto.class);
		criteria.add(Restrictions.eq("id", id));
		criteria.createAlias("departamento", "d");
		
		return (Produto)criteria.uniqueResult();
	}
	
	public List<Produto> pesquisar(String nome, String descricao)
	{
		session = Conexao.getSession();
		Criteria criteria = session.createCriteria(Produto.class);
		criteria.add(Restrictions.like("nome", nome+"%"));
		criteria.add(Restrictions.like("descricao", descricao+"%"));
		criteria.createAlias("departamento", "d");
		
		return criteria.list();
	}
}
