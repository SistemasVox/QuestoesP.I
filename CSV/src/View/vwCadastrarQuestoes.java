package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Controller.Controladora;
import Model.Alternativa;
import Model.Questoes;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class vwCadastrarQuestoes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblA, lblQ;
	private JButton bntMontar, btnLimpar, btnSalvar;
	private static JTextArea txtArea;
	private JTextArea txtQ;
	private JTextArea txtA;
	private JScrollPane scrollQ, scrollA, scrollArea;
	private String eliminar;
	private ArrayList<String> listaAlternativas = new ArrayList<String>();
	private JButton btnSair;
	private JRadioButton rdP, rbS;
	private ButtonGroup buttonGroup = new ButtonGroup();
	private static final int alfabeto = 26;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vwCadastrarQuestoes frame = new vwCadastrarQuestoes();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public vwCadastrarQuestoes() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 937, 740);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblQ = new JLabel("Q:");
		lblQ.setToolTipText("Quantidade de Quest\u00F5es no Banco de Dados.");
		lblQ.setHorizontalAlignment(SwingConstants.RIGHT);
		lblQ.setBounds(851, 19, 63, 14);
		contentPane.add(lblQ);

		lblA = new JLabel("A:");
		lblA.setToolTipText("Quantidade de Alternativas contidas no Banco de Dados.");
		lblA.setHorizontalAlignment(SwingConstants.RIGHT);
		lblA.setBounds(851, 43, 63, 14);
		contentPane.add(lblA);

		JLabel lblInsertQuestesSistemasvox = new JLabel("X-Quest, Cadastro Quest\u00F5es.");
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
		scrollArea.setBounds(10, 337, 713, 354);

		btnSalvar = new JButton("Salvar Quest\u00E3o");
		btnSalvar.setToolTipText("Salve a nova quest\u00E3o no Banco de Dados.");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				salvar();
			}
		});
		btnSalvar.setBounds(735, 464, 159, 46);
		contentPane.add(btnSalvar);

		btnLimpar = new JButton("Limpar Campos");
		btnLimpar.setToolTipText("Limpe toda a \u00E1rea e recomece novamente.");
		btnLimpar.setBounds(735, 360, 159, 46);
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
		scrollA.setBounds(10, 150, 907, 165);

		bntMontar = new JButton("Montar Quest\u00E3o");
		bntMontar.setToolTipText("Click e veja a quest\u00E3o como ficaria criada.");
		bntMontar.setBounds(735, 412, 159, 46);
		bntMontar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				montarQuestao();
			}
		});
		contentPane.add(bntMontar);

		JButton btnListar = new JButton("Listar Tudo.");
		btnListar.setToolTipText("Liste todas as quest\u00F5es e suas alternativas contidas no Banco de Dados.");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listarQuestoes();
			}
		});
		btnListar.setBounds(735, 517, 159, 46);
		contentPane.add(btnListar);

		JButton btnExpor = new JButton("Exportar CSV");
		btnExpor.setToolTipText("Exporte todas as quest\u00F5es e suas alternativas para CSV.");
		btnExpor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vwExport export = new vwExport();
				export.setVisible(true);
			}
		});
		btnExpor.setBounds(735, 574, 159, 46);
		contentPane.add(btnExpor);

		btnSair = new JButton("Sair");
		btnSair.setToolTipText("Fechar a janela de Cadastro de Quest\u00F5es.");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSair.setBounds(735, 631, 159, 46);
		contentPane.add(btnSair);
		
		JLabel lblEliminar = new JLabel("Eliminar:");
		lblEliminar.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEliminar.setBounds(710, 316, 64, 14);
		contentPane.add(lblEliminar);
		
		rdP = new JRadioButton("Parenteses");
		buttonGroup.add(rdP);
		rdP.setSelected(true);
		rdP.setBounds(723, 336, 109, 23);
		contentPane.add(rdP);
		
		rbS = new JRadioButton("Espa\u00E7o");
		rbS.setBounds(834, 336, 109, 23);
		contentPane.add(rbS);
		buttonGroup.add(rbS);
		
		JLabel lblEnunciado = new JLabel("Enunciado:");
		lblEnunciado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEnunciado.setBounds(10, 43, 76, 14);
		contentPane.add(lblEnunciado);
		
		JLabel lblAlternativas = new JLabel("Alternativas:");
		lblAlternativas.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAlternativas.setBounds(10, 136, 76, 14);
		contentPane.add(lblAlternativas);
		atualizarLBL();
		bloquearBotao();
	}

	static void exportarCSV(String separador, String texto) {
		ArrayList<Questoes> questoes = new ArrayList<Questoes>();
		ArrayList<Alternativa> alternativas = new ArrayList<Alternativa>();

		for (int i = 1; i <= Integer.parseInt(Controladora.consultarTotalQ()); i++) {
			questoes.add(Controladora.consultarQuestao(String.valueOf(i)));
		}

		for (int i = 1; i <= Integer.parseInt(Controladora.consultarTotalA()); i++) {
			alternativas.add(Controladora.consultarAlternativa(String.valueOf(i)));
		}
		exportarQ(questoes, separador, texto);
		exportarA(alternativas, separador, texto);
		File arquivo = new File("Quest�es.csv");
		File arquivo2 = new File("Alternativas.csv");
		if (arquivo.exists() && arquivo2.exists()) {
			JOptionPane.showMessageDialog(null, "Exporta��o realizada com sucesso, verifique no caminho:\n" + arquivo.getAbsoluteFile() + "\n" + arquivo2.getAbsoluteFile());
			txtArea.setText("Exporta��o realizada com sucesso, verifique no caminho:\n" + arquivo.getAbsoluteFile() + "\n" + arquivo2.getAbsoluteFile());
		}else {
			JOptionPane.showMessageDialog(null, "Erro na exporta��o" + arquivo.getAbsoluteFile() + "\n" + arquivo2.getAbsoluteFile());
		}
	}

	private static void exportarQ(ArrayList<Questoes> questoes, String separador, String texto) {
		try {
			PrintWriter pw = new PrintWriter(new File("Quest�es.csv"));
			StringBuilder sb = new StringBuilder();
			String finalizador = "\r\n";

			sb.append("cod");
			sb.append(separador);
			sb.append("enunciado");
			sb.append(separador);
			sb.append("dificuldade");
			sb.append(separador);
			sb.append("referencia");
			sb.append(finalizador);

			for (int i = 0; i < questoes.size(); i++) {
				sb.append(questoes.get(i).getCod());
				sb.append(separador);
				sb.append(texto + questoes.get(i).getEnunciado() + texto);
				sb.append(separador);
				sb.append(texto + questoes.get(i).getDificuldade() + texto);
				sb.append(separador);
				sb.append(texto + questoes.get(i).getReferencia() + texto);
				sb.append(finalizador);
			}
			pw.write(sb.toString());
			pw.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	private static void exportarA(ArrayList<Alternativa> alternativas, String separador, String texto) {
		try {
			PrintWriter pw = new PrintWriter(new File("Alternativas.csv"));
			StringBuilder sb = new StringBuilder();
			String finalizador = "\r\n";

			sb.append("cod");
			sb.append(separador);
			sb.append("cod_q");
			sb.append(separador);
			sb.append("classificacao");
			sb.append(separador);
			sb.append("resposta");
			sb.append(separador);
			sb.append("justificativa");
			sb.append(finalizador);

			for (int i = 0; i < alternativas.size(); i++) {
				sb.append(alternativas.get(i).getCod());
				sb.append(separador);
				sb.append(alternativas.get(i).getCod_q());
				sb.append(separador);
				sb.append(alternativas.get(i).getClassificacao());
				sb.append(separador);
				sb.append(alternativas.get(i).getResposta());
				sb.append(separador);
				sb.append(texto + alternativas.get(i).getJustificativa() + texto);
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
				s += az(j) + ")" + " " + alternativas.get(j).getResposta() + "\n";
			}
			s+= "\nDificuldade:	" + questoes.get(i).getDificuldade();
			s+= ".\nRefer�ncia:	" + questoes.get(i).getReferencia();
			s += "\n--------------------------------------------------------------------------------------\n";
		}

		txtArea.setText(s.replace(": ", ":\n\n") + "FIM.\n");
	}

	private void bloquearBotao() {
		btnLimpar.setEnabled(false);
		btnSalvar.setEnabled(false);
	}

	protected void salvar() {
		vwSalvarAlter saveAlter = new vwSalvarAlter((tratarQ(txtQ.getText())), listaAlternativas, this);
		saveAlter.setVisible(true);		
	}
	public void salvarSucesso() {
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
			txtArea.setText("Poxa Vida, os Campos de Enunciado e Alternativas est�o vazios.!!!!");
		} else {
			tratarQuebrasLinhas();
			txtA.setText(txtA.getText() + "\n");
			String s = "";
			s += (String.valueOf(Integer.parseInt(Controladora.consultarTotalQ()) + 1)) + ") "
					+ txtQ.getText() + "\n";
			s += alternativasLimpa(txtA.getText());
			if (listaAlternativas.size() > 1) {
				txtArea.setText(s);
				liberarbotao();
			} else {
				txtArea.setText("Ops, verifique o campo das 'Alternativas', se a resposta for somente uma letra, formate as alternativas da forma correta:\n\nA) Z\nB) S\nC) M\nD) J");
			}
		}
	}

	private void tratarQuebrasLinhas() {
		while (txtQ.getText().indexOf("\n\n") != -1) {
			txtQ.setText(txtQ.getText().replaceAll("\n\n", "\n").trim());			
		}
		while (txtA.getText().indexOf("\n\n") != -1) {
			txtA.setText(txtA.getText().replaceAll("\n\n", "\n").trim());			
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
		int letra = 0;
		
		alternativas = textoAlternativa.split(Pattern.quote("\n"));
		
		if (alternativas.length <= alfabeto) {
			for (int i = 0; i < alternativas.length; i++) {
				if (alternativas[i].length() > 1) {
					s += ("\n" + az(letra) +") " + tratarA(alternativas[i]));
					listaAlternativas.add(tratarA(alternativas[i]));
					letra++;
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Caramba, s�o permitidas s� alternativas de a .. z");
		}
		return s;
	}

	public String az(int i) {
		char s = 'a';
		
		for (int j = 0; j < i; j++) {
			s++;
		}
		return "" +  Character.toUpperCase(s);
	}

	public String tratarA(String txt) {
		eliminar();		
		for (int i = 0; i < (alfabeto - 1) ; i++) {
			if (txt.length() > 2) {
				if (txt.trim().substring(0, 2).toUpperCase().equals((az(i) + eliminar))) {
					txt = txt.substring(2, txt.length());
				}
			}
		}
		return txt.trim();
	}

	private void eliminar() {
		if (rdP.isSelected()) {
			eliminar = ")";
		} else if (rbS.isSelected()){
			eliminar = " ";
		}else {
			JOptionPane.showMessageDialog(null, "Aconteceu algo de errado nos RadioButtons");
		}
	}

	private void atualizarLBL() {
		lblA.setText("A: " + Controladora.consultarTotalA() + ".");
		lblQ.setText("Q: " + Controladora.consultarTotalQ() + ".");
	}
}
