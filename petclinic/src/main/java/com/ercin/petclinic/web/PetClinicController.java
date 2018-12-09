package com.ercin.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ercin.petclinic.service.PetClinicService;

@Controller
@RequestMapping("/petclinic")
public class PetClinicController {
	
	@Autowired
	private PetClinicService petclinicService;
	
	@RequestMapping("/owners")
	public ModelAndView getOwners() {
		ModelAndView mav=new ModelAndView();
		
		mav.addObject("owners",petclinicService.findOwners()); //addObject metodu içindeki ilk parametre nesnenin ismidir.
		//Yukarıdaki örnekte petclinic servisinin içindeki findOwners metodundan dönen değer owners isimli nesneye set edildi.
		//Bu owners isimli nesne ise ilgili jsp dosyasının içinde kullandığımız nesne ismiyle aynı olmalıdır.Yani modeldeki veriyi view katmanına gönderdik.
		
		mav.setViewName("owners"); //setViewName metodu jsp dosyasını döndürür.İçine ilgili jsp dosyasının ismi yazılmalıdır.
		return mav;
	}
	
	//ResponseBody anotasyonu olmazsa controller returnde yazan stringi view olarak handle etmeye çalışır.
	//Bu da hata verir.Eğer uygulamamızda disphacterservlet kullanırsak ResponseBody anotasyonuna gerek kalmaz.
	@RequestMapping("/pcs")
	@ResponseBody
	public String welcome() {
		return "welcome petclinic";
	}
	
}
