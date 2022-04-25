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
	
	SqlSessionFactory ssf; // sql 섹션을 만들어주는 
    

	
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

		// member 클래스를 만들어서, DB에서 받은 값을 member 객체의 필드에 넣어준다
		
		
		
		System.out.println(ssf);  //mybatis를 이용해서 DB처리
		SqlSession sqlSession = ssf.openSession(true);
		// openSession() 메소드를 이용해서 XML파일 실행
		//sqlSession.commit();//접속할 때 설정할수 있다.
		
		sqlSession.insert("memberMapper.save", dto);  
		//mapper.member.xml 파일에 있는 sql문을 실행해준다
		sqlSession.close();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
