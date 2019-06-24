package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.DBUtil;

public class VideoDao {
	public ArrayList<String> findVideo(String name){
		Connection connection = DBUtil.getConn();
		String sql = "select video from spot where name=?";
		PreparedStatement preparedStatement = null;
		ResultSet rSet = null;
		String music = null;
		ArrayList<String> list = new ArrayList<String>();
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			rSet = preparedStatement.executeQuery();
			
			while(rSet.next()){
				music = rSet.getString(1);
				list.add(music);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DBUtil.closeRst(rSet);
			DBUtil.closePstmt(preparedStatement);
			DBUtil.closeConn(connection);
		}
		
		return list;
	}
}
