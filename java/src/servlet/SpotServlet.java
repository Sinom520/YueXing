package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.Delayed;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.filters.AddDefaultCharsetFilter;
import org.eclipse.jdt.internal.compiler.tool.EclipseCompiler;

import com.google.gson.Gson;
import com.sun.glass.ui.CommonDialogs.Type;

import dao.SpotDao;
import kind.Spot;

/**
 * Servlet implementation class SpotServlet
 */
@WebServlet("/SpotServlet")
public class SpotServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SpotDao spotDao = new SpotDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SpotServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 response.setContentType("text/html;charset=utf-8");          
	        /* 设置响应头允许ajax跨域访问 */  
	        response.setHeader("Access-Control-Allow-Origin", "*");  
	        /* 星号表示所有的异域请求都可以接受， */  
	        response.setHeader("Access-Control-Allow-Methods", "GET,POST");  
	       
	        //获取微信小程序get的参数值并打印
	        String name = request.getParameter("keyword");
	        //浏览器转码
	        //request.setCharacterEncoding("GBK");
	        //response.setContentType("text/html;charset=GBK");
	        //小程序转码
	        byte [] bytes =name .getBytes("ISO-8859-1");
	        name = new String(bytes, "utf-8");
	        Spot spot = new Spot();
	        spot.setName(name);
	        ArrayList<Spot> spotList= spotDao.findAll(spot);;

			Gson gson = new Gson();
			String spotListToJson = gson.toJson(spotList);
	        
	        //返回值给微信小程序
	        PrintWriter out = response.getWriter();
	        out.write(spotListToJson);
	        //out.write("进入后台了");
	        out.flush();   

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
