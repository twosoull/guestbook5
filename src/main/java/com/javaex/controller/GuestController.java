package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestVo;

@Controller // 컨트롤러표시 안할경우 접근이 되질않는다 + RequestMapping으로 주소 분할
@RequestMapping(value = "/guest", method = { RequestMethod.GET, RequestMethod.POST })
public class GuestController {

	@Autowired
	private GuestbookDao guestDao; // 오토와이어를 사용하기위해서 레파지토리표시 해야함

	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model) {
		System.out.println("Controller list");

		// list를 불러와야함 Dao를 이용
		List<GuestVo> guestList = guestDao.getList();

		System.out.println("controller list = " + guestList);// 보내기전 확인작업

		// 포워드
		// 리퀘스트어트리뷰트를 사용하기위해 model에 담아 디스패처로 보내줌
		model.addAttribute("guestList", guestList);

		return "list"; // viewResolver를 이용해 url을 줄였기 때문에 파일이름만 입력
	}
	

	@RequestMapping(value = "/insert", method = { RequestMethod.GET, RequestMethod.POST })
	public String insert(@ModelAttribute GuestVo guestVo) {
		// @RequestParam("name") String name 식의 파라미터를 이용할 수 있지만
		// ModelAttribte를 이용해면 디스패처에서 vo로 묶어준 vo의 주소를 받을 수 있다
		System.out.println("Controller insert");
		System.out.println("Controller guestVo = " + guestVo);// 테스트

		// DAO로 vo의 주소를 넘겨준다
		guestDao.insert(guestVo);

		return "redirect:/guest/list";

	}
	
	
	//map 이용
	@RequestMapping(value = "/insert2", method = { RequestMethod.GET, RequestMethod.POST })
	public String insert2(@RequestParam("name") String name,
						  @RequestParam("password") String password,
						  @RequestParam("content") String content) {
		
		System.out.println("Controller insert2");
		
		System.out.println(name +", "+ password+", " + content);
		
		guestDao.insert2(name,password,content);
	

		return "redirect:/guest/list";

	}

	@RequestMapping(value = "/deleteForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleteForm(@RequestParam("no") int no) {
		System.out.println("controller deleteForm");
		System.out.println("deleteForm no = " + no);

		// 파라미터 값 포워드
		return "deleteForm";
	}

	@RequestMapping(value = "/delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(@ModelAttribute GuestVo guestVo, Model model) {
		System.out.println("controller delete");
		System.out.println("delete : " + guestVo); // 테스트

		int count = guestDao.delete(guestVo);

		if (count == 1) {

			return "redirect:/guest/list";
		} else {
			// 삭제 실패여부를 위해 count를 보낸다
			return "redirect:/guest/deleteForm?no="+guestVo.getNo()+"&result="+count;
			
			
		}
	}
}
