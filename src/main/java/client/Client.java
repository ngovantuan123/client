package client;


import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Client
{
	private static String host = "localhost";
	private static int port = 1234;
	private static Socket socket;

	private static BufferedWriter out;
	private static BufferedReader in;

	private static ExecutorService executor;

	public static GUI gui;

	public void run() throws IOException {

		executor = Executors.newFixedThreadPool(2);

		socket = new Socket(host, port);
		System.out.println("Client connected ");

		out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    
		//gui = new GUI(socket,out,in);
		ChonMonHoc chonMonHoc =new ChonMonHoc(socket,in,out);
		ReceiveServer recv = new ReceiveServer(socket, in);
		executor.execute(chonMonHoc);
		//executor.execute(recv);
	}

	public static void close() throws IOException
	{
		executor.shutdown();
		System.out.println("Client closed connection");
		in.close();
		out.close();
		socket.close();
	}
}
