package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Controller.Controladora;
import Model.Alternativa;
import Model.Questoes;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class vwHome extends JFrame {

	private JPanel contentPane;
	private JLabel lblA, lblQ;
	private JButton bntMontar;
	private JTextArea txtArea, txtQ, txtA;
	private JScrollPane scrollQ, scrollA, scrollArea;
	private String [] ae = {"A) ","B) ","C) ","D) ","E) "};
	private ArrayList<String> listaAlternativas = new ArrayList<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vwHome frame = new vwHome();
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
	public vwHome() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 937, 722);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblQ = new JLabel("Q:");
		lblQ.setHorizontalAlignment(SwingConstants.RIGHT);
		lblQ.setBounds(868, 19, 46, 14);
		contentPane.add(lblQ);
		
		lblA = new JLabel("A:");
		lblA.setHorizontalAlignment(SwingConstants.RIGHT);
		lblA.setBounds(868, 43, 46, 14);
		contentPane.add(lblA);
		
		JLabel lblInsertQuestesSistemasvox = new JLabel("INSERT Quest\u00F5es, SistemasVOX.");
		lblInsertQuestesSistemasvox.setBounds(76, -5, 704, 57);
		lblInsertQuestesSistemasvox.setHorizontalAlignment(SwingConstants.CENTER);
		lblInsertQuestesSistemasvox.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		contentPane.add(lblInsertQuestesSistemasvox);
		
		txtArea = new JTextArea();
		txtArea.setWrapStyleWord(true);
		txtArea.setToolTipText("Quest\u00E3o Montada.");
		txtArea.setFont(new Font("Monospaced", Font.BOLD, 14));
		txtArea.setLineWrap(true);
		
		contentPane.add(this.scrollArea = new JScrollPane(txtArea));
		scrollArea.setBounds(10, 319, 713, 354);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				salvar();
			}
		});
		btnSalvar.setBounds(745, 423, 159, 46);
		contentPane.add(btnSalvar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(745, 319, 159, 46);
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpar();
			}
		});
		contentPane.add(btnLimpar);
		
		txtQ = new JTextArea();
		txtQ.setFont(new Font("Monospaced", Font.ITALIC, 14));
		txtQ.setLineWrap(true);
		txtQ.setWrapStyleWord(true);
		txtQ.setToolTipText("Informe o Enunciado");
		
		contentPane.add(this.scrollQ = new JScrollPane(txtQ));
		scrollQ.setBounds(10, 60, 907, 65);
		
		txtA = new JTextArea();
		txtA.setFont(new Font("Monospaced", Font.BOLD, 14));
		txtA.setLineWrap(true);
		txtA.setWrapStyleWord(true);
		txtA.setToolTipText("Informe as Alternativas?");
		
		contentPane.add(this.scrollA = new JScrollPane(txtA));
		scrollA.setBounds(10, 136, 907, 165);
		
		bntMontar = new JButton("Montar");
		bntMontar.setBounds(745, 371, 159, 46);
		bntMontar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				montarQuestao();
			}
		});
		contentPane.add(bntMontar);
		atualizarLBL();
	}

	protected void salvar() {
		Controladora.savarQ(new Questoes((String.valueOf(Integer.parseInt(Controladora.consultarTotalQ()) + 1)), (txtQ.getText().replace("\n", " ") +":\n"), ""));
		for (int i = 0; i < listaAlternativas.size(); i++) {
			Controladora.savarA(new Alternativa(String.valueOf(Integer.parseInt(Controladora.consultarTotalA()) + 1), String.valueOf(Integer.parseInt(Controladora.consultarTotalQ())), "0", listaAlternativas.get(i)));
		}
		atualizarLBL();
		limpar();
		txtArea.setText("Salvo com Sucesso.");
		
	}

	protected void limpar() {
		txtA.setText("");
		txtQ.setText("");
		txtArea.setText("");
		atualizarLBL();
	}

	protected void montarQuestao() {
		String s = "";
		s += (Controladora.consultarTotalQ() + 1) + ") " + txtQ.getText().replace("\n", " ") +":\n";
		s += alternativasLimpa(txtA.getText());
		txtArea.setText(s);
		
	}

	private String alternativasLimpa(String texto) {
		String[] alternativas;	
		String s = "";
		texto = texto.replace("\n", "");
		alternativas = texto.split(Pattern.quote("."));
		for (int i = 0; i < alternativas.length; i++) {
			s += ("\n" + ae[i]+ alternativas[i].substring(2, alternativas[i].length()) + ".");
			listaAlternativas.add(alternativas[i].substring(2, alternativas[i].length()) + ".");
			//System.out.println(alternativas[i].substring(2, alternativas[i].length()) + ".");
			//System.out.println(alternativas[i]);
		}
		return s;
	}

	private void atualizarLBL() {
		lblA.setText("A: " + Controladora.consultarTotalA() + ".");
		lblQ.setText("Q: " + Controladora.consultarTotalQ() + ".");	
	}
}
