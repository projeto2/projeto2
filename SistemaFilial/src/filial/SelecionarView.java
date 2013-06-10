package filial;

import java.awt.Color;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import principal.SistemaController;
import principal.SistemaView;

import filial.Filial;
import filial.FilialDAO;

public class SelecionarView extends JDialog
{
	private JPanel painel;
	private JLabel lFilial;
	private JComboBox filial;
	private JButton enviar;
	
	public SelecionarView(JFrame parent) throws Exception
	{
		super(parent, "Selecionar Filial", true);
		
		inicializar((SistemaView)parent);
		configurar();
		
		
	}
	
	public void inicializar(SistemaView s) throws Exception
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
		enviar.addActionListener(new SistemaController(s, this)); 
	
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
}
