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
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		btnNewButton.setToolTipText(" Click e cadastre novas quest\u00F5es.");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vwCadastrarQuestoes home = new vwCadastrarQuestoes();
				home.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		btnNewButton.setBounds(10, 55, 242, 130);
		contentPane.add(btnNewButton);
		
		JButton btnMontarQuestes = new JButton("Gerar Avalia\u00E7\u00E3o");
		btnMontarQuestes.setToolTipText("Click e crie novas avalia\u00E7\u00F5es");
		btnMontarQuestes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vwGerarAvaliacao montaQuestao = new vwGerarAvaliacao();
				montaQuestao.setVisible(true);
			}
		});
		btnMontarQuestes.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		btnMontarQuestes.setBounds(263, 55, 242, 130);
		contentPane.add(btnMontarQuestes);
		
		JLabel lblXquest = new JLabel("X-Quest");
		lblXquest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new vwAbout().setVisible(true);
			}
		});
		lblXquest.setToolTipText("Click e veja mais sobre o Software.");
		lblXquest.setHorizontalAlignment(SwingConstants.CENTER);
		lblXquest.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblXquest.setBounds(10, 0, 495, 57);
		contentPane.add(lblXquest);
		
		JButton btnAsso = new JButton("Associar Quest\u00F5es");
		btnAsso.setToolTipText("Click e associe antigas e novas quest\u00F5es aos seus respectivos conte\u00FAdos.");
		btnAsso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new vwAssociarQcC().setVisible(true);
			}
		});
		btnAsso.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		btnAsso.setBounds(10, 196, 242, 130);
		contentPane.add(btnAsso);
		
		JButton btnCadastrarContedo = new JButton("Cadastrar Conte\u00FAdo");
		btnCadastrarContedo.setToolTipText("Click e cadastre novos conte\u00FAdos a suas disciplinas.");
		btnCadastrarContedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new vwConteudo().setVisible(true);
			}
		});
		btnCadastrarContedo.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		btnCadastrarContedo.setBounds(263, 196, 242, 130);
		contentPane.add(btnCadastrarContedo);
	}
}
