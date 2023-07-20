package ssu.swcontest2023.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import ssu.swcontest2023.domain.Product;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class JpaProductRepository implements ProductRepository {

    private final EntityManager em;

    @Autowired
    public JpaProductRepository(EntityManager em){
        this.em = em;
    }

    @Override
    public Product save(Product product) {
        em.persist(product);
        return product;
    }

    //@Override
    public int saveList(List<Product> products) {
        //* 내용부 수정하기
        return 0;
    }

    @Override
    public Optional<Product> findById(Long id) {
        //return Optional.empty();
        Product product = em.find(Product.class, id);
        return Optional.ofNullable(product);
    }

    @Override
    public Optional<Product> findByName(String name) {
        //return Optional.empty();
        List<Product> result = em.createQuery("select p from Product p where p.name = :name", Product.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    //Tips!!: ctrl+alt+n 인라인 단축키
    @Override
    public List<Product> findAll() {
        //return null;
        //객체를 대상으로 쿼리 날리기
        return em.createQuery("select p from Product p", Product.class)
                .getResultList();
    }
}
