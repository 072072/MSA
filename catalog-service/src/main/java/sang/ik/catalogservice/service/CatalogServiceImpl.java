package sang.ik.catalogservice.service;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sang.ik.catalogservice.jpa.CatalogEntity;
import sang.ik.catalogservice.jpa.CatalogRepository;

@Data
@Slf4j
@RequiredArgsConstructor
@Service
public class CatalogServiceImpl implements CatalogService{

    private final CatalogRepository catalogRepository;

    @Override
    public Iterable<CatalogEntity> getAllCatalogs() {
        return catalogRepository.findAll();
    }
}
