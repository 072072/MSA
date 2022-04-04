package sang.ik.catalogservice.service;

import sang.ik.catalogservice.jpa.CatalogEntity;

public interface CatalogService {

    Iterable<CatalogEntity> getAllCatalogs();
}
