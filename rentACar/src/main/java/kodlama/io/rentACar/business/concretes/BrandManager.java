package kodlama.io.rentACar.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.rentACar.business.abstracts.BrandService;
import kodlama.io.rentACar.business.requests.CreateBrandRequest;
import kodlama.io.rentACar.business.responses.GetAllBrandsResponse;
import kodlama.io.rentACar.core.utilities.mappers.ModelMapperService;
import kodlama.io.rentACar.dataAccess.abstracts.BrandRepository;
import kodlama.io.rentACar.entities.concretes.Brand;
import lombok.AllArgsConstructor;

@Service // bu sınıf bir business nesnesidir.
@AllArgsConstructor
public class BrandManager implements BrandService {
	private BrandRepository brandRepository;
	private ModelMapperService modelMapperService;

	@Override
	public List<GetAllBrandsResponse> getAll() {
		// iş kuralları

		List<Brand> brands = brandRepository.findAll();

//		List<GetAllBrandsResponse> brandsResponse = new ArrayList<GetAllBrandsResponse>();
//		
//		for (Brand brand : brands) {
//			GetAllBrandsResponse responseItem = new GetAllBrandsResponse();
//			responseItem.setId(brand.getId());
//			responseItem.setName(brand.getName());
//			brandsResponse.add(responseItem);
//		} Aşağıdaki kod bu for döngüsünün yaptığı işi daha kolay bir biçimde yapıyor!

		List<GetAllBrandsResponse> brandsResponse = brands.stream()
				.map(brand->this.modelMapperService.forResponse()
						.map(brand, GetAllBrandsResponse.class)).collect(Collectors.toList());
		
		return brandsResponse;
	}

	@Override
	public void add(CreateBrandRequest createBrandRequest) {
		Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		
		this.brandRepository.save(brand);
		
	}

}
