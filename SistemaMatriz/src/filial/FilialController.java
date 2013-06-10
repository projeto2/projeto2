package filial;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import Historico.HistoricoDAO;

import estoque.Estoque;
import estoque.EstoqueDAO;

import principal.SistemaView;
import produto.Produto;
import produto.ProdutoDAO;

public class FilialController implements ActionListener 
{
	private FilialView filialView;
	private PesquisarFilialView pesquisaView;
	private SistemaView sistema;
	
	public FilialController(FilialView f)
	{
		filialView = f;
	}
	
	public FilialController(PesquisarFilialView p, SistemaView s)
	{
		pesquisaView = p;
		sistema = s;
	}
	
	public void actionPerformed(ActionEvent event)
	{
		try
		{
			if(filialView != null)
			{
				if(event.getSource() == filialView.getSalvar())
				{
					Filial filial = new Filial();
					
					if(filialView.getFilial() != null)
						filial.setId(filialView.getFilial().getId());
					filial.setNome(filialView.getTxtNome().getText());
					
					if(filialView.getAtivo().isSelected())
						filial.setStatus(true);
					else 
						filial.setStatus(false);
					
					FilialDAO fDao = new FilialDAO();
					filial = fDao.salvar(filial);
					
					HistoricoDAO.salvar("Salvou a filial, id=" + filial.getId());
					
					if(filialView.getFilial() == null)
					{
						ProdutoDAO pDAO = new ProdutoDAO();
						List<Produto> produtos = pDAO.pesquisar("", "");
						EstoqueDAO eDAO = new EstoqueDAO();
						
						for(int i = 0; i < produtos.size(); i++)
						{
							Estoque e = new Estoque();
							e.setFilial(filial);
							e.setProduto(produtos.get(i));
							e.setQuantidade(0);
							
							eDAO.salvar(e);
							
						}
					}
					filialView.getTxtNome().setText("");
					filialView.getAtivo().setSelected(true);
					
					JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!", "Informação", JOptionPane.INFORMATION_MESSAGE);
					
					if(filialView.getFilial() != null)
						filialView.dispose();
				}
			}
			else if(pesquisaView != null)
			{
				if(event.getSource()== pesquisaView.getBuscar())
				{
					FilialDAO filialDAO = new FilialDAO();
					List<Filial> lista = filialDAO.pesquisar(pesquisaView.getTxtPesquisar().getText());
					
					Object[][] rows = new Object[lista.size()][3];
					
					for(int i = 0; i < lista.size(); i++)
					{
						rows[i][0] = String.valueOf(lista.get(i).getId());
						rows[i][1] = lista.get(i).getNome();
						if(lista.get(i).isStatus())
							rows[i][2] = "ATIVO";
						else
							rows[i][2] = "DESATIVADO";
						
					}
					
					String[] columnNames = {"Código","Nome", "Status"};
					
					DefaultTableModel tableModel = new DefaultTableModel(rows, columnNames);
					
					DefaultTableCellRenderer render = new DefaultTableCellRenderer();
					render.setHorizontalAlignment(JLabel.CENTER);
					
					pesquisaView.getTabela().setModel(tableModel);
					pesquisaView.getTabela().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					pesquisaView.getTabela().getColumnModel().getColumn(0).setPreferredWidth(60);
					pesquisaView.getTabela().getColumnModel().getColumn(0).setCellRenderer(render);
					
					pesquisaView.getTabela().getColumnModel().getColumn(1).setPreferredWidth(200);
					pesquisaView.getTabela().getColumnModel().getColumn(2).setPreferredWidth(98);
					pesquisaView.getTabela().getColumnModel().getColumn(2).setCellRenderer(render);
				}
				else if(event.getSource() == pesquisaView.getAlterar())
				{
					int row = pesquisaView.getTabela().getSelectedRow();
					Integer id = Integer.parseInt(pesquisaView.getTabela().getValueAt(row, 0).toString());
					
					FilialDAO filialDAO = new FilialDAO();
					Filial filial = filialDAO.obter(id);
					
					new FilialView(sistema, "Alterar Filial", filial);
					
					List<Filial> lista = filialDAO.pesquisar(pesquisaView.getTxtPesquisar().getText());
					
					Object[][] rows = new Object[lista.size()][3];
					
					for(int i = 0; i < lista.size(); i++)
					{
						rows[i][0] = String.valueOf(lista.get(i).getId());
						rows[i][1] = lista.get(i).getNome();
						if(lista.get(i).isStatus())
							rows[i][2] = "ATIVO";
						else
							rows[i][2] = "DESATIVADO";
						
					}
					
					String[] columnNames = {"Código","Nome", "Status"};
					
					DefaultTableModel tableModel = new DefaultTableModel(rows, columnNames);
					
					DefaultTableCellRenderer render = new DefaultTableCellRenderer();
					render.setHorizontalAlignment(JLabel.CENTER);
					
					pesquisaView.getTabela().setModel(tableModel);
					pesquisaView.getTabela().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					pesquisaView.getTabela().getColumnModel().getColumn(0).setPreferredWidth(60);
					pesquisaView.getTabela().getColumnModel().getColumn(0).setCellRenderer(render);
					
					pesquisaView.getTabela().getColumnModel().getColumn(1).setPreferredWidth(200);
					pesquisaView.getTabela().getColumnModel().getColumn(2).setPreferredWidth(98);
					pesquisaView.getTabela().getColumnModel().getColumn(2).setCellRenderer(render);
				}
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Não foi possível realizar a operação!", "Informação", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}
