package org.ARuiz.Model.Connections;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMySQL {
    private String file = "conexion.xml";
    private static ConnectionMySQL _newInstance;
    private static Connection con;

    private ConnectionMySQL() {
        ConecctionData cd = loadXML();

        try {
            con = DriverManager.getConnection(cd.getServer()+"/"+cd.getDatabase(),cd.getUsername(),cd.getPassword());
        } catch (SQLException e) {
            con=null;
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnect() {
        if(_newInstance==null){
            _newInstance=new ConnectionMySQL();
        }
        return con;
    }

    public ConecctionData loadXML() {
        ConecctionData con = new ConecctionData();
        JAXBContext jaxbContext;
        try{
            jaxbContext = JAXBContext.newInstance(ConecctionData.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            con = (ConecctionData) jaxbUnmarshaller.unmarshal(new File(file));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

        return con;
    }

}
