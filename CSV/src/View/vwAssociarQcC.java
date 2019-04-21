package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Controladora;
import Model.Questoes;
import Tools.Alphabet;

import javax.swing.JLabel;
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

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public vwAssociarQcC() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 754, 519);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblXquestAssociarQuestes = new JLabel("X-Quest, Associar Quest\u00F5es com Conte\u00FAdo.");
		lblXquestAssociarQuestes.setHorizontalAlignment(SwingConstants.CENTER);
		lblXquestAssociarQuestes.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblXquestAssociarQuestes.setBounds(0, 11, 735, 57);
		contentPane.add(lblXquestAssociarQuestes);
		
		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setBounds(10, 156, 534, 317);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setToolTipText("Quest\u00E3o Montada.");
		textArea.setLineWrap(true);
		textArea.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		scrollPane.setViewportView(textArea);
		
		JLabel lblQuesto = new JLabel("Quest\u00E3o:");
		lblQuesto.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblQuesto.setBounds(10, 87, 100, 14);
		contentPane.add(lblQuesto);
		
		cbxQ = new JComboBox();
		cbxQ.setBounds(91, 79, 644, 32);
		cbxQ.addItemListener(new ItemListener() {
	        public void itemStateChanged(ItemEvent arg0) {
	        	atualizaArea();
	        }
	    });
		contentPane.add(cbxQ);
		
		JLabel label_1 = new JLabel("Conte\u00FAdo:");
		label_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		label_1.setBounds(10, 131, 100, 14);
		contentPane.add(label_1);
		
		cbxC = new JComboBox();
		cbxC.setBounds(91, 122, 644, 31);
		contentPane.add(cbxC);
		
		JButton btnSubirBarraDe = new JButton("Associar");
		btnSubirBarraDe.setToolTipText("Associar a quest\u00E3o, com o Conte\u00FAdo.");
		btnSubirBarraDe.setBounds(554, 156, 181, 46);
		contentPane.add(btnSubirBarraDe);
		
		JButton btnVerQuestesSem = new JButton("Quest\u00F5es Sem Associa\u00E7\u00E3o.");
		btnVerQuestesSem.setToolTipText("Quest\u00F5es Sem Associa\u00E7\u00E3o.");
		btnVerQuestesSem.setBounds(554, 208, 181, 46);
		contentPane.add(btnVerQuestesSem);
		
		JButton btnVerTodasQuestes = new JButton("Ver Todas Quest\u00F5es.");
		btnVerTodasQuestes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				carregarTodasQuestoes();
			}
		});
		btnVerTodasQuestes.setBounds(554, 260, 181, 46);
		contentPane.add(btnVerTodasQuestes);
		
		JButton btnCadastrarContedo = new JButton("Cadastrar Conte\u00FAdo");
		btnCadastrarContedo.setToolTipText("Cadastrar Novo Conte\u00FAdo");
		btnCadastrarContedo.setBounds(554, 313, 181, 46);
		contentPane.add(btnCadastrarContedo);
		
		JButton button_4 = new JButton("Voltar Menu Principal.");
		button_4.setBounds(554, 370, 181, 46);
		contentPane.add(button_4);
		
		JButton button_5 = new JButton("Sair");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_5.setBounds(554, 427, 181, 46);
		contentPane.add(button_5);
	}

	protected void atualizaArea() {
		String string = "";
		try {
			if (!cbxQ.getSelectedItem().toString().isEmpty()) {
				string += cbxQ.getSelectedItem().toString() +"\n\n";
				for (int i = 0; i <  Controladora.getAlternativas(String.valueOf(cbxQ.getSelectedIndex() + 1)).size(); i++) {
					string += Alphabet.getLetra(i).toLowerCase() +") " +  Controladora.getAlternativas(String.valueOf(cbxQ.getSelectedIndex() + 1)).get(i).getResposta();
					string += "\n";
					
				}
				string += "\n\nPertence a qual conteúdo?";
				textArea.setText(string);
				atualizarConteudo();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	private void atualizarConteudo() {		
		cbxC.removeAllItems();
		ArrayList<String> conteudos = Controladora.getConteudos("");
		for (int i = 0; i < conteudos.size(); i++) {
			cbxC.addItem((i +1) + ") "+conteudos.get(i));
		}
	}

	protected void carregarTodasQuestoes() {
		cbxQ.removeAllItems();
		ArrayList<Questoes> questoes = Controladora.getQuestoes("");	
		for (int i = 0; i < questoes.size(); i++) {
			cbxQ.addItem(questoes.get(i).getCod() +") " + questoes.get(i).getEnunciado());
		}
	}
}
