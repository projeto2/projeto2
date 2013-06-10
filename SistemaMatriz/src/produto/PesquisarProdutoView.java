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

import principal.SistemaView;

public class PesquisarProdutoView extends JDialog
{
	private JPanel painel;
	private JLabel lNome, lDescricao;
	private JTextField txtNome, txtDescricao;
	private JTable tabela;
	private JScrollPane scroll;
	private JButton alterar, buscar;
	private SistemaView sistema;
	
	public PesquisarProdutoView(JFrame parent)
	{
		super(parent, "Pesquisar Produto", true);
		sistema = (SistemaView)parent;
		inicializar();
		configurar();
	}
	
	public void inicializar()
	{
		painel = new JPanel();
		painel.setBackground(Color.white);
		painel.setLayout(null);
		
		lNome = new JLabel("Nome: ");
		lDescricao = new JLabel("Descrição: ");
		
		txtNome = new JTextField();
		txtDescricao = new JTextField();
		
		buscar = new JButton("Buscar");
		buscar.addActionListener(new ProdutoController(this));
		
		String[] columnNames = {"Código", "Nome", "Descrição", "Valor"};
		String[][] rows = {{"","","",""},{"","","",""},{"","","",""},{"","","",""},
				{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""}
				,{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""}
				,{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""}};
		
		tabela = new JTable(rows, columnNames);
		
		scroll = new JScrollPane(tabela);
		
		alterar = new JButton("Alterar");
		alterar.addActionListener(new ProdutoController(this));
	}
	
	public void configurar()
	{
		lNome.setBounds(10, 10, 70, 20);
		txtNome.setBounds(75, 10, 300, 20);
		
		lDescricao.setBounds(10, 40, 70, 20);
		txtDescricao.setBounds(75, 40, 300, 20);
		
		buscar.setBounds(380, 40, 100, 20);
		
		scroll.setBounds(10, 70, 470, 250);
		
		alterar.setBounds(190, 330, 100, 20);
		
		painel.add(lNome);
		painel.add(txtNome);
		painel.add(lDescricao);
		painel.add(txtDescricao);
		painel.add(buscar);
		painel.add(scroll);
		painel.add(alterar);
		
		this.setContentPane(painel);
		this.setSize(500, 400);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public JTextField getTxtNome() {
		return txtNome;
	}

	public void setTxtNome(JTextField txtNome) {
		this.txtNome = txtNome;
	}

	public JTextField getTxtDescricao() {
		return txtDescricao;
	}

	public void setTxtDescricao(JTextField txtDescricao) {
		this.txtDescricao = txtDescricao;
	}

	public JTable getTabela() {
		return tabela;
	}

	public void setTabela(JTable tabela) {
		this.tabela = tabela;
	}

	public JButton getAlterar() {
		return alterar;
	}

	public void setAlterar(JButton alterar) {
		this.alterar = alterar;
	}

	public JButton getBuscar() {
		return buscar;
	}

	public void setBuscar(JButton buscar) {
		this.buscar = buscar;
	}

	public SistemaView getSistema() {
		return sistema;
	}

	public void setSistema(SistemaView sistema) {
		this.sistema = sistema;
	}
}
