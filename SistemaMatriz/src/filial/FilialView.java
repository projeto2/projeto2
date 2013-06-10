package filial;

import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import principal.SistemaController;

public class FilialView extends JDialog
{
	private JPanel painel;
	private JLabel lNome;
	private JTextField txtNome;
	private JButton salvar;
	private JRadioButton ativo, desativado;
	private ButtonGroup group;
	private Filial filial;
	public FilialView(JFrame parent, String titulo, Filial f)
	{
		super(parent, titulo, true);
		filial = f;
		inicializar();
		configurar();
		
	}
	
	public void inicializar()
	{
		painel = new JPanel();
		painel.setBackground(Color.white);
		painel.setLayout(null);
			
		lNome = new JLabel("Nome:");
		
		txtNome = new JTextField();
		if(filial != null)
			txtNome.setText(filial.getNome());
		
		salvar = new JButton("Salvar");
		
		group = new ButtonGroup();
		ativo = new JRadioButton("Ativo");
		desativado = new JRadioButton("Desativado");
		ativo.setBackground(Color.white);
		if(filial != null)
		{
			if(filial.isStatus())
				ativo.setSelected(true);
			else
				desativado.setSelected(true);
		}		
		else 
		{
			ativo.setSelected(true);
		}
		
		desativado.setBackground(Color.white);
		
		group.add(ativo);
		group.add(desativado);
	}
	
	public void configurar()
	{
		lNome.setBounds(10, 10, 50, 20);
		txtNome.setBounds(60, 10, 200, 20);
		
		ativo.setBounds(270, 10, 60, 20);
		desativado.setBounds(340, 10, 100, 20);
		
		salvar.addActionListener(new FilialController(this));
		salvar.setBounds(200, 45, 100, 20);
		
		painel.add(lNome);
		painel.add(txtNome);
		painel.add(salvar);
		painel.add(ativo);
		painel.add(desativado);
		
		this.setContentPane(painel);
		this.setSize(500, 120);
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

	public JButton getSalvar() {
		return salvar;
	}

	public void setSalvar(JButton salvar) {
		this.salvar = salvar;
	}

	public JRadioButton getAtivo() {
		return ativo;
	}

	public void setAtivo(JRadioButton ativo) {
		this.ativo = ativo;
	}

	public JRadioButton getDesativado() {
		return desativado;
	}

	public void setDesativado(JRadioButton desativado) {
		this.desativado = desativado;
	}

	public Filial getFilial() {
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}
}
