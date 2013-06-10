package Historico;

import java.util.Date;

import org.hibernate.Session;

import bd.Conexao;

public class HistoricoDAO 
{
	public static void salvar(String descricao)
	{
		Session session = Conexao.getSession();
		session.getTransaction().begin();
		
		Historico h = new Historico();
		h.setData(new Date());
		h.setDescricao(descricao);
		
		session.save(h);
		
		session.getTransaction().commit();
	}
}
