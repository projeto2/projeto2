package venda;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.Date;
import java.util.Formatter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import principal.SistemaView;

import com.toedter.calendar.JDateChooser;

public class PesquisarPedidoLocalView extends JDialog 
{
	
	private JPanel painel;
	private JLabel lDataInicio, lDataFim;
	private JDateChooser inicio, fim;
	private JButton buscar, visualizar;
	private JTable tabela;
	private JScrollPane scroll;
	private SistemaView sistema;
	

	public PesquisarPedidoLocalView(JFrame parent)
	{
		super(parent, "Pesquisar Pedidos", true);
		sistema = (SistemaView)parent;
		inicializar();
		configurar();
	}
	
	public void inicializar()
	{
		painel = new JPanel();
		painel.setBackground(Color.white);
		painel.setLayout(null);
		
		lDataInicio = new JLabel("Inicio: ");
		lDataFim = new JLabel("Fim: ");
		
		String[] columnNames = {"Código", "Data", "Observação"};
		String[][] rows = {{"","",""},{"","",""},{"","",""},{"","",""}
			,{"","",""},{"","",""},{"","",""},{"","",""},{"","",""}
			,{"","",""},{"","",""},{"","",""},{"","",""},{"","",""}
			,{"","",""},{"","",""},{"","",""},{"","",""},{"","",""}};
		tabela = new JTable(rows, columnNames);
		
		scroll = new JScrollPane(tabela);
		
		buscar = new JButton("Buscar");
		buscar.addActionListener(new PedidoLocalController(this));
		
		visualizar = new JButton("Visualizar");
		visualizar.addActionListener(new PedidoLocalController(this));
		inicio = new JDateChooser();
		fim = new JDateChooser();
		
	}
	
	public void configurar()
	{
		lDataInicio.setBounds(10, 10, 50, 20);
		inicio.setBounds(50, 10, 100, 20);
		
		lDataFim.setBounds(160, 10, 30, 20);
		fim.setBounds(190, 10, 100, 20);
		
		buscar.setBounds(300, 10, 100, 20);
		
		scroll.setBounds(10, 40, 620, 280);
		
		visualizar.setBounds(250, 330, 120, 20);
		
		painel.add(lDataInicio);
		painel.add(inicio);
		painel.add(lDataFim);
		painel.add(fim);
		painel.add(buscar);
		painel.add(scroll);
		painel.add(visualizar);
		
		this.setContentPane(painel);
		this.setSize(650, 400);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public JDateChooser getInicio() {
		return inicio;
	}

	public void setInicio(JDateChooser inicio) {
		this.inicio = inicio;
	}

	public JDateChooser getFim() {
		return fim;
	}

	public void setFim(JDateChooser fim) {
		this.fim = fim;
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

	public JButton getVisualizar() {
		return visualizar;
	}

	public void setVisualizar(JButton visualizar) {
		this.visualizar = visualizar;
	}

	public SistemaView getSistema() {
		return sistema;
	}

	public void setSistema(SistemaView sistema) {
		this.sistema = sistema;
	}
}
