package produto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Historico.HistoricoDAO;

import departamento.Departamento;
import departamento.DepartamentoDAO;
import estoque.Estoque;
import estoque.EstoqueDAO;
import filial.Filial;
import filial.FilialDAO;

public class ProdutoController implements ActionListener 
{
	private ProdutoView view;
	private PesquisarProdutoView pesquisaView;
	
	public ProdutoController(ProdutoView p)
	{
		view = p;
	}
	
	public ProdutoController(PesquisarProdutoView p)
	{
		pesquisaView = p;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		if(view != null)
		{
			if(event.getSource() == view.getSalvar())
			{
				Produto produto = new Produto();
				if(view.getProduto() != null)
					produto.setId(view.getProduto().getId());
				
				produto.setNome(view.getTxtNome().getText());
				produto.setDescricao(view.getTxtDescricao().getText());
				produto.setValor(Double.parseDouble(view.getTxtValor().getText().replace(",", ".")));
				
				DepartamentoDAO dDao = new DepartamentoDAO();
				Departamento d = dDao.obter(Integer.parseInt(view.getcDepartamento().getSelectedItem().toString().subSequence(0, view.getcDepartamento().getSelectedItem().toString().indexOf("-")).toString()));
				
				produto.setDepartamento(d);
				
				ProdutoDAO pDao = new ProdutoDAO();
				produto = pDao.salvar(produto);
				
				HistoricoDAO.salvar("Salvou o produto, id=" + produto.getId());
				if(view.getProduto() == null)
				{
					FilialDAO fDAO = new FilialDAO();
					List<Filial> filiais = fDAO.pesquisar("");
					
					EstoqueDAO eDAO = new EstoqueDAO();
					for(int i = 0; i < filiais.size(); i++)
					{
						Estoque e = new Estoque();
						e.setFilial(filiais.get(i));
						e.setProduto(produto);
						e.setQuantidade(0);
						
						eDAO.salvar(e);
					}
				}
				JOptionPane.showMessageDialog(null, "Produto Cadastrado com sucesso!", "Informação", JOptionPane.INFORMATION_MESSAGE);
				
				view.getTxtNome().setText("");
				view.getTxtDescricao().setText("");
				view.getTxtValor().setText("");
				view.getcDepartamento().setSelectedIndex(0);
				
				if(view.getProduto() != null)
					view.dispose();
			}
		}
		else if(pesquisaView != null)
		{
			if(event.getSource() == pesquisaView.getBuscar())
			{
				ProdutoDAO pDAO = new ProdutoDAO();
				
				List<Produto> lista = pDAO.pesquisar(pesquisaView.getTxtNome().getText(), pesquisaView.getTxtDescricao().getText());
				
				String[] columnNames = {"Código", "Nome", "Descrição", "Valor"};
				
				String[][] rows = new String[lista.size()][4];
				
				DecimalFormat df = new DecimalFormat();
				df.setMinimumFractionDigits(2);
				df.setMaximumFractionDigits(2);
				
				for(int i = 0; i < lista.size(); i++)
				{
					rows[i][0] = String.valueOf(lista.get(i).getId());
					rows[i][1] = lista.get(i).getNome();
					rows[i][2] = lista.get(i).getDescricao();
					rows[i][3] = df.format(lista.get(i).getValor());
				}
				
				DefaultTableModel dtm = new DefaultTableModel(rows, columnNames);
				pesquisaView.getTabela().setModel(dtm);
				
				DefaultTableCellRenderer render = new DefaultTableCellRenderer();
				render.setHorizontalAlignment(JLabel.CENTER);
				
				pesquisaView.getTabela().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				pesquisaView.getTabela().getColumnModel().getColumn(0).setPreferredWidth(50);
				pesquisaView.getTabela().getColumnModel().getColumn(0).setCellRenderer(render);
				pesquisaView.getTabela().getColumnModel().getColumn(1).setPreferredWidth(150);
				pesquisaView.getTabela().getColumnModel().getColumn(2).setPreferredWidth(190);
				
				
			}
			else if(event.getSource() == pesquisaView.getAlterar())
			{
				ProdutoDAO pDAO = new ProdutoDAO();
				
				int row = pesquisaView.getTabela().getSelectedRow();
				Produto produto = pDAO.obter(Integer.parseInt(pesquisaView.getTabela().getValueAt(row, 0).toString())); 
				
				new ProdutoView(pesquisaView.getSistema(), "Alterar Produto", produto);
				
				List<Produto> lista = pDAO.pesquisar(pesquisaView.getTxtNome().getText(), pesquisaView.getTxtDescricao().getText());
				
				String[] columnNames = {"Código", "Nome", "Descrição", "Valor"};
				
				String[][] rows = new String[lista.size()][4];
				
				DecimalFormat df = new DecimalFormat();
				df.setMinimumFractionDigits(2);
				df.setMaximumFractionDigits(2);
				
				for(int i = 0; i < lista.size(); i++)
				{
					rows[i][0] = String.valueOf(lista.get(i).getId());
					rows[i][1] = lista.get(i).getNome();
					rows[i][2] = lista.get(i).getDescricao();
					rows[i][3] = df.format(lista.get(i).getValor());
				}
				
				DefaultTableModel dtm = new DefaultTableModel(rows, columnNames);
				pesquisaView.getTabela().setModel(dtm);
				
				DefaultTableCellRenderer render = new DefaultTableCellRenderer();
				render.setHorizontalAlignment(JLabel.CENTER);
				
				pesquisaView.getTabela().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				pesquisaView.getTabela().getColumnModel().getColumn(0).setPreferredWidth(50);
				pesquisaView.getTabela().getColumnModel().getColumn(0).setCellRenderer(render);
				pesquisaView.getTabela().getColumnModel().getColumn(1).setPreferredWidth(150);
				pesquisaView.getTabela().getColumnModel().getColumn(2).setPreferredWidth(190);
			}
		}
		
	}

}
