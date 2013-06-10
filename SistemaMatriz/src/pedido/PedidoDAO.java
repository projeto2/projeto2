package pedido;

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
	
	public void salvar(Pedido pedido)
	{
		session = Conexao.getSession();
		session.getTransaction().begin();
		session.saveOrUpdate(pedido);
		session.getTransaction().commit();
	}
	
	public List<Pedido> pesquisar(Date inicio, Date fim, boolean encaminhado)
	{
		session = Conexao.getSession();
		Criteria criteria = session.createCriteria(Pedido.class);
		criteria.add(Restrictions.ge("data", inicio));
		criteria.add(Restrictions.le("data", fim));
		criteria.add(Restrictions.eq("encaminhado", encaminhado));
		criteria.createAlias("usuario", "u");
		
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
