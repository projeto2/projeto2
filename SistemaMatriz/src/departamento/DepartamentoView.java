package departamento;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DepartamentoView extends JDialog
{
	private JLabel lNome;
	private JTextField txtNome;
	private JButton salvar;
	private JPanel painel;
	private Departamento departamento;
	public DepartamentoView(JFrame parent, String titulo, Departamento d)
	{
		super(parent, titulo, true);
		departamento = d;
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
		if(departamento != null)
			txtNome.setText(departamento.getNome());
		
		salvar = new JButton("Salvar");
		salvar.addActionListener(new DepartamentoController(this));
	}
	
	public void configurar()
	{
		lNome.setBounds(10, 10, 50, 20);
		txtNome.setBounds(70, 10, 200, 20);
		salvar.setBounds(280, 10, 100, 20);
		
		painel.add(lNome);
		painel.add(txtNome);
		painel.add(salvar);
		this.setContentPane(painel);
		this.setSize(405, 100);
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

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
}
