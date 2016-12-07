package com.ktdsuniv.admin.user.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ktdsuniv.admin.user.service.UserService;

import common.pageVO.PageListVO;
import common.pageVO.SearchVO;
import common.util.pager.ClassicPageExplorer;
import common.util.pager.PageExplorer;
import user.schema.AdminsSchema;
import user.schema.InstructorsSchema;
import user.schema.UsersSchema;


@Controller
public class UserController {

	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	
	/*
	 * 관리자 메인 페이지
	 * 일반회원, 강사, 팀, 프로젝트, 게시판, 호실, 강의 등 불러올 수 있는 리스트는 다 불러와서 최신 3개만 보여 주신 식으로 진행
	 * 관리 기능의 경우 각 리스트별로 분리해서 진행
	 */
	@RequestMapping("/admin/adminMain")
	public ModelAndView viewMainPage(SearchVO search){
		ModelAndView view = new ModelAndView();
		PageListVO userList = userService.getUserList(search);
		PageListVO instructorList = userService.getInstructorList(search);
		view.addObject("userList", userList);
		view.addObject("instructorList", instructorList);
		view.setViewName("adminMain");
		return view;
	}
	
	@RequestMapping("/admin/instructorRegister")
	public ModelAndView viewInstructorRegisterPage() {		
		ModelAndView view = new ModelAndView();
		view.setViewName("/instructor/instructorRegister");
		return view;		
	}
	
	@RequestMapping("/admin/instructorDoRegister")
	public String doInstructorRegister(UsersSchema users, @RequestParam String agency) {
		InstructorsSchema instructors = new InstructorsSchema();
		instructors.setAgency(agency);
		instructors.setUser(users);
		
		userService.addInstructor(instructors);
		return "redirect:/admin/adminMain";
	}
	
	@RequestMapping("/admin/adminRegister")
	public ModelAndView viewAdminRegisterPage() {
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/adminRegister");
		return view;
	}
	
	@RequestMapping("/admin/adminDoRegister")
	public String doAdminRegister(UsersSchema users, @RequestParam String department, @RequestParam String position) {
		AdminsSchema admins = new AdminsSchema();
		admins.setDepartment(department);
		admins.setPosition(position);
		admins.setUser(users);
		
		userService.addAdmin(admins);
		return "redirect:/adminMain";
	}
	
	@RequestMapping("/admin/adminSignIn")
	public ModelAndView viewSignInPage() {
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/adminSignIn");
		return view;
	}
	
	@RequestMapping("/admin/doAdminSignIn")
	public String doSignInAction(UsersSchema user, HttpSession session) {
		userService.adminSignIn(user, session);
		return "redirect:/admin/adminMain";
	}
	

	/*
	 * 유저 리스트 
	 * 완성
	 */
	@RequestMapping("/user/list")
	public ModelAndView viewUserList(SearchVO search){
		ModelAndView view = new ModelAndView();
		PageListVO userList = userService.getUserList(search);
		PageExplorer pageExplorer = new ClassicPageExplorer(userList.getPager());
		String pager = pageExplorer.getPagingList("pageNumber", "[@]", "<<", ">>", "searchForm");
		view.addObject("paging", pager);
		view.addObject("search", search);
		view.addObject("userList", userList);
		view.setViewName("user/list");
		return view;
	}
	
	/*
	 * 유저 상세보기
	 * 완성
	 */
	@RequestMapping("/user/detail/{userId}")
	public ModelAndView viewDetailUserPage(@PathVariable String userId){
		ModelAndView view = new ModelAndView();
		UsersSchema user = userService.getUserById(userId);
		view.addObject("user", user);
		view.setViewName("user/detail");
		return view;
	}
	
	/*
	 * 유저 정보 수정
	 * 완성
	 */
	@RequestMapping("/user/modify/{userId}")
	public ModelAndView viewModifyUserPage(@PathVariable String userId){
		ModelAndView view = new ModelAndView();
		UsersSchema user = userService.getUserById(userId);
		view.addObject("user", user);
		view.setViewName("user/modify");
		return view;
	}
	
	/*
	 * 유저정보 수정 처리
	 * 만듬
	 */
	@RequestMapping("/user/doModify")
	public ModelAndView doModifyUserInfo(UsersSchema usersSchema){
		ModelAndView view = new ModelAndView();
		boolean isSuccess = userService.doModifyUserInfo(usersSchema);
		view.setViewName("redirect:/user/detail/"+usersSchema.getId());
		return view;
	}
	
	/*
	 * 유저정보 삭제
	 * 완성
	 */
	@RequestMapping("/user/doDelete")
	public ModelAndView doDeleteUserInfo(@RequestParam List<String> users){
		ModelAndView view = new ModelAndView();
		boolean isSuccess = userService.doDeleteUserInfo(users);
		view.setViewName("redirect:/user/list");
		return view;
	}
	
	/*
	 * 강사 리스트
	 */
	@RequestMapping("/instructor/list")
	public ModelAndView viewInstructorList(SearchVO search){
		ModelAndView view = new ModelAndView();
		PageListVO instructorList = userService.getInstructorList(search);
		PageExplorer pageExplorer = new ClassicPageExplorer(instructorList.getPager());
		String pager = pageExplorer.getPagingList("pageNumber", "[@]", "<<", ">>", "searchForm");
		view.addObject("paging", pager);
		view.addObject("search", search);
		view.addObject("instructorList", instructorList);
		view.setViewName("instructor/list");
		return view;
	}
	
	/*
	 * 강사 디테일
	 */
	@RequestMapping("/instructor/detail/{istructorId}")
	public ModelAndView viewDetaiInstructorPage(@PathVariable String istructorId){
		ModelAndView view = new ModelAndView();
		InstructorsSchema instructor = userService.getInstructorById(istructorId);
		view.addObject("instructor", instructor);
		view.setViewName("instructor/detail");
		return view;
	}
	
	/*
	 * 강사 수정
	 */
	@RequestMapping("/instructor/modify/{istructorId}")
	public ModelAndView viewModifyinstructorPage(@PathVariable String istructorId){
		ModelAndView view = new ModelAndView();
		InstructorsSchema instructor = userService.getInstructorById(istructorId);
		view.addObject("instructor", instructor);
		view.setViewName("instructor/modify");
		return view;
	}
	
	/*
	 * 강사 수정 처리
	 */
	@RequestMapping("/instructor/doModify")
	public ModelAndView doModifyInstructorInfo(UsersSchema user, @RequestParam String agency){
		ModelAndView view = new ModelAndView();
		InstructorsSchema instructor = new InstructorsSchema();
		instructor.setAgency(agency);
		instructor.setUser(user);
		
		boolean isSuccess = userService.doModifyInstructorInfo(instructor);
		view.setViewName("redirect:/instructor/detail/"+instructor.getId());
		return view;
	}
	
	/*
	 * 강사정보 삭제
	 */
	@RequestMapping("/instructor/doDelete")
	public ModelAndView doDeleteInstructorInfo(@RequestParam List<String> users){
		ModelAndView view = new ModelAndView();
		boolean isSuccess = userService.doDeleteInstructorInfo(users);
		view.setViewName("redirect:/instructor/list");
		return view;
	}
	
	
	@RequestMapping("/admin/adminSignOut")
	public String doSignOutAction(HttpSession session) {
		session.invalidate();
		return "redirect:/admin/adminSignIn";
	}
}
