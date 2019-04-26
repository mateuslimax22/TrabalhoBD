package classes_conec;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
	private String nomeRe;
	private JTable table_1;

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
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setBounds(21, 74, 124, 33);
		contentPane.add(lblNewLabel);
		
		JButton NovaReceita = new JButton("Novo");
		NovaReceita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Tela_Camposreceita exibir = new Tela_Camposreceita();
				exibir.setVisible(true);
				
				setVisible(false);
				
			}
		});
		NovaReceita.setBounds(21, 31, 97, 23);
		contentPane.add(NovaReceita);
		
		JButton btnEditar = new JButton("Editar ");
		btnEditar.setBounds(128, 31, 89, 23);
		contentPane.add(btnEditar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int FilaSelect= table.getSelectedRow();
				
				if(FilaSelect >=0) {
					nomeRe = (String)table.getValueAt(table.getSelectedRow(), 0);
					TelaConfirmacao exibir = new TelaConfirmacao();
					exibir.setNomeRe(nomeRe);
					exibir.setVisible(true);
					
					setVisible(true);
				
				}
			}
		});
		btnExcluir.setBounds(227, 31, 89, 23);
		contentPane.add(btnExcluir);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 112, 303, 281);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome", "Ingrediente", "Nota"
			}
		));
		scrollPane.setViewportView(table);
		
		campesq = new JTextField();
		campesq.setBounds(426, 32, 168, 20);
		contentPane.add(campesq);
		campesq.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Connection cone;
				try {
					cone = Conexao.faz_conexao();
					String sql ="SELECT receita.nome,ingredientes.nome,receita.nota FROM ingredientes JOIN receita ON ingredientes.nomeRe=receita.nome AND ingredientes.nome LIKE ?";					
					PreparedStatement stmt = cone.prepareStatement(sql);
					stmt.setString(1, campesq.getText());
					
					ResultSet rs = stmt.executeQuery();
					
					DefaultTableModel modelo =(DefaultTableModel) table.getModel();
					modelo.setNumRows(0);
					
					while(rs.next()){
						modelo.addRow(new Object[] {rs.getString("receita.nome"),rs.getString("ingredientes.nome"),rs.getString("receita.nota")});
						
						
					}
					rs.close();
					cone.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				
				
			}
		});
		btnPesquisar.setBounds(604, 31, 89, 23);
		contentPane.add(btnPesquisar);
		
		JButton btnAvaliar = new JButton("Avaliar");
		btnAvaliar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int FilaSelect= table.getSelectedRow();
				if(FilaSelect >=0) {
					nomeRe = (String)table.getValueAt(table.getSelectedRow(), 0);
					Tela_avaliacao exibir = new Tela_avaliacao();
					exibir.setNomeRe(nomeRe);
					exibir.setVisible(true);
					
					
					setVisible(true);
					
				}
				
				
			}
		});
		btnAvaliar.setBounds(327, 31, 89, 23);
		contentPane.add(btnAvaliar);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(370, 112, 303, 281);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome", "Categoria"
			}
		));
		scrollPane_1.setViewportView(table_1);
		
		JLabel lblSugestoDoDia = new JLabel("Sugest\u00E3o do dia");
		lblSugestoDoDia.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblSugestoDoDia.setBounds(370, 81, 234, 27);
		contentPane.add(lblSugestoDoDia);
	}
}
