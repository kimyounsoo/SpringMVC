package imageboard.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import imageboard.bean.ImageboardDTO;
import imageboard.bean.ImageboardPaging;
import imageboard.service.ImageboardService;

@Controller
@RequestMapping(value="imageboard")
public class ImageboardController {
	@Autowired
	private ImageboardService imageboardService;
	
	@RequestMapping(value="imageboardWriteForm", method=RequestMethod.GET)
	public String imageboardWriteForm(Model model) {
		model.addAttribute("display","/imageboard/imageboardWriteForm.jsp");
		return "/index";
	}
	
//	name="img" 1개일 경우
//	@RequestMapping(value="imageboardWrite", method=RequestMethod.POST)
//	@ResponseBody
//	public void imageboardWrite(@ModelAttribute ImageboardDTO imageboardDTO,
//								@RequestParam MultipartFile img) {
//		
//		String filePath = "C:\\Spring\\workspace\\SpringMVC\\src\\main\\webapp\\storage";
//		String fileName = img.getOriginalFilename();
//		File file = new File(filePath, fileName);
//		
//		//파일 복사
//		try {
//			FileCopyUtils.copy(img.getInputStream(), new FileOutputStream(file));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		imageboardDTO.setImage1(fileName);
//	}
//---------------------------------------------	
	
//	name="img" 2개일 경우 - 배열로 받는다
//	@RequestMapping(value="imageboardWrite", method=RequestMethod.POST)
//	@ResponseBody
//	public void imageboardWrite(@ModelAttribute ImageboardDTO imageboardDTO,
//								@RequestParam MultipartFile[] img) {
//		
//		String filePath = "C:\\Spring\\workspace\\SpringMVC\\src\\main\\webapp\\storage";
//		String fileName;
//		File file;
//		
//		//파일 복사
//		if(img[0] != null) {
//			fileName = img[0].getOriginalFilename();
//			file = new File(filePath, fileName);
//			
//			try {
//				FileCopyUtils.copy(img[0].getInputStream(), new FileOutputStream(file));
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			
//			imageboardDTO.setImage1(fileName);
//		}else {
//			imageboardDTO.setImage1("");
//		}
//		//----------
//		if(img[1] != null) {
//			fileName = img[1].getOriginalFilename();
//			file = new File(filePath, fileName);
//			
//			try {
//				FileCopyUtils.copy(img[1].getInputStream(), new FileOutputStream(file));
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			
//			imageboardDTO.setImage2(fileName);
//		}else {
//			imageboardDTO.setImage2("");
//		}
//	}
//---------------------------------------------	
	
//	드래그해서 한번에 여러개의 파일을 선택 
	@RequestMapping(value="imageboardWrite", method=RequestMethod.POST)
	@ResponseBody
	public void imageboardWrite(@ModelAttribute ImageboardDTO imageboardDTO,
								@RequestParam("img[]") List<MultipartFile> list) {
		
		String filePath = "C:\\Spring\\workspace\\SpringMVC\\src\\main\\webapp\\storage";
		
		//List는 당연히 포문 돌린다
		for(MultipartFile img : list) {
			String fileName = img.getOriginalFilename();
			File file = new File(filePath, fileName);

			//파일 복사
			try {
				FileCopyUtils.copy(img.getInputStream(), new FileOutputStream(file));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			imageboardDTO.setImage1(fileName);
			imageboardDTO.setImage2("");// null 아니고 ""처리
			
			//DB
			imageboardService.imageboardWrite(imageboardDTO);// for문 안에 있어야 이미지 전부다 올라감
		}//for
	}
	
	@RequestMapping(value="imageboardList", method=RequestMethod.GET)
	public String imageboardList(@RequestParam(required=false, defaultValue="1") String pg,
								 Model model) {
		
		model.addAttribute("pg", pg);
		model.addAttribute("display", "/imageboard/imageboardList.jsp");
		return "/index";
	}
	
	@RequestMapping(value="getImageboardList", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView getImageboardList(@RequestParam String pg) {
		//1페이지당 5개씩
		List<ImageboardDTO> list = imageboardService.getImageboardList(pg);
		
		//페이징 처리
		ImageboardPaging imageboardPaging = imageboardService.imageboardPaging(pg);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("pg", pg);
		mav.addObject("list", list);
		mav.addObject("imageboardPaging", imageboardPaging);
		mav.setViewName("jsonView");
		return mav;
	}
	
	@RequestMapping(value="imageboardView", method=RequestMethod.GET)
	public String imageboardView(@RequestParam String seq,
								 @RequestParam String pg,
								 Model model) {
		model.addAttribute("seq", seq);
		model.addAttribute("pg", pg);
		model.addAttribute("display", "/imageboard/imageboardView.jsp");
		return "/index";
	}
	
	@RequestMapping(value="getImageboardView", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView getImageboardView(@RequestParam String seq) {
		ImageboardDTO imageboardDTO = imageboardService.getImageboardView(seq);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("imageboardDTO", imageboardDTO);
		mav.setViewName("jsonView");
		return mav;
	}
	
//	@RequestMapping(value="imageboardDelete", method=RequestMethod.GET)
//	public String imageboardDelete(@RequestParam String[] check, Model model) {
//		imageboardService.imageboardDelete(check);
//		
//		model.addAttribute("display", "/imageboard/imageboardDelete.jsp");
//		return "/index";
//	}
	
	//위 주석은 "삭제되었습니다" 하나띄울려고 imageboardDelete.jsp 로 보내는데(바로가는건 아님)
	//이건 redirect 로 imageboardList직접 부르는것이다
	//imageboardList.jsp 를 직접부르는것이 아니라 위에 value="imageboardList" 를 부르는것이다
	//대신 imageboardDelete.jsp 같은 jsp를 거치지 않기 때문에 얼럿은 안뜬다
	@RequestMapping(value="imageboardDelete", method=RequestMethod.GET)
	public ModelAndView imageboardDelete(String[] check) {
		imageboardService.imageboardDelete(check);
		
		return new ModelAndView("redirect:/imageboard/imageboardList");
	}
}








