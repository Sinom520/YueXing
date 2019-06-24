package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kind.View;
import util.DBUtil;

public class ViewDao {
	// 根据景点名字查找景点详情
	public View findView(String name){
		Connection connection = DBUtil.getConn();
		String sql = "select * from spot where binary name=?";
		PreparedStatement preparedStatement = null;
		ResultSet rSet = null;
		View view = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			rSet = preparedStatement.executeQuery();
			
			while(rSet.next()){
				view = new View();
				view.setName(name);
				view.setDetails(rSet.getString(2));
				view.setImage(rSet.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DBUtil.closeRst(rSet);
			DBUtil.closePstmt(preparedStatement);
			DBUtil.closeConn(connection);
		}
		
		return view;
	}
	
}
