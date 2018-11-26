package com.xgh.model.query.operational.enumerator;

import com.xgh.infra.service.BasicQueryService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnumeratorQueryService extends BasicQueryService<Enumerator, EnumeratorRepository> {

    @Autowired
    public EnumeratorQueryService(EnumeratorRepository repository) {
        super(repository);
    }
    
    public List<Enumerator> findByDeletedFalseAndKindContainingIgnoreCase(int page, String kind) {
        return this.repository.findByDeletedFalseAndKindContainingIgnoreCase(kind);
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
