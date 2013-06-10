package bd;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import Historico.Historico;

import departamento.Departamento;
import estoque.Estoque;

import pedido.Item;
import pedido.Pedido;
import produto.Produto;
import usuario.Endereco;
import usuario.Usuario;

import filial.Filial;

public class Conexao 
{
	private static Session session;
	
	static
	{
		Configuration configuration = new Configuration();
		configuration.addAnnotatedClass(Filial.class);
		configuration.addAnnotatedClass(Produto.class);
		configuration.addAnnotatedClass(Departamento.class);
		configuration.addAnnotatedClass(Pedido.class);
		configuration.addAnnotatedClass(Item.class);
		configuration.addAnnotatedClass(Usuario.class);
		configuration.addAnnotatedClass(Endereco.class);
		configuration.addAnnotatedClass(Estoque.class);
		configuration.addAnnotatedClass(Historico.class);
		
		configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
		
		configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
		configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/sistema?autoReconnect=true");
		configuration.setProperty("hibernate.connection.username", "root");
		configuration.setProperty("hibernate.connection.password", "root");
		
		configuration.setProperty("hibernate.hbm2ddl.auto", "update");
		
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		
		session = configuration.buildSessionFactory(serviceRegistry).openSession();
	}
	
	public static Session getSession()
	{
		return session;
	}
}
