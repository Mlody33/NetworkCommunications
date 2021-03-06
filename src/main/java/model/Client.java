package model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.time.LocalDateTime;

import controller.Signal;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Client implements ClientTemplate, Externalizable {

	private static final long serialVersionUID = 1L;
	private SimpleIntegerProperty clientNumber;
	private SimpleBooleanProperty connected;
	private SimpleIntegerProperty authorizationCode;
	private SimpleBooleanProperty authorized;
	private ObjectProperty<Signal> signalToCommunicationWithServer;
	private ObjectProperty<LocalDateTime> timeConnection;
	
	public Client() {
		this.clientNumber = new SimpleIntegerProperty(0);
		this.connected = new SimpleBooleanProperty(false);
		this.authorizationCode = new SimpleIntegerProperty(0);
		this.authorized = new SimpleBooleanProperty(false);
		this.signalToCommunicationWithServer = new SimpleObjectProperty<Signal>(Signal.NONE);
		this.timeConnection = new SimpleObjectProperty<LocalDateTime>(LocalDateTime.now().withNano(0));
	}
	
	public Client (int clientNumber, boolean connected, int authorizationCode, boolean authorized, LocalDateTime timeConnection) {
		this.clientNumber = new SimpleIntegerProperty(clientNumber);
		this.connected = new SimpleBooleanProperty(connected);
		this.authorizationCode = new SimpleIntegerProperty(authorizationCode);
		this.authorized = new SimpleBooleanProperty(authorized);
		this.signalToCommunicationWithServer = new SimpleObjectProperty<Signal>(Signal.NONE);
		this.timeConnection = new SimpleObjectProperty<LocalDateTime>(timeConnection.withNano(0));
	}
	
	public void setClientData(Client client) {
		this.clientNumber = new SimpleIntegerProperty(client.getClientNumber());
		this.connected = new SimpleBooleanProperty(client.isConnected());
		this.authorizationCode = new SimpleIntegerProperty(client.getAuthorizationCode());
		this.authorized = new SimpleBooleanProperty(client.isAuthorized());
		this.signalToCommunicationWithServer = new SimpleObjectProperty<Signal>(client.getSignalToCommunicationWithServer());
		this.timeConnection = new SimpleObjectProperty<LocalDateTime>(client.getTimeConnection());
	}
	
	@Override
	public int getClientNumber() {
		return this.clientNumber.get();
	}

	@Override
	public void setClientNumber(int number) {
		this.clientNumber = new SimpleIntegerProperty(number);
	}

	@Override
	public boolean isConnected() {
		return this.connected.get();
	}

	@Override
	public void setConnected() {
		this.connected = new SimpleBooleanProperty(true);
	}
	
	@Override
	public void setNotConnected() {
		this.connected = new SimpleBooleanProperty(false);
	}

	@Override
	public int getAuthorizationCode() {
		return this.authorizationCode.get();
	}

	@Override
	public void setAuthorizationCode(int number) {
		this.authorizationCode = new SimpleIntegerProperty(number);
	}

	@Override
	public boolean isAuthorized() {
		return this.authorized.get();
	}

	@Override
	public void setAuthorized() {
		this.authorized = new SimpleBooleanProperty(true);
	}
	
	@Override
	public void setNotAuthorized() {
		this.authorized = new SimpleBooleanProperty(false);
	}
	
	@Override
	public Signal getSignalToCommunicationWithServer() {
		return this.signalToCommunicationWithServer.get();
	}

	@Override
	public void setSignalToCommunicationWithServer(Signal signal) {
		this.signalToCommunicationWithServer = new SimpleObjectProperty<Signal>(signal);
	}

	@Override
	public LocalDateTime getTimeConnection() {
		return this.timeConnection.get().withNano(0);
	}

	@Override
	public void setTimeConnection(LocalDateTime timeConnection) {
		this.timeConnection = new SimpleObjectProperty<LocalDateTime>(timeConnection.withNano(0));
	}
	
	@Override
	public String toString() {
		return "Client [clientNumber=" + clientNumber + ", connected=" + connected + ", authorizationCode="
				+ authorizationCode + ", authorized=" + authorized + ", signalToCommunicationWithServer="
				+ signalToCommunicationWithServer + ", timeConnection=" + timeConnection + "]";
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		this.clientNumber = new SimpleIntegerProperty(in.readInt());
		this.authorizationCode = new SimpleIntegerProperty(in.readInt());
		this.connected = new SimpleBooleanProperty(in.readBoolean());
		this.authorized = new SimpleBooleanProperty(in.readBoolean());
		this.signalToCommunicationWithServer = new SimpleObjectProperty<Signal>((Signal) in.readObject());
		this.timeConnection = new SimpleObjectProperty<LocalDateTime>((LocalDateTime) in.readObject());
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeInt(getClientNumber());
		out.writeInt(getAuthorizationCode());
		out.writeBoolean(isConnected());
		out.writeBoolean(isAuthorized());
		out.writeObject(getSignalToCommunicationWithServer());
		out.writeObject(getTimeConnection());
	}

}
