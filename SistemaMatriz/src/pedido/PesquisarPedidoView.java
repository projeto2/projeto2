package pedido;

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

public class PesquisarPedidoView extends JDialog
{
	private JPanel painel;
	private JLabel lDataInicio, lDataFim;
	private JDateChooser inicio, fim;
	private JRadioButton encaminhado, aguardando;
	private ButtonGroup group;
	private JButton buscar, visualizar, encaminhar;
	private JTable tabela;
	private JScrollPane scroll;
	private SistemaView sistema;
	

	public PesquisarPedidoView(JFrame parent)
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
		
		encaminhado = new JRadioButton("Encaminhado");
		encaminhado.setBackground(Color.white);
		aguardando = new JRadioButton("Aguardando");
		aguardando.setSelected(true);
		aguardando.setBackground(Color.white);
		
		inicio = new JDateChooser();
		fim = new JDateChooser();
		group = new ButtonGroup();
		group.add(encaminhado);
		group.add(aguardando);
		
		String[] columnNames = {"Código", "Data", "Usuário", "Status"};
		String[][] rows = {{"","","",""},{"","","",""},{"","","",""},{"","","",""}
			,{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""}
			,{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""}
			,{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""}};
		tabela = new JTable(rows, columnNames);
		
		scroll = new JScrollPane(tabela);
		
		buscar = new JButton("Buscar");
		buscar.addActionListener(new PedidoController(this));
		
		visualizar = new JButton("Visualizar");
		visualizar.addActionListener(new PedidoController(this));
		
		encaminhar = new JButton("Encaminhar");
		encaminhar.addActionListener(new PedidoController(this));
	}
	
	public void configurar()
	{
		lDataInicio.setBounds(10, 10, 50, 20);
		inicio.setBounds(50, 10, 100, 20);
		
		lDataFim.setBounds(160, 10, 30, 20);
		fim.setBounds(190, 10, 100, 20);
		
		aguardando.setBounds(310, 10, 100, 20);
		encaminhado.setBounds(410, 10, 120, 20);
		
		buscar.setBounds(530, 10, 100, 20);
		
		scroll.setBounds(10, 40, 620, 280);
		
		visualizar.setBounds(200, 330, 120, 20);
		encaminhar.setBounds(330, 330, 120, 20);
		
		painel.add(lDataInicio);
		painel.add(inicio);
		painel.add(lDataFim);
		painel.add(fim);
		painel.add(encaminhado);
		painel.add(aguardando);
		painel.add(buscar);
		painel.add(scroll);
		painel.add(visualizar);
		painel.add(encaminhar);
		
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

	public JRadioButton getEncaminhado() {
		return encaminhado;
	}

	public void setEncaminhado(JRadioButton encaminhado) {
		this.encaminhado = encaminhado;
	}

	public JRadioButton getAguardando() {
		return aguardando;
	}

	public void setAguardando(JRadioButton aguardando) {
		this.aguardando = aguardando;
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

	public JButton getEncaminhar() {
		return encaminhar;
	}

	public void setEncaminhar(JButton encaminhar) {
		this.encaminhar = encaminhar;
	}

	public SistemaView getSistema() {
		return sistema;
	}

	public void setSistema(SistemaView sistema) {
		this.sistema = sistema;
	}
}
