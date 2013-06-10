package filial;

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

public class PesquisarFilialView extends JDialog
{
	private JPanel painel;
	private JButton buscar, alterar;
	private JTable tabela;
	private JScrollPane scrollPane;
	private JLabel lPesquisar;
	private JTextField txtPesquisar;
	public PesquisarFilialView(JFrame parent)
	{
		super(parent, "Pesquisar Filial", true);
		inicializar((SistemaView)parent);
		configurar();
	}
	
	public void inicializar(SistemaView s)
	{
		painel = new JPanel();
		painel.setBackground(Color.white);
		painel.setLayout(null);
		
		String[][] rowData = {{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},
				{"","",""},{"","",""},{"","",""},{"","",""},{"","",""},{"","",""}};
		String[] columnNames = {"Código","Nome", "Status"};
		tabela = new JTable(rowData, columnNames);
		
		buscar = new JButton("Buscar");
		buscar.addActionListener(new FilialController(this, s));
		
		lPesquisar = new JLabel("Nome:");
		txtPesquisar = new JTextField();
		
		scrollPane = new JScrollPane(tabela);
		
		alterar = new JButton("Alterar");
		alterar.addActionListener(new FilialController(this, s));
	}
	
	public void configurar()
	{
		lPesquisar.setBounds(10, 10, 50, 20);
		txtPesquisar.setBounds(60, 10, 200, 20);
		buscar.setBounds(270, 10, 100, 20);
		
		scrollPane.setBounds(10, 50, 362, 200);
		
		alterar.setBounds(140, 265, 100, 20);
		
		painel.add(lPesquisar);
		painel.add(txtPesquisar);
		painel.add(buscar);
		painel.add(scrollPane);
		painel.add(alterar); 
		
		this.setContentPane(painel);
		this.setSize(395, 340);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public JButton getBuscar() {
		return buscar;
	}

	public void setBuscar(JButton buscar) {
		this.buscar = buscar;
	}

	public JTable getTabela() {
		return tabela;
	}

	public void setTabela(JTable tabela) {
		this.tabela = tabela;
	}

	public JTextField getTxtPesquisar() {
		return txtPesquisar;
	}

	public void setTxtPesquisar(JTextField txtPesquisar) {
		this.txtPesquisar = txtPesquisar;
	}

	public JButton getAlterar() {
		return alterar;
	}

	public void setAlterar(JButton alterar) {
		this.alterar = alterar;
	}
}
