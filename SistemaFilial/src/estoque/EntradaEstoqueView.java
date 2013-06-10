package estoque;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import principal.SistemaView;
import venda.ItemLocal;

import com.toedter.calendar.JDateChooser;

public class EntradaEstoqueView extends JDialog 
{
	private JPanel painel;
	private JLabel lData, lProduto, lQuantidade, lObservacao, lTipo;
	private JDateChooser txtData;
	private JPanel itens;
	private JTextField txtCodigo, txtDescricao, txtQuantidade;
	private JButton procurar, adicionar, excluir, salvar;
	private JTable tabela;
	private JScrollPane scroll, scroll2;
	private JTextArea txtObservacao;
	private SistemaView sistema;
	private List<ItemEntradaSaida> itensLocal;
	private JRadioButton entrada, saida;
	private ButtonGroup group;
	
	public EntradaEstoqueView(JFrame sistema)
	{
		super(sistema, "Entrada/Saída de Produtos", true);
		this.sistema = (SistemaView)sistema;
		inicializar();
		configurar();
	}
	
	private void inicializar()
	{
		try
		{
			itensLocal = new ArrayList<ItemEntradaSaida>();
			painel = new JPanel(); 
			painel.setBackground(Color.WHITE);
			painel.setLayout(null);
			
			lData = new JLabel("Data:");
			
			txtData = new JDateChooser(new Date());
			
			lTipo = new JLabel("Tipo:");
			entrada = new JRadioButton("Entrada");
			entrada.setBackground(Color.WHITE);
			
			saida = new JRadioButton("Saída");
			saida.setBackground(Color.white);
			
			group = new ButtonGroup();
			group.add(entrada);
			group.add(saida);
			
			
			itens = new JPanel();
			itens.setBorder(new TitledBorder("Itens do Pedido"));
			itens.setBackground(Color.white);
			itens.setLayout(null);
			
			lProduto = new JLabel("Produto:");
			lQuantidade = new JLabel("Quantidade:");
			
			txtCodigo = new JTextField();
			txtCodigo.addKeyListener(new EstoqueController(this));
			
			txtDescricao = new JTextField();
			txtDescricao.setEditable(false);
			
			txtQuantidade = new JTextField();
			
			procurar = new JButton("Buscar");
			procurar.addActionListener(new EstoqueController(this));
			
			adicionar = new JButton("Adicionar");
			adicionar.addActionListener(new EstoqueController(this));
			
			String[] coluns = {"Código", "Descrição", "Quantidade"};
			String[][] rows = {{"","",""}};
			
			tabela = new JTable(rows, coluns); 
			tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			tabela.getColumnModel().getColumn(0).setPreferredWidth(100);
			tabela.getColumnModel().getColumn(1).setPreferredWidth(336);
			tabela.getColumnModel().getColumn(2).setPreferredWidth(100);
			
			
			scroll = new JScrollPane(tabela);
			
			excluir = new JButton("Excluir");
			excluir.addActionListener(new EstoqueController(this));
			
			lObservacao = new JLabel("Observação:");
			
			txtObservacao = new JTextArea(4, 45);
			scroll2 = new JScrollPane(txtObservacao);
			
			salvar = new JButton("Salvar");
			salvar.addActionListener(new EstoqueController(this));
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
		
		lTipo.setBounds(160, 10, 30, 20);
		entrada.setBounds(190, 10, 80, 20);
		saida.setBounds(270, 10, 80, 20);
		
		lObservacao.setBounds(10, 400, 100, 20);
		scroll2.setBounds(100, 400, 450, 80);
		
		salvar.setBounds(250, 500, 100, 20);
		
		itens.setBounds(10, 35, 565, 350);
		
		lProduto.setBounds(10, 30, 70, 20);
		txtCodigo.setBounds(80, 30, 100, 20);
		txtDescricao.setBounds(185, 30, 200, 20);
		lQuantidade.setBounds(10, 55, 70, 20);
		txtQuantidade.setBounds(80, 55, 100, 20);
		procurar.setBounds(390, 30, 100, 20);
		adicionar.setBounds(185, 55, 100, 20);
		scroll.setBounds(10, 90, 540, 200);
		
		excluir.setBounds(10, 300, 100, 20);
		
		itens.add(lProduto);
		itens.add(txtCodigo);
		itens.add(txtDescricao);
		itens.add(lQuantidade);
		itens.add(txtQuantidade);
		itens.add(procurar);
		itens.add(adicionar);
		itens.add(scroll);
		itens.add(excluir);
		
		painel.add(lData);
		painel.add(txtData);
		painel.add(itens);
		painel.add(lObservacao);
		painel.add(scroll2);
		painel.add(salvar);
		painel.add(lTipo);
		painel.add(entrada);
		painel.add(saida);
		
		this.setContentPane(painel);
		this.setSize(600, 570);
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

	public List<ItemEntradaSaida> getItensLocal() {
		return itensLocal;
	}

	public void setItensLocal(List<ItemEntradaSaida> itensLocal) {
		this.itensLocal = itensLocal;
	}

	public JLabel getlTipo() {
		return lTipo;
	}

	public void setlTipo(JLabel lTipo) {
		this.lTipo = lTipo;
	}

	public JRadioButton getEntrada() {
		return entrada;
	}

	public void setEntrada(JRadioButton entrada) {
		this.entrada = entrada;
	}

	public JRadioButton getSaida() {
		return saida;
	}

	public void setSaida(JRadioButton saida) {
		this.saida = saida;
	}

	public ButtonGroup getGroup() {
		return group;
	}

	public void setGroup(ButtonGroup group) {
		this.group = group;
	}
}
