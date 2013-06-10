package principal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import filial.Filial;
import filial.FilialDAO;
import filial.SelecionarView;


import relatorio.RelatorioVendaView;

import estoque.EntradaEstoqueView;
import estoque.PesquisarEstoqueView;

import venda.PedidoView;
import venda.PesquisarPedidoLocalView;
import venda.PesquisarPedidoOnlineView;

public class SistemaController implements ActionListener
{
	private SistemaView sistemaView;
	private SelecionarView selecionarView;
	
	public SistemaController(SistemaView sv)
	{
		this.sistemaView = sv;
	}
	
	public SistemaController(SistemaView s, SelecionarView selecionarView) 
	{
		this.sistemaView = s;
		this.selecionarView = selecionarView;
	}

	public void actionPerformed(ActionEvent evento)
	{
		if(evento.getSource() == sistemaView.getGerarPedido())
		{
			PedidoView pv = new PedidoView(sistemaView);
		}
		else if(evento.getSource() == sistemaView.getPedidoLocal())
		{
			new PesquisarPedidoLocalView(sistemaView);
		}
		else if(evento.getSource() == sistemaView.getPedidoOnline())
		{
			new PesquisarPedidoOnlineView(sistemaView);
		}
		else if(evento.getSource() == sistemaView.getPesquisaEstoque())
		{
			new PesquisarEstoqueView(sistemaView);
		} 
		else if(evento.getSource() == sistemaView.getReceberProduto())
		{
			new EntradaEstoqueView(sistemaView);
		} 
		else if(evento.getSource() == sistemaView.getRelatorioVenda())
		{
			new RelatorioVendaView(sistemaView);
		} 
		else if(selecionarView != null)
		{
			if(evento.getSource() == selecionarView.getEnviar())
			{
				FilialDAO fDAO = new FilialDAO();
				int id = Integer.parseInt(selecionarView.getFilial().getSelectedItem().toString().substring(0, selecionarView.getFilial().getSelectedItem().toString().indexOf("-")));
				Filial f = fDAO.obter(id);
				
				sistemaView.setFilial(f);
				
				selecionarView.dispose();
			}
		}
	}
}
