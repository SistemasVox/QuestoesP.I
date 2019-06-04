/*
 * Classe: vwAbout do tipo: View = Janela.
 * Função: Apresentar a janela de informações para o Usuário.
 * Última atualização: v2.1 05/05/2018 15:00
 */
package View;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class vwAbout extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    static vwAbout frame;
    //Image img = new ImageIcon(this.getClass().getResource("/AB1.png")).getImage(); 

    public vwAbout() {
    	setIconImage(Toolkit.getDefaultToolkit().getImage("pic/1ic.png"));
        setTitle("Sobre");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(300, 90, 710, 447);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblSair = new JLabel("");
        lblSair.setToolTipText("Sair");
        lblSair.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                dispose();
            }
        });
        lblSair.setBounds(65, 11, 512, 107);
        contentPane.add(lblSair);
        
        JLabel lblFundo = new JLabel("");
        lblFundo.setBackground(UIManager.getColor("Button.background"));
        lblFundo.setBounds(0, 0, 704, 422);
        lblFundo.setIcon(new ImageIcon("pic/fundo.png"));
        contentPane.add(lblFundo);
        JTextArea txtpnSoftwareAgendaTelefnica = new JTextArea();
        txtpnSoftwareAgendaTelefnica.setWrapStyleWord(true);
        txtpnSoftwareAgendaTelefnica.setLineWrap(true);
        txtpnSoftwareAgendaTelefnica.setBackground(UIManager.getColor("Button.background"));
        txtpnSoftwareAgendaTelefnica.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
        txtpnSoftwareAgendaTelefnica.setEditable(false);
        txtpnSoftwareAgendaTelefnica.setText("Software X-QUEST - SISTEMA DE CADASTRO DE QUESTÕES.\r\nCódigo desenvolvido por Marcelo Vieira,  7º Período.\r\n\r\nLink source:\r\nhttps://github.com/SistemasVox/QuestoesP.I\r\n\r\nEsse projeto foi possível graças ao projeto de código aberto AgendaTelefonicaVOX e outros softwares de código aberto.\r\n\r\n\r\nCopyright © 2013-2019 Sistemas VOX. Todos os direitos reservados.");
        txtpnSoftwareAgendaTelefnica.setBounds(31, 102, 546, 309);
        contentPane.add(txtpnSoftwareAgendaTelefnica);
        setTitle("Sobre");
    }
}
