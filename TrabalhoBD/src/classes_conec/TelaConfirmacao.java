package classes_conec;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class TelaConfirmacao extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private String id_nomeRe;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TelaConfirmacao dialog = new TelaConfirmacao();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TelaConfirmacao() {
		setModal(true);
		setBounds(100, 100, 280, 139);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JButton btnSim = new JButton("Ok");
			btnSim.setBounds(81, 63, 49, 23);
			btnSim.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Connection cone;
					
					try {
						cone = Conexao.faz_conexao();
						String sql ="delete from receita where nome=?";					
						PreparedStatement stmt = cone.prepareStatement(sql);
						stmt.setString(1, id_nomeRe);
						
						stmt.execute();	
						stmt.close();
						dispose();
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});
			contentPanel.setLayout(null);
			contentPanel.add(btnSim);
		}
		{
			JLabel lblTemCerteza = new JLabel("Deseja realmente exluir?");
			lblTemCerteza.setBounds(10, 11, 182, 27);
			lblTemCerteza.setFont(new Font("Tahoma", Font.PLAIN, 15));
			contentPanel.add(lblTemCerteza);
		}
		{
			JButton btnNewButton = new JButton("Cancelar");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnNewButton.setBounds(152, 63, 89, 23);
			contentPanel.add(btnNewButton);
		}
	}
	
	public void setNomeRe(String receita){
		id_nomeRe=receita;
	}

}
