package venda;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import bd.Conexao;

import usuario.Usuario;

public class PedidoDAO 
{
	private Session session;
	
	public Pedido salvar(Pedido pedido)
	{
		session = Conexao.getSession();
		session.getTransaction().begin();
		pedido = (Pedido)session.merge(pedido);
		session.getTransaction().commit();
		
		return pedido;
	}
	
	public List<Pedido> pesquisar(Date inicio, Date fim, Integer id)
	{
		session = Conexao.getSession();
		Criteria criteria = session.createCriteria(Pedido.class);
		criteria.add(Restrictions.ge("data", inicio));
		criteria.add(Restrictions.le("data", fim));
		criteria.createAlias("usuario", "u");
		criteria.createAlias("filial", "f");
		criteria.add(Restrictions.eq("f.id", id));
		
		return criteria.list();
	}
	
	public Pedido obter(Integer id)
	{
		session = Conexao.getSession();
		Criteria criteria = session.createCriteria(Pedido.class);
		criteria.add(Restrictions.eq("id", id));
		criteria.createAlias("endereco", "end");
		criteria.createAlias("itens", "item");
		
		return (Pedido) criteria.uniqueResult();
		
	}
}
