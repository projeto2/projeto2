package produto;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import estoque.EntradaEstoqueView;
import estoque.EstoqueController;

import principal.SistemaView;

import venda.PedidoLocalController;
import venda.PedidoView;

public class PesquisaProdutoView extends JDialog 
{
	private JPanel painel;
	private JLabel lNome;
	private JTextField txtNome;
	private JButton buscar, selecionar;
	private JTable tabela;
	private JScrollPane scroll;
	
	public PesquisaProdutoView(JFrame parent, PedidoView pedido, EntradaEstoqueView entrada)
	{
		super(parent, "Pesquisar Produto", true);
		inicializar(pedido, entrada);
		configurar();
	}
	
	
	
	public void inicializar(PedidoView pedido, EntradaEstoqueView entrada) 
	{
		painel = new JPanel();
		painel.setBackground(Color.white);
		painel.setLayout(null);
		
		lNome = new JLabel("Nome:");
		txtNome = new JTextField();
		buscar = new JButton("Buscar");
		buscar.addActionListener(new PedidoLocalController(this, pedido));
		
		String[] columns = {"Código", "Nome", "Descrição"};
		String[][] rows = {{"","",""}};
		
		tabela = new JTable(rows, columns);
		scroll = new JScrollPane(tabela);
		
		selecionar = new JButton("Selecionar");
		if(pedido != null)
			selecionar.addActionListener(new PedidoLocalController(this, pedido));
		else 
			selecionar.addActionListener(new EstoqueController(entrada, this));
	}
	
	public void configurar()
	{
		lNome.setBounds(10, 10, 50, 20);
		txtNome.setBounds(60, 10, 200, 20);
		buscar.setBounds(270, 10, 100, 20);
		
		scroll.setBounds(10, 35, 470, 200);
		
		selecionar.setBounds(190, 250, 100, 20);
		
		painel.add(lNome);
		painel.add(txtNome);
		painel.add(buscar);
		painel.add(scroll);
		painel.add(selecionar);
		
		this.setContentPane(painel);
		this.setSize(500, 320);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public JPanel getPainel() {
		return painel;
	}

	public void setPainel(JPanel painel) {
		this.painel = painel;
	}

	public JLabel getlNome() {
		return lNome;
	}

	public void setlNome(JLabel lNome) {
		this.lNome = lNome;
	}

	public JTextField getTxtNome() {
		return txtNome;
	}

	public void setTxtNome(JTextField txtNome) {
		this.txtNome = txtNome;
	}

	public JButton getBuscar() {
		return buscar;
	}

	public void setBuscar(JButton buscar) {
		this.buscar = buscar;
	}

	public JButton getSelecionar() {
		return selecionar;
	}

	public void setSelecionar(JButton selecionar) {
		this.selecionar = selecionar;
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
}
