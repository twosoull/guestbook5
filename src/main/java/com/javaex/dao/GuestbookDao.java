package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestVo;

@Repository
public class GuestbookDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<GuestVo> getList(){
		System.out.println("Dao : getList");
		
		List<GuestVo> guestList = sqlSession.selectList("guestbook.selectList");
		
		System.out.println("Dao : guestList"+guestList);
		
		return guestList;
	}
	

	public void insert(GuestVo guestVo) {
		System.out.println("Dao : insert");
		
		int count = sqlSession.insert("guestbook.insert",guestVo);
		
		System.out.println("Dao : count = " + count);
		
	}
	
	//map 이용
	public void insert2(String name, String password, String content) {
		
		System.out.println("Dao : insert2 = " 
							+ ", " + name
							+ ", " + password
							+ ", " + content
						  );
		//sqlSession insert에는 파라미터값이 하나밖에 들어갈수없어서
		//vo가 없다는 가정하에 map으로 만든다
		
		Map<String,Object> guestMap = new HashMap<String,Object>();
		
		guestMap.put("name", name);
		guestMap.put("password", password);
		guestMap.put("content", content);
		
		sqlSession.insert("guestbook.insert2",guestMap);
		
		
	}
	
	public int delete(GuestVo guestVo) {
		System.out.println("Dao: delete guestVo" + guestVo);
		
		//비밀번호 성공 실패를 위해 count 값이 필요함
		int count = sqlSession.delete("guestbook.delete",guestVo);
		System.out.println("Dao: delete count = "+count);
		return count;
	}
	
	
}
