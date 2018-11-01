package com.xgh.model.query.enumerator;

import com.xgh.Constants;
import com.xgh.infra.service.BasicQueryService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class EnumeratorQueryService extends BasicQueryService<Enumerator, EnumeratorRepository> {

    @Autowired
    public EnumeratorQueryService(EnumeratorRepository repository) {
        super(repository);
    }
    
    public Page<Enumerator> findByTypeContainingIgnoreCase(int page, String kind) {
        PageRequest request = PageRequest.of(page, Constants.PAGE_SIZE.asInteger());
        return this.repository.findByKindContainingIgnoreCase(request,kind);
    }

	public ArrayList<EnumeratorGroup> findAllGroupingByKind() {

        boolean found;

		List<Enumerator> allKinds = this.repository.findAll();				  
		
		ArrayList<EnumeratorGroup> allKindsGrouped = new ArrayList<EnumeratorGroup>();	
		
		for(int i = 0; i < allKinds.size(); i++) { 
				
			Enumerator current = allKinds.get(i);

            found = false;

			for (EnumeratorGroup enumeratorGroup : allKindsGrouped) {


				if (enumeratorGroup.getName().equals(current.getKind())) {
					found = true;
					if(!current.isDeleted()){
						enumeratorGroup.addEnumerator(allKinds.get(i));
					}
				}											
			}
			
			if (!found & !current.isDeleted()) {
				allKindsGrouped.add( new EnumeratorGroup(current.getKind(), allKinds.get(i)) );
			}

		}		
		
		return allKindsGrouped;
	}
}
