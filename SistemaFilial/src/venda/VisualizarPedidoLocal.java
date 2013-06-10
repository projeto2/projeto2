package venda;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;

import principal.SistemaView;

import com.toedter.calendar.JDateChooser;

public class VisualizarPedidoLocal extends JDialog 
{
	private JPanel painel;
	private JLabel lData, lPagamento, lObservacao;
	private JDateChooser txtData;
	private JComboBox comboBox;
	private JPanel itens;
	private JTextField txtQuantidadeTotal, txtTotalValor;
	private JTable tabela;
	private JScrollPane scroll, scroll2;
	private JTextArea txtObservacao;
	private SistemaView sistema;
	private PedidoLocal pedido;
	
	public VisualizarPedidoLocal(JFrame sistema, PedidoLocal p)
	{
		super(sistema, "Visualizar Pedido de Venda", true);
		this.sistema = (SistemaView)sistema;
		pedido = p;
		inicializar();
		configurar();
	}
	
	private void inicializar()
	{
		try
		{
			painel = new JPanel(); 
			painel.setBackground(Color.WHITE);
			painel.setLayout(null);
			
			lData = new JLabel("Data:");
			lPagamento = new JLabel("Pagamento:");
			
			txtData = new JDateChooser(pedido.getData());
			
			
			String[] formas = {"Dinheiro", "Cartão Debito", "Cartão Crédito"};
			comboBox = new JComboBox(formas);
			comboBox.setSelectedItem(pedido.getFormaPagamento());
			
			itens = new JPanel();
			itens.setBorder(new TitledBorder("Itens do Pedido"));
			itens.setBackground(Color.white);
			itens.setLayout(null);
			
			String[] coluns = {"Código", "Descrição", "Quantidade", "Valor", "Total"};
			String[][] rows = new String[pedido.getItens().size()][5];
			
			DecimalFormat decimal = new DecimalFormat();
			decimal.setMinimumFractionDigits(2);
			decimal.setMaximumFractionDigits(2);
			
			Integer totalQuantidade = 0;
			Double totalValor = 0.0;
			
			for(int i = 0; i < pedido.getItens().size(); i++)
			{
				rows[i][0] = pedido.getItens().get(i).getProduto().getId().toString();
				rows[i][1] = pedido.getItens().get(i).getProduto().getDescricao();
				rows[i][2] = pedido.getItens().get(i).getQuantidade().toString();
				rows[i][3] = decimal.format(pedido.getItens().get(i).getProduto().getValor());
				rows[i][4] = decimal.format(pedido.getItens().get(i).getQuantidade() * pedido.getItens().get(i).getProduto().getValor());
				
				totalQuantidade += pedido.getItens().get(i).getQuantidade();
				totalValor += pedido.getItens().get(i).getQuantidade() * pedido.getItens().get(i).getProduto().getValor();
			}
			
			DefaultTableCellRenderer renderCenter = new DefaultTableCellRenderer();
			renderCenter.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
			
			DefaultTableCellRenderer renderRight = new DefaultTableCellRenderer();
			renderRight.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
			
			tabela = new JTable(rows, coluns); 
			tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			tabela.getColumnModel().getColumn(0).setPreferredWidth(100);
			tabela.getColumnModel().getColumn(0).setCellRenderer(renderCenter);
			tabela.getColumnModel().getColumn(1).setPreferredWidth(336);
			tabela.getColumnModel().getColumn(2).setPreferredWidth(100);
			tabela.getColumnModel().getColumn(2).setCellRenderer(renderCenter);
			tabela.getColumnModel().getColumn(3).setPreferredWidth(100);
			tabela.getColumnModel().getColumn(3).setCellRenderer(renderRight);
			tabela.getColumnModel().getColumn(4).setPreferredWidth(100);
			tabela.getColumnModel().getColumn(4).setCellRenderer(renderRight);
			
			
			scroll = new JScrollPane(tabela);
			
			txtQuantidadeTotal = new JTextField();
			txtQuantidadeTotal.setText(totalQuantidade.toString());
			txtQuantidadeTotal.setEditable(false);
			txtQuantidadeTotal.setHorizontalAlignment(JTextField.CENTER);
			
			txtTotalValor = new JTextField();
			txtTotalValor.setText(decimal.format(totalValor));
			txtTotalValor.setEditable(false);
			txtTotalValor.setHorizontalAlignment(JTextField.RIGHT);
			
			
			lObservacao = new JLabel("Observação:");
			
			txtObservacao = new JTextArea(4, 45);
			txtObservacao.setText(pedido.getObservacao());
			scroll2 = new JScrollPane(txtObservacao);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void configurar()
	{		
		lData.setBounds(10, 10, 80, 20);
		txtData.setBounds(50, 10, 95, 20);
		
		lPagamento.setBounds(200, 10, 70, 20);
		comboBox.setBounds(280, 10, 150, 20);
		
		lObservacao.setBounds(10, 340, 100, 20);
		scroll2.setBounds(100, 340, 500, 80);
		
		itens.setBounds(10, 35, 765, 300);
		
		scroll.setBounds(10, 20, 740, 200);
		txtQuantidadeTotal.setBounds(450, 230, 100, 20);
		txtTotalValor.setBounds(650, 230, 100, 20);
	
		itens.add(scroll);
		itens.add(txtQuantidadeTotal);
		itens.add(txtTotalValor);
		
		painel.add(lData);
		painel.add(txtData);
		painel.add(lPagamento);
		painel.add(comboBox);
		painel.add(itens);
		painel.add(lObservacao);
		painel.add(scroll2);
		
		
		this.setContentPane(painel);
		this.setSize(800, 500);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}

	public JPanel getPainel() {
		return painel;
	}

	public void setPainel(JPanel painel) {
		this.painel = painel;
	}

	public JLabel getlData() {
		return lData;
	}

	public void setlData(JLabel lData) {
		this.lData = lData;
	}

	public JLabel getlPagamento() {
		return lPagamento;
	}

	public void setlPagamento(JLabel lPagamento) {
		this.lPagamento = lPagamento;
	}

	public JLabel getlObservacao() {
		return lObservacao;
	}

	public void setlObservacao(JLabel lObservacao) {
		this.lObservacao = lObservacao;
	}

	public JDateChooser getTxtData() {
		return txtData;
	}

	public void setTxtData(JDateChooser txtData) {
		this.txtData = txtData;
	}

	public JComboBox getComboBox() {
		return comboBox;
	}

	public void setComboBox(JComboBox comboBox) {
		this.comboBox = comboBox;
	}

	public JPanel getItens() {
		return itens;
	}

	public void setItens(JPanel itens) {
		this.itens = itens;
	}

	public JTextField getTxtQuantidadeTotal() {
		return txtQuantidadeTotal;
	}

	public void setTxtQuantidadeTotal(JTextField txtQuantidadeTotal) {
		this.txtQuantidadeTotal = txtQuantidadeTotal;
	}

	public JTextField getTxtTotalValor() {
		return txtTotalValor;
	}

	public void setTxtTotalValor(JTextField txtTotalValor) {
		this.txtTotalValor = txtTotalValor;
	}

	public JTable getTabela() {
		return tabela;
	}

	public void setTabela(JTable tabela) {
		this.tabela = tabela;
	}

	public JScrollPane getScroll() {
		return scroll;
	}

	public void setScroll(JScrollPane scroll) {
		this.scroll = scroll;
	}

	public JScrollPane getScroll2() {
		return scroll2;
	}

	public void setScroll2(JScrollPane scroll2) {
		this.scroll2 = scroll2;
	}

	public JTextArea getTxtObservacao() {
		return txtObservacao;
	}

	public void setTxtObservacao(JTextArea txtObservacao) {
		this.txtObservacao = txtObservacao;
	}

	public SistemaView getSistema() {
		return sistema;
	}

	public void setSistema(SistemaView sistema) {
		this.sistema = sistema;
	}

	public PedidoLocal getPedido() {
		return pedido;
	}

	public void setPedido(PedidoLocal pedido) {
		this.pedido = pedido;
	}
}
