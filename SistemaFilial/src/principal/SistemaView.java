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

import filial.Filial;
import filial.SelecionarView;

public class SistemaView extends JFrame
{
	private JPanel painel;
	private JLabel imagem;
	
	private JMenuBar menuBar;
	private JMenu menuVenda, menuEstoque, menuRelatorio;
	private JMenuItem gerarPedido, pedidoLocal, pedidoOnline, pesquisaEstoque, receberProduto, relatorioVenda;
	private Filial filial;
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
		
		imagem = new JLabel(new ImageIcon("C:\\Users\\Bruno\\workspace\\SistemaWEB\\WebContent\\resources\\imagem\\logo.png"));
		
		menuBar = new JMenuBar();
		
		menuVenda = new JMenu("Venda");
		menuEstoque = new JMenu("Estoque");
		menuRelatorio = new JMenu("Relatórios");
		
		gerarPedido = new JMenuItem("Gerar Pedido");
		pedidoLocal = new JMenuItem("Pedidos Locais");
		pedidoOnline = new JMenuItem("Pedidos Online");
		
		pesquisaEstoque = new JMenuItem("Pesquisar Estoque");
		receberProduto = new JMenuItem("Entrada/Saída de Produto");
		
		relatorioVenda = new JMenuItem("Vendas");
		
	}
	
	private void configurar()
	{
		gerarPedido.addActionListener(new SistemaController(this));
		pedidoLocal.addActionListener(new SistemaController(this));
		pedidoOnline.addActionListener(new SistemaController(this));
		
		menuVenda.add(gerarPedido);
		menuVenda.add(pedidoLocal);
		menuVenda.add(pedidoOnline);
		
		pesquisaEstoque.addActionListener(new SistemaController(this));
		receberProduto.addActionListener(new SistemaController(this));
		menuEstoque.add(pesquisaEstoque);
		menuEstoque.add(receberProduto);
		
		relatorioVenda.addActionListener(new SistemaController(this));
		menuRelatorio.add(relatorioVenda);
		
		menuBar.add(menuVenda);
		menuBar.add(menuEstoque);
		menuBar.add(menuRelatorio);
		
		painel.add(imagem, BorderLayout.CENTER);
		this.setJMenuBar(menuBar);
		
		this.setContentPane(painel);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setSize(800, 600);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Sistema Filial");
	}

	public JMenuItem getGerarPedido() {
		return gerarPedido;
	}

	public void setGerarPedido(JMenuItem gerarPedido) {
		this.gerarPedido = gerarPedido;
	}

	public JMenuItem getPedidoLocal() {
		return pedidoLocal;
	}

	public void setPedidoLocal(JMenuItem pedidoLocal) {
		this.pedidoLocal = pedidoLocal;
	}

	public JMenuItem getPedidoOnline() {
		return pedidoOnline;
	}

	public void setPedidoOnline(JMenuItem pedidoOnline) {
		this.pedidoOnline = pedidoOnline;
	}

	public JMenuItem getPesquisaEstoque() {
		return pesquisaEstoque;
	}

	public void setPesquisaEstoque(JMenuItem pesquisaEstoque) {
		this.pesquisaEstoque = pesquisaEstoque;
	}

	public JMenuItem getReceberProduto() {
		return receberProduto;
	}

	public void setReceberProduto(JMenuItem receberProduto) {
		this.receberProduto = receberProduto;
	}

	public JMenuItem getRelatorioVenda() {
		return relatorioVenda;
	}

	public void setRelatorioVenda(JMenuItem relatorioVenda) {
		this.relatorioVenda = relatorioVenda;
	}

	public Filial getFilial() {
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}
}

