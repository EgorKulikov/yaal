package net.egork;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author egor@egork.net
 */
public class Video {
	public static void main(String[] args) throws IOException {
		DatagramPacket outgoing = new DatagramPacket("\n".getBytes(), 1, InetAddress.getByName("ch24.org"), 24130);
		DatagramSocket socket = new DatagramSocket();
		socket.send(outgoing);
		byte[] data = new byte[1 << 17];
		DatagramPacket incoming = new DatagramPacket(data, data.length);
		JFrame frame = new JFrame();
		JLabel label = new JLabel();
		frame.setContentPane(label);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		boolean first = true;
		while (true) {
			socket.receive(incoming);
			BufferedImage image = ImageIO.read(new ByteArrayInputStream(incoming.getData(), 0, incoming.getLength()));
			label.setIcon(new ImageIcon(image));
			if (first) {
				frame.pack();
				first = false;
			}
		}
	}
}
