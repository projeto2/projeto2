package venda;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class VisualizarPedidoOnline extends JDialog
{
	private JPanel painel, itens;
	private Pedido pedido;
	private JLabel lData, lCliente, lStatus,lTotal;
	private JTextField data, cliente,txtQuantidadeTotal, txtTotalValor, txtStatus;
	private JTable tabela;
	private JScrollPane scroll;
	
	public VisualizarPedidoOnline(JFrame parent, Pedido p)
	{
		super(parent, "Visualizar Pedido", true);
		pedido = p;
		inicializar();
		configurar();
	}
	
	public void inicializar()
	{
		painel = new JPanel();
		painel.setBackground(Color.white);
		painel.setLayout(null);
		
		lData = new JLabel("Data:");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		data = new JTextField(sdf.format(pedido.getData()));
		data.setEditable(false);
		
		lCliente = new JLabel("Cliente:");
		cliente = new JTextField(pedido.getUsuario().getNome());
		cliente.setEditable(false);
		
		itens = new JPanel();
		itens.setBorder(new TitledBorder("Itens do Pedido"));
		itens.setBackground(Color.white);
		itens.setLayout(null);
		
		String[] coluns = {"Código", "Descrição", "Quantidade", "Valor", "Total"};
		String[][] rows = new String[pedido.getItens().size()][5];
		
		DecimalFormat decimal = new DecimalFormat();
		decimal.setMinimumFractionDigits(2);
		decimal.setMaximumFractionDigits(2);
		
		Integer totalQuantidade = 0;
		Double totalValor = 0.0;
		
		for(int i = 0; i < pedido.getItens().size(); i++)
		{
			rows[i][0] = pedido.getItens().get(i).getProduto().getId().toString();
			rows[i][1] = pedido.getItens().get(i).getProduto().getDescricao();
			rows[i][2] = pedido.getItens().get(i).getQuantidade().toString();
			rows[i][3] = decimal.format(pedido.getItens().get(i).getProduto().getValor());
			rows[i][4] = decimal.format(pedido.getItens().get(i).getQuantidade() * pedido.getItens().get(i).getProduto().getValor());
			
			totalQuantidade += pedido.getItens().get(i).getQuantidade();
			totalValor += pedido.getItens().get(i).getQuantidade() * pedido.getItens().get(i).getProduto().getValor();
		}
		
		DefaultTableCellRenderer renderCenter = new DefaultTableCellRenderer();
		renderCenter.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		
		DefaultTableCellRenderer renderRight = new DefaultTableCellRenderer();
		renderRight.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
		
		tabela = new JTable(rows, coluns); 
		tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabela.getColumnModel().getColumn(0).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(0).setCellRenderer(renderCenter);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(336);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(2).setCellRenderer(renderCenter);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(3).setCellRenderer(renderRight);
		tabela.getColumnModel().getColumn(4).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(4).setCellRenderer(renderRight);
		
		
		scroll = new JScrollPane(tabela);
		
		lTotal = new JLabel("Totais:");
		txtQuantidadeTotal = new JTextField();
		txtQuantidadeTotal.setText(totalQuantidade.toString());
		txtQuantidadeTotal.setEditable(false);
		txtQuantidadeTotal.setHorizontalAlignment(JTextField.CENTER);
		
		txtTotalValor = new JTextField();
		txtTotalValor.setText(decimal.format(totalValor));
		txtTotalValor.setEditable(false);
		txtTotalValor.setHorizontalAlignment(JTextField.RIGHT);
		
		lStatus = new JLabel("Status:");
		txtStatus = new JTextField(pedido.getStatus());
		txtStatus.setEditable(false);
	}
	
	public void configurar()
	{
		lData.setBounds(10, 10, 50, 20);
		data.setBounds(50, 10, 80, 20);
		lCliente.setBounds(10, 35, 50, 20);
		cliente.setBounds(60, 35, 200, 20);
		
		itens.setBounds(10, 70, 765, 300);
		
		scroll.setBounds(10, 20, 740, 200);
		lTotal.setBounds(400, 230, 50, 20);
		txtQuantidadeTotal.setBounds(450, 230, 100, 20);
		txtTotalValor.setBounds(650, 230, 100, 20);
	
		itens.add(scroll);
		itens.add(lTotal);
		itens.add(txtQuantidadeTotal);
		itens.add(txtTotalValor);
		
		lStatus.setBounds(10, 380, 50, 20);
		txtStatus.setBounds(60, 380, 150, 20);
		
		painel.add(lData);
		painel.add(data);
		painel.add(lCliente);
		painel.add(cliente);
		painel.add(itens);
		painel.add(lStatus);
		painel.add(txtStatus);
		this.setContentPane(painel);
		this.setSize(800, 500);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
