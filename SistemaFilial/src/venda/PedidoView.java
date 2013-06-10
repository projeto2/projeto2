package venda;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
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

import principal.SistemaView;

import com.toedter.calendar.JDateChooser;

public class PedidoView extends JDialog 
{
	private JPanel painel;
	private JLabel lData, lPagamento, lProduto, lQuantidade, lTotal, lObservacao, lValor;
	private JDateChooser txtData;
	private JComboBox comboBox;
	private JPanel itens;
	private JTextField txtCodigo, txtDescricao, txtQuantidade, txtQuantidadeTotal, txtTotalValor, txtValor;
	private JButton procurar, adicionar, excluir, salvar;
	private JTable tabela;
	private JScrollPane scroll, scroll2;
	private JTextArea txtObservacao;
	private SistemaView sistema;
	private List<ItemLocal> itensLocal;
	
	public PedidoView(JFrame sistema)
	{
		super(sistema, "Gerar Pedido de Venda", true);
		this.sistema = (SistemaView)sistema;
		inicializar();
		configurar();
	}
	
	private void inicializar()
	{
		try
		{
			itensLocal = new ArrayList<ItemLocal>();
			painel = new JPanel(); 
			painel.setBackground(Color.WHITE);
			painel.setLayout(null);
			
			lData = new JLabel("Data:");
			lPagamento = new JLabel("Pagamento:");
			
			txtData = new JDateChooser(new Date());
			
			String[] formas = {"Dinheiro", "Cartão Debito", "Cartão Crédito"};
			comboBox = new JComboBox(formas);
			
			itens = new JPanel();
			itens.setBorder(new TitledBorder("Itens do Pedido"));
			itens.setBackground(Color.white);
			itens.setLayout(null);
			
			lProduto = new JLabel("Produto:");
			lQuantidade = new JLabel("Quantidade:");
			lValor = new JLabel("Valor:");
			
			txtCodigo = new JTextField();
			txtCodigo.addKeyListener(new PedidoLocalController(this));
			
			txtDescricao = new JTextField();
			txtDescricao.setEditable(false);
			
			txtQuantidade = new JTextField();
			
			txtValor = new JTextField();
			txtValor.setEditable(false);
			
			procurar = new JButton("Buscar");
			procurar.addActionListener(new PedidoLocalController(this));
			
			adicionar = new JButton("Adicionar");
			adicionar.addActionListener(new PedidoLocalController(this));
			
			String[] coluns = {"Código", "Descrição", "Quantidade", "Valor", "Total"};
			String[][] rows = {{"","","","",""}};
			
			tabela = new JTable(rows, coluns); 
			tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			tabela.getColumnModel().getColumn(0).setPreferredWidth(100);
			tabela.getColumnModel().getColumn(1).setPreferredWidth(336);
			tabela.getColumnModel().getColumn(2).setPreferredWidth(100);
			tabela.getColumnModel().getColumn(3).setPreferredWidth(100);
			tabela.getColumnModel().getColumn(4).setPreferredWidth(100);
			
			
			scroll = new JScrollPane(tabela);
			
			lTotal = new JLabel("Totais:");
			
			txtQuantidadeTotal = new JTextField();
			txtQuantidadeTotal.setEditable(false);
			txtQuantidadeTotal.setHorizontalAlignment(JTextField.CENTER);
			
			txtTotalValor = new JTextField();
			txtTotalValor.setEditable(false);
			txtTotalValor.setHorizontalAlignment(JTextField.RIGHT);
			
			excluir = new JButton("Excluir");
			excluir.addActionListener(new PedidoLocalController(this));
			
			lObservacao = new JLabel("Observação:");
			
			txtObservacao = new JTextArea(4, 45);
			scroll2 = new JScrollPane(txtObservacao);
			
			salvar = new JButton("Salvar");
			salvar.addActionListener(new PedidoLocalController(this));
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
		comboBox.setBounds(280, 10, 100, 20);
		
		lObservacao.setBounds(10, 400, 100, 20);
		scroll2.setBounds(100, 400, 500, 80);
		
		salvar.setBounds(330, 500, 100, 20);
		
		itens.setBounds(10, 35, 765, 350);
		
		lProduto.setBounds(10, 30, 70, 20);
		txtCodigo.setBounds(80, 30, 100, 20);
		txtDescricao.setBounds(185, 30, 200, 20);
		lQuantidade.setBounds(10, 55, 70, 20);
		txtQuantidade.setBounds(80, 55, 100, 20);
		lValor.setBounds(245, 55, 50, 20);
		txtValor.setBounds(285, 55,100,20);
		procurar.setBounds(390, 30, 100, 20);
		adicionar.setBounds(390, 55, 100, 20);
		scroll.setBounds(10, 90, 740, 200);
		lTotal.setBounds(400, 300, 50, 20);
		txtQuantidadeTotal.setBounds(450, 300, 100, 20);
		txtTotalValor.setBounds(650, 300, 100, 20);
		
		excluir.setBounds(10, 300, 100, 20);
		
		itens.add(lProduto);
		itens.add(txtCodigo);
		itens.add(txtDescricao);
		itens.add(lQuantidade);
		itens.add(txtQuantidade);
		itens.add(lValor);
		itens.add(txtValor);
		itens.add(procurar);
		itens.add(adicionar);
		itens.add(scroll);
		itens.add(lTotal);
		itens.add(txtQuantidadeTotal);
		itens.add(txtTotalValor);
		itens.add(excluir);
		
		painel.add(lData);
		painel.add(txtData);
		painel.add(lPagamento);
		painel.add(comboBox);
		painel.add(itens);
		painel.add(lObservacao);
		painel.add(scroll2);
		painel.add(salvar);
		
		
		this.setContentPane(painel);
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}

	public JLabel getlValor() {
		return lValor;
	}

	public void setlValor(JLabel lValor) {
		this.lValor = lValor;
	}

	public JTextField getTxtValor() {
		return txtValor;
	}

	public void setTxtValor(JTextField txtValor) {
		this.txtValor = txtValor;
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

	public JLabel getlProduto() {
		return lProduto;
	}

	public void setlProduto(JLabel lProduto) {
		this.lProduto = lProduto;
	}

	public JLabel getlQuantidade() {
		return lQuantidade;
	}

	public void setlQuantidade(JLabel lQuantidade) {
		this.lQuantidade = lQuantidade;
	}

	public JLabel getlTotal() {
		return lTotal;
	}

	public void setlTotal(JLabel lTotal) {
		this.lTotal = lTotal;
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

	public JTextField getTxtCodigo() {
		return txtCodigo;
	}

	public void setTxtCodigo(JTextField txtCodigo) {
		this.txtCodigo = txtCodigo;
	}

	public JTextField getTxtDescricao() {
		return txtDescricao;
	}

	public void setTxtDescricao(JTextField txtDescricao) {
		this.txtDescricao = txtDescricao;
	}

	public JTextField getTxtQuantidade() {
		return txtQuantidade;
	}

	public void setTxtQuantidade(JTextField txtQuantidade) {
		this.txtQuantidade = txtQuantidade;
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

	public JButton getProcurar() {
		return procurar;
	}

	public void setProcurar(JButton procurar) {
		this.procurar = procurar;
	}

	public JButton getAdicionar() {
		return adicionar;
	}

	public void setAdicionar(JButton adicionar) {
		this.adicionar = adicionar;
	}

	public JButton getExcluir() {
		return excluir;
	}

	public void setExcluir(JButton excluir) {
		this.excluir = excluir;
	}

	public JButton getSalvar() {
		return salvar;
	}

	public void setSalvar(JButton salvar) {
		this.salvar = salvar;
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

	public List<ItemLocal> getItensLocal() {
		return itensLocal;
	}

	public void setItensLocal(List<ItemLocal> itensLocal) {
		this.itensLocal = itensLocal;
	}
}
