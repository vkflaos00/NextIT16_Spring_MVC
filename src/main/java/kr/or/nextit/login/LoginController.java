package kr.or.nextit.login;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.or.nextit.code.service.CommCodeServiceImpl;
import kr.or.nextit.code.service.ICommCodeService;
import kr.or.nextit.code.vo.CodeVO;
import kr.or.nextit.member.service.IMemberService;
import kr.or.nextit.member.service.MemberServiceImpl;
import kr.or.nextit.member.vo.MemberVO;

@Controller
public class LoginController {

	//@Autowired
	//@Inject
	@Resource(name="codeService")
	private ICommCodeService codeService;
	
	
	@Autowired
	private IMemberService memberService;
	
	
	
	//@RequestMapping("/login")
	//@RequestMapping( value="/login", method = RequestMethod.POST )
	//@RequestMapping( value="/login", method = RequestMethod.GET)
	@GetMapping("/login")
	public String login() {
		System.out.println("LoginController login");
		return "/login/login";
	}
	
	
	@GetMapping("/login/{msg}")
	public String loginMsg (@PathVariable String msg, Model model) {
		System.out.println("LoginController loginMsg");
		System.out.println("msg : "+ msg);
		
		model.addAttribute("msg", msg);
		return "/login/login";
	}
	
	/*@RequestMapping("/login/join")
	public String join(Model model) {
		System.out.println("LoginController join");
	
		List<CodeVO> jobList = codeService.getCodeListByParent("JB00");
		model.addAttribute("jobList", jobList);

		List<CodeVO> hobbyList = codeService.getCodeListByParent("HB00");
		model.addAttribute("hobbyList", hobbyList);
		
		return "/login/join";
	}*/
	
	
	@RequestMapping("/login/join")
	public ModelAndView join(ModelAndView mav) {
		System.out.println("LoginController join");
	
		List<CodeVO> jobList = codeService.getCodeListByParent("JB00");
		//model.addAttribute("jobList", jobList);
		mav.addObject("jobList", jobList);

		List<CodeVO> hobbyList = codeService.getCodeListByParent("HB00");
		//model.addAttribute("hobbyList", hobbyList);
		mav.addObject("hobbyList", hobbyList);
		
		mav.setViewName("/login/join");
		return mav;
	}
	
	
	@RequestMapping("/login/loginCheck")
	public String loginCheck(@ModelAttribute MemberVO member
			, HttpServletRequest request
			, HttpServletResponse response) {
		System.out.println("LoginController loginCheck");
		//return "redirect:/home";
		
		//MemberVO member = new MemberVO();
		//BeanUtils.populate(member, request.getParameterMap());
		System.out.println("loginCheck member.toString(): "+ member.toString());
		
		//IMemberService memberService = new MemberServiceImpl();
		try{
			boolean loginCheck = memberService.loginCheck(member, request, response);
			if(loginCheck) {
				//return "redirect:/home/home.do";
				return "redirect:/home";
			}else {
				//return "redirect:/login/login.do?msg=fail";
				return "redirect:/login/fail";
			}
		}catch(Exception e){
			e.printStackTrace();
			//return "redirect:/login/login.do?msg=error";
			return "redirect:/login/error";
		}
		
	}
	
	@RequestMapping("/home")
	public String home() {
		System.out.println("LoginController home");
		return "/home/home";
	}
	
	@RequestMapping("/login/logout")
	public String logout(HttpServletRequest request) {
		System.out.println("LoginController logout");
		
		request.getSession().removeAttribute("memberVO");
		return "redirect:/login";
	}
	
}
