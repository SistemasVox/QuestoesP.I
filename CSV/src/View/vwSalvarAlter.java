package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Controladora;
import Model.Alternativa;
import Model.Questoes;

import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JTextArea;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class vwSalvarAlter extends JFrame {

	private JPanel contentPane;
	private JTextArea txtQ, txtQA;
	private int i = 0;
	
	private String Enunciado;
	private String[] ae = { "A) ", "B) ", "C) ", "D) ", "E) " };
	private ArrayList<String> listaAlternativas;
	private JComboBox comboBox;
	 vwHome vwHome;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vwSalvarAlter frame = new vwSalvarAlter("Versão de Teste", new ArrayList<String>(), null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public vwSalvarAlter(String Enunciado, ArrayList<String> listaAlternativas, vwHome vwHome) {
		
		this.Enunciado = Enunciado;
		this.listaAlternativas = listaAlternativas;
		this.vwHome = vwHome;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 758, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtQ = new JTextArea();
		txtQ.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		txtQ.setLineWrap(true);
		txtQ.setWrapStyleWord(true);
		txtQ.setToolTipText("Enunciado da Quest\u00E3o, mais a Alternativa atual que est\u00E1 salvando.");		
		txtQ.setBounds(59, 85, 605, 155);
		contentPane.add(txtQ);
		
		txtQA = new JTextArea();		
		txtQA.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		txtQA.setLineWrap(true);
		txtQA.setWrapStyleWord(true);
		txtQA.setToolTipText("Seja: claro e expec\u00EDfico, porque a alternativa referente a quest\u00E3o, est\u00E1 correta ou incorrenta.");
		txtQA.setBounds(69, 293, 595, 103);
		contentPane.add(txtQA);
		
		JLabel lblEnunciadoDaQuesto = new JLabel("Enunciado da Quest\u00E3o");
		lblEnunciadoDaQuesto.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblEnunciadoDaQuesto.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnunciadoDaQuesto.setBounds(10, 21, 722, 51);
		contentPane.add(lblEnunciadoDaQuesto);
		
		JLabel lblAlternativaAtual = new JLabel("Justificativa da Alternativa atual.");
		lblAlternativaAtual.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlternativaAtual.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblAlternativaAtual.setBounds(10, 236, 722, 51);
		contentPane.add(lblAlternativaAtual);
		
		comboBox = new JComboBox();
		comboBox.setToolTipText("Selecione, quanto distante essa alternativa est\u00E1 incorreta referente a alterativa correta, se for a correta, selecione '0'.");
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"}));
		comboBox.setBounds(492, 407, 73, 20);
		contentPane.add(comboBox);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox.getSelectedItem().toString().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Selecione o Distanciamento.");
				} else {
					salvar();
					i++;
					iniciar();
				}
				
				
			}
		});
		btnSalvar.setBounds(575, 407, 89, 23);
		contentPane.add(btnSalvar);
		

		
		JLabel lblDistanciamentoDaAlternativa = new JLabel("Distanciamento da alternativa correta:");
		lblDistanciamentoDaAlternativa.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDistanciamentoDaAlternativa.setBounds(211, 405, 271, 23);
		contentPane.add(lblDistanciamentoDaAlternativa);
		
		iniciar();
		
	}

	protected void salvar() {
		Controladora.savarA(new Alternativa(String.valueOf(Integer.parseInt(Controladora.consultarTotalA()) + 1), String.valueOf(Integer.parseInt(Controladora.consultarTotalQ() + 1)),
				comboBox.getSelectedItem().toString(), listaAlternativas.get(i), txtQA.getText().toString()));
		
	}
	private void iniciar() {
		comboBox.setSelectedIndex(0);
		txtQA.setText("");
		if (i == listaAlternativas.size()) {
			if (JOptionPane.showConfirmDialog(null, "Essa Questão Possui Referência?") == 0) {
				Controladora.savarQ(new Questoes((String.valueOf(Integer.parseInt(Controladora.consultarTotalQ()) + 1)),
						Enunciado, JOptionPane.showInputDialog("Qual seria?")));
			} else {
				Controladora.savarQ(new Questoes((String.valueOf(Integer.parseInt(Controladora.consultarTotalQ()) + 1)),
						Enunciado, "Referência Vazia"));
			}
			dispose();
			vwHome.salvarSucesso();
			
		}else {
			txtQ.setText(Enunciado + "\n" + ae[i] + listaAlternativas.get(i));	
		}
	}
}
