package classes_conec;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import java.awt.TextField;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Tela_Camposreceita extends JFrame {

	private JPanel contentPane;
	private JTextField campoNome;
	private JTextField campoIngre;
	private JTextField campoQuant;
	private JTextField Auto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_Camposreceita frame = new Tela_Camposreceita();
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
	public Tela_Camposreceita() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCadastroRceita = new JLabel("Cadastro Receita");
		lblCadastroRceita.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCadastroRceita.setBounds(10, 0, 154, 25);
		contentPane.add(lblCadastroRceita);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(45, 39, 31, 14);
		contentPane.add(lblNome);
		
		campoNome = new JTextField();
		campoNome.setBounds(78, 36, 147, 20);
		contentPane.add(campoNome);
		campoNome.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Ingredientes:");
		lblNewLabel.setBounds(10, 67, 65, 14);
		contentPane.add(lblNewLabel);
		
		campoIngre = new JTextField();
		campoIngre.setBounds(78, 67, 147, 20);
		contentPane.add(campoIngre);
		campoIngre.setColumns(10);
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setBounds(235, 70, 65, 14);
		contentPane.add(lblQuantidade);
		
		campoQuant = new JTextField();
		campoQuant.setBounds(299, 67, 46, 20);
		contentPane.add(campoQuant);
		campoQuant.setColumns(10);
		
		JButton button = new JButton("+");
		button.setBounds(355, 66, 46, 23);
		contentPane.add(button);
		
		JLabel lblDescri = new JLabel("Modo de Preparo:");
		lblDescri.setBounds(10, 92, 104, 14);
		contentPane.add(lblDescri);
		
		TextArea campo_mod = new TextArea();
		campo_mod.setBounds(78, 112, 314, 108);
		contentPane.add(campo_mod);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Tela_login objetologin = new Tela_login();
					Connection cone = Conexao.faz_conexao();
					String sql ="INSERT INTO ingredientes(nome,quantidade,nomeRe) VALUES (?,?,?)";					
					PreparedStatement stmt = cone.prepareStatement(sql);
					stmt.setString(1, campoIngre.getText());
					stmt.setString(2, campoQuant.getText());
					stmt.setString(3, campoNome.getText());
					
					
					String sql2 ="INSERT INTO receita(email,modoP,nome) VALUES (?,?,?)";					
					PreparedStatement stmt2 = cone.prepareStatement(sql2);
					stmt2.setString(1, Auto.getText());
					stmt2.setString(2, campo_mod.getText());
					stmt2.setString(3, campoNome.getText());
					
					stmt2.execute();
					stmt.execute();
					stmt.close();
					stmt2.close();
					cone.close();
					
					
					
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				
				
				
			}
		});
		btnSalvar.setBounds(335, 226, 89, 23);
		contentPane.add(btnSalvar);
		
		JLabel lblAutor = new JLabel("Autor Id:");
		lblAutor.setBounds(235, 39, 46, 14);
		contentPane.add(lblAutor);
		
		Auto = new JTextField();
		Auto.setBounds(285, 36, 116, 20);
		contentPane.add(Auto);
		Auto.setColumns(10);
	}
}
