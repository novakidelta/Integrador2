package Classes;

import javax.swing.JOptionPane;
import javax.swing.JTable;

public class ComandosJtable {

	public int PegaRegistro(JTable Jtable) {
		int codigo = 0;
		int linha = Jtable.getSelectedRow();
		if(linha<0) {
			JOptionPane.showMessageDialog(null,"Selecione algum registro");
		}
		else {
			Object cod = Jtable.getValueAt(linha,0);
			if(cod==null) {
				JOptionPane.showMessageDialog(null,"A linha selecionada nao contem dados");
			}
			else {
				codigo = Integer.parseInt(String.valueOf(Jtable.getValueAt(linha, 0)));
			}
		}
		return codigo;
	}
	
	public String PegaRegistroString(JTable Jtable) {
		String codigo = null;
		int linha = Jtable.getSelectedRow();
		if(linha<0) {
			JOptionPane.showMessageDialog(null,"Selecione algum registro");
		}
		else {
			Object cod = Jtable.getValueAt(linha,0);
			if(cod==null) {
				JOptionPane.showMessageDialog(null,"A linha selecionada nao contem dados");
			}
			else {
				codigo = String.valueOf(Jtable.getValueAt(linha, 0));
			}
		}
		return codigo;
	}
}
