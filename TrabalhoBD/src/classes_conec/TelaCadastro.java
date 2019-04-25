package classes_conec;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class TelaCadastro extends JFrame {

	private JPanel contentPane;
	private JTextField camp_sobrenome;
	private JTextField camp_nome;
	private JTextField camp_email;
	private JTextField camp_end;
	private JPasswordField camp_pass;
	private JTextField camp_cpf;
	private static ButtonGroup bg= new ButtonGroup();
	private static ButtonGroup bg2= new ButtonGroup();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastro frame = new TelaCadastro();
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
	public TelaCadastro() throws ParseException {
		setResizable(false);
		setTitle("Tela Cadastro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 396, 327);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPrimeiroNome = new JLabel("Primeiro Nome:");
		lblPrimeiroNome.setBounds(10, 11, 72, 22);
		contentPane.add(lblPrimeiroNome);
		
		JLabel lblSobrenome = new JLabel("Sobrenome:");
		lblSobrenome.setBounds(24, 41, 58, 22);
		contentPane.add(lblSobrenome);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(59, 74, 23, 14);
		contentPane.add(lblCpf);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(54, 99, 28, 14);
		contentPane.add(lblEmail);
		
		JLabel lblNewLabel = new JLabel("Endere\u00E7o:");
		lblNewLabel.setBounds(33, 124, 49, 14);
		contentPane.add(lblNewLabel);
		
		JRadioButton rdbtnAutor = new JRadioButton("Autor");
		rdbtnAutor.setBounds(88, 148, 58, 23);
		contentPane.add(rdbtnAutor);
		
		JRadioButton rdbtnAdministrador = new JRadioButton("Administrador");
		rdbtnAdministrador.setBounds(158, 148, 109, 23);
		contentPane.add(rdbtnAdministrador);
		
		camp_sobrenome = new JTextField();
		camp_sobrenome.setBounds(88, 42, 197, 20);
		contentPane.add(camp_sobrenome);
		camp_sobrenome.setColumns(10);
		
		camp_nome = new JTextField();
		camp_nome.setColumns(10);
		camp_nome.setBounds(88, 12, 197, 20);
		contentPane.add(camp_nome);
		
		camp_email = new JTextField();
		camp_email.setBounds(88, 96, 197, 20);
		contentPane.add(camp_email);
		camp_email.setColumns(10);
		
		camp_end = new JTextField();
		camp_end.setColumns(10);
		camp_end.setBounds(88, 121, 197, 20);
		contentPane.add(camp_end);
		
		JLabel lblTipoUsurio = new JLabel("Tipo usu\u00E1rio:");
		lblTipoUsurio.setBounds(20, 152, 62, 14);
		contentPane.add(lblTipoUsurio);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(48, 177, 34, 14);
		contentPane.add(lblSenha);
		
		camp_pass = new JPasswordField();
		camp_pass.setBounds(88, 174, 109, 20);
		contentPane.add(camp_pass);
		
		JLabel lblTipoAutor = new JLabel("Tipo autor:");
		lblTipoAutor.setBounds(24, 202, 58, 14);
		contentPane.add(lblTipoAutor);
		
		JRadioButton rdbtnEstudante = new JRadioButton("Estudante");
		rdbtnEstudante.setBounds(88, 201, 89, 23);
		contentPane.add(rdbtnEstudante);
		
		JRadioButton rdbtnAmador = new JRadioButton("Amador");
		rdbtnAmador.setBounds(179, 198, 72, 23);
		contentPane.add(rdbtnAmador);
		
		JRadioButton rdbtnProfissional = new JRadioButton("Profissional");
		rdbtnProfissional.setBounds(262, 198, 122, 23);
		contentPane.add(rdbtnProfissional);
		
		 bg.add(rdbtnAutor);
		 bg.add(rdbtnAdministrador);
		 
		 bg2.add(rdbtnEstudante);
		 bg2.add(rdbtnAmador);
		 bg2.add(rdbtnProfissional);
		 	
		JButton btnSalvar = new JButton("salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection cone = Conexao.faz_conexao();//faz a conexão com o banco
					String sql ="INSERT INTO senha(id_usuario, id_senha) VALUES (?,?)";					
					PreparedStatement stmt = cone.prepareStatement(sql);
					stmt.setString(1, camp_email.getText() );
					stmt.setString(2, new String(camp_pass.getPassword()));
					
					
					if(rdbtnAutor.isSelected()){
						
					String sql2 ="INSERT INTO usuario(pnome, Unome, cpf ,endereco, email) VALUES(?,?,?,?,?)";
					PreparedStatement stmt2 = cone.prepareStatement(sql2);
					stmt2.setString(1, camp_nome.getText());
					stmt2.setString(2, camp_sobrenome.getText() );
					stmt2.setString(3, camp_cpf.getText());
					stmt2.setString(4, camp_end.getText());
					stmt2.setString(5, camp_email.getText());
						
						
					String sql3 ="INSERT INTO autor(cpfa,endereco,Pnome,Unome,email) VALUES(?,?,?,?,?)";
					PreparedStatement stmt3 = cone.prepareStatement(sql3);

					stmt3.setString(1, camp_cpf.getText());
					stmt3.setString(2, camp_end.getText());
					stmt3.setString(3, camp_nome.getText());
					stmt3.setString(4, camp_sobrenome.getText() );
					stmt3.setString(5, camp_email.getText());
					
					stmt2.execute();
					stmt3.execute();
					stmt3.close();
					stmt2.close();
					}
					
					if(rdbtnAdministrador.isSelected()){
					String sql2 ="INSERT INTO usuario(pnome, Unome, cpf,endereco,email) VALUES(?,?,?,?,?)";	
					PreparedStatement stmt2 = cone.prepareStatement(sql2);
					stmt2.setString(1, camp_nome.getText());
					stmt2.setString(2, camp_sobrenome.getText() );
					stmt2.setString(3, camp_cpf.getText());
					stmt2.setString(4, camp_end.getText());
					stmt2.setString(5, camp_email.getText());
					
						
					String sql4="INSERT INTO Administrador(cpfad,Pnome,Unome,endereco,email) VALUES(?,?,?,?,?)";
					PreparedStatement stmt4 = cone.prepareStatement(sql4);
					stmt4.setString(1, camp_cpf.getText());
					stmt4.setString(2, camp_nome.getText());
					stmt4.setString(3, camp_sobrenome.getText());
					stmt4.setString(4, camp_end.getText());
					stmt4.setString(5, camp_email.getText());
					
					stmt2.execute();
					stmt4.execute();
					stmt2.close();
					stmt4.close();
					
					}
					
					if(rdbtnProfissional.isSelected()){
						String sq5 ="INSERT INTO profissional(cpf_a,endereco,Pnome,Unome,email) VALUES (?,?,?,?,?)";
						PreparedStatement stmt5 = cone.prepareStatement(sq5);
						stmt5.setString(1, camp_cpf.getText());
						stmt5.setString(2, camp_end.getText());
						stmt5.setString(3, camp_nome.getText());
						stmt5.setString(4, camp_sobrenome.getText());
						stmt5.setString(5, camp_email.getText());
						stmt5.execute();	
						stmt5.close();}
					
					if(rdbtnEstudante.isSelected()){
						String sq6 ="INSERT INTO estudante(cpf_a,endereco,Pnome,Unome,email) VALUES (?,?,?,?,?)";
						PreparedStatement stmt6 = cone.prepareStatement(sq6);
						stmt6.setString(1, camp_cpf.getText());
						stmt6.setString(2, camp_end.getText());
						stmt6.setString(3, camp_nome.getText());
						stmt6.setString(4, camp_sobrenome.getText());
						stmt6.setString(5, camp_email.getText());
						stmt6.execute();	
						stmt6.close();}
					
					if(rdbtnAmador.isSelected()){
						String sq7 ="INSERT INTO amador(cpf_a,endereco,Pnome,Unome,email) VALUES (?,?,?,?,?)";
						PreparedStatement stmt7 = cone.prepareStatement(sq7);
						stmt7.setString(1, camp_cpf.getText());
						stmt7.setString(2, camp_end.getText());
						stmt7.setString(3, camp_nome.getText());
						stmt7.setString(4, camp_sobrenome.getText());
						stmt7.setString(5, camp_email.getText());
						stmt7.execute();		
						stmt7.close();}
					
					stmt.execute();	
					stmt.close();
					cone.close();
					
					JOptionPane.showMessageDialog(null, "Usuário cadastrado");
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}
		});
		btnSalvar.setBounds(219, 264, 89, 23);
		contentPane.add(btnSalvar);
		
		camp_cpf = new JTextField();
		camp_cpf.setBounds(88, 71, 197, 20);
		contentPane.add(camp_cpf);
		camp_cpf.setColumns(10);
		
		
	}
}
