package departamento;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import bd.Conexao;

public class DepartamentoDAO 
{
	private Session session;
	
	public Departamento salvar(Departamento departamento)
	{
		session = Conexao.getSession();
		session.getTransaction().begin();
		departamento = (Departamento)session.merge(departamento);
		session.getTransaction().commit();
		
		return departamento;
	}
	
	public Departamento obter(Integer id)
	{
		session = Conexao.getSession();
		return (Departamento)session.get(Departamento.class, id);
	}
	
	public List pesquisar(String nome)
	{
		session = Conexao.getSession();
		Criteria criteria = session.createCriteria(Departamento.class);
		criteria.add(Restrictions.like("nome", nome+"%"));
		
		return criteria.list();
	}
}
