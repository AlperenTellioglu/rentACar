package kodlama.io.rentACar.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kodlama.io.rentACar.business.abstracts.ModelService;
import kodlama.io.rentACar.business.responses.GetAllModelsResponse;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/models")
public class ModelsController {
	private ModelService modelService;
	
	@GetMapping("/list")
	public String  listModels(Model theModel) {
		
		List<GetAllModelsResponse> models = modelService.getAll();
		
		theModel.addAttribute("models", models);
		
		return "list-models";
	}
}
