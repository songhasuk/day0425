package nowon.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import nowon.controller.dto.MemberDTO;


@WebServlet("/member/join")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	SqlSessionFactory ssf; // sql ������ ������ִ� 
    

	
	@Override
	public void init() throws ServletException {
		ssf = (SqlSessionFactory)getServletContext().getAttribute("ssf");
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		
		
		MemberDTO dto = new MemberDTO(email, name, pass); 

		// member Ŭ������ ����, DB���� ���� ���� member ��ü�� �ʵ忡 �־��ش�
		
		
		
		System.out.println(ssf);  //mybatis�� �̿��ؼ� DBó��
		SqlSession sqlSession = ssf.openSession(true);
		// openSession() �޼ҵ带 �̿��ؼ� XML���� ����
		//sqlSession.commit();//������ �� �����Ҽ� �ִ�.
		
		sqlSession.insert("memberMapper.save", dto);  
		//mapper.member.xml ���Ͽ� �ִ� sql���� �������ش�
		sqlSession.close();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
