package classes_conec;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Tela_login extends JFrame {

	private JPanel contentPane;
	public JTextField caixausu;
	private JPasswordField caixapass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_login frame = new Tela_login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		 try {
	            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
	                if ("Windows".equals(info.getName())) {
	                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
	                    break;
	                }
	            }
	        } catch (ClassNotFoundException ex) {
	            System.err.println(ex);
	        } catch (InstantiationException ex) {
	        	System.err.println(ex);
	        } catch (IllegalAccessException ex) {
	        	System.err.println(ex);	        
	        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
	        	System.err.println(ex);
	        }
		 
		 
		 
	}

	/**
	 * Create the frame.
	 */
	public Tela_login() {
		
		setResizable(false);
		setTitle("Tela Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 475, 399);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblUsuario = new JLabel("Usu\u00E1rio:");
		lblUsuario.setBounds(123, 137, 62, 22);
		lblUsuario.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
		contentPane.add(lblUsuario);
		
		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setBounds(123, 101, 55, 23);
		lblLogin.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 17));
		contentPane.add(lblLogin);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(133, 180, 51, 22);
		lblSenha.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
		contentPane.add(lblSenha);
		
		caixausu = new JTextField();
		caixausu.setBounds(195, 140, 158, 20);
		contentPane.add(caixausu);
		caixausu.setColumns(10);
		
		caixapass = new JPasswordField();
		caixapass.setBounds(194, 183, 159, 20);
		contentPane.add(caixapass);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection cone = Conexao.faz_conexao();//faz a conexão com o banco
					String sql ="select * from senha where id_usuario = ? and id_senha=?";
					PreparedStatement stmt = cone.prepareStatement(sql);//transfera a msg sql para o banco
					stmt.setString(1, caixausu.getText() );//pega o conteudo da caixa usuario
					stmt.setString(2, new String(caixapass.getPassword()));//pega o conteudo da caixa senha
					
					ResultSet rs = stmt.executeQuery();//armazena o resultado da consulta em rs
					
					if(rs.next()) {
						Tela_cadasReceita exibir = new Tela_cadasReceita();
						exibir.setVisible(true);
						
						setVisible(false);
						
					}else {
						JOptionPane.showMessageDialog(null,"Usuário não cadastrado");
						
					}
					
					stmt.close();
					cone.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnEntrar.setBounds(246, 245, 89, 23);
		contentPane.add(btnEntrar);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TelaCadastro exibir = null;
				try {
					exibir = new TelaCadastro();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				exibir.setVisible(true);
				
				setVisible(false);
			}
		});
		btnCadastrar.setBounds(117, 245, 103, 23);
		contentPane.add(btnCadastrar);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(Tela_login.class.getResource("/classes_conec/user.png")));
		label_2.setBounds(188, 23, 103, 93);
		contentPane.add(label_2);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Tela_login.class.getResource("/classes_conec/2bg.png")));
		label_1.setBounds(84, 23, 357, 300);
		contentPane.add(label_1);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Tela_login.class.getResource("/classes_conec/planofundo.png")));
		label.setBounds(0, 0, 469, 370);
		contentPane.add(label);
		
		
	}
}

