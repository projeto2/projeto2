package venda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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

public class PedidoLocalController extends KeyAdapter implements ActionListener
{
	private PedidoView view;
	private PedidoView pedido;
	private PesquisaProdutoView produtoView;
	private PesquisarPedidoLocalView pesquisarView;
	
	public PedidoLocalController(PedidoView v)
	{
		view = v;
	}
	
	public PedidoLocalController(PesquisarPedidoLocalView p)
	{
		pesquisarView = p;
	}
	
	public PedidoLocalController(PesquisaProdutoView v, PedidoView p)
	{
		produtoView = v;
		pedido = p;
	}
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		if(view != null)
		{
			if(event.getSource() == view.getProcurar())
			{
				new PesquisaProdutoView(view.getSistema(), view, null);
			}
			else if(event.getSource() == view.getAdicionar())
			{
				ProdutoDAO pDAO = new ProdutoDAO();
				Produto produto = pDAO.obter(Integer.parseInt(view.getTxtCodigo().getText()));
				
				if(produto == null)
				{
					JOptionPane.showMessageDialog(null, "Produto não encontrado!", "Informação", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Integer quantidade = Integer.parseInt(view.getTxtQuantidade().getText());
				
				ItemLocal item = new ItemLocal();
				item.setProduto(produto);
				item.setQuantidade(quantidade);
				view.getItensLocal().add(item);
				
				view.getTxtCodigo().setText("");
				view.getTxtDescricao().setText("");
				view.getTxtQuantidade().setText("");
				view.getTxtValor().setText("");
				
				String[] coluns = {"Código", "Descrição", "Quantidade", "Valor", "Total"};
				String[][] rows = new String[view.getItensLocal().size()][5];
				
				DecimalFormat decimal = new DecimalFormat();
				decimal.setMinimumFractionDigits(2);
				decimal.setMaximumFractionDigits(2);
				
				Integer totalQuantidade = 0;
				Double totalValor = 0.0;
				for(int i = 0; i < view.getItensLocal().size(); i++)
				{
					rows[i][0] = view.getItensLocal().get(i).getProduto().getId().toString();
					rows[i][1] = view.getItensLocal().get(i).getProduto().getDescricao();
					rows[i][2] = view.getItensLocal().get(i).getQuantidade().toString();
					rows[i][3] = decimal.format(view.getItensLocal().get(i).getProduto().getValor());
					rows[i][4] = decimal.format(view.getItensLocal().get(i).getQuantidade() * view.getItensLocal().get(i).getProduto().getValor());
					
					totalQuantidade += view.getItensLocal().get(i).getQuantidade();
					totalValor += view.getItensLocal().get(i).getQuantidade() * view.getItensLocal().get(i).getProduto().getValor();
				}
				
				DefaultTableCellRenderer renderCenter = new DefaultTableCellRenderer();
				renderCenter.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
				
				DefaultTableCellRenderer renderRight = new DefaultTableCellRenderer();
				renderRight.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
				
				DefaultTableModel model = new DefaultTableModel(rows, coluns);
				view.getTabela().setModel(model);
				view.getTabela().getColumnModel().getColumn(0).setPreferredWidth(100);
				view.getTabela().getColumnModel().getColumn(0).setCellRenderer(renderCenter);
				view.getTabela().getColumnModel().getColumn(1).setPreferredWidth(336);
				view.getTabela().getColumnModel().getColumn(2).setPreferredWidth(100);
				view.getTabela().getColumnModel().getColumn(2).setCellRenderer(renderCenter);
				view.getTabela().getColumnModel().getColumn(3).setPreferredWidth(100);
				view.getTabela().getColumnModel().getColumn(3).setCellRenderer(renderRight);
				view.getTabela().getColumnModel().getColumn(4).setPreferredWidth(100);
				view.getTabela().getColumnModel().getColumn(4).setCellRenderer(renderRight);
				
				view.getTxtTotalValor().setText(decimal.format(totalValor));
				view.getTxtQuantidadeTotal().setText(totalQuantidade.toString());
			}
			else if(event.getSource() == view.getExcluir())
			{
				int index = view.getTabela().getSelectedRow();
				
				if(index < 0)
				{
					JOptionPane.showMessageDialog(null, "Selecione uma linha na lista de itens!", "Informação", JOptionPane.ERROR_MESSAGE);
					return;
				}
				view.getItensLocal().remove(index);
				
				String[] coluns = {"Código", "Descrição", "Quantidade", "Valor", "Total"};
				String[][] rows = new String[view.getItensLocal().size()][5];
				
				DecimalFormat decimal = new DecimalFormat();
				decimal.setMinimumFractionDigits(2);
				decimal.setMaximumFractionDigits(2);
				
				Integer totalQuantidade = 0;
				Double totalValor = 0.0;
				for(int i = 0; i < view.getItensLocal().size(); i++)
				{
					rows[i][0] = view.getItensLocal().get(i).getProduto().getId().toString();
					rows[i][1] = view.getItensLocal().get(i).getProduto().getDescricao();
					rows[i][2] = view.getItensLocal().get(i).getQuantidade().toString();
					rows[i][3] = decimal.format(view.getItensLocal().get(i).getProduto().getValor());
					rows[i][4] = decimal.format(view.getItensLocal().get(i).getQuantidade() * view.getItensLocal().get(i).getProduto().getValor());
					
					totalQuantidade += view.getItensLocal().get(i).getQuantidade();
					totalValor += view.getItensLocal().get(i).getQuantidade() * view.getItensLocal().get(i).getProduto().getValor();
				}
				
				DefaultTableCellRenderer renderCenter = new DefaultTableCellRenderer();
				renderCenter.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
				
				DefaultTableCellRenderer renderRight = new DefaultTableCellRenderer();
				renderRight.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
				
				DefaultTableModel model = new DefaultTableModel(rows, coluns);
				view.getTabela().setModel(model);
				view.getTabela().getColumnModel().getColumn(0).setPreferredWidth(100);
				view.getTabela().getColumnModel().getColumn(0).setCellRenderer(renderCenter);
				view.getTabela().getColumnModel().getColumn(1).setPreferredWidth(336);
				view.getTabela().getColumnModel().getColumn(2).setPreferredWidth(100);
				view.getTabela().getColumnModel().getColumn(2).setCellRenderer(renderCenter);
				view.getTabela().getColumnModel().getColumn(3).setPreferredWidth(100);
				view.getTabela().getColumnModel().getColumn(3).setCellRenderer(renderRight);
				view.getTabela().getColumnModel().getColumn(4).setPreferredWidth(100);
				view.getTabela().getColumnModel().getColumn(4).setCellRenderer(renderRight);
				
				view.getTxtTotalValor().setText(decimal.format(totalValor));
				view.getTxtQuantidadeTotal().setText(totalQuantidade.toString());
				
			}
			else if(event.getSource() == view.getSalvar())
			{
				PedidoLocal pedido = new PedidoLocal();
				pedido.setData(view.getTxtData().getDate());
				pedido.setFormaPagamento(view.getComboBox().getSelectedItem().toString());
				pedido.setObservacao(view.getTxtObservacao().getText());
				pedido.setItens(view.getItensLocal());
				
				PedidoLocalDAO pDAO = new PedidoLocalDAO();
				pDAO.salvar(pedido);
				
				view.getTxtCodigo().setText("");
				view.getTxtData().setDate(new Date());
				view.getTxtDescricao().setText("");
				view.getTxtObservacao().setText("");
				view.getTxtQuantidade().setText("");
				view.getTxtQuantidadeTotal().setText("");
				view.getTxtTotalValor().setText("");
				view.getTxtValor().setText("");
				
				view.setItensLocal(new ArrayList<ItemLocal>());
				String[] coluns = {"Código", "Descrição", "Quantidade", "Valor", "Total"};
				String[][] rows = {{"","","","",""}};
				
				DefaultTableCellRenderer renderCenter = new DefaultTableCellRenderer();
				renderCenter.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
				
				DefaultTableCellRenderer renderRight = new DefaultTableCellRenderer();
				renderRight.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
				
				DefaultTableModel model = new DefaultTableModel(rows, coluns);
				view.getTabela().setModel(model);
				view.getTabela().getColumnModel().getColumn(0).setPreferredWidth(100);
				view.getTabela().getColumnModel().getColumn(0).setCellRenderer(renderCenter);
				view.getTabela().getColumnModel().getColumn(1).setPreferredWidth(336);
				view.getTabela().getColumnModel().getColumn(2).setPreferredWidth(100);
				view.getTabela().getColumnModel().getColumn(2).setCellRenderer(renderCenter);
				view.getTabela().getColumnModel().getColumn(3).setPreferredWidth(100);
				view.getTabela().getColumnModel().getColumn(3).setCellRenderer(renderRight);
				view.getTabela().getColumnModel().getColumn(4).setPreferredWidth(100);
				view.getTabela().getColumnModel().getColumn(4).setCellRenderer(renderRight);
				
				HistoricoDAO.salvar("Salvou o pedido, id=" + pedido.getId());
				JOptionPane.showMessageDialog(null, "Pedido cadastrado com sucesso!", "Informação", JOptionPane.INFORMATION_MESSAGE);
				
			}
		}
		else if(produtoView != null)
		{
			if(event.getSource() == produtoView.getBuscar())
			{
				ProdutoDAO pDAO = new ProdutoDAO();
				
				List<Produto> lista = pDAO.pesquisar(produtoView.getTxtNome().getText());
				
				String[] columns = {"Código", "Nome", "Descrição"};
				
				String rows[][] = new String[lista.size()][3];
				
				for(int i = 0; i < lista.size(); i++)
				{
					rows[i][0] = lista.get(i).getId().toString();
					rows[i][1] = lista.get(i).getNome();
					rows[i][2] = lista.get(i).getDescricao();
				}
				
				DefaultTableModel model = new DefaultTableModel(rows, columns);
				
				produtoView.getTabela().setModel(model);
				
			}
			else if(event.getSource() == produtoView.getSelecionar())
			{
				if(produtoView.getTabela().getSelectedRow() < 0)
				{
					
					return;
				}
				Integer chave = Integer.parseInt(produtoView.getTabela().getValueAt(produtoView.getTabela().getSelectedRow(), 0).toString());
				
				ProdutoDAO pDAO = new ProdutoDAO();
				Produto produto = pDAO.obter(chave);
				
				DecimalFormat decimal = new DecimalFormat();
				decimal.setMaximumFractionDigits(2);
				decimal.setMinimumFractionDigits(2);
				pedido.getTxtCodigo().setText(produto.getId().toString());
				pedido.getTxtDescricao().setText(produto.getDescricao());
				pedido.getTxtValor().setText(decimal.format(produto.getValor()));
				
				produtoView.dispose();
			}
		}
		else if(pesquisarView != null)
		{
			if(event.getSource() == pesquisarView.getBuscar())
			{
				PedidoLocalDAO pDAO = new PedidoLocalDAO();
				
				List<PedidoLocal> lista = pDAO.pesquisar(pesquisarView.getInicio().getDate(), pesquisarView.getFim().getDate());
				
				String[] columnNames = {"Código", "Data", "Observação"};
				String[][] rows = new String[lista.size()][3];
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				
				for(int i = 0; i < lista.size(); i++)
				{
					rows[i][0] = lista.get(i).getId().toString();
					rows[i][1] = sdf.format(lista.get(i).getData());
					rows[i][2] = lista.get(i).getObservacao();
				}
				
				DefaultTableCellRenderer renderCenter = new DefaultTableCellRenderer();
				renderCenter.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
				
				DefaultTableModel model = new DefaultTableModel(rows, columnNames);
				pesquisarView.getTabela().setModel(model);
				pesquisarView.getTabela().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				pesquisarView.getTabela().getColumnModel().getColumn(0).setPreferredWidth(100);
				pesquisarView.getTabela().getColumnModel().getColumn(0).setCellRenderer(renderCenter);
				pesquisarView.getTabela().getColumnModel().getColumn(1).setPreferredWidth(100);
				pesquisarView.getTabela().getColumnModel().getColumn(1).setCellRenderer(renderCenter);
				pesquisarView.getTabela().getColumnModel().getColumn(2).setPreferredWidth(415);
			}
			else if(event.getSource() == pesquisarView.getVisualizar())
			{
				Integer chave = Integer.parseInt(pesquisarView.getTabela().getValueAt(pesquisarView.getTabela().getSelectedRow(), 0).toString());
				
				PedidoLocalDAO pDAO = new PedidoLocalDAO();
				PedidoLocal pedidoLocal = pDAO.obter(chave);
				
				VisualizarPedidoLocal v = new VisualizarPedidoLocal(pesquisarView.getSistema(), pedidoLocal);
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
				Produto produto = pDAO.obter(Integer.parseInt(view.getTxtCodigo().getText()));
				
				if(produto != null)
				{
					DecimalFormat decimal = new DecimalFormat();
					decimal.setMaximumFractionDigits(2);
					decimal.setMinimumFractionDigits(2);
					view.getTxtDescricao().setText(produto.getDescricao());
					view.getTxtValor().setText(decimal.format(produto.getValor()));
					view.getTxtQuantidade().requestFocus();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Produto não encontrado!", "Informação", JOptionPane.ERROR_MESSAGE);
					view.getTxtCodigo().requestFocus();
				}			
			}
		}
		catch(Exception e)
		{
			view.getTxtCodigo().setText("");
			view.getTxtDescricao().setText("");
			view.getTxtValor().setText("");
			JOptionPane.showMessageDialog(null, "Código inválido!", "Informação", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
