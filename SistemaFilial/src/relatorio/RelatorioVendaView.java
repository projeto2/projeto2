package relatorio;

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
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.toedter.calendar.JDateChooser;

public class RelatorioVendaView extends JDialog 
{
	
	private JPanel painel;
	private JDateChooser inicio, fim;
	private JLabel lInicio, lFim;
	private JButton gerar;
	
	public RelatorioVendaView(JFrame sistema)
	{
		super(sistema, "Relatório de Venda", true);
		
		inicializar();
		configurar();
	}
	
	private void inicializar()
	{
		painel = new JPanel();
		painel.setBackground(Color.white);
		painel.setLayout(null);
		
		lInicio = new JLabel("Inicio:");
		lFim = new JLabel("Fim:");
		
		inicio = new JDateChooser();
		fim = new JDateChooser();
		
		gerar = new JButton("Gerar");
		gerar.addActionListener(new RelatorioController(this));
	}
	
	private void configurar()
	{		
		lInicio.setBounds(10, 10, 40, 20);
		inicio.setBounds(50, 10, 100, 20);
		lFim.setBounds(160, 10, 30, 20);
		fim.setBounds(190, 10, 100, 20);
		gerar.setBounds(300, 10, 100, 20);
		
		painel.add(lInicio);
		painel.add(lFim);
		painel.add(inicio);
		painel.add(fim);
		painel.add(gerar);
		
		this.setContentPane(painel);
		this.setSize(500, 100);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}

	public JPanel getPainel() {
		return painel;
	}

	public void setPainel(JPanel painel) {
		this.painel = painel;
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

	public JLabel getlInicio() {
		return lInicio;
	}

	public void setlInicio(JLabel lInicio) {
		this.lInicio = lInicio;
	}

	public JLabel getlFim() {
		return lFim;
	}

	public void setlFim(JLabel lFim) {
		this.lFim = lFim;
	}

	public JButton getGerar() {
		return gerar;
	}

	public void setGerar(JButton gerar) {
		this.gerar = gerar;
	}
}
