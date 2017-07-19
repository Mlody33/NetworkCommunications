package controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import model.Client;

public class MainController implements Initializable{

	@FXML TableView<Client> clientsTableView;
	@FXML TableColumn<Client, String> identyfierColumn;
	@FXML TableColumn<Client, LocalDate> timeConnectionColumn;
	@FXML Button connectionBtn;
	@FXML Text statusTxt;
	
	private static final String ONLINE = "Server is online";
	private static final String OFFLINE = "Server is offline";
	private static final String SET_ON = "Switch ON";
	private static final String SET_OFF = "Switch OFF";
	
	private static final int CODE_CONNECTION = new Random().nextInt(8999)+1000;
	
	private boolean serverStatus = true;
	private AcceptanceOfClientsConnection acceptanceOfClientsConnection;
	private Main main;
	
	public void setMain(Main main) {
		this.main = main;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		identyfierColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("identyfier"));
		timeConnectionColumn.setCellValueFactory(new PropertyValueFactory<Client, LocalDate>("timeConnection"));
		connectionBtn.setText(SET_OFF);
		
		statusTxt.setText(ONLINE + " #" + CODE_CONNECTION);
	}
	
	public void setTable() {
		clientsTableView.setItems(main.getConnectedClients());
	}
	
	public void setConnection() throws IOException, InterruptedException {
		acceptanceOfClientsConnection = new AcceptanceOfClientsConnection(serverStatus);
		acceptanceOfClientsConnection.start();
	}

	@FXML public void switchServerConnection() {
		setServerStatus(!serverStatus);
	}

	public void setServerStatus(boolean online) {
		if(online) {
			serverStatus = true;
			statusTxt.setText(ONLINE);
			connectionBtn.setText(SET_OFF);
		} else {
			serverStatus = false;
			statusTxt.setText(OFFLINE);
			connectionBtn.setText(SET_ON);
			acceptanceOfClientsConnection.stopThread();
		}
		acceptanceOfClientsConnection.setServerStatus(serverStatus);
	}

}
