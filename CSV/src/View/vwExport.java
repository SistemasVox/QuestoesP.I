package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class vwExport extends JFrame {

	private JPanel contentPane;
	public JComboBox comboBoxC;
	public JComboBox comboBoxT;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vwExport frame = new vwExport();
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
	public vwExport() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 487, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblExportQuestes = new JLabel("EXPORT Quest\u00F5es & Alternativas, SistemasVOX.");
		lblExportQuestes.setBounds(10, 11, 439, 22);
		lblExportQuestes.setHorizontalAlignment(SwingConstants.CENTER);
		lblExportQuestes.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		contentPane.add(lblExportQuestes);
		
		JLabel lblOpesDeCampo = new JLabel("Op\u00E7\u00F5es de Campo:");
		lblOpesDeCampo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblOpesDeCampo.setBounds(21, 83, 175, 38);
		contentPane.add(lblOpesDeCampo);
		
		JLabel lblDemimitadorDeCampo = new JLabel("Demimitador de Campo:");
		lblDemimitadorDeCampo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblDemimitadorDeCampo.setBounds(31, 132, 175, 38);
		contentPane.add(lblDemimitadorDeCampo);
		
		JLabel lblDemimitadorDeTexto = new JLabel("Demimitador de Texto:");
		lblDemimitadorDeTexto.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblDemimitadorDeTexto.setBounds(31, 181, 175, 38);
		contentPane.add(lblDemimitadorDeTexto);
		
		comboBoxC = new JComboBox();
		comboBoxC.setEditable(true);
		comboBoxC.setFont(new Font("Tahoma", Font.BOLD, 16));
		comboBoxC.setModel(new DefaultComboBoxModel(new String[] {";", ":", ","}));
		comboBoxC.setBounds(216, 143, 101, 20);
		contentPane.add(comboBoxC);
		
		comboBoxT = new JComboBox();
		comboBoxT.setModel(new DefaultComboBoxModel(new String[] {"\"", "'"}));
		comboBoxT.setBounds(216, 192, 101, 20);
		contentPane.add(comboBoxT);
		
		JTextArea txtAre = new JTextArea();
		txtAre.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 16));
		txtAre.setLineWrap(true);
		txtAre.setEditable(false);
		txtAre.setWrapStyleWord(true);
		txtAre.setText("Click no Bot\u00E3o Exemplicar, ap\u00F3s escolher as op\u00E7\u00F5es.");
		txtAre.setBounds(31, 230, 429, 110);
		contentPane.add(txtAre);
		
		JButton btnExportar = new JButton("Exportar");
		btnExportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				vwHome.exportarCSV(comboBoxC.getSelectedItem().toString(), comboBoxT.getSelectedItem().toString());
				dispose();
				
			}
		});
		btnExportar.setEnabled(false);
		btnExportar.setBounds(183, 351, 101, 38);
		contentPane.add(btnExportar);
		
		JButton bntExe = new JButton("Exemplificar");
		bntExe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("HÁ UMA DOCE LUZ NO SILÊNCIO");
				list.add("NÃO DIGAS ONDE ACABA O DIA");
				list.add("LEVO O MEU RUMO NA MINHA MÃO");
				list.add("UM DIA SE VÊ QUE AS HORAS TODAS PASSAM");
				String txt = "";
				for (int i = 0; i < list.size(); i++) {
					txt += (int) (Math.random() * 10) + comboBoxC.getSelectedItem().toString();
					txt += (int) (Math.random() * 10) + comboBoxC.getSelectedItem().toString();
					txt += (int) (Math.random() * 10) + comboBoxC.getSelectedItem().toString();
					txt += comboBoxT.getSelectedItem().toString() + list.get(i).toString() + comboBoxT.getSelectedItem().toString() +"\n";										
				}
				txtAre.setText(txt);
				btnExportar.setEnabled(true);
				
			}
		});
		bntExe.setBounds(327, 132, 122, 82);
		contentPane.add(bntExe);
	}
}
