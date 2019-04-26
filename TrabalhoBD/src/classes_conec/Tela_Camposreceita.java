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
	private static String autor;

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
		setBounds(100, 100, 450, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCadastroRceita = new JLabel("Receita");
		lblCadastroRceita.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCadastroRceita.setBounds(10, 0, 154, 25);
		contentPane.add(lblCadastroRceita);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 39, 31, 14);
		contentPane.add(lblNome);
		
		campoNome = new JTextField();
		campoNome.setBounds(43, 36, 147, 20);
		contentPane.add(campoNome);
		campoNome.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Ingredientes:");
		lblNewLabel.setBounds(11, 143, 65, 14);
		contentPane.add(lblNewLabel);
		
		campoIngre = new JTextField();
		campoIngre.setBounds(78, 140, 147, 20);
		contentPane.add(campoIngre);
		campoIngre.setColumns(10);
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setBounds(235, 143, 65, 14);
		contentPane.add(lblQuantidade);
		
		campoQuant = new JTextField();
		campoQuant.setBounds(299, 140, 46, 20);
		contentPane.add(campoQuant);
		campoQuant.setColumns(10);
		
		JButton button = new JButton("+");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection cone;
				try {
					cone = Conexao.faz_conexao();
					String sql ="INSERT INTO ingredientes(nome,quantidade,nomeRe) VALUES (?,?,?)";					
					PreparedStatement stmt = cone.prepareStatement(sql);
					stmt.setString(1, campoIngre.getText());
					stmt.setString(2, campoQuant.getText());
					stmt.setString(3, campoNome.getText());
					
					campoIngre.setText("");
					campoQuant.setText("");
					
				

					stmt.execute();
					stmt.close();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		button.setBounds(355, 139, 46, 23);
		contentPane.add(button);
		
		JLabel lblDescri = new JLabel("Modo de Preparo:");
		lblDescri.setBounds(10, 168, 104, 14);
		contentPane.add(lblDescri);
		
		TextArea campo_mod = new TextArea();
		campo_mod.setBounds(97, 178, 314, 108);
		contentPane.add(campo_mod);
		
		JButton btnSalvar = new JButton("Cadastrar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection cone = Conexao.faz_conexao();
					
					String sql2 ="INSERT INTO receita(modoP,email,nome) VALUES (?,?,?)";					
					PreparedStatement stmt2 = cone.prepareStatement(sql2);
					stmt2.setString(1, campo_mod.getText());
					stmt2.setString(2, autor);
					stmt2.setString(3, campoNome.getText());
					
					stmt2.execute();
					stmt2.close();
					cone.close();
					
					
					
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				
				
				
			}
		});
		btnSalvar.setBounds(312, 71, 89, 23);
		contentPane.add(btnSalvar);
		
		JLabel lblIngredientes = new JLabel("Ingredientes");
		lblIngredientes.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblIngredientes.setBounds(10, 90, 162, 33);
		contentPane.add(lblIngredientes);
		
		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection cone;
				try {
					cone = Conexao.faz_conexao();
					String sql3 ="UPDATE receita SET modoP= ? where nome= ?";					
					PreparedStatement stmt3 = cone.prepareStatement(sql3);
					stmt3.setString(1, campo_mod.getText());
					stmt3.setString(2, campoNome.getText());
					
					stmt3.execute();
					stmt3.close();
					cone.close();
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnFinalizar.setBounds(335, 292, 89, 23);
		contentPane.add(btnFinalizar);
	}
	
	public void setAutor(String nome){
		autor=nome;
	}
}
