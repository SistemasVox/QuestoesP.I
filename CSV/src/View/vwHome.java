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
import Test.SelecionarAqr;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class vwHome extends JFrame {

	private JPanel contentPane;
	private JLabel lblA, lblQ;
	private JButton bntMontar, btnLimpar, btnSalvar;
	private JTextArea txtArea, txtQ, txtA;
	private JScrollPane scrollQ, scrollA, scrollArea;
	private String[] ae = { "A", "B", "C", "D", "E" };
	private String[] eliminar = { " ", ")" };
	private ArrayList<String> listaAlternativas = new ArrayList<String>();
	private JButton btnSair;

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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 937, 722);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblQ = new JLabel("Q:");
		lblQ.setHorizontalAlignment(SwingConstants.RIGHT);
		lblQ.setBounds(851, 19, 63, 14);
		contentPane.add(lblQ);

		lblA = new JLabel("A:");
		lblA.setHorizontalAlignment(SwingConstants.RIGHT);
		lblA.setBounds(851, 43, 63, 14);
		contentPane.add(lblA);

		JLabel lblInsertQuestesSistemasvox = new JLabel("INSERT Quest\u00F5es, SistemasVOX.");
		lblInsertQuestesSistemasvox.setBounds(76, -5, 704, 57);
		lblInsertQuestesSistemasvox.setHorizontalAlignment(SwingConstants.CENTER);
		lblInsertQuestesSistemasvox.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		contentPane.add(lblInsertQuestesSistemasvox);

		txtArea = new JTextArea();
		txtArea.setEditable(false);
		txtArea.setWrapStyleWord(true);
		txtArea.setToolTipText("Quest\u00E3o Montada.");
		txtArea.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		txtArea.setLineWrap(true);

		contentPane.add(this.scrollArea = new JScrollPane(txtArea));
		scrollArea.setBounds(10, 319, 713, 354);

		btnSalvar = new JButton("Salvar Quest\u00E3o");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				salvar();
			}
		});
		btnSalvar.setBounds(745, 423, 159, 46);
		contentPane.add(btnSalvar);

		btnLimpar = new JButton("Limpar Campos");
		btnLimpar.setBounds(745, 319, 159, 46);
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpar();
			}
		});
		contentPane.add(btnLimpar);

		txtQ = new JTextArea();
		txtQ.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				bloquearBotao();
			}
		});
		txtQ.setFont(new Font("Monospaced", Font.ITALIC, 14));
		txtQ.setLineWrap(true);
		txtQ.setWrapStyleWord(true);
		txtQ.setToolTipText("Informe o Enunciado");

		contentPane.add(this.scrollQ = new JScrollPane(txtQ));
		scrollQ.setBounds(10, 60, 907, 65);

		txtA = new JTextArea();
		txtA.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				bloquearBotao();
			}
		});
		txtA.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 14));
		txtA.setLineWrap(true);
		txtA.setWrapStyleWord(true);
		txtA.setToolTipText("Informe as Alternativas?");

		contentPane.add(this.scrollA = new JScrollPane(txtA));
		scrollA.setBounds(10, 136, 907, 165);

		bntMontar = new JButton("Montar Quest\u00E3o");
		bntMontar.setBounds(745, 371, 159, 46);
		bntMontar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				montarQuestao();
			}
		});
		contentPane.add(bntMontar);

		JButton btnListar = new JButton("Listar Tudo.");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listarQuestoes();
			}
		});
		btnListar.setBounds(745, 476, 159, 46);
		contentPane.add(btnListar);

		JButton btnExpor = new JButton("Exportar CSV");
		btnExpor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarCSV();
			}
		});
		btnExpor.setBounds(745, 533, 159, 46);
		contentPane.add(btnExpor);

		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSair.setBounds(745, 590, 159, 46);
		contentPane.add(btnSair);
		atualizarLBL();
		bloquearBotao();
	}

	protected void exportarCSV() {
		ArrayList<Questoes> questoes = new ArrayList<Questoes>();
		ArrayList<Alternativa> alternativas = new ArrayList<Alternativa>();

		for (int i = 1; i <= Integer.parseInt(Controladora.consultarTotalQ()); i++) {
			questoes.add(Controladora.consultarQuestao(String.valueOf(i)));
		}

		for (int i = 1; i <= Integer.parseInt(Controladora.consultarTotalA()); i++) {
			alternativas.add(Controladora.consultarAlternativa(String.valueOf(i)));
		}
		exportarQ(questoes);
		exportarA(alternativas);
	}

	private void exportarQ(ArrayList<Questoes> questoes) {
		try {
			PrintWriter pw = new PrintWriter(new File("Questões.csv"));
			StringBuilder sb = new StringBuilder();
			String separador = ",";
			String finalizador = "\r\n";

			sb.append("cod");
			sb.append(separador);
			sb.append("enunciado");
			sb.append(separador);
			sb.append("referencia");
			sb.append(finalizador);

			for (int i = 0; i < questoes.size(); i++) {
				sb.append(questoes.get(i).getCod());
				sb.append(separador);
				sb.append("'" + questoes.get(i).getEnunciado() + "'");
				sb.append(separador);
				sb.append("'" + questoes.get(i).getReferencia() + "'");
				sb.append(finalizador);
			}
			pw.write(sb.toString());
			pw.close();
			txtArea.setText("Exportado com Sucesso.");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	private void exportarA(ArrayList<Alternativa> alternativas) {
		try {
			PrintWriter pw = new PrintWriter(new File("Alternativas.csv"));
			StringBuilder sb = new StringBuilder();
			String separador = ",";
			String finalizador = "\r\n";

			sb.append("cod");
			sb.append(separador);
			sb.append("cod_q");
			sb.append(separador);
			sb.append("classificacao");
			sb.append(separador);
			sb.append("resposta");
			sb.append(finalizador);

			for (int i = 0; i < alternativas.size(); i++) {
				sb.append(alternativas.get(i).getCod());
				sb.append(separador);
				sb.append(alternativas.get(i).getCod_q());
				sb.append(separador);
				sb.append(alternativas.get(i).getClassificacao());
				sb.append(separador);
				sb.append("'" + alternativas.get(i).getResposta()+ "'");
				sb.append(finalizador);
			}
			pw.write(sb.toString());
			pw.close();
			txtArea.setText("Exportado com Sucesso.");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	protected void listarQuestoes() {
		listartudo();
	}

	private void listartudo() {
		ArrayList<Questoes> questoes = new ArrayList<Questoes>();
		String s = "";

		for (int i = 1; i <= Integer.parseInt(Controladora.consultarTotalQ()); i++) {
			questoes.add(Controladora.consultarQuestao(String.valueOf(i)));
		}

		for (int i = 0; i < questoes.size(); i++) {
			s += questoes.get(i).getCod() + ") " + questoes.get(i).getEnunciado() + "\n\n";

			ArrayList<Alternativa> alternativas = Controladora.getAlternativas(questoes.get(i).getCod());
			
			for (int j = 0; j < alternativas.size(); j++) {
				s += ae[j] + eliminar[1] + " " + alternativas.get(j).getResposta() + "\n";
			}
			s += "--------------------------------------------------------------------------------------\n";
		}

		txtArea.setText(s.replace(": ", ":\n\n") + "FIM.\n");
	}

	private void bloquearBotao() {
		btnLimpar.setEnabled(false);
		btnSalvar.setEnabled(false);
	}

	protected void salvar() {
		Controladora.savarQ(new Questoes((String.valueOf(Integer.parseInt(Controladora.consultarTotalQ()) + 1)),
				(tratarQ(txtQ.getText())), "RFV"));
		
		for (int i = 0; i < listaAlternativas.size(); i++) {
			Controladora.savarA(new Alternativa(String.valueOf(Integer.parseInt(Controladora.consultarTotalA()) + 1),
					String.valueOf(Integer.parseInt(Controladora.consultarTotalQ())), "0", listaAlternativas.get(i)));
		}
		atualizarLBL();
		limpar();
		txtArea.setText("Salvo com Sucesso.");
		bloquearBotao();

	}

	private String tratarQ(String txtQuestaoTela) {
		return txtQuestaoTela.replace("\n", " ").trim();
	}

	protected void limpar() {
		txtA.setText("");
		txtQ.setText("");
		txtArea.setText("");
		bloquearBotao();
		atualizarLBL();
	}

	protected void montarQuestao() {
		if (txtQ.getText().isEmpty() || txtA.getText().isEmpty()) {
			txtArea.setText("Poxa Vida, os Campos de Enunciado e Alternativas estão vazios.!!!!");
		} else {
			String s = "";
			s += (String.valueOf(Integer.parseInt(Controladora.consultarTotalQ()) + 1)) + ") "
					+ txtQ.getText().replace("\n", " ") + "\n";
			s += alternativasLimpa(txtA.getText());
			txtArea.setText(s);
			liberarbotao();
		}
	}

	private void liberarbotao() {
		btnLimpar.setEnabled(true);
		btnSalvar.setEnabled(true);

	}

	private String alternativasLimpa(String textoAlternativa) {
		String[] alternativas;
		listaAlternativas.clear();
		String s = "";
		textoAlternativa = textoAlternativa.replace("\n", " ").trim();
		
		alternativas = textoAlternativa.split(Pattern.quote("."));
		
		for (int i = 0; i < alternativas.length; i++) {
			s += ("\n" + ae[i] +") " + tratarA(alternativas[i]));
			listaAlternativas.add(tratarA(alternativas[i]));
		}
		return s;
	}

	private String tratarA(String txt) {
		
		for (int i = 0; i < ae.length; i++) {
			for (int j = 0; j < eliminar.length; j++) {	
				try {
					if (txt.trim().substring(0, 2).equals((ae [i] + eliminar[j]))) {
						txt = txt.replace(ae [i] + eliminar[j], " ");	
					}
				} catch (Exception e) {
					
				}					
			}
		}

		return txt.trim() + ".";
	}

	private void atualizarLBL() {
		lblA.setText("A: " + Controladora.consultarTotalA() + ".");
		lblQ.setText("Q: " + Controladora.consultarTotalQ() + ".");
	}
}
