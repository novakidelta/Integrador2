package modelos;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

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
    String situacao;
    JTable tablesuspeito;
    PerguntaSuspeitoDAO dao = new PerguntaSuspeitoDAO();

        public ButtonColumn(JTable table,JTable tablesuspeito, int column)
        {
            super();
            this.table = table;
            this.tablesuspeito = tablesuspeito;
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
   
            /*if (hasFocus)
            {
                renderButton.setBackground(new Color(0, 0, 255));
            }
            else if (isSelected)
            {

            }
            else
            {

            }*/
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
            situacao="";
            
            int linhaPergunta = table.getSelectedRow();
            int idpergunta = (int) table.getValueAt(linhaPergunta, 0);
            int linhaSuspeito =  tablesuspeito.getSelectedRow();
            int idSuspeito = (int) tablesuspeito.getValueAt(linhaSuspeito, 0);
            PerguntaSuspeito perguntaSuspeito = new PerguntaSuspeito();
            situacao = (String) table.getValueAt(linhaPergunta, 2);
            System.out.println(linhaPergunta +  idpergunta);
            if(situacao== "ADICIONAR") {
            	perguntaSuspeito.setFK_IDPergunta(idpergunta);
                perguntaSuspeito.setFK_IDSuspeito(idSuspeito);
                perguntaSuspeito.setFK_SusSit(1);
            	dao.inserirPerguntaeSuspeito(perguntaSuspeito);
            }
            else if (situacao == "REMOVER") {
            	try {
            		System.out.println("remover");
					dao.ExcluirPerguntaeSuspeito(idpergunta, idSuspeito);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
            
            
            
        }

}
