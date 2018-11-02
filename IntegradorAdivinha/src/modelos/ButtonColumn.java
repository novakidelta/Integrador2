package modelos;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import Classes.PerguntaSuspeito;
import DAO.PerguntaSuspeitoDAO;
import Telas.CadastrSuspeito;


//OUR MAIN CLASS
public class ButtonColumn extends AbstractCellEditor 
implements TableCellRenderer, TableCellEditor, ActionListener{
	
	JTable table;
    JButton renderButton;
    JButton editButton;
    String text;
    PerguntaSuspeitoDAO dao = new PerguntaSuspeitoDAO();

        public ButtonColumn(JTable table, int column)
        {
            super();
            this.table = table;
            renderButton = new JButton();
            editButton = new JButton();
            editButton.setFocusPainted( false );
            editButton.addActionListener( this );
            TableColumnModel columnModel = table.getColumnModel();
            columnModel.getColumn(column).setCellRenderer( this );
            columnModel.getColumn(column).setCellEditor( this );
        }
        public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
        {
   
            if (hasFocus)
            {
                renderButton.setBackground(new Color(0, 0, 255));
            }
            else if (isSelected)
            {

            }
            else
            {

            }
            renderButton.setText( (value == null) ? "" : value.toString() );
            return renderButton;
        }
        public Component getTableCellEditorComponent(
            JTable table, Object value, boolean isSelected, int row, int column)
        {
            text = (value == null) ? "" : value.toString();
            editButton.setText( text );
            return editButton;
        }
        public Object getCellEditorValue()
        {
            return text;
        }
        public void actionPerformed(ActionEvent e)
        {
            fireEditingStopped();
            int linha = table.getSelectedRow();
            int id = (int) table.getValueAt(linha, 0);
            PerguntaSuspeito perguntaSuspeito = new PerguntaSuspeito();
            CadastrSuspeito  cadastrSuspeito = new CadastrSuspeito();
            
            perguntaSuspeito.setFK_IDPergunta(id);
            perguntaSuspeito.setFK_IDSuspeito(1);
            perguntaSuspeito.setFK_SusSit(1);
            dao.inserirPerguntaeSuspeito(perguntaSuspeito);

            System.out.println(table.getValueAt(linha, 0));
            
        }

}
