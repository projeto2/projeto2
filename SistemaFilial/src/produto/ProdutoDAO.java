package produto;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import bd.Conexao;

public class ProdutoDAO 
{
	private Session session;
	
	public Produto obter(Integer chave)
	{
		session = Conexao.getSession();
		return (Produto) session.get(Produto.class, chave);
	}
	
	public List<Produto> pesquisar(String nome)
	{
		session = Conexao.getSession();
		Criteria criteria = session.createCriteria(Produto.class);
		criteria.add(Restrictions.like("nome", nome+"%"));
		
		return criteria.list();
	}
}
