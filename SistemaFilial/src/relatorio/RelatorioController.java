package relatorio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import bd.ConexaoReport;

public class RelatorioController implements ActionListener
{

	private RelatorioVendaView view;
	
	public RelatorioController(RelatorioVendaView r)
	{
		view = r;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		if(view.getInicio().getDate() == null)
		{
			JOptionPane.showMessageDialog(null, "Data inicial invalida!", "Informação", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if(view.getFim().getDate() == null)
		{
			JOptionPane.showMessageDialog(null, "Data final invalida!", "Informação", JOptionPane.ERROR_MESSAGE);
			return;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("inicio", view.getInicio().getDate());
		map.put("fim", view.getFim().getDate());
		
		try 
		{
			view.dispose();
			JasperPrint print = JasperFillManager.fillReport("c:\\Relatorio\\vendas.jasper", map, ConexaoReport.obterConexao());
			JasperViewer viewer = new JasperViewer(print,false);
			viewer.setVisible(true);
			viewer.setSize(850, 600);
		} 
		catch (JRException e) 
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
		
	}
	
}
