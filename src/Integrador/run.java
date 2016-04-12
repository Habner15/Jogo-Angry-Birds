package Integrador;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class run extends JFrame {
	/****/

	private static final long serialVersionUID = 1L;
	static JFrame janela;
	static ImageIcon fundo = new ImageIcon("run\\fundo.png");
	static ImageIcon teste = new ImageIcon("run\\teste.png");
	static ImageIcon slingshot = new ImageIcon("run\\Slingshot.png");
	static ImageIcon passaro = new ImageIcon("run\\red_birds.png");
	static ImageIcon slingshot2 = new ImageIcon("run\\Slingshot2.png");

	static JLabel cena;
	static JLabel red;
	static JLabel slingshotFora;
	static JLabel slingshotDentro;
	static JLabel muro;
	static JLabel rota;
	static JLabel msg;
	static JLabel titulo;
	static JPanel painel;
	static JProgressBar barra;
	static JMenuBar construirMenuBarra;
	static int forca;
	static boolean soltar = false;

	public static void main(String[] args) {

		CriaJanela();
		MostraJanela();
		TelaTrava();
		BarraMovimento();

		if (soltar == true) {
			FuncaoMovimenta();
		}
	}

	// Menu Barra ainda não terminado
	private JMenuBar ConstruirMenuBarra() {
		JMenuBar menuBarra = new JMenuBar();
		menuBarra.setBackground(Color.WHITE);
		menuBarra.setBorder(new LineBorder(Color.BLUE));
		JMenu menu = new JMenu("Menu");
		JMenuItem sobre = new JMenuItem("Sobre");
		sobre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane
						.showMessageDialog(
								null,
								"Desenvolvido por Gilsimar Habner Monteiro \n\nVersão 1.0 - 2016",
								"Informações", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		JMenuItem voltar = new JMenuItem("Voltar");
		voltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		JMenuItem sair = new JMenuItem("Sair");
		sair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});

		menu.add(sobre);
		menu.add(new JSeparator());
		menu.add(voltar);
		menu.add(new JSeparator());
		menuBarra.add(menu);
		menu.add(sair);
		menuBarra.add(menu);
		setJMenuBar(menuBarra);

		return menuBarra;
	}

	static void CriaJanela() {
		janela = new JFrame("Angry Birds");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	static void MostraJanela() {
		IniciaComponentes();
		janela.getContentPane().setBackground(Color.BLUE);
		cena.add(barra);
		cena.add(msg);
		cena.add(titulo);
		janela.add(cena);
		cena.add(slingshotFora);
		cena.add(red);
		cena.add(slingshotDentro);
		cena.add(muro);
		janela.setVisible(true);
	}

	static void IniciaComponentes() {
		cena = new JLabel(fundo);
		cena.setLayout(null);
		slingshotFora = new JLabel(slingshot);
		slingshotFora.setBounds(52, 101, 85, 150);
		red = new JLabel(passaro);
		red.setBounds(45, 125, 48, 45);
		slingshotDentro = new JLabel(slingshot2);
		slingshotDentro.setBounds(50, 100, 85, 150);
		muro = new JLabel(teste);
		muro.setBounds(35, 245, 160, 181);
		titulo = new JLabel("PRESSINE ENTER PARA LANÇAR");
		titulo.setBounds(10, 20, 250, 30);
		titulo.setFont(new Font("Comic Sans", Font.BOLD + Font.ITALIC, 12));
		titulo.setVisible(true);
		msg = new JLabel(
				"------------------------------------------------------------------");
		msg.setBounds(10, 30, 250, 30);
		msg.setFont(new Font("Comic Sans", Font.BOLD + Font.ITALIC, 10));
		msg.setVisible(true);

		// Definição da Barra de Força
		barra = new JProgressBar();
		barra.setMinimum(0);
		barra.setMaximum(100);
		barra.setLocation(10, 10);
		barra.setSize(200, 15);
		barra.setStringPainted(true);
		barra.setBackground(Color.ORANGE);
		barra.setForeground(Color.RED);
		barra.setIndeterminate(false);
		barra.setVisible(true);

	}

	static void BarraMovimento() {
		for (int i = 0; i <= 100; i++) {
			barra.setValue(i);
			try {
				Thread.sleep(20);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			ChamarAcoes();
			if (soltar) {
				break;
			}
			if (i == 100) {
				for (i = 100; i >= 0; i--) {
					barra.setValue(i);
					try {
						Thread.sleep(20);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					ChamarAcoes();
					if (soltar) {
						break;
					}
				}
			}
		}
	}

	static void FuncaoMovimenta() {

		int x1 = 1;
		double x = 0;
		double angulo = 30 * (Math.PI / 180); // converte para radianos
		double t = 1;
		double cos = (Math.cos(angulo));
		double v = forca;
		int y1 = 340;
		double y = 0;
		double sen = (Math.sin(angulo));
		double gravidade = 9.8;

		while (y >= 0 && x <= 800) {
			red.setLocation((int) x, (((int) y - 500) * (-1)));
			x = (x1 + (v * cos * t));
			y = (y1 + (v * sen * t) - ((gravidade * (t * t)) / 2));
			t = t + 0.01;
			if (y <= 50.0 && t > 0) {
				v = v * 0.6;
				x1 = (int) x;
				y1 = 1;
				y = 0;
				t = 0;
			}
			JLabel rota = new JLabel("");
			rota.setBounds((int) x, (((int) y - 500) * (-1)), 70, 70);
			rota.setVisible(true);
			cena.add(rota);
			int velocidade = CalculaBarraDeForca();
			try {
				Thread.sleep(velocidade);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}

	static void ChamarAcoes() {
		janela.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					soltar = true;
					forca = barra.getValue();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					soltar = true;
					forca = barra.getValue();
				}
			}
		});
		janela.getKeyListeners();
	}

	static int CalculaBarraDeForca() {
		int frc = 0;
		if (forca >= 90 && forca <= 100) {
			frc = 1;
		} else if (forca >= 80 && forca < 90) {
			frc = 2;
		} else if (forca >= 70 && forca < 80) {
			frc = 3;
		} else if (forca >= 60 && forca < 70) {
			frc = 4;
		} else if (forca >= 50 && forca < 70) {
			frc = 5;
		} else if (forca >= 40 && forca < 50) {
			frc = 6;
		} else if (forca >= 30 && forca < 40) {
			frc = 7;
		} else if (forca >= 20 && forca < 30) {
			frc = 8;
		} else if (forca >= 10 && forca < 20) {
			frc = 9;
		} else if (forca >= 0 && forca < 10) {
			frc = 10;
		}

		return frc;
	}

	static void Movimenta() {
		int contador = 60;
		int limite = 2000;
		while (contador < limite) {
			red.setLocation(contador, 100);
			contador++;

		}
		try {
			Thread.sleep(CalculaBarraDeForca());
		} catch (InterruptedException e1) {

			e1.printStackTrace();
		}
	}

	static void TelaTrava() {
		Insets in = Toolkit.getDefaultToolkit().getScreenInsets(
				janela.getGraphicsConfiguration());
		Dimension g = Toolkit.getDefaultToolkit().getScreenSize();

		int height = g.height - (in.top + in.bottom);
		int width = g.width - (in.left + in.top);

		int largura = 1280;
		int altura = 500;

		janela.setSize(largura, altura);
		janela.setLocation(((width - largura) / 2), ((height - altura) / 2));

		janela.setResizable(false);
		janela.addComponentListener(

		new ComponentAdapter() {
			public void componentMoved(ComponentEvent e) {
				janela.setEnabled(true);
			}
		});
	}
}
