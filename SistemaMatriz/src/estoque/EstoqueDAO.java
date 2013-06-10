package estoque;

import org.hibernate.Session;

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
}
