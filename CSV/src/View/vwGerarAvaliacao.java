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
import Tools.Alphabet;
import Tools.ExportarPDF;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;

public class vwGerarAvaliacao extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblA, lblQ;
	private JButton bntMontar, btnTop, btnDetalhes;
	private static JTextArea txtArea;
	private JScrollPane scrollArea;
	private JButton btnSair;
	private final ButtonGroup letras = new ButtonGroup();
	private final ButtonGroup formatacao = new ButtonGroup();
	private JComboBox cbxA, cbxDisc, cbxConte;
	private JRadioButton rbMai;
	private JRadioButton rbMin;
	private JRadioButton rbPare;
	private JRadioButton rbPf;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vwGerarAvaliacao frame = new vwGerarAvaliacao();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public vwGerarAvaliacao() {
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

		JLabel lblInsertQuestesSistemasvox = new JLabel("X-Quest, Gerar Avalia��o.");
		lblInsertQuestesSistemasvox.setBounds(76, -5, 704, 57);
		lblInsertQuestesSistemasvox.setHorizontalAlignment(SwingConstants.CENTER);
		lblInsertQuestesSistemasvox.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		contentPane.add(lblInsertQuestesSistemasvox);

		txtArea = new JTextArea();
		txtArea.setLineWrap(true);
		txtArea.setWrapStyleWord(true);
		txtArea.setToolTipText("\u00C1rea que as quest\u00F5es ser\u00E3o exibidas.");
		txtArea.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));

		contentPane.add(this.scrollArea = new JScrollPane(txtArea));
		scrollArea.setBounds(10, 144, 713, 317);

		btnDetalhes = new JButton("Ver Quest\u00F5es Detalhada.");
		btnDetalhes.setToolTipText("Ver quest\u00F5es espec\u00EDficas, escolhidas no campos de conte\u00FAdo, de forma detalhada.");
		btnDetalhes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				verQuestoesDetalhes();				
			}
		});
		btnDetalhes.setBounds(739, 248, 181, 46);
		contentPane.add(btnDetalhes);

		btnTop = new JButton("Subir Barra de Rolagem");
		btnTop.setToolTipText("Subir barra de rolagem das Quest\u00F5es.");
		btnTop.setBounds(739, 144, 181, 46);
		btnTop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpar();
			}
		});
		contentPane.add(btnTop);

		bntMontar = new JButton("Ver Quest\u00F5es.");
		bntMontar.setToolTipText("Ver quest\u00F5es espec\u00EDficas, escolhidas no campos de conte\u00FAdo.");
		bntMontar.setBounds(739, 196, 181, 46);
		bntMontar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verQuestoes();
			}
		});
		contentPane.add(bntMontar);

		JButton btnPDF = new JButton("Exportar para PDF.");
		btnPDF.setToolTipText("Exportar avalia\u00E7\u00E3o para PDF.");
		btnPDF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				exportarPDF();
			}
		});
		btnPDF.setBounds(739, 301, 181, 46);
		contentPane.add(btnPDF);

		JButton btnExpor = new JButton("Voltar Menu Principal.");
		btnExpor.setToolTipText("Voltar ao Menu Principal.");
		btnExpor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();				
			}
		});
		btnExpor.setBounds(739, 358, 181, 46);
		contentPane.add(btnExpor);

		btnSair = new JButton("Sair");
		btnSair.setToolTipText("Fechar janela de Gerar Avalia\u00E7\u00E3o.");
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
		cbxA.setToolTipText("Selecione a \u00E1rea do conhecimento desejada.");
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
		cbxDisc.setToolTipText("Selecione a Disciplina desejada.");
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
		cbxConte.setToolTipText("Selecione o conte\u00FAdo desejado.");
		cbxConte.addItemListener(new ItemListener() {
	        public void itemStateChanged(ItemEvent arg0) {
	        	limpaarea();
	        }
	    });
		cbxConte.setBounds(164, 117, 446, 20);
		contentPane.add(cbxConte);
		
		JLabel lblAlternativas = new JLabel("Alternativas:");
		lblAlternativas.setToolTipText("Escolha o tipo de forma\u00E7\u00E3o das alternativas desejada.");
		lblAlternativas.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlternativas.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAlternativas.setBounds(620, 91, 100, 14);
		contentPane.add(lblAlternativas);
		
		rbMai = new JRadioButton("Mai\u00FAsculas");
		rbMai.setToolTipText("Escolha o tipo de forma\u00E7\u00E3o das alternativas desejada.");
		letras.add(rbMai);
		rbMai.setSelected(true);
		rbMai.setBounds(719, 88, 100, 23);
		contentPane.add(rbMai);
		
		rbMin = new JRadioButton("Min\u00FAsculas");
		rbMin.setToolTipText("Escolha o tipo de forma\u00E7\u00E3o das alternativas desejada.");
		letras.add(rbMin);
		rbMin.setBounds(821, 88, 100, 23);
		contentPane.add(rbMin);
		
		rbPare = new JRadioButton("Parenteses");
		rbPare.setToolTipText("Escolha o tipo de forma\u00E7\u00E3o das alternativas desejada.");
		formatacao.add(rbPare);
		rbPare.setSelected(true);
		rbPare.setBounds(718, 114, 100, 23);
		contentPane.add(rbPare);
		
		rbPf = new JRadioButton("Ponto final");
		rbPf.setToolTipText("Escolha o tipo de forma\u00E7\u00E3o das alternativas desejada.");
		formatacao.add(rbPf);
		rbPf.setBounds(820, 114, 100, 23);
		contentPane.add(rbPf);
		bloquearBotao();
		atualizarAreaConhecimento();
		atualizarLBL();
	}

	protected void exportarPDF() {
		ExportarPDF ex = new ExportarPDF(cbxConte.getSelectedItem().toString(), formatacaoA(), rbMai.isSelected());
		ex.gerarPDF();
		
	}
	@SuppressWarnings("unchecked")
	private void atualizarConteudo() {
		cbxConte.removeAllItems();
		limpaarea();
		ArrayList<Conteudo> conteudos = new ArrayList<Conteudo>();
		try {
			conteudos = Controladora.consultarConteudos(cbxDisc.getSelectedItem().toString());	
			for (int i = 0; i < conteudos.size(); i++) {
				cbxConte.addItem(conteudos.get(i).getNome());
			}
		} catch (Exception e) {
			System.out.println("Conte�do: " + e.getMessage());
		}
	}
	private void atualizarDisciplinas() {
		limpaarea();
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
			limpaarea();
		}
		
		
	}
	private void limpaarea() {
		txtArea.setText("");
		
	}
	protected void verQuestoesDetalhes() {
		ArrayList<Questoes> questoes = new ArrayList<Questoes>();
		String s = "";

		questoes = Controladora.consultarQuestoesC(cbxConte.getSelectedItem().toString());
		
		for (int i = 0; i < questoes.size(); i++) {
			s += (i + 1) + ") " + questoes.get(i).getEnunciado() + "\n\n";

			ArrayList<Alternativa> alternativas = Controladora.getAlternativas(questoes.get(i).getCod());
			
			for (int j = 0; j < alternativas.size(); j++) {
				if (rbMin.isSelected()) {
					s += Alphabet.getLetra(j).toLowerCase() + formatacaoA() + alternativas.get(j).getResposta() + "\n";
				} else {
					s += Alphabet.getLetra(j) + formatacaoA() + alternativas.get(j).getResposta() + "\n";
				}
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
		verQuestoes();
	}

	private void verQuestoes() {
		ArrayList<Questoes> questoes = new ArrayList<Questoes>();
		String s = "";
		System.out.println(Controladora.consultarTotalQ(cbxConte.getSelectedItem().toString()));

		questoes = Controladora.consultarQuestoesC(cbxConte.getSelectedItem().toString());
		
		for (int i = 0; i < questoes.size(); i++) {
			s += (i + 1) + ") " + questoes.get(i).getEnunciado() + "\n\n";

			ArrayList<Alternativa> alternativas = Controladora.getAlternativas(questoes.get(i).getCod());
			
			for (int j = 0; j < alternativas.size(); j++) {
				if (rbMin.isSelected()) {
					s += Alphabet.getLetra(j).toLowerCase() + formatacaoA() + alternativas.get(j).getResposta() + "\n";
				} else {
					s += Alphabet.getLetra(j) + formatacaoA() + alternativas.get(j).getResposta() + "\n";
				}
			}
			s+= "\nDificuldade:	" + questoes.get(i).getDificuldade();
			s+= ".\nRefer�ncia:	" + questoes.get(i).getReferencia();
			s += "\n--------------------------------------------------------------------------------------\n";
		}

		txtArea.setText(s.replace(": ", ":\n\n") + "FIM.\n");
	}


	private String formatacaoA() {
		if (rbPare.isSelected()) {
			return ") ";
		} else if(rbPf.isSelected()){
			return ". ";
		}else {
			return ") ";
		}
	}
	private void bloquearBotao() {
	}
	
	protected void limpar() {
		scrollArea.getVerticalScrollBar().setValue(0);
	}

	private void atualizarLBL() {
		lblA.setText("A: " + Controladora.consultarTotalA() + ".");
		lblQ.setText("Q: " + Controladora.consultarTotalQ() + ".");
	}
}

