package produto;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import departamento.Departamento;
import departamento.DepartamentoDAO;

public class ProdutoView extends JDialog
{
	private JPanel painel;
	private JLabel lNome, lDescricao, lValor, lDepartamento;
	private JTextField txtNome, txtDescricao, txtValor;
	private JComboBox cDepartamento;
	private JButton salvar;
	private Produto produto;
	
	public ProdutoView(JFrame parent, String titulo, Produto p)
	{
		super(parent, titulo, true);
		produto = p;
		inicializar();
		configurar();
	}
	
	public void inicializar()
	{
		painel = new JPanel();
		painel.setBackground(Color.white);
		painel.setLayout(null);
		
		lNome = new JLabel("Nome: ");
		lDescricao = new JLabel("Descrição: ");
		lValor = new JLabel("Valor: ");
		lDepartamento = new JLabel("Departamento: ");
		
		txtNome = new JTextField();
		if(produto != null)
			txtNome.setText(produto.getNome());
		
		txtDescricao = new JTextField();
		if(produto != null)
			txtDescricao.setText(produto.getDescricao());
		
		txtValor = new JTextField();
		if(produto != null)
		{
			DecimalFormat df = new DecimalFormat();
			df.setMinimumFractionDigits(2);
			df.setMaximumFractionDigits(2);
			
			txtValor.setText(df.format(produto.getValor()));
		}
		
		DepartamentoDAO departamentoDAO = new DepartamentoDAO();
		List<Departamento> departamentos = departamentoDAO.pesquisar("");
		
		String[] lista = new String[departamentos.size()];
		int index = 0;
		for(int i = 0; i < lista.length; i++)
		{
			lista[i] = departamentos.get(i).getId() + "-"+ departamentos.get(i).getNome();
			
			if(produto != null)
			{
				if(produto.getDepartamento().getId() == departamentos.get(i).getId())
					index = i;
			}
		}
		
		cDepartamento = new JComboBox(lista);
		cDepartamento.setSelectedIndex(index);
		salvar = new JButton("Salvar");
		salvar.addActionListener(new ProdutoController(this));
	}
	
	public void configurar()
	{
		lNome.setBounds(10, 10, 90 ,20);
		txtNome.setBounds(100, 10, 230, 20);
		lValor.setBounds(350, 10, 50, 20);
		txtValor.setBounds(390, 10, 80, 20);
		
		lDescricao.setBounds(10, 40, 90, 20);
		txtDescricao.setBounds(100, 40, 370, 20);
		
		lDepartamento.setBounds(10, 70, 90, 20);
		cDepartamento.setBounds(100, 70, 150, 20);
		
		salvar.setBounds(190, 110, 100, 20);
		
		painel.add(lNome);
		painel.add(txtNome);
		painel.add(lValor);
		painel.add(txtValor);
		painel.add(lDescricao);
		painel.add(txtDescricao);
		painel.add(lDepartamento);
		painel.add(cDepartamento);
		painel.add(salvar);
		
		this.setContentPane(painel);
		this.setSize(500, 190);
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

	public JTextField getTxtDescricao() {
		return txtDescricao;
	}

	public void setTxtDescricao(JTextField txtDescricao) {
		this.txtDescricao = txtDescricao;
	}

	public JTextField getTxtValor() {
		return txtValor;
	}

	public void setTxtValor(JTextField txtValor) {
		this.txtValor = txtValor;
	}

	public JComboBox getcDepartamento() {
		return cDepartamento;
	}

	public void setcDepartamento(JComboBox cDepartamento) {
		this.cDepartamento = cDepartamento;
	}

	public JButton getSalvar() {
		return salvar;
	}

	public void setSalvar(JButton salvar) {
		this.salvar = salvar;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}
