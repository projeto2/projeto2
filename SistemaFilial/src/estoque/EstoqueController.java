package estoque;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Historico.HistoricoDAO;

import produto.PesquisaProdutoView;
import produto.Produto;
import produto.ProdutoDAO;
import venda.ItemLocal;

public class EstoqueController extends KeyAdapter implements ActionListener 
{
	private PesquisarEstoqueView view;
	private EntradaEstoqueView entradaView;
	private EntradaEstoqueView entrada;
	private PesquisaProdutoView produto;
	
	public EstoqueController(PesquisarEstoqueView v)
	{
		view = v;
	}
	
	public EstoqueController(EntradaEstoqueView entrada)
	{
		entradaView = entrada;
	}

	public EstoqueController(EntradaEstoqueView entrada, PesquisaProdutoView pesquisaProdutoView) 
	{
		this.entrada = entrada;
		produto = pesquisaProdutoView;
	}

	@Override
	public void actionPerformed(ActionEvent event) 
	{
		if(view != null)
		{
			if(event.getSource() == view.getBuscar())
			{
				EstoqueDAO e = new EstoqueDAO();
				List<Estoque> lista = e.pesquisar(view.getTxtNome().getText(), view.getTxtDescricao().getText(), view.getSistema().getFilial().getId());
				
				String[] columnNames = {"Código", "Nome", "Descrição", "Quantidade"};
				String[][] rows = new String[lista.size()][4];
				
				for(int i = 0;  i < lista.size(); i++)
				{
					rows[i][0] = lista.get(i).getProduto().getId().toString();
					rows[i][1] = lista.get(i).getProduto().getNome();
					rows[i][2] = lista.get(i).getProduto().getDescricao();
					rows[i][3] = lista.get(i).getQuantidade().toString();
				}
				
				DefaultTableCellRenderer render = new DefaultTableCellRenderer();
				render.setHorizontalAlignment(render.CENTER);
				DefaultTableModel model = new DefaultTableModel(rows, columnNames);
				view.getTabela().setModel(model);
				view.getTabela().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				view.getTabela().getColumnModel().getColumn(0).setPreferredWidth(80);
				view.getTabela().getColumnModel().getColumn(0).setCellRenderer(render);
				view.getTabela().getColumnModel().getColumn(1).setPreferredWidth(140);
				view.getTabela().getColumnModel().getColumn(2).setPreferredWidth(165);
				view.getTabela().getColumnModel().getColumn(3).setPreferredWidth(80);
				view.getTabela().getColumnModel().getColumn(3).setCellRenderer(render);
				
			}
		}
		else if(entradaView != null)
		{
			if(event.getSource() == entradaView.getProcurar())
			{
				new PesquisaProdutoView(entradaView.getSistema(), null, entradaView);
			}
			else if(event.getSource() == entradaView.getAdicionar())
			{
				ProdutoDAO pDAO = new ProdutoDAO();
				Produto produto = pDAO.obter(Integer.parseInt(entradaView.getTxtCodigo().getText()));
				
				if(produto == null)
				{
					JOptionPane.showMessageDialog(null, "Produto não encontrado!", "Informação", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Integer quantidade = Integer.parseInt(entradaView.getTxtQuantidade().getText());
				
				ItemEntradaSaida item = new ItemEntradaSaida();
				item.setProduto(produto);
				item.setQuantidade(quantidade);
				entradaView.getItensLocal().add(item);
				
				entradaView.getTxtCodigo().setText("");
				entradaView.getTxtDescricao().setText("");
				entradaView.getTxtQuantidade().setText("");
				
				String[] coluns = {"Código", "Descrição", "Quantidade"};
				String[][] rows = new String[entradaView.getItensLocal().size()][3];
				
				for(int i = 0; i < entradaView.getItensLocal().size(); i++)
				{
					rows[i][0] = entradaView.getItensLocal().get(i).getProduto().getId().toString();
					rows[i][1] = entradaView.getItensLocal().get(i).getProduto().getDescricao();
					rows[i][2] = entradaView.getItensLocal().get(i).getQuantidade().toString();
				}
				
				DefaultTableCellRenderer renderCenter = new DefaultTableCellRenderer();
				renderCenter.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
				
				DefaultTableModel model = new DefaultTableModel(rows, coluns);
				entradaView.getTabela().setModel(model);
				entradaView.getTabela().getColumnModel().getColumn(0).setPreferredWidth(100);
				entradaView.getTabela().getColumnModel().getColumn(0).setCellRenderer(renderCenter);
				entradaView.getTabela().getColumnModel().getColumn(1).setPreferredWidth(336);
				entradaView.getTabela().getColumnModel().getColumn(2).setPreferredWidth(100);
				entradaView.getTabela().getColumnModel().getColumn(2).setCellRenderer(renderCenter);
			}
			else if(event.getSource() == entradaView.getExcluir())
			{
				int index = entradaView.getTabela().getSelectedRow();
				entradaView.getItensLocal().remove(index);
				
				String[] coluns = {"Código", "Descrição", "Quantidade"};
				String[][] rows = new String[entradaView.getItensLocal().size()][3];
				
				for(int i = 0; i < entradaView.getItensLocal().size(); i++)
				{
					rows[i][0] = entradaView.getItensLocal().get(i).getProduto().getId().toString();
					rows[i][1] = entradaView.getItensLocal().get(i).getProduto().getDescricao();
					rows[i][2] = entradaView.getItensLocal().get(i).getQuantidade().toString();
				}
				
				DefaultTableCellRenderer renderCenter = new DefaultTableCellRenderer();
				renderCenter.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
				
				DefaultTableModel model = new DefaultTableModel(rows, coluns);
				entradaView.getTabela().setModel(model);
				entradaView.getTabela().getColumnModel().getColumn(0).setPreferredWidth(100);
				entradaView.getTabela().getColumnModel().getColumn(0).setCellRenderer(renderCenter);
				entradaView.getTabela().getColumnModel().getColumn(1).setPreferredWidth(336);
				entradaView.getTabela().getColumnModel().getColumn(2).setPreferredWidth(100);
				entradaView.getTabela().getColumnModel().getColumn(2).setCellRenderer(renderCenter);
				
			}
			else if(event.getSource() == entradaView.getSalvar())
			{
				if(entradaView.getEntrada().isSelected() == false && entradaView.getSaida().isSelected() == false )
				{
					JOptionPane.showMessageDialog(null, "Selecione o tipo!", "Informação", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(entradaView.getTxtData().getDate() == null)
				{
					JOptionPane.showMessageDialog(null, "Data incorreta!", "Informação", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(entradaView.getItensLocal().size() == 0)
				{
					JOptionPane.showMessageDialog(null, "Nenhum item adicionado!", "Informação", JOptionPane.ERROR_MESSAGE);
					return;
				}
				 
				EntradaSaidaEstoque obj = new EntradaSaidaEstoque();
				obj.setData(entradaView.getTxtData().getDate());
				
				if(entradaView.getEntrada().isSelected())
				{
					obj.setTipo("ENTRADA");
				}
				else
				{
					obj.setTipo("SAIDA");
				}
				
				obj.setObservacao(entradaView.getTxtObservacao().getText());
				obj.setItens(entradaView.getItensLocal());
				
				EstoqueDAO eDAO = new EstoqueDAO();
				eDAO.salvarEntradaSaida(obj);
				
				if(entradaView.getEntrada().isSelected())
				{
					HistoricoDAO.salvar("Fez a entrada, id=" + obj.getId());
				}
				else
				{
					HistoricoDAO.salvar("Fez a saida, id=" + obj.getId());
				}
				
				for(int i = 0; i < obj.getItens().size(); i++)
				{
					Estoque e = eDAO.obter(obj.getItens().get(i).getProduto().getId(), entradaView.getSistema().getFilial().getId());
					if(entradaView.getEntrada().isSelected())
					{
						e.setQuantidade(e.getQuantidade() + obj.getItens().get(i).getQuantidade());
					}
					else
					{
						e.setQuantidade(e.getQuantidade() - obj.getItens().get(i).getQuantidade());
					}
					
					eDAO.salvar(e);
				}
				
				JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!", "Informação", JOptionPane.INFORMATION_MESSAGE);
				entradaView.getTxtData().setDate(new Date());
				entradaView.getEntrada().setSelected(true);
				entradaView.getTxtObservacao().setText("");
				entradaView.setItensLocal(new ArrayList<ItemEntradaSaida>());
				
				String[] coluns = {"Código", "Descrição", "Quantidade"};
				String[][] rows = new String[entradaView.getItensLocal().size()][3];
				
				for(int i = 0; i < entradaView.getItensLocal().size(); i++)
				{
					rows[i][0] = entradaView.getItensLocal().get(i).getProduto().getId().toString();
					rows[i][1] = entradaView.getItensLocal().get(i).getProduto().getDescricao();
					rows[i][2] = entradaView.getItensLocal().get(i).getQuantidade().toString();
				}
				
				DefaultTableCellRenderer renderCenter = new DefaultTableCellRenderer();
				renderCenter.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
				
				DefaultTableModel model = new DefaultTableModel(rows, coluns);
				entradaView.getTabela().setModel(model);
				entradaView.getTabela().getColumnModel().getColumn(0).setPreferredWidth(100);
				entradaView.getTabela().getColumnModel().getColumn(0).setCellRenderer(renderCenter);
				entradaView.getTabela().getColumnModel().getColumn(1).setPreferredWidth(336);
				entradaView.getTabela().getColumnModel().getColumn(2).setPreferredWidth(100);
				entradaView.getTabela().getColumnModel().getColumn(2).setCellRenderer(renderCenter);
			}
		}
		else if(produto != null)
		{
			if(event.getSource() == produto.getSelecionar())
			{
				if(produto.getTabela().getSelectedRow() < 0)
				{
					
					return;
				}
				Integer chave = Integer.parseInt(produto.getTabela().getValueAt(produto.getTabela().getSelectedRow(), 0).toString());
				
				ProdutoDAO pDAO = new ProdutoDAO();
				Produto produto = pDAO.obter(chave);
				
				DecimalFormat decimal = new DecimalFormat();
				decimal.setMaximumFractionDigits(2);
				decimal.setMinimumFractionDigits(2);
				entrada.getTxtCodigo().setText(produto.getId().toString());
				entrada.getTxtDescricao().setText(produto.getDescricao());
				
				this.produto.dispose();
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent event) 
	{
		try
		{
			if(event.getKeyCode() == KeyEvent.VK_ENTER)
			{
				ProdutoDAO pDAO = new ProdutoDAO();
				Produto produto = pDAO.obter(Integer.parseInt(entradaView.getTxtCodigo().getText()));
				
				if(produto != null)
				{
					DecimalFormat decimal = new DecimalFormat();
					decimal.setMaximumFractionDigits(2);
					decimal.setMinimumFractionDigits(2);
					entradaView.getTxtDescricao().setText(produto.getDescricao());
					entradaView.getTxtQuantidade().requestFocus();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Produto não encontrado!", "Informação", JOptionPane.ERROR_MESSAGE);
					entradaView.getTxtCodigo().requestFocus();
				}			
			}
		}
		catch(Exception e)
		{
			entradaView.getTxtCodigo().setText("");
			entradaView.getTxtDescricao().setText("");
			JOptionPane.showMessageDialog(null, "Código inválido!", "Informação", JOptionPane.ERROR_MESSAGE);
		}
	}

}
