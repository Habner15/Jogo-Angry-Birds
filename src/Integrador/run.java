package Integrador;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import java.awt.Color;
import java.awt.Component;
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
	static ImageIcon MenuFoto = new ImageIcon("run\\Menu.jpg");
	static ImageIcon porco = new ImageIcon("run\\pig.gif");
	static ImageIcon fundo = new ImageIcon("run\\fundo.png");
	static ImageIcon slingshot = new ImageIcon("run\\Slingshot.png");
	static ImageIcon passaro = new ImageIcon("run\\red_birds.png");
	static ImageIcon slingshot2 = new ImageIcon("run\\Slingshot2.png");
	static JFrame frame = new JFrame("Menu Angry Birds");
	static JButton Jogar = new JButton("Jogar");
	static JButton Sair = new JButton("Sair");
	static JButton Voltar = new JButton("Fechar");
	static JLabel cena;
	static JLabel red;
	static JLabel slingshotFora;
	static JLabel slingshotDentro;
	static JLabel pig;
	static JLabel rota;
	static JLabel msg;
	static JLabel angulo;
	static JLabel titulo;
	static JPanel painel;
	static JLabel menu = new JLabel();
	static JProgressBar barra;
	static JProgressBar barraAngulo;

	static int forca;
	static boolean soltar = false;

	public static void main(String[] args) {
		telaInicial();
		CriaJanela();
		MostraJanela();
		BarraMovimento();
	}

	static void BotaoJogar() {

		Jogar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				janela.setVisible(true);
				TelaTrava();
				JOptionPane.showMessageDialog(null,
						"     Acerte o Porcão Rei!\nAperte OK para continuar");
			}
		});

	}

	public static void TelaDoJogo() {

		CriaJanela();
		MostraJanela();
		TelaTrava();
		BarraMovimento();
	}

	static void BotaoSair() {

		Sair.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

	}

	static void BotaoFechar() {

		Voltar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

	}

	static void CriaJanela() {
		janela = new JFrame("Angry Birds");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	static void telaInicial() {

		componentesMenu();
		frame.setSize(400, 350);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.add(menu);
		menu.setIcon(MenuFoto);
		menu.setVisible(true);
		menu.setSize(400, 350);
	}

	private static void componentesMenu() {

		menu.add(Jogar);
		Jogar.setVisible(true);
		Jogar.setBounds(125, 60, 150, 50);
		Jogar.setFont(new Font("Tahoma", Font.BOLD, 15));
		Jogar.setForeground(Color.RED);
		Jogar.setBorder(null);
		BotaoJogar();

		menu.add(Sair);
		Sair.setVisible(true);
		Sair.setBounds(125, 190, 150, 50);
		Sair.setFont(new Font("Tahoma", Font.BOLD, 15));
		Sair.setForeground(Color.RED);
		Sair.setBorder(null);
		BotaoSair();

	}

	static void MostraJanela() {
		IniciaComponentes();
		janela.getContentPane().setBackground(Color.BLUE);
		cena.add(barraAngulo);
		cena.add(barra);
		cena.add(msg);
		cena.add(angulo);
		cena.add(titulo);
		janela.add(cena);
		cena.add(slingshotFora);
		cena.add(red);
		cena.add(slingshotDentro);
		cena.add(pig);
		janela.setVisible(true);

	}

	static void IniciaComponentes() {

		cena = new JLabel(fundo);
		cena.setLayout(null);
		slingshotFora = new JLabel(slingshot);
		slingshotFora.setBounds(52, 281, 85, 150);
		red = new JLabel(passaro);
		red.setBounds(45, 275, 48, 45);
		slingshotDentro = new JLabel(slingshot2);
		slingshotDentro.setBounds(50, 280, 85, 150);
		pig = new JLabel(porco);
		pig.setBounds(1000, 315, 107, 119);
		titulo = new JLabel("PRESSIONE ESPAÇO PARA LANÇAR");
		titulo.setBounds(530, 20, 250, 30);
		titulo.setFont(new Font("Comic Sans", Font.BOLD + Font.ITALIC, 14));
		titulo.setVisible(true);
		msg = new JLabel("Barra de Força");
		msg.setBounds(10, 15, 250, 30);
		msg.setFont(new Font("Comic Sans", Font.BOLD + Font.ITALIC, 12));
		msg.setVisible(true);
		angulo = new JLabel("Ângulo = " + 40 + " Graus");
		angulo.setBounds(10, 65, 250, 30);
		angulo.setFont(new Font("Comic Sans", Font.BOLD + Font.ITALIC, 12));
		angulo.setVisible(true);

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

		barraAngulo = new JProgressBar();
		barraAngulo.setMinimum(0); // valor inicial da barra
		barraAngulo.setMaximum(90);// valor final da barra
		barraAngulo.setLocation(10, 60);
		barraAngulo.setSize(200, 15);// largura e altura
		barraAngulo.setStringPainted(true); // exibir os numeros com a
		barraAngulo.setBackground(Color.ORANGE);// cor de fundo da barra de
		barraAngulo.setForeground(Color.RED); // cor do progresso da barra de
		barraAngulo.setIndeterminate(false); // tipo de progresso
		barraAngulo.setVisible(false);

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
		double angulo = 40 * (Math.PI / 180); // converte para radianos
		double t = 1;
		double cos = (Math.cos(angulo));
		double v = forca;
		int y1 = 200;
		double y = 0;
		double sen = (Math.sin(angulo));
		double gravidade = 7.0;

		while (y >= 0 && x <= 1500) {
			red.setLocation((int) x, (((int) y - 500) * (-1)));
			x = (x1 + (v * cos * t));
			y = (y1 + (v * sen * t) - ((gravidade * (t * t)) / 2));
			t = t + 0.03;
			if (y <= 50.0 && t > 0) {
				v = v * 0.6;
				x1 = (int) x;
				y1 = 1;
				y = 0;
				t = 0;
			}
			JLabel rota = new JLabel("");
			cena.add(rota);
			int velocidade = CalculaBarraDeForca();
			try {
				Thread.sleep(velocidade);
			} catch (InterruptedException e1) {
				e1.printStackTrace();

			}
			red.setBounds(red.getX(), red.getY(), 35, 38);
			if (bateu(red, pig)) {
				JOptionPane.showMessageDialog(null,
						"Parabéns! Você acertou! \nAperte Ok Para Fechar");
				System.exit(0);

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
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					soltar = true;
					forca = barra.getValue();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					soltar = true;
					forca = barra.getValue();
				}
			}
		});
		janela.getKeyListeners();

		if (soltar == true) {
			FuncaoMovimenta();

		}
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
		int limite = 100;
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

				cena.add(Voltar);
				Voltar.setVisible(true);
				Voltar.setBounds(585, 60, 100, 50);
				Voltar.setFont(new Font("Tahoma", Font.BOLD, 15));
				Voltar.setForeground(Color.BLUE);
				Voltar.setBorder(null);
				BotaoFechar();
			}
		});
	}

	public static boolean bateu(Component red, Component pig) {
		int redX = red.getX();
		int redY = red.getY();
		int ladoDireitoA = redX + red.getWidth();
		int ladoEsquerdoA = redX;
		int ladoBaixoA = redY + red.getHeight();
		int ladoCimaA = redY;

		int pigX = pig.getX();
		int pigY = pig.getY();
		int ladoDireitoB = pigX + pig.getWidth();
		int ladoEsquerdoB = pigX;
		int ladoBaixoB = pigY + pig.getHeight();
		int ladoCimaB = pigY;

		boolean bateu = false;

		boolean cDireita = false;
		boolean cCima = false;
		boolean cBaixo = false;
		boolean cEsquerda = false;

		if (ladoDireitoA >= ladoEsquerdoB) {
			cDireita = true;
		}
		if (ladoCimaA <= ladoBaixoB) {
			cCima = true;
		}
		if (ladoBaixoA >= ladoCimaB) {
			cBaixo = true;
		}
		if (ladoEsquerdoA <= ladoDireitoB) {
			cEsquerda = true;
		}

		if (cDireita && cEsquerda && cBaixo && cCima) {
			bateu = true;
		}

		return bateu;
	}
}
