package venda;

import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import principal.SistemaView;

import com.toedter.calendar.JDateChooser;

public class PesquisarPedidoOnlineView extends JDialog
{
	private JPanel painel;
	private JLabel lDataInicio, lDataFim;
	private JDateChooser inicio, fim;
	private JButton buscar, status, visualizar;
	private JTable tabela;
	private JScrollPane scroll;
	private SistemaView sistema;
	

	public PesquisarPedidoOnlineView(JFrame parent)
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
		
		String[] columnNames = {"Código", "Data", "Usuário", "Status"};
		String[][] rows = {{"","","",""},{"","","",""},{"","","",""},{"","","",""}
			,{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""}
			,{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""}
			,{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""}};
		tabela = new JTable(rows, columnNames);
		
		scroll = new JScrollPane(tabela);
		
		buscar = new JButton("Buscar");
		buscar.addActionListener(new PedidoController(this));
		
		status = new JButton("Status");
		visualizar = new JButton("Visualizar");
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
		
		status.setBounds(200, 330, 120, 20);
		status.addActionListener(new PedidoController(this));
		
		visualizar.setBounds(330, 330, 120, 20);
		visualizar.addActionListener(new PedidoController(this));
		
		painel.add(lDataInicio);
		painel.add(inicio);
		painel.add(lDataFim);
		painel.add(fim);
		painel.add(buscar);
		painel.add(scroll);
		painel.add(status);
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

	public JButton getStatus() {
		return status;
	}

	public void setStatus(JButton visualizar) {
		this.status = visualizar;
	}

	public SistemaView getSistema() {
		return sistema;
	}

	public void setSistema(SistemaView sistema) {
		this.sistema = sistema;
	}

	public JButton getVisualizar() {
		return visualizar;
	}

	public void setVisualizar(JButton visualizar) {
		this.visualizar = visualizar;
	}
}
