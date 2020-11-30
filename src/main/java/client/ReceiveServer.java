package client;


import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


class ReceiveServer implements Runnable
{
	private BufferedReader in;
	private Socket socket;
	public static List<tkb> mainData = new ArrayList();


	public ReceiveServer(Socket s, BufferedReader i) {
		this.socket = s;
		this.in = i;
	}

	private String receive() throws IOException {
		String input = in.readLine();
		if (input == null)
			return "";

		return input; // giai ma hoa
	}
	public List<tkb> convertJsonToArray(String jsonString){
		JSONArray jsonArray= new JSONArray(jsonString);
		List<tkb> tkbs = new ArrayList<>();
		for (int i = 0; i < jsonArray.length(); i++) {
			tkb tkb = new Gson().fromJson(jsonArray.get(i).toString(), tkb.class);
			tkbs.add(tkb);
		}
		return tkbs;

	}


	public void run() {

		try {
			while(true) {
				String input = receive();
				if (input.isEmpty()){
					continue;
				}
				else{
					 GUI.tkbs=convertJsonToArray(input);

					 //Client.gui.processInput(result);
						//showResult();
					}

				System.out.println("[Server] "+input);
			}
		} catch (IOException e) {

		}
	}
}
