package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Controladora;
import Model.Conteudo;
import Model.Disciplina;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;

public class vwConteudo extends JFrame {

	private JPanel contentPane;
	private JTextField txtConteudo;
	private JComboBox cbxC;
	private JComboBox cbxDisc;
	private ArrayList<Conteudo> conteudos = new ArrayList<Conteudo>();
	private ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();
	private JTextArea textArea;
	private JComboBox cbxSerie;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vwConteudo frame = new vwConteudo();
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
	public vwConteudo() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 717, 426);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblXquestCadastroContedo = new JLabel("X-Quest, Cadastro Conte\u00FAdo.");
		lblXquestCadastroContedo.setHorizontalAlignment(SwingConstants.CENTER);
		lblXquestCadastroContedo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblXquestCadastroContedo.setBounds(0, 11, 704, 57);
		contentPane.add(lblXquestCadastroContedo);
		
		JLabel lblDisciplinas = new JLabel("Disciplinas:");
		lblDisciplinas.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblDisciplinas.setBounds(0, 87, 100, 14);
		contentPane.add(lblDisciplinas);
		
		JLabel lblContedos = new JLabel("Conte\u00FAdos:");
		lblContedos.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblContedos.setBounds(0, 131, 100, 14);
		contentPane.add(lblContedos);
		
		cbxDisc = new JComboBox();
		cbxDisc.addItemListener(new ItemListener() {
	        public void itemStateChanged(ItemEvent arg0) {
	        	atualizarConteudo();
	        }
	    });
		cbxDisc.setBounds(81, 79, 610, 32);
		contentPane.add(cbxDisc);
		
		cbxC = new JComboBox();
		cbxC.setBounds(81, 122, 610, 31);
		contentPane.add(cbxC);
		
		JLabel lblNovoContedo = new JLabel("Nome do novo Conte\u00FAdo:");
		lblNovoContedo.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblNovoContedo.setBounds(0, 172, 156, 14);
		contentPane.add(lblNovoContedo);
		
		txtConteudo = new JTextField();
		txtConteudo.setFont(new Font("Times New Roman", Font.BOLD, 14));
		txtConteudo.setBounds(166, 163, 525, 34);
		contentPane.add(txtConteudo);
		txtConteudo.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar ");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvarNovoConteudo();
			}
		});
		btnSalvar.setBounds(602, 324, 89, 23);
		contentPane.add(btnSalvar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirConteudo();
			}
		});
		btnExcluir.setBounds(503, 324, 89, 23);
		contentPane.add(btnExcluir);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnSair.setBounds(602, 358, 89, 23);
		contentPane.add(btnSair);
		
		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setBounds(0, 221, 691, 92);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setToolTipText("Seja: claro e expec\u00EDfico, porque a alternativa referente a quest\u00E3o, est\u00E1 correta ou incorrenta.");
		textArea.setText("");
		textArea.setLineWrap(true);
		textArea.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		scrollPane.setViewportView(textArea);
		
		JLabel lblDescrioDoNovo = new JLabel("Descri\u00E7\u00E3o do novo Conte\u00FAdo:");
		lblDescrioDoNovo.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblDescrioDoNovo.setBounds(0, 208, 185, 14);
		contentPane.add(lblDescrioDoNovo);
		
		JLabel lblSrie = new JLabel("S\u00E9rie:");
		lblSrie.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblSrie.setBounds(610, 208, 47, 14);
		contentPane.add(lblSrie);
		
		cbxSerie = new JComboBox();
		cbxSerie.setModel(new DefaultComboBoxModel(new String[] {"", "1", "2", "3"}));
		cbxSerie.setBounds(644, 206, 47, 20);
		contentPane.add(cbxSerie);
		atualizarDisciplinas();
	}

	protected void excluirConteudo() {
		if (JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir o conteúdo:\n" + cbxC.getSelectedItem().toString() + ".\nDa disciplina:\n" + disciplinas.get(cbxDisc.getSelectedIndex()).getNome() +"?") == 0) {
			Controladora.excluirConteudo(conteudos.get(cbxC.getSelectedIndex()).getCod());
			JOptionPane.showMessageDialog(null, cbxC.getSelectedItem().toString() + ", deletado com sucesso.");
			atualizarConteudo();
		}		
	}

	protected void salvarNovoConteudo() {
		if (!txtConteudo.getText().isEmpty() && !textArea.getText().isEmpty() && !cbxSerie.getSelectedItem().toString().isEmpty()) {
			if (Controladora.consultarConteudoExiste(txtConteudo.getText()) == 0) {
				if (JOptionPane.showConfirmDialog(null, "Tem certeza que deseja adcionar o novo conteúdo:\n" + txtConteudo.getText() +".\nA Disciplina: "+ disciplinas.get(cbxDisc.getSelectedIndex()).getNome() +"?") == 0) {
					Controladora.insertConteudo(txtConteudo.getText(), textArea.getText(), disciplinas.get(cbxDisc.getSelectedIndex()).getCod(), cbxSerie.getSelectedItem().toString());
					atualizarConteudo();
					zerarTela();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Conteúdo: " + txtConteudo.getText()+"\nJá existe.");
			}	
		}else {
			JOptionPane.showMessageDialog(null, "Nome, Descrição ou Série estão em branco.");
		}
	}

	private void zerarTela() {
		textArea.setText("");
		txtConteudo.setText("");
		cbxSerie.setSelectedIndex(0);		
	}

	protected void atualizarConteudo() {
		cbxC.removeAllItems();
		conteudos.clear();
		try {
			conteudos  = Controladora.consultarConteudos(disciplinas.get(cbxDisc.getSelectedIndex()).getNome());	
			for (int i = 0; i < conteudos.size(); i++) {
				cbxC.addItem((i +1 ) + ") "+ conteudos.get(i).getNome());
			}
		} catch (Exception e) {
			System.out.println("Conteúdo: " + e.getMessage());
		}
		
	}
	private void atualizarDisciplinas() {
		cbxDisc.removeAllItems();
		disciplinas  = new ArrayList<Disciplina>();		
		try {			
			disciplinas = Controladora.consultarTodasDisciplinas();	
			for (int i = 0; i < disciplinas.size(); i++) {
				cbxDisc.addItem((i +1 ) + ") "+ disciplinas.get(i).getNome());
			}	
		} catch (Exception e) {
			System.out.println("Disciplina: " + e.getMessage());
		}	
	}
}
