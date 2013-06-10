package pedido;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Historico.Historico;
import Historico.HistoricoDAO;


import filial.Filial;
import filial.FilialDAO;

public class PedidoController implements ActionListener
{
	private PesquisarPedidoView view;
	public EncaminharView encaminhar;
	
	public PedidoController(PesquisarPedidoView p)
	{
		view = p;
	}
	
	public PedidoController(EncaminharView e)
	{
		encaminhar = e;
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
				List<Pedido> lista = pDAO.pesquisar(view.getInicio().getDate(), view.getFim().getDate(), view.getEncaminhado().isSelected());
				
				String[] columnNames = {"Código", "Data", "Usuário", "Status"};
				String[][] rows = new String[lista.size()][4];
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				for(int i = 0; i < lista.size(); i++)
				{
					rows[i][0] = String.valueOf(lista.get(i).getId());
					rows[i][1] = sdf.format(lista.get(i).getData());
					rows[i][2] = lista.get(i).getUsuario().getNome();
					
					if(lista.get(i).isEncaminhado())
						rows[i][3] = "Encaminhado";
					else
						rows[i][3] = "Aguardando";
				}
				
				DefaultTableModel dt = new DefaultTableModel(rows, columnNames);
				view.getTabela().setModel(dt);
			}
			else if(event.getSource() == view.getEncaminhar())
			{
				int row = view.getTabela().getSelectedRow();
				new EncaminharView(view.getSistema(), Integer.parseInt(view.getTabela().getValueAt(row, 0).toString()));
				
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
				List<Pedido> lista = pDAO.pesquisar(view.getInicio().getDate(), view.getFim().getDate(), view.getEncaminhado().isSelected());
				
				String[] columnNames = {"Código", "Data", "Usuário", "Status"};
				String[][] rows = new String[lista.size()][4];
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				for(int i = 0; i < lista.size(); i++)
				{
					rows[i][0] = String.valueOf(lista.get(i).getId());
					rows[i][1] = sdf.format(lista.get(i).getData());
					rows[i][2] = lista.get(i).getUsuario().getNome();
					
					if(lista.get(i).isEncaminhado())
						rows[i][3] = "Encaminhado";
					else
						rows[i][3] = "Aguardando";
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
		else if(encaminhar != null)
		{
			if(event.getSource() == encaminhar.getEnviar())
			{
				PedidoDAO pDAO = new PedidoDAO();
				Pedido pedido = pDAO.obter(encaminhar.getPedido());
				
				FilialDAO fDAO = new FilialDAO();
				int id = Integer.parseInt(encaminhar.getFilial().getSelectedItem().toString().substring(0, encaminhar.getFilial().getSelectedItem().toString().indexOf("-")));
				Filial f = fDAO.obter(id);
				
				pedido.setEncaminhado(true);
				pedido.setFilial(f);
				
				pDAO.salvar(pedido);
				
				HistoricoDAO.salvar("Encaminhou o pedido, id=" + pedido.getId());
				
				JOptionPane.showMessageDialog(null, "Pedido encaminhado com sucesso!", "Informação", JOptionPane.INFORMATION_MESSAGE);
				encaminhar.dispose();
			}
		}
	}

}
