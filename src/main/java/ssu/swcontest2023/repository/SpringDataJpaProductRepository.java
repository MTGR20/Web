package ssu.swcontest2023.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssu.swcontest2023.domain.Product;

import java.util.Optional;

//select m from Member m where m.name = ?
public interface SpringDataJpaProductRepository extends JpaRepository<Product, Long>, ProductRepository{
    @Override
    Optional<Product> findById(Long id);

    @Override
    Optional<Product> findByName(String name);
}
