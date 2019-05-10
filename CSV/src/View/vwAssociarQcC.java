package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Controladora;
import Model.Area_Conhecimento;
import Model.Conteudo;
import Model.Disciplina;
import Model.Questoes;
import Tools.Alphabet;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class vwAssociarQcC extends JFrame {

	private JPanel contentPane;
	private JComboBox<String> cbxQ;
	private JComboBox cbxC;
	private JTextArea textArea;
	private JComboBox cbxA;
	private JComboBox cbxDisc;
	private ArrayList<Questoes> questoes = new ArrayList<Questoes>();
	private ArrayList<Conteudo> conteudos = new ArrayList<Conteudo>();
	private ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vwAssociarQcC frame = new vwAssociarQcC();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public vwAssociarQcC() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 754, 614);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setBounds(10, 252, 534, 317);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setToolTipText("Quest\u00E3o Montada.");
		textArea.setLineWrap(true);
		textArea.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		scrollPane.setViewportView(textArea);
		
		cbxQ = new JComboBox();
		cbxQ.addItemListener(new ItemListener() {
	        public void itemStateChanged(ItemEvent arg0) {
	        	try {
	        		cbxQ.setToolTipText(cbxQ.getSelectedItem().toString());
		        	atualizaArea();
				} catch (Exception e) {
				}
	        }
	    });
		cbxQ.setBounds(91, 218, 644, 32);
		JLabel lblXquestAssociarQuestes = new JLabel("X-Quest, Associar Quest\u00F5es com Conte\u00FAdo.");
		lblXquestAssociarQuestes.setHorizontalAlignment(SwingConstants.CENTER);
		lblXquestAssociarQuestes.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblXquestAssociarQuestes.setBounds(0, 11, 735, 57);
		contentPane.add(lblXquestAssociarQuestes);
		
		cbxA = new JComboBox();
		cbxA.addItemListener(new ItemListener() {
	        public void itemStateChanged(ItemEvent arg0) {
	        	atualizarDisciplinas();
	        }
	    });
		cbxA.setBounds(164, 93, 571, 32);
		contentPane.add(cbxA);		

		
		JLabel label = new JLabel("\u00C1rea do Conhecimento:");
		label.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		label.setBounds(10, 102, 159, 14);
		contentPane.add(label);
		
		cbxDisc = new JComboBox();
		cbxDisc.addItemListener(new ItemListener() {
	        public void itemStateChanged(ItemEvent arg0) {
	        	atualizarConteudo();
	        }
	    });
		cbxDisc.setBounds(91, 133, 644, 32);
		contentPane.add(cbxDisc);
		
		JLabel lblDisciplina = new JLabel("Disciplina:");
		lblDisciplina.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblDisciplina.setBounds(10, 141, 100, 14);
		contentPane.add(lblDisciplina);
		
		cbxC = new JComboBox();
		cbxC.setBounds(91, 176, 644, 31);
		contentPane.add(cbxC);
		
		JLabel label_1 = new JLabel("Conte\u00FAdo:");
		label_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		label_1.setBounds(10, 185, 100, 14);
		contentPane.add(label_1);
		contentPane.add(cbxQ);
		
		JLabel lblQuesto = new JLabel("Quest\u00E3o:");
		lblQuesto.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblQuesto.setBounds(10, 226, 100, 14);
		contentPane.add(lblQuesto);
		
		JButton btnSubirBarraDe = new JButton("Associar.");
		btnSubirBarraDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				associarQuestaoConteudo();
			}
		});
		btnSubirBarraDe.setToolTipText("Associar a quest\u00E3o, com o Conte\u00FAdo.");
		btnSubirBarraDe.setBounds(554, 252, 181, 46);
		contentPane.add(btnSubirBarraDe);
		
		JButton btnVerQuestesSem = new JButton("Quest\u00F5es Novas.");
		btnVerQuestesSem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				preencherQuestoesNovas();
			}
		});
		btnVerQuestesSem.setToolTipText("Quest\u00F5es Sem Associa\u00E7\u00E3o.");
		btnVerQuestesSem.setBounds(554, 304, 181, 46);
		contentPane.add(btnVerQuestesSem);
		
		JButton btnVerTodasQuestes = new JButton("Ver Todas Quest\u00F5es.");
		btnVerTodasQuestes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				carregarTodasQuestoes();
			}
		});
		btnVerTodasQuestes.setBounds(554, 415, 181, 46);
		contentPane.add(btnVerTodasQuestes);
		
		JButton btnCadastrarContedo = new JButton("Cadastrar Conte\u00FAdo.");
		btnCadastrarContedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new vwConteudo().setVisible(true);
			}
		});
		btnCadastrarContedo.setToolTipText("Cadastrar Novo Conte\u00FAdo");
		btnCadastrarContedo.setBounds(554, 472, 181, 46);
		contentPane.add(btnCadastrarContedo);
		
		JButton bntQE = new JButton("Quest\u00F5es Espec\u00EDficas");
		bntQE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				questoeEspecificas();
			}
		});
		bntQE.setBounds(554, 358, 181, 46);
		contentPane.add(bntQE);
		
		JButton button_5 = new JButton("Sair");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_5.setBounds(554, 523, 181, 46);
		contentPane.add(button_5);
		
		atualizarAreaConhecimento();	
		
	}
	
	protected void questoeEspecificas() {
		cbxQ.removeAllItems();
		questoes.clear();
		questoes = Controladora.getQuestoes(conteudos.get(cbxC.getSelectedIndex()).getNome());	
		for (int i = 0; i < questoes.size(); i++) {
			cbxQ.addItem(questoes.get(i).getCod() +") " + questoes.get(i).getEnunciado());
		}			
	}

	protected void associarQuestaoConteudo() {
		if (cbxQ.getItemCount() != 0) {
			if (JOptionPane.showConfirmDialog(null, "Tem certeza que deseja associar:\n" + cbxQ.getSelectedItem().toString() + "\nAo conteúdo:\n" + cbxC.getSelectedItem().toString()) == 0) {
				System.out.println(questoes.size());
				if (Controladora.consultarQuestaoConteudo(questoes.get(cbxQ.getSelectedIndex()).getCod(), conteudos.get(cbxC.getSelectedIndex()).getCod()) == 0) {
					Controladora.insertQuestaoConteudo(questoes.get(cbxQ.getSelectedIndex()).getCod(), conteudos.get(cbxC.getSelectedIndex()).getCod());
					JOptionPane.showMessageDialog(null, cbxQ.getSelectedItem().toString() + ", associado com sucesso.");
					textArea.setText("");
					preencherQuestoesNovas();
				} else {
					JOptionPane.showMessageDialog(null, "Questão: " + questoes.get(cbxQ.getSelectedIndex()).getEnunciado() + "\nJá pertence ao conteúdo: " +conteudos.get(cbxC.getSelectedIndex()).getNome());
				}
			}	
		}
	}

	protected void preencherQuestoesNovas() {
		cbxQ.removeAllItems();
		questoes.clear();
		questoes = Controladora.getQuestoesSemAssociacao();	
		for (int i = 0; i < questoes.size(); i++) {
			cbxQ.addItem(questoes.get(i).getCod() +") " + questoes.get(i).getEnunciado());
		}				
	}

	private void atualizarAreaConhecimento() {
		cbxA.removeAllItems();
		ArrayList<Area_Conhecimento> areas = Controladora.consultarAreas();
		for (int i = 0; i < areas.size(); i++) {
			cbxA.addItem((i +1 ) + ") "+ areas.get(i).getNome());
		}
		atualizarDisciplinas();
	}
	private void atualizarDisciplinas() {
		cbxDisc.removeAllItems();
		disciplinas.clear();
		disciplinas = new ArrayList<Disciplina>();		
		try {			
			disciplinas = Controladora.consultarDisciplinas(cbxA.getSelectedItem().toString().substring(3, cbxA.getSelectedItem().toString().length()));	
			for (int i = 0; i < disciplinas.size(); i++) {
				cbxDisc.addItem((i +1 ) + ") "+ disciplinas.get(i).getNome());
			}	
		} catch (Exception e) {
			System.out.println("Disciplina: " + e.getMessage());
		}
	}

	protected void atualizaArea() {
		String string = "";
		try {
			if (!cbxQ.getSelectedItem().toString().isEmpty()) {
				string += cbxQ.getSelectedItem().toString() +"\n\n";
				for (int i = 0; i <  Controladora.getAlternativas(String.valueOf(questoes.get(cbxQ.getSelectedIndex()).getCod())).size(); i++) {
					string += Alphabet.getLetra(i).toLowerCase() +") " +  Controladora.getAlternativas(questoes.get(cbxQ.getSelectedIndex()).getCod()).get(i).getResposta();
					string += "\n";
					
				}
				string += "\n\nDeseja associar a qual conteúdo?";
				textArea.setText(string);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	private void atualizarConteudo() {
		cbxC.removeAllItems();
		conteudos.clear();
		try {
			conteudos = Controladora.consultarConteudos(cbxDisc.getSelectedItem().toString().substring(3, cbxDisc.getSelectedItem().toString().length()));	
			for (int i = 0; i < conteudos.size(); i++) {
				cbxC.addItem((i +1 ) + ") "+ conteudos.get(i).getNome());
			}
		} catch (Exception e) {
			System.out.println("Conteúdo: " + e.getMessage());
		}
	}

	protected void carregarTodasQuestoes() {
		cbxQ.removeAllItems();
		questoes.clear();
		questoes = Controladora.getQuestoes("");	
		for (int i = 0; i < questoes.size(); i++) {
			cbxQ.addItem(questoes.get(i).getCod() +") " + questoes.get(i).getEnunciado());
		}
	}
}
