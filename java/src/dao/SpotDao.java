package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.print.attribute.ResolutionSyntax;

import com.google.gson.Gson;

import kind.Spot;
import util.DBUtil;

public class SpotDao {
	public  ArrayList<Spot> findAll(Spot s){
		//创建连接
		Connection conn = DBUtil.getConn();
		String keyword=s.getName();
		ArrayList<Spot> list = new ArrayList<Spot>();
		//SQL语句
		String sql = "select name from spot where name like '%"+keyword+"%'";
		if(keyword==null){
			Spot sp = new Spot();
			sp.setName(null);
			sp.setEmptyflag(1);
			list.add(sp);
			return list;
		}
		else{
		//创建PreparedStatement
		PreparedStatement pstmt = null;
		ResultSet rSet = null;
		try {
			pstmt = conn.prepareStatement(sql);
			//发送SQL语句
			rSet = pstmt.executeQuery();
			//处理结果
			while(rSet.next()){
				Spot sp = new Spot();
				sp.setName(rSet.getString(1));
				list.add(sp);
			}
			if(list.isEmpty())
			{
				Spot sp = new Spot();
				sp.setName(null);
				sp.setNoresultflag(1);
				list.add(sp);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.closeRst(rSet);
			DBUtil.closePstmt(pstmt);
			DBUtil.closeConn(conn);
		}
		}
		
		return list;
	}

}
