package principal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import bd.Conexao;
import bd.ConexaoReport;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import pedido.PesquisarPedidoView;
import produto.PesquisarProdutoView;
import produto.ProdutoView;

import departamento.DepartamentoView;
import departamento.PesquisarDepartamentoView;
import filial.FilialView;
import filial.PesquisarFilialView;



public class SistemaController implements ActionListener
{
	private SistemaView sistemaView;
	
	public SistemaController(SistemaView sv)
	{
		this.sistemaView = sv;
	}
	
	public void actionPerformed(ActionEvent evento)
	{
		if(evento.getSource() == sistemaView.getCadastrarFilial())
		{
			new FilialView(sistemaView, "Cadastrar Filial", null);
		}
		else if(evento.getSource() == sistemaView.getPesquisarFilial())
		{
			new PesquisarFilialView(sistemaView);
		}
		else if(evento.getSource() == sistemaView.getCadastrarDepartamento())
		{
			new DepartamentoView(sistemaView, "Cadastrar Departamento", null);
		}
		else if(evento.getSource() == sistemaView.getPesquisarDepartamento())
		{
			new PesquisarDepartamentoView(sistemaView);
		}
		else if(evento.getSource() == sistemaView.getCadastrarProduto())
		{
			new ProdutoView(sistemaView, "Cadastrar Produto", null);
		}
		else if(evento.getSource() == sistemaView.getPesquisarProduto())
		{
			new PesquisarProdutoView(sistemaView);
		}
		else if(evento.getSource() == sistemaView.getPesquisarPedido())
		{
			new PesquisarPedidoView(sistemaView);
		}
		else if(evento.getSource() == sistemaView.getRelatorioEstoque())
		{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("SUBREPORT_DIR", "C:\\Relatorio\\");
			
			
			try 
			{
				JasperPrint print = JasperFillManager.fillReport("c:\\Relatorio\\estoque.jasper", map, ConexaoReport.obterConexao());
				JasperViewer view = new JasperViewer(print,false);
				view.setVisible(true);
				view.setSize(850, 600);
				
			} 
			catch (JRException e) 
			{
				e.printStackTrace();
			}
		}
	}
}
