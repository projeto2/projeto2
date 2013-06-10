package departamento;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Historico.HistoricoDAO;

import filial.Filial;
import filial.FilialDAO;
import filial.FilialView;

import principal.SistemaView;

public class DepartamentoController implements ActionListener
{
	private DepartamentoView departamentoView; 
	private PesquisarDepartamentoView pesquisarView;
	private SistemaView sistema;
	
	public DepartamentoController(DepartamentoView d)
	{
		departamentoView = d;
	}
	
	public DepartamentoController(PesquisarDepartamentoView p, SistemaView s)
	{
		pesquisarView = p;
		sistema = s;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		if(departamentoView != null)
		{
			if(event.getSource() == departamentoView.getSalvar())
			{
				Departamento d = new Departamento();
				
				if(departamentoView.getDepartamento()!= null)
					d.setId(departamentoView.getDepartamento().getId());
				
				d.setNome(departamentoView.getTxtNome().getText());
				
				DepartamentoDAO dDAO = new DepartamentoDAO();
				d = dDAO.salvar(d);
				
				departamentoView.getTxtNome().setText("");
				JOptionPane.showMessageDialog(null, "Departamento cadastrado com sucesso!", "Informação", JOptionPane.INFORMATION_MESSAGE);
				
				HistoricoDAO.salvar("Salvou o departamento, id = " + d.getId());
				if(departamentoView.getDepartamento() != null)
					departamentoView.dispose();
			}
		}
		else if(pesquisarView != null)
		{
			if(event.getSource()== pesquisarView.getBuscar())
			{
				DepartamentoDAO departamentoDAO = new DepartamentoDAO();
				List<Departamento> lista = departamentoDAO.pesquisar(pesquisarView.getTxtPesquisar().getText());
				
				Object[][] rows = new Object[lista.size()][2];
				
				for(int i = 0; i < lista.size(); i++)
				{
					rows[i][0] = String.valueOf(lista.get(i).getId());
					rows[i][1] = lista.get(i).getNome();
				}
				
				String[] columnNames = {"Código","Nome"};
				
				DefaultTableModel tableModel = new DefaultTableModel(rows, columnNames);
				
				DefaultTableCellRenderer render = new DefaultTableCellRenderer();
				render.setHorizontalAlignment(JLabel.CENTER);
				
				pesquisarView.getTabela().setModel(tableModel);
				pesquisarView.getTabela().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				pesquisarView.getTabela().getColumnModel().getColumn(0).setPreferredWidth(60);
				pesquisarView.getTabela().getColumnModel().getColumn(0).setCellRenderer(render);
				
				pesquisarView.getTabela().getColumnModel().getColumn(1).setPreferredWidth(298);
				
			}
			else if(event.getSource() == pesquisarView.getAlterar())
			{
				int row = pesquisarView.getTabela().getSelectedRow();
				Integer id = Integer.parseInt(pesquisarView.getTabela().getValueAt(row, 0).toString());
				
				DepartamentoDAO departamentoDAO = new DepartamentoDAO();
				Departamento departamento = departamentoDAO.obter(id);
				
				new DepartamentoView(sistema, "Alterar Departamento", departamento);
				
				List<Departamento> lista = departamentoDAO.pesquisar(pesquisarView.getTxtPesquisar().getText());
				
				Object[][] rows = new Object[lista.size()][2];
				
				for(int i = 0; i < lista.size(); i++)
				{
					rows[i][0] = String.valueOf(lista.get(i).getId());
					rows[i][1] = lista.get(i).getNome();
				}
				
				String[] columnNames = {"Código","Nome"};
				
				DefaultTableModel tableModel = new DefaultTableModel(rows, columnNames);
				
				DefaultTableCellRenderer render = new DefaultTableCellRenderer();
				render.setHorizontalAlignment(JLabel.CENTER);
				
				pesquisarView.getTabela().setModel(tableModel);
				pesquisarView.getTabela().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				pesquisarView.getTabela().getColumnModel().getColumn(0).setPreferredWidth(60);
				pesquisarView.getTabela().getColumnModel().getColumn(0).setCellRenderer(render);
				
				pesquisarView.getTabela().getColumnModel().getColumn(1).setPreferredWidth(298);
			}
		}
	}
	
}
