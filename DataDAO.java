package DAO;

import com.mysql.cj.x.protobuf.MysqlxPrepare;
import db.MyConnection;
import model.Data;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//show
//hide
//unhide files
public class DataDAO {
    public static List<Data> getAllFiles(String email) throws SQLException {
        Connection connection = MyConnection.getConnection();
        PreparedStatement stmt = connection.prepareStatement("select * from data where email = ?");
        stmt.setString(1,email);
        ResultSet rs = stmt.executeQuery();
        List<Data> files = new ArrayList<>();
        while(rs.next()){
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String path = rs.getString(3);
            files.add(new Data(id,name,path));
        }
        return files;
    }
    public static int hideFile(Data file) throws SQLException, IOException {
        Connection connection = MyConnection.getConnection();
        PreparedStatement stmt = connection.prepareStatement("insert into data(fileName,filePath,email,bin_data) values (?,?,?,?);");
        stmt.setString(1,file.getFileName());
        stmt.setString(2,file.getPath());
        stmt.setString(3,file.getEmail());
        File f = new File(file.getPath());
        FileReader fr = new FileReader(f);
        stmt.setCharacterStream(4,fr,f.length());
        int ans = stmt.executeUpdate();
        System.out.println("File hidden Successfully.");
        fr.close();
        f.delete();
        return ans;
    }
    public static void unhide(int id) throws SQLException, IOException{
        Connection connection = MyConnection.getConnection();
        PreparedStatement stmt = connection.prepareStatement("select filePath,bin_data from data where id=?");
        stmt.setInt(1,id);
        ResultSet rs  = stmt.executeQuery();

        rs.next(); //no need for while loop as there is only 1 data to fetch.
        String path = rs.getString("filePath");
        Clob c = rs.getClob("bin_data");
        Reader r = c.getCharacterStream();
        FileWriter fw = new FileWriter(path);
        int i ;
        while((i = r.read()) != -1){
            fw.write((char)i);

        }
        fw.close();
        stmt = connection.prepareStatement("delete from data where id = ?");
        stmt.setInt(1,id);
        stmt.executeUpdate();
        System.out.println("File is Unhide Successful");
    }
}
