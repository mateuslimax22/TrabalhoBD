package classes_conec;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;

public class Tela_cadasReceita extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField campesq;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_cadasReceita frame = new Tela_cadasReceita();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Tela_cadasReceita() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 719, 522);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Receitas");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 31));
		lblNewLabel.setBounds(21, 12, 124, 33);
		contentPane.add(lblNewLabel);
		
		JButton NovaReceita = new JButton("Novo");
		NovaReceita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Tela_Camposreceita exibir = new Tela_Camposreceita();
				exibir.setVisible(true);
				
				setVisible(false);
				
			}
		});
		NovaReceita.setBounds(21, 56, 97, 23);
		contentPane.add(NovaReceita);
		
		JButton btnEditar = new JButton("Editar ");
		btnEditar.setBounds(128, 56, 89, 23);
		contentPane.add(btnEditar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(227, 56, 89, 23);
		contentPane.add(btnExcluir);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 108, 303, 281);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome", "Nota"
			}
		));
		scrollPane.setViewportView(table);
		
		campesq = new JTextField();
		campesq.setBounds(404, 57, 190, 20);
		contentPane.add(campesq);
		campesq.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Connection cone;
				try {
					cone = Conexao.faz_conexao();
					String sql ="SELECT * FROM receita WHERE nome LIKE ?";					
					PreparedStatement stmt = cone.prepareStatement(sql);
					stmt.setString(1, campesq.getText());
					
					ResultSet rs = stmt.executeQuery();
					
					DefaultTableModel modelo =(DefaultTableModel) table.getModel();
					modelo.setNumRows(0);
					
					while(rs.next()){
						modelo.addRow(new Object[] {rs.getString("nome")});
						
						
					}
					rs.close();
					cone.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				
				
			}
		});
		btnPesquisar.setBounds(604, 56, 89, 23);
		contentPane.add(btnPesquisar);
	}
}
