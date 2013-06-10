package venda;

import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class StatusView extends JDialog
{
	private JPanel painel;
	private Integer pedido;
	private JLabel lStatus;
	private JRadioButton aguardandoPagamento, aprovado, saiuEntrega, entregue;
	private ButtonGroup group;
	private JButton salvar;
	
	public StatusView(JFrame parent, Integer pedido, String status)
	{
		super(parent, "Status do Pedido", true);
		this.pedido = pedido;
		inicializar(status);
		configurar();
	}

	public void inicializar(String status)
	{
		painel = new JPanel();
		painel.setBackground(Color.white);
		painel.setLayout(null);
		
		lStatus = new JLabel("Selecione o status:");
		
		aguardandoPagamento = new JRadioButton("Aguar. Pagamento");
		aguardandoPagamento.setBackground(Color.white);
		if(status.equals("AGUARDANDO"))
			aguardandoPagamento.setSelected(true);
		
		aprovado = new JRadioButton("Aprovado");
		aprovado.setBackground(Color.white);
		if(status.equals("APROVADO"))
			aprovado.setSelected(true);
		
		saiuEntrega = new JRadioButton("Saiu para Entrega");
		saiuEntrega.setBackground(Color.white);
		if(status.equals("SAIU PARA ENTREGA"))
			saiuEntrega.setSelected(true);
		
		entregue = new JRadioButton("Entregue");
		entregue.setBackground(Color.white);
		if(status.equals("ENTREGUE"))
			entregue.setSelected(true);
		
		group = new ButtonGroup();
		group.add(aguardandoPagamento);
		group.add(aprovado);
		group.add(saiuEntrega);
		group.add(entregue);
		
		
		salvar = new JButton("Salvar");
	}
	
	public Integer getPedido() {
		return pedido;
	}

	public void setPedido(Integer pedido) {
		this.pedido = pedido;
	}

	public JRadioButton getAguardandoPagamento() {
		return aguardandoPagamento;
	}

	public void setAguardandoPagamento(JRadioButton aguardandoPagamento) {
		this.aguardandoPagamento = aguardandoPagamento;
	}

	public JRadioButton getAprovado() {
		return aprovado;
	}

	public void setAprovado(JRadioButton aprovado) {
		this.aprovado = aprovado;
	}

	public JRadioButton getSaiuEntrega() {
		return saiuEntrega;
	}

	public void setSaiuEntrega(JRadioButton saiuEntrega) {
		this.saiuEntrega = saiuEntrega;
	}

	public JRadioButton getEntregue() {
		return entregue;
	}

	public void setEntregue(JRadioButton entregue) {
		this.entregue = entregue;
	}

	public JButton getSalvar() {
		return salvar;
	}

	public void setSalvar(JButton salvar) {
		this.salvar = salvar;
	}

	public void configurar()
	{
		lStatus.setBounds(10, 10, 150, 20);
		
		aguardandoPagamento.setBounds(10, 35, 130, 20);
		aprovado.setBounds(140, 35, 80, 20);
		entregue.setBounds(380, 35, 80, 20);
		saiuEntrega.setBounds(230, 35, 150, 20);
		
		salvar.setBounds(190, 60, 100, 20);
		salvar.addActionListener(new PedidoController(this));
		
		painel.add(lStatus);
		painel.add(aprovado);
		painel.add(entregue);
		painel.add(aguardandoPagamento);
		painel.add(saiuEntrega);
		painel.add(salvar);
		
		this.setContentPane(painel);
		this.setSize(500, 130);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
