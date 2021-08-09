package mx.edu.utez.model.game;

import mx.edu.utez.model.category.BeanCategory;
import mx.edu.utez.service.ConnectionMySQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class DaoGame {
    private Connection con;
    private CallableStatement cstm;
    private ResultSet rs;
    final private Logger CONSOLE = LoggerFactory.getLogger(DaoGame.class);
    int g;

    public List<BeanGame> findAll(){
        List<BeanGame> listgames =new ArrayList<>();
        try {
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call findgames}");
            rs = cstm.executeQuery();
            while (rs.next()){
                BeanCategory beanCategory = new BeanCategory();
                BeanGame beanGame = new BeanGame();

                beanCategory.setIdcategory(rs.getInt("idcategory"));
                beanCategory.setNamecategory(rs.getString("namecategory"));
                beanCategory.setStatus(rs.getInt("status"));
                beanGame.setIdgame(rs.getInt("idgame"));
                beanGame.setNombregame(rs.getString("nameGame"));
                beanGame.setCategory_idcategory(rs.getInt("idcategory"));
                beanGame.setDatepremiere(rs.getString("date_premiere"));
                beanGame.setStatus(rs.getInt("statuss"));
                beanGame.setImggame(Base64.getEncoder().encodeToString(rs.getBytes("imggame")));
                listgames.add(beanGame);
            }
        }catch (SQLException e){
            CONSOLE.error("Ha ocurrido un error: "+e.getMessage());
        }finally {
            ConnectionMySQL.closeConnections(con,cstm,rs);
        }
        return listgames;
    }

    public BeanGame ListarporId(int id){
        BeanGame beanGame = null;
        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("select * from empleados where idgame = ?");
            cstm.setInt(1,id);
            rs = cstm.executeQuery();

            while(rs.next()){
                beanGame = new BeanGame();
                beanGame.setIdgame(rs.getInt("idgame"));
                beanGame.setNombregame(rs.getString("nameGame"));
                beanGame.setCategory_idcategory(rs.getInt("idcategory"));
                beanGame.setDatepremiere(rs.getString("date_premiere"));
                beanGame.setStatus(rs.getInt("statuss"));
            }
        }catch(SQLException e){
            CONSOLE.error("Ha ocurrido un error: " + e.getMessage());
        }finally {
            ConnectionMySQL.closeConnections(con,cstm,rs);
        }
        return beanGame;
    }

    public boolean agregar(BeanGame beanGame, InputStream image){
        boolean flag = false;
        BeanCategory beanCategory = new BeanCategory();
        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call registergame(?,?,?,?)}");
            cstm.setString(1,beanGame.getNombregame());
            //cstm.setInt(2,beanGame.getCategory_idcategory());
            cstm.setString(3,beanGame.getDatepremiere());
            cstm.setInt(4,beanGame.getStatus());
            cstm.setBlob(5,image);
            cstm.executeUpdate();
            flag=true;
        }catch(SQLException e){
            CONSOLE.error("Ha ocurrido un error: " + e.getMessage());
        }finally {
            ConnectionMySQL.closeConnections(con,cstm);
        }
        return flag;
    }

    public boolean actualizar(BeanGame beanGame){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call modifygame(?,?,?,?,?,?,?,?)}");

            cstm.setString(1,beanGame.getNombregame());
           // cstm.setInt(2,beanGame.getCategory_idcategory());
            cstm.setString(3,beanGame.getDatepremiere());
            cstm.setInt(4,beanGame.getStatus());
            cstm.executeUpdate();
            flag = true;
        }catch (SQLException e){
            CONSOLE.error("Ha ocurrido un error: " + e.getMessage());
        }finally {
            ConnectionMySQL.closeConnections(con,cstm);
        }
        return flag;
    }

    public boolean eliminar(int idgame){
        boolean flag=false;
        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call deletegame(?)}");
            cstm.setInt(1,idgame);
            cstm.executeUpdate();
            flag=true;
        }catch(SQLException e){
            CONSOLE.error("Ha ocurrido un error: " + e.getMessage());
        }finally {
            ConnectionMySQL.closeConnections(con,cstm);
        }
        return flag;
    }

}
