package pedido;

import java.awt.Color;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import filial.Filial;
import filial.FilialDAO;

public class EncaminharView extends JDialog
{
	private Integer pedido;
	private JPanel painel;
	private JLabel lFilial;
	private JComboBox filial;
	private JButton enviar;
	
	public EncaminharView(JFrame parent, Integer pedido)
	{
		super(parent, "Encaminhar Pedido", true);
		this.pedido = pedido;
		inicializar();
		configurar();
	}
	
	public void inicializar()
	{
		painel = new JPanel();
		painel.setBackground(Color.white);
		painel.setLayout(null);
		
		lFilial = new JLabel("Selecione a filial: ");
		
		FilialDAO fDao = new FilialDAO();
		List<Filial> lista = fDao.pesquisar("");
		
		String[] filiais = new String[lista.size()];
		for(int i = 0; i < lista.size(); i++)
		{
			filiais[i] = lista.get(i).getId() + "-" + lista.get(i).getNome();
		}
		
		filial = new JComboBox(filiais);
		
		enviar = new JButton("Enviar");
		enviar.addActionListener(new PedidoController(this));
	}
	
	public void configurar()
	{
		lFilial.setBounds(10, 10, 100, 20);
		filial.setBounds(110, 10, 150, 20);
		enviar.setBounds(270, 10, 100, 20);
		
		painel.add(lFilial);
		painel.add(filial);
		painel.add(enviar);
		
		this.setContentPane(painel);
		this.setSize(400, 100);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public JComboBox getFilial() {
		return filial;
	}

	public void setFilial(JComboBox filial) {
		this.filial = filial;
	}

	public JButton getEnviar() {
		return enviar;
	}

	public void setEnviar(JButton enviar) {
		this.enviar = enviar;
	}

	public Integer getPedido() {
		return pedido;
	}

	public void setPedido(Integer pedido) {
		this.pedido = pedido;
	}
}
