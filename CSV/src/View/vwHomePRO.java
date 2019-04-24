package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class vwHomePRO extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vwHomePRO frame = new vwHomePRO();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public vwHomePRO() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 531, 366);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Cadastar Quest\u00F5es");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vwCadastrarQuestoes home = new vwCadastrarQuestoes();
				home.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		btnNewButton.setBounds(10, 55, 242, 130);
		contentPane.add(btnNewButton);
		
		JButton btnMontarQuestes = new JButton("Montar Quest\u00F5es");
		btnMontarQuestes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vwMontaQuestao montaQuestao = new vwMontaQuestao();
				montaQuestao.setVisible(true);
			}
		});
		btnMontarQuestes.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		btnMontarQuestes.setBounds(263, 55, 242, 130);
		contentPane.add(btnMontarQuestes);
		
		JLabel lblXquest = new JLabel("X-Quest");
		lblXquest.setHorizontalAlignment(SwingConstants.CENTER);
		lblXquest.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblXquest.setBounds(10, 0, 495, 57);
		contentPane.add(lblXquest);
		
		JButton btnAsso = new JButton("Associar Quest\u00F5es");
		btnAsso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new vwAssociarQcC().setVisible(true);
			}
		});
		btnAsso.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		btnAsso.setBounds(10, 196, 242, 130);
		contentPane.add(btnAsso);
		
		JButton btnCadastrarContedo = new JButton("Cadastrar Conte\u00FAdo");
		btnCadastrarContedo.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		btnCadastrarContedo.setBounds(263, 196, 242, 130);
		contentPane.add(btnCadastrarContedo);
	}
}
