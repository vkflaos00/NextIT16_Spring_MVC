package kr.or.nextit.free;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import kr.or.nextit.code.service.ICommCodeService;
import kr.or.nextit.code.vo.CodeVO;
import kr.or.nextit.exception.BizNotEffectedException;
import kr.or.nextit.exception.BizNotFoundException;
import kr.or.nextit.exception.BizPasswordNotMatchedException;
import kr.or.nextit.exception.DaoException;
import kr.or.nextit.free.service.FreeBoardServiceImpl;
import kr.or.nextit.free.service.IFreeBoardService;
import kr.or.nextit.free.vo.FreeBoardSearchVO;
import kr.or.nextit.free.vo.FreeBoardVO;

@Controller
@RequestMapping("/free")
public class FreeBoardController {

	@Resource(name = "codeService")
	private ICommCodeService codeService;

	@ModelAttribute("categoryList")
	public List<CodeVO> categoryList() {
		return codeService.getCodeListByParent("BC00");
	}

	@Resource(name = "freeBoardService")
	private IFreeBoardService freeBoardService;

	@RequestMapping(value = "/freeList")
	public String freeList(@ModelAttribute("searchVO") FreeBoardSearchVO searchVO, Model model) {
		System.out.println("FreeBoardController freeList");

		try {
			List<FreeBoardVO> freeBoardList = freeBoardService.getBoardList(searchVO);
			model.addAttribute("freeBoardList", freeBoardList);
			//request.setAttribute("searchVO", searchVO);
			//model.addAttribute("searchVO", searchVO);
		} catch (BizNotEffectedException bne) {
			model.addAttribute("bne", bne);
			bne.printStackTrace();
		} catch (Exception de) {
			model.addAttribute("de", de);
			de.printStackTrace();
		}
		return "/free/freeList";
	}

	@RequestMapping("/freeForm")
	public String freeForm() {
		System.out.println("FreeBoardController freeForm");

		return "/free/freeForm";
	}

	@RequestMapping(value = "/freeRegister", method = RequestMethod.POST)
	public String freeRegister(@ModelAttribute FreeBoardVO freeBoard, Model model) {
		System.out.println("FreeBoardController freeRegister");

		try {

			if (freeBoard.getBoTitle() != null && !freeBoard.getBoTitle().equals("")) {
				freeBoardService.registerBoard(freeBoard);
			} else {
				throw new Exception();
			}
			return "redirect:/free/freeList";

		} catch (BizNotEffectedException bne) {
			model.addAttribute("bne", bne);
			bne.printStackTrace();
		} catch (Exception de) {
			model.addAttribute("de", de);
			de.printStackTrace();
		}

		return "/free/freeRegister";
	}

	@RequestMapping("/freeView")
	public String freeView(@ModelAttribute("searchVO") FreeBoardSearchVO searchVO
			, @RequestParam String boNo,
			Model model) {
		System.out.println("FreeBoardController freeView");

		
		//String boNo = request.getParameter("boNo");
		try {
			FreeBoardVO freeBoard = null;
			if (boNo != null && !boNo.equals("")) {
				freeBoard = freeBoardService.getBoard(boNo);
			} else {
				throw new Exception();
			}

			freeBoardService.increaseHit(boNo);

			System.out.println("freeBoard: " + freeBoard.toString());

			model.addAttribute("freeBoard", freeBoard);

		} catch (BizNotEffectedException bne) {
			model.addAttribute("bne", bne);
			bne.printStackTrace();
		} catch (Exception de) {
			model.addAttribute("de", de);
			de.printStackTrace();
		}

		return "/free/freeView";
	}

	@RequestMapping("/freeEdit")
	public String freeEdit(@ModelAttribute("searchVO") FreeBoardSearchVO searchVO
			, @RequestParam String boNo,
			Model model) {
		System.out.println("FreeBoardController freeEdit");

		try {
			FreeBoardVO freeBoard = null;
			if (boNo != null && !boNo.equals("")) {
				freeBoard = freeBoardService.getBoard(boNo);
			} else {
				throw new Exception();
			}

			freeBoardService.increaseHit(boNo);
			model.addAttribute("freeBoard", freeBoard);

		} catch (BizNotEffectedException bne) {
			model.addAttribute("bne", bne);
			bne.printStackTrace();
		} catch (Exception de) {
			model.addAttribute("de", de);
			de.printStackTrace();
		}
		return "/free/freeEdit";
	}

	@RequestMapping(value = "/freeModify", method = RequestMethod.POST)
	public String freeModify(@ModelAttribute FreeBoardVO freeBoard, Model model) {
		System.out.println("FreeBoardController freeModify");
		try {
			if (freeBoard.getBoNo() != null && !freeBoard.getBoNo().equals("")) {
				freeBoardService.modifyBoard(freeBoard);
			} else {
				throw new Exception();
			}
			return "redirect:/free/freeView?boNo=" + freeBoard.getBoNo();

		} catch (BizNotFoundException bnf) {
			model.addAttribute("bnf", bnf);
			bnf.printStackTrace();
		} catch (BizPasswordNotMatchedException bpn) {
			model.addAttribute("bpn", bpn);
			bpn.printStackTrace();
		} catch (BizNotEffectedException bne) {
			model.addAttribute("bne", bne);
			bne.printStackTrace();
		} catch (Exception de) {
			model.addAttribute("de", de);
			de.printStackTrace();
		}
		return "/free/freeModify";
	}

	@RequestMapping(value = "/freeDelete", method = RequestMethod.POST)
	public String freeDelete(@ModelAttribute FreeBoardVO freeBoard, Model model) {
		System.out.println("FreeBoardController freeDelete");

		try {
			if (freeBoard.getBoNo() != null && !freeBoard.getBoNo().equals("")) {
				freeBoardService.deleteBoard(freeBoard);
			} else {
				throw new Exception();
			}
			return "redirect:/free/freeList";

		} catch (BizNotEffectedException bne) {
			model.addAttribute("bne", bne);
			bne.printStackTrace();
		} catch (BizPasswordNotMatchedException bpn) {
			model.addAttribute("bpn", bpn);
			bpn.printStackTrace();
		} catch (Exception de) {
			model.addAttribute("de", de);
			de.printStackTrace();
		}
		return "/free/freeDelete";
	}

	@RequestMapping(value = "/freeHide", method = RequestMethod.POST)
	public String freeHide(@RequestParam String memId, @RequestParam String boNo, Model model) {
		System.out.println("FreeBoardController freeHide");

		try {
			if (boNo != null && !boNo.equals("")) {
				freeBoardService.hideBoard(memId, boNo);
			} else {
				throw new BizNotEffectedException();
			}

			return "redirect:/free/freeList";

		} catch (BizNotEffectedException bne) {
			model.addAttribute("bne", bne);
			bne.printStackTrace();
		} catch (DaoException de) {
			model.addAttribute("de", de);
			de.printStackTrace();
		}
		return "/free/freeHide";
	}

}
