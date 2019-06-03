package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Controladora;
import Model.Alternativa;
import Model.Questoes;

import javax.swing.JScrollPane;
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

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea txtQ, txtQA;
	private int i = 0;
	
	private String Enunciado;
	private ArrayList<String> listaAlternativas;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox, cbxDifi;
	 vwCadastrarQuestoes vwHome;
	private JScrollPane scrollArea, scrollQA;
	
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
	public vwSalvarAlter(String Enunciado, ArrayList<String> listaAlternativas, vwCadastrarQuestoes vwHome) {
		
		this.Enunciado = Enunciado;
		this.listaAlternativas = listaAlternativas;
		this.vwHome = vwHome;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 758, 537);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtQ = new JTextArea();
		txtQ.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		txtQ.setLineWrap(true);
		txtQ.setWrapStyleWord(true);
		txtQ.setToolTipText("Enunciado da Quest\u00E3o, mais a Alternativa atual que est\u00E1 salvando.");		
		
		contentPane.add(this.scrollArea = new JScrollPane(txtQ));
		scrollArea.setBounds(59, 85, 605, 155);
		
		txtQA = new JTextArea();		
		txtQA.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		txtQA.setLineWrap(true);
		txtQA.setWrapStyleWord(true);
		txtQA.setToolTipText("Seja: claro e expec\u00EDfico, porque a alternativa referente a quest\u00E3o, est\u00E1 correta ou incorrenta.");
		
		contentPane.add(this.scrollQA = new JScrollPane(txtQA));
		scrollQA.setBounds(69, 351, 595, 103);
		
		JLabel lblEnunciadoDaQuesto = new JLabel("Enunciado da Quest\u00E3o");
		lblEnunciadoDaQuesto.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblEnunciadoDaQuesto.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnunciadoDaQuesto.setBounds(10, 21, 722, 51);
		contentPane.add(lblEnunciadoDaQuesto);
		
		JLabel lblAlternativaAtual = new JLabel("Justificativa da Alternativa atual.");
		lblAlternativaAtual.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlternativaAtual.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblAlternativaAtual.setBounds(10, 294, 722, 51);
		contentPane.add(lblAlternativaAtual);
		
		comboBox = new JComboBox();
		comboBox.setToolTipText("Selecione, quanto distante essa alternativa est\u00E1 incorreta referente a alterativa correta, se for a correta, selecione '0'.");
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"}));
		comboBox.setBounds(492, 465, 73, 20);
		contentPane.add(comboBox);
		
		cbxDifi = new JComboBox();
		cbxDifi.setToolTipText("Selecione, nível de dificuldade desta Questão");
		cbxDifi.setModel(new DefaultComboBoxModel(new String[] {"", "F\u00E1cil", "M\u00E9dio", "Dif\u00EDcil"}));
		cbxDifi.setSelectedIndex(0);
		cbxDifi.setFont(new Font("Tahoma", Font.BOLD, 14));
		cbxDifi.setBounds(502, 248, 162, 20);
		contentPane.add(cbxDifi);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox.getSelectedItem().toString().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Selecione o Distanciamento.");
				} else if(cbxDifi.getSelectedItem().toString().isEmpty()){
					JOptionPane.showMessageDialog(null, "Selecione o nível de dificuldade da Questão.");
				}else {
					cbxDifi.setEnabled(false);
					salvar();
					i++;
					iniciar();
				}
				
				
			}
		});
		btnSalvar.setBounds(575, 465, 89, 23);
		contentPane.add(btnSalvar);
		

		
		JLabel lblDistanciamentoDaAlternativa = new JLabel("Distanciamento da alternativa correta:");
		lblDistanciamentoDaAlternativa.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDistanciamentoDaAlternativa.setBounds(211, 463, 271, 23);
		contentPane.add(lblDistanciamentoDaAlternativa);
		
		JLabel lblQ = new JLabel("N\u00EDvel de Dificuldade:");
		lblQ.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblQ.setBounds(346, 247, 146, 23);
		contentPane.add(lblQ);		
		iniciar();
		
	}

	protected void salvar() {
		Controladora.savarA(new Alternativa(String.valueOf(Integer.parseInt(Controladora.consultarTotalA()) + 1), String.valueOf(Integer.parseInt(Controladora.consultarTotalQ()) + 1),
				comboBox.getSelectedItem().toString(), listaAlternativas.get(i).replaceAll("`", "'").replaceAll("'", "''"), txtQA.getText().toString()));
		
	}
	private void iniciar() {
		comboBox.setSelectedIndex(0);
		txtQA.setText("");
		if (i == listaAlternativas.size()) {
			if (JOptionPane.showConfirmDialog(null, "Essa Questão Possui Referência?") == 0) {
				Controladora.savarQ(new Questoes((String.valueOf(Integer.parseInt(Controladora.consultarTotalQ()) + 1)),
						Enunciado, cbxDifi.getSelectedItem().toString(), JOptionPane.showInputDialog("Qual seria?")));
			} else {
				Controladora.savarQ(new Questoes((String.valueOf(Integer.parseInt(Controladora.consultarTotalQ()) + 1)),
						Enunciado, cbxDifi.getSelectedItem().toString(), "Referência Vazia."));
				
			}
			dispose();
			vwHome.salvarSucesso();
			
		}else {
			txtQ.setText(Enunciado + "\n\n" + vwHome.az(i) +") " +listaAlternativas.get(i));	
		}
	}
}
