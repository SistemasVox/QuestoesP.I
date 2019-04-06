package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Controller.Controladora;
import Model.Alternativa;
import Model.Area_Conhecimento;
import Model.Conteudo;
import Model.Disciplina;
import Model.Questoes;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

public class vwMontaQuestao extends JFrame {

	private JPanel contentPane;
	private JLabel lblA, lblQ;
	private JButton bntMontar, btnLimpar, btnDetalhes;
	private static JTextArea txtArea;
	private JScrollPane scrollQ, scrollA, scrollArea;
	private String eliminar;
	private ArrayList<String> listaAlternativas = new ArrayList<String>();
	private JButton btnSair;
	private static final int alfabeto = 26;
	private final ButtonGroup letras = new ButtonGroup();
	private final ButtonGroup formatacao = new ButtonGroup();
	private JComboBox cbxA;
	private JComboBox cbxDisc;
	private JComboBox cbxConte;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vwMontaQuestao frame = new vwMontaQuestao();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public vwMontaQuestao() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 937, 505);
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

		JLabel lblInsertQuestesSistemasvox = new JLabel("X-Quest, Monta Quest\u00F5es.");
		lblInsertQuestesSistemasvox.setBounds(76, -5, 704, 57);
		lblInsertQuestesSistemasvox.setHorizontalAlignment(SwingConstants.CENTER);
		lblInsertQuestesSistemasvox.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		contentPane.add(lblInsertQuestesSistemasvox);

		txtArea = new JTextArea();
		txtArea.setLineWrap(true);
		txtArea.setWrapStyleWord(true);
		txtArea.setToolTipText("Quest\u00E3o Montada.");
		txtArea.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));

		contentPane.add(this.scrollArea = new JScrollPane(txtArea));
		scrollArea.setBounds(10, 144, 713, 317);

		btnDetalhes = new JButton("Ver Quest\u00F5es Detalhada.");
		btnDetalhes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listarDetalhes();				
			}
		});
		btnDetalhes.setBounds(739, 248, 181, 46);
		contentPane.add(btnDetalhes);

		btnLimpar = new JButton("Limpar Campos.");
		btnLimpar.setBounds(739, 144, 181, 46);
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpar();
			}
		});
		contentPane.add(btnLimpar);

		bntMontar = new JButton("Ver Quest\u00F5es.");
		bntMontar.setBounds(739, 196, 181, 46);
		bntMontar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listartudo();
			}
		});
		contentPane.add(bntMontar);

		JButton btnListar = new JButton("Escolher Aleat\u00F3rio.");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listarQuestoes();
			}
		});
		btnListar.setBounds(739, 301, 181, 46);
		contentPane.add(btnListar);

		JButton btnExpor = new JButton("Voltar Menu Principal.");
		btnExpor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();				
			}
		});
		btnExpor.setBounds(739, 358, 181, 46);
		contentPane.add(btnExpor);

		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSair.setBounds(739, 415, 181, 46);
		contentPane.add(btnSair);
		
		JLabel lvlAreConhe = new JLabel("\u00C1rea do Conhecimento:");
		lvlAreConhe.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lvlAreConhe.setBounds(16, 65, 159, 14);
		contentPane.add(lvlAreConhe);
		
		cbxA = new JComboBox();
		cbxA.addItemListener(new ItemListener() {
	        public void itemStateChanged(ItemEvent arg0) {
	        	atualizarDisciplinas();
	        }
	    });
		cbxA.setBounds(164, 63, 446, 20);
		contentPane.add(cbxA);
		
		JLabel lblDisc = new JLabel("Disciplina:");
		lblDisc.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblDisc.setBounds(16, 92, 100, 14);
		contentPane.add(lblDisc);
		
		cbxDisc = new JComboBox();
		cbxDisc.addItemListener(new ItemListener() {
	        public void itemStateChanged(ItemEvent arg0) {
	        	atualizarConteudo();
	        }
	    });
		cbxDisc.setBounds(164, 90, 446, 20);
		contentPane.add(cbxDisc);
		
		JLabel lblConteudo = new JLabel("Conte\u00FAdo:");
		lblConteudo.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblConteudo.setBounds(16, 119, 100, 14);
		contentPane.add(lblConteudo);
		
		cbxConte = new JComboBox();
		cbxConte.setBounds(164, 117, 446, 20);
		contentPane.add(cbxConte);
		
		JLabel lblAlternativas = new JLabel("Alternativas:");
		lblAlternativas.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlternativas.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAlternativas.setBounds(620, 91, 100, 14);
		contentPane.add(lblAlternativas);
		
		JRadioButton rdbtnMaisculas = new JRadioButton("Mai\u00FAsculas");
		letras.add(rdbtnMaisculas);
		rdbtnMaisculas.setSelected(true);
		rdbtnMaisculas.setBounds(719, 88, 100, 23);
		contentPane.add(rdbtnMaisculas);
		
		JRadioButton rdbtnMinsculas = new JRadioButton("Min\u00FAsculas");
		letras.add(rdbtnMinsculas);
		rdbtnMinsculas.setBounds(821, 88, 100, 23);
		contentPane.add(rdbtnMinsculas);
		
		JRadioButton rdbtnParenteses = new JRadioButton("Parenteses");
		formatacao.add(rdbtnParenteses);
		rdbtnParenteses.setSelected(true);
		rdbtnParenteses.setBounds(718, 114, 100, 23);
		contentPane.add(rdbtnParenteses);
		
		JRadioButton rdbtnProntoFinal = new JRadioButton("Ponto final");
		formatacao.add(rdbtnProntoFinal);
		rdbtnProntoFinal.setBounds(820, 114, 100, 23);
		contentPane.add(rdbtnProntoFinal);
		bloquearBotao();
		atualizarAreaConhecimento();
		atualizarLBL();
	}

	private void atualizarConteudo() {
		cbxConte.removeAllItems();
		ArrayList<String> conteudos = new ArrayList<String>();
		try {
			conteudos = Controladora.consultarConteudos(cbxDisc.getSelectedItem().toString());	
			for (int i = 0; i < conteudos.size(); i++) {
				cbxConte.addItem(conteudos.get(i));
			}
		} catch (Exception e) {
			System.out.println("Conte�do: " + e.getMessage());
		}
	}
	private void atualizarDisciplinas() {
		cbxDisc.removeAllItems();
		ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();
		try {
			disciplinas = Controladora.consultarDisciplinas(cbxA.getSelectedItem().toString());	
			for (int i = 0; i < disciplinas.size(); i++) {
				cbxDisc.addItem(disciplinas.get(i).getNome());
			}	
		} catch (Exception e) {
			System.out.println("Disciplina: " + e.getMessage());
		}	
	}
	private void atualizarAreaConhecimento() {
		cbxA.removeAllItems();
		ArrayList<Area_Conhecimento> areas = Controladora.consultarAreas();
		System.out.println(areas.size());
		for (int i = 0; i < areas.size(); i++) {
			cbxA.addItem(areas.get(i).getNome());
		}
		
		
	}
	protected void listarDetalhes() {
		ArrayList<Questoes> questoes = new ArrayList<Questoes>();
		String s = "";

		for (int i = 1; i <= Integer.parseInt(Controladora.consultarTotalQ(cbxConte.getSelectedItem().toString())); i++) {
			questoes.add(Controladora.consultarQuestao(String.valueOf(i)));
		}

		for (int i = 0; i < questoes.size(); i++) {
			s += questoes.get(i).getCod() + ") " + questoes.get(i).getEnunciado() + "\n\n";

			ArrayList<Alternativa> alternativas = Controladora.getAlternativas(questoes.get(i).getCod());
			
			for (int j = 0; j < alternativas.size(); j++) {
				s += az(j) + ")" + " " + alternativas.get(j).getResposta() + "\n";
				s += "Classifica��o:	" + alternativas.get(j).getClassificacao() + ". " + acerto(alternativas.get(j).getClassificacao()) + "\n";
				s += "Justificativa:	" + alternativas.get(j).getJustificativa() + "\n\n";
			}
			s+= "\nDificuldade:	" + questoes.get(i).getDificuldade();
			s+= ".\nRefer�ncia:	" + questoes.get(i).getReferencia();
			s += "\n--------------------------------------------------------------------------------------\n";
		}

		txtArea.setText(s.replace(": ", ":\n\n") + "FIM.\n");
	}

	private String acerto(String classifi) {
		if (Integer.parseInt(classifi) == 0) {
			return "Correto.";
		} else {
			return "Incorreto.";
		}
	}
	protected void listarQuestoes() {
		listartudo();
	}

	private void listartudo() {
		ArrayList<Questoes> questoes = new ArrayList<Questoes>();
		String s = "";
		System.out.println(Controladora.consultarTotalQ(cbxConte.getSelectedItem().toString()));

		questoes = Controladora.consultarQuestoesC(cbxConte.getSelectedItem().toString());
		
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
	}
	
	protected void limpar() {
		txtArea.setText("");
		bloquearBotao();
		atualizarLBL();
	}

	private void liberarbotao() {
		btnLimpar.setEnabled(true);
		btnDetalhes.setEnabled(true);

	}


	public static String az(int i) {
		char s = 'a';
		
		for (int j = 0; j < i; j++) {
			s++;
		}
		return "" +  Character.toUpperCase(s);
	}

	private void atualizarLBL() {
		lblA.setText("A: " + Controladora.consultarTotalA() + ".");
		lblQ.setText("Q: " + Controladora.consultarTotalQ() + ".");
	}
}
