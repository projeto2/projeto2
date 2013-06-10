package venda;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import bd.Conexao;

public class PedidoLocalDAO 
{
	private Session session;
	
	public void salvar(PedidoLocal pedido)
	{
		session = Conexao.getSession();
		session.getTransaction().begin();
		session.saveOrUpdate(pedido);
		session.getTransaction().commit();
	}
	
	public PedidoLocal obter(Integer id)
	{
		session = Conexao.getSession();
		Criteria criteria = session.createCriteria(PedidoLocal.class);
		criteria.add(Restrictions.eq("id", id));
		criteria.createAlias("itens", "i");
		
		return (PedidoLocal)criteria.uniqueResult();
	}
	
	public List<PedidoLocal> pesquisar(Date inicio, Date fim)
	{
		session = Conexao.getSession();
		Criteria criteria = session.createCriteria(PedidoLocal.class);
		criteria.add(Restrictions.ge("data", inicio));
		criteria.add(Restrictions.le("data", fim));
		
		return criteria.list();
	}
}
