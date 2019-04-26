package classes_conec;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Tela_avaliacao extends JDialog {
	private JTextField campnota;
	private String id_nomeRe;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Tela_avaliacao dialog = new Tela_avaliacao();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setUndecorated(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Tela_avaliacao() {
		setModal(true);
		setBounds(100, 100, 212, 115);
		getContentPane().setLayout(null);
		
		JLabel lblAvaliao = new JLabel("Avalia\u00E7\u00E3o");
		lblAvaliao.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAvaliao.setBounds(10, 0, 114, 25);
		getContentPane().add(lblAvaliao);
		
		JLabel lblNota = new JLabel("Nota:");
		lblNota.setBounds(10, 38, 46, 14);
		getContentPane().add(lblNota);
		
		campnota = new JTextField();
		campnota.setBounds(45, 35, 86, 20);
		getContentPane().add(campnota);
		campnota.setColumns(10);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection cone;
				try {
					cone = Conexao.faz_conexao();
					String sql ="UPDATE receita SET nota= ? where nome= ?";					
					PreparedStatement stmt = cone.prepareStatement(sql);
					stmt.setString(1, campnota.getText());
					stmt.setString(2, id_nomeRe);
					
					
					stmt.execute();	
					stmt.close();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				dispose();
			}
		});
		btnOk.setBounds(140, 34, 46, 23);
		getContentPane().add(btnOk);
	}
	
	public void setNomeRe(String receita){
		id_nomeRe=receita;
	}
}
