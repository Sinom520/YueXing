package servlet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.ViewDao;
import kind.View;
import vo.ViewPath;

/**
 * Servlet implementation class ViewServlet
 */
@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ViewDao viewDao = new ViewDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");          
        /* 设置响应头允许ajax跨域访问 */  
        response.setHeader("Access-Control-Allow-Origin", "*");  
        /* 星号表示所有的异域请求都可以接受， */  
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");  
        
        // 返回值给微信小程序
        Gson gson = new Gson();
        String viewToJson = null;
        
        // 获取请求查找景点详情
        String name = request.getParameter("viewName");
        byte [] bytes =name.getBytes("ISO-8859-1");
        name = new String(bytes, "utf-8");
        View view = new View();
        ViewPath viewPath = new ViewPath();
        view = viewDao.findView(name);
        StringBuffer text =new StringBuffer();
        
        if(view == null || view.getDetails() == null || view.getDetails().isEmpty()){ // 该景点没有详情介绍
        	String erro = "很遗憾，该景点还没有官方介绍，快去上传你的音频介绍吧~";
        	text.append(erro);
        	viewPath.setImage("../../image/default.jpg");
        } else { // 有介绍，获取文本文件中的内容
        	String file = view.getDetails();
        	InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "gbk");//加上编码转换  
        	BufferedReader read = new BufferedReader(isr);  
        	String line = null;    
        	while ((line = read.readLine()) != null) {     
        	      text.append(line);    
        	}    
        	read.close();
        	
        	if(view.getImage() == null || view.getImage().isEmpty()){
        		viewPath.setImage("../../image/default.jpg");
        	} else{
        		viewPath.setImage(view.getImage());
        	}
        }

        viewPath.setName(name);
        viewPath.setText(text);
        viewToJson = gson.toJson(viewPath);
        // 返回
        PrintWriter out = response.getWriter();
        out.write(viewToJson);
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
