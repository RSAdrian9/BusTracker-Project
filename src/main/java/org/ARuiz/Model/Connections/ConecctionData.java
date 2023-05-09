package org.ARuiz.Model.Connections;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
@XmlRootElement(name = "conexion")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConecctionData implements Serializable {
    private static final long serialVersionUID=1L;
    private String server;
    private String database;
    private String username;
    private String password;

    public ConecctionData() {
        this.server="";
        this.database="";
        this.username="";
        this.password="";
    }

    @Override
    public String toString() {
        return "ConexionData{" +
                "server='" + server + '\'' +
                ", database='" + database + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
