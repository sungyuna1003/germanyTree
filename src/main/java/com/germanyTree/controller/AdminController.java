package com.germanyTree.controller;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.germanyTree.service.CategoryService;

@Controller
public class AdminController {

	@Inject
	private CategoryService ctService;
	@Inject
	private JavaMailSenderImpl mailSender;//root-context.xml »ý¼º
	
	@RequestMapping(value = "/adminContact", method = RequestMethod.GET)
	public String adminContact(Model model) throws Exception{
		model.addAttribute("ctg1", ctService.selectGubun1(1));
		model.addAttribute("ctg2", ctService.selectGubun1(2));
		model.addAttribute("ctg3", ctService.selectGubun1(3));
		model.addAttribute("ctg4", ctService.selectGubun1(4));
		return "admin/adminContact";
	}

	@RequestMapping(value = "/mailSend", method = RequestMethod.POST)
	public String mailSend(@RequestParam("userName")String name,
			@RequestParam("userEmail")String fromEmail,
			@RequestParam("message")String text,
			@RequestParam("adminEmail")String toEmail) throws Exception{
		
		final MimeMessagePreparator preparator = new MimeMessagePreparator() { 
			@Override public void prepare(MimeMessage mimeMessage) throws Exception { 
				final MimeMessageHelper helper = 
						new MimeMessageHelper(mimeMessage, true, "UTF-8"); 
		helper.setFrom(fromEmail); 
		helper.setTo(toEmail); 
		helper.setSubject(name +" GET IN TOUCH"); 
		helper.setText(text, true); } };
		mailSender.send(preparator);
		
		return "redirect:adminContact";
	}
}
















