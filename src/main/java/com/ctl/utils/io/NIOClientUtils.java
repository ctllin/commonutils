/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ctl.utils.io;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;

//import org.apache.log4j.Logger;



/**
 *
 * @author ctl
 */
@SuppressWarnings("serial")
public class NIOClientUtils extends javax.swing.JFrame {
	//private static Logger logger = Logger.getLogger(NIOClientUtils.class);
	// 定义检测SocketChannel的selector对象
	private Selector selector = null;
	// 定义处理编码和解码的字符集
	private Charset charset = Charset.forName("gbk");
	// 客户端SocketChannel
	private SocketChannel sc = null;
	private StringBuilder showContent=new StringBuilder();

	/**
	 * Creates new form NIOClientUtil
	 */
	public NIOClientUtils() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	private void initComponents() {
		this.setTitle("NIOTCPClient");
		jLabelIP = new javax.swing.JLabel();
		ip = new javax.swing.JTextField();
		jLabelPORT = new javax.swing.JLabel();
		port = new javax.swing.JTextField();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTextArea1 = new javax.swing.JTextArea();
		jTextArea1.setWrapStyleWord(true);// 激活断行不断字功能
		jTextArea1.setLineWrap(true);
		jTextArea1.setFont(new   Font( "新宋体 ",Font.PLAIN,9));
		jTextArea1.setBackground(new Color(240, 240, 240));
		clearContent = new javax.swing.JButton();
		sendContent = new javax.swing.JButton();
		clientLink = new javax.swing.JButton();
		jScrollPane2 = new javax.swing.JScrollPane();
		jTextArea2 = new javax.swing.JTextArea();
		jTextArea2.setWrapStyleWord(true);// 激活断行不断字功能
		jTextArea2.setLineWrap(true);
		jTextArea2.setEditable(false);
		jTextArea2.setFont(new   Font( "新宋体 ",Font.PLAIN,9));
		//jTextArea2.setForeground(Color.LIGHT_GRAY); //设置字体颜色
		//	jTextArea2.setBackground(new Color(199, 237, 204));
		jTextArea2.setBackground(new Color(240, 240, 240));
		jTextArea2.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jTextArea2MouseClicked(evt);
			}
		});
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jLabelIP.setText("IP:");

		ip.setText("192.168.42.1");
		ip.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				ipActionPerformed(evt);
			}
		});

		jLabelPORT.setText("PORT:");

		port.setText("1234");
		port.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				portActionPerformed(evt);
			}
		});

		jTextArea1.setColumns(20);
		jTextArea1.setRows(5);
		jScrollPane1.setViewportView(jTextArea1);

		clearContent.setText("清空");
		clearContent.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				clearContentMouseClicked(evt);
			}
		});

		sendContent.setText("发送");
		sendContent.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				sendContentMouseClicked(evt);
			}
		});

		clientLink.setText("连接");
		clientLink.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				clientLinkMouseClicked(evt);
			}
		});
		clientLink.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				clientLinkActionPerformed(evt);
			}
		});

		jTextArea2.setColumns(20);
		jTextArea2.setRows(5);
		jScrollPane2.setViewportView(jTextArea2);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addGap(18, 18, 18)
								.addComponent(clearContent,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										81,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(sendContent,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										81,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(22, 22, 22))
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(jLabelIP,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(ip,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										115,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jLabelPORT)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(port,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										57,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(clientLink,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										67,
										javax.swing.GroupLayout.PREFERRED_SIZE))
				.addComponent(jScrollPane1).addComponent(jScrollPane2));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabelIP)
												.addComponent(
														ip,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabelPORT)
												.addComponent(
														port,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(clientLink))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jScrollPane1,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										236, Short.MAX_VALUE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(clearContent)
												.addComponent(sendContent))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jScrollPane2,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										213, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>

	private void ipActionPerformed(java.awt.event.ActionEvent evt) {
	}

	private void clearContentMouseClicked(java.awt.event.MouseEvent evt) {
		jTextArea1.setText(null);
	}

	private void sendContentMouseClicked(java.awt.event.MouseEvent evt) {
		try {
			if(jTextArea1.getText()==null||"".equals(jTextArea1.getText().trim())){
				return;
			}
			sc.write(charset.encode(jTextArea1.getText()));
			showContent.append("Client Send:\n"+jTextArea1.getText()+"\n");
			jTextArea1.setText(null);
			jTextArea2.setText(showContent.toString());
			jTextArea2.setCaretPosition(showContent
					.length());
		} catch (IOException e) {
			//logger.info(e.getMessage());
		}
	}

	private void clientLinkActionPerformed(java.awt.event.ActionEvent evt) {
	}
	private void jTextArea2MouseClicked(java.awt.event.MouseEvent evt) {
		if(evt.getClickCount()==2){
			showContent.delete(0, showContent.length());
			jTextArea2.setText(null);
		}
	}

	private void clientLinkMouseClicked(java.awt.event.MouseEvent evt) {
		if(clientLink.getText().equals("连接")){
			try {
				selector = Selector.open();
				InetSocketAddress isa = new InetSocketAddress(ip.getText(), 1234);
				sc = SocketChannel.open(isa);
				sc.configureBlocking(false);
				sc.register(selector, SelectionKey.OP_READ);
				clientLink.setText("断开");
				class ClientThread extends Thread {
					public void run() {
						try {
							while (selector.select()> 0) {//select()==1
								for (SelectionKey sk : selector.selectedKeys()) {
									// 删除正在处理的SelectionKey
									selector.selectedKeys().remove(sk);
									if (sk.isReadable()) {
										SocketChannel socketChannel = (SocketChannel) sk.channel();
										if(sc!=null&&sc==socketChannel){
											ByteBuffer buff = ByteBuffer.allocate(1024);
											String content = "";
											while (socketChannel.read(buff) > 0) {
												socketChannel.read(buff);
												buff.flip();
												content += charset.decode(buff).toString();
											}
											if(content.trim().length()<=0){
												//logger.info("SocketServer 关闭");
												clientLink.setText("连接");
												jTextArea2.setText("");
												jTextArea2.setCaretPosition(0);
												return;
											}
											showContent.append("Server Send:" + content+"\n");
											System.out.println(showContent.toString());
											jTextArea2.setText(showContent.toString());
											jTextArea2.setCaretPosition(showContent
													.length());
											sk.interestOps(SelectionKey.OP_READ);
										}
									}
								}
							}
						} catch (Exception ex) {//ServerScoket关闭
							try {
								if(sc!=null&&sc.isOpen())
									sc.close();
								if(selector!=null&&selector.isOpen())
									selector.close();
							} catch (Exception e) {
								//	logger.info(e.getMessage());
							}
							clientLink.setText("连接");
							//logger.info("1"+ex.getMessage());
						}
					}
				}
				new ClientThread().start();
			} catch (Exception e) {
				try {
					if(sc!=null&&sc.isOpen())
						sc.close();
					if(selector!=null&&selector.isOpen())
						selector.close();
				} catch (Exception e1) {
					//logger.info("2"+e1.getMessage());
				}
				clientLink.setText("连接");
				//logger.info("3"+e.getMessage());
			}
		}else{
			clientLink.setText("连接");
			try {
				if(sc!=null&&sc.isOpen())
					sc.close();
				if(selector!=null&&selector.isOpen())
					selector.close();
			} catch (IOException e) {
				//logger.info("4"+e.getMessage());
			}
		}

	}

	private void portActionPerformed(java.awt.event.ActionEvent evt) {
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(NIOClientUtils.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(NIOClientUtils.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(NIOClientUtils.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(NIOClientUtils.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new NIOClientUtils().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify
	private javax.swing.JButton clearContent;
	private javax.swing.JButton clientLink;
	private javax.swing.JTextField ip;
	private javax.swing.JLabel jLabelIP;
	private javax.swing.JLabel jLabelPORT;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JTextArea jTextArea1;
	private javax.swing.JTextArea jTextArea2;
	private javax.swing.JTextField port;
	private javax.swing.JButton sendContent;
	// End of variables declaration
}
