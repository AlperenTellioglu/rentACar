package kodlama.io.rentACar.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import kodlama.io.rentACar.business.abstracts.BrandService;
import kodlama.io.rentACar.business.requests.CreateBrandRequest;
import kodlama.io.rentACar.business.responses.GetAllBrandsResponse;
import kodlama.io.rentACar.business.responses.GetByIdBrandResponse;
import kodlama.io.rentACar.entities.concretes.Brand;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/brands")
public class BrandsController {
	private BrandService brandService;

	@GetMapping("/list")
	public String listBrands(Model theModel) {

		List<GetAllBrandsResponse> brands = brandService.getAll();
		// add to the spring model
		theModel.addAttribute("brands", brands);

		return "list-brands";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {

		Brand brand = new Brand();

		model.addAttribute("brand", brand);

		return "brand-form";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("brandId") int id, Model theModel) {
		GetByIdBrandResponse brand = brandService.getById(id);
		
		theModel.addAttribute("brand", brand);
		
		return "brand-form";
	}

	@PostMapping("/save")
	//@ResponseStatus(code=HttpStatus.CREATED)
	public String saveBrand(@Valid @ModelAttribute("brand") CreateBrandRequest brand) {
		brandService.add(brand);
		
		return "redirect:/brands/list";
	}
}
