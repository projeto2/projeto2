package principal;

import javax.swing.JOptionPane;

import filial.SelecionarView;

public class Main 
{
	public static void main(String[] args) 
	{
		try {
			SistemaView sistema = new SistemaView();
			new SelecionarView(sistema);
			
			if(sistema.getFilial() == null) 
				System.exit(0);
		} catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}
}
