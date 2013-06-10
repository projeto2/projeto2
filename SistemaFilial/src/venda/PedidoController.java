package venda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Historico.HistoricoDAO;


public class PedidoController implements ActionListener
{	
	private PesquisarPedidoOnlineView view;
	private StatusView statusView;
	public PedidoController(PesquisarPedidoOnlineView p)
	{
		view = p;
	}
	
	public PedidoController(StatusView v)
	{
		statusView = v;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		if(view != null)
		{
			if(event.getSource() == view.getBuscar())
			{
				if(view.getInicio().getDate() == null)
				{
					JOptionPane.showMessageDialog(null, "Selecione a data inicial!", "Informação", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(view.getFim().getDate() == null)
				{
					JOptionPane.showMessageDialog(null, "Selecione a data final!", "Informação", JOptionPane.ERROR_MESSAGE);
					return;
				}
		
				PedidoDAO pDAO = new PedidoDAO();
				List<Pedido> lista = pDAO.pesquisar(view.getInicio().getDate(), view.getFim().getDate(), view.getSistema().getFilial().getId());
				
				String[] columnNames = {"Código", "Data", "Usuário", "Status"};
				String[][] rows = new String[lista.size()][4];
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				for(int i = 0; i < lista.size(); i++)
				{
					rows[i][0] = String.valueOf(lista.get(i).getId());
					rows[i][1] = sdf.format(lista.get(i).getData());
					rows[i][2] = lista.get(i).getUsuario().getNome();
					rows[i][3] = lista.get(i).getStatus();
				}
				
				DefaultTableModel dt = new DefaultTableModel(rows, columnNames);
				view.getTabela().setModel(dt);
			}
			else if(event.getSource() == view.getStatus())
			{
				int row = view.getTabela().getSelectedRow();
				PedidoDAO pDAO = new PedidoDAO();
				Pedido p = pDAO.obter(Integer.parseInt(view.getTabela().getValueAt(row, 0).toString()));
				
				new StatusView(view.getSistema(), Integer.parseInt(view.getTabela().getValueAt(row, 0).toString()), p.getStatus());
				
				List<Pedido> lista = pDAO.pesquisar(view.getInicio().getDate(), view.getFim().getDate(), view.getSistema().getFilial().getId());
				
				String[] columnNames = {"Código", "Data", "Usuário", "Status"};
				String[][] rows = new String[lista.size()][4];
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				for(int i = 0; i < lista.size(); i++)
				{
					rows[i][0] = String.valueOf(lista.get(i).getId());
					rows[i][1] = sdf.format(lista.get(i).getData());
					rows[i][2] = lista.get(i).getUsuario().getNome();
					rows[i][3] = lista.get(i).getStatus();
				}
				
				DefaultTableModel dt = new DefaultTableModel(rows, columnNames);
				view.getTabela().setModel(dt);
			}
			else if(event.getSource() == view.getVisualizar())
			{
				int row = view.getTabela().getSelectedRow();
				PedidoDAO pDAO = new PedidoDAO();
				Pedido p = pDAO.obter(Integer.parseInt(view.getTabela().getValueAt(row, 0).toString()));
				
				new VisualizarPedidoOnline(view.getSistema(), p);
			}
			
		}
		else if(statusView != null)
		{
			if(event.getSource() == statusView.getSalvar())
			{
				PedidoDAO pDAO = new PedidoDAO();
				Pedido p = pDAO.obter(statusView.getPedido());
				
				if(statusView.getAguardandoPagamento().isSelected())
				{
					p.setStatus("AGUARDANDO");
				}
				else if(statusView.getAprovado().isSelected())
				{
					p.setStatus("APROVADO");
				}
				else if(statusView.getSaiuEntrega().isSelected())
				{
					p.setStatus("SAIU PARA ENTREGA");
				} 
				else if(statusView.getEntregue().isSelected())
				{
					p.setStatus("ENTREGUE");
				} 
				
				p = pDAO.salvar(p);
				
				
				HistoricoDAO.salvar("Alterou o status do pedido, id="+p.getId());
				
				statusView.dispose();
			}
		}
	}
}
