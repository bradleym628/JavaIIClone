package dmacc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import dmacc.beans.Phone;
import dmacc.repository.PhoneRepository;

/**
 * Abutalib Hasan - amhasan
 * 202101 CIS171 12928
 * Dec 7, 2021
 */
@Controller
public class PhonesListController {
	
	@Autowired
	PhoneRepository rep;
	
	@GetMapping("/lists")
	public String listsPage() {
		return "lists";
	}
	
	@GetMapping("/stacks")
	public String stacksPage() {
		return "stacks";
	}
    
	// will add a customer to the database
		@GetMapping("/addPhoneToList")
		public String addNewPhoneToList(Model model) {
			Phone p = new Phone();
			model.addAttribute("newP", p);
			return "inputPhone";
		}
		
		@PostMapping("/addPhoneToList")
		public String addNewPhoneToList(@ModelAttribute Phone p, Model model) {
		rep.save(p);
			return viewPhoneList(model);
			
		}
		
		@GetMapping("/viewPhonesFromList")
		public String viewPhoneList(Model model) {
			if(rep.findAll().isEmpty()) {
				return addNewPhoneToList(model);
			}
			
			 List<Phone> allphones = rep.findAll();
			model.addAttribute("phoneList", allphones);
			return "cellPhoneList";
		}
		
		@GetMapping("/edit/{id}")
		public String editPhoneL(@PathVariable("id") long id, Model model) {
			Phone p = rep.findById(id).orElse(null);
			model.addAttribute("newP", p);
			return "inputPhone";
		}
		
		@PostMapping("/update/{id}")
		public String updatePhoneL(@ModelAttribute Phone p, Model model) {
			rep.save(p);
			return viewPhoneList(model);
		}
		
		@GetMapping("/delete/{id}")
		public String deletePhoneL(@PathVariable("id") long id, Model model) {
			Phone p = rep.findById(id).orElse(null);
			rep.delete(p);
			return viewPhoneList(model);
		}
	
}
