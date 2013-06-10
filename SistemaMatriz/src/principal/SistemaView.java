
package principal;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class SistemaView extends JFrame
{
	private JPanel painel;
	private JLabel imagem;
	private JMenuBar menuBar;
	private JMenu menuDepartamento, menuProduto, menuFilial, menuPedido, menuRelatorio;
	private JMenuItem cadastrarDepartamento, pesquisarDepartamento, cadastrarProduto, pesquisarProduto, cadastrarFilial, pesquisarFilial;
	private JMenuItem pesquisarPedido, relatorioEstoque;
	
	public SistemaView()
	{
		super();
		inicializar();
		configurar();
	}
	
	private void inicializar()
	{
		painel = new JPanel();
		painel.setBackground(Color.WHITE);
		painel.setLayout(new BorderLayout());
		
		imagem = new JLabel(new ImageIcon("C:\\Relatorio\\logo.png"));
		
		menuBar = new JMenuBar();
		
		menuFilial = new JMenu("Filial");
		cadastrarFilial = new JMenuItem("Cadastrar");
		pesquisarFilial = new JMenuItem("Pesquisar");
		
		menuDepartamento = new JMenu("Departamento");
		cadastrarDepartamento = new JMenuItem("Cadastrar");
		pesquisarDepartamento = new JMenuItem("Pesquisar");
		
		menuProduto = new JMenu("Produto");
		cadastrarProduto = new JMenuItem("Cadastrar");
		pesquisarProduto = new JMenuItem("Pesquisar");
		
		menuPedido = new JMenu("Pedidos");
		pesquisarPedido = new JMenuItem("Pesquisar");
		
		menuRelatorio = new JMenu("Relatórios");
		relatorioEstoque = new JMenuItem("Estoque");
	}
	
	private void configurar()
	{
		cadastrarFilial.addActionListener(new SistemaController(this));
		pesquisarFilial.addActionListener(new SistemaController(this));
		menuFilial.add(cadastrarFilial);
		menuFilial.add(pesquisarFilial);
		
		cadastrarDepartamento.addActionListener(new SistemaController(this));
		pesquisarDepartamento.addActionListener(new SistemaController(this));
		menuDepartamento.add(cadastrarDepartamento);
		menuDepartamento.add(pesquisarDepartamento);
		
		cadastrarProduto.addActionListener(new SistemaController(this));
		pesquisarProduto.addActionListener(new SistemaController(this));
		menuProduto.add(cadastrarProduto);
		menuProduto.add(pesquisarProduto);
		
		pesquisarPedido.addActionListener(new SistemaController(this));
		menuPedido.add(pesquisarPedido);
		
		relatorioEstoque.addActionListener(new SistemaController(this));
		menuRelatorio.add(relatorioEstoque);
		
		menuBar.add(menuFilial);
		menuBar.add(menuDepartamento);
		menuBar.add(menuProduto);
		menuBar.add(menuPedido);
		menuBar.add(menuRelatorio);
		
		painel.add(imagem, BorderLayout.CENTER);
		this.setJMenuBar(menuBar);
		
		this.setContentPane(painel);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setSize(800, 600);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Sistema Matriz");
	}

	public JPanel getPainel() {
		return painel;
	}

	public void setPainel(JPanel painel) {
		this.painel = painel;
	}

	public JLabel getImagem() {
		return imagem;
	}

	public void setImagem(JLabel imagem) {
		this.imagem = imagem;
	}

	public void setMenuBar(JMenuBar menuBar) {
		this.menuBar = menuBar;
	}

	public JMenu getMenuDepartamento() {
		return menuDepartamento;
	}

	public void setMenuDepartamento(JMenu menuDepartamento) {
		this.menuDepartamento = menuDepartamento;
	}

	public JMenu getMenuProduto() {
		return menuProduto;
	}

	public void setMenuProduto(JMenu menuProduto) {
		this.menuProduto = menuProduto;
	}

	public JMenu getMenuFilial() {
		return menuFilial;
	}

	public void setMenuFilial(JMenu menuFilial) {
		this.menuFilial = menuFilial;
	}

	public JMenuItem getCadastrarDepartamento() {
		return cadastrarDepartamento;
	}

	public void setCadastrarDepartamento(JMenuItem cadastrarDepartamento) {
		this.cadastrarDepartamento = cadastrarDepartamento;
	}

	public JMenuItem getPesquisarDepartamento() {
		return pesquisarDepartamento;
	}

	public void setPesquisarDepartamento(JMenuItem pesquisarDepartamento) {
		this.pesquisarDepartamento = pesquisarDepartamento;
	}

	public JMenuItem getCadastrarProduto() {
		return cadastrarProduto;
	}

	public void setCadastrarProduto(JMenuItem cadastrarProduto) {
		this.cadastrarProduto = cadastrarProduto;
	}

	public JMenuItem getPesquisarProduto() {
		return pesquisarProduto;
	}

	public void setPesquisarProduto(JMenuItem pesquisarProduto) {
		this.pesquisarProduto = pesquisarProduto;
	}

	public JMenuItem getCadastrarFilial() {
		return cadastrarFilial;
	}

	public void setCadastrarFilial(JMenuItem cadastrarFilial) {
		this.cadastrarFilial = cadastrarFilial;
	}

	public JMenuItem getPesquisarFilial() {
		return pesquisarFilial;
	}

	public void setPesquisarFilial(JMenuItem pesquisarFilial) {
		this.pesquisarFilial = pesquisarFilial;
	}

	public JMenuItem getPesquisarPedido() {
		return pesquisarPedido;
	}

	public void setPesquisarPedido(JMenuItem pesquisarPedido) {
		this.pesquisarPedido = pesquisarPedido;
	}

	public JMenuItem getRelatorioEstoque() {
		return relatorioEstoque;
	}

	public void setRelatorioEstoque(JMenuItem relatorioEstoque) {
		this.relatorioEstoque = relatorioEstoque;
	}

	
}
