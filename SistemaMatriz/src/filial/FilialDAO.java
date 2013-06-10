package filial;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import bd.Conexao;

public class FilialDAO 
{
	private Session session;
	
	public Filial salvar(Filial filial)
	{
		
		session = Conexao.getSession();
		session.getTransaction().begin();
		filial = (Filial)session.merge(filial);
		session.getTransaction().commit();
		return filial;
	}
	
	public Filial obter(Integer id)
	{
		session = Conexao.getSession();
		return (Filial)session.get(Filial.class, id);
	}
	
	public List<Filial> pesquisar(String nome)
	{
		session = Conexao.getSession();
		Criteria criteria = session.createCriteria(Filial.class);
		criteria.add(Restrictions.like("nome", nome+"%"));
		
		return criteria.list();
	}
}
